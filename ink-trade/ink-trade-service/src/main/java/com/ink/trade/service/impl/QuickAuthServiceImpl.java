package com.ink.trade.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ink.asile.core.manager.IAsileResCodeManager;
import com.ink.asile.core.manager.IAsileSignManager;
import com.ink.asile.core.po.AsileResCode;
import com.ink.asile.core.po.AsileSign;
import com.ink.base.log.util.YinkerLogger;
import com.ink.base.utils.BeanCopyConverter;
import com.ink.base.utils.IdCodeGenerator;
import com.ink.channel.api.model.in.QuickAgainValidCodeInput;
import com.ink.channel.api.model.in.QuickAuthInput;
import com.ink.channel.api.model.out.QuickAgainValidCodeOutput;
import com.ink.channel.api.model.out.QuickAuthOutput;
import com.ink.channel.api.service.QuickAgainValidCodeService;
import com.ink.channel.api.service.QuickAuthService;
import com.ink.trade.api.enums.OrderStatus;
import com.ink.trade.api.enums.PayType;
import com.ink.trade.api.enums.RouteBusinessType;
import com.ink.trade.api.enums.TradeType;
import com.ink.trade.api.model.in.AsileRouteInput;
import com.ink.trade.api.model.in.TradeQuickAuthInput;
import com.ink.trade.api.model.out.AsileRouteOutput;
import com.ink.trade.api.model.out.TradeQuickAuthOutput;
import com.ink.trade.api.rule.IAsileRoute;
import com.ink.trade.api.service.ITradeQuickAuthService;
import com.ink.trade.core.cnst.TradeLogConstant;
import com.ink.trade.core.cnst.TradeRespConstant;
import com.ink.trade.core.enums.TradeStatus;
import com.ink.trade.core.exception.TradeException;
import com.ink.trade.core.manager.IAuthManager;
import com.ink.trade.core.manager.ITradeOrderManager;
import com.ink.trade.core.po.Auth;
import com.ink.trade.core.po.TradeOrder;
import com.ink.trade.core.query.AuthQuery;
import com.ink.trade.service.check.CheckOptions;
import com.ink.trade.service.check.Order;


/**
 * 
 * @Description 快捷鉴权
 * @author xuguoqi
 * @date 2016年6月27日 下午3:45:45
 */
@Service("quickAuthService")
public class QuickAuthServiceImpl implements ITradeQuickAuthService {
    private YinkerLogger log = YinkerLogger.getLogger(QuickAuthServiceImpl.class);
    
    @Autowired
    @Qualifier("userLegalCheck")
    private CheckOptions check;//用户合法性检查
    @Autowired
    private QuickAuthService quickAuthService;//快捷服务
    @Autowired
    private IAsileRoute asileRoute;//路由
    @Autowired
    private IAsileResCodeManager asileResCodeManager;//return code转化
    @Autowired
    private IAuthManager authManager;//绑卡
    @Autowired
    private IdCodeGenerator idCodeGenerator;//主键生成器
    @Autowired
    private ITradeOrderManager tradeOrderManager;//交易订单
    @Autowired
    private IAsileSignManager asileSignManager;//渠道签约表
    @Autowired
    private QuickAgainValidCodeService quickAgainValidCodeService;//再次发起短信验证接口

    /**
     * 
     * @Description 快捷鉴权接口 
     * @author xuguoqi
     * @date 2016年6月20日 下午3:49:22
     * @param input
     * @return
     */
    @Override
    public TradeQuickAuthOutput quickAuth(TradeQuickAuthInput input) {
        log.info(TradeLogConstant.LOGGER_MODULE_QUICK_PAY, TradeLogConstant.LOGGER_BIZ_QUICK_AUTH, "交易快捷鉴权调用开始"+input.toString());
        TradeQuickAuthOutput out = new TradeQuickAuthOutput();
        out.setMerchantId(input.getMerchantId());
        out.setPayType(input.getPayType());
        out.setTradeCode(input.getTradeCode());
        out.setUserId(input.getUserId());
        try {
            // 交易检查
            Auth auth= check(input);
            // 获取路由
            QuickAuthOutput quickAuthOut;
            TradeOrder order;
            long start=System.currentTimeMillis();
            AsileRouteOutput asileRouteOutput = route(input,auth);
            log.debug("快捷路由结束耗时"+(System.currentTimeMillis()-start));
            if (asileRouteOutput != null && TradeRespConstant.TRADE_SUCCESS.equals(asileRouteOutput.getReponseCode())) {
                // 创建交易订单
               order= createOrder(input, asileRouteOutput.getAsileCode(),auth);
                //调用渠道鉴权
               long startAuth=System.currentTimeMillis();
               //判断是否为首次签约还是再次签约 调用不同接口发送短信验证码
               AsileSign asileSign = this.selectSignIdByChannel(auth, asileRouteOutput.getAsileCode(), input.getPayType());
               if(null!=asileSign){//调用二次发送短信验证接口
            	   quickAuthOut = this.quickAgainValidCode(order,auth,asileSign);
               }else{//首次发送短信验证码
            	   quickAuthOut = authority(auth, order);
               }
               log.info(TradeLogConstant.LOGGER_MODULE_QUICK_PAY, TradeLogConstant.LOGGER_BIZ_QUICK_AUTH, "快捷鉴权渠道调用结束，结果"+quickAuthOut.toString());
               log.debug("快捷鉴权结束耗时"+(System.currentTimeMillis()-startAuth));
            } else {
                // 创建交易订单
                createOrder(input, null,auth);
                log.error(TradeLogConstant.LOGGER_MODULE_QUICK_PAY, TradeLogConstant.LOGGER_BIZ_QUICK_AUTH,"签约申请失败,原因:获取渠道信息失败", input.getSignOrderId());
                out.setReponseCode(TradeRespConstant.NO_USEFUL_CHANNEL);
                out.setReponseMsg(TradeRespConstant.NO_USEFUL_CHANNEL_MSG);
                out.setTradeStatus(TradeStatus.FAIL.getValue());
                return out;
            }
            AsileResCode asileResCode = asileResCodeManager.findByAsileCodeAndAsileResCode(
                            asileRouteOutput.getAsileCode(), quickAuthOut.getResCode());
            // 响应码未映射
            if (asileResCode == null) {
            	log.error(TradeLogConstant.LOGGER_MODULE_QUICK_PAY, TradeLogConstant.LOGGER_BIZ_QUICK_AUTH,"解析响应码未成功：code=" + quickAuthOut.getResCode() + "msg" + quickAuthOut.getResMsg(), null);
                out.setReponseCode(TradeRespConstant.TRADE_SYSERROR);
                out.setReponseMsg(TradeRespConstant.TRADE_SYSERROR_MSG);
                out.setTradeStatus(TradeStatus.FAIL.getValue());
                return out;
            }
            if (TradeRespConstant.TRADE_SUCCESS.equals(asileResCode.getResCode())) {
                 //更新交易订单状态 
                order.setStatus(OrderStatus.PENDING.getValue());
                order.setLastupdateTime(new Date());
                order.setToken(quickAuthOut.getToken());
                order.setRemark(quickAuthOut.getOrgTranFlow());//渠道返回流水号
                tradeOrderManager.updateOrderAndCreateLog(order);
                out.setReponseCode(TradeRespConstant.TRADE_SUCCESS);
                out.setReponseMsg(TradeRespConstant.TRADE_SUCCESS_MSG);
                out.setTradeStatus(TradeStatus.SUCCESS.getValue());
            } else {
                out.setReponseCode(asileResCode.getResCode());
                out.setReponseMsg(asileResCode.getResMsg());
                out.setTradeStatus(TradeStatus.FAIL.getValue());
            }
        } catch (TradeException ex) {
        	log.error(TradeLogConstant.LOGGER_MODULE_QUICK_PAY, TradeLogConstant.LOGGER_BIZ_QUICK_AUTH,"快捷鉴权失败,订单号："+input.getSignOrderId(),ex,"");
            out.setTradeStatus(TradeStatus.FAIL.getValue());
            out.setReponseCode(ex.getCode());
            out.setReponseMsg(ex.getMessage());
        } catch (Exception ex) {
        	log.error(TradeLogConstant.LOGGER_MODULE_QUICK_PAY, TradeLogConstant.LOGGER_BIZ_QUICK_AUTH,"快捷鉴权失败,订单号："+input.getSignOrderId(),ex,"");
            out.setTradeStatus(TradeStatus.FAIL.getValue());
            out.setReponseCode(TradeRespConstant.TRADE_SYSERROR);
            out.setReponseMsg(TradeRespConstant.TRADE_SYSERROR_MSG);
        }
        log.info(TradeLogConstant.LOGGER_MODULE_QUICK_PAY, TradeLogConstant.LOGGER_BIZ_QUICK_AUTH,"交易系统快捷鉴权结束");
        return out;
    }
    
    /**
     * 
     * @Description 查询快捷渠道签约信息
     * @author xuguoqi
     * @date 2016年6月22日 上午10:56:26
     * @param auth
     * @param channelNo
     * @param payType
     * @return
     */
    private AsileSign selectSignIdByChannel(Auth auth,String channelNo,String payType){
    	 AsileSign asileSign = new AsileSign();
         asileSign.setCid(auth.getId());
         asileSign.setChanelNo(channelNo);
         asileSign.setPayType(payType);
         AsileSign selectSignIdByChannel = asileSignManager.selectSignIdByChannel(asileSign);
         return selectSignIdByChannel;
    }

    /**
     * 调用渠道接口鉴权
     * 
     * @param input
     * @param asileCode
     * @return
     */
    private QuickAuthOutput authority(Auth auth,TradeOrder order) {
        // 调用渠道鉴权
        QuickAuthInput quickAuth = new QuickAuthInput();
        quickAuth.setAmount(order.getAmt().toString());
        quickAuth.setBankShort(order.getBankShort());
        quickAuth.setCardNo(auth.getCardNo());
        quickAuth.setIdNo(auth.getIdNo());
        quickAuth.setIdType(auth.getIdType());
        quickAuth.setChannelId(order.getChannelNo());
        quickAuth.setUserName(auth.getUserName());
        quickAuth.setOrderNo(order.getReqId());
        quickAuth.setPhoneNo(auth.getPhoneNo());
        quickAuth.setMerchantNo(auth.getMchId());
        log.info(TradeLogConstant.LOGGER_MODULE_QUICK_PAY, TradeLogConstant.LOGGER_BIZ_QUICK_AUTH, "快捷鉴权渠道调用开始，"+quickAuth.toString());
        return quickAuthService.quickAuth(quickAuth);
    }
    
    /**
     * 
     * @Description 再次发起短信验证码接口
     * @author xuguoqi
     * @date 2016年6月20日 下午5:24:23
     * @param input
     * @param order
     * @return
     */
    private QuickAuthOutput quickAgainValidCode(TradeOrder order,Auth auth,AsileSign asileSign) {
    	QuickAgainValidCodeInput againInput = new QuickAgainValidCodeInput();
    	againInput.setAmount(order.getAmt().toString());
    	againInput.setBankShort(order.getBankShort());
    	againInput.setCardNo(order.getCardNo());
    	againInput.setChannelId(order.getChannelNo());
    	againInput.setIdNo(auth.getIdNo());
    	againInput.setOrderNo(order.getReqId());
    	againInput.setPhoneNo(auth.getPhoneNo());
    	againInput.setUserName(auth.getUserName());
    	againInput.setIdType(auth.getIdType());
    	againInput.setIdentityId(asileSign.getSignId());
    	againInput.setMerchantNo(auth.getMchId());
    	QuickAgainValidCodeOutput againValidCode = quickAgainValidCodeService.againValidCode(againInput);
    	QuickAuthOutput converterClass = BeanCopyConverter.converterClass(againValidCode, QuickAuthOutput.class);
        return converterClass;
    }

    /**
     * 交易检查
     * 
     * @param input
     */
    private Auth check(TradeQuickAuthInput input) {
        // 交易类型检查
        if (!input.getTradeCode().trim().equals(TradeType.RECHARGE.getCode())) {
            throw new TradeException(TradeRespConstant.TRADE_TYPE_ERROR,TradeRespConstant.TRADE_TYPE_ERROR_MSG);
        }
        //用户合法性校验
        Order checkOrder = new Order();
        checkOrder.setMerchantId(input.getMerchantId());
        checkOrder.setUserId(input.getUserId());
        checkOrder.setOrderId(input.getSignOrderId());
        check.operateCheck(checkOrder);
        // 交易订单唯一性检查 
        TradeOrder param = new TradeOrder();
        param.setMchId(input.getMerchantId());
        param.setOrderId(input.getSignOrderId());
        TradeOrder order = tradeOrderManager.getByMerNoAndMerOrderNo(param);
        if (order != null) {
        	log.error(TradeLogConstant.LOGGER_MODULE_QUICK_PAY, TradeLogConstant.LOGGER_BIZ_QUICK_PAY,"用户" + input.getUserId() + "请求订单" + input.getSignOrderId() + "重复","");
            throw new TradeException(TradeRespConstant.TRADE_Order_0001, TradeRespConstant.TRADE_Order_0001_MSG);
        }
        AuthQuery query = new AuthQuery();
        query.setMchId(input.getMerchantId());
        query.setUserId(input.getUserId());
        query.setPayType(PayType.ALL.getValue());
        query.setCardNo(input.getCardNo());
        query.setMasterMark(true);// 查主库
        // 查询鉴权表，不存在则校验参数是否完整
        List<Auth> authList = authManager.find(query);
        if (CollectionUtils.isEmpty(authList)) {
            // 如果鉴权信息不存在 异常
           throw new TradeException(TradeRespConstant.CARD_NOT_BIND,TradeRespConstant.CARD_NOT_BIND_MSG);
        } else {
            // 如果鉴权信息已经存在 则使用数据库保存的信息
            if (authList.size() > 1) {
                throw new TradeException(TradeRespConstant.TRADE_SIGN_0001,TradeRespConstant.TRADE_SIGN_0001_MSG);
            }
            Auth auth = authList.get(0);
            return auth;
        }

    }

    /**
     * 快捷路由
     * 
     * @param quickAuthInput
     * @return
     */
    private AsileRouteOutput route(TradeQuickAuthInput quickAuthInput,Auth auth) {
        AsileRouteInput condition = new AsileRouteInput();
        condition.setAmt(quickAuthInput.getAmt());
        condition.setBankShort(auth.getBankShort());
        condition.setTradeDate(new Date());
        condition.setRouteBusinessType(RouteBusinessType.QUICKPAY);
        condition.setMchId(quickAuthInput.getMerchantId());
        AsileRouteOutput routeOutput = new AsileRouteOutput();
        log.info(TradeLogConstant.LOGGER_MODULE_QUICK_PAY, TradeLogConstant.LOGGER_BIZ_QUICK_AUTH, "快捷路由开始"+quickAuthInput.getSignOrderId());
        try {
            routeOutput = asileRoute.getTradeAsile(condition);
        } catch (Exception e) {
        	log.error(TradeLogConstant.LOGGER_MODULE_QUICK_PAY, TradeLogConstant.LOGGER_BIZ_QUICK_AUTH,"快捷鉴权路由失败,路由信息："+condition.toString(),e,quickAuthInput.getSignOrderId());
            routeOutput = null;
        }
        log.info(TradeLogConstant.LOGGER_MODULE_QUICK_PAY, TradeLogConstant.LOGGER_BIZ_QUICK_AUTH, "快捷路由结束，路由结果："+routeOutput.getAsileCode()+"响应信息："+routeOutput.getReponseMsg());
        return routeOutput;
    }

    /**
     * 创建交易订单
     * 
     * @param tradeQuickPayInput
     * @return
     */
    private TradeOrder createOrder(TradeQuickAuthInput input, String asileCode,Auth auth) {
        TradeOrder order = new TradeOrder();
        order.setReqId(idCodeGenerator.getId());// 请求流水号，根据某种策略生成
        order.setMchId(input.getMerchantId());// 商户号
        order.setTxnName(TradeType.getNameByCode(input.getTradeCode()));// 交易名称
        order.setTxnCode(input.getTradeCode());// 交易码
        order.setOrderTime(new Date());// 订单日期
        order.setCreateTime(new Date());// 创建时间
        order.setLastupdateTime(new Date());// 最后修改时间
        order.setPhoneNo(auth.getPhoneNo());// 手机号
        order.setUserName(auth.getUserName());// 姓名
        order.setAmt(input.getAmt());// 金额
        order.setVersion(1);
        order.setPayType(input.getPayType());
        order.setAccountType(input.getAccountType());
        order.setTradeDate(new Date());//交易日期
        order.setRemark("");// 交易备注
        order.setCardNo(input.getCardNo());// 卡号
        order.setUserId(input.getUserId());// 用户号
        order.setOrderId(input.getSignOrderId());// 商户订单号
        order.setStatus(OrderStatus.CREATEORDER.getValue());
        order.setBankShort(auth.getBankShort());// 银行简码
        order.setNoticeUrl(input.getNoticeUrl());
        order.setCid(auth.getId());
        order.setRouteBusinessType(RouteBusinessType.QUICKPAY.getCode());
        if (!StringUtils.isEmpty(asileCode)) {
            order.setChannelNo(asileCode);
        }
        int count = tradeOrderManager.saveOrderAndCreateLog(order);
        if (count > 0) {
            return order;
        } else {
            throw new TradeException("创建订单失败");
        }
    }
}

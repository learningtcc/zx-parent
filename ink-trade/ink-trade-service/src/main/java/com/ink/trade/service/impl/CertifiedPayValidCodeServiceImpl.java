package com.ink.trade.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.ink.asile.core.manager.IAsileResCodeManager;
import com.ink.asile.core.manager.IAsileSignManager;
import com.ink.asile.core.po.AsileResCode;
import com.ink.asile.core.po.AsileSign;
import com.ink.base.log.util.YinkerLogger;
import com.ink.base.utils.BeanCopyConverter;
import com.ink.base.utils.IdCodeGenerator;
import com.ink.basic.core.manager.IBankcardBinManager;
import com.ink.channel.api.model.in.AutheBindCardValidInput;
import com.ink.channel.api.model.in.AutheForPayVaidInput;
import com.ink.channel.api.model.out.AutheBindCardValidOutput;
import com.ink.channel.api.model.out.AutheForPayVaidOutput;
import com.ink.channel.api.service.AutheBindCardValidCodeService;
import com.ink.channel.api.service.AutheForPayVaidCodeService;
import com.ink.trade.api.enums.OrderStatus;
import com.ink.trade.api.enums.PayType;
import com.ink.trade.api.enums.RouteBusinessType;
import com.ink.trade.api.enums.TradeType;
import com.ink.trade.api.model.in.AsileRouteInput;
import com.ink.trade.api.model.in.CertifiedPayValidCodeInput;
import com.ink.trade.api.model.out.AsileRouteOutput;
import com.ink.trade.api.model.out.CertifiedPayValidCodeOutput;
import com.ink.trade.api.rule.IAsileRoute;
import com.ink.trade.api.service.ICertifiedPayValidCodeService;
import com.ink.trade.core.cnst.TradeLogConstant;
import com.ink.trade.core.cnst.TradeRespConstant;
import com.ink.trade.core.enums.TradeStatus;
import com.ink.trade.core.exception.TradeException;
import com.ink.trade.core.manager.IAuthManager;
import com.ink.trade.core.manager.ITradeOrderManager;
import com.ink.trade.core.po.Auth;
import com.ink.trade.core.po.TradeOrder;
import com.ink.trade.core.query.AuthQuery;

/**
 * 
 * <pre>
 * <b>类描述:</b>()
 * <b>作者:</b>zx
 * <b>创建日期:</b>2016年6月20日 下午3:36:26
 * </pre>
 */
@Service("certifiedPayValidCodeService")
public class CertifiedPayValidCodeServiceImpl implements ICertifiedPayValidCodeService {
    private YinkerLogger LOGGER = YinkerLogger.getLogger(CertifiedPayValidCodeServiceImpl.class);
    @Autowired
    private ITradeOrderManager tradeOrderManager;
    @Autowired
    private IAsileResCodeManager asileResCodeManager;
    @Autowired
    private IAuthManager authManager;
    @Autowired
    private IdCodeGenerator idCodeGenerator;
    @Autowired
    private IBankcardBinManager bankcardBinManager;
    @Autowired
    private IAsileRoute asileRoute;
    @Autowired
    private IAsileSignManager asileSignManager;
    @Autowired
    private AutheBindCardValidCodeService autheBindCardValidCodeService;
    @Autowired
    private AutheForPayVaidCodeService autheForPayVaidCodeService;

    @Override
    public CertifiedPayValidCodeOutput validCode(CertifiedPayValidCodeInput input) {
        LOGGER.info(TradeLogConstant.LOGGER_MODULE_QUICK_PAY, TradeLogConstant.LOGGER_BIZ_QUICK_AUTH, "交易快捷鉴权调用开始"
                        + input.toString());
        CertifiedPayValidCodeOutput out = new CertifiedPayValidCodeOutput();
        out.setMerchantId(input.getMerchantId());
        out.setPayType(input.getPayType());
        try {
            // 交易检查
            Auth auth = check(input);
            // 获取路由
            AutheForPayVaidOutput autheForPayVaidOutput;
            TradeOrder order;
            long start = System.currentTimeMillis();
            AsileRouteOutput asileRouteOutput = route(input, auth);
            LOGGER.debug("快捷路由结束耗时" + (System.currentTimeMillis() - start));
            if (asileRouteOutput != null && TradeRespConstant.TRADE_SUCCESS.equals(asileRouteOutput.getReponseCode())) {
                // 创建交易订单
                order = createOrder(input, asileRouteOutput.getAsileCode(), auth);
                // 调用渠道鉴权
                long startAuth = System.currentTimeMillis();

                autheForPayVaidOutput = authority(order, auth);
                LOGGER.info(TradeLogConstant.LOGGER_MODULE_QUICK_PAY, TradeLogConstant.LOGGER_BIZ_QUICK_AUTH,
                                "快捷鉴权渠道调用结束，结果" + autheForPayVaidOutput.toString());
                LOGGER.debug("快捷鉴权结束耗时" + (System.currentTimeMillis() - startAuth));
            } else {
                // 创建交易订单
                createOrder(input, null, auth);
                LOGGER.error(TradeLogConstant.LOGGER_MODULE_QUICK_PAY, TradeLogConstant.LOGGER_BIZ_QUICK_AUTH,
                                "签约申请失败,原因:获取渠道信息失败", input.getOrderId());
                out.setReponseCode(TradeRespConstant.NO_USEFUL_CHANNEL);
                out.setReponseMsg(TradeRespConstant.NO_USEFUL_CHANNEL_MSG);
                out.setTradeStatus(TradeStatus.FAIL.getValue());
                return out;
            }
            // 响应码解析
            AsileResCode asileResCode = asileResCodeManager.findByAsileCodeAndAsileResCode(
                            asileRouteOutput.getAsileCode(), autheForPayVaidOutput.getResCode());
            // 响应码未映射
            if (asileResCode == null) {
                LOGGER.error(TradeLogConstant.LOGGER_MODULE_QUICK_PAY, TradeLogConstant.LOGGER_BIZ_QUICK_AUTH,
                                "解析响应码未成功：code=" + autheForPayVaidOutput.getResCode() + "msg" + autheForPayVaidOutput.getResMsg(), null);
                out.setReponseCode(TradeRespConstant.TRADE_SYSERROR);
                out.setReponseMsg(TradeRespConstant.TRADE_SYSERROR_MSG);
                out.setTradeStatus(TradeStatus.FAIL.getValue());
                return out;
            }
            if (TradeRespConstant.TRADE_SUCCESS.equals(asileResCode.getResCode())) {
                // 更新交易订单状态
                order.setStatus(OrderStatus.PENDING.getValue());
                order.setLastupdateTime(new Date());
                order.setToken(autheForPayVaidOutput.getToken());
                tradeOrderManager.updateOrderAndCreateLog(order);
                out.setReponseCode(TradeRespConstant.TRADE_SUCCESS);
                out.setReponseMsg(TradeRespConstant.TRADE_SUCCESS_MSG);
                out.setTradeStatus(TradeStatus.FAIL.getValue());
            } else {
                out.setReponseCode(asileResCode.getResCode());
                out.setReponseMsg(asileResCode.getResMsg());
                out.setTradeStatus(TradeStatus.FAIL.getValue());
            }
        } catch (TradeException ex) {
            LOGGER.error(TradeLogConstant.LOGGER_MODULE_QUICK_PAY, TradeLogConstant.LOGGER_BIZ_QUICK_AUTH,
                            "快捷鉴权失败,订单号：" + input.getOrderId(), ex, "");
            out.setTradeStatus(TradeStatus.FAIL.getValue());
            out.setReponseCode(ex.getCode());
            out.setReponseMsg(ex.getMessage());
        } catch (Exception ex) {
            LOGGER.error(TradeLogConstant.LOGGER_MODULE_QUICK_PAY, TradeLogConstant.LOGGER_BIZ_QUICK_AUTH,
                            "快捷鉴权失败,订单号：" + input.getOrderId(), ex, "");
            out.setTradeStatus(TradeStatus.FAIL.getValue());
            out.setReponseCode(TradeRespConstant.TRADE_SYSERROR);
            out.setReponseMsg(TradeRespConstant.TRADE_SYSERROR_MSG);
        }
        LOGGER.info(TradeLogConstant.LOGGER_MODULE_QUICK_PAY, TradeLogConstant.LOGGER_BIZ_QUICK_AUTH, "交易系统快捷鉴权结束");
        return out;
    }

    /**
     * 调用渠道获取短信验证码
     * 
     * @param input
     * @param asileCode
     * @return
     */
    public AutheForPayVaidOutput authority(TradeOrder order, Auth auth) {
        // 查询鉴权表
        AsileSign asileSign = new AsileSign();
        asileSign.setCid(auth.getId());
        asileSign.setChanelNo(order.getChannelNo());
        asileSign = asileSignManager.selectSignIdByChannel(asileSign);
        AutheBindCardValidOutput autheBindCardValidOutput=null;
        AutheForPayVaidOutput autheForPayVaidOutput=null;
        if (asileSign == null) {
           //调用首次认证短信验证码接口 
            AutheBindCardValidInput autheBindCardValidInput=new AutheBindCardValidInput();
            autheBindCardValidInput.setAmount(order.getAmt().toString());
            autheBindCardValidInput.setBankShort(auth.getBankShort());
            autheBindCardValidInput.setCardNo(auth.getCardNo());
            autheBindCardValidInput.setChannelId(order.getChannelNo());
            autheBindCardValidInput.setIdNo(auth.getIdNo());
            autheBindCardValidInput.setIdType(auth.getIdType());
            autheBindCardValidInput.setMerchantNo(auth.getMchId());
            autheBindCardValidInput.setOrderNo(order.getReqId());
            autheBindCardValidInput.setPhoneNo(auth.getPhoneNo());
            autheBindCardValidInput.setUserName(auth.getUserName());
            LOGGER.info(TradeLogConstant.LOGGER_MODULE_QUICK_PAY, TradeLogConstant.LOGGER_BIZ_QUICK_AUTH, "快捷鉴权渠道调用开始，"
                            + autheBindCardValidInput.toString());
            autheBindCardValidOutput= autheBindCardValidCodeService.bindCardValidCode(autheBindCardValidInput);
            autheForPayVaidOutput=BeanCopyConverter.converterClass(autheBindCardValidOutput, AutheForPayVaidOutput.class);
        } else {
           //调用再次支付短信验证码接口
            AutheForPayVaidInput autheForPayVaidInput=new AutheForPayVaidInput();
            autheForPayVaidInput.setAmount(order.getAmt().toString());
            autheForPayVaidInput.setBankShort(auth.getBankShort());
            autheForPayVaidInput.setCardNo(auth.getCardNo());
            autheForPayVaidInput.setChannelId(order.getChannelNo());
            autheForPayVaidInput.setIdentityId(asileSign.getSignId());
            autheForPayVaidInput.setIdNo(auth.getIdNo());
            autheForPayVaidInput.setIdType(auth.getIdType());
            autheForPayVaidInput.setMerchantNo(auth.getMchId());
            autheForPayVaidInput.setOrderNo(order.getReqId());
            autheForPayVaidInput.setPhoneNo(auth.getPhoneNo());
            autheForPayVaidInput.setUserName(auth.getUserName());
            LOGGER.info(TradeLogConstant.LOGGER_MODULE_QUICK_PAY, TradeLogConstant.LOGGER_BIZ_QUICK_AUTH, "快捷鉴权渠道调用开始，"
                            + autheForPayVaidInput.toString());
            autheForPayVaidOutput= autheForPayVaidCodeService.forPayVaidCode(autheForPayVaidInput);
        }
        
        
        return autheForPayVaidOutput;
    }

    /**
     * 交易检查
     * 
     * @param input
     */
    public Auth check(CertifiedPayValidCodeInput input) {
        // 交易类型检查
        if (!input.getTradeCode().trim().equals(TradeType.RECHARGE.getCode())) {
            throw new TradeException("交易类型不正确！");
        }
        // 交易订单唯一性检查
        TradeOrder param = new TradeOrder();
        param.setMchId(input.getMerchantId());
        param.setOrderId(input.getOrderId());
        TradeOrder order = tradeOrderManager.getByMerNoAndMerOrderNo(param);
        if (order != null) {
            LOGGER.error(TradeLogConstant.LOGGER_MODULE_QUICK_PAY, TradeLogConstant.LOGGER_BIZ_QUICK_PAY,
                            "用户" + input.getUserId() + "请求订单" + input.getOrderId() + "重复", "");
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
            // 如果鉴权信息不存在 则返回
            throw new TradeException("该卡未绑定，请先绑卡！");
        } else {
            // 如果鉴权信息已经存在 则使用数据库保存的信息
            if (authList.size() > 1) {
                throw new TradeException("快捷签约信息异常！");
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
    public AsileRouteOutput route(CertifiedPayValidCodeInput input, Auth auth) {
        AsileRouteInput condition = new AsileRouteInput();
        condition.setAmt(input.getAmt());
        condition.setBankShort(auth.getBankShort());
        condition.setTradeDate(new Date());
        condition.setRouteBusinessType(RouteBusinessType.CERTIFIEDPAY_SMS);
        AsileRouteOutput routeOutput = new AsileRouteOutput();
        LOGGER.info(TradeLogConstant.LOGGER_MODULE_QUICK_PAY, TradeLogConstant.LOGGER_BIZ_QUICK_AUTH,
                        "快捷路由开始" + input.getOrderId());
        try {
            routeOutput = asileRoute.getTradeAsile(condition);
        } catch (Exception e) {
            LOGGER.error(TradeLogConstant.LOGGER_MODULE_QUICK_PAY, TradeLogConstant.LOGGER_BIZ_QUICK_AUTH,
                            "快捷鉴权路由失败,路由信息：" + condition.toString(), e, "");
            e.printStackTrace();
            routeOutput = null;
        }
        LOGGER.info(TradeLogConstant.LOGGER_MODULE_QUICK_PAY, TradeLogConstant.LOGGER_BIZ_QUICK_AUTH, "快捷路由结束，路由结果："
                        + routeOutput.getAsileCode() + "响应信息：" + routeOutput.getReponseMsg());
        return routeOutput;
    }

    /**
     * 创建交易订单
     * 
     * @param tradeQuickPayInput
     * @return
     */
    public TradeOrder createOrder(CertifiedPayValidCodeInput input, String asileCode, Auth auth) {
        TradeOrder order = new TradeOrder();
        order.setReqId(idCodeGenerator.getId());// 请求流水号，根据某种策略生成
        order.setMchId(auth.getMchId());// 商户号
        order.setTxnName(TradeType.getNameByCode(input.getTradeCode()));// 交易名称
        order.setTxnCode(input.getTradeCode());// 交易码
        order.setOrderTime(new Date());// 订单日期
        order.setCreateTime(new Date());// 创建时间
        order.setLastupdateTime(new Date());// 最后修改时间
        order.setPhoneNo(auth.getPhoneNo());// 手机号
        order.setUserName(auth.getUserName());// 姓名
        order.setAmt(input.getAmt());// 金额
        order.setVersion(1);
        order.setAccountType(input.getAccountType());
        order.setTradeDate(new Date());// 交易日期
        order.setCardNo(input.getCardNo());// 卡号
        order.setUserId(input.getUserId());// 用户号
        order.setOrderId(input.getOrderId());// 商户订单号
        order.setStatus(OrderStatus.CREATEORDER.getValue());
        order.setBankShort(auth.getBankShort());// 银行简码
        order.setPayType(input.getPayType());//支付类型
        order.setNoticeUrl(input.getNoticeUrl());//回调地址
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

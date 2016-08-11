package com.ink.trade.service.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ink.asile.core.manager.IAsileResCodeManager;
import com.ink.asile.core.po.AsileResCode;
import com.ink.base.log.util.YinkerLogger;
import com.ink.base.utils.IdCodeGenerator;
import com.ink.basic.core.manager.IBankcardBinManager;
import com.ink.basic.core.po.BankcardBin;
import com.ink.channel.api.model.in.ValidCodeInput;
import com.ink.channel.api.model.out.ValidCodeOutput;
import com.ink.channel.api.service.ValidCodeService;
import com.ink.trade.api.enums.AuthOrderStatus;
import com.ink.trade.api.enums.RouteBusinessType;
import com.ink.trade.api.model.in.AsileRouteInput;
import com.ink.trade.api.model.in.SignApplyInput;
import com.ink.trade.api.model.out.AsileRouteOutput;
import com.ink.trade.api.model.out.SignApplyOutput;
import com.ink.trade.api.rule.IAsileRoute;
import com.ink.trade.api.service.ISignApplyService;
import com.ink.trade.core.cnst.TradeLogConstant;
import com.ink.trade.core.cnst.TradeRespConstant;
import com.ink.trade.core.enums.TradeStatus;
import com.ink.trade.core.exception.TradeException;
import com.ink.trade.core.manager.IAuthOrderManager;
import com.ink.trade.core.po.AuthOrder;
import com.ink.trade.service.check.CheckOptions;
import com.ink.trade.service.check.Order;

/**
 * 
 * @Description 签约申请
 * @author xuguoqi
 * @date 2016年4月26日 下午6:46:49
 */
@Service("signApplyService")
public class SignApplyServiceImpl implements ISignApplyService {

    YinkerLogger log = YinkerLogger.getLogger(SignApplyServiceImpl.class);

    public final int DEFAULT_VERSION = 1;

    @Autowired
    @Qualifier("signOrderUnique")
    // 注入signOrderUnique
    private CheckOptions check;
    @Autowired
    private IAsileResCodeManager asileResCodeManager;

    @Autowired
    private IAuthOrderManager authOrderManager;

    @Autowired
    private IAsileRoute asileRoute;// 路由决策

    @Autowired
    private ValidCodeService validCodeService;// 获取短验

    @Autowired
    private IBankcardBinManager bankcardBinManager;
    @Autowired
    private IdCodeGenerator idCodeGenerator;

    /**
     * 
     * @Description 签约申请
     * @author xuguoqi
     * @date 2016年4月27日 上午10:19:57
     * @param signApplyInput
     * @return
     */
    @Override
    public SignApplyOutput signApply(SignApplyInput signApplyInput) {
        long startTime = System.currentTimeMillis();
        log.info(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY, TradeLogConstant.LOGGER_BIZ_SIGNAPPLY, "签约申请开始参数为:"
                        + signApplyInput, signApplyInput.getSignOrderId());
        log.info(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY, TradeLogConstant.LOGGER_BIZ_SIGNAPPLY, "签约申请开始:"
                        + startTime, signApplyInput.getSignOrderId());
        AuthOrder authOrder = null;
        ValidCodeOutput validCodeOutput = null;
        SignApplyOutput out = new SignApplyOutput();
        // check 数据封装
        Order checkOrder = this.checkOrder(signApplyInput);
        String asileCode = "";
        try {
            BankcardBin cardBin = bankcardBinManager.getByCardBin(signApplyInput.getCardNo());
            if (StringUtils.isBlank(signApplyInput.getBankShort())) {// bankSort 是否为空 如果为空根据cardbin生成对应的bankShort
                if (null != cardBin) {
                    signApplyInput.setBankShort(cardBin.getBankSimpleCode());
                } else {
                    throw new TradeException(TradeRespConstant.TRADE_SIGN_0003, TradeRespConstant.TRADE_SIGN_0003_MSG);
                }

            }
            // 不为空判断carbin正确性
            if (null != cardBin) {
                if (!signApplyInput.getBankShort().equals(cardBin.getBankSimpleCode())) {// bankshort正确
                    log.error(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY, TradeLogConstant.LOGGER_BIZ_SIGNAPPLY,
                                    "签约申请入参bankshort非法", signApplyInput.getSignOrderId());
                    throw new TradeException(TradeRespConstant.TRADE_ERROR_0001, TradeRespConstant.TRADE_ERROR_0001_MSG+"bankshort与cardBin不一致");
                }
            }
            // 交易检查
            log.info(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY, TradeLogConstant.LOGGER_BIZ_SIGNAPPLY, "校验开始",
                            signApplyInput.getSignOrderId());
            check.operateCheck(checkOrder);
            log.info(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY, TradeLogConstant.LOGGER_BIZ_SIGNAPPLY, "校验结束",
                            signApplyInput.getSignOrderId());
            // 交易数据封装
            log.info(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY, TradeLogConstant.LOGGER_BIZ_SIGNAPPLY, "签约申请订单创建开始",
                            signApplyInput.getSignOrderId());
            authOrder = this.signApplyInputToAuthOrder(signApplyInput);
            // 订单创建
            authOrder = this.createAuthOrder(authOrder);
            log.info(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY, TradeLogConstant.LOGGER_BIZ_SIGNAPPLY, "签约申请订单创建结束",
                            signApplyInput.getSignOrderId());
            // 调用路由,路由决策 生成渠道号
            log.info(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY, TradeLogConstant.LOGGER_BIZ_SIGNAPPLY,
                            "调用路由,路由决策生成渠道号开始", signApplyInput.getSignOrderId());
            AsileRouteOutput asileRouteOutput = this.getChannelNo(authOrder);
            log.info(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY, TradeLogConstant.LOGGER_BIZ_SIGNAPPLY,
                            "调用路由,路由决策生成渠道号结束", signApplyInput.getSignOrderId());
            if (asileRouteOutput != null) {// 常量在前
                if (TradeRespConstant.TRADE_SUCCESS.equals(asileRouteOutput.getReponseCode())) {
                    asileCode = asileRouteOutput.getAsileCode();
                    authOrder.setChannelNo(asileCode);
                }
            }// 获取通道失败反回并更新订单状态
            if (StringUtils.isBlank(authOrder.getChannelNo())) {
                log.error(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY, TradeLogConstant.LOGGER_BIZ_SIGNAPPLY,
                                "签约申请失败,原因:获取渠道信息失败", authOrder.getOrderId());
                out.setReponseCode(TradeRespConstant.NO_USEFUL_CHANNEL);
                out.setReponseMsg(TradeRespConstant.NO_USEFUL_CHANNEL_MSG);
                out.setTradeStatus(TradeStatus.FAIL.getValue());
                AuthOrder updateFileAuthOrder = authOrderManager.getById(authOrder.getId(),true);
                updateFileAuthOrder.setStatus(Integer.valueOf(TradeStatus.FAIL.getValue()));
                updateFileAuthOrder.setLastupdateTime(new Date());
                authOrderManager.update(updateFileAuthOrder);
                return out;
            }
            // 短信验证码数据调用参数封装
            log.info(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY, TradeLogConstant.LOGGER_BIZ_SIGNAPPLY, "获取短信验证码开始",
                            signApplyInput.getSignOrderId());
            ValidCodeInput validCodeInput = this.getValidCodeInput(signApplyInput, authOrder);
            // 调用渠道生成短信验证码
            log.info(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY, TradeLogConstant.LOGGER_BIZ_SIGNAPPLY,
                            "调用渠道获取短信验证码开始", validCodeInput.toString());
            // #mock start#
            validCodeOutput = validCodeService.getValidCode(validCodeInput);
            // validCodeOutput = new ValidCodeOutput();
            // validCodeOutput.setResCode("C000000000");
            // validCodeOutput.setResMsg("成功");
            // #mock end#

            log.info(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY, TradeLogConstant.LOGGER_BIZ_SIGNAPPLY, "获取短信验证码结束",
                            validCodeOutput!=null?validCodeOutput.toString():null);
        } catch (TradeException ex) {
            log.error(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY, TradeLogConstant.LOGGER_BIZ_SIGNAPPLY, "签约申请失败,原因:"
                            + ex.getMessage(), ex, signApplyInput.getSignOrderId());
            // 捕获交易异常处理
            out.setReponseCode(ex.getCode());
            out.setReponseMsg(ex.getMessage());
            out.setTradeStatus(TradeStatus.FAIL.getValue());
            return out;
        } catch (Exception ex) {
            log.error(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY, TradeLogConstant.LOGGER_BIZ_SIGNAPPLY, "签约申请失败,原因:"
                            + ex.getMessage(), ex, signApplyInput.getSignOrderId());
            // 未知异常处理
            out.setReponseCode(TradeRespConstant.TRADE_SYSERROR);
            out.setReponseMsg(TradeRespConstant.TRADE_SYSERROR_MSG);
            out.setTradeStatus(TradeStatus.FAIL.getValue());
            return out;
        }
        // 判断渠道信息返回状态
        if (validCodeOutput != null) {
            out.setMerchantId(authOrder.getMchId());
            out.setSignOrderId(authOrder.getOrderId());
            out.setTradeCode(authOrder.getTxnCode());
            out.setUserId(authOrder.getUserId());
            // 渠道响应码转平台响应码
            log.info(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY, TradeLogConstant.LOGGER_BIZ_SIGNAPPLY,
                            "渠道响应码转平台响应码开始", signApplyInput.getSignOrderId());
            AsileResCode asileResCode = asileResCodeManager.findByAsileCodeAndAsileResCode(authOrder.getChannelNo(),
                            validCodeOutput.getResCode());
            if (asileResCode != null) {
                out.setReponseCode(asileResCode.getResCode());
                out.setReponseMsg(asileResCode.getResMsg());
                out.setTradeStatus(TradeStatus.FAIL.getValue());
            } else {
                log.error(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY, TradeLogConstant.LOGGER_BIZ_SIGNAPPLY,
                                "签约申请失败,:"+validCodeOutput.toString(),"");
                out.setReponseCode(TradeRespConstant.TRADE_ERROR_0001);
                out.setReponseMsg(TradeRespConstant.TRADE_ERROR_0001_MSG+validCodeOutput.getResMsg());
                out.setTradeStatus(TradeStatus.FAIL.getValue());
            }
            // success
            if (TradeRespConstant.TRADE_SUCCESS.equals(out.getReponseCode())) {
                out.setTradeStatus(TradeStatus.SUCCESS.getValue());
                AuthOrder preAuthOrder = authOrderManager.getById(authOrder.getId(),true);
                // 保存token
                if (!StringUtils.isEmpty(validCodeOutput.getToken())) {
                    preAuthOrder.setToken(validCodeOutput.getToken());
                    preAuthOrder.setTokenCreateTime(new Date());
                }
                if (!StringUtils.isEmpty(validCodeOutput.getReqId())) {
                    preAuthOrder.setReqId(validCodeOutput.getReqId());
                }
                if (!StringUtils.isEmpty(validCodeOutput.getIdentityId()))
                    preAuthOrder.setSignId(validCodeOutput.getIdentityId());
                preAuthOrder.setLastupdateTime(new Date());
                preAuthOrder.setChannelNo(asileCode);
                preAuthOrder.setStatus(AuthOrderStatus.PRECONTRACT.getValue());// 预签约
                int update = authOrderManager.update(preAuthOrder);
                // 更新失败 详细日志信息
                if (update != 1) {
                    log.error(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY, TradeLogConstant.LOGGER_BIZ_SIGNAPPLY,
                                    "签约申请，更新AuthOrder表失败,原因为该数据已被更改" + preAuthOrder, "");
                    out.setReponseCode(TradeRespConstant.TRADE_SYSERROR);
                    out.setReponseMsg(TradeRespConstant.TRADE_SYSERROR_MSG);
                    out.setTradeStatus(TradeStatus.FAIL.getValue());
                    return out;
                }
            }
            log.info(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY, TradeLogConstant.LOGGER_BIZ_SIGNAPPLY,
                            "渠道响应码转平台响应码结束", signApplyInput.getSignOrderId());
            // 无对应响应吗 返回签约失败
        } else {
            // 记录渠道反回响应码
            log.error(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY, TradeLogConstant.LOGGER_BIZ_SIGNAPPLY,
                            "签约申请失败,原因:渠道响应码转平台响应码", signApplyInput.getSignOrderId());
            AuthOrder preAuthOrder = authOrderManager.getById(authOrder.getId(),true);
            preAuthOrder.setLastupdateTime(new Date());
            preAuthOrder.setStatus(AuthOrderStatus.FAIL.getValue());// 预签约
            try {
                authOrderManager.update(preAuthOrder);
            } catch (Exception ex) {
                log.error(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY, TradeLogConstant.LOGGER_BIZ_SIGNAPPLY,
                                "签约申请，更新AuthOrder表失败,原因为该数据已被更改" + preAuthOrder, "");
            }
            out.setReponseCode(TradeRespConstant.TRADE_ERROR_0001);
            out.setReponseMsg(TradeRespConstant.TRADE_ERROR_0001_MSG);
            out.setTradeStatus(TradeStatus.FAIL.getValue());
        }
        log.info(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY, TradeLogConstant.LOGGER_BIZ_SIGNAPPLY, "签约申请结束,共耗时:"
                        + (System.currentTimeMillis() - startTime) + "ms", signApplyInput.getSignOrderId());
        return out;
    }

    /**
     * 
     * @Description 业务封装校验
     * @author xuguoqi
     * @date 2016年4月27日 上午11:42:13
     * @param signApplyInput
     * @return
     */
    private Order checkOrder(SignApplyInput signApplyInput) {
        Order checkOrder = new Order();
        checkOrder.setMerchantId(signApplyInput.getMerchantId());
        checkOrder.setUserId(signApplyInput.getUserId());
        checkOrder.setOrderId(signApplyInput.getSignOrderId());
        checkOrder.setCustName(signApplyInput.getRealName());
        checkOrder.setIdType(signApplyInput.getIdType().getValue());
        checkOrder.setIdNo(signApplyInput.getIdNo());
        checkOrder.setCardType(signApplyInput.getCardType().getValue());
        checkOrder.setCardNo(signApplyInput.getCardNo());
        checkOrder.setBankMblNo(signApplyInput.getPhoneNo());
        return checkOrder;
    }

    /**
     * 
     * @Description 生成authOrder数据
     * @author xuguoqi
     * @date 2016年4月27日 上午11:35:09
     * @param authOrder
     * @return
     */
    private AuthOrder createAuthOrder(AuthOrder authOrder) {
        authOrder.setCreateTime(new Date());
        authOrder.setVersion(this.DEFAULT_VERSION);
        authOrder.setSignId(idCodeGenerator.getId());
        authOrder.setReqId(String.valueOf(idCodeGenerator.getId()));
        authOrder.setStatus(AuthOrderStatus.PROCESSING.getValue());// 待处理
        authOrderManager.save(authOrder);
        return authOrder;
    }

    /**
     * 
     * @Description 获取渠道信息
     * @author xuguoqi
     * @date 2016年4月29日 上午10:18:57
     * @param authOrder
     * @return
     * @throws Exception
     */
    private AsileRouteOutput getChannelNo(AuthOrder authOrder) throws Exception {
        AsileRouteInput routeCondition = new AsileRouteInput();
        routeCondition.setMchId(authOrder.getMchId());
        routeCondition.setCardType(authOrder.getCardType());
        routeCondition.setCardId(authOrder.getCardNo());
        routeCondition.setTradeDate(new Date());// 非必填
        routeCondition.setBankShort(authOrder.getBankNameShort());
        routeCondition.setRouteBusinessType(RouteBusinessType.AUTH);
        AsileRouteOutput asileRouteOutput = asileRoute.authRoute(routeCondition);
        return asileRouteOutput;
    }

    /**
     * 
     * @Description 短验参数封装
     * @author xuguoqi
     * @date 2016年4月27日 上午10:20:20
     * @param input
     * @param authOrder
     * @return
     */
    private ValidCodeInput getValidCodeInput(SignApplyInput input, AuthOrder authOrder) {
        ValidCodeInput validCodeInput = new ValidCodeInput();
        validCodeInput.setUserName(input.getRealName());
        validCodeInput.setIdNo(input.getIdNo());
        validCodeInput.setIdType(input.getIdType().getValue());
        validCodeInput.setCardNo(input.getCardNo());
        validCodeInput.setIdentityid(authOrder.getSignId());
        validCodeInput.setReqId(authOrder.getReqId());
        validCodeInput.setPhoneNo(input.getPhoneNo());
        validCodeInput.setChannelId(authOrder.getChannelNo());
        validCodeInput.setBankShort(input.getBankShort());
        validCodeInput.setMerchantNo(input.getMerchantId());
        return validCodeInput;
    }

    /**
     * 
     * @Description 签约申请入参装成AutoOrder
     * @author xuguoqi
     * @date 2016年4月22日 下午6:01:59
     * @param signApplyInput
     * @return
     */
    private AuthOrder signApplyInputToAuthOrder(SignApplyInput input) {
        AuthOrder authOrder = new AuthOrder();
        authOrder.setMchId(input.getMerchantId());
        if (StringUtils.isNotBlank(input.getTradeCode())) {
            authOrder.setTxnCode(input.getTradeCode());
        }
        authOrder.setBankNameShort(input.getBankShort());
        authOrder.setCardNo(input.getCardNo());
        authOrder.setUserName(input.getRealName());
        authOrder.setUserId(input.getUserId());
        authOrder.setCardType(input.getCardType().getValue());
        authOrder.setIdType(input.getIdType().getValue());
        authOrder.setIdNo(input.getIdNo());
        authOrder.setPhone(input.getPhoneNo());
        authOrder.setOrderId(input.getSignOrderId());
        if (StringUtils.isNotBlank(input.getExpireDate())) {
            authOrder.setExpireDate(input.getExpireDate());
        }

        if (StringUtils.isNotBlank(input.getCvv2())) {
            authOrder.setCvv2(input.getCvv2());
        }

        return authOrder;
    }

    public CheckOptions getCheck() {
        return check;
    }

    public void setCheck(CheckOptions check) {
        this.check = check;
    }

}

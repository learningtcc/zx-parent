package com.ink.trade.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.ink.asile.core.manager.IAsileBusinessManager;
import com.ink.asile.core.manager.IAsileResCodeManager;
import com.ink.asile.core.manager.IAsileSignManager;
import com.ink.asile.core.po.AsileResCode;
import com.ink.asile.core.po.AsileSign;
import com.ink.base.log.util.YinkerLogger;
import com.ink.base.utils.BeanCopyConverter;
import com.ink.channel.api.model.in.AutheConfirmPayInput;
import com.ink.channel.api.model.in.AuthenBindCardInput;
import com.ink.channel.api.model.out.AutheConfirmPayOutput;
import com.ink.channel.api.model.out.AuthenBindCardOutput;
import com.ink.channel.api.service.AutheConfirmPayService;
import com.ink.channel.api.service.AuthenBindCardService;
import com.ink.trade.api.enums.OrderStatus;
import com.ink.trade.api.enums.PayType;
import com.ink.trade.api.enums.TradeType;
import com.ink.trade.api.model.in.CertifiedPayInput;
import com.ink.trade.api.model.out.CertifiedPayOutput;
import com.ink.trade.api.service.ICertifiedPayService;
import com.ink.trade.core.cnst.TradeLogConstant;
import com.ink.trade.core.cnst.TradeRespConstant;
import com.ink.trade.core.enums.IsDelete;
import com.ink.trade.core.enums.TradeStatus;
import com.ink.trade.core.exception.TradeException;
import com.ink.trade.core.exception.orderfail.OrderStatusErrorException;
import com.ink.trade.core.manager.IAuthManager;
import com.ink.trade.core.manager.IPayManager;
import com.ink.trade.core.manager.ITradeOrderManager;
import com.ink.trade.core.po.Auth;
import com.ink.trade.core.po.Pay;
import com.ink.trade.core.po.TradeOrder;
import com.ink.trade.core.query.AuthQuery;
import com.ink.trade.service.callbacks.PreCollectionCallBackServiceImpl;
import com.ink.trade.service.dto.PreCollectionCallBackDto;
@Service("certifiedPayService")
public class CertifiedPayServiceImpl implements ICertifiedPayService {
    private YinkerLogger LOGGER = YinkerLogger.getLogger(CertifiedPayServiceImpl.class);
    @Autowired
    private ITradeOrderManager tradeOrderManager;
    @Autowired
    private IPayManager payManager;
    @Autowired
    private IAuthManager authManager;
    @Autowired
    private PreCollectionCallBackServiceImpl preCollectionCallBackService;
    @Autowired
    private IAsileResCodeManager asileResCodeManager;
    @Autowired
    private IAsileSignManager asileSignManager;
    @Autowired
    private IAsileBusinessManager asileBusinessManager;
    @Autowired
    private AutheConfirmPayService authConfirmPayService;
    @Autowired
    private AuthenBindCardService authenBindCardService;

    @Override
    public CertifiedPayOutput pay(CertifiedPayInput input) {
        LOGGER.info(TradeLogConstant.LOGGER_MODULE_QUICK_PAY, TradeLogConstant.LOGGER_BIZ_QUICK_PAY, "交易系统快捷支付开始");
        CertifiedPayOutput out = new CertifiedPayOutput();
        out.setMerchantId(input.getMerchantId());
        out.setPayType(input.getPayType());
        out.setTradeCode(input.getTradeCode());
        // 交易检查
        try {
            TradeOrder tradeOrder = check(input);
            if (!tradeOrder.getStatus().equals(OrderStatus.PENDING.getValue())) {
                LOGGER.error(TradeLogConstant.LOGGER_MODULE_QUICK_PAY, TradeLogConstant.LOGGER_BIZ_QUICK_PAY,
                                "签约订单不正常！" + input.getOrderId(), null);
                throw new OrderStatusErrorException();
            }
            // 更新交易订单状态处理中
            tradeOrder.setTxnName(TradeType.getNameByCode(input.getTradeCode()));// 交易名称
            tradeOrder.setTxnCode(input.getTradeCode());// 交易码
            tradeOrder.setStatus(OrderStatus.PROCESSING.getValue());
            tradeOrder.setLastupdateTime(new Date());
            tradeOrderManager.updateOrderAndCreateLog(tradeOrder);
            // 创建支付订单
            Pay payOrder = createPayOrder(tradeOrder);
            // 调用渠道支付
            AutheConfirmPayOutput autheConfirmPayOutput = pay(tradeOrder,payOrder, input);
            AsileResCode asileResCode=asileResCodeManager.findByAsileCodeAndAsileResCode(payOrder.getChanelNo(), autheConfirmPayOutput.getResCode());
            if (asileResCode == null) {
                LOGGER.error(TradeLogConstant.LOGGER_MODULE_QUICK_PAY,
                                TradeLogConstant.LOGGER_BIZ_QUICK_PAY,
                                "认证支付失败,原因:解析渠道响应信息失败,响应码：" + autheConfirmPayOutput.getResCode() + "响应信息："
                                                + autheConfirmPayOutput.getResMsg(), input.getOrderId());
                out.setReponseCode(TradeRespConstant.TRADE_SYSERROR);
                out.setReponseMsg(TradeRespConstant.TRADE_SYSERROR_MSG);
                out.setTradeStatus(TradeStatus.FAIL.getValue());
                PreCollectionCallBackDto dto = new PreCollectionCallBackDto();
                dto.setOrderNo(payOrder.getPayOrderId());
                dto.setOrderStatus("03");
                preCollectionCallBackService.callBack(dto);
                return out;
            }
            // 请求超时,修改订单状态返回失败
            if (TradeRespConstant.CHANNEL_REQUEST_TIMEOUT.equals(asileResCode.getResCode())) {
                out.setReponseCode(TradeRespConstant.CHANNEL_REQUEST_TIMEOUT);
                out.setReponseMsg(TradeRespConstant.CHANNEL_REQUEST_TIMEOUT_MSG);
                out.setTradeStatus(TradeStatus.FAIL.getValue());
                PreCollectionCallBackDto dto = new PreCollectionCallBackDto();
                dto.setOrderNo(payOrder.getPayOrderId());
                dto.setOrderStatus("03");
                preCollectionCallBackService.callBack(dto);
                return out;
            }
            if (asileBusinessManager.IsSynchronized(payOrder.getChanelNo(), PayType.AUTHPAY.getValue())) {
                // 响应超时
                if (com.ink.channel.core.enums.OrderStatus.PROCESSES_CODE.getCode().equals(autheConfirmPayOutput.getOrderStatus())) {
                    out.setReponseCode(TradeRespConstant.CHANNEL_RESPONSE_TIMEOUT);
                    out.setReponseMsg(TradeRespConstant.CHANNEL_RESPONSE_TIMEOUT);
                    out.setTradeStatus(TradeStatus.PROCESSING.getValue());
                    PreCollectionCallBackDto dto = new PreCollectionCallBackDto();
                    dto.setOrderNo(payOrder.getPayOrderId());
                    dto.setOrderStatus("02");
                    preCollectionCallBackService.callBack(dto);
                    return out;
                }
                if (com.ink.channel.core.enums.OrderStatus.SUCCESS_CODE.getCode().equals(autheConfirmPayOutput.getOrderStatus())) {
                    // 更新支付交易订单状态
                    PreCollectionCallBackDto dto = new PreCollectionCallBackDto();
                    dto.setOrderNo(payOrder.getPayOrderId());
                    payOrder.setPayReqId(autheConfirmPayOutput.getOrgTranFlow());
                    dto.setOrderStatus("01");
                    payOrder.setAsileRepCode(asileResCode.getAsileResCode());
                    payOrder.setAsileRepMsg(asileResCode.getAsileResMsg());
                    payOrder.setRepCode(asileResCode.getResCode());
                    payOrder.setRepMsg(asileResCode.getResMsg());
                    payOrder.setLastupdateTime(new Date());
                    payManager.updatePayAndCreateLog(payOrder);
                    preCollectionCallBackService.callBack(dto);
                    out.setReponseCode(TradeRespConstant.TRADE_SUCCESS);
                    out.setReponseMsg(TradeRespConstant.TRADE_SUCCESS_MSG);
                    out.setTradeStatus(TradeStatus.SUCCESS.getValue());
                } else {
                    // 更新支付交易订单状态
                    PreCollectionCallBackDto dto = new PreCollectionCallBackDto();
                    dto.setOrderNo(payOrder.getPayOrderId());
                    dto.setOrderStatus("03");
                    preCollectionCallBackService.callBack(dto);
                    out.setReponseCode(asileResCode.getResCode());
                    out.setReponseMsg(asileResCode.getResMsg());
                    out.setTradeStatus(TradeStatus.FAIL.getValue());
                }
            } else {
                payOrder.setAsileRepCode(asileResCode.getAsileResCode());
                payOrder.setAsileRepMsg(asileResCode.getAsileResMsg());
                payOrder.setPayReqId(autheConfirmPayOutput.getOrgTranFlow());
                payManager.updatePayAndCreateLog(payOrder);
                out.setTradeStatus(TradeStatus.PROCESSING.getValue());
            }
        } catch (TradeException ex) {
            LOGGER.error(TradeLogConstant.LOGGER_MODULE_QUICK_PAY, TradeLogConstant.LOGGER_BIZ_QUICK_PAY, ex.getCode()
                            + ex.getMessage(), "");
            out.setTradeStatus(TradeStatus.FAIL.getValue());
            out.setReponseCode(ex.getCode());
            out.setReponseMsg(ex.getMessage());
        } catch (Exception ex) {
            LOGGER.error(TradeLogConstant.LOGGER_MODULE_QUICK_PAY, TradeLogConstant.LOGGER_BIZ_QUICK_PAY, "快捷支付失败！", "");
            out.setTradeStatus(TradeStatus.FAIL.getValue());
            out.setReponseCode(TradeRespConstant.TRADE_SYSERROR);
            out.setReponseMsg(TradeRespConstant.TRADE_SYSERROR_MSG);
        }
        LOGGER.info(TradeLogConstant.LOGGER_MODULE_QUICK_PAY, TradeLogConstant.LOGGER_BIZ_QUICK_PAY, "");
        return out;
    }

    /**
     * 订单检查
     * 
     * @param input
     */
    public TradeOrder check(CertifiedPayInput input) {
        // 交易类型检查
        if (!input.getTradeCode().trim().equals(TradeType.RECHARGE.getCode())) {
            throw new TradeException("交易类型不正确！");
        }
        // 校验订单唯一性
        TradeOrder param = new TradeOrder();
        param.setMchId(input.getMerchantId());
        param.setOrderId(input.getOrderId());
        TradeOrder order = tradeOrderManager.getByMerNoAndMerOrderNo(param);
        if (order == null) {
            LOGGER.error(TradeLogConstant.LOGGER_MODULE_QUICK_PAY, TradeLogConstant.LOGGER_BIZ_QUICK_PAY,
                            "用户" + input.getUserId() + "请求订单" + input.getOrderId() + "不存在", "");
            throw new TradeException(TradeRespConstant.TRADE_Order_0001, TradeRespConstant.TRADE_Order_0001_MSG);
        }
        return order;
    }

    /**
     * 调用渠道支付
     * 
     * @param payOrder
     * @param tradeOrder
     * @param tradeQuickPayInput
     * @return
     */
    public AutheConfirmPayOutput pay(TradeOrder tradeOrder,Pay payOrder, CertifiedPayInput input) {

        // 查询鉴权表
        AuthQuery query = new AuthQuery();
        query.setMchId(input.getMerchantId());
        query.setUserId(input.getUserId());
        query.setPayType(PayType.ALL.getValue());
        query.setCardNo(input.getCardNo());
        query.setMasterMark(true);// 查主库
        List<Auth> authList = authManager.find(query);
        if (CollectionUtils.isEmpty(authList)) {
            throw new TradeException("绑卡信息异常!");
        }
        AsileSign asileSign = new AsileSign();
        asileSign.setCid(authList.get(0).getId());
        asileSign.setChanelNo(payOrder.getChanelNo());
        asileSign = asileSignManager.selectSignIdByChannel(asileSign);
        AuthenBindCardOutput authenBindCardOutput=null;
        AutheConfirmPayOutput autheConfirmPayOutput=null;
        if (asileSign == null) {
            // 渠道首次支付接口
            AuthenBindCardInput authenBindCardInput=new AuthenBindCardInput();
            authenBindCardInput.setAccountName(authList.get(0).getUserName());
            authenBindCardInput.setAccountNo(authList.get(0).getCardNo());
            authenBindCardInput.setBankShort(authList.get(0).getBankShort());
            authenBindCardInput.setCertNo(authList.get(0).getIdNo());
            authenBindCardInput.setCertType(authList.get(0).getIdType());
            authenBindCardInput.setChannelId(payOrder.getChanelNo());
            authenBindCardInput.setMerchantNo(payOrder.getMchId());
            authenBindCardInput.setOrderNo(payOrder.getPayOrderId());
            authenBindCardInput.setPhoneNo(authList.get(0).getPhoneNo());
             authenBindCardOutput= authenBindCardService.bindCard(authenBindCardInput);
            // 保存签约信息
            if(authenBindCardOutput!=null && TradeRespConstant.TRADE_SUCCESS.equals(asileResCodeManager.findByAsileCodeAndAsileResCode(payOrder.getChanelNo(),
              authenBindCardOutput.getResCode()).getResCode())){
             asileSign=new AsileSign();
             asileSign.setBankShort(authList.get(0).getBankShort());
             asileSign.setCardNo(authList.get(0).getCardNo());
             asileSign.setCardType(authList.get(0).getCardType());
             asileSign.setChanelNo(payOrder.getChanelNo());
             asileSign.setCid(authList.get(0).getId());
             asileSign.setCreateTime(new Date());
             asileSign.setIdNo(authList.get(0).getIdNo());
             asileSign.setIdType(authList.get(0).getIdType());
             asileSign.setIsDelete(IsDelete.NO.getValue());
             asileSign.setPhone(authList.get(0).getPhoneNo());
             asileSign.setPayType(payOrder.getPayType());
             asileSign.setLastupdateTime(new Date());
             asileSign.setMchId(payOrder.getMchId());
             asileSign.setSignId(authenBindCardOutput.getIdentityid());
             asileSign.setUserId(authList.get(0).getUserId());
             asileSign.setUserName(authList.get(0).getUserName());
             asileSignManager.save(asileSign);}
            autheConfirmPayOutput=BeanCopyConverter.converterClass(authenBindCardOutput, AutheConfirmPayOutput.class);
        } else {
            AutheConfirmPayInput autheConfirmPayInput=new AutheConfirmPayInput();
            autheConfirmPayInput.setAmount(payOrder.getAmt());
            autheConfirmPayInput.setBankShort(authList.get(0).getBankShort());
            autheConfirmPayInput.setCardNo(authList.get(0).getCardNo());
            autheConfirmPayInput.setChannelId(payOrder.getChanelNo());
            autheConfirmPayInput.setClientDate(payOrder.getReqTime());
            autheConfirmPayInput.setIdentityId(asileSign.getSignId());
            autheConfirmPayInput.setIdNo(authList.get(0).getIdNo());
            autheConfirmPayInput.setIdType(authList.get(0).getIdType());
            autheConfirmPayInput.setMerchantNo(payOrder.getMchId());
            autheConfirmPayInput.setOrderNo(payOrder.getPayOrderId());
            autheConfirmPayInput.setPhoneNo(authList.get(0).getPhoneNo());
            autheConfirmPayInput.setToken(tradeOrder.getToken());
            autheConfirmPayInput.setUserName(authList.get(0).getUserName());
            autheConfirmPayInput.setValidCode(input.getValidCode());
            autheConfirmPayOutput= authConfirmPayService.confirmPay(autheConfirmPayInput);
        }
        
        Long startQuick = System.currentTimeMillis();
        
        LOGGER.debug("调用渠道快捷支付完成，耗时" + (System.currentTimeMillis() - startQuick));
        return autheConfirmPayOutput;
    }

    /**
     * 创建支付订单
     * 
     * @param tradeOrder
     * @return
     */
    public Pay createPayOrder(TradeOrder tradeOrder) {
        Pay pay = new Pay();
        pay.setAmt(tradeOrder.getAmt());
        pay.setChanelNo(tradeOrder.getChannelNo());
        pay.setCreateTime(new Date());
        pay.setLastupdateTime(new Date());
        pay.setMchId(tradeOrder.getMchId());
        pay.setOrderDate(new Date());
        pay.setOrdId(tradeOrder.getOrderId());
        pay.setReqId(tradeOrder.getReqId());
        pay.setVersion(1);
        pay.setChanelNo(tradeOrder.getChannelNo());
        pay.setStatus(OrderStatus.CREATEORDER.getValue());
        pay.setReqTime(new Date());
        pay.setPayOrderId(tradeOrder.getReqId());
        pay.setPayType(tradeOrder.getPayType());
        int count = payManager.savePayAndCreateLog(pay);
        if (count > 0) {
            LOGGER.info(TradeLogConstant.LOGGER_MODULE_QUICK_PAY, TradeLogConstant.LOGGER_BIZ_QUICK_PAY, "创建支付订单成功"
                            + pay.toString());
            return pay;
        } else {
            LOGGER.error(TradeLogConstant.LOGGER_MODULE_QUICK_PAY, TradeLogConstant.LOGGER_BIZ_QUICK_PAY, null,
                            "创建支付订单失败" + pay.toString());
            throw new TradeException("创建支付订单失败！");
        }
    }

}

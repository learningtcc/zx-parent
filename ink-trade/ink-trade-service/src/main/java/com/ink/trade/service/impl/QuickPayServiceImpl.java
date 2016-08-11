package com.ink.trade.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ink.asile.core.manager.IAsileBusinessManager;
import com.ink.asile.core.manager.IAsileResCodeManager;
import com.ink.asile.core.manager.IAsileSignManager;
import com.ink.asile.core.po.AsileResCode;
import com.ink.asile.core.po.AsileSign;
import com.ink.base.log.util.YinkerLogger;
import com.ink.channel.api.model.in.QuickAgainPayInput;
import com.ink.channel.api.model.in.QuickPayInput;
import com.ink.channel.api.model.out.QuickPayOutput;
import com.ink.channel.api.service.QuickAgainPayService;
import com.ink.channel.api.service.QuickPayService;
import com.ink.channel.core.cnst.ChannelConstants;
import com.ink.trade.api.enums.OrderStatus;
import com.ink.trade.api.enums.PayType;
import com.ink.trade.api.enums.TradeType;
import com.ink.trade.api.model.in.TradeQuickPayInput;
import com.ink.trade.api.model.out.TradeQuickPayOutput;
import com.ink.trade.api.service.ITradeQuickPayService;
import com.ink.trade.core.cnst.TradeLogConstant;
import com.ink.trade.core.cnst.TradeRespConstant;
import com.ink.trade.core.enums.TradeStatus;
import com.ink.trade.core.exception.TradeException;
import com.ink.trade.core.exception.orderfail.AsileRequestTimeOutException;
import com.ink.trade.core.manager.IAuthManager;
import com.ink.trade.core.manager.IPayManager;
import com.ink.trade.core.manager.ITradeOrderManager;
import com.ink.trade.core.manager.IUnknownOrderManager;
import com.ink.trade.core.po.Auth;
import com.ink.trade.core.po.Pay;
import com.ink.trade.core.po.TradeOrder;
import com.ink.trade.core.po.UnknownOrder;
import com.ink.trade.core.query.AuthQuery;
import com.ink.trade.service.PreCollectionCallBackService;
import com.ink.trade.service.check.CheckOptions;
import com.ink.trade.service.check.Order;
import com.ink.trade.service.dto.PreCollectionCallBackDto;
import com.ink.trade.service.mq.constant.QueenNameConstant;

/**
 * 
 * <pre>
 * <b>类描述:</b>(快捷支付实现)
 * <b>作者:</b>zx
 * <b>创建日期:</b>2016年5月16日 下午5:34:05
 * </pre>
 */
@Service("quickPayService")
public class QuickPayServiceImpl implements ITradeQuickPayService {
    private YinkerLogger LOGGER = YinkerLogger.getLogger(QuickPayServiceImpl.class);
    
    @Autowired
    @Qualifier("userLegalCheck")
    private CheckOptions check;//用户合法性检查
    @Autowired
    private QuickPayService quickPayService;
    @Autowired
    private ITradeOrderManager tradeOrderManager;
    @Autowired
    private IPayManager payManager;
    @Autowired
    private IAsileResCodeManager asileResCodeManager;
    @Autowired
    private IAuthManager authManager;
    @Autowired
    private PreCollectionCallBackService preCollectionCallBackService;
    @Autowired
    private IUnknownOrderManager unknownOrderManager;
    @Autowired
    private IAsileSignManager asileSignManager;// 签约信息表
    @Autowired
    private QuickAgainPayService quickAgainPayService;// 再次支付
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private IAsileBusinessManager asileBusinessManager;// 渠道返回信息为同步还是异步字典

    /**
     * 
     * @Description 支付 分为首次支付和再次支付
     * @author xuguoqi
     * @date 2016年6月20日 上午11:31:50
     * @param input
     * @return
     */
    @Override
    public TradeQuickPayOutput quickPay(TradeQuickPayInput input) {
        LOGGER.info(TradeLogConstant.LOGGER_MODULE_QUICK_PAY, TradeLogConstant.LOGGER_BIZ_QUICK_PAY, "交易系统快捷支付开始");
        TradeQuickPayOutput out = new TradeQuickPayOutput();
        out.setMerchantId(input.getMerchantId());
        out.setPayType(input.getPayType());
        out.setTradeCode(input.getTradeCode());
        out.setUserId(input.getUserId());
        // 交易检查
        try {
            List<Auth> authList = this.getAuthList(input);
            TradeOrder tradeOrder = check(input, authList);
            if (!tradeOrder.getStatus().equals(OrderStatus.PENDING.getValue())) {
                LOGGER.error(TradeLogConstant.LOGGER_MODULE_QUICK_PAY, TradeLogConstant.LOGGER_BIZ_QUICK_PAY, "签约订单不正常！" + input.getOrderId(), null);
                throw new TradeException(TradeRespConstant.ORDER_STATUS_ERROR,TradeRespConstant.ORDER_STATUS_ERROR_MSG);
            }

            // 更新交易订单状态处理中
            tradeOrder.setTxnName(TradeType.getNameByCode(input.getTradeCode()));// 交易名称
            tradeOrder.setTxnCode(input.getTradeCode());// 交易码
            tradeOrder.setStatus(OrderStatus.PROCESSING.getValue());
            tradeOrder.setLastupdateTime(new Date());
            tradeOrderManager.updateOrderAndCreateLog(tradeOrder);
            // 创建支付订单
            Pay payOrder = createPayOrder(tradeOrder);
            //向对账发送预处理命令
            prepareBalanceSys(payOrder);
            // 调用渠道支付
            QuickPayOutput quickPayOut = pay(payOrder, tradeOrder, input, authList);
            LOGGER.info(TradeLogConstant.LOGGER_MODULE_QUICK_PAY, TradeLogConstant.LOGGER_BIZ_QUICK_PAY, "快捷支付调用渠道返回："+quickPayOut.toString());
         // 更新渠道响应码到支付订单表
         			payOrder.setAsileRepCode(quickPayOut.getResCode());// 渠道响应码
         			payOrder.setAsileRepMsg(quickPayOut.getResMsg());
         			payOrder.setPayReqId(quickPayOut.getOrgTranFlow());// 渠道交易流水号
         			// 判断同步调用还是异步调用
         			if (asileBusinessManager.IsSynchronized(tradeOrder.getChannelNo(), input.getPayType())) {
         				// 成功
         				PreCollectionCallBackDto dto = new PreCollectionCallBackDto();
         				dto.setOrderNo(payOrder.getPayOrderId());
         				dto.setTranFlowNo(quickPayOut.getOrgTranFlow());
         				if (com.ink.channel.core.enums.OrderStatus.SUCCESS_CODE.getCode().equals(quickPayOut.getResStatus())) {
         					// 订单成功
         					// 更新渠道响应码到支付订单表
         					payOrder.setRepCode(TradeRespConstant.TRADE_SUCCESS);// 平台响应码
         					payOrder.setRepMsg(TradeRespConstant.TRADE_SUCCESS_MSG);
         					long updateResCodeStart = System.currentTimeMillis();
         					payManager.updatePayAndCreateLog(payOrder);
         					long updateResCodeEnd = System.currentTimeMillis();
         					LOGGER.debug("提现更新渠道响应码到支付订单耗时" + (updateResCodeEnd - updateResCodeStart));
         					out.setRequestId(tradeOrder.getReqId());
         					out.setTradeStatus(TradeStatus.SUCCESS.getValue());
         					out.setReponseCode(TradeRespConstant.TRADE_SUCCESS);
         					out.setReponseMsg(TradeRespConstant.TRADE_SUCCESS_MSG);
         					dto.setOrderStatus("01");
         				}
         				//处理中,订单未知
         				if (com.ink.channel.core.enums.OrderStatus.PROCESSES_CODE.getCode().equals(quickPayOut.getResStatus())) {
         					payOrder.setRepCode(TradeRespConstant.TRADE_PROCESSING);// 平台响应码
         					payOrder.setRepMsg(TradeRespConstant.TRADE_PROCESSING_MSG);
         					long updateResCodeStart = System.currentTimeMillis();
         					payManager.updatePayAndCreateLog(payOrder);
         					long updateResCodeEnd = System.currentTimeMillis();
         					LOGGER.debug("提现更新渠道响应码到支付订单耗时" + (updateResCodeEnd - updateResCodeStart));
         					out.setRequestId(tradeOrder.getReqId());
         					out.setTradeStatus(TradeStatus.PROCESSING.getValue());
         					out.setReponseCode(TradeRespConstant.TRADE_PROCESSING);
         					out.setReponseMsg(TradeRespConstant.TRADE_PROCESSING_MSG);
         					dto.setOrderStatus("02");
         				}
         				//失败
         				if (com.ink.channel.core.enums.OrderStatus.FAILE_CODE.getCode().equals(quickPayOut.getResStatus())) {
         					AsileResCode asileResCode = asileResCodeManager.findByAsileCodeAndAsileResCode(payOrder.getChanelNo(), quickPayOut.getResCode());
         					if (asileResCode == null) {
         						asileResCode=new AsileResCode();
         						LOGGER.error(TradeLogConstant.LOGGER_MODULE_QUICK_PAY, TradeLogConstant.LOGGER_BIZ_QUICK_PAY, "快捷支付失败,原因:解析渠道响应信息失败,响应码：" + quickPayOut.getResCode() + "响应信息：" + quickPayOut.getResMsg(), input.getOrderId());
         						asileResCode.setResCode(TradeRespConstant.TRADE_FAIL_UNKNOWN);
         						asileResCode.setResMsg(TradeRespConstant.TRADE_FAIL_UNKNOWN_MSG);
         					}
         					payOrder.setRepCode(asileResCode.getResCode());// 平台响应码
         					payOrder.setRepMsg(asileResCode.getResMsg());
         					long updateResCodeStart = System.currentTimeMillis();
         					payManager.updatePayAndCreateLog(payOrder);
         					long updateResCodeEnd = System.currentTimeMillis();
         					LOGGER.debug("提现更新渠道响应码到支付订单耗时" + (updateResCodeEnd - updateResCodeStart));
         					out.setReponseCode(asileResCode.getResCode());
         					out.setReponseMsg(asileResCode.getResMsg());
         					out.setTradeStatus(TradeStatus.FAIL.getValue());
         					out.setRequestId(tradeOrder.getReqId());
         					dto.setOrderStatus("03");
         				}
         				long syncCallStart = System.currentTimeMillis();
         				preCollectionCallBackService.callBack(dto);
         				long syncCallEnd = System.currentTimeMillis();
         				LOGGER.info(TradeLogConstant.LOGGER_MODULE_QUICK_PAY, TradeLogConstant.LOGGER_BIZ_QUICK_PAY,"快捷充值，回调耗时"+(syncCallEnd-syncCallStart));
         			} else {
         				// 判断是否是连接超时
         				if (ChannelConstants.REQUEST_EXCEPTION_CODE.equals(quickPayOut.getResCode())) {
         					// 异步更新支付订单和交易订单为失败
         					payOrder.setRepCode(TradeRespConstant.CHANNEL_REQUEST_TIMEOUT);// 平台响应码
         					payOrder.setRepMsg(TradeRespConstant.CHANNEL_REQUEST_TIMEOUT_MSG);
         					payOrder.setStatus(OrderStatus.FAIL.getValue());
         					tradeOrder.setStatus(OrderStatus.FAIL.getValue());
         					payManager.updatePayAndCreateLog(payOrder);
         					tradeOrderManager.updateOrderAndCreateLog(tradeOrder);
         					LOGGER.error(TradeLogConstant.LOGGER_MODULE_QUICK_PAY, TradeLogConstant.LOGGER_BIZ_QUICK_PAY, "快捷调用渠道连接超时", "");
         					throw new AsileRequestTimeOutException();
         				}
         				payOrder.setRepCode(TradeRespConstant.TRADE_PROCESSING);// 平台响应码
     					payOrder.setRepMsg(TradeRespConstant.TRADE_PROCESSING_MSG);
     					long updateResCodeStart = System.currentTimeMillis();
     					payManager.updatePayAndCreateLog(payOrder);
     					long updateResCodeEnd = System.currentTimeMillis();
     					LOGGER.debug("提现更新渠道响应码到支付订单耗时" + (updateResCodeEnd - updateResCodeStart));
         				out.setRequestId(tradeOrder.getReqId());
         				out.setTradeStatus(TradeStatus.PROCESSING.getValue());
         				out.setReponseCode(TradeRespConstant.TRADE_PROCESSING);
         				out.setReponseMsg(TradeRespConstant.TRADE_PROCESSING_MSG);
         				this.savaUnKnownOrder(payOrder, tradeOrder);
         			}

        } catch (TradeException ex) {
            LOGGER.error(TradeLogConstant.LOGGER_MODULE_QUICK_PAY, TradeLogConstant.LOGGER_BIZ_QUICK_PAY, ex.getCode()
                            + ex.getMessage(), "");
            out.setTradeStatus(TradeStatus.FAIL.getValue());
            out.setReponseCode(ex.getCode());
            out.setReponseMsg(ex.getMessage());
        } catch (Exception ex) {
            LOGGER.error(TradeLogConstant.LOGGER_MODULE_QUICK_PAY, TradeLogConstant.LOGGER_BIZ_QUICK_PAY, "快捷支付失败！", ex.getMessage());
            out.setTradeStatus(TradeStatus.FAIL.getValue());
            out.setReponseCode(TradeRespConstant.TRADE_SYSERROR);
            out.setReponseMsg(TradeRespConstant.TRADE_SYSERROR_MSG);
        }
        LOGGER.info(TradeLogConstant.LOGGER_MODULE_QUICK_PAY, TradeLogConstant.LOGGER_BIZ_QUICK_PAY, "");
        return out;
    }
    
    /**
     * 
     * @Description 渠道响应超时时,支付信息保存到unKnownOrder中
     * @author xuguoqi
     * @date 2016年6月22日 上午11:27:21
     * @param payOrder
     * @param tradeOrder
     */
    private void savaUnKnownOrder(Pay payOrder,TradeOrder tradeOrder){
    	UnknownOrder unknownOrder = new UnknownOrder();
        unknownOrder.setPayId(payOrder.getId());
        unknownOrder.setExecuteRemark("NONE");
        unknownOrder.setFinalStatus("N");
        unknownOrder.setPayOrderNo(payOrder.getPayOrderId());
        unknownOrder.setOrderStatus(payOrder.getStatus());
        unknownOrder.setChannelNo(payOrder.getChanelNo());
        unknownOrder.setTradeOrderId(tradeOrder.getId());
        unknownOrder.setExecuteCount(0);
        unknownOrder.setTransType(tradeOrder.getPayType());
        unknownOrder.setOrderTime(payOrder.getOrderDate());
        unknownOrder.setLastUpdateTime(new Date());
        unknownOrder.setMchId(tradeOrder.getMchId());
        try {
            unknownOrderManager.save(unknownOrder);
        } catch (Exception e) {
            LOGGER.error(TradeLogConstant.LOGGER_MODULE_QUICK_PAY, TradeLogConstant.LOGGER_BIZ_QUICK_PAY,"保存未知状态订单异常" + unknownOrder, e, payOrder.getOrdId());
        }
    }
    

    /**
     * 
     * @Description 
     * @author xuguoqi
     * @date 2016年6月20日 下午5:54:42
     * @param input
     * @param quickPayOut
     * @param authList2
     */
    private void saveAsileSign(TradeOrder tradeOrder, QuickPayOutput quickPayOut, List<Auth> authList) {

        AsileSign asileSign = new AsileSign();
        asileSign.setCardNo(authList.get(0).getCardNo());
        asileSign.setBankShort(authList.get(0).getBankShort());
        asileSign.setCardType(authList.get(0).getCardType());
        asileSign.setChanelNo(tradeOrder.getChannelNo());
        asileSign.setCid(authList.get(0).getId());
        asileSign.setCreateTime(new Date());
        asileSign.setIdNo(authList.get(0).getIdNo());
        asileSign.setIdType(authList.get(0).getIdType());
        asileSign.setIsDelete(0);
        asileSign.setUserName(authList.get(0).getUserName());
        asileSign.setLastupdateTime(new Date());
        asileSign.setMchId(authList.get(0).getMchId());
        asileSign.setPayType(PayType.QUICKPAY.getValue());
        asileSign.setPhone(authList.get(0).getPhoneNo());
        asileSign.setReqId(tradeOrder.getReqId());
        asileSign.setSignId(quickPayOut.getIdentityId());
        asileSign.setStatus(1);
        asileSign.setUserId(authList.get(0).getUserId());
        asileSign.setVersion(1);

        asileSignManager.save(asileSign);
    }

    /**
     * 订单检查
     * 
     * @param input
     * @param authList
     */
    private TradeOrder check(TradeQuickPayInput input, List<Auth> authList) {
        // 交易类型检查
        if (!input.getTradeCode().trim().equals(TradeType.RECHARGE.getCode())) {
        	throw new TradeException(TradeRespConstant.TRADE_TYPE_ERROR,TradeRespConstant.TRADE_TYPE_ERROR_MSG);
        }
        
      //用户合法性校验
        Order checkOrder = new Order();
        checkOrder.setMerchantId(input.getMerchantId());
        checkOrder.setUserId(input.getUserId());
        checkOrder.setOrderId(input.getOrderId());
        check.operateCheck(checkOrder);
        
        // 校验订单唯一性
        TradeOrder param = new TradeOrder();
        param.setMchId(input.getMerchantId());
        param.setOrderId(input.getOrderId());
        TradeOrder order = tradeOrderManager.getByMerNoAndMerOrderNo(param);
        if (order == null) {
            LOGGER.error(TradeLogConstant.LOGGER_MODULE_QUICK_PAY, TradeLogConstant.LOGGER_BIZ_QUICK_PAY, "用户" + input.getUserId() + "请求订单" + input.getOrderId() + "不存在", "");
            throw new TradeException(TradeRespConstant.TRADE_Order_0001, TradeRespConstant.TRADE_Order_0001_MSG);
        }

        return order;
    }
    
    /**
     * 
     * @Description 查询绑卡信息
     * @author xuguoqi
     * @date 2016年6月22日 上午11:12:54
     * @param tradeQuickPayInput
     * @return
     */
    private List<Auth> getAuthList(TradeQuickPayInput tradeQuickPayInput) {
        AuthQuery query = new AuthQuery();
        query.setMchId(tradeQuickPayInput.getMerchantId());
        query.setUserId(tradeQuickPayInput.getUserId());
        query.setPayType(PayType.ALL.getValue());
        query.setCardNo(tradeQuickPayInput.getCardNo());
        query.setMasterMark(true);// 查主库
        List<Auth> authList = authManager.find(query);
        if (CollectionUtils.isEmpty(authList)) {//未绑卡
        	throw new TradeException(TradeRespConstant.CARD_NOT_BIND,TradeRespConstant.CARD_NOT_BIND_MSG);
        }
        return authList;
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
     * 调用渠道支付
     * 
     * @param payOrder
     * @param tradeOrder
     * @param tradeQuickPayInput
     * @param authList
     * @return
     */
    private QuickPayOutput pay(Pay payOrder, TradeOrder tradeOrder, TradeQuickPayInput tradeQuickPayInput,
                    List<Auth> authList) {
        QuickPayInput quickPay = new QuickPayInput();
        AsileSign asileSign = this.selectSignIdByChannel(authList.get(0), tradeOrder.getChannelNo(), tradeQuickPayInput.getPayType());

        QuickPayOutput quickPayOut;
        Long startQuick = System.currentTimeMillis();
        if (null==asileSign) {// 首次支付
            quickPay.setIdNo(authList.get(0).getIdNo());
            quickPay.setUserName(authList.get(0).getUserName());
            quickPay.setIdType(authList.get(0).getIdType());
            quickPay.setPhoneNo(authList.get(0).getPhoneNo());
            quickPay.setAmount(payOrder.getAmt());
            quickPay.setCardNo(tradeOrder.getCardNo());
            quickPay.setOrderNo(payOrder.getPayOrderId());
            quickPay.setValidCode(tradeQuickPayInput.getValidCode());
            quickPay.setToken(tradeOrder.getToken());
            quickPay.setChannelId(payOrder.getChanelNo());
            quickPay.setBankShort(tradeOrder.getBankShort());
            quickPay.setClientDate(payOrder.getReqTime());
            quickPay.setMerchantNo(tradeQuickPayInput.getMerchantId());
            quickPay.setOrgTranFlow(tradeOrder.getRemark());//渠道反回的流水
            LOGGER.info(TradeLogConstant.LOGGER_MODULE_QUICK_PAY, TradeLogConstant.LOGGER_BIZ_QUICK_PAY, "调用渠道快捷支付开始" + quickPay);

            quickPayOut = quickPayService.pay(quickPay);
            AsileResCode asileResCode = asileResCodeManager.findByAsileCodeAndAsileResCode(payOrder.getChanelNo(),quickPayOut.getResCode());
            if (asileResCode != null && TradeRespConstant.TRADE_SUCCESS.equals(asileResCode.getResCode())) {
                // 保存首次签约信息表
                this.saveAsileSign(tradeOrder, quickPayOut, authList);
            }
        } else {// 再次支付
            QuickAgainPayInput againInput = new QuickAgainPayInput();
            againInput.setAmount(payOrder.getAmt());
            againInput.setBankShort(tradeOrder.getBankShort());
            againInput.setCardNo(tradeOrder.getCardNo());
            againInput.setClientDate(new Date());
            againInput.setChannelId(payOrder.getChanelNo());
            againInput.setIdentityId(asileSign.getSignId());
            againInput.setIdNo(authList.get(0).getIdNo());
            againInput.setIdType(authList.get(0).getIdType());
            againInput.setOrderNo(payOrder.getPayOrderId());
            againInput.setPhoneNo(authList.get(0).getPhoneNo());
            againInput.setToken(tradeOrder.getToken());
            againInput.setValidCode(tradeQuickPayInput.getValidCode());
            againInput.setUserName(authList.get(0).getUserName());
            againInput.setMerchantNo(tradeQuickPayInput.getMerchantId());
            againInput.setOrgTranFlow(tradeOrder.getRemark());//渠道返回流水号
            quickPayOut = quickAgainPayService.againPay(againInput);
        }
        if (quickPayOut == null) {
            LOGGER.error(TradeLogConstant.LOGGER_MODULE_QUICK_PAY, TradeLogConstant.LOGGER_BIZ_QUICK_PAY, null, "调用渠道快捷支付失败" + quickPay.toString());
            throw new TradeException("EE0001","调用渠道快捷支付失败");
        }
        LOGGER.debug("调用渠道快捷支付完成，耗时" + (System.currentTimeMillis() - startQuick));
        return quickPayOut;

    }

    /**
     * 创建支付订单
     * 
     * @param tradeOrder
     * @return
     */
    private Pay createPayOrder(TradeOrder tradeOrder) {
        Pay pay = new Pay();
        pay.setAmt(tradeOrder.getAmt());
        pay.setPayType(tradeOrder.getPayType());
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
        int count = payManager.savePayAndCreateLog(pay);
        if (count > 0) {
            LOGGER.info(TradeLogConstant.LOGGER_MODULE_QUICK_PAY, TradeLogConstant.LOGGER_BIZ_QUICK_PAY, "创建支付订单成功"+ pay.toString());
            return pay;
        } else {
            LOGGER.error(TradeLogConstant.LOGGER_MODULE_QUICK_PAY, TradeLogConstant.LOGGER_BIZ_QUICK_PAY, null, "创建支付订单失败" + pay.toString());
            throw new TradeException("EE0001","创建支付订单失败！");
        }
    }
    /**
	 * 向对账系统发送充值预处理指令
	 *
	 * @param pay
	 */
	private void prepareBalanceSys(Pay pay) {
		Map<String, Object> prepareMap = new HashMap<String, Object>();
		try {
			prepareMap.put("amt", pay.getAmt().toString());// 金额
			prepareMap.put("platformOrderNo", pay.getPayOrderId());// 平台订单号
			prepareMap.put("payTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(pay.getCreateTime()));// 订单创建时间
			prepareMap.put("payStatus", "1");// 交易状态 1、待支付 2、支付成功 3、支付失败
			amqpTemplate.convertAndSend(QueenNameConstant.BALANCE_PREPARED_MQ_NAME, prepareMap);

			LOGGER.info(TradeLogConstant.LOGGER_MODULE_AUTH_PAY, TradeLogConstant.LOGGER_BIZ_AUTH_PAY, "充值交易订单" + prepareMap + "对账预处理指令发送成功");
		} catch (AmqpException e) {
			LOGGER.error(TradeLogConstant.LOGGER_MODULE_AUTH_PAY, TradeLogConstant.LOGGER_BIZ_AUTH_PAY, "交易订单" + pay.getOrdId() + "发送对账预处理MQ失败，内容："+prepareMap, e, pay.getOrdId());
		}
	}

}

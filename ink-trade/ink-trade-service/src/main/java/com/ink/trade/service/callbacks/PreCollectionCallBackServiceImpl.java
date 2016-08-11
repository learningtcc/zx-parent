package com.ink.trade.service.callbacks;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.ink.balance.api.constants.PayMethodConst;
import com.ink.balance.api.constants.PayStatusConst;
import com.ink.base.log.util.YinkerLogger;
import com.ink.base.utils.IdCodeGenerator;
import com.ink.base.utils.dateUtil.DateUtil;
import com.ink.channel.core.utils.Constants;
import com.ink.trade.api.enums.OrderStatus;
import com.ink.trade.api.enums.PayType;
import com.ink.trade.core.cnst.TradeLogConstant;
import com.ink.trade.core.cnst.TradeUserConstant;
import com.ink.trade.core.enums.IsDelete;
import com.ink.trade.core.exception.TradeException;
import com.ink.trade.core.manager.IFailerLogManager;
import com.ink.trade.core.manager.IPayManager;
import com.ink.trade.core.manager.ITradeOrderManager;
import com.ink.trade.core.manager.IUnknownOrderManager;
import com.ink.trade.core.po.FailerLog;
import com.ink.trade.core.po.Pay;
import com.ink.trade.core.po.TradeOrder;
import com.ink.trade.core.po.UnknownOrder;
import com.ink.trade.service.PreCollectionCallBackService;
import com.ink.trade.service.dto.NotifyMerchantDto;
import com.ink.trade.service.dto.PreCollectionCallBackDto;
import com.ink.trade.service.mq.constant.QueenNameConstant;
import com.ink.trade.service.util.NotifyMerchantUtil;
import com.ink.user.api.constants.RespCodeConstant;
import com.ink.user.api.model.in.AccRechargeInput;
import com.ink.user.api.model.out.RetOutput;
import com.ink.user.api.service.IAccRechargeService;

/**
 * 代收回调服务实现 Created by huohb on 2016/5/26.
 */
@Service("preCollectionCallBackService")
public class PreCollectionCallBackServiceImpl implements
        PreCollectionCallBackService {

    private static final YinkerLogger logger = YinkerLogger
            .getLogger(PreCollectionCallBackServiceImpl.class);
    @Autowired
    private IPayManager payManager;// 支付订单Manager
    @Autowired
    private ITradeOrderManager tradeOrderManager;// 交易订单Manager
    @Autowired
    private IUnknownOrderManager unknownOrderManager;// 未知订单Manager
    @Autowired
    private IFailerLogManager failerLogManager;// 渠道失败日志Manager

    @Autowired
    private IAccRechargeService accRechargeService;// 账户充值接口
    @Autowired
    private AmqpTemplate amqpTemplate;// rabbitMq
    @Autowired
    private IdCodeGenerator certificateUtil;// 配置信息

    @Override
    public boolean callBack(PreCollectionCallBackDto dto) {
        try {
            // 查询支付订单
            Pay queryPayParam = new Pay();
            queryPayParam.setPayOrderId(dto.getOrderNo());
            Pay pay = payManager.findPayOrder(queryPayParam);
            if (pay == null) {
                logger.error("代收回调，支付订单" + dto.getOrderNo() + "不存在");
                throw new RuntimeException("pay order not exist");
            }
            // 查询交易订单
            TradeOrder queryTradeParam = new TradeOrder();
            queryTradeParam.setMchId(pay.getMchId());
            queryTradeParam.setOrderId(pay.getOrdId());
            TradeOrder tradeOrder = tradeOrderManager
                    .getByMerNoAndMerOrderNo(queryTradeParam);
            if (tradeOrder == null) {
                logger.error("代收回调，交易订单不存在，支付订单号" + dto.getOrderNo());
                throw new RuntimeException("trade order not exist");
            }
            // 判断订单状态
            int orderStatus = OrderStatus.SUCCESS.getValue();
            String payStatus = null;
            // 订单成功
            if ("01".equals(dto.getOrderStatus())) {
                // 调用账务系统进行记账(dubbo)
                long accountStart = System.currentTimeMillis();
                callAccount(tradeOrder);
                long accountEnd = System.currentTimeMillis();

                logger.debug("充值交易订单" + tradeOrder.getOrderId() + "调用记账耗时"
                        + (accountEnd - accountStart) + "ms");
                payStatus = PayStatusConst.PAY_SUCCESS;
            }// 订单未知
            else if ("02".equals(dto.getOrderStatus())) {
                orderStatus = OrderStatus.PROCESSING.getValue();
                saveUnknownOrder(pay, tradeOrder);
            }// 订单失败
            else if ("03".equals(dto.getOrderStatus())) {
                orderStatus = OrderStatus.FAIL.getValue();
                saveFailerLog(pay.getChanelNo(), pay, tradeOrder);
                payStatus = PayStatusConst.PAY_FAIL;
            }

            // 更新支付订单的状态并创建支付流水
            pay.setStatus(orderStatus);
            updatePayOrderAndCreatePayLog(pay);
            logger.info(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY,
                    TradeLogConstant.LOGGER_BIZ_COLLECT,
                    "充值交易订单" + tradeOrder.getOrderId() + "更新支付订单表并创建支付流水完成");

            if (payStatus != null) {
            	
            	//回调商户告知交易结果
            	if(StringUtils.isNoneBlank(tradeOrder.getNoticeUrl())){
            		NotifyMerchantDto notifyDto = new NotifyMerchantDto();
            		notifyDto.setMerOrderNo(tradeOrder.getOrderId());
            		notifyDto.setPlatOrderNo(tradeOrder.getReqId());
            		notifyDto.setOrderStatus(payStatus);
            		this.notifyMerchant(tradeOrder.getNoticeUrl(), notifyDto);
            	}
            	
            	
            	// 根据渠道返回码通知对账系统更新订单状态
                updateBalanceSys(pay, payStatus);
            }
            
            
            // 更新交易订单的状态
            tradeOrder.setStatus(orderStatus);
            updateTradeOrderAndCreateTradeLog(tradeOrder);

        } catch (TradeException e) {
            logger.error(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY,
                    TradeLogConstant.LOGGER_BIZ_COLLECT, "业务异常", e.getMessage());
            return true;
        } catch (Exception e) {
            logger.error(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY,
                    TradeLogConstant.LOGGER_BIZ_COLLECT, "代收回调异常",
                    e.getMessage());
            return false;
        }
        return true;
    }
    
    /**
     * 
     * @Description 异步同商户订单结果
     * @author xuguoqi
     * @date 2016年7月4日 下午6:53:46
     * @param notifyUr
     * @param dto
     */
    private void notifyMerchant(String notifyUr ,NotifyMerchantDto dto){
    	boolean result = NotifyMerchantUtil.notify(notifyUr, dto);
    	logger.info("通知商户结果:"+ result);
    }

    /**
     * 根据标识更新交易订单并创建交易流水
     *
     * @param order
     */
    private void updateTradeOrderAndCreateTradeLog(TradeOrder order) {
        order.setLastupdateTime(new Date());
        try {
            tradeOrderManager.updateStatusAndCreateLog(order);
        } catch (TradeException e) {
            logger.error(e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY,
                    TradeLogConstant.LOGGER_BIZ_COLLECT,
                    "更新交易订单" + order.getOrderId() + "失败", e, order.getOrderId());
        }
    }

    /**
     * 更新支付订单并创建支付流水
     *
     * @param pay
     */
    private void updatePayOrderAndCreatePayLog(Pay pay) {
        pay.setLastupdateTime(new Date());
        try {
            payManager.updateStatusAndCreateLog(pay);
        } catch (TradeException e) {
            logger.error(e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY,
                    TradeLogConstant.LOGGER_BIZ_COLLECT,
                    "交易订单" + pay.getOrdId() + "更新支付订单失败", e, pay.getOrdId());
        }
    }

    /**
     * 通知对账系统更新充值订单状态
     *
     * @param pay
     * @param orderStatus
     */
    private void updateBalanceSys(Pay pay, String orderStatus) {
        try {
            // 获取商户号
            String channelNo = pay.getChanelNo();
            String merchantId = pay.getMchId();
            final String merchantNo = certificateUtil.getIpMapsConfig().get(
                    merchantId + channelNo + Constants.MerchantSuffix);

            Map<String, Object> updateMap = new HashMap<String, Object>();
            updateMap.put("arrivedAmt", pay.getAmt().toString());// 到账金额
            updateMap.put("platformOrderNo", pay.getPayOrderId());// 订单号
            updateMap.put("arrivedTime", new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss").format(pay.getLastupdateTime()));// 到账时间
            updateMap.put("payStatus", orderStatus);// 交易状态 1、待支付 2、支付成功 3、支付失败
            updateMap.put("channelNo", pay.getChanelNo());// 渠道号
            updateMap.put("transNo",
                    pay.getPayReqId() == null ? "" : pay.getPayReqId());
            updateMap.put("channelMerchantNo", merchantNo);
            // 渠道交易流水号
            String payMethod = null;
            // 代收
            if (PayType.COLLECT.getValue().equals(pay.getPayType())) {
                payMethod = PayMethodConst.COLLECT;
            } // 快捷
            else if (PayType.QUICKPAY.getValue().equals(pay.getPayType())) {
                payMethod = PayMethodConst.SHORTCUT;
            } // 网关
            else if (PayType.GATEWAY.getValue().equals(pay.getPayType())) {
                payMethod = PayMethodConst.BANK;
            } // 认证支付
            else if (PayType.AUTHPAY.getValue().equals(pay.getPayType())) {
                payMethod = PayMethodConst.AUTH;
            }
            updateMap.put("payMethod", payMethod);// 支付方式：（1：代收、2：代付、3：网银、4：快捷）
            logger.debug("充值给对账推送消息为["+convertMap2String(updateMap)+"]");
            amqpTemplate.convertAndSend(
                    QueenNameConstant.BALANCE_UPDATE_MQ_NAME, updateMap);
            logger.info(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY,
                    TradeLogConstant.LOGGER_BIZ_COLLECT, "充值交易订单" + updateMap
                            + "通知对账系统更新订单状态完成");
        } catch (AmqpException e) {
            logger.error(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY,
                    TradeLogConstant.LOGGER_BIZ_COLLECT,
                    "交易订单" + pay.getOrdId() + "通知对账系统更新订单状态失败", e,
                    pay.getOrdId());
        }
    }

    /**
     * Map转String
     * @param map
     * @return
     */
    private String convertMap2String(Map<String,Object> map){
        Set<Map.Entry<String, Object>> entries = map.entrySet();
        Iterator<Map.Entry<String, Object>> iterator = entries.iterator();
        StringBuffer strBuf = new StringBuffer();
        while (iterator.hasNext()){
            Map.Entry<String,Object> entry = iterator.next();
            strBuf.append(entry.getKey()).append(":").append(entry.getValue().toString()).append(",");
        }
        return strBuf.toString().substring(0, strBuf.toString().length()-1);
    }

    /**
     * 保存未知状态订单，用来查证交易
     *
     * @param pay
     */
    private void saveUnknownOrder(Pay pay, TradeOrder tradeOrder) {
        UnknownOrder unknownOrder = new UnknownOrder();
        unknownOrder.setPayId(pay.getId());
        unknownOrder.setExecuteRemark("NONE");
        unknownOrder.setFinalStatus("N");
        unknownOrder.setPayOrderNo(pay.getPayOrderId());
        unknownOrder.setOrderStatus(pay.getStatus());
        unknownOrder.setChannelNo(pay.getChanelNo());
        unknownOrder.setTradeOrderId(tradeOrder.getId());
        unknownOrder.setExecuteCount(0);
        unknownOrder.setTransType(tradeOrder.getPayType());
        unknownOrder.setOrderTime(pay.getOrderDate());
        unknownOrder.setLastUpdateTime(new Date());
        unknownOrder.setMchId(tradeOrder.getMchId());
        try {
            unknownOrderManager.save(unknownOrder);
        } catch (Exception e) {
            logger.error(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY,
                    TradeLogConstant.LOGGER_BIZ_COLLECT, "保存未知状态订单异常", e,
                    pay.getOrdId());
        }
    }

    /**
     * 保存渠道失败日志
     *
     * @param channelId
     * @param pay
     * @param tradeOrder
     */
    private void saveFailerLog(String channelId, Pay pay, TradeOrder tradeOrder) {
        try {
            FailerLog failerLog = new FailerLog();
            failerLog.setAsileCode(channelId);// 渠道号
            failerLog.setCreateTime(new Date());// 创建时间
            failerLog.setReqId(pay.getReqId());// 请求流水号
            failerLog.setTxnCode(tradeOrder.getTxnCode());// 交易码
            failerLog.setRemark(tradeOrder.getRemark());// 备注
            failerLog.setBankCode(tradeOrder.getBankShort());
            failerLog.setIsDelete(IsDelete.NO.getValue());// 逻辑删除位
            failerLog.setRouteBusinessType(tradeOrder.getRouteBusinessType());
            failerLogManager.save(failerLog);
            logger.info(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY,
                    TradeLogConstant.LOGGER_BIZ_COLLECT,
                    "充值交易订单" + pay.getOrdId() + "保存渠道失败日志表完成");
        } catch (Exception e) {
            logger.error("记录渠道失败日志失败", e);
        }
    }

    /**
     * 调用账务系统进行记账
     *
     * @param tradeOrder
     */
    private void callAccount(TradeOrder tradeOrder) {

        AccRechargeInput dto = new AccRechargeInput();
        RetOutput retOutput = new RetOutput();
        dto.setTxnCode("AR");
        dto.setMchId(tradeOrder.getMchId());
        dto.setCustId(tradeOrder.getUserId());
        dto.setOrdId(tradeOrder.getOrderId());
        dto.setTradeDate(DateUtil.formatToYYYYMMDDMMHHSSStr(new Date()));
        dto.setAmt(tradeOrder.getAmt().toString());
        dto.setCustFee("0");
        dto.setAccountType(tradeOrder.getAccountType());
        dto.setCardNo(tradeOrder.getCardNo());
        dto.setChannelId(tradeOrder.getChannelNo());
        dto.setMac(DigestUtils.md5Hex(TradeUserConstant.TRADE_USER_KEY+dto.getOrdId()));
        try {
            retOutput = accRechargeService.exec(dto);
        } catch (Exception e) {
            logger.error(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY,
                    TradeLogConstant.LOGGER_BIZ_COLLECT,
                    "交易订单" + tradeOrder.getOrderId()
                            + "调用余额账户记账失败，开始发送MQ进行异步记账", e,
                    tradeOrder.getOrderId());
            try {
                amqpTemplate.convertAndSend(
                        QueenNameConstant.ACC_RECHARG_MQ_NAME,
                        JSON.toJSONString(dto));
            } catch (AmqpException e1) {
                logger.error(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY,
                        TradeLogConstant.LOGGER_BIZ_COLLECT, "交易订单"
                                + tradeOrder.getOrderId() + "发送MQ进行余额账户异步记账失败",
                        e, tradeOrder.getOrderId());
            }
        }

        if (!RespCodeConstant.RespCode_000000.equals(retOutput.getRetCode())) {
            logger.error(
                    TradeLogConstant.LOGGER_MODULE_COLLECT_PAY,
                    TradeLogConstant.LOGGER_BIZ_COLLECT,
                    "交易订单" + tradeOrder.getOrderId() + "记账返回失败，失败码["
                            + retOutput.getRetCode() + "]，失败信息["
                            + retOutput.getRetMsg() + "]",
                    tradeOrder.getOrderId());
        }
    }


}

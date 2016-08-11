package com.ink.trade.service.callbacks;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
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
import com.ink.trade.core.cnst.TradeLogConstant;
import com.ink.trade.core.cnst.TradeRespConstant;
import com.ink.trade.core.cnst.TradeUserConstant;
import com.ink.trade.core.enums.IsDelete;
import com.ink.trade.core.exception.TradeException;
import com.ink.trade.core.manager.IFailerLogManager;
import com.ink.trade.core.manager.IPayManager;
import com.ink.trade.core.manager.ITradeOrderManager;
import com.ink.trade.core.po.FailerLog;
import com.ink.trade.core.po.Pay;
import com.ink.trade.core.po.TradeOrder;
import com.ink.trade.service.PrepaidCallBackService;
import com.ink.trade.service.dto.NotifyMerchantDto;
import com.ink.trade.service.mq.constant.QueenNameConstant;
import com.ink.trade.service.util.NotifyMerchantUtil;
import com.ink.user.api.constants.RespCodeConstant;
import com.ink.user.api.model.in.AccWithdrawAcceptInput;
import com.ink.user.api.model.in.AccWithdrawCancelInput;
import com.ink.user.api.model.out.RetOutput;
import com.ink.user.api.service.IAccWithdrawAcceptService;
import com.ink.user.api.service.IAccWithdrawCancelService;

@Service
public class PrepaidCallBackServiceImpl implements PrepaidCallBackService {
	private YinkerLogger logger = YinkerLogger
			.getLogger(PrepaidCallBackServiceImpl.class);
	@Autowired
	private AmqpTemplate amqpTemplate;

	@Autowired
	private IAccWithdrawAcceptService accWithdrawAcceptService;// 提现受理
	@Autowired
	private IAccWithdrawCancelService accWithdrawCancelService;// 提现业务撤回
	@Autowired
	private IPayManager payManager;
	@Autowired
	private ITradeOrderManager tradeOrderManager;
	@Autowired
	private IFailerLogManager failerLogManager;
	@Autowired
	private IdCodeGenerator idCodeGenerator;
	@Autowired
	private IdCodeGenerator certificateUtil;// 配置信息

	@Override
	public boolean callBack(String orderNo, boolean orderStatus,
			String tranFlowNo) {
		boolean flag = true;
		try {
			// 查询支付订单
			Pay queryPayParam = new Pay();
			queryPayParam.setPayOrderId(orderNo);
			Pay pay = payManager.findPayOrder(queryPayParam);
			if (pay == null) {
				logger.error("代付回调，支付订单" + orderNo + "不存在");
				throw new RuntimeException("pay order not exist");
			}
			// 查询交易订单
			TradeOrder queryTradeParam = new TradeOrder();
			queryTradeParam.setMchId(pay.getMchId());
			queryTradeParam.setOrderId(pay.getOrdId());
			TradeOrder tradeOrder = tradeOrderManager
					.getByMerNoAndMerOrderNo(queryTradeParam);
			if (tradeOrder == null) {
				logger.error("代付回调，交易订单不存在，支付订单号" + orderNo);
				throw new RuntimeException("trade order not exist");
			}
			if (pay.getStatus() == OrderStatus.SUCCESS.getValue()
					|| pay.getStatus() == OrderStatus.FAIL.getValue()) {
				return true;
			}
			String mchOrderStatus;
			if (orderStatus) {
				callBackSuccess(pay, tradeOrder, tranFlowNo);
				mchOrderStatus=PayStatusConst.PAY_SUCCESS;
			} else {
				callBackFailer(pay, tradeOrder, tranFlowNo);
				mchOrderStatus=PayStatusConst.PAY_FAIL;
			}
			if(StringUtils.isNoneBlank(tradeOrder.getNoticeUrl())){
				logger.info(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY,
						TradeLogConstant.LOGGER_BIZ_PAY,"回调商户地址"+tradeOrder.getNoticeUrl());
        		NotifyMerchantDto notifyDto = new NotifyMerchantDto();
        		notifyDto.setMerOrderNo(tradeOrder.getOrderId());
        		notifyDto.setPlatOrderNo(tradeOrder.getReqId());
        		notifyDto.setOrderStatus(mchOrderStatus);
        		this.notifyMerchant(tradeOrder.getNoticeUrl(), notifyDto);
        	}

		} catch (TradeException ex) {
			logger.error(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY,
					TradeLogConstant.LOGGER_BIZ_PAY, "代付回调失败,订单号：" + orderNo,
					ex, "");
			flag = true;
		} catch (Exception ex) {
			flag = false;
			logger.error(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY,
					TradeLogConstant.LOGGER_BIZ_PAY, "代付回调失败，订单号" + orderNo,
					ex, "");
		}
		return flag;
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
    	logger.info(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY,
				TradeLogConstant.LOGGER_BIZ_PAY,"通知商户结果:"+ result);
    }

	/**
	 * 
	 * @param pay
	 * @param tradeOrder
	 * @param userBalance
	 * @param transNo
	 */
	private void callBackSuccess(Pay pay, TradeOrder tradeOrder,
			 String transNo) {
		if (StringUtils.isNotEmpty(transNo)) {
			pay.setPayReqId(transNo);
		}
		pay.setStatus(OrderStatus.SUCCESS.getValue());
		// 通知对账
		pushToBalance(QueenNameConstant.BALANCE_UPDATE_MQ_NAME,
				balanceUpdateDataMap(pay, PayStatusConst.PAY_SUCCESS));
		// 通知账户
		callAccForWithdrawAccept(tradeOrder);
		// 更新支付订单
		updatePay(pay);
		// 更新交易订单
		tradeOrder.setStatus(OrderStatus.SUCCESS.getValue());
		updateTradeOrder(tradeOrder);
	}

	/**
	 * 
	 * @param pay
	 * @param tradeOrder
	 * @param transNo
	 */
	private void callBackFailer(Pay pay, TradeOrder tradeOrder, String transNo) {
		if (StringUtils.isNotEmpty(transNo)) {
			pay.setPayReqId(transNo);
		}
		pay.setStatus(OrderStatus.FAIL.getValue());
		// 对账更新状态
		pushToBalance(QueenNameConstant.BALANCE_UPDATE_MQ_NAME,
				balanceUpdateDataMap(pay, PayStatusConst.PAY_FAIL));
		// 失败时调账户解冻
		freezeThaw(tradeOrder);
		// 失败添加渠道到错误日志
		saveFailerLog(tradeOrder);
		// 更新支付订单 pay
		updatePay(pay);
		// 更新交易订单 trade
		tradeOrder.setStatus(OrderStatus.FAIL.getValue());
		updateTradeOrder(tradeOrder);
	}

	/**
	 * 向对账系统推送订单
	 * 
	 * @param pay
	 */
	public void pushToBalance(String adr, Map<String, String> params) {
		logger.info(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY,
				TradeLogConstant.LOGGER_BIZ_PAY, "向对账异步推送订单数据" + params);
		try {
			amqpTemplate.convertAndSend(adr, params);
		} catch (Exception ex) {
			logger.error(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY,
					TradeLogConstant.LOGGER_BIZ_PAY,
					"代付业务向对账推送流水信息失败" + params, ex, "");
		}
	}

	/**
	 * 交易失败时，通知账户解冻
	 * 
	 * @param order
	 * @param frezzAmtMap
	 */
	public void freezeThaw(TradeOrder order) {
		long startTime = System.currentTimeMillis();
		logger.info(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY,
				TradeLogConstant.LOGGER_BIZ_PAY, "交易提现,解冻资金" + order.toString());
		AccWithdrawCancelInput dto = new AccWithdrawCancelInput();
		RetOutput ret = new RetOutput();
		try {
			dto.setTxnCode("AWC");
			dto.setOriTxnCode("AWF");
			dto.setMchId(String.valueOf(order.getMchId()));
			dto.setOrdId(idCodeGenerator.getId().toString());
			dto.setOriOrdId(order.getReqId());// 交易流水号作为账户的订单号，保证唯一
			dto.setOriTradeDate(DateUtil.formatToYYYYMMDDMMHHSSStr(order
					.getTradeDate()));
			dto.setTradeDate(DateUtil.formatToYYYYMMDDMMHHSSStr(new Date()));
			dto.setMac(DigestUtils.md5Hex(TradeUserConstant.TRADE_USER_KEY+dto.getOrdId()));
			ret = accWithdrawCancelService.exec(dto);
			logger.debug("调用账户解冻结束，耗时"
					+ (System.currentTimeMillis() - startTime));
			logger.info(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY,
					TradeLogConstant.LOGGER_BIZ_PAY, "交易提现解冻输入："+dto.toString()+"交易提现解冻返回:" + ret);
			if (!RespCodeConstant.RespCode_000000.equals(ret.getRetCode())) {
				logger.error(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY,
						TradeLogConstant.LOGGER_BIZ_PAY,
						"交易体现,账户系统解冻失败!" + dto, null);
				// 放入队列
				putOrder2ExceptionQueue(
						QueenNameConstant.ACC_WITHDRAW_CANCEL_MQ_NAME, dto);
			}
		} catch (Exception e) {
			logger.error(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY,
					TradeLogConstant.LOGGER_BIZ_PAY, "交易体现,账户系统解冻失败!" + dto, e,
					null);
			// 放入队列
			putOrder2ExceptionQueue(
					QueenNameConstant.ACC_WITHDRAW_CANCEL_MQ_NAME, dto);
		}
	}

	/**
	 * 
	 * @param order
	 */
	private void saveFailerLog(TradeOrder order) {
		try {
			FailerLog failerLog = new FailerLog();
			failerLog.setAsileCode(order.getChannelNo());
			failerLog.setBankCode(order.getBankShort());
			failerLog.setCreateTime(new Date());
			failerLog.setRemark("");
			failerLog.setReqId(order.getReqId());
			failerLog.setTxnCode(order.getTxnCode());
			failerLog.setIsDelete(IsDelete.NO.getValue());
			failerLog.setCreateTime(new Date());
			failerLog.setRouteBusinessType(order.getRouteBusinessType());
			failerLogManager.save(failerLog);
		} catch (Exception e) {
			logger.error(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY,
					TradeLogConstant.LOGGER_BIZ_PAY, "交易提现失败,失败信息统计添加异常,订单："
							+ order.toString(), e, null);
		}
	}

	/**
	 * @date 2016年4月25日 下午5:26:55
	 * @Description: 更新pay状态
	 * @author wanghao
	 * @param pay
	 * @param status
	 */
	private void updatePay(Pay pay) {
		try {
			Date date = new Date();
			pay.setRepTime(date);
			pay.setLastupdateTime(date);
			payManager.updatePayAndCreateLog(pay);
		} catch (Exception e) {
			logger.error(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY,
					TradeLogConstant.LOGGER_BIZ_PAY,
					"交易提现,更新pay时数据库异常!" + pay.toString(), e, null);
			throw new TradeException(TradeRespConstant.TRADE_SYSERROR,
					TradeRespConstant.TRADE_SYSERROR_MSG);
		}
	}

	/**
	 * @Description: 更新订单状态
	 * @param order
	 * @param status
	 */
	public void updateTradeOrder(TradeOrder order) {
		try {
			order.setLastupdateTime(new Date());
			logger.info(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY,
					TradeLogConstant.LOGGER_BIZ_PAY,
					"交易提现,订单状态更新!" + order.toString());
			tradeOrderManager.updateStatusAndCreateLog(order);
		} catch (TradeException e) {
			throw e;
		} catch (Exception e) {
			logger.error(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY,
					TradeLogConstant.LOGGER_BIZ_PAY,
					"交易提现,数据库异常" + order.toString(), e, null);
		}
	}

	/**
	 * updateData
	 */
	private Map<String, String> balanceUpdateDataMap(Pay pay, String payStatus) {
		// 获取商户号
		String channelNo = pay.getChanelNo();
		String merchantId = pay.getMchId();
		final String merchantNo = certificateUtil.getIpMapsConfig().get(
				merchantId + channelNo + Constants.MerchantSuffix);

		// 数据封装
		Map<String, String> params = new HashMap<String, String>();
		params.put("platformOrderNo", pay.getPayOrderId());
		params.put("arrivedAmt", String.valueOf(pay.getAmt()));
		params.put("channelNo", pay.getChanelNo());
		params.put("arrivedTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(pay.getLastupdateTime()));
		params.put("transNo", pay.getPayReqId());
		params.put("payStatus", payStatus);
		params.put("payMethod", PayMethodConst.PAY);
		params.put("channelMerchantNo", merchantNo);
		logger.info(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY,
				TradeLogConstant.LOGGER_BIZ_PAY,"对账推送数据："+params.get("channelMerchantNo"),"");
		return params;
	}

	/**
	 * @Description: 代付成功执行提现受理
	 * @param withdrawInput
	 * @return RetOutput
	 * @throws Exception
	 */
	private void callAccForWithdrawAccept(TradeOrder order) {
		long startTime = System.currentTimeMillis();
		logger.info(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY,
				TradeLogConstant.LOGGER_BIZ_PAY, "通知账户提现受理" + order.toString());
		// 提现受理
		AccWithdrawAcceptInput dto = new AccWithdrawAcceptInput();
		RetOutput retOutput = null;
		try {
			dto.setAmt(order.getAmt().toString());
			dto.setChannelId(order.getChannelNo());
			dto.setCustFee("0.00");
			dto.setMchId(String.valueOf(order.getMchId()));
			dto.setOrdId(idCodeGenerator.getId().toString());
			dto.setOriOrdId(order.getReqId());// 交易流水号作为账户的订单号，保证唯一
			dto.setOriTradeDate(DateUtil.formatToYYYYMMDDMMHHSSStr(order
					.getTradeDate()));
			dto.setOriTxnCode("AWF");
			dto.setTradeDate(DateUtil.formatToYYYYMMDDMMHHSSStr(order
					.getTradeDate()));
			dto.setTxnCode("AWA");
			dto.setMac(DigestUtils.md5Hex(TradeUserConstant.TRADE_USER_KEY+dto.getOrdId()));
			retOutput = accWithdrawAcceptService.exec(dto);
			if (RespCodeConstant.RespCode_000000.equals(retOutput.getRetCode())) {
				logger.debug("调用账户提现受理，耗时"
						+ (System.currentTimeMillis() - startTime));
				logger.info(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY,
						TradeLogConstant.LOGGER_BIZ_PAY, "交易提现,冻结资金受理成功!"
								+ retOutput);
			} else {
				logger.error(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY,
						TradeLogConstant.LOGGER_BIZ_PAY,
						"交易提现,调用账户系统ACC30060失败," + dto.getOriOrdId() + "响应信息:"
								+ retOutput.toString(), "");
				// 放入队列
				putOrder2ExceptionQueue(
						QueenNameConstant.ACC_WITHDRAW_ACCEPT_MQ_NAME, dto);// TODO
			}

		} catch (Exception e) {
			logger.error(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY,
					TradeLogConstant.LOGGER_BIZ_PAY, "交易提现,调用账户系统ACC30060异常"
							+ dto.getOriOrdId(), e, "");
			// 放入队列
			putOrder2ExceptionQueue(
					QueenNameConstant.ACC_WITHDRAW_ACCEPT_MQ_NAME, dto);// TODO
		}

	}

	/**
	 * 同步调用账户提现操作失败，异步补偿
	 * 
	 * @param name
	 * @param object
	 */
	public void putOrder2ExceptionQueue(final String name, Object object) {
		try {
			amqpTemplate.convertAndSend(name, JSON.toJSONString(object));
		} catch (Exception ex) {
			logger.error(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY,
					TradeLogConstant.LOGGER_BIZ_PAY, "代付同步调用账户失败时异步补偿消息发送异常"
							+ JSON.toJSONString(object), ex, "");
		}
	}

}

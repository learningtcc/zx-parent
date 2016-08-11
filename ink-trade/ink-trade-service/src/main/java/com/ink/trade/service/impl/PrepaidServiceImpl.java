package com.ink.trade.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.asile.core.manager.IAsileBusinessManager;
import com.ink.asile.core.manager.IAsileResCodeManager;
import com.ink.asile.core.po.AsileResCode;
import com.ink.balance.api.constants.PayMethodConst;
import com.ink.balance.api.constants.PayStatusConst;
import com.ink.base.log.util.YinkerLogger;
import com.ink.base.utils.IdCodeGenerator;
import com.ink.base.utils.dateUtil.DateUtil;
import com.ink.channel.api.model.in.Pay2CardInput;
import com.ink.channel.api.model.out.Pay2CardOutput;
import com.ink.channel.api.service.Pay2CardService;
import com.ink.channel.core.cnst.ChannelConstants;
import com.ink.channel.core.minsheng.CmbcConstants;
import com.ink.trade.api.enums.OrderStatus;
import com.ink.trade.api.enums.PayType;
import com.ink.trade.api.enums.RouteBusinessType;
import com.ink.trade.api.enums.TradeCurrency;
import com.ink.trade.api.enums.TradeType;
import com.ink.trade.api.model.in.AsileRouteInput;
import com.ink.trade.api.model.in.PrepaidInput;
import com.ink.trade.api.model.out.AsileRouteOutput;
import com.ink.trade.api.model.out.PrepaidOutput;
import com.ink.trade.api.rule.IAsileRoute;
import com.ink.trade.api.service.IPrepaidService;
import com.ink.trade.core.cnst.TradeLogConstant;
import com.ink.trade.core.cnst.TradeRespConstant;
import com.ink.trade.core.cnst.TradeUserConstant;
import com.ink.trade.core.enums.TradeStatus;
import com.ink.trade.core.exception.TradeException;
import com.ink.trade.core.exception.orderfail.AsileRequestTimeOutException;
import com.ink.trade.core.manager.IAuthManager;
import com.ink.trade.core.manager.IPayManager;
import com.ink.trade.core.manager.ITradeOrderManager;
import com.ink.trade.core.manager.IUnknownOrderManager;
import com.ink.trade.core.manager.IUserBalanceManager;
import com.ink.trade.core.po.Auth;
import com.ink.trade.core.po.Pay;
import com.ink.trade.core.po.TradeOrder;
import com.ink.trade.core.po.UnknownOrder;
import com.ink.trade.core.query.AuthQuery;
import com.ink.trade.service.PrepaidCallBackService;
import com.ink.trade.service.check.Order;
import com.ink.trade.service.check.WithDrawCheck;
import com.ink.user.api.constants.RespCodeConstant;
import com.ink.user.api.model.in.AccWithdrawFreezeInput;
import com.ink.user.api.model.out.AccWithdrawFreezeOutput;
import com.ink.user.api.service.IAccWithdrawFreezeService;

/**
 * 
 * <pre>
 * <b>类描述:</b>(提现接口实现)
 * <b>作者:</b>zx
 * <b>创建日期:</b>2016年4月13日 下午2:16:44
 * </pre>
 */
@Service("prepaidService")
public class PrepaidServiceImpl implements IPrepaidService {

	private static YinkerLogger logger = YinkerLogger.getLogger(PrepaidServiceImpl.class);
	@Autowired
	private ITradeOrderManager tradeOrderManager;
	@Autowired
	private IAsileRoute asileRoute;
	@Autowired
	private IPayManager payManager;
	@Autowired
	private IUserBalanceManager userBalanceManager;
	@Autowired
	private Pay2CardService pay2CardService;
	@Autowired
	private IAccWithdrawFreezeService accWithdrawFreezeService;// 提现冻结
	@Autowired
	private WithDrawCheck withDrawCheck;
	@Autowired
	private IAuthManager authManager;
	@Autowired
	private IAsileResCodeManager asileResCodeManager;
	@Autowired
	private IAsileBusinessManager asileBusinessManager;
	@Autowired
	private IdCodeGenerator idCodeGenerator;
	// 对账业务prepare队列
	private static final String BALANCE_PREPARED_MQ_NAME = "yinker.balance.platform.notifyData";
	// 对账业务update队列
	// 账户队列
	@Autowired
	private IUnknownOrderManager unknownOrderManager;
	@Autowired
	private PrepaidCallBackService prepaidCallBackService;
	@Autowired
	private IdCodeGenerator certificateUtil;

	@Override
	public PrepaidOutput withdraw(PrepaidInput withdrawInput) {
		logger.info("提现开始订单" + withdrawInput.getOrderId());
		long start = System.currentTimeMillis();
		try {
			PrepaidOutput withdrawOutput = new PrepaidOutput();
			withdrawOutput.setAccountType(withdrawInput.getAccountType());
			withdrawOutput.setAmt(withdrawInput.getAmt());
			withdrawOutput.setCurrency(TradeCurrency.CNY);
			withdrawOutput.setMerchantId(withdrawInput.getMerchantId());
			withdrawOutput.setPayType(withdrawInput.getPayType());
			withdrawOutput.setUserId(withdrawInput.getUserId());
			// 用户、商户检查
			// 订单唯一性检查
			checkInput(withdrawInput);
			// 检查绑卡
			Auth auth = authCheck(withdrawInput);
			// 订单创建
			TradeOrder order = createOrder(withdrawInput, auth);
			// 冻结,冻结失败抛异常交易失败
			long startTime = System.currentTimeMillis();
			try {
				callAccForWithdrawFrezzeAmt(order);
			} catch (TradeException ex) {
				logger.error(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY, TradeLogConstant.LOGGER_BIZ_PAY, "提现业务异常" + withdrawInput.toString(), ex, null);
				return createErrorWithdrawOutput(TradeStatus.FAIL.getValue(), ex.getCode(), ex.getMessage());
			}
			logger.debug("调用账户冻结结束，耗时" + (System.currentTimeMillis() - startTime));
			// 路由并更新订单状态
			tradeHandle(order);
			// 创建支付订单
			Pay pay = insertPayOrder(order);
			// 更新订单状态为受理中
			order.setStatus(OrderStatus.PROCESSING.getValue());
			prepaidCallBackService.updateTradeOrder(order);
			// 清结算初始状态数据推送，异步推送，失败记录log
			prepaidCallBackService.pushToBalance(BALANCE_PREPARED_MQ_NAME, buildBalanceNotifyDataMap(pay, PayStatusConst.UN_PAY));
			// 调渠道去做代付
			Pay2CardOutput pay2CardOutput = callChannel(order, pay, auth);
			// 更新渠道响应码到支付订单表
			pay.setAsileRepCode(pay2CardOutput.getResCode());
			pay.setAsileRepMsg(pay2CardOutput.getResMsg());
			pay.setPayReqId(pay2CardOutput.getOrgTranFlow());
            //接口同步还是异步为准
			if (asileBusinessManager.IsSynchronized(order.getChannelNo(), PayType.PAY.getValue())) {
				//成功
				if (com.ink.channel.core.enums.OrderStatus.SUCCESS_CODE.getCode().equals(pay2CardOutput.getOrderStatus())) {
					pay.setRepCode(TradeRespConstant.TRADE_SUCCESS);
					pay.setRepCode(TradeRespConstant.TRADE_SUCCESS_MSG);
					long starttime=System.currentTimeMillis();
					payManager.updatePayAndCreateLog(pay);
					long endtime=System.currentTimeMillis();
					logger.debug("代收更新支付订单，耗时"+(starttime-endtime));
					withdrawOutput.setReponseCode(TradeRespConstant.TRADE_SUCCESS);
					withdrawOutput.setReponseMsg(TradeRespConstant.TRADE_SUCCESS_MSG);
					withdrawOutput.setTradeStatus(TradeStatus.SUCCESS.getValue());
					withdrawOutput.setRequestId(order.getReqId());
					prepaidCallBackService.callBack(pay.getPayOrderId(), true, pay.getPayReqId());
				}
				//处理中
				if (com.ink.channel.core.enums.OrderStatus.PROCESSES_CODE.getCode().equals(pay2CardOutput.getOrderStatus())) {
					pay.setRepCode(TradeRespConstant.TRADE_PROCESSING);
					pay.setRepCode(TradeRespConstant.TRADE_PROCESSING_MSG);
					long starttime=System.currentTimeMillis();
					payManager.updatePayAndCreateLog(pay);
					long endtime=System.currentTimeMillis();
					logger.debug("代收更新支付订单，耗时"+(starttime-endtime));
					withdrawOutput.setReponseCode(TradeRespConstant.TRADE_PROCESSING);
					withdrawOutput.setReponseMsg(TradeRespConstant.TRADE_PROCESSING_MSG);
					withdrawOutput.setTradeStatus(TradeStatus.PROCESSING.getValue());
					withdrawOutput.setRequestId(order.getReqId());
					saveUnknownOrder(pay, order);
				}//失败
				if (com.ink.channel.core.enums.OrderStatus.FAILE_CODE.getCode().equals(pay2CardOutput.getOrderStatus())) {
					AsileResCode asileResCode = asileResCodeManager.findByAsileCodeAndAsileResCode(pay.getChanelNo(), pay2CardOutput.getResCode());
					// 响应码未映射
					if (asileResCode == null) {
						logger.error(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY, TradeLogConstant.LOGGER_BIZ_PAY, "渠道码解析异常,响应码：" + pay2CardOutput.getResCode() + "响应信息" + pay2CardOutput.getResMsg(), "");
						asileResCode=new AsileResCode();
						asileResCode.setResCode(TradeRespConstant.TRADE_FAIL_UNKNOWN);
						asileResCode.setResMsg(TradeRespConstant.TRADE_FAIL_UNKNOWN_MSG);
					}
					pay.setRepCode(asileResCode.getResCode());
					pay.setRepCode(asileResCode.getResMsg());
					long starttime=System.currentTimeMillis();
					payManager.updatePayAndCreateLog(pay);
					long endtime=System.currentTimeMillis();
					logger.debug("代收更新支付订单，耗时"+(starttime-endtime));
					withdrawOutput.setReponseCode(asileResCode.getResCode());
					withdrawOutput.setReponseMsg(asileResCode.getResMsg());
					withdrawOutput.setTradeStatus(TradeStatus.FAIL.getValue());
					withdrawOutput.setRequestId(order.getReqId());
					prepaidCallBackService.callBack(pay.getPayOrderId(), false, pay.getPayReqId());
				}
			} else {
				// 判断是否是连接超时
				if (ChannelConstants.REQUEST_EXCEPTION_CODE.equals(pay2CardOutput.getResCode())) {
					// 异步更新支付订单和交易订单为失败
					pay.setRepCode(TradeRespConstant.CHANNEL_REQUEST_TIMEOUT);// 平台响应码
					pay.setRepMsg(TradeRespConstant.CHANNEL_REQUEST_TIMEOUT_MSG);
					pay.setStatus(OrderStatus.FAIL.getValue());
					order.setStatus(OrderStatus.FAIL.getValue());
					payManager.updatePayAndCreateLog(pay);
					tradeOrderManager.updateOrderAndCreateLog(order);
					logger.error(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY, TradeLogConstant.LOGGER_BIZ_PAY, "代付调用渠道连接超时", "");
					throw new AsileRequestTimeOutException();
				}
				pay.setRepCode(TradeRespConstant.TRADE_PROCESSING);
				pay.setRepCode(TradeRespConstant.TRADE_PROCESSING_MSG);
				long starttime=System.currentTimeMillis();
				payManager.updatePayAndCreateLog(pay);
				long endtime=System.currentTimeMillis();
				logger.debug("代收更新支付订单，耗时"+(starttime-endtime));
				withdrawOutput.setReponseCode(TradeRespConstant.TRADE_PROCESSING);
				withdrawOutput.setReponseMsg(TradeRespConstant.TRADE_PROCESSING_MSG);
				withdrawOutput.setTradeStatus(TradeStatus.PROCESSING.getValue());
				withdrawOutput.setRequestId(order.getReqId());
				saveUnknownOrder(pay,order);

			}

			logger.debug("交易提现调用结束，耗时" + (System.currentTimeMillis() - start));
			logger.info(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY, TradeLogConstant.LOGGER_BIZ_PAY, "提现结束");
			return withdrawOutput;
		} catch (TradeException e) {
			logger.error(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY, TradeLogConstant.LOGGER_BIZ_PAY, "提现业务异常" + withdrawInput.toString(), e, null);
			return createErrorWithdrawOutput(TradeStatus.FAIL.getValue(), e.getCode(), e.getMessage());
		} catch (Exception e) {
			logger.error(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY, TradeLogConstant.LOGGER_BIZ_PAY, "系统异常" + withdrawInput.toString(), e, null);
			return createErrorWithdrawOutput(TradeStatus.FAIL.getValue(), TradeRespConstant.TRADE_SYSERROR, TradeRespConstant.TRADE_SYSERROR_MSG);
		}
	}

	private Auth authCheck(PrepaidInput withdrawInput) {
		// 绑卡检查
		AuthQuery authRe = new AuthQuery();
		authRe.setMchId(withdrawInput.getMerchantId());
		authRe.setUserId(withdrawInput.getUserId());
		authRe.setCardNo(withdrawInput.getCardNo());
		authRe.setPayType(PayType.ALL.getValue());
		authRe.setMasterMark(true);// 查主库
		List<Auth> authList = authManager.find(authRe);
		if (CollectionUtils.isEmpty(authList)) {
			logger.error(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY, TradeLogConstant.LOGGER_BIZ_PAY, "没有查询到绑卡信息" + withdrawInput.toString(), "");
			throw new TradeException("没有查询到绑卡信息！");
		}
		return authList.get(0);
	}

	/**
	 * 订单创建
	 * 
	 * @param withdrawInput
	 * @return
	 */
	private TradeOrder createOrder(PrepaidInput withdrawInput, Auth auth) throws TradeException {
		// 订单初始化
		TradeOrder order = initTradeOrder(withdrawInput, auth);
		// 订单入库
		logger.info(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY, TradeLogConstant.LOGGER_BIZ_PAY, "保存代付订单:" + order.toString());
		try {
			tradeOrderManager.saveOrderAndCreateLog(order);
		} catch (Exception e) {
			logger.error(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY, TradeLogConstant.LOGGER_BIZ_PAY, "交易提现,数据库异常" + order.toString(), e, null);
			throw new TradeException(TradeRespConstant.TRADE_SYSERROR, TradeRespConstant.TRADE_SYSERROR_MSG);
		}
		return order;
	}

	/**
	 * 初始化订单
	 * 
	 * @param withdrawInput
	 * @return
	 */
	private TradeOrder initTradeOrder(PrepaidInput withdrawInput, Auth auth) {
		TradeOrder order = new TradeOrder();
		if (withdrawInput.getBankShort() != null) {
			if (!withdrawInput.getBankShort().equals(auth.getBankShort())) {
				logger.info(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY, TradeLogConstant.LOGGER_BIZ_PAY, "提现银行简码输入参数校验失败!Auth:" + auth + "输入参数：" + withdrawInput.toString());
				throw new TradeException("提现银行简码参数与银行卡不符!");
			}
		}
		order.setBankShort(auth.getBankShort());
		order.setAccountType(withdrawInput.getAccountType());// 账户类型
		order.setReqId(idCodeGenerator.getId());// 流水号
		order.setMchId(withdrawInput.getMerchantId());// 商户号
		order.setTxnName(TradeType.WITHDRAW.getName());
		order.setTxnCode(TradeType.WITHDRAW.getCode());
		order.setOrderTime(new Date());
		order.setPhoneNo(auth.getPhoneNo());// 手机号
		order.setUserName(auth.getUserName());// 用户名
		order.setAmt(withdrawInput.getAmt());// 提现金额
		// 创建订单。。订单是待支付状态
		order.setStatus(OrderStatus.CREATEORDER.getValue());
		order.setVersion(1);
		order.setCreateTime(new Date());
		order.setCid(auth.getId());// 绑卡信息表主键
		order.setCardNo(withdrawInput.getCardNo());// 卡号
		order.setUserId(withdrawInput.getUserId());// 用户号
		order.setOrderId(withdrawInput.getOrderId());// 订单号
		order.setTradeDate(new Date());// 交易日期
		order.setLastupdateTime(order.getTradeDate());// 更新日期
		order.setPayType(withdrawInput.getPayType());// 支付类型
		order.setNoticeUrl(withdrawInput.getNoticeUrl());
		logger.info(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY, TradeLogConstant.LOGGER_BIZ_PAY, "交易提现,订单初始化完成,Order" + order.toString());
		return order;
	}

	/**
	 * 检查输入信息
	 * 
	 * @param withdrawInput
	 */
	private void checkInput(PrepaidInput withdrawInput) throws TradeException {

		Order order = new Order();
		order.setOrderId(withdrawInput.getOrderId());// 订单号
		order.setUserId(withdrawInput.getUserId());// 用户号
		order.setMerchantId(withdrawInput.getMerchantId());// 商户号
		order.setTradeCode(withdrawInput.getTradeCode());// 交易码
		order.setPayType(withdrawInput.getPayType());// 支付类型
		order.setCardNo(withdrawInput.getCardNo());// 卡号
		/** 卡号 **/
		order.setAmt(withdrawInput.getAmt());
		/** 交易金额 **/
		order.setIdNo(withdrawInput.getCardNo());
		/** 证件号码 **/
		order.setBankMblNo(withdrawInput.getPhoneNo());
		/** 银行预留手机号 **/
		// 用户状态,商户状态,
		withDrawCheck.operateCheck(order);
		logger.info(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY, TradeLogConstant.LOGGER_BIZ_PAY, "交易提现,输入信息检查完成.");
	}

	/**
	 * 交易处理
	 * 
	 * @param withdrawInput
	 * @throws Exception
	 */
	private AsileRouteOutput tradeHandle(TradeOrder order) {
		long startTime = System.currentTimeMillis();
		// 得到渠道号
		AsileRouteOutput asileRouteOutput = null;
		try {
			asileRouteOutput = getTradeAsile(order);
			logger.debug("路由结束,耗时：" + (System.currentTimeMillis() - startTime));
		} catch (Exception ex) {
			logger.error(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY, TradeLogConstant.LOGGER_BIZ_ROUTEQUERY, "路由异常，订单" + order.toString(), ex, "");
			order.setStatus(OrderStatus.FAIL.getValue());
			prepaidCallBackService.updateTradeOrder(order);
			prepaidCallBackService.freezeThaw(order);
			throw new TradeException(TradeRespConstant.NO_USEFUL_CHANNEL, TradeRespConstant.NO_USEFUL_CHANNEL_MSG);
		}
		if (RespCodeConstant.RespCode_000000.equals(asileRouteOutput.getReponseCode())) {
			// 交易订单改为支付中,更新渠道号
			order.setChannelNo(asileRouteOutput.getAsileCode());
			order.setStatus(OrderStatus.PENDING.getValue());
			order.setRouteBusinessType(RouteBusinessType.PAYMENT.getCode());
			prepaidCallBackService.updateTradeOrder(order);
		} else {
			// 无渠道 更新订单解冻
			logger.error(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY, TradeLogConstant.LOGGER_BIZ_ROUTEQUERY, "路由失败，无合适渠道" + order.toString(), "");
			order.setStatus(OrderStatus.FAIL.getValue());
			prepaidCallBackService.updateTradeOrder(order);
			prepaidCallBackService.freezeThaw(order);
			throw new TradeException(TradeRespConstant.NO_USEFUL_CHANNEL, TradeRespConstant.NO_USEFUL_CHANNEL_MSG);
		}
		return asileRouteOutput;
	}

	/**
	 * @Description: 得到渠道号
	 * @return AsileRouteInput
	 * @throws
	 */
	private AsileRouteOutput getTradeAsile(TradeOrder order) throws Exception {
		// 调用路由规则，拿到一个渠道
		AsileRouteInput routeInput = new AsileRouteInput();
		AsileRouteOutput asileRouteOutput = new AsileRouteOutput();
		try {
			routeInput.setRouteBusinessType(RouteBusinessType.PAYMENT);
			routeInput.setBankShort(order.getBankShort());
			routeInput.setMchId(order.getMchId());
			routeInput.setAmt(order.getAmt());
			routeInput.setTradeDate(order.getTradeDate());
			logger.info(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY, TradeLogConstant.LOGGER_BIZ_PAY, "交易提现,调用路由规则" + routeInput.toString());
			asileRouteOutput = asileRoute.getTradeAsile(routeInput);
			if (null != asileRouteOutput && null != asileRouteOutput.getAsileCode()) {
				logger.info(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY, TradeLogConstant.LOGGER_BIZ_PAY, "交易提现,得到渠道编码" + asileRouteOutput.getAsileCode());
			}
		} catch (Exception e) {
			logger.error(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY, TradeLogConstant.LOGGER_BIZ_PAY, "获取渠道异常!" + order.toString(), e, "");
			throw new TradeException(TradeRespConstant.CHANNEL_SYSERROR, TradeRespConstant.CHANNEL_SYSERROR_MSG);

		}
		return asileRouteOutput;
	}

	/**
	 * notifyData
	 */
	private Map<String, String> buildBalanceNotifyDataMap(Pay pay, String payStatus) {
		HashMap<String, String> params = new HashMap<String, String>();
		// 交易状态 1、待支付 2、支付成功 3、支付失败
		params.put("amt", String.valueOf(pay.getAmt()));
		params.put("platformOrderNo", pay.getPayOrderId());
		params.put("payTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(pay.getCreateTime()));
		params.put("payStatus", payStatus);
		params.put("pay_method", PayMethodConst.PAY);// '支付方式（1：代收、2：代付、3：网银、4：快捷）'
		return params;
	}

	/**
	 * @date 2016年4月27日 上午11:29:18
	 * @Description: 账户冻结
	 * @author wanghao
	 * @param withdrawInput
	 * @param order
	 * @return Map<String, String> 冻结信息
	 * @throws Exception
	 */
	private void callAccForWithdrawFrezzeAmt(TradeOrder order) throws Exception {
		logger.info(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY, TradeLogConstant.LOGGER_BIZ_PAY, "交易提现,资金冻结开始!" + order);
		AccWithdrawFreezeOutput retOutput;
		AccWithdrawFreezeInput dto = new AccWithdrawFreezeInput();
		try {
			dto.setTxnCode("AWF");
			dto.setMchId(order.getMchId());
			dto.setChannelId(order.getChannelNo());
			dto.setCustId(order.getUserId());
			dto.setCustFee("0.00");
			dto.setCardNo(order.getCardNo());
			dto.setOrdId(order.getReqId());// 交易流水号作为账户的订单号，保证唯一
			dto.setTradeDate(DateUtil.formatToYYYYMMDDMMHHSSStr(order.getTradeDate()));
			dto.setAmt(order.getAmt().toString());
			dto.setAccountType(order.getAccountType());
			dto.setMac(DigestUtils.md5Hex(TradeUserConstant.TRADE_USER_KEY + dto.getOrdId()));
			retOutput = accWithdrawFreezeService.exec(dto);
			if (RespCodeConstant.RespCode_000000.equals(retOutput.getRetCode())) {
				logger.info(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY, TradeLogConstant.LOGGER_BIZ_PAY, "交易提现,资金冻结成功!" + retOutput);
			} else {
				logger.error(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY, TradeLogConstant.LOGGER_BIZ_PAY, "交易提现,资冻结失败，订单：" + order + "账户响应：" + (Object) retOutput.getRetMsg(), "");
				order.setStatus(OrderStatus.FAIL.getValue());
				order.setLastupdateTime(new Date());
				tradeOrderManager.updateOrderAndCreateLog(order);
				throw new TradeException(TradeRespConstant.ACCOUNT_FROZEN_FILE, retOutput.getRetMsg());
			}
		} catch (Exception e) {
			logger.error(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY, TradeLogConstant.LOGGER_BIZ_PAY, "交易提现,调用账户系统冻结资金异常" + order, e, null);
			// 冻结失败订单更新为失败状态
			order.setStatus(OrderStatus.FAIL.getValue());
			order.setLastupdateTime(new Date());
			tradeOrderManager.updateOrderAndCreateLog(order);
			throw new Exception("调用账户提现冻结业务异常");
		}
	}

	/**
	 * @Description: 代付
	 * @param withdrawInput
	 * @param order
	 * @return Pay2CardOutput
	 */
	private Pay2CardOutput callChannel(TradeOrder order, Pay pay, Auth auth) {
		Pay2CardOutput pay2CardOutput = new Pay2CardOutput();
		Pay2CardInput input = null;
		try {

			input = new Pay2CardInput();
			input.setCardNo(auth.getCardNo());
			input.setAmount(pay.getAmt());
			input.setChannelId(order.getChannelNo());
			input.setOrderNo(pay.getPayOrderId());
			input.setIdNo(auth.getIdNo());
			input.setIdType(auth.getIdType());
			input.setUserName(auth.getUserName());
			input.setBankShort(order.getBankShort());
			input.setMerchantNo(pay.getMchId());
			long startTime = System.currentTimeMillis();
			logger.info(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY, TradeLogConstant.LOGGER_BIZ_PAY, "交易提现,渠道代付开始" + input.toString());
			pay2CardOutput = pay2CardService.pay(input);
			logger.info(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY, TradeLogConstant.LOGGER_BIZ_PAY, "交易提现,渠道代付成功!" + pay2CardOutput);
			logger.debug("调用渠道提现结束，耗时:" + (System.currentTimeMillis() - startTime));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY, TradeLogConstant.LOGGER_BIZ_PAY, "交易提现,Pay2CardService代付异常!" + input.toString(), e, null);
			pay2CardOutput.setResCode(TradeRespConstant.PAY2_SYSERROR);
			pay2CardOutput.setResMsg(TradeRespConstant.PAY2_SYSERROR_MSG);
		}
		return pay2CardOutput;
	}

	/**
	 * @Description: 创建支付
	 * @param order
	 * @return
	 * @throws TradeException
	 */
	private Pay insertPayOrder(TradeOrder order) throws TradeException {
		Pay pay = initPayOrder(order);
		try {
			payManager.savePayAndCreateLog(pay);
		} catch (Exception ex) {
			logger.error(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY, TradeLogConstant.LOGGER_BIZ_PAY, "交易提现,创建支付失败!" + order.toString(), null);
			throw new TradeException(TradeRespConstant.TRADE_SYSERROR, TradeRespConstant.TRADE_SYSERROR_MSG);
		}
		return pay;
	}

	/**
	 * @Description:初始化支付
	 * @param order
	 * @return
	 */
	private Pay initPayOrder(TradeOrder order) {
		Pay pay = new Pay();
		Date date = new Date();
		pay.setReqId(order.getReqId());
		pay.setOrdId(order.getOrderId());
		pay.setChanelNo(order.getChannelNo());
		pay.setAmt(order.getAmt());
		pay.setCreateTime(date);
		pay.setVersion(1);
		pay.setRemark(order.getRemark());
		pay.setReqTime(date);
		pay.setMchId(order.getMchId());
		pay.setOrderDate(order.getOrderTime());
		pay.setPayType(order.getPayType());
		// 支付订单号
		String ASILE_NO = certificateUtil.getIpMapsConfig().get(CmbcConstants.ASILE_CMBC);
		if (ASILE_NO.equals(order.getChannelNo())) {
			String merchantNo = certificateUtil.getIpMapsConfig().get(order.getMchId() + ASILE_NO + CmbcConstants.merchantNoRechargeAndCash);
			pay.setPayOrderId(merchantNo + order.getReqId());
		} else {
			pay.setPayOrderId(order.getReqId());
		}
		pay.setStatus(OrderStatus.PROCESSING.getValue());
		pay.setLastupdateTime(date);
		return pay;
	}

	/**
	 * @Description: 提现返回结果设置
	 * @param withdrawInput
	 * @param order
	 * @return
	 */
	private PrepaidOutput withdrawResultHandle(PrepaidInput withdrawInput, TradeOrder order, Pay pay, PrepaidOutput withdrawOutput) {
		withdrawOutput.setMerchantId(String.valueOf(order.getMchId()));
		withdrawOutput.setTradeStatus(TradeStatus.SUCCESS.getValue());
		withdrawOutput.setTradeCode(withdrawInput.getTradeCode());
		withdrawOutput.setPayType(withdrawInput.getPayType());
		withdrawOutput.setOrderId(String.valueOf(order.getId()));
		withdrawOutput.setAmt(order.getAmt());
		withdrawOutput.setRequestId(String.valueOf(order.getReqId()));
		withdrawOutput.setCurrency(withdrawInput.getCurrency());
		withdrawOutput.setUserId(order.getUserId());
		withdrawOutput.setAccountType(withdrawInput.getAccountType());
		return withdrawOutput;
	}

	/**
	 * 创建异常交易结果
	 * 
	 * @param code
	 * @param message
	 * @return
	 */
	private PrepaidOutput createErrorWithdrawOutput(String status, String code, String message) {
		PrepaidOutput output = new PrepaidOutput();
		output.setTradeStatus(status);
		output.setReponseCode(code);
		output.setReponseMsg(message);
		return output;
	}

	private void saveUnknownOrder(Pay pay, TradeOrder order) {
		// 响应超时放入未知状态表中，等待交易查证
		UnknownOrder unknownOrder = new UnknownOrder();
		unknownOrder.setPayId(pay.getId());
		unknownOrder.setExecuteRemark("NONE");
		unknownOrder.setFinalStatus("N");
		unknownOrder.setPayOrderNo(pay.getPayOrderId());
		unknownOrder.setOrderStatus(pay.getStatus());
		unknownOrder.setChannelNo(pay.getChanelNo());
		unknownOrder.setTradeOrderId(order.getId());
		unknownOrder.setExecuteCount(0);
		unknownOrder.setTransType(order.getPayType());
		unknownOrder.setOrderTime(pay.getOrderDate());
		unknownOrder.setLastUpdateTime(new Date());
		unknownOrder.setMchId(order.getMchId());
		try {
			unknownOrderManager.save(unknownOrder);
		} catch (Exception e) {
			logger.error(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY, TradeLogConstant.LOGGER_BIZ_PAY, "保存未知状态订单异常" + unknownOrder, e, pay.getOrdId());
		}
	}
}

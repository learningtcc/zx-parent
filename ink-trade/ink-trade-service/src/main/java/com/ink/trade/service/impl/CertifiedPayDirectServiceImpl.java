package com.ink.trade.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.ink.asile.core.manager.IAsileBusinessManager;
import com.ink.asile.core.manager.IAsileResCodeManager;
import com.ink.asile.core.po.AsileResCode;
import com.ink.base.log.util.YinkerLogger;
import com.ink.base.utils.IdCodeGenerator;
import com.ink.channel.api.model.in.AuthenPayInput;
import com.ink.channel.api.model.out.AuthenPayOutput;
import com.ink.channel.api.service.AuthenPayService;
import com.ink.channel.core.cnst.ChannelConstants;
import com.ink.channel.core.minsheng.CmbcConstants;
import com.ink.trade.api.enums.OrderStatus;
import com.ink.trade.api.enums.PayType;
import com.ink.trade.api.enums.RouteBusinessType;
import com.ink.trade.api.enums.TradeType;
import com.ink.trade.api.model.in.AsileRouteInput;
import com.ink.trade.api.model.in.PayDirectInput;
import com.ink.trade.api.model.out.AsileRouteOutput;
import com.ink.trade.api.model.out.PayDirectOutput;
import com.ink.trade.api.rule.IAsileRoute;
import com.ink.trade.api.service.ICertifiedPayDirectService;
import com.ink.trade.core.cnst.TradeLogConstant;
import com.ink.trade.core.cnst.TradeRespConstant;
import com.ink.trade.core.enums.TradeStatus;
import com.ink.trade.core.exception.TradeException;
import com.ink.trade.core.exception.orderfail.AsileRequestTimeOutException;
import com.ink.trade.core.exception.orderfail.OrderUniqueException;
import com.ink.trade.core.exception.orderfail.RouteFailException;
import com.ink.trade.core.exception.orderfail.TradeCodeErrorException;
import com.ink.trade.core.manager.IAuthManager;
import com.ink.trade.core.manager.IPayManager;
import com.ink.trade.core.manager.ITradeOrderManager;
import com.ink.trade.core.manager.IUnknownOrderManager;
import com.ink.trade.core.po.Auth;
import com.ink.trade.core.po.Pay;
import com.ink.trade.core.po.TradeOrder;
import com.ink.trade.core.po.UnknownOrder;
import com.ink.trade.core.query.AuthQuery;
import com.ink.trade.service.ISignInfoService;
import com.ink.trade.service.callbacks.PreCollectionCallBackServiceImpl;
import com.ink.trade.service.check.Order;
import com.ink.trade.service.check.RechargeCheck;
import com.ink.trade.service.dto.PreCollectionCallBackDto;
import com.ink.trade.service.dto.QuerySignInfoDto;
import com.ink.trade.service.mq.constant.QueenNameConstant;

/**
 * 
 * <pre>
 * <b>类描述:</b>(认证支付-无需短信验证码)
 * <b>作者:</b>zx
 * <b>创建日期:</b>2016年6月20日 下午1:52:29
 * </pre>
 */
@Service("certifiedPayDirectService")
public class CertifiedPayDirectServiceImpl implements ICertifiedPayDirectService {
	private YinkerLogger logger = YinkerLogger.getLogger(CertifiedPayDirectServiceImpl.class);
	@Autowired
	private IAuthManager authManager; // 绑卡
	@Autowired
	private IAsileResCodeManager asileResCodeManager; // 渠道响应码解析
	@Autowired
	private ITradeOrderManager tradeOrderManager; // 交易订单
	@Autowired
	private IAsileRoute asileRoute;// 路由
	@Autowired
	private IPayManager payManager; // 支付订单
	@Autowired
	private AmqpTemplate amqpTemplate;
	@Autowired
	private IdCodeGenerator idCodeGenerator; // 发号器
	@Autowired
	private RechargeCheck rechargeCheck;// 充值业务检查器
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;// 线程池
	@Autowired
	private ISignInfoService signInfoService; // 签约
	@Autowired
	private IAsileBusinessManager asileBusinessManager;
	@Autowired
	private PreCollectionCallBackServiceImpl preCollectionCallBackService;
	@Autowired
	private AuthenPayService authenPayCardService;
	@Autowired
	private IdCodeGenerator certificateUtil;
	@Autowired
	private IUnknownOrderManager unknownOrderManager;

	@Override
	public PayDirectOutput pay(PayDirectInput input) {
		logger.info(TradeLogConstant.LOGGER_MODULE_AUTH_PAY, TradeLogConstant.LOGGER_BIZ_AUTH_PAY, "充值交易开始" + input.getOrderId());
		// 构造返回消息
		PayDirectOutput output = new PayDirectOutput();
		long rechargeStart = System.currentTimeMillis();
		try {
			// 1、业务校验
			// 先查询绑卡列表
			AuthQuery authParam = new AuthQuery();
			authParam.setMchId(input.getMerchantId());
			authParam.setUserId(input.getUserId());
			authParam.setCardNo(input.getCardNo());
			authParam.setPayType(PayType.ALL.getValue());
			authParam.setMasterMark(true);// 查主库
			long authStart = System.currentTimeMillis();
			List<Auth> authList = authManager.find(authParam);
			if (CollectionUtils.isEmpty(authList)) {
				logger.error("用户" + input.getUserId() + "未绑卡");
				throw new RuntimeException("user " + input.getUserId() + " not yet bound card");
			}
			long authEnd = System.currentTimeMillis();
			logger.debug("充值查询校验绑卡耗时:" + (authEnd - authStart));
			Auth auth = authList.get(0);
			long checkStart = System.currentTimeMillis();
			check(input, auth);
			long checkEnd = System.currentTimeMillis();
			logger.debug("充值校验耗时:" + (checkEnd - checkStart));

			// 2、创建交易订单并生成流水
			long tradeOrderStart = System.currentTimeMillis();
			TradeOrder tradeOrder = createTradeOrder(input, auth);
			long tradeOrderEnd = System.currentTimeMillis();
			logger.debug("充值创建交易订单耗时:" + (tradeOrderEnd - tradeOrderStart));
			logger.info(TradeLogConstant.LOGGER_MODULE_AUTH_PAY, TradeLogConstant.LOGGER_BIZ_AUTH_PAY, "充值交易订单" + input.getOrderId() + "创建完成");

			// 3、渠道路由
			long routeStart = System.currentTimeMillis();
			AsileRouteOutput asileRouteOutput = route(input, auth);
			long routeEnd = System.currentTimeMillis();
			logger.debug("路由耗时" + (routeEnd - routeStart) + "ms");

			if (asileRouteOutput == null || !TradeRespConstant.TRADE_SUCCESS.equals(asileRouteOutput.getReponseCode())) {
				logger.error(TradeLogConstant.LOGGER_MODULE_AUTH_PAY, TradeLogConstant.LOGGER_BIZ_AUTH_PAY, "未筛选到合适的支付渠道，充值交易订单号：" + input.getOrderId(), input.getOrderId());
				// 更新交易订单状态为失败
				tradeOrder.setStatus(OrderStatus.FAIL.getValue());
				updateTradeOrderAndCreateTradeLog(tradeOrder);
				throw new RouteFailException(TradeRespConstant.NO_USEFUL_CHANNEL, TradeRespConstant.NO_USEFUL_CHANNEL_MSG);
			}
			logger.info(TradeLogConstant.LOGGER_MODULE_AUTH_PAY, TradeLogConstant.LOGGER_BIZ_AUTH_PAY, "充值交易订单" + input.getOrderId() + "路由成功，渠道号" + asileRouteOutput.getAsileCode());

			tradeOrder.setChannelNo(asileRouteOutput.getAsileCode());// 落地渠道号
			tradeOrder.setRouteBusinessType(RouteBusinessType.CERTIFIEDPAY.getCode());
			// 更新落地渠道号到交易订单表
			long updateChannelNoStart = System.currentTimeMillis();
			updateTradeOrderAndCreateTradeLog(tradeOrder);
			long updateChannelNoEnd = System.currentTimeMillis();
			logger.debug("充值更新落地渠道号到交易订单耗时:" + (updateChannelNoEnd - updateChannelNoStart));
			// 5、创建支付订单并生成流水
			long payOrderStart = System.currentTimeMillis();
			Pay pay = createPayOrder(tradeOrder);
			long payOrderEnd = System.currentTimeMillis();
			logger.debug("充值创建支付订单耗时" + (payOrderEnd - payOrderStart));
			if (pay == null) {
				// 更新交易订单状态为失败
				tradeOrder.setStatus(OrderStatus.FAIL.getValue());
				updateTradeOrderAndCreateTradeLog(tradeOrder);
				throw new RuntimeException("create pay order failed");
			}
			logger.info(TradeLogConstant.LOGGER_MODULE_AUTH_PAY, TradeLogConstant.LOGGER_BIZ_AUTH_PAY, "充值交易订单" + input.getOrderId() + "创建支付订单成功");

			// 7、查询签约信息表，如果未签约，需要进行签约
			long identityStart = System.currentTimeMillis();
			String identityId = "";
			try {
				identityId = signInfoService.getSignIdByUserIdAndCardNo(generateQuerySignInfoDto(input, asileRouteOutput.getAsileCode(), auth));
			} catch (Exception e) {
				// 获取签约号失败，更新支付订单状态为失败
				pay.setStatus(OrderStatus.FAIL.getValue());
				updatePayOrderAndCreatePayLog(pay);
				// 获取签约号失败，更新交易订单状态为失败
				tradeOrder.setStatus(OrderStatus.FAIL.getValue());
				updateTradeOrderAndCreateTradeLog(tradeOrder);
				throw e;
			}
			long identityEnd = System.currentTimeMillis();
			logger.debug("充值查询签约信息耗时" + (identityEnd - identityStart));
			// 8、调用渠道支付前给对账系统发送prepare消息
			long prepareBalanceStart = System.currentTimeMillis();
			prepareBalanceSys(pay);
			long prepareBalanceEnd = System.currentTimeMillis();
			logger.debug("充值调用对账prepare耗时" + new Object[] { prepareBalanceEnd - prepareBalanceStart });

			// 9、支付
			long payStart = System.currentTimeMillis();
			AuthenPayOutput authenPayOutput = authenPay(pay, identityId);
			long payEnd = System.currentTimeMillis();

			logger.debug("交易订单" + input.getOrderId() + "调用渠道耗时" + (payEnd - payStart) + "ms");
			if (authenPayOutput == null) {
				// 调用渠道失败，更新支付订单状态为失败
				pay.setStatus(OrderStatus.FAIL.getValue());
				updatePayOrderAndCreatePayLog(pay);
				// 调用渠道失败，更新交易订单状态为失败
				tradeOrder.setStatus(OrderStatus.FAIL.getValue());
				updateTradeOrderAndCreateTradeLog(tradeOrder);
				logger.error(TradeLogConstant.LOGGER_MODULE_AUTH_PAY, TradeLogConstant.LOGGER_BIZ_AUTH_PAY, "认证调用渠道失败,渠道编号" + asileRouteOutput.getAsileCode() + "请求参数：" + input, "");
				throw new RuntimeException("call channel to pay failed");
			}
			logger.info(TradeLogConstant.LOGGER_MODULE_AUTH_PAY, TradeLogConstant.LOGGER_BIZ_AUTH_PAY, "交易订单" + input.getOrderId() + "调用调用渠道返回内容：" + authenPayOutput.toString());
			// 更新渠道响应码到支付订单表
			pay.setAsileRepCode(authenPayOutput.getResCode());// 渠道响应码
			pay.setAsileRepMsg(authenPayOutput.getResMsg());
			pay.setPayReqId(authenPayOutput.getOrgTranFlow());// 渠道交易流水号

			// 同步
			if (!isAsync(pay.getChanelNo())) {
				logger.info(TradeLogConstant.LOGGER_MODULE_AUTH_PAY, TradeLogConstant.LOGGER_BIZ_AUTH_PAY, "充值交易订单" + tradeOrder.getOrderId() + "调用渠道" + pay.getChanelNo() + "为同步返回结果");

				PreCollectionCallBackDto dto = new PreCollectionCallBackDto();
				dto.setOrderNo(pay.getPayOrderId());
				dto.setTranFlowNo(authenPayOutput.getOrgTranFlow());
				// 判断支付返回状态码
				if (com.ink.channel.core.enums.OrderStatus.SUCCESS_CODE.getCode().equals(authenPayOutput.getOrderStatus())) {
					// 订单成功
					// 更新渠道响应码到支付订单表
					pay.setRepCode(TradeRespConstant.TRADE_SUCCESS);// 平台响应码成功
					pay.setRepMsg(TradeRespConstant.TRADE_SUCCESS_MSG);
					long updateResCodeStart = System.currentTimeMillis();
					updatePayOrderAndCreatePayLog(pay);
					long updateResCodeEnd = System.currentTimeMillis();
					logger.debug("充值更新渠道响应码到支付订单耗时" + (updateResCodeEnd - updateResCodeStart));
					output.setRequestId(tradeOrder.getReqId());
					output.setTradeStatus(TradeStatus.SUCCESS.getValue());
					output.setReponseCode(TradeRespConstant.TRADE_SUCCESS);
					output.setReponseMsg(TradeRespConstant.TRADE_SUCCESS_MSG);
					dto.setOrderStatus("01");//设置回调状态
				} else if (com.ink.channel.core.enums.OrderStatus.PROCESSES_CODE.getCode().equals(authenPayOutput.getOrderStatus())) {
					// 订单未知
					pay.setRepCode(TradeRespConstant.TRADE_PROCESSING);// 平台响应码处理中
					pay.setRepMsg(TradeRespConstant.TRADE_PROCESSING_MSG);
					long updateResCodeStart = System.currentTimeMillis();
					updatePayOrderAndCreatePayLog(pay);
					long updateResCodeEnd = System.currentTimeMillis();
					logger.debug("充值更新渠道响应码到支付订单耗时" + (updateResCodeEnd - updateResCodeStart));
					output.setRequestId(tradeOrder.getReqId());
					output.setTradeStatus(TradeStatus.PROCESSING.getValue());
					output.setReponseCode(TradeRespConstant.TRADE_PROCESSING);
					output.setReponseMsg(TradeRespConstant.TRADE_PROCESSING_MSG);
					dto.setOrderStatus("02");
				} else {
					AsileResCode asileResCode = asileResCodeManager.findByAsileCodeAndAsileResCode(asileRouteOutput.getAsileCode(), authenPayOutput.getResCode());
					if (asileResCode == null) {
						logger.error(TradeLogConstant.LOGGER_MODULE_AUTH_PAY, TradeLogConstant.LOGGER_BIZ_AUTH_PAY, "充值交易订单" + input.getOrderId() + "渠道返回码未找到与平台的关联关系，转换为默认的错误码", input.getOrderId());
						asileResCode = new AsileResCode();
						asileResCode.setResCode(TradeRespConstant.TRADE_FAIL_UNKNOWN);
						asileResCode.setResMsg(TradeRespConstant.TRADE_FAIL_UNKNOWN_MSG);
					}
					// 订单状态失败
					pay.setRepCode(asileResCode.getResCode());// 平台响应码失败
					pay.setRepMsg(asileResCode.getResMsg());
					long updateResCodeStart = System.currentTimeMillis();
					updatePayOrderAndCreatePayLog(pay);
					long updateResCodeEnd = System.currentTimeMillis();
					logger.debug("充值更新渠道响应码到支付订单耗时" + (updateResCodeEnd - updateResCodeStart));
					output.setReponseCode(asileResCode.getResCode());
					output.setReponseMsg(asileResCode.getResMsg());
					output.setTradeStatus(TradeStatus.FAIL.getValue());
					output.setRequestId(tradeOrder.getReqId());
					dto.setOrderStatus("03");
				}
				long syncCallStart = System.currentTimeMillis();
				// 调用回调
				asyncCallBack(dto);
				long syncCallEnd = System.currentTimeMillis();
				logger.debug("充值同步调用回调函数耗时:" + (syncCallEnd - syncCallStart));
			}// 异步
			else {
				logger.info(TradeLogConstant.LOGGER_MODULE_AUTH_PAY, TradeLogConstant.LOGGER_BIZ_AUTH_PAY, "充值交易订单" + tradeOrder.getOrderId() + "调用渠道以异步返回结果为准");
				// 判断是否是连接超时
				if (ChannelConstants.REQUEST_EXCEPTION_CODE.equals(authenPayOutput.getResCode())) {
					// 异步更新支付订单和交易订单为失败
					pay.setRepCode(TradeRespConstant.CHANNEL_REQUEST_TIMEOUT);// 平台响应码请求超时
					pay.setRepMsg(TradeRespConstant.CHANNEL_REQUEST_TIMEOUT_MSG);
					pay.setStatus(OrderStatus.FAIL.getValue());
					tradeOrder.setStatus(OrderStatus.FAIL.getValue());
					asyncUpdateOrderStatus(pay, tradeOrder);
					logger.error(TradeLogConstant.LOGGER_MODULE_AUTH_PAY, TradeLogConstant.LOGGER_BIZ_AUTH_PAY, "认证调用渠道连接超时", "");
					throw new AsileRequestTimeOutException();
				}
				pay.setRepCode(TradeRespConstant.TRADE_PROCESSING);// 平台响应码处理中
				pay.setRepMsg(TradeRespConstant.TRADE_PROCESSING_MSG);
				long updateResCodeStart = System.currentTimeMillis();
				updatePayOrderAndCreatePayLog(pay);
				long updateResCodeEnd = System.currentTimeMillis();
				logger.debug("充值更新渠道响应码到支付订单耗时" + (updateResCodeEnd - updateResCodeStart));
				output.setRequestId(tradeOrder.getReqId());
				output.setTradeStatus(TradeStatus.PROCESSING.getValue());
				output.setReponseCode(TradeRespConstant.TRADE_PROCESSING);
				output.setReponseMsg(TradeRespConstant.TRADE_PROCESSING_MSG);
				saveUnknownOrder(pay, tradeOrder);
			}

			logger.info(TradeLogConstant.LOGGER_MODULE_AUTH_PAY, TradeLogConstant.LOGGER_BIZ_AUTH_PAY, "充值交易订单" + input.getOrderId() + "完成");
		} catch (TradeException e) {
			logger.error(TradeLogConstant.LOGGER_MODULE_AUTH_PAY, TradeLogConstant.LOGGER_BIZ_AUTH_PAY, "充值失败", e, input.getOrderId());
			output.setTradeStatus(TradeStatus.FAIL.getValue());
			output.setReponseCode(e.getCode());
			output.setReponseMsg(e.getMessage());
		} catch (Exception e) {
			logger.error(TradeLogConstant.LOGGER_MODULE_AUTH_PAY, TradeLogConstant.LOGGER_BIZ_AUTH_PAY, "充值失败", e, input.getOrderId());
			output.setTradeStatus(TradeStatus.FAIL.getValue());
			output.setReponseCode(TradeRespConstant.TRADE_SYSERROR);
			output.setReponseMsg(TradeRespConstant.TRADE_SYSERROR_MSG);
		} finally {
			// 填充返回消息类
			output.setTradeCode(input.getTradeCode());
			output.setMerchantId(input.getMerchantId());
			output.setAmt(input.getAmt());
			output.setUserId(input.getUserId());
			output.setPayType(input.getPayType());
		}
		long rechargeEnd = System.currentTimeMillis();
		logger.debug("充值总共耗时" + (rechargeEnd - rechargeStart));
		return output;
	}

	/**
	 * 是否异步
	 * 
	 * @param channelNo
	 * @return
	 */
	private boolean isAsync(String channelNo) {
		long isAsyncStart = System.currentTimeMillis();
		try {
			return !asileBusinessManager.IsSynchronized(channelNo, PayType.AUTHPAY.getValue());
		} catch (Exception e) {
			logger.error("认证业务获取渠道同异步方式出错", e);
		}
		long isAsyncEnd = System.currentTimeMillis();
		logger.debug("充值查询是否同异步耗时" + (isAsyncEnd - isAsyncStart));
		return false;
	}

	/**
	 * 异步更新订单状态
	 * 
	 * @param pay
	 * @param tradeOrder
	 */
	private void asyncUpdateOrderStatus(final Pay pay, final TradeOrder tradeOrder) {
		taskExecutor.execute(new Runnable() {
			@Override
			public void run() {
				updatePayOrderAndCreatePayLog(pay);
				updateTradeOrderAndCreateTradeLog(tradeOrder);
			}
		});
	}

	/**
	 * 异步回调
	 * 
	 * @param dto
	 */
	private void asyncCallBack(final PreCollectionCallBackDto dto) {
		taskExecutor.execute(new Runnable() {
			@Override
			public void run() {
				preCollectionCallBackService.callBack(dto);
			}
		});
	}

	/**
	 * 业务校验
	 *
	 * @param rechargeInput
	 * @param auth
	 */
	private void check(PayDirectInput rechargeInput, Auth auth) {
		// 校验交易类型，如果交易类型不匹配的话，直接返回错误信息
		if (!TradeType.RECHARGE.getCode().equals(rechargeInput.getTradeCode())) {
			logger.error(TradeLogConstant.LOGGER_MODULE_AUTH_PAY, TradeLogConstant.LOGGER_BIZ_AUTH_PAY, "用户" + rechargeInput.getUserId() + "请求交易码非充值交易，交易码" + rechargeInput.getTradeCode(), rechargeInput.getOrderId());
			throw new TradeCodeErrorException();
		}
		// 校验订单唯一性
		TradeOrder param = new TradeOrder();
		param.setMchId(rechargeInput.getMerchantId());
		param.setOrderId(rechargeInput.getOrderId());
		TradeOrder order = tradeOrderManager.getByMerNoAndMerOrderNo(param);
		if (order != null) {
			logger.error(TradeLogConstant.LOGGER_MODULE_AUTH_PAY, TradeLogConstant.LOGGER_BIZ_AUTH_PAY, "用户" + rechargeInput.getUserId() + "请求订单" + rechargeInput.getOrderId() + "重复", rechargeInput.getOrderId());
			throw new OrderUniqueException();
		}
		Order orderCheck = new Order();
		orderCheck.setOrderId(rechargeInput.getOrderId());// 订单号
		orderCheck.setUserId(rechargeInput.getUserId());// 用户号
		orderCheck.setMerchantId(rechargeInput.getMerchantId());// 商户号
		orderCheck.setTradeCode(rechargeInput.getTradeCode());// 交易码
		orderCheck.setPayType(rechargeInput.getPayType());// 支付类型（代扣）
		orderCheck.setCardNo(auth.getCardNo());// 卡号
		orderCheck.setAmt(rechargeInput.getAmt());// 交易金额
		orderCheck.setCustName(auth.getUserName());// 姓名
		orderCheck.setIdType(auth.getIdType());// 证件类型
		orderCheck.setIdNo(auth.getIdNo());// 证件号
		orderCheck.setCardType(auth.getCardType());// 卡类型
		orderCheck.setBankMblNo(auth.getPhoneNo());// 手机号
		long starttime = System.currentTimeMillis();
		rechargeCheck.operateCheck(orderCheck);
		logger.debug("充值检查调用账户，耗时" + (System.currentTimeMillis() - starttime));

	}

	/**
	 * 创建充值交易订单并生成流水
	 *
	 * @param input
	 * @param auth
	 */
	private TradeOrder createTradeOrder(PayDirectInput input, Auth auth) {
		TradeOrder order = new TradeOrder();
		order.setReqId(idCodeGenerator.getId());// 请求流水号，根据某种策略生成
		order.setMchId(input.getMerchantId());// 商户号
		order.setTxnName(TradeType.getNameByCode(input.getTradeCode()));// 交易名称
		order.setTxnCode(input.getTradeCode());// 交易码
		Date sysdate = new Date();
		order.setOrderTime(sysdate);// 订单日期
		order.setPhoneNo(auth.getPhoneNo());// 手机号
		order.setUserName(auth.getUserName());// 姓名
		order.setAmt(input.getAmt());// 金额
		order.setVersion(1);// 乐观锁
		order.setCreateTime(sysdate);// 创建时间
		order.setLastupdateTime(sysdate);// 最后修改时间
		order.setCardNo(auth.getCardNo());// 卡号
		order.setUserId(auth.getUserId());// 用户号
		order.setTradeDate(new Date());// 交易时间（商户订单时间）
		order.setOrderId(input.getOrderId());// 商户订单号
		order.setStatus(OrderStatus.CREATEORDER.getValue());// 订单状态：创建订单
		order.setCid(auth.getId());// 绑卡关系主键
		order.setAccountType(input.getAccountType());// 账户类型
		order.setBankShort(auth.getBankShort());// 银行简码
		order.setPayType(input.getPayType());// 支付方式
		order.setNoticeUrl(input.getNoticeUrl());// 回调地址
		// 此处不进行判断Manager返回的影响条数，判断放在Manager里进行，因为事务控制在Manager层
		tradeOrderManager.saveOrderAndCreateLog(order);
		return order;
	}

	/**
	 * 路由
	 *
	 * @param input
	 * @param auth
	 * @return
	 */
	private AsileRouteOutput route(PayDirectInput input, Auth auth) {
		AsileRouteInput condition = new AsileRouteInput();
		condition.setAmt(input.getAmt());
		condition.setBankShort(auth.getBankShort());
		condition.setCardId(auth.getCardNo());
		condition.setCardType(auth.getCardType());
		condition.setMchId(input.getMerchantId());
		condition.setTradeDate(new Date());
		condition.setRouteBusinessType(RouteBusinessType.CERTIFIEDPAY);
		logger.info(TradeLogConstant.LOGGER_MODULE_AUTH_PAY, TradeLogConstant.LOGGER_BIZ_AUTH_PAY, "充值交易订单" + input.getOrderId() + "开始调用路由系统进行路由，路由参数：" + condition.toString());
		AsileRouteOutput output = null;
		try {
			output = asileRoute.getTradeAsile(condition);
		} catch (Exception e) {
			logger.error(TradeLogConstant.LOGGER_MODULE_AUTH_PAY, TradeLogConstant.LOGGER_BIZ_AUTH_PAY, "充值交易订单" + input.getOrderId() + "路由失败", input.getOrderId());
		}

		logger.info(TradeLogConstant.LOGGER_MODULE_AUTH_PAY, TradeLogConstant.LOGGER_BIZ_AUTH_PAY, "充值交易订单" + input.getOrderId() + "调用路由系统结束，路由返回参数：" + (output == null ? "" : output.toString()));
		return output;
	}

	/**
	 * 创建支付订单
	 *
	 * @param order
	 * @return
	 */
	private Pay createPayOrder(TradeOrder order) {
		try {
			Pay pay = new Pay();
			pay.setReqId(order.getReqId());// 交易请求流水号
			pay.setOrdId(order.getOrderId());// 交易订单号
			pay.setChanelName(order.getChannelNo());// 渠道名称
			pay.setChanelNo(order.getChannelNo());// 渠道号
			pay.setAmt(order.getAmt());// 支付金额
			pay.setStatus(OrderStatus.CREATEORDER.getValue());// 支付订单初始状态
			Date sysdate = new Date();
			pay.setCreateTime(sysdate);// 创建时间
			pay.setVersion(1);// 乐观锁
			pay.setRemark(order.getRemark());// 备注
			pay.setReqTime(sysdate);// 请求时间
			pay.setLastupdateTime(sysdate);// 最后更新时间
			pay.setMchId(order.getMchId());// 商户号
			pay.setOrderDate(order.getOrderTime());// 订单日期
			pay.setPayType(order.getPayType());// 支付方式
			String payOrderId = order.getReqId();
			// 民生的订单号需要单独处理
			String ASILE_NO = certificateUtil.getIpMapsConfig().get(CmbcConstants.ASILE_CMBC);
			if (ASILE_NO.equals(order.getChannelNo())) {
				String merchantNo = certificateUtil.getIpMapsConfig().get(order.getMchId() + ASILE_NO + CmbcConstants.merchantNoRechargeAndCash);
				payOrderId = merchantNo + order.getReqId();
			}
			pay.setPayOrderId(payOrderId);// 支付订单号

			payManager.savePayAndCreateLog(pay);
			return pay;
		} catch (Exception e) {
			logger.error(TradeLogConstant.LOGGER_MODULE_AUTH_PAY, TradeLogConstant.LOGGER_BIZ_AUTH_PAY, "交易订单" + order.getOrderId() + "生成支付订单失败", e, order.getOrderId());
		}
		return null;
	}

	/**
	 * 向对账系统发送充值预处理指令
	 *
	 * @param pay
	 */
	private void prepareBalanceSys(Pay pay) {
		try {
			Map<String, Object> prepareMap = new HashMap<String, Object>();
			prepareMap.put("amt", pay.getAmt().toString());// 金额
			prepareMap.put("platformOrderNo", pay.getPayOrderId());// 平台订单号
			prepareMap.put("payTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(pay.getCreateTime()));// 订单创建时间
			prepareMap.put("payStatus", "1");// 交易状态 1、待支付 2、支付成功 3、支付失败
			amqpTemplate.convertAndSend(QueenNameConstant.BALANCE_PREPARED_MQ_NAME, prepareMap);

			logger.info(TradeLogConstant.LOGGER_MODULE_AUTH_PAY, TradeLogConstant.LOGGER_BIZ_AUTH_PAY, "充值交易订单" + prepareMap + "对账预处理指令发送成功");
		} catch (AmqpException e) {
			logger.error(TradeLogConstant.LOGGER_MODULE_AUTH_PAY, TradeLogConstant.LOGGER_BIZ_AUTH_PAY, "交易订单" + pay.getOrdId() + "发送对账预处理MQ失败", e, pay.getOrdId());
		}
	}

	/**
	 * 调用渠道进行支付
	 * 
	 * @param pay
	 * @param identityId
	 * @return
	 */
	private AuthenPayOutput authenPay(Pay pay, String identityId) {
		try {

			AuthenPayInput input = new AuthenPayInput();
			input.setAmount(pay.getAmt());
			input.setChannelId(pay.getChanelNo());
			input.setIdentityid(identityId);
			input.setOrderNo(pay.getPayOrderId());
			input.setTradeDate(pay.getReqTime());
			input.setMerchantNo(pay.getMchId());
			logger.info(TradeLogConstant.LOGGER_MODULE_AUTH_PAY, TradeLogConstant.LOGGER_BIZ_AUTH_PAY, "充值交易订单" + pay.getOrdId() + "开始调用渠道进行支付，请求参数：" + input.toString());

			// mock start TODO
			// PayAccountOut payAccountOut=new PayAccountOut();
			// payAccountOut.setOrderNo("111111");
			// payAccountOut.setOrgTranFlow("222222");
			// payAccountOut.setResCode("C000000000");
			// payAccountOut.setResMsg("成功");
			// mock stop
			logger.info(TradeLogConstant.LOGGER_MODULE_AUTH_PAY, TradeLogConstant.LOGGER_BIZ_AUTH_PAY, "充值交易订单" + pay.getOrdId() + "开始调用渠道进行支付，请求参数：" + input.toString());
			AuthenPayOutput authenPayOutput = authenPayCardService.authenPay(input);
			logger.info(TradeLogConstant.LOGGER_MODULE_AUTH_PAY, TradeLogConstant.LOGGER_BIZ_AUTH_PAY, "充值交易订单" + pay.getOrdId() + "调用渠道支付完成，渠道返回参数：" + authenPayOutput.toString());

			return authenPayOutput;
		} catch (Exception e) {
			logger.error(TradeLogConstant.LOGGER_MODULE_AUTH_PAY, TradeLogConstant.LOGGER_BIZ_AUTH_PAY, "交易订单" + pay.getOrdId() + "调用渠道支付失败", e, pay.getOrdId());
		}
		return null;
	}

	/**
	 * 根据标识更新交易订单并创建交易流水
	 *
	 * @param order
	 */
	private void updateTradeOrderAndCreateTradeLog(TradeOrder order) {
		order.setLastupdateTime(new Date());
		try {
			tradeOrderManager.updateOrderAndCreateLog(order);
		} catch (Exception e) {
			logger.error(TradeLogConstant.LOGGER_MODULE_AUTH_PAY, TradeLogConstant.LOGGER_BIZ_AUTH_PAY, "更新交易订单" + order.getOrderId() + "失败", e, order.getOrderId());
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
			payManager.updatePayAndCreateLog(pay);
		} catch (Exception e) {
			logger.error(TradeLogConstant.LOGGER_MODULE_AUTH_PAY, TradeLogConstant.LOGGER_BIZ_AUTH_PAY, "交易订单" + pay.getOrdId() + "更新支付订单失败", e, pay.getOrdId());
		}
	}

	/**
	 * 生成查询签约信息dto
	 *
	 * @param input
	 * @param channelId
	 * @param auth
	 * @return
	 */
	private QuerySignInfoDto generateQuerySignInfoDto(PayDirectInput input, String channelId, Auth auth) {
		QuerySignInfoDto dto = new QuerySignInfoDto();
		dto.setMerNo(input.getMerchantId());// 商户号
		dto.setUserId(input.getUserId());// 用户号
		dto.setCardNo(auth.getCardNo());// 卡号
		dto.setBankShort(auth.getBankShort());// 银行英文缩写
		dto.setChannelId(channelId);// 渠道号
		dto.setIdNo(auth.getIdNo());// 身份证号
		dto.setPhoneNo(auth.getPhoneNo());// 手机号
		dto.setRealName(auth.getUserName());// 姓名
		dto.setCardType(auth.getCardType());// 卡类型
		dto.setIdType(auth.getIdType());// 证件类型
		dto.setPayType(input.getPayType());// 支付类型
		return dto;
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
			logger.error(TradeLogConstant.LOGGER_MODULE_AUTH_PAY, TradeLogConstant.LOGGER_BIZ_AUTH_PAY, "保存未知状态订单异常" + unknownOrder, e, pay.getOrdId());
		}
	}
}

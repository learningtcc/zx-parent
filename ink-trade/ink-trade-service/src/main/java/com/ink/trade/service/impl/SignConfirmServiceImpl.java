package com.ink.trade.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.asile.core.manager.IAsileInfoManager;
import com.ink.asile.core.manager.IAsileResCodeManager;
import com.ink.asile.core.manager.IAsileSignManager;
import com.ink.asile.core.po.AsileInfo;
import com.ink.asile.core.po.AsileResCode;
import com.ink.base.log.util.YinkerLogger;
import com.ink.base.utils.dateUtil.DateUtil;
import com.ink.basic.core.manager.IBankcardBinManager;
import com.ink.basic.core.po.BankcardBin;
import com.ink.channel.api.model.in.AuthorityInput;
import com.ink.channel.api.model.out.AuthorityOutput;
import com.ink.channel.api.service.AuthorityService;
import com.ink.trade.api.enums.AuthOrderStatus;
import com.ink.trade.api.enums.PayType;
import com.ink.trade.api.model.in.SignConfirmInput;
import com.ink.trade.api.model.out.SignConfirmOutput;
import com.ink.trade.api.service.ISignConfirmService;
import com.ink.trade.core.cnst.TradeLogConstant;
import com.ink.trade.core.cnst.TradeRespConstant;
import com.ink.trade.core.enums.IsDelete;
import com.ink.trade.core.enums.TradeStatus;
import com.ink.trade.core.exception.TradeException;
import com.ink.trade.core.manager.IAuthManager;
import com.ink.trade.core.manager.IAuthOrderManager;
import com.ink.trade.core.po.Auth;
import com.ink.trade.core.po.AuthOrder;
import com.ink.trade.service.mq.AsileSignToAccProducer;
import com.ink.trade.service.mq.constant.QueenNameConstant;
import com.ink.user.api.model.in.BindCardInput;

/**
 * 
 * <pre>
 * <b>类描述:</b>(签约确认服务实现)
 * <b>作者:</b>zx
 * <b>创建日期:</b>2016年4月13日 下午1:41:16
 * </pre>
 */
@Service("signConfirmService")
public class SignConfirmServiceImpl implements ISignConfirmService {
	private YinkerLogger LOGGER = YinkerLogger.getLogger(SignConfirmServiceImpl.class);
	@Autowired
	private AuthorityService authorityService;
	@Autowired
	private IAuthOrderManager authOrderManager;
	@Autowired
	private IAsileResCodeManager asileResCodeManager;
	@Autowired
	private IAsileSignManager asileSignManager;
	@Autowired
	private AsileSignToAccProducer asileSignToAccProducer;
	@Autowired
	private IAuthManager authManager;
	@Autowired
	private IBankcardBinManager bankCardBinManager;
	@Autowired
	private IAsileInfoManager asileInfoManager;

	@Override
	public SignConfirmOutput signConfirm(SignConfirmInput signConfirmInput) {
		LOGGER.info(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY, TradeLogConstant.LOGGER_BIZ_SIGNCONFIRM, "确认签约信息:" + signConfirmInput.toString());
		LOGGER.debug("开始调用签约确认接口，确认签约信息:" + signConfirmInput.toString());
		SignConfirmOutput signConfirmOut = new SignConfirmOutput();
		try {
			// 根据签约申请订单号查询信息
			AuthOrder authOrder = authOrderManager.getOrderByOrderId(signConfirmInput.getSignOrderId());
			if (authOrder == null) {
				LOGGER.error(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY, TradeLogConstant.LOGGER_BIZ_SIGNCONFIRM, "签约订单不存在,订单id:" + signConfirmInput.getSignOrderId(), null);
				throw new TradeException("签约订单不存在");
			}
			if (authOrder.getStatus() != AuthOrderStatus.PRECONTRACT.getValue()) {
				LOGGER.error(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY, TradeLogConstant.LOGGER_BIZ_SIGNCONFIRM, "签约订单不正常！" + authOrder.toString(), null);
				throw new TradeException("订单状态不正常");
			}
			// 判断token的有效期
			AsileInfo asileInfo = new AsileInfo();
			asileInfo.setAsileCode(authOrder.getChannelNo());
			asileInfo = asileInfoManager.findAsileInfos(asileInfo).get(0);
			if (null != asileInfo && asileInfo.getTokenExpireTime() != null) {
				if (DateUtil.getSenconds(new Date(), authOrder.getTokenCreateTime()) > asileInfo.getTokenExpireTime()) {
					LOGGER.error(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY, TradeLogConstant.LOGGER_BIZ_SIGNCONFIRM, "token已失效！" + authOrder.toString(), null);
					throw new TradeException("token已失效！");
				}
			}
			// 渠道签约确认
			AuthorityOutput authorityOutput = confirmSign(authOrder, signConfirmInput);
			// 解析响应码更新签约订单信息
			signConfirmOut = updateAuthOrder(signConfirmInput, authorityOutput, authOrder);
			LOGGER.info(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY, TradeLogConstant.LOGGER_BIZ_SIGNCONFIRM, "签约确认完成！");
		} catch (TradeException ex) {
			LOGGER.error(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY, TradeLogConstant.LOGGER_BIZ_SIGNCONFIRM, "确认签约失败", ex, null);
			signConfirmOut.setTradeStatus(TradeStatus.FAIL.getValue());
			signConfirmOut.setReponseCode(ex.getCode());
			signConfirmOut.setReponseMsg(ex.getMessage());
		} catch (Exception ex) {
			LOGGER.error(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY, TradeLogConstant.LOGGER_BIZ_SIGNCONFIRM, "确认签约失败", ex, null);
			signConfirmOut.setTradeStatus(TradeStatus.FAIL.getValue());
			signConfirmOut.setReponseCode(TradeRespConstant.TRADE_SYSERROR);
			signConfirmOut.setReponseMsg(TradeRespConstant.TRADE_SYSERROR_MSG);
		}

		return signConfirmOut;
	}

	/**
	 * 确认签约
	 * 
	 * @param authOrder
	 * @param signConfirmInput
	 * @return
	 */
	public AuthorityOutput confirmSign(AuthOrder authOrder, SignConfirmInput signConfirmInput) {
		LOGGER.info(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY, TradeLogConstant.LOGGER_BIZ_SIGNCONFIRM, "调用渠道签约确认开始");
		AuthorityInput authority = new AuthorityInput();
		authority.setBankShort(authOrder.getBankNameShort());
		authority.setCardNo(authOrder.getCardNo());
		authority.setCardType(authOrder.getCardType());
		authority.setChannelId(authOrder.getChannelNo());
		authority.setIdentityId(authOrder.getSignId());
		authority.setIdNo(authOrder.getIdNo());
		authority.setIdType(authOrder.getIdType());
		authority.setPhoneNo(authOrder.getPhone());
		authority.setReqId(authOrder.getReqId());
		authority.setUserName(authOrder.getUserName());
		authority.setValidCode(signConfirmInput.getValidMessage());
		authority.setToken(authOrder.getToken());
		authority.setMerchantNo(authOrder.getMchId());
		AuthorityOutput out = new AuthorityOutput();
		LOGGER.info(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY, TradeLogConstant.LOGGER_BIZ_SIGNCONFIRM, "签约确认请求参数：" + authority != null ? authority.toString() : null);
		// #mock start
		out = authorityService.authorize(authority);
		LOGGER.info(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY, TradeLogConstant.LOGGER_BIZ_SIGNCONFIRM, "签约确认响应结果：" + out != null ? out.toString() : null);
		// out.setIdentityId("88665544332211");
		// out.setResCode("C000000000");
		// out.setResMsg("成功");
		// #mock stop
		// out.setIdentityId("12345678");
		// out.setResCode("000000");
		// out.setResMsg("成功");
		return out;
		// 调用渠道鉴权确认接口
		// return authorityService.authorize(authority);
	}

	/**
	 * 解析响应码更新签约订单
	 * 
	 * @param signConfirmInput
	 * @param authorityOutput
	 * @return
	 */
	public SignConfirmOutput updateAuthOrder(SignConfirmInput signConfirmInput, AuthorityOutput authorityOutput, AuthOrder authOrder) {
		LOGGER.info(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY, TradeLogConstant.LOGGER_BIZ_SIGNCONFIRM, "进入更新签约订单");
		SignConfirmOutput signConfirmOut = new SignConfirmOutput();
		signConfirmOut.setMerchantId(signConfirmInput.getMerchantId());
		signConfirmOut.setTradeCode(signConfirmInput.getTradeCode());
		signConfirmOut.setSignOrderId(signConfirmInput.getSignOrderId());
		signConfirmOut.setPayType(signConfirmInput.getPayType());
		signConfirmOut.setUserId(signConfirmInput.getUserId());
		// 响应码解析
		AsileResCode asileResCode = asileResCodeManager.findByAsileCodeAndAsileResCode(authOrder.getChannelNo(), authorityOutput.getResCode());
		// 响应码未映射
		if (asileResCode == null) {
			LOGGER.error(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY, TradeLogConstant.LOGGER_BIZ_SIGNCONFIRM, "解析响应码未成功：code=" + authorityOutput.getResCode() + "msg" + authorityOutput.getResMsg(), null);
			signConfirmOut.setReponseCode(TradeRespConstant.TRADE_SYSERROR);
			signConfirmOut.setReponseMsg(TradeRespConstant.TRADE_SYSERROR_MSG);
			signConfirmOut.setTradeStatus(TradeStatus.FAIL.getValue());
			return signConfirmOut;
		}
		signConfirmOut.setReponseCode(asileResCode.getResCode());
		signConfirmOut.setReponseMsg(asileResCode.getResMsg());
		authOrder.setAsileRepCode(asileResCode.getAsileCode());
		authOrder.setAsileRepMsg(asileResCode.getAsileResMsg());
		authOrder.setRepCode(asileResCode.getResCode());
		authOrder.setRepMsg(asileResCode.getResMsg());
		// 响应成功
		if (TradeRespConstant.TRADE_SUCCESS.equals(asileResCode.getResCode())) {
			// 通知账户绑卡
			bindCard(authOrder, signConfirmInput);
			signConfirmOut.setTradeStatus(TradeStatus.SUCCESS.getValue());
			signConfirmOut.setMerchantId(signConfirmInput.getMerchantId());
			signConfirmOut.setTradeCode(signConfirmInput.getTradeCode());
			signConfirmOut.setSignOrderId(signConfirmInput.getSignOrderId());
			// 更新签约订单表
			authOrder.setStatus(AuthOrderStatus.SUCCESS.getValue());
			// 更新签约号
			authOrder.setSignId(authorityOutput.getIdentityId());
			authOrder.setLastupdateTime(new Date());
			authOrder.setStatus(AuthOrderStatus.SUCCESS.getValue());
			authOrderManager.update(authOrder);
			LOGGER.info(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY, TradeLogConstant.LOGGER_BIZ_SIGNCONFIRM, "更新签约订单结束");
			// 保存签约信息到签约表
		} else {// 渠道失败
			LOGGER.info(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY, TradeLogConstant.LOGGER_BIZ_SIGNCONFIRM, "签约确认失败，更新签约订单开始");
			signConfirmOut.setTradeStatus(TradeStatus.FAIL.getValue());
			// 更新签约订单表
			authOrder.setStatus(AuthOrderStatus.FAIL.getValue());
			authOrder.setLastupdateTime(new Date());
			authOrderManager.update(authOrder);
			LOGGER.info(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY, TradeLogConstant.LOGGER_BIZ_SIGNCONFIRM, "签约确认失败，更新签约订单结束");
		}
		return signConfirmOut;
	}

	/**
	 * 调用绑卡接口
	 * 
	 * @param authOrder
	 */
	public void bindCard(AuthOrder authOrder, SignConfirmInput signConfirmInput) {
		LOGGER.info(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY, TradeLogConstant.LOGGER_BIZ_SIGNCONFIRM, "签约成功，保存绑卡信息开始");
		BindCardInput dto = new BindCardInput();
		dto.setBankMblNo(authOrder.getPhone());
		dto.setCardNo(authOrder.getCardNo());
		dto.setCardType(authOrder.getCardType());
		dto.setCustId(authOrder.getUserId());
		dto.setCustName(authOrder.getUserName());
		dto.setIdNo(authOrder.getIdNo());
		dto.setIdType(authOrder.getIdType());
		dto.setMchId(authOrder.getMchId());
		dto.setOrdId(authOrder.getOrderId());
		dto.setTradeDate(DateUtil.formatToYYYYMMDDMMHHSS(authOrder.getCreateTime()));
		dto.setTxnCode("BC");
		LOGGER.info(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY, TradeLogConstant.LOGGER_BIZ_SIGNCONFIRM, "向账户异步推送绑卡信息" + dto.toString());
		asileSignToAccProducer.sendMessage(QueenNameConstant.ACC_BIND_CARD_MQ_NAME, dto);
		Auth auth = new Auth();
		auth.setUserName(authOrder.getUserName());
		auth.setBankShort(authOrder.getBankNameShort());
		auth.setCardNo(authOrder.getCardNo());
		auth.setCardType(authOrder.getCardType());
		auth.setIdNo(authOrder.getIdNo());
		auth.setIdType(authOrder.getIdType());
		auth.setIsDelete(IsDelete.NO.getValue());
		auth.setLastupdateTime(new Date());
		auth.setMchId(authOrder.getMchId());
		auth.setPhoneNo(authOrder.getPhone());
		auth.setVersion(1);
		auth.setUserId(authOrder.getUserId());
		auth.setCreateTime(new Date());
		BankcardBin bankCardBin = bankCardBinManager.getByCardBin(authOrder.getCardNo());
		auth.setBankName(bankCardBin.getBankName());
		auth.setPayType(PayType.ALL.getValue());
		try {
			authManager.saveOrUpdate(auth);
		} catch (Exception ex) {
			LOGGER.error(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY, TradeLogConstant.LOGGER_BIZ_SIGNCONFIRM, "保存绑卡信息失败" + auth, "");
		}
		LOGGER.info(TradeLogConstant.LOGGER_MODULE_COLLECT_PAY, TradeLogConstant.LOGGER_BIZ_SIGNCONFIRM, "签约成功，保存绑卡信息结束");
	}
}

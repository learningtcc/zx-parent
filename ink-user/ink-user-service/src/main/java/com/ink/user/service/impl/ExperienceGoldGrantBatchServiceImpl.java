package com.ink.user.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ink.base.log.util.YinkerLogger;
import com.ink.user.api.constants.AtpTnsConstant;
import com.ink.user.api.constants.RespCodeConstant;
import com.ink.user.api.exception.AtpBusinessException;
import com.ink.user.api.model.GoldGrantBean;
import com.ink.user.api.model.in.ExperienceGoldGrantBatchInput;
import com.ink.user.api.model.out.RetOutput;
import com.ink.user.api.service.IExperienceGoldGrantBatchService;
import com.ink.user.common.constant.UserLoggerCnst;
import com.ink.user.core.po.AccCust;
import com.ink.user.core.po.ReqLog;
import com.ink.user.core.po.TnsLog;
import com.ink.user.core.po.TnsTxn;
import com.ink.user.service.handle.AccountBusinessHandle;
import com.ink.user.service.handle.FundBusinessHandle;

/**
 * @Description: 体验金发放(批量)
 * @author wanghao^_^
 * @date 2016年6月13日 下午1:32:31
 * @version V1.0
 */
public class ExperienceGoldGrantBatchServiceImpl extends AbstractAccServiceImpl<RetOutput, ExperienceGoldGrantBatchInput> implements IExperienceGoldGrantBatchService{

	@Autowired
	private AccountBusinessHandle accountBusinessHandle;
	@Autowired
	private FundBusinessHandle fundBusinessHandle;
	private static YinkerLogger logger = YinkerLogger.getLogger(ExperienceGoldGrantBatchServiceImpl.class);
	
	@Override
	public RetOutput exec(ExperienceGoldGrantBatchInput dto) throws Exception {
		logger.info(UserLoggerCnst.USER_ACCOUNT_MOUDLE,
				UserLoggerCnst.USER_EGGB,
				"进入体验金发放批量接口.....ExperienceGoldGrantBatch#exec()方法dto=" + dto.toString());
		RetOutput ret = doExec(dto);
		return ret;
	}
	
	public RetOutput doExec(ExperienceGoldGrantBatchInput dto) throws Exception {
		RetOutput ret = new RetOutput();
		try {
			String txnCode = dto.getTxnCode();
			if (!txnCode.equals(AtpTnsConstant.ACC_EGGB)) {
				logger.error("报文类型格式不正确！");
				throw new AtpBusinessException(RespCodeConstant.RespCode_000002,
						RespCodeConstant.RespCode_000002Desc);
			}

			// 启动异步线程处理
			callAsynProcess(dto);

			// 返回结果
			ret.setRetCode(RespCodeConstant.RespCode_000000);
			ret.setRetMsg(RespCodeConstant.RespCode_000000Desc);
		} catch (AtpBusinessException e) {
			logger.error("ExperienceGoldGrantBatch体验金回收批量接口出现异常！", e);
			ret.setRetCode(e.getCode());
			ret.setRetMsg(e.getMessage());
		} catch (Exception e) {
			logger.error("ExperienceGoldGrantBatch体验金发放批量接口！", e);
			ret.setRetCode(RespCodeConstant.RespCode_200000);
			ret.setRetMsg("ExperienceGoldGrantBatch体验金发放批量接口" + RespCodeConstant.RespCode_200000Desc);
		} finally {
			ret.setOrdId(dto.getId());
			ret.setTradeDate(dto.getTradeDate());
		}
		return ret;
	}

	@Override
	protected void doAsynProcess(ExperienceGoldGrantBatchInput dto) {
		logger.info("异步线程开启，处理ExperienceGoldGrantBatchInput");
		for (GoldGrantBean goldGrant : dto.getList()) {
			TnsLog tnsLog = new TnsLog();
			Map<String, Object> map = new HashMap<String, Object>();
			try {
				logger.info("体验金发放处理，goldGrant={}", goldGrant);
				map = goldGrantCheck(goldGrant, dto.getTxnCode(),
						dto.getMchId(), tnsLog);
				AccCust accCust = (AccCust) map.get("accCust");
				accountBusinessHandle.createAccIfNotExist(accCust, tnsLog.getAccSubType(), "");
				fundBusinessHandle.experienceGoldGrant(map);
				tnsLog.setUid(accCust.getUid());// 更新uid平台唯一编号
				map.put("retCode", RespCodeConstant.RespCode_000000);
				map.put("retMsg", RespCodeConstant.RespCode_000000Desc);
				map.put("state", "0");
				logger.info(UserLoggerCnst.USER_ACCOUNT_MOUDLE,
						UserLoggerCnst.USER_EGGB, "体验金发放处理成功，goldGrant={}",
						goldGrant);

			} catch (AtpBusinessException e) {
				logger.error(UserLoggerCnst.USER_ACCOUNT_MOUDLE,
						UserLoggerCnst.USER_EGGB, "体验金发放处理异常，goldGrant="
								+ goldGrant.toString(), e, null);
				map.put("retCode", e.getCode());
				map.put("retMsg", e.getMessage());
				map.put("state", "1");

			} catch (Exception e) {
				logger.error(UserLoggerCnst.USER_ACCOUNT_MOUDLE,
						UserLoggerCnst.USER_EGGB, "体验金发放处理异常，goldGrant="
								+ goldGrant.toString(), e, null);
				map.put("retCode", RespCodeConstant.RespCode_200000);
				map.put("retMsg", RespCodeConstant.RespCode_200000Desc);
				map.put("state", "1");

			} finally {
				map.put("mchId", dto.getMchId());
				fundBusinessHandle.saveGoldGrantHistory(map, goldGrant,
						dto.getTxnCode());
			}
		}
	}

	private Map<String, Object> goldGrantCheck(GoldGrantBean goldGrant,
			String txnCode, String mchId, TnsLog tnsLog) throws Exception {
		JSONObject json = (JSONObject) JSON.toJSON(goldGrant);
		json.put("txnCode", txnCode);
		json.put("mchId", mchId);
		Map<String, Object> map = check(txnCode, json.toJSONString());
		// tnsLog
		tnsLog.setAccSubType(goldGrant.getAccountType());
		tnsLog.setAmt(new BigDecimal(goldGrant.getAmt()));
		tnsLog.setTxnCode(txnCode);
		map.put("tnsLog", tnsLog);
		map.put("upItemId", "224104");
		map.put("aceGroup", AtpTnsConstant.ACC_30110_0001);
		map.put("subType", tnsLog.getAccSubType());
		return map;
	}

	@Override
	public void insertTnsLog(TnsLog tnsLog, ReqLog reqLog, String json, TnsTxn tnsTxn)
			throws Exception {
	}
}

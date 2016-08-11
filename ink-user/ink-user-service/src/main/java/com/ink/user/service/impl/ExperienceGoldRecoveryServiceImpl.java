package com.ink.user.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ink.base.log.util.YinkerLogger;
import com.ink.user.api.constants.AtpTnsConstant;
import com.ink.user.api.constants.RespCodeConstant;
import com.ink.user.api.exception.AtpBusinessException;
import com.ink.user.api.model.GoldRecoveryBean;
import com.ink.user.api.model.in.ExperienceGoldRecoveryInput;
import com.ink.user.api.model.out.RetOutput;
import com.ink.user.api.service.IExperienceGoldRecoveryService;
import com.ink.user.common.constant.UserLoggerCnst;
import com.ink.user.core.po.ReqLog;
import com.ink.user.core.po.TnsLog;
import com.ink.user.core.po.TnsTxn;
import com.ink.user.core.service.tns.ITnsTxnService;
import com.ink.user.service.handle.FundBusinessHandle;

/**
 * @Description: 体验金回收
 * @author wanghao^_^
 * @date 2016年6月13日 下午2:18:11
 * @version V1.0
 */
public class ExperienceGoldRecoveryServiceImpl extends AbstractAccServiceImpl<RetOutput, ExperienceGoldRecoveryInput> implements IExperienceGoldRecoveryService{

	@Autowired
	private ITnsTxnService tnsTxnService;
	@Autowired
	private FundBusinessHandle fundBusinessHandle;
	private static YinkerLogger logger = YinkerLogger.getLogger(ExperienceGoldRecoveryServiceImpl.class);
	
	@Override
	public RetOutput exec(ExperienceGoldRecoveryInput dto) throws Exception {
		logger.info(UserLoggerCnst.USER_ACCOUNT_MOUDLE,UserLoggerCnst.USER_EGR, 
				"进入体验金回收批量接口.....ExperienceGoldRecovery#exec()方法dto=" + dto.toString());
		RetOutput ret = doExec(dto);
		return ret;
	}

	public RetOutput doExec(ExperienceGoldRecoveryInput dto) throws Exception {
		RetOutput ret = new RetOutput();
		try {
			String txnCode = dto.getTxnCode();
			if (!txnCode.equals(AtpTnsConstant.ACC_EGR)) {
				logger.error("报文类型格式不正确！");
				throw new AtpBusinessException(RespCodeConstant.RespCode_000002,
						RespCodeConstant.RespCode_000002Desc);
			}
			// 根据交易代码获取交易信息
			tnsTxnService.checkTnsTxn(dto.getTxnCode());
			
			// 启动异步线程处理
			callAsynProcess(dto);
			// 返回结果
			
			ret.setRetCode(RespCodeConstant.RespCode_000000);
			ret.setRetMsg(RespCodeConstant.RespCode_000000Desc);
		} catch (AtpBusinessException e) {
			logger.error("ExperienceGoldRecovery体验金回收批量接口出现异常！", e);
			ret.setRetCode(e.getCode());
			ret.setRetMsg(e.getMessage());
		} catch (Exception e) {
			logger.error("ExperienceGoldRecovery体验金回收批量接口！", e);
			ret.setRetCode(RespCodeConstant.RespCode_200000);
			ret.setRetMsg("ExperienceGoldRecovery体验金回收批量接口" + RespCodeConstant.RespCode_200000Desc);
		} finally {
			ret.setOrdId(dto.getId());
			ret.setTradeDate(dto.getTradeDate());
		}
		return ret;
	}

	@Override
	public void insertTnsLog(TnsLog tnsLog, ReqLog reqLog, String json, TnsTxn tnsTxn)
			throws Exception {
	}
	
	@Override
	protected void doAsynProcess(ExperienceGoldRecoveryInput dto) {
		logger.info("异步线程开启，处理ExperienceGoldRecoveryInput");
		for(GoldRecoveryBean goldRecovery : dto.getList()){
			Map<String, Object> map = new HashMap<String, Object>();
			try {
				goldRecovery.setAccountType("0004");
				logger.info("体验金回收处理，goldRecovery={}", goldRecovery);
				map = goldReceveryCheck(goldRecovery, dto.getTxnCode(), dto.getMchId());
				map.put("goldRecovery", goldRecovery);
				// 账户增加利息
				fundBusinessHandle.experienceGoldRecovery(map);
				map.put("retCode", RespCodeConstant.RespCode_000000);
				map.put("retMsg", RespCodeConstant.RespCode_000000Desc);
				map.put("state", "0");
				logger.info(UserLoggerCnst.USER_ACCOUNT_MOUDLE,UserLoggerCnst.USER_EGR, 
						"体验金回收处理成功，goldRecovery={}", goldRecovery);
			} catch(AtpBusinessException e){
				logger.error(UserLoggerCnst.USER_ACCOUNT_MOUDLE,UserLoggerCnst.USER_EGR, 
						"体验金回收处理异常，goldRecovery=" + goldRecovery.toString(), e, null);
				map.put("retCode", e.getCode());
				map.put("retMsg", e.getMessage());
				map.put("state", "1");

			} catch (Exception e) {
				logger.error(UserLoggerCnst.USER_ACCOUNT_MOUDLE,UserLoggerCnst.USER_EGR, 
						"体验金回收处理异常，goldRecovery=" + goldRecovery.toString(), e, null);
				map.put("retCode", RespCodeConstant.RespCode_200000);
				map.put("retMsg", RespCodeConstant.RespCode_200000Desc);
				map.put("state", "1");

			}finally{
				map.put("mchId", dto.getMchId());
				fundBusinessHandle.saveGoldRecoveryHistory(map ,goldRecovery, dto.getTxnCode());
			}
		}
	}

	private Map<String, Object> goldReceveryCheck(
			GoldRecoveryBean goldRecovery, String txnCode, String mchId) throws Exception {
		JSONObject json = (JSONObject) JSON.toJSON(goldRecovery);
		json.put("txnCode", txnCode);
		json.put("mchId", mchId);
		Map<String, Object> map = check(txnCode, json.toJSONString());
		return map;
	}

}

package com.ink.user.service.impl;

import java.util.Map;

import com.ink.user.service.handle.AccountBusinessHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import com.alibaba.fastjson.JSON;
import com.ink.base.log.util.YinkerLogger;
import com.ink.user.api.constants.AtpTnsConstant;
import com.ink.user.api.constants.RespCodeConstant;
import com.ink.user.api.exception.AtpBusinessException;
import com.ink.user.api.model.in.ModifyUserBaseInfoInput;
import com.ink.user.api.model.out.RetOutput;
import com.ink.user.api.service.IModifyUserBaseInfoService;
import com.ink.user.common.constant.UserLoggerCnst;
import com.ink.user.core.po.AccCust;
import com.ink.user.core.po.ReqLog;
import com.ink.user.core.po.TnsLog;
import com.ink.user.core.po.TnsTxn;
import com.ink.user.core.service.IReqLogManager;
import com.ink.user.util.DateUtils;

public class ModifyUserBaseInfoServiceImpl extends AbstractAccServiceImpl<RetOutput, ModifyUserBaseInfoInput> implements IModifyUserBaseInfoService{
	private static YinkerLogger logger = YinkerLogger.getLogger(ModifyUserBaseInfoServiceImpl.class);

	@Autowired
	private IReqLogManager reqLogManager;
	@Autowired
	private AccountBusinessHandle accountBusinessHandle;
	@Override
	public RetOutput exec(ModifyUserBaseInfoInput dto) {
		logger.info(UserLoggerCnst.USER_ACCOUNT_MOUDLE,UserLoggerCnst.USER_MUBI, 
				"进入客户基本资料修改........ModifyUserBaseInfo#exec(dto:" + dto.toString() + ")");
		RetOutput ret = doExec(dto);
		logger.info(UserLoggerCnst.USER_ACCOUNT_MOUDLE,UserLoggerCnst.USER_MUBI, ret.toString());
		return ret;
	}
	private RetOutput doExec(ModifyUserBaseInfoInput dto) {
		RetOutput ret = new RetOutput();
		ReqLog reqLog = new ReqLog();
		try {
			String json = JSON.toJSONString(dto);
			// 检查
			Map<String, Object> map = check(AtpTnsConstant.ACC_MUBI, json);
			
			insertTnsLog(null, reqLog, json, (TnsTxn) map.get("tnsTxn"));
			
			// 修改
			accountBusinessHandle.changeCustBaseInfo((AccCust) map.get("accCust"), dto );
			
			ret.setRetCode(RespCodeConstant.RespCode_000000);
			ret.setRetMsg(RespCodeConstant.RespCode_000000Desc);
		}catch(AtpBusinessException e){
			logger.error(UserLoggerCnst.USER_ACCOUNT_MOUDLE,UserLoggerCnst.USER_MUBI, 
					"个人基本信息修改异常！"+e.getMessage(), e, dto.getOrdId());
			ret.setRetCode(e.getCode());
			ret.setRetMsg(e.getMessage());
		}catch(Exception e){
			logger.error(UserLoggerCnst.USER_ACCOUNT_MOUDLE,UserLoggerCnst.USER_MUBI, 
					"个人基本信息修改异常！"+e.getMessage(), e, dto.getOrdId());
			ret.setRetCode(RespCodeConstant.RespCode_200000);
			ret.setRetMsg(RespCodeConstant.RespCode_200000Desc);
		}finally{
			if (reqLog.getId() != null) {
				reqLog.setRetCode(ret.getRetCode());
				reqLog.setRetMsg(ret.getRetMsg());
				reqLogManager.update(reqLog);
				ret.setAccTnsId(reqLog.getId().toString());
			}
			ret.setOrdId(dto.getOrdId());
			ret.setTradeDate(dto.getTradeDate());
		}
		return ret;
	}
	@Override
	public void insertTnsLog(TnsLog tnsLog, ReqLog reqLog, String json,
			TnsTxn tnsTxn) throws Exception {
		ModifyUserBaseInfoInput dto = JSON.parseObject(json, ModifyUserBaseInfoInput.class);
		// 数据拷贝
		BeanCopier copier = BeanCopier.create(ModifyUserBaseInfoInput.class, ReqLog.class,
				false);
		copier.copy(dto, reqLog, null);
		BeanCopier tsnTxncopier = BeanCopier.create(TnsTxn.class, ReqLog.class,
				false);
		tsnTxncopier.copy(tnsTxn, reqLog, null);
		reqLog.setMchId(Long.parseLong(dto.getMchId()));
		reqLog.setOrdDate(DateUtils.strToDateLong(dto.getTradeDate()));
		reqLogManager.insert(reqLog);		
	}

}

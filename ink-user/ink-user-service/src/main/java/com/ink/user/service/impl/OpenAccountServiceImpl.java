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
import com.ink.user.api.model.in.OpenAccountInput;
import com.ink.user.api.model.out.OpenAccountOutput;
import com.ink.user.api.service.IOpenAccountService;
import com.ink.user.common.constant.UserLoggerCnst;
import com.ink.user.core.po.AccCust;
import com.ink.user.core.po.ReqLog;
import com.ink.user.core.po.TnsLog;
import com.ink.user.core.po.TnsTxn;
import com.ink.user.core.service.IReqLogManager;
import com.ink.user.util.DateUtils;

/**
 * @Description: 个人账户开户默认先开资金账户
 * @author wanghao
 * @date 2016年5月24日 下午5:20:24
 */
public class OpenAccountServiceImpl extends AbstractAccServiceImpl<OpenAccountOutput, OpenAccountInput> implements IOpenAccountService{
	@Autowired
	private AccountBusinessHandle accountBusinessHandle;
	@Autowired
	private IReqLogManager reqLogManager;
	private static YinkerLogger logger = YinkerLogger.getLogger(OpenAccountServiceImpl.class);
	
	@Override
	public OpenAccountOutput exec(OpenAccountInput dto) throws Exception {
		logger.info(UserLoggerCnst.USER_ACCOUNT_MOUDLE,UserLoggerCnst.USER_OA, 
				"进入个人账户开户-------- OpenAccount#exec(tnsLog:" + dto.toString()
				+ ")");
		OpenAccountOutput ret = doExec(dto);
		logger.info(UserLoggerCnst.USER_ACCOUNT_MOUDLE,UserLoggerCnst.USER_OA, ret.toString());
		return ret;
	}

	public OpenAccountOutput doExec(OpenAccountInput dto) throws Exception{
		OpenAccountOutput retDTO = new OpenAccountOutput();
		ReqLog reqLog = new ReqLog();
		try {
			String json = JSON.toJSONString(dto);
			//检查信息
			Map<String, Object> map = check(AtpTnsConstant.ACC_OA, json);
			insertTnsLog(null, reqLog, json, (TnsTxn) map.get("tnsTxn"));
			//开户逻辑信息
			AccCust accCust = accountBusinessHandle.openAccount(reqLog, dto);
			retDTO.setUid(accCust.getUid().toString());
			retDTO.setAccountId(accCust.getPacId().toString());
			retDTO.setRetCode(RespCodeConstant.RespCode_000000);
			retDTO.setRetMsg(RespCodeConstant.RespCode_000000Desc);
		} catch (AtpBusinessException e) {
			logger.error(UserLoggerCnst.USER_ACCOUNT_MOUDLE,UserLoggerCnst.USER_OA, 
					"个人账户开户（OpenAccount）异常！"+e.getMessage(), e, dto.getOrdId());
			retDTO.setRetCode(e.getCode());
			retDTO.setRetMsg(e.getMessage());
		} catch (Exception e) {
			logger.error(UserLoggerCnst.USER_ACCOUNT_MOUDLE,UserLoggerCnst.USER_OA, 
					"个人账户开户（OpenAccount）异常！"+e.getMessage(), e, dto.getOrdId());
			retDTO.setRetCode(RespCodeConstant.RespCode_200000);
			retDTO.setRetMsg(RespCodeConstant.RespCode_200000Desc);
		}
		finally {
			if (reqLog.getId() != null) {
				reqLog.setRetCode(retDTO.getRetCode());
				reqLog.setRetMsg(retDTO.getRetMsg());
				try {
					reqLogManager.update(reqLog);
				} catch (Exception e) {
					logger.error(UserLoggerCnst.USER_ACCOUNT_MOUDLE,UserLoggerCnst.USER_OA, 
							"个人账户开户（OpenAccount）异常！"+e.getMessage(), e, dto.getOrdId());				}
				retDTO.setAccTnsId(reqLog.getId().toString());
			}
			// 更新流水和报文信息
			retDTO.setTradeDate(DateUtils.getDateTime());
		}
		return retDTO;
	}
	
	@Override
	public void insertTnsLog(TnsLog tnsLog, ReqLog reqLog, String json, TnsTxn tnsTxn)
			throws Exception {
		OpenAccountInput dto = JSON.parseObject(json, OpenAccountInput.class);
		// 数据拷贝
		BeanCopier copier = BeanCopier.create(OpenAccountInput.class, ReqLog.class,
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

package com.ink.user.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import com.ink.user.service.handle.AccountBusinessHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import com.alibaba.fastjson.JSON;
import com.ink.base.log.util.YinkerLogger;
import com.ink.user.api.constants.AtpTnsConstant;
import com.ink.user.api.constants.RespCodeConstant;
import com.ink.user.api.exception.AtpBusinessException;
import com.ink.user.api.model.in.AccRechargeInput;
import com.ink.user.api.model.out.RetOutput;
import com.ink.user.api.service.IAccRechargeService;
import com.ink.user.common.constant.UserLoggerCnst;
import com.ink.user.core.po.AccCust;
import com.ink.user.core.po.ReqLog;
import com.ink.user.core.po.TnsLog;
import com.ink.user.core.po.TnsTxn;
import com.ink.user.core.service.tns.ITnsLogService;
import com.ink.user.service.handle.FundBusinessHandle;
import com.ink.user.util.DateUtils;

public class AccRechargeServiceImpl extends AbstractAccServiceImpl<RetOutput, AccRechargeInput> implements IAccRechargeService{

	@Autowired
	private ITnsLogService tnsLogService;
	@Autowired
	private FundBusinessHandle fundBusinessHandle;
	@Autowired
	private AccountBusinessHandle accountBusinessHandle;
	
	private static YinkerLogger logger = YinkerLogger.getLogger(AccRechargeServiceImpl.class);
	
	@Override
	public RetOutput exec(AccRechargeInput dto) throws Exception {
		logger.info(UserLoggerCnst.USER_RECHARGE_MOUDLE,UserLoggerCnst.USER_AR, 
				"AccRecharge充值接口报文信息.....AccRecharge#exec()方法,tnsLog=【" 
						+ dto.toString() + "】");
//		Date in = new Date();
//		logger.debug("30150入参时间=" + in.getTime()  );
		RetOutput ret = doExec(dto);
		logger.info(UserLoggerCnst.USER_RECHARGE_MOUDLE,UserLoggerCnst.USER_AR,"[" + ret.toString() + "],时间=" + new Date().getTime() ); 
//		Date out = new Date();
//		logger.debug("30150出参时间=" + out.getTime() + ", 时间差 ：" + ( out.getTime() - in.getTime() ) );
		return ret;
	}

	public void onMessage(String msg) throws Exception {
		try{
			logger.info(UserLoggerCnst.USER_RECHARGE_MOUDLE,"MQ异步活期充值接口ACC30150，msg=" + msg);
			if(msg == null){
				logger.error(UserLoggerCnst.USER_RECHARGE_MOUDLE, "AccRecharge充值接口报文信息.....AccRecharge#exec()， msg 为空");
			}
			AccRechargeInput dto = JSON.parseObject(msg,AccRechargeInput.class);
			doExec(dto);
		}catch(Exception e){
			logger.error(UserLoggerCnst.USER_RECHARGE_MOUDLE, "AccRecharge充值接口报文信息.....AccRecharge#exec()， msg 解析出错");
		}
	}
	
	public RetOutput doExec(AccRechargeInput dto) throws Exception {
		RetOutput ret = new RetOutput();
		TnsLog tnsLog = new TnsLog();
		try {
			
			String json = JSON.toJSONString(dto);
			// 检查
			Map<String, Object> map = check(AtpTnsConstant.ACC_AR, json);
			insertTnsLog(tnsLog, null, json, (TnsTxn) map.get("tnsTxn"));
			map.put("tnsLog", tnsLog);
			map.put("aceGroup", AtpTnsConstant.ACC_30110_0001);
			map.put("subType", tnsLog.getAccSubType());
			AccCust accCust = (AccCust) map.get("accCust");
			tnsLog.setUid(accCust.getUid());// 更新uid平台唯一编号
			accountBusinessHandle.createAccIfNotExist(accCust,tnsLog.getAccSubType(), "");
			// 充值操作
			fundBusinessHandle.fundAccount(map);
			ret.setRetCode(RespCodeConstant.RespCode_000000);
			ret.setRetMsg(RespCodeConstant.RespCode_000000Desc);
		} catch (AtpBusinessException e) {
			logger.error(UserLoggerCnst.USER_RECHARGE_MOUDLE,UserLoggerCnst.USER_AR, 
					"AccRecharge充值接口异常！"+e.getMessage(), e, dto.getOrdId());
			ret.setRetCode(e.getCode());
			ret.setRetMsg(e.getMessage());
		} catch (Exception e) {
			logger.error(UserLoggerCnst.USER_RECHARGE_MOUDLE,UserLoggerCnst.USER_AR, 
					"AccRecharge充值接口出现Exception异常！"+e.getMessage(), e, dto.getOrdId());
			ret.setRetCode(RespCodeConstant.RespCode_200000);
			ret.setRetMsg(RespCodeConstant.RespCode_200000Desc);
			throw e;
		} finally {
			if (tnsLog.getId() != null) {
				tnsLog.setRetCode(ret.getRetCode());
				tnsLog.setRetMsg(ret.getRetMsg());
				tnsLogService.updateTnsLog(tnsLog);
				ret.setAccTnsId(tnsLog.getId().toString());
			}
			ret.setOrdId(dto.getOrdId());
			ret.setTradeDate(dto.getTradeDate());
		}
		return ret;
	}

	@Override
	public void insertTnsLog(TnsLog tnsLog, ReqLog reqLog, String json, TnsTxn tnsTxn)
			throws Exception {
		AccRechargeInput dto = JSON.parseObject(json, AccRechargeInput.class);
		// 数据拷贝
		BeanCopier copier = BeanCopier.create(AccRechargeInput.class, TnsLog.class,
				false);
		copier.copy(dto, tnsLog, null);
		BeanCopier tsnTxncopier = BeanCopier.create(TnsTxn.class, TnsLog.class,
				false);
		tsnTxncopier.copy(tnsTxn, tnsLog, null);
		tnsLog.setAmt(new BigDecimal(dto.getAmt()));
		tnsLog.setMchId(Long.parseLong(dto.getMchId()));
		tnsLog.setCustFee(new BigDecimal(dto.getCustFee()));
		tnsLog.setOrgId(dto.getChannelId());
		tnsLog.setOrdDate(DateUtils.strToDateLong(dto.getTradeDate()));
		tnsLog.setAccSubType(dto.getAccountType());
		tnsLogService.insertTnsLog(tnsLog);
		logger.info(UserLoggerCnst.USER_RECHARGE_MOUDLE,UserLoggerCnst.USER_AR, 
				"交易流水信息,tnsLog=【" + tnsLog.toString() + "】");
	}

}

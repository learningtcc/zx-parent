package com.ink.user.service.impl;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import com.alibaba.fastjson.JSON;
import com.ink.base.log.util.YinkerLogger;
import com.ink.user.api.constants.AtpTnsConstant;
import com.ink.user.api.constants.RespCodeConstant;
import com.ink.user.api.exception.AtpBusinessException;
import com.ink.user.api.model.in.ExperienceGoldGrantInput;
import com.ink.user.api.model.out.RetOutput;
import com.ink.user.api.service.IExperienceGoldGrantService;
import com.ink.user.common.constant.UserLoggerCnst;
import com.ink.user.core.po.AccCust;
import com.ink.user.core.po.ReqLog;
import com.ink.user.core.po.TnsLog;
import com.ink.user.core.po.TnsTxn;
import com.ink.user.core.service.tns.ITnsLogService;
import com.ink.user.service.handle.AccountBusinessHandle;
import com.ink.user.service.handle.FundBusinessHandle;
import com.ink.user.util.DateUtils;

/**
 * @Description: 体验金发放
 * @author wanghao^_^
 * @date 2016年6月13日 上午11:33:21
 * @version V1.0
 */
public class ExperienceGoldGrantServiceImpl extends AbstractAccServiceImpl<RetOutput, ExperienceGoldGrantInput> implements IExperienceGoldGrantService{
	
	@Autowired
	private ITnsLogService tnsLogService;
	@Autowired
	private FundBusinessHandle fundBusinessHandle;
	@Autowired
	private AccountBusinessHandle accountBusinessHandle;
	private static YinkerLogger logger = YinkerLogger.getLogger(ExperienceGoldGrantServiceImpl.class);
	
	@Override
	public RetOutput exec(ExperienceGoldGrantInput dto) throws Exception {
		logger.info(UserLoggerCnst.USER_ACCOUNT_MOUDLE,UserLoggerCnst.USER_EGG, 
				"进入体验金发放接口.....ExperienceGoldGrant#exec()方法,dto=【" + dto.toString() + "】");
		RetOutput ret = doExec(dto);
		logger.info(UserLoggerCnst.USER_ACCOUNT_MOUDLE,UserLoggerCnst.USER_EGG, ret.toString());
		return ret;
	}

	public RetOutput doExec(ExperienceGoldGrantInput dto) throws Exception {
		TnsLog tnsLog = new TnsLog();
		RetOutput ret = new RetOutput();
		try {
			String json = JSON.toJSONString(dto);
			Map<String, Object> map = check(AtpTnsConstant.ACC_EGG, json);
			insertTnsLog(tnsLog, null, json, (TnsTxn) map.get("tnsTxn"));
			map.put("tnsLog", tnsLog);
			map.put("upItemId", "224104");
			map.put("aceGroup", AtpTnsConstant.ACC_30110_0001);
			map.put("subType", tnsLog.getAccSubType());
			AccCust accCust = (AccCust) map.get("accCust");
			accountBusinessHandle.createAccIfNotExist(accCust, tnsLog.getAccSubType(), "");
			fundBusinessHandle.experienceGoldGrant(map);
			tnsLog.setUid(accCust.getUid());// 更新uid平台唯一编号
			ret.setRetCode(RespCodeConstant.RespCode_000000);
			ret.setRetMsg(RespCodeConstant.RespCode_000000Desc);
		} catch (AtpBusinessException e) {
			logger.error(UserLoggerCnst.USER_ACCOUNT_MOUDLE,UserLoggerCnst.USER_EGG, 
					"ExperienceGoldGrant体验金发放出现异常！"+e.getMessage(), e, dto.getOrdId());
			ret.setRetCode(e.getCode());
			ret.setRetMsg(e.getMessage());
		} catch (Exception e) {
			logger.error(UserLoggerCnst.USER_ACCOUNT_MOUDLE,UserLoggerCnst.USER_EGG, 
					"ExperienceGoldGrant体验金发放出现异常！"+e.getMessage(), e, dto.getOrdId());
			ret.setRetCode(RespCodeConstant.RespCode_200000);
			ret.setRetMsg(RespCodeConstant.RespCode_200000Desc);
		} finally {
			if (tnsLog.getId() != null) {
				tnsLog.setRetCode(ret.getRetCode());
				tnsLog.setRetMsg(ret.getRetMsg());
				tnsLogService.updateTnsLog(tnsLog);
				ret.setAccTnsId(tnsLog.getId().toString());
			}
			ret.setTradeDate(dto.getTradeDate());
		}
		
		return ret;
	}


	@Override
	public void insertTnsLog(TnsLog tnsLog, ReqLog reqLog, String json, TnsTxn tnsTxn)
			throws Exception {
		ExperienceGoldGrantInput dto = JSON.parseObject(json, ExperienceGoldGrantInput.class);
		// 数据拷贝
		BeanCopier copier = BeanCopier.create(ExperienceGoldGrantInput.class, TnsLog.class,
				false);
		copier.copy(dto, tnsLog, null);
		BeanCopier tsnTxncopier = BeanCopier.create(TnsTxn.class, TnsLog.class,
				false);
		tsnTxncopier.copy(tnsTxn, tnsLog, null);
		tnsLog.setAmt(new BigDecimal(dto.getAmt()));
		tnsLog.setMchId(Long.parseLong(dto.getMchId()));
		tnsLog.setOrdDate(DateUtils.strToDateLong(dto.getTradeDate()));
		tnsLog.setAccSubType(dto.getAccountType());
		tnsLogService.insertTnsLog(tnsLog);
		logger.info(UserLoggerCnst.USER_ACCOUNT_MOUDLE,UserLoggerCnst.USER_EGG, "交易流水信息,tnsLog=【" + tnsLog.toString() + "】");
	}
}

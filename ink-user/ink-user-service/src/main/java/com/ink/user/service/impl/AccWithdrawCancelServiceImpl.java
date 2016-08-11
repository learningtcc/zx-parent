package com.ink.user.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import com.alibaba.fastjson.JSON;
import com.ink.base.log.util.YinkerLogger;
import com.ink.user.api.constants.AtpTnsConstant;
import com.ink.user.api.constants.RespCodeConstant;
import com.ink.user.api.exception.AtpBusinessException;
import com.ink.user.api.model.in.AccWithdrawCancelInput;
import com.ink.user.api.model.out.RetOutput;
import com.ink.user.api.service.IAccWithdrawCancelService;
import com.ink.user.common.constant.UserLoggerCnst;
import com.ink.user.core.po.AccCust;
import com.ink.user.core.po.ReqLog;
import com.ink.user.core.po.TnsAuth;
import com.ink.user.core.po.TnsLog;
import com.ink.user.core.po.TnsTxn;
import com.ink.user.core.service.tns.ITnsAuthService;
import com.ink.user.core.service.tns.ITnsLogService;
import com.ink.user.service.handle.FundBusinessHandle;
import com.ink.user.util.DateUtils;

/**
 * @Description: 提现撤回交易(线上只能撤销提现申请交易)
 * @author wanghao
 * @date 2016年5月24日 下午5:21:35
 */
public class AccWithdrawCancelServiceImpl extends AbstractAccServiceImpl<RetOutput, AccWithdrawCancelInput> implements IAccWithdrawCancelService{
	@Autowired
	private ITnsLogService tnsLogService;
	@Autowired
	private FundBusinessHandle fundBusinessHandle;
	@Autowired
	private ITnsAuthService tnsAuthService;
	private static YinkerLogger logger = YinkerLogger.getLogger(AccWithdrawCancelServiceImpl.class);
	
	@Override
	public RetOutput exec(AccWithdrawCancelInput dto) throws Exception {
		logger.info(UserLoggerCnst.USER_WITHDRAW_MOUDLE,UserLoggerCnst.USER_AWC, 
				"进入提现撤回交易接口.....ACC_AWC#exec()方法,dto=【" + dto.toString() + "】");
		RetOutput ret = doExec(dto);
		logger.info(UserLoggerCnst.USER_WITHDRAW_MOUDLE,UserLoggerCnst.USER_AWC, ret.toString());
		return ret;
	}
	public void onMessage(String msg) throws Exception {
		try{
			logger.info(UserLoggerCnst.USER_RECHARGE_MOUDLE, "MQ提现撤回交易接口，" + msg);
			if(msg == null){
				logger.error(UserLoggerCnst.USER_WITHDRAW_MOUDLE, "进入提现撤回交易接口.....ACC39080#exec()， msg 为空");
			}
			AccWithdrawCancelInput dto = JSON.parseObject(msg,AccWithdrawCancelInput.class);
			RetOutput ret = doExec(dto);
			logger.info(UserLoggerCnst.USER_RECHARGE_MOUDLE,
					UserLoggerCnst.USER_AWC ,ret.toString());
		}catch(Exception e){
			logger.error(UserLoggerCnst.USER_WITHDRAW_MOUDLE, "进入提现撤回交易接口.....ACC39080#exec()， msg 解析出错");
		}
	}

	public RetOutput doExec(AccWithdrawCancelInput dto) throws Exception {
		RetOutput ret = new RetOutput();
		TnsLog tnsLog = new TnsLog();
		try {
			String json = JSON.toJSONString(dto);
			Map<String, Object> map = check(AtpTnsConstant.ACC_AWC, json);
			insertTnsLog(tnsLog, null, json, (TnsTxn) map.get("tnsTxn"));
			
			TnsLog oriTnsLog = (TnsLog) map.get("oriTnsLog");
			AccCust accCust = (AccCust) map.get("accCust");
			// 6.更加原订单id查询授权信息
			TnsAuth tnsAuth = tnsAuthService.checkTnsAuth(Long.valueOf(oriTnsLog.getId()));
			if (tnsAuth.getAuthAmt().compareTo(tnsAuth.getAuthBal()) != 0) {
				logger.info(UserLoggerCnst.USER_WITHDRAW_MOUDLE,UserLoggerCnst.USER_AWC, 
						"授权金额和授权余额不一致！");
				throw new AtpBusinessException(RespCodeConstant.RespCode_200027,
						RespCodeConstant.RespCode_200027Desc);
			}
			
			map.put("code", tnsLog.getTxnCode());
			map.put("subType", oriTnsLog.getAccSubType());
			// 解冻金额
			fundBusinessHandle.freezeThaw(map);
			// 更新授权信息
			tnsAuthService.updateTnsAuthByrevFlg(tnsAuth.getTnsLogId(),
					tnsAuth.getVersion());
			// 报文返回信息
			tnsLog.setUid(accCust.getUid());
			ret.setRetCode(RespCodeConstant.RespCode_000000);
			ret.setRetMsg(RespCodeConstant.RespCode_000000Desc);
		} catch (AtpBusinessException e) {
			logger.error(UserLoggerCnst.USER_WITHDRAW_MOUDLE,UserLoggerCnst.USER_AWC, 
					"ACC_AWC提现撤回业务出现异常！"+e.getMessage(), e, dto.getOrdId());
			ret.setRetCode(e.getCode());
			ret.setRetMsg(e.getMessage());
		} catch (Exception e) {
			logger.error(UserLoggerCnst.USER_WITHDRAW_MOUDLE,UserLoggerCnst.USER_AWC, 
					"ACC_AWC提现撤回业务出现异常！", e, dto.getOrdId());
			ret.setRetCode(RespCodeConstant.RespCode_200000);
			ret.setRetMsg(RespCodeConstant.RespCode_200000Desc);
		}
		return ret;
	}


	@Override
	public void insertTnsLog(TnsLog tnsLog, ReqLog reqLog, String json, TnsTxn tnsTxn)
			throws Exception {
		AccWithdrawCancelInput dto = JSON.parseObject(json, AccWithdrawCancelInput.class);
		// 数据拷贝
		BeanCopier copier = BeanCopier.create(AccWithdrawCancelInput.class, TnsLog.class,
				false);
		copier.copy(dto, tnsLog, null);
		BeanCopier tsnTxncopier = BeanCopier.create(TnsTxn.class, TnsLog.class,
				false);
		tsnTxncopier.copy(tnsTxn, tnsLog, null);
		tnsLog.setMchId(Long.parseLong(dto.getMchId()));
		tnsLog.setOrdDate(DateUtils.strToDateLong(dto.getTradeDate()));
		tnsLog.setOriOrdDate(DateUtils.strToDateLong(dto.getOriTradeDate()));
		tnsLogService.insertTnsLog(tnsLog);
	}
}

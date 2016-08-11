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
import com.ink.user.api.model.in.AccWithdrawAcceptInput;
import com.ink.user.api.model.out.RetOutput;
import com.ink.user.api.service.IAccWithdrawAcceptService;
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
 * @Description: 提现受理接口
 * @author wanghao^_^
 * @date 2016年6月12日 下午7:37:08
 * @version V1.0
 */
public class AccWithdrawAcceptServiceImpl extends AbstractAccServiceImpl<RetOutput, AccWithdrawAcceptInput> implements IAccWithdrawAcceptService{

	@Autowired
	private ITnsAuthService tnsAuthService;
	@Autowired
	private ITnsLogService tnsLogService;
	@Autowired
	private FundBusinessHandle fundBusinessHandle;
	private static YinkerLogger logger = YinkerLogger.getLogger(AccWithdrawAcceptServiceImpl.class);
	
	@Override
	public RetOutput exec(AccWithdrawAcceptInput dto) throws Exception {
		logger.info(UserLoggerCnst.USER_WITHDRAW_MOUDLE,UserLoggerCnst.USER_AWA, 
				"AccWithdrawAccept提现受理接口.....AccWithdrawAccept#exec()方法,dto=【" + dto.toString() + "】");
		RetOutput ret = doExec(dto);
		logger.info(UserLoggerCnst.USER_WITHDRAW_MOUDLE,UserLoggerCnst.USER_AWA,ret.toString());
		return ret;
	}

	public void onMessage(String msg) throws Exception {
		try{
			logger.info(UserLoggerCnst.USER_RECHARGE_MOUDLE, "MQ进入活期提现受理 交易接口，" + msg);
			if(msg == null){
				logger.error(UserLoggerCnst.USER_WITHDRAW_MOUDLE, "AccWithdrawAcceptMQ提现受理接口.....AccWithdrawAccept#exec()， msg 为空");
			}
			AccWithdrawAcceptInput dto = JSON.parseObject(msg,AccWithdrawAcceptInput.class);
			RetOutput ret = doExec(dto);
			logger.info(UserLoggerCnst.USER_RECHARGE_MOUDLE,
					UserLoggerCnst.USER_AWA ,ret.toString());
		}catch(Exception e){
			logger.error(UserLoggerCnst.USER_WITHDRAW_MOUDLE, "AccWithdrawAcceptMQ提现受理接口.....AccWithdrawAccept#exec()， msg 解析出错");
		}
	}
	
	public RetOutput doExec(AccWithdrawAcceptInput dto) throws Exception {
		RetOutput ret = new RetOutput();
		TnsLog tnsLog = new TnsLog();
		try {
			String json = JSON.toJSONString(dto);
			// 检查
			Map<String, Object> map = check(AtpTnsConstant.ACC_AWA, json);

			insertTnsLog(tnsLog, null, json, (TnsTxn) map.get("tnsTxn"));
			TnsLog oriTnsLog = (TnsLog) map.get("oriTnsLog");
			AccCust accCust = (AccCust) map.get("accCust");
			
			// 检查授权信息，检查原冻结流水是否存在并且状态为进行中（是否已提现）
			TnsAuth tnsAuth = tnsAuthService.checkTnsAuth(Long
					.valueOf(oriTnsLog.getId()));
			// 解冻交易金额
			map.put("tnsLog", tnsLog);
			map.put("upItemId", AtpTnsConstant.ITEM_220201);
			map.put("code", tnsLog.getTxnCode());
			map.put("subType", oriTnsLog.getAccSubType());

			fundBusinessHandle.withdrawAccepted(map);
			
			// 更新授权信息
			// 根据报文金额和提现申请金额进行比较，bal为0则更新授权状态
			BigDecimal bal = tnsLog.getAmt().subtract(oriTnsLog.getAmt());
			tnsAuthService.updateTnsAuth(bal, Long.valueOf(oriTnsLog.getId()),
					tnsAuth.getVersion());
			tnsLog.setCustId(oriTnsLog.getCustId());
			tnsLog.setUid(accCust.getUid());
			ret.setRetCode(RespCodeConstant.RespCode_000000);
			ret.setRetMsg(RespCodeConstant.RespCode_000000Desc);
		} catch (AtpBusinessException e) {
			logger.error(UserLoggerCnst.USER_WITHDRAW_MOUDLE,
					UserLoggerCnst.USER_AWA, "AccWithdrawAccept提现受理系统出现异常！"+e.getMessage(), e,
					dto.getOrdId());
			ret.setRetCode(e.getCode());
			ret.setRetMsg(e.getMessage());
		} catch (Exception e) {
			logger.error(UserLoggerCnst.USER_WITHDRAW_MOUDLE,
					UserLoggerCnst.USER_AWA, "AccWithdrawAccept提现受理系统出现异常！"+e.getMessage(), e,
					dto.getOrdId());
			ret.setRetCode(RespCodeConstant.RespCode_200000);
			ret.setRetMsg(RespCodeConstant.RespCode_200000Desc);
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
		AccWithdrawAcceptInput dto = JSON.parseObject(json, AccWithdrawAcceptInput.class);
		// 数据拷贝
		BeanCopier copier = BeanCopier.create(AccWithdrawAcceptInput.class,
				TnsLog.class, false);
		copier.copy(dto, tnsLog, null);
		BeanCopier tsnTxncopier = BeanCopier.create(TnsTxn.class, TnsLog.class,
				false);
		tsnTxncopier.copy(tnsTxn, tnsLog, null);
		tnsLog.setAmt(new BigDecimal(dto.getAmt()));
		tnsLog.setCustFee(new BigDecimal(dto.getCustFee()));
		tnsLog.setMchId(Long.parseLong(dto.getMchId()));
		tnsLog.setOrgId(dto.getChannelId());
		tnsLog.setOrdDate(DateUtils.strToDateLong(dto.getTradeDate()));
		tnsLog.setOriOrdDate(DateUtils.strToDateLong(dto.getOriTradeDate()));
		tnsLogService.insertTnsLog(tnsLog);
		logger.info(UserLoggerCnst.USER_WITHDRAW_MOUDLE,
				UserLoggerCnst.USER_AWA,
				"交易流水信息,tnsLog=【" + tnsLog.toString() + "】");
	}

}

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
import com.ink.user.api.model.in.AccWithdrawFreezeInput;
import com.ink.user.api.model.out.AccWithdrawFreezeOutput;
import com.ink.user.api.service.IAccWithdrawFreezeService;
import com.ink.user.common.constant.UserLoggerCnst;
import com.ink.user.core.po.AccCust;
import com.ink.user.core.po.ReqLog;
import com.ink.user.core.po.TnsLog;
import com.ink.user.core.po.TnsTxn;
import com.ink.user.core.service.tns.ITnsAuthService;
import com.ink.user.core.service.tns.ITnsLogService;
import com.ink.user.service.handle.FundBusinessHandle;
import com.ink.user.util.DateUtils;

/**
 * @Description: 账户提现冻结
 * @author wanghao ^_^
 * @date 2016年6月12日 下午6:22:16
 * @version V1.0
 */
public class AccWithdrawFreezeServiceImpl extends AbstractAccServiceImpl<AccWithdrawFreezeOutput, AccWithdrawFreezeInput> implements IAccWithdrawFreezeService{

	@Autowired
	private ITnsAuthService tnsAuthService;
	@Autowired
	private ITnsLogService tnsLogService;
	@Autowired
	private FundBusinessHandle fundBusinessHandle;
	private static YinkerLogger logger = YinkerLogger.getLogger(AccWithdrawFreezeServiceImpl.class);
	
	@Override
	public AccWithdrawFreezeOutput exec(AccWithdrawFreezeInput dto) throws Exception {
		logger.info(UserLoggerCnst.USER_WITHDRAW_MOUDLE,UserLoggerCnst.USER_AWF, 
				"AccWithdrawFreeze提现冻结接口...AccWithdrawFreeze#exec()方法,参数为dto=【"
				+ dto.toString() + "】");
		AccWithdrawFreezeOutput ret = doExec(dto);
		logger.info(UserLoggerCnst.USER_WITHDRAW_MOUDLE,UserLoggerCnst.USER_AWF, ret.toString());
		return ret;
	}
	
	public AccWithdrawFreezeOutput doExec(AccWithdrawFreezeInput dto) throws Exception {
		
		AccWithdrawFreezeOutput ret = new AccWithdrawFreezeOutput();
		TnsLog tnsLog = new TnsLog();
		try {
			String json = JSON.toJSONString(dto);
			// 检查
			Map<String, Object> map = check(AtpTnsConstant.ACC_AWF, json);
			
			insertTnsLog(tnsLog, null, json, (TnsTxn) map.get("tnsTxn"));

			// 4.提现金额冻结操作
			map.put("oriTnsLog", tnsLog);
			map.put("subType", tnsLog.getAccSubType());
			map.put("code", tnsLog.getTxnCode());
			fundBusinessHandle.freezeThaw(map);
			//返回冻结金额
			ret.setAmt(tnsLog.getAmt().toString());
			ret.setCustFee(tnsLog.getCustFee().toString());
			AccCust accCust = (AccCust) map.get("accCust");
			tnsLog.setUid(accCust.getUid());
			// 插入授权登记表信息
			tnsAuthService.insertTnsAuth(tnsLog, accCust);
			ret.setRetCode(RespCodeConstant.RespCode_000000);
			ret.setRetMsg(RespCodeConstant.RespCode_000000Desc);
		} catch (AtpBusinessException e) {
			logger.error(UserLoggerCnst.USER_WITHDRAW_MOUDLE,UserLoggerCnst.USER_AWF, 
					"AccWithdrawFreeze提现冻结异常！"+e.getMessage(), e, dto.getOrdId());
			ret.setRetCode(e.getCode());
			ret.setRetMsg(e.getMessage());
		} catch (Exception e) {
			logger.error(UserLoggerCnst.USER_WITHDRAW_MOUDLE,UserLoggerCnst.USER_AWF, 
					"AccWithdrawFreeze提现冻结异常！"+e.getMessage(), e, dto.getOrdId());
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
		AccWithdrawFreezeInput dto = JSON.parseObject(json, AccWithdrawFreezeInput.class);
		// 数据拷贝
		BeanCopier copier = BeanCopier.create(AccWithdrawFreezeInput.class, TnsLog.class,
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
		logger.info(UserLoggerCnst.USER_WITHDRAW_MOUDLE,UserLoggerCnst.USER_AWF, 
				"交易流水信息,tnsLog=【" + tnsLog.toString() + "】");
	}

}

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
import com.ink.user.api.model.in.MchAcountTransferInput;
import com.ink.user.api.model.out.RetOutput;
import com.ink.user.api.service.IMchAcountTransferService;
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
 * @Description: 商户账户转账
 * @author wanghao ^_^
 * @date 2016年6月13日 下午4:06:18
 * @version V1.0
 */
public class MchAcountTransferServiceImpl extends AbstractAccServiceImpl<RetOutput, MchAcountTransferInput> implements IMchAcountTransferService{
	@Autowired
	private ITnsLogService tnsLogService;
	@Autowired
	private FundBusinessHandle fundBusinessHandle;
	@Autowired
	private AccountBusinessHandle accountBusinessHandle;
	private static YinkerLogger logger = YinkerLogger.getLogger(MchAcountTransferServiceImpl.class);

	@Override
	public RetOutput exec(MchAcountTransferInput dto) throws Exception {
		logger.info(UserLoggerCnst.USER_TRANSFERS_MOUDLE,
				UserLoggerCnst.USER_MAT,
				"进入商户转账交易接口.....MchAcountTransfer#exec()方法,dto=【" + dto.toString() + "】");
		RetOutput ret = doExec(dto);
		logger.info(UserLoggerCnst.USER_TRANSFERS_MOUDLE,
				UserLoggerCnst.USER_MAT,ret.toString());
		return ret;
	}
	
	public RetOutput doExec(MchAcountTransferInput dto) throws Exception {
		RetOutput ret = new RetOutput();
		TnsLog tnsLog = new TnsLog();
		try {
			String json = JSON.toJSONString(dto);
			Map<String, Object> map = check(AtpTnsConstant.ACC_MAT, json);
			insertTnsLog(tnsLog, null, json, (TnsTxn) map.get("tnsTxn"));
			map.put("tnsLog", tnsLog);
			AccCust accCust = (AccCust) map.get("accCust");
			// 检查客户账户
			accountBusinessHandle.createAccIfNotExist(accCust, tnsLog.getAgaSubType(), "");
			// 进行转账操作
			fundBusinessHandle.mchTransferAccount(map);
			ret.setRetCode(RespCodeConstant.RespCode_000000);
			ret.setRetMsg(RespCodeConstant.RespCode_000000Desc);
		} catch (AtpBusinessException e) {
			logger.error(UserLoggerCnst.USER_TRANSFERS_MOUDLE,
					UserLoggerCnst.USER_MAT, "MchAcountTransfer商户转账出现异常！"+e.getMessage(), e, dto.getOrdId());
			ret.setRetCode(e.getCode());
			ret.setRetMsg(e.getMessage());
		} catch (Exception e) {
			logger.error(UserLoggerCnst.USER_TRANSFERS_MOUDLE,
					UserLoggerCnst.USER_MAT, "MchAcountTransfer商户转账出现异常！"+e.getMessage(), e, dto.getOrdId());
			ret.setRetCode(RespCodeConstant.RespCode_200000);
			ret.setRetMsg("MchAcountTransfer商户转账" + RespCodeConstant.RespCode_200000Desc);
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
		BeanCopier tsnTxncopier = BeanCopier.create(TnsTxn.class, TnsLog.class,
				false);
		tsnTxncopier.copy(tnsTxn, tnsLog, null);
		
		MchAcountTransferInput dto = JSON.parseObject(json, MchAcountTransferInput.class);
		// 数据拷贝
		BeanCopier copier = BeanCopier
				.create(MchAcountTransferInput.class, TnsLog.class, false);
		copier.copy(dto, tnsLog, null);
		
		tnsLog.setAmt(new BigDecimal(dto.getAmt()));
		tnsLog.setMchId(Long.parseLong(dto.getMchId()));
		tnsLog.setOrdDate(DateUtils.strToDateLong(dto.getTradeDate()));
		tnsLog.setAccSubType(dto.getMchAccountType());
		tnsLog.setAgaSubType(dto.getCustAccountType());
		tnsLog.setDir(dto.getDir());
		tnsLogService.insertTnsLog(tnsLog);

	}
}

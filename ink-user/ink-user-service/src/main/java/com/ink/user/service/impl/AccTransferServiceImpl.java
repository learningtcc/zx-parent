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
import com.ink.user.api.model.in.AccTransferInput;
import com.ink.user.api.model.out.RetOutput;
import com.ink.user.api.service.IAccTransferService;
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
 * @Description: 转账接口
 * @author wanghao^_^
 * @date 2016年6月13日 上午10:47:47
 * @version V1.0
 */
public class AccTransferServiceImpl extends AbstractAccServiceImpl<RetOutput, AccTransferInput> implements IAccTransferService{

	@Autowired
	private ITnsLogService tnsLogService;
	@Autowired
	private FundBusinessHandle fundBusinessHandle;
	@Autowired
	private AccountBusinessHandle accountBusinessHandle;
	
	private static YinkerLogger logger = YinkerLogger.getLogger(AccTransferServiceImpl.class);
	@Override
	public RetOutput exec(AccTransferInput dto) throws Exception {
		logger.info(UserLoggerCnst.USER_TRANSFERS_MOUDLE,UserLoggerCnst.USER_AT,
				"进入AccTransfer转账接口.....AccTransfer#exec()方法,dto=【" + dto.toString()+ "】");
		RetOutput ret = doExec(dto);
		logger.info(UserLoggerCnst.USER_TRANSFERS_MOUDLE,UserLoggerCnst.USER_AT,ret.toString());
		return ret;
	}

	public RetOutput doExec(AccTransferInput dto) throws Exception {
		// 公用同一个
		RetOutput ret = new RetOutput();
		TnsLog tnsLog = new TnsLog();
		try {
			
			String json = JSON.toJSONString(dto);
			Map<String, Object> map = check(AtpTnsConstant.ACC_AT, json);
			insertTnsLog(tnsLog, null, json, (TnsTxn) map.get("tnsTxn"));
			
			// 7.用户与机构间资金划转
			map.put("tnsLog", tnsLog);
			AccCust accCust = (AccCust) map.get("accCust");
			map.put("agaCust", accCust);
			tnsLog.setUid(accCust.getUid());// 更新uid平台唯一编号
			AccCust agaCust = (AccCust) map.get("agaCust");
			// 检查转入账户，如果不存在就开户
			accountBusinessHandle.createAccIfNotExist(agaCust, tnsLog.getAgaSubType(), "");
			// 进行转账操作
			fundBusinessHandle.transferAccount(map);
			ret.setRetCode(RespCodeConstant.RespCode_000000);
			ret.setRetMsg(RespCodeConstant.RespCode_000000Desc);
		} catch (AtpBusinessException e) {
			logger.error(UserLoggerCnst.USER_TRANSFERS_MOUDLE,UserLoggerCnst.USER_AT,
					"AccTransfer转账接口出现异常！"+e.getMessage(),
					e, dto.getOrdId());
			ret.setRetCode(e.getCode());
			ret.setRetMsg(e.getMessage());
		} catch (Exception e) {
			logger.error(UserLoggerCnst.USER_TRANSFERS_MOUDLE,UserLoggerCnst.USER_AT,"AccTransfer内部转账出现异常！"+e.getMessage(),
					e, dto.getOrdId());
			ret.setRetCode(RespCodeConstant.RespCode_200000);
			ret.setRetMsg("AccTransfer转账接口出现异常" + RespCodeConstant.RespCode_200000Desc);
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
		AccTransferInput dto = JSON.parseObject(json, AccTransferInput.class);
		// 数据拷贝
		BeanCopier copier = BeanCopier
				.create(AccTransferInput.class, TnsLog.class, false);
		copier.copy(dto, tnsLog, null);
		BeanCopier tsnTxncopier = BeanCopier.create(TnsTxn.class, TnsLog.class,
				false);
		tsnTxncopier.copy(tnsTxn, tnsLog, null);
		tnsLog.setAmt(new BigDecimal(dto.getAmt()));
		tnsLog.setMchId(Long.parseLong(dto.getMchId()));
		tnsLog.setCustFee(new BigDecimal(dto.getCustFee()));
		tnsLog.setOrdDate(DateUtils.strToDateLong(dto.getTradeDate()));
		tnsLog.setAccSubType(dto.getAccountType());
		tnsLog.setAgaSubType(dto.getAgaSacType());
		tnsLogService.insertTnsLog(tnsLog);
	}
}

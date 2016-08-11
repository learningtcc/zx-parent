package com.ink.user.service.impl;

import java.util.Map;

import org.springframework.cglib.beans.BeanCopier;

import com.alibaba.fastjson.JSON;
import com.ink.base.log.util.YinkerLogger;
import com.ink.user.api.constants.AtpTnsConstant;
import com.ink.user.api.constants.RespCodeConstant;
import com.ink.user.api.exception.AtpBusinessException;
import com.ink.user.api.model.in.CheckCustInput;
import com.ink.user.api.model.out.CheckCustOutput;
import com.ink.user.api.service.ICheckCustService;
import com.ink.user.common.constant.UserLoggerCnst;
import com.ink.user.core.po.AccCust;
import com.ink.user.core.po.ReqLog;
import com.ink.user.core.po.TnsLog;
import com.ink.user.core.po.TnsTxn;

/**
 * 用户资料查询
 * @author yangchen
 * @date 2016年4月19日 下午3:19:31
 */
public class CheckCustServiceImpl extends AbstractAccServiceImpl<CheckCustOutput, CheckCustInput> implements ICheckCustService {

	private static YinkerLogger logger = YinkerLogger.getLogger(CheckCustServiceImpl.class);

	@Override
	public CheckCustOutput exec(CheckCustInput dto) throws Exception {
		logger.info(UserLoggerCnst.USER_ACCOUNT_MOUDLE,UserLoggerCnst.USER_CC, 
				"CheckCust用户合法性检查接口.....CheckCust#exec()方法,dto=【" + dto.toString() + "】");
		CheckCustOutput ret = doExec(dto);
		logger.info(UserLoggerCnst.USER_ACCOUNT_MOUDLE,UserLoggerCnst.USER_CC, 
				"CheckCust用户查询结果.....客户信息=" + ret.toString());
		return ret;
	}
	
	public CheckCustOutput doExec(CheckCustInput dto) throws Exception {
		CheckCustOutput ret = new CheckCustOutput();
		try {
			String json = JSON.toJSONString(dto);
			// 检查商户,客户是否存在,存在放入Map中
			Map<String, Object> map = check(AtpTnsConstant.ACC_CC, json);
			AccCust accCust = (AccCust) map.get("accCust");
			ret.setOrdId(dto.getOrdId());
			ret.setTxnCode(dto.getTxnCode());
			ret.setRetCode(RespCodeConstant.RespCode_000000);
			ret.setRetMsg(RespCodeConstant.RespCode_000000Desc);
			
			// 复制
			BeanCopier copier = BeanCopier.create(AccCust.class,
					CheckCustOutput.class, false);
			copier.copy(accCust, ret, null);
			logger.info(UserLoggerCnst.USER_ACCOUNT_MOUDLE,UserLoggerCnst.USER_CC, 
					"CheckCust交易查询结果.....客户信息=" + ret.toString());
		} catch (AtpBusinessException e) {
			logger.error(UserLoggerCnst.USER_ACCOUNT_MOUDLE,UserLoggerCnst.USER_CC, 
					"CheckCust用户合法性检查出现异常！"+e.getMessage(), e, dto.getOrdId());
			ret.setRetCode(e.getCode());
			ret.setRetMsg(e.getMessage());
		} catch (Exception e) {
			logger.error(UserLoggerCnst.USER_ACCOUNT_MOUDLE,UserLoggerCnst.USER_CC, 
					"CheckCust用户合法性检查出现异常！"+e.getMessage(), e, dto.getOrdId());
			ret.setRetCode(RespCodeConstant.RespCode_200000);
			ret.setRetMsg(RespCodeConstant.RespCode_200000Desc);
		} 
		return ret;
	}

	@Override
	public void insertTnsLog(TnsLog tnsLog, ReqLog reqLog, String json, TnsTxn tnsTxn)
			throws Exception {

	}

}

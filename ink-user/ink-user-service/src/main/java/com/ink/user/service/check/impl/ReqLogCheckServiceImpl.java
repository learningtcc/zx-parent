package com.ink.user.service.check.impl;

import java.util.Map;

import com.ink.user.service.check.api.Checker;
import com.ink.user.service.check.bean.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.user.api.constants.RespCodeConstant;
import com.ink.user.api.exception.AtpBusinessException;
import com.ink.user.core.po.ReqLog;
import com.ink.user.core.po.TnsTxn;
import com.ink.user.core.service.IReqLogManager;

/**
 * @Description: 金额无关的请求检查
 * @author wanghao
 * @date 2016年5月27日 上午11:24:26
 */
@Service("reqLogCheckService")
public class ReqLogCheckServiceImpl extends AbstractCheck implements Checker {
	@Autowired
	private IReqLogManager reqLogManager;
	@Override
	public Map<String, Object> check(String txnCode, Map<String, String> dtoMap, Map<String, Object> map)
			throws Exception {
		String ordId = dtoMap.get("ordId");
		String mchId = dtoMap.get("mchId");
		// 检查流水一定在检查交易之后
		TnsTxn tnsTxn = (TnsTxn) map.get(Constants.TnsTxn);
		ReqLog reqLog = reqLogManager.checkReqLog(ordId,tnsTxn.getTxnCode(), mchId);
		if (null != reqLog) {
			logger.info("交易流水重复, ordId : {}", ordId);
			throw new AtpBusinessException(RespCodeConstant.RespCode_000008,
					RespCodeConstant.RespCode_000008Desc);
		}
		return putMap(map, Constants.ReqLog, reqLog);
	}

}

package com.ink.user.service.check.impl;

import java.util.Map;

import com.ink.user.service.check.bean.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.user.api.constants.RespCodeConstant;
import com.ink.user.api.exception.AtpBusinessException;
import com.ink.user.core.po.TnsLog;
import com.ink.user.core.po.TnsTxn;
import com.ink.user.core.service.tns.ITnsLogService;
import com.ink.user.service.check.api.Checker;

/**
 * 交易日志检查
 * @author yangchen
 * @date 2016年5月24日 下午4:58:13
 */
@Service("tnsLogCheckService")
public class TnsLogCheckServiceImpl extends AbstractCheck implements Checker{
	@Autowired
	private ITnsLogService tnsLogService;
	@Override
	public Map<String, Object> check(String txnCode, Map<String, String> dtoMap, Map<String, Object> map)
			throws Exception {
		String ordId = dtoMap.get("ordId");
		String mchId = dtoMap.get("mchId");
		// 检查流水一定在检查交易之后
		TnsTxn tnsTxn = (TnsTxn) map.get(Constants.TnsTxn);
		TnsLog tnsLog = tnsLogService.checkTnsLog(ordId,tnsTxn.getTxnCode(), mchId);
		if (null != tnsLog) {
			logger.info("交易流水重复, ordId : {}", ordId);
			throw new AtpBusinessException(RespCodeConstant.RespCode_000008,
					RespCodeConstant.RespCode_000008Desc + ", ordId : " + ordId);
		}
		return putMap(map, Constants.TnsLog, tnsLog);
	}

}

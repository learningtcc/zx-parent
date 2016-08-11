package com.ink.user.service.check.impl;

import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import com.ink.user.api.constants.RespCodeConstant;
import com.ink.user.api.exception.AtpBusinessException;
import com.ink.user.service.check.api.Checker;

@Service("tradeRequestCheckService")
public class TradeRequestCheckServiceImpl extends AbstractCheck implements Checker{

	@Override
	public Map<String, Object> check(String txnCode, Map<String, String> dtoMap, Map<String, Object> map)
			throws Exception {
		String mac =  dtoMap.get("mac");
		String ordId = dtoMap.get("ordId");
		if (!(DigestUtils.md5Hex("com.ink.trade!user"+ordId)).equals(mac)) {
			logger.error("错误的请求来源！");
			throw new AtpBusinessException(RespCodeConstant.RespCode_000006,
					RespCodeConstant.RespCode_000006Desc);
		}
		return map;
	}

}

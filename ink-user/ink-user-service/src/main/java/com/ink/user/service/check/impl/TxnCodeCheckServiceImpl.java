package com.ink.user.service.check.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.ink.user.api.constants.RespCodeConstant;
import com.ink.user.api.exception.AtpBusinessException;
import com.ink.user.service.check.api.Checker;

/**
 * 交易代码是否匹配的检查
 * @author yangchen
 * @date 2016年5月24日 下午5:00:04
 */
@Service("txnCodeCheckService")
public class TxnCodeCheckServiceImpl extends AbstractCheck implements Checker{

	@Override
	public Map<String, Object> check(String txnCode, Map<String, String> dtoMap,
			Map<String, Object> map) throws Exception {
		String dtoTxnCode =  dtoMap.get("txnCode");
		if (!txnCode.equals(dtoTxnCode)) {
			logger.error("报文类型格式不正确！");
			throw new AtpBusinessException(RespCodeConstant.RespCode_000002,
					RespCodeConstant.RespCode_000002Desc);
		}
		return map;
	}

}

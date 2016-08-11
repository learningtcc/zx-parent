package com.ink.user.service.check.impl;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ink.user.api.constants.RespCodeConstant;
import com.ink.user.api.exception.AtpBusinessException;
import com.ink.user.service.check.api.Checker;

/**
 * 金额校验
 * @author yangchen
 * @date 2016年5月24日 下午4:57:29
 */
@Service("amtCheckService")
public class AmtCheckServiceImpl extends AbstractCheck implements Checker {

	@Override
	public Map<String, Object> check(String txnCode, Map<String, String> dtoMap,
			Map<String, Object> map) throws Exception {
		BigDecimal amt = new BigDecimal(dtoMap.get("amt"));
		if (amt.compareTo(BigDecimal.ZERO) == 0) {// 校验金额是否为0
			logger.error("交易金额为0");
			throw new AtpBusinessException(RespCodeConstant.RespCode_200026,
					RespCodeConstant.RespCode_200026Desc
					+ "交易流水号 ："+ dtoMap.get("ordId")
					+ "amt : " + amt);
		}
		return putMap(map, "amt", amt);
	}

}

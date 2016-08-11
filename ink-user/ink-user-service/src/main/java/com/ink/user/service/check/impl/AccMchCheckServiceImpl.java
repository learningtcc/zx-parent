package com.ink.user.service.check.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.user.api.constants.RespCodeConstant;
import com.ink.user.api.exception.AtpBusinessException;
import com.ink.user.core.dao.IAccMchDao;
import com.ink.user.core.po.AccMch;
import com.ink.user.service.check.api.Checker;
import com.ink.user.service.check.bean.Constants;

/**
 * 商户检查
 * @author yangchen
 * @date 2016年5月24日 下午4:56:59
 */
@Service("accMchCheckService")
public class AccMchCheckServiceImpl extends AbstractCheck implements Checker{
	
	@Autowired
	private IAccMchDao accMchDao;
	@Override
	public Map<String, Object> check(String txnCode, Map<String, String> dtoMap, Map<String, Object> map) throws Exception{
		Long mchId = Long.valueOf( dtoMap.get("mchId"));
		AccMch accMch = accMchDao.checkAccMch(mchId);
		if (accMch == null) {
			logger.error("根据商户Id查询商户信息为空！mchId=【" + mchId + "】");
			throw new AtpBusinessException(RespCodeConstant.RespCode_200014,
					RespCodeConstant.RespCode_200014Desc
					+ "");
		}
		return putMap(map, Constants.AccMch, accMch);
	}

}

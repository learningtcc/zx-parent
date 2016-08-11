package com.ink.user.service.check.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.user.api.constants.RespCodeConstant;
import com.ink.user.api.exception.AtpBusinessException;
import com.ink.user.core.dao.IAccCustDao;
import com.ink.user.core.po.AccCust;
import com.ink.user.core.po.AccMch;
import com.ink.user.service.check.api.Checker;
import com.ink.user.service.check.bean.Constants;

/**
 * 客户是否已经存在的验证
 * @author yangchen
 * @date 2016年5月24日 下午4:53:23
 */
@Service("accCustExistCheckService")
public class AccCustExistCheckServiceImpl extends AbstractCheck implements Checker{

	@Autowired
	private IAccCustDao accCustDao;
	@Override
	public Map<String, Object> check(String txnCode,
			Map<String, String> dtoMap, Map<String, Object> map)
			throws Exception {
		String custId = dtoMap.get("custId");
		// 检查客户一定在检查商户之后，所以可以从map中拿到accMch
		AccMch accMch = (AccMch) map.get(Constants.AccMch);
		AccCust accCust = accCustDao.existCheckAccCust(accMch.getMchId(), custId);
		if (null != accCust) {
			logger.error("客户已存在！custId=【" + custId + "】");
			throw new AtpBusinessException(RespCodeConstant.RespCode_300007,
					RespCodeConstant.RespCode_300007Desc + ", custId : " + custId);
		}
		return  map;
	}

}

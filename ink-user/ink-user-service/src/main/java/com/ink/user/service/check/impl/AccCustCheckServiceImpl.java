package com.ink.user.service.check.impl;

import java.util.Map;

import com.ink.user.service.check.api.Checker;
import com.ink.user.service.check.bean.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.user.core.dao.IAccCustDao;
import com.ink.user.core.po.AccCust;
import com.ink.user.core.po.AccMch;

/**
 * 用户信息验证
 * @author yangchen
 * @date 2016年5月24日 下午4:52:58
 */
@Service("accCustCheckService")
public class AccCustCheckServiceImpl extends AbstractCheck implements Checker {
	
	@Autowired
	private IAccCustDao accCustDao;
	@Override
	public Map<String, Object> check(String txnCode, Map<String, String> dtoMap, Map<String, Object> map) throws Exception{
		String custId = dtoMap.get("custId");
		// 检查客户一定在检查商户之后，所以可以从map中拿到accMch
		AccMch accMch = (AccMch) map.get(Constants.AccMch);
		AccCust accCust = accCustDao.checkAccCust(accMch.getMchId(), custId);
		return putMap(map, Constants.AccCust, accCust);
	}


}

package com.ink.user.service.check.impl;

import java.util.Map;

import com.ink.user.service.check.api.Checker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.user.core.dao.IAccCardDao;

/**
 * 用户绑卡信息验证
 * @author yangchen
 * @date 2016年5月24日 下午4:52:33
 */
@Service("accCardCheckService")
public class AccCardCheckServiceImpl extends AbstractCheck implements Checker {

	@Autowired
	private IAccCardDao accCardDao;
	@Override
	public Map<String, Object> check(String txnCode, Map<String, String> dtoMap, Map<String, Object> map)
			throws Exception {
//		String cardNo = dtoMap.get("cardNo");
//		AccCust accCust = (AccCust) map.get(Constants.AccCust);
//		AccCard accCard = accCardDao.checkAccCardIsExist(accCust.getUid(), cardNo);
//		if (accCard == null) {
//			logger.error("客户未绑定该卡，custId : {}, cardNo : {}", accCust.getCustId()
//					, cardNo);
//			throw new AtpBusinessException(RespCode.RespCode_400008,
//					RespCode.RespCode_400008Desc);
//		}
//		return putMap(map, Constants.AccCard, accCard);
		return map;
	}

}

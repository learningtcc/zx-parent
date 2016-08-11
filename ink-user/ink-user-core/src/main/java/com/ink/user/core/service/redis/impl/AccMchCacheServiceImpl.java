package com.ink.user.core.service.redis.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ink.user.core.dao.IAccMchDao;
import com.ink.user.core.po.AccMch;
import com.ink.user.core.service.redis.AccMchCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

@Service("accMchCacheService")
public class AccMchCacheServiceImpl  extends BaseCacheServiceImpl implements AccMchCacheService {

//	private static final YinkerLoger logger = YinkerLoger.getLogger(AccMchCacheServiceImpl.class);
	@Autowired
	private IAccMchDao accMchDao;
	private static final String AccMchCacheKey = "AccMchCacheHashMap";
	@Override
	public AccMch getAccMchCacheByMchId(Long mchId) {
		Object obj = getCache(AccMchCacheKey, String.valueOf(mchId), AccMch.class);
		if(obj == null){
			return null;
		}
		AccMch accMch = (AccMch) obj;
//		logger.info(UserLoggerCnst.USER_ACCOUNT_MOUDLE,"从缓存中返回商户信息结构, accMch : "+accMch.toString());
		return accMch;
	}

	@Override
	public void setAccMchCache(AccMch accMch) {
//		logger.info(UserLoggerCnst.USER_ACCOUNT_MOUDLE,"设置商户缓存信息， accMch :"+accMch.toString());
		setCache( AccMchCacheKey, String.valueOf(accMch.getMchId()), accMch);
	}

	@Override
	public void removeAccMchCache(Long mchId) {
//		logger.info(UserLoggerCnst.USER_ACCOUNT_MOUDLE,"移除商户缓存信息，mchId : "+mchId);
		removeCache(AccMchCacheKey, String.valueOf(mchId));
	}

	@Override
	protected Map<String, String> readFromDataBase() {
		Map<String, String> map = new HashMap<String, String>();
		List<AccMch> list = accMchDao.selectAll();
		for(AccMch accMch : list){
			map.put(String.valueOf(accMch.getMchId()), JSON.toJSONString(accMch));
		}
		return map;
	}

}

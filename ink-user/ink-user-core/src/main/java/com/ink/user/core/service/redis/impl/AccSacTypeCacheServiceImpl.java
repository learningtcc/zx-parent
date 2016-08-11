package com.ink.user.core.service.redis.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ink.user.core.dao.IAccSacTypeDao;
import com.ink.user.core.service.redis.AccSacTypeCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.ink.user.core.po.AccSacType;

@Service("accSacTypeCacheService")
public class AccSacTypeCacheServiceImpl extends BaseCacheServiceImpl implements AccSacTypeCacheService {

	@Autowired
	private IAccSacTypeDao accSacTypeDao;
	
	private static final String AccSacTypeCacheKey = "AccSacTypeCacheKey";
	@Override
	public AccSacType getAccSacTypeCache(String sacType) {
		Object obj = getCache(AccSacTypeCacheKey, sacType, AccSacType.class);
		if(obj == null){
			return null;
		}
		AccSacType accSacType = (AccSacType) obj;
		
//		log.info(UserLoggerCnst.USER_ACCOUNT_MOUDLE,"从缓存中返回账户类型信息, accSacType : "+accSacType);
		return accSacType;
	}

	@Override
	public void setAccSacTypeCache(AccSacType accSacType) {
//		log.info(UserLoggerCnst.USER_ACCOUNT_MOUDLE,"设置账户类型信息缓存， accSacType : "+accSacType);
		setCache( AccSacTypeCacheKey, accSacType.getSacType(), accSacType);
	}

	@Override
	public void removeAccSacTypeCache(String sacType) {
//		log.info(UserLoggerCnst.USER_ACCOUNT_MOUDLE,"移除账户类型缓存信息，accSacType : "+sacType);
		removeCache(AccSacTypeCacheKey, sacType);
	}

	@Override
	protected Map<String, String> readFromDataBase() {
		Map<String, String> map = new HashMap<String, String>();
		AccSacType record = new AccSacType();
		record.setStatus(1);
		List<AccSacType> list = accSacTypeDao.findAccSacTypeAll(record);
		for(AccSacType acc : list){
			map.put(acc.getSacType(), JSON.toJSONString(acc));
		}
		return map;
	}

}

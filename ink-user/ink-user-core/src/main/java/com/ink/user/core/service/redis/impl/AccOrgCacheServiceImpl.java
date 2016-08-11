//package com.ink.user.core.service.redis.impl;
//
//import org.springframework.stereotype.Service;
//
//import com.ink.user.core.po.AccOrg;
//import com.ink.user.core.service.redis.AccOrgCacheService;
//@Service("accOrgCacheService")
//public class AccOrgCacheServiceImpl  extends BaseCacheServiceImpl implements AccOrgCacheService {
//
////	private static final YinkerLoger logger = YinkerLoger.getLogger(AccOrgCacheServiceImpl.class);
//	private static final String AccOrgCacheKey = "AccOrgCacheHashMap";
//	@Override
//	public AccOrg getAccOrgCacheByOrgId(Long orgId) {
//		Object obj = getCache(AccOrgCacheKey, String.valueOf(orgId), AccOrg.class);
//		if(obj == null){
//			return null;
//		}
//		AccOrg accOrg = (AccOrg) obj;
////		logger.info(UserLoggerCnst.USER_ACCOUNT_MOUDLE, "从缓存中返回渠道信息结构, accOrg : "+accOrg.toString());
//		return accOrg;
//	}
//
//	@Override
//	public void setAccOrgCache(AccOrg accOrg) {
////		logger.info(UserLoggerCnst.USER_ACCOUNT_MOUDLE, "设置渠道缓存信息， accOrg : "+accOrg.toString());
//		setCache( AccOrgCacheKey, String.valueOf(accOrg.getOrgId()), accOrg);
//	}
//
//	@Override
//	public void removeAccOrgCache(Long orgId) {
////		logger.info(UserLoggerCnst.USER_ACCOUNT_MOUDLE, "移除渠道缓存信息， orgId : "+orgId);
//		removeCache(AccOrgCacheKey, String.valueOf(orgId));
//	}
//
//}

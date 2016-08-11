//package com.ink.user.core.service.redis.impl;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//
//import com.ink.user.core.po.AccRsakey;
//import com.ink.user.core.service.redis.AccRsaKeyCacheService;
//
//@Service("accRsaKeyCacheService")
//public class AccRsaKeyCacheServiceImpl extends BaseCacheServiceImpl implements AccRsaKeyCacheService {
//	private static final Logger logger = LoggerFactory.getLogger(AccRsaKeyCacheServiceImpl.class);
//
//	private static final String AccRsakeyCacheKey = "AccRsakeyHashMap";
//	@Override
//	public void setAccRsakeyCache(AccRsakey accRsakey) {
//		logger.info("设置商户秘钥缓存信息， accRsakey : {}", accRsakey);
//		setCache(AccRsakeyCacheKey, String.valueOf(accRsakey.getMchid()), accRsakey);
//	}
//
//	@Override
//	public AccRsakey getAccRsakeyCacheByMchId(Long mchId) {
//		Object obj = getCache(AccRsakeyCacheKey, String.valueOf(mchId), AccRsakey.class);
//		if(obj == null){
//			return null;
//		}
//		AccRsakey accRsakey = (AccRsakey) obj;
//		logger.info("从缓存中返回商户秘钥信息结果, accRsaKey : {}", accRsakey);
//		return accRsakey;
//	}
//
//	@Override
//	public void removeAccRsaKeyCache(Long mchId) {
//		logger.info("移除商户秘钥信息缓存, mchId");
//		removeCache(AccRsakeyCacheKey, String.valueOf(mchId));
//	}
//
//}

//package com.ink.user.core.service.redis.impl;
//
//import java.util.List;
//
//import org.springframework.stereotype.Service;
//
//import TnsAceGen;
//import TnsAceGenCacheService;
//
//@Service("tnsAceGenCacheService")
//public class TnsAceGenCacheServiceImpl  extends BaseCacheServiceImpl implements TnsAceGenCacheService {
////	private static final YinkerLoger logger = YinkerLoger.getLogger(TnsAceGenCacheServiceImpl.class);
//	private static final String TnsTnxGenCacheKey = "TnsTnxGenCacheHashMap";
//	@Override
//	public List<TnsAceGen> getTnsAceGenFromCache(String txnCode, String aceGroup) {
//		Object obj = getCache(TnsTnxGenCacheKey, txnCode + aceGroup,
//				TnsAceGen.class, true);
//		if(obj == null){
//			return null;
//		}
//		@SuppressWarnings("unchecked")
//		List<TnsAceGen> list = (List<TnsAceGen>) obj;
////		logger.info(UserLoggerCnst.USER_ACCOUNT_MOUDLE,"从缓存中返回会计分录配置信息， list ： "+list);
//		return list;
//	}
//
//	@Override
//	public void setTnsAceGenList(String txnCode, String aceGroup,
//			List<TnsAceGen> list) {
////		logger.info(UserLoggerCnst.USER_ACCOUNT_MOUDLE,"设置会计分录配置，list ："+list);
//		setCache( TnsTnxGenCacheKey, txnCode + aceGroup, list);
//	}
//
//	@Override
//	public void removeTnsAceGenList(String txnCode, String aceGroup) {
////		logger.info("移除会计分录配置缓存，txnCode : {}， aceGroup : {}", txnCode, aceGroup);
//		removeCache(TnsTnxGenCacheKey, txnCode + aceGroup);
//	}
//	
//	
//
//}

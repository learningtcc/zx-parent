//package com.ink.user.core.service.redis.impl;
//
//import java.util.Calendar;
//import java.util.Date;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.alibaba.fastjson.JSON;
//import com.ink.base.log.util.YinkerLogger;
//import UserLoggerCnst;
//import AccPac;
//import com.ink.user.core.service.redis.AccLimitCacheService;
//import BaseRedis;
//
///**
// * 账户控制缓存的实现
// * @author yangchen
// * @date 2016年5月17日 下午3:20:03
// */
//@Service("accLimitCacheService")
//public class AccLimitCacheServiceImpl implements AccLimitCacheService{
//	
//	private static final YinkerLogger logger = YinkerLogger.getLogger(AccLimitCacheServiceImpl.class);
//	@Autowired
//	private BaseRedis baseRedis;
//	
//	private static final String AccSacKeyFlag = "accSac_";
//	
//	private static final String AccPacKeyFlag = "accPac_";
//	
//	@Override
//	public AccPac getAccPacByPacId(String pacId) {
//		try{
//		Object json = baseRedis.get(AccSacKeyFlag + pacId);
//			if(json != null){
//				return JSON.parseObject((String)json, AccPac.class);
//			}
//		}catch(Exception e){
//			logger.error(UserLoggerCnst.USER_ACCOUNT_MOUDLE, "从redis获取主账户控制信息失败" + pacId, e);
//		}
//		return null;
//	}
//
//	@Override
//	public void setAccPac(AccPac accPac) {
//		if(accPac != null){
//			try{
//				baseRedis.setex(AccPacKeyFlag + accPac.getPacId(),
//						JSON.toJSONString(accPac), getExpireTime());
//			}catch(Exception e){
//				logger.error(UserLoggerCnst.USER_ACCOUNT_MOUDLE, "设置主账户控制信息到redis失败" + accPac, e);
//			}
//		}
//	}
//	
//	/**
//	 * 返回到今天0点的过期时间，以秒为单位
//	 * @return
//	 */
//	private int getExpireTime(){
//		Date startTime = new Date();
//		
//		Calendar cal = Calendar.getInstance();
//		cal.add(Calendar.DATE, 1);
//		cal.set(Calendar.HOUR_OF_DAY, 0);
//		cal.set(Calendar.MINUTE, 0);
//		cal.set(Calendar.SECOND, 0);
//		cal.set(Calendar.MILLISECOND, 0);
//		Date endTime = cal.getTime();
//		
//		long diff = endTime.getTime() - startTime.getTime();
//		
//		long ns = 1000;// 一秒钟的毫秒数
//		long sec = diff / ns;// 计算差多少秒
//		return (int) sec;
//	}
//	
////	@Override
////	public AccSac getAccSacBySacId(String sacId) {
////		try{
////			Object json = baseRedis.get(AccSacKeyFlag + sacId);
////			if(json != null){
////				return JSON.parseObject((String)json, AccSac.class);
////			}
////		}catch(Exception e){
////			logger.error(UserLoggerCnst.USER_ACCOUNT_MOUDLE, "从redis获取子账户控制信息失败" + sacId, e);
////		}
////		return null;
////	}
////	@Override
////	public void setAccSac(AccSac accSac) {
////		if(accSac != null){
////			try{
////				baseRedis.setex(AccPacKeyFlag + accSac.getSacId(),
////						JSON.toJSONString(accSac), getExpireTime());
////			}catch(Exception e){
////				logger.error(UserLoggerCnst.USER_ACCOUNT_MOUDLE, "设置子账户控制信息到redis失败" + accSac, e);
////			}
////		}
////	}
//
//}

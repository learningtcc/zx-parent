//package com.ink.user.core.service.redis.impl;
//
//import java.math.BigDecimal;
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.ink.user.core.model.redis.AccIacRedisBean;
//import com.ink.user.core.po.acc.AccIac;
//import com.ink.user.core.service.redis.AccIacJobRedisService;
//import BaseRedis;
//import com.ink.user.tns.TnsAce;
//
//@Service("accIacJobRedisService")
//public class AccIacJobRedisServiceImpl implements AccIacJobRedisService{
//
//	private static final Logger logger = LoggerFactory.getLogger(AccIacJobRedisServiceImpl.class);
//	
//	@Autowired
//	private BaseRedis baseRedis;
//	private static String key = "idKey";
//	private static final String flag = "accIac:";
//	private String redisKey = RedisKey.getInstance().getValue(key);
//	
//	@Override
//	public boolean pushIacToRedisList(AccIac accIac, BigDecimal amt, TnsAce tnsAce)
//			throws Exception {
//		String redisChinnal = getRedisChinnal( tnsAce.getTxnCode());
//		Long ret = -1L;
//		try {
//			AccIacRedisBean accIacRedisBean = new AccIacRedisBean();
//			accIacRedisBean.setAccIac(accIac);
//			accIacRedisBean.setAmt(amt);
//			accIacRedisBean.setTnsAce(tnsAce);
//			if (accIacRedisBean != null) {
//				ret = baseRedis.rpush(redisChinnal, accIacRedisBean);
//			}
//			if (ret == -1L || ret == -1) {
//				return false;
//			} else {
//				return true;
//			}
//		} catch (Exception e) {
//			logger.error("存入" + redisChinnal +"通道异常！", e);
//			throw e;
//		}
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<AccIacRedisBean> getAccIacRedisBeanListByTxnCode(String txnCode) throws Exception{
//		String redisChinnal = getRedisChinnal( txnCode);
//		try{
//			return baseRedis.lrange(redisChinnal, 0, -1);
//		}catch(Exception e){
//			logger.error("从redis取出accIacRedisBean队列出错,redisChinnal : " + redisChinnal, e);
//			throw e;
//		}
//		
//	}
//
//	@Override
//	public void popAccIacRedisBeanList(String txnCode) throws Exception{
//		String redisChinnal = getRedisChinnal( txnCode);
//		try{
//			baseRedis.rpop(redisChinnal);
//		}catch(Exception e){
//			logger.error("移除队头元素出错，redisChinnal : " + redisChinnal, e);
//			throw e;
//		}
//	}
//
//	@Override
//	public boolean pushIacToRedisList(AccIacRedisBean accIacRedisBean)
//			throws Exception {
//		return pushIacToRedisList(accIacRedisBean.getAccIac(),
//				accIacRedisBean.getAmt(),
//				accIacRedisBean.getTnsAce());
//	}
//
//	@Override
//	public void removeAccIacRedisBeanList(String txnCode) throws Exception {
//		baseRedis.del(getRedisChinnal( txnCode));
//	}
//	
//	private String getRedisChinnal(String txnCode) {
//		return flag + txnCode + redisKey;
//	}
//
//}

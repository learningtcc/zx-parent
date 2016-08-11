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
//import AccAccRedisBean;
//import com.ink.user.core.po.acc.AccAcc;
//import AccAccJobRedisService;
//import BaseRedis;
//import com.ink.user.tns.TnsAce;
//
//@Service("accAccJobRedisService")
//public class AccAccJobRedisServiceImpl implements AccAccJobRedisService{
//
//	private static final Logger logger = LoggerFactory.getLogger(AccAccJobRedisServiceImpl.class);
//	
//	@Autowired
//	private BaseRedis baseRedis;
//	private static String key = "idKey";
//	private static final String flag = "accAcc:";
//	
//	private String redisKey = RedisKey.getInstance().getValue(key);
//	
//	@Override
//	public boolean pushAccToRedisList(AccAcc accAcc, BigDecimal amt, TnsAce tnsAce)
//			throws Exception {
//		String redisChinnal = getRedisChinnal(tnsAce.getTxnCode());
//		Long ret = -1L;
//		try {
//			AccAccRedisBean accAccRedisBean = new AccAccRedisBean();
//			accAccRedisBean.setAccAcc(accAcc);
//			accAccRedisBean.setAmt(amt);
//			accAccRedisBean.setTnsAce(tnsAce);
//			if (accAccRedisBean != null) {
//				ret = baseRedis.rpush(redisChinnal, accAccRedisBean);
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
//	public List<AccAccRedisBean> getAccAccRedisBeanListByTxnCode(String txnCode) throws Exception{
//		String redisChinnal = getRedisChinnal(txnCode);
//		try{
//			return baseRedis.lrange(redisChinnal, 0, -1);
//		}catch(Exception e){
//			logger.error("从redis取出accAccRedisBean队列出错,redisChinnal : " + redisChinnal, e);
//			throw e;
//		}
//		
//	}
//
//	@Override
//	public void popAccAccRedisBeanList(String txnCode) throws Exception{
//		String redisChinnal = getRedisChinnal(txnCode);
//		try{
//			baseRedis.rpop(redisChinnal);
//		}catch(Exception e){
//			logger.error("移除队头元素出错，redisChinnal : " + redisChinnal, e);
//			throw e;
//		}
//	}
//
//	@Override
//	public boolean pushAccToRedisList(AccAccRedisBean accAccRedisBean)
//			throws Exception {
//		return pushAccToRedisList(accAccRedisBean.getAccAcc(),
//				accAccRedisBean.getAmt(),
//				accAccRedisBean.getTnsAce());
//	}
//
//	@Override
//	public void removeAccAccRedisBeanList(String txnCode) throws Exception {
//		baseRedis.del(getRedisChinnal(txnCode));
//	}
//
//	private String getRedisChinnal(String txnCode) {
//		return flag + txnCode + redisKey;
//	}
//}

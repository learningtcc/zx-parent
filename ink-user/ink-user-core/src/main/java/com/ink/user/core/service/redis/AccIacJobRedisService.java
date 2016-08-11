//package com.ink.user.core.service.redis;
//
//import java.math.BigDecimal;
//import java.util.List;
//
//import com.ink.user.core.model.redis.AccIacRedisBean;
//import com.ink.user.core.po.AccIac;
//import TnsAce;
//
///**
// * accIac批量更新缓存服务
// * @author yangchen
// * @date 2016年1月29日 上午10:56:17
// */
//public interface AccIacJobRedisService {
//	
//	public boolean pushIacToRedisList(AccIac accIac, BigDecimal amt, TnsAce tnsAce) throws Exception ;
//
//	public boolean pushIacToRedisList(AccIacRedisBean accIacRedisBean) throws Exception ;
//
//	public List<AccIacRedisBean> getAccIacRedisBeanListByTxnCode(String txnCode) throws Exception;
//	
//	public void popAccIacRedisBeanList(String txnCode) throws Exception;
//	
//	public void removeAccIacRedisBeanList(String txnCode) throws Exception;
//}

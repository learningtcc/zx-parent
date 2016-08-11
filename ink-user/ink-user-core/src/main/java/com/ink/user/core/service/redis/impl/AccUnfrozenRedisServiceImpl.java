//package com.ink.user.core.service.redis.impl;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.alibaba.fastjson.JSON;
//import com.ink.base.redis.client.Yedis;
//import com.ink.base.redis.support.DataFrom;
//import TnsLog;
//import com.ink.user.core.service.redis.AccUnfrozenRedisService;
//import BaseRedis;
//
//@Service("accUnfrozenRedisService")
//public class AccUnfrozenRedisServiceImpl implements AccUnfrozenRedisService{
//	private Logger logger = LoggerFactory.getLogger(AccUnfrozenRedisServiceImpl.class);
//	private static final String AccUnfrozenRedisKey = "accUnfrozenRedisKey";
//	@Autowired
//	private Yedis yedis;
//	@Override
//	public boolean pushTnsLogToRedisMap(TnsLog tnsLog) throws Exception {
//		if(yedis.exists(AccUnfrozenRedisKey)){
//			try{
//				yedis.hset(AccUnfrozenRedisKey, tnsLog.getOrdId(), JSON.toJSONString(tnsLog),DataFrom.DB);
//			}catch(Exception e){
//				return handelException(e, tnsLog);
//			}
//		}else{
//			synchronized (this) {
//				try{
//					Map<String, String> map = new HashMap<String, String>();
//					map.put(tnsLog.getOrdId(), JSON.toJSONString(tnsLog));
//					yedis.hmset(AccUnfrozenRedisKey, map, DataFrom.DB);
//				}catch(Exception e ){
//					return handelException(e, tnsLog);
//				}
//			}
//		}
//		return true;
//	}
//
//	private boolean handelException(Exception e, TnsLog tnsLog) {
//		logger.error("设置tnsLog到redis待解冻队列失败, tnsLog : {}" + tnsLog, e);
//		return false;
//	}
//
//	@Override
//	public List<TnsLog> getAllTnsLogFromRedisMap() throws Exception {
//		try{
//			Map<String, String> map = yedis.hgetAll(AccUnfrozenRedisKey, DataFrom.DB);
//			if(map != null){
//				List<TnsLog> tnsLogs = new ArrayList<TnsLog>();
//				for(String value : map.values()){
//					tnsLogs.add(JSON.parseObject(value, TnsLog.class));
//				}
//				return tnsLogs;
//			}else{
//				return null;
//			}
//		}catch(Exception e){
//			logger.error("从redis中取出所有待解冻tnsLog失败", e);
//		}
//		return null;
//	}
//
//	@Override
//	public boolean removeTnsLogFromRedisMap(String ordId) throws Exception {
//		synchronized (this) {
//			try{
//				yedis.hdel(AccUnfrozenRedisKey, ordId);
//			}catch(Exception e){
//				logger.error("删除待解冻队列元素失败，ordId : {}", ordId);
//			}
//		}
//		return false;
//	}
//
//}

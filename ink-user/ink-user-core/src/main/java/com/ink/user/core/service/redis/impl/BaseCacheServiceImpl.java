package com.ink.user.core.service.redis.impl;

import java.util.HashMap;
import java.util.Map;

import com.ink.user.common.constant.UserLoggerCnst;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.ink.base.log.util.YinkerLogger;
import com.ink.base.redis.client.Reader;
import com.ink.base.redis.client.Yedis;
import com.ink.base.redis.support.DataFrom;

/**
 * 抽象基础缓存实现
 * @author yangchen
 * @date 2016年1月27日 上午11:17:03
 */
public abstract class BaseCacheServiceImpl {
	private static final YinkerLogger logger = YinkerLogger.getLogger(BaseCacheServiceImpl.class);
	@Autowired
	private Yedis yedis;
	protected void setCache(  String key, String field, Object obj){
		if(yedis.exists(key)){
			try{
				yedis.hset(key, field,
						JSON.toJSONString(obj), DataFrom.DB);
			}catch(Exception e){
				logger.error(UserLoggerCnst.USER_ACCOUNT_MOUDLE,"信息设置到缓存失败" + key,e);
			}
		}else{
			synchronized (this) {
				try{
					Map<String, String> map = new HashMap<String, String>();
					map.put(field, JSON.toJSONString(obj));
					yedis.hmset(key, map, DataFrom.DB);
				}catch(Exception e){
					logger.error(UserLoggerCnst.USER_ACCOUNT_MOUDLE,"将信息设置到缓存失败" + key, e);
				}
			}
		}
	}
	
	protected Object getCache(String key, String field, Class<?> clazz, boolean isArray){
		Object obj = null;
		try {
			String value = yedis.hget(key, field, new Reader<Map<String, String>>() {

				@Override
				public Map<String, String> readerFromDatabase() {
					return readFromDataBase();
				}
			});
			if (value == null || value.trim().equals("")) {
				logger.debug("从缓存中未找到目标字段或者对应的值是空，目标字段field : " + field);
				return null;
			}
			if (isArray) {
				obj = JSON.parseArray(value, clazz);
			} else {
				obj = JSON.parseObject(value, clazz);
			}
		} catch (Exception e) {
			logger.error(UserLoggerCnst.USER_ACCOUNT_MOUDLE, "从缓存中取信息失败" + key, e);
		}
		if (obj != null)
			logger.debug("从缓存中返回信息结果, obj : " + obj);
		else
			logger.debug("从缓存中返回信息结果, obj : null");
		return obj;
	}
	
	protected Object getCache(String key, String field, Class<?> clazz){
		return getCache(key, field, clazz, false);
	}
	
	/**
	 * 移除缓存
	 * @param key
	 * @param field
	 */
	protected void removeCache(String key, String field){
		synchronized (this) {
			try{
				yedis.hdel(key, field);
			}catch(Exception e){
				logger.error(UserLoggerCnst.USER_ACCOUNT_MOUDLE,"删除缓存失败，key : "+key+"，field : {}"+field);
			}
		}
	}

	protected abstract Map<String, String> readFromDataBase();
}

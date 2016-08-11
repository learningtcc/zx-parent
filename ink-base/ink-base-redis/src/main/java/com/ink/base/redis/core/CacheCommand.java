package com.ink.base.redis.core;

import java.util.Map;

import org.springframework.beans.factory.DisposableBean;

import com.ink.base.redis.client.CacheObject;
import com.ink.base.redis.client.Reader;
import com.ink.base.redis.support.DataFrom;

public interface CacheCommand extends DisposableBean{
	
	public CacheObject getObject(String rdk, Class<?> obj,Integer expiredTime, Reader<Object> reader);
	
	public boolean deleteObject(String rdk);
	
	public boolean cacheObject(String rdk, Object obj, Integer expiredTime, DataFrom df);
	
	public long hset(String key, String field, String value, Integer expiredTime,DataFrom data);
	
	public boolean hmset(String key, Map<String, String> map,Integer expiredTime,DataFrom data);
	
	public String hget(String key, String field,Integer expiredTime,Reader<Map<String, String>> reader);
	
	public Map<String, String> hgetAll(String key, Integer expiredTime,Reader<Map<String, String>> reader);
	
	public long hdel(String key, String field);
}
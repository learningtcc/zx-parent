package com.ink.base.redis.core.ehcache;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.exceptions.PersistenceException;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheEventListenerConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;
import org.ehcache.event.EventType;
import org.ehcache.impl.serialization.CompactJavaSerializer;

import com.ink.base.log.util.YinkerLogger;
import com.ink.base.redis.client.CacheObject;
import com.ink.base.redis.client.Reader;
import com.ink.base.redis.client.Yedis;
import com.ink.base.redis.core.CacheCommand;
import com.ink.base.redis.core.db.DbReader;
import com.ink.base.redis.exception.EhcacheRuntimeException;
import com.ink.base.redis.support.CacheEnum;
import com.ink.base.redis.support.DataFrom;
/**
 * ehcache实现
 * @author zongtt
 * 2016年7月7日13:56:23
 */
public class EhcacheCommand implements CacheCommand {
	
	private CacheManager cacheManager;
	private CacheConfiguration<String, Object> configuration;
	private YinkerLogger logger = YinkerLogger.getLogger(EhcacheCommand.class);
	private DbReader dbReader = DbReader.getInstance();
	private Cache<String, Object> cache = null;
	
	public EhcacheCommand() {
		
		CacheEventListenerConfigurationBuilder cacheEventListenerConfiguration = CacheEventListenerConfigurationBuilder
			    .newEventListenerConfiguration(new CacheListener(), EventType.CREATED, EventType.UPDATED,EventType.EVICTED,EventType.EXPIRED,EventType.REMOVED) 
			    .unordered().asynchronous(); 
		
		configuration = CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, Object.class, ResourcePoolsBuilder.heap(512).offheap(128, MemoryUnit.MB).disk(512, MemoryUnit.MB,false))
				.withValueSerializer(new CompactJavaSerializer(ClassLoader.getSystemClassLoader()))/*.add(cacheEventListenerConfiguration)*/.build();
		
//		cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
//				.with(CacheManagerBuilder.persistence("ehcache"))
//				.withCache("local-cache",CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, Object.class,
//		                ResourcePoolsBuilder.newResourcePoolsBuilder().heap(10).offheap(1, MemoryUnit.MB).disk(20, MemoryUnit.MB)))
//				.withSerializer(Object.class, CompactJavaSerializer.class)
//				.build(true);

		cacheManager = CacheManagerBuilder.newCacheManagerBuilder().with(CacheManagerBuilder.persistence("ehcache-"+logger.getLogContext())).withCache("local-cache", configuration).build(true);
		cache = cacheManager.getCache("local-cache", String.class, Object.class);
	}

	@Override
	public CacheObject getObject(String rdk, Class<?> obj, Integer expiredTime, Reader<Object> reader) {
		CacheObject result = null;
		
		try {
			
			Object value = cache.get(rdk);
			
			if(value == null){
				if(reader != null){
					result = dbReader.loadAndSetCache4Ehcache(rdk, cache, obj,expiredTime, reader);
				}
			}else {
				if(dbReader.containLock(rdk)){
					//当缓存中已经存在相应的KEY时，清理锁对象，避免累积
					dbReader.removeLock(rdk);
				}
				result = dbReader.convertDataType(rdk,obj, (Map<String, String>)value,expiredTime, null, cache, CacheEnum.LOCAL, reader);
			}
			
		} catch (PersistenceException pe) {
			//数据库异常，
			pe.printStackTrace();
		} catch (RuntimeException jce) {
			jce.printStackTrace();
			result = dbReader.loadAndSetCache4Ehcache(rdk, cache, obj, expiredTime, reader);
		}
		
		return result;
	}

	@Override
	public boolean deleteObject(String rdk) {
		cache.remove(rdk);
		return true;
	}
	
	public void removeCache(){
		cache.clear();
	}
	
	@Override
	public boolean cacheObject(String rdk, Object obj, Integer expiredTime, DataFrom data) {
		if(data.compareTo(DataFrom.DB) > 0){
			//数据来源不来自数据库时，不支持存储
			throw new EhcacheRuntimeException("Ehcache不支持非DB来源的cacheObject()");
		}else {
			Map<String, String> map = dbReader.convertObjectToMap(expiredTime, obj);
			cache.put(rdk, map);
			
			return true;
		}
	}
	
	@Override
	public long hset(String key, String field, String value, Integer expiredTime,DataFrom data) {
		if(data.compareTo(DataFrom.DB) > 0){
			//数据来源不来自数据库时，不支持存储
			throw new EhcacheRuntimeException("Ehcache不支持非DB来源的hset");
		}else {
			int result = -1;
			
			Map<String,String> map = null;

			if(cache.containsKey(key)){
				map = (Map<String, String>)cache.get(key);
				String preValue = map.put(field, value);
				result = StringUtils.isBlank(preValue) ? 1 : 0;
			}else{
 				map = new HashMap<>();
 				map.put(field, value);
 				
 				map = dbReader.convertObjectToMap(expiredTime,map);
				
 				result = 1;
			}
			
			if(map != null){
				cache.put(key, map);
			}
			
			return result;
		}
	}
	
	public boolean hmset(String key, Map<String, String> map, Integer expiredTime, DataFrom data){
		if(data.compareTo(DataFrom.DB) > 0){
			//数据来源不来自数据库时，不支持存储
			throw new EhcacheRuntimeException("Ehcache不支持非DB来源的hmset");
		}else {
			map = dbReader.convertObjectToMap(expiredTime, map);
			cache.put(key, map);
			return true;
		}
	}
	
	public String hget(String key, String field, Integer expiredTime, Reader<Map<String, String>> reader){
		
		Map<String, String> map = null;
		
		if(cache.containsKey(key)){
			if(dbReader.containLock(key)){
				//当缓存中已经存在相应的KEY时，清理锁对象，避免累积
				dbReader.removeLock(key);
			}
			
			CacheObject co = dbReader.convertDataType(key ,Map.class, (Map)cache.get(key),expiredTime, null, cache, CacheEnum.LOCAL, reader);
			map = (Map<String, String>)co.getValue();
			
			return map.get(field);
		}else{
			//缓存无KEY，需查询数据库
			if(reader != null){
				CacheObject result = dbReader.loadAndSetCache4Ehcache(key, cache, Map.class, expiredTime, reader);
				map = (Map<String, String>)result.getValue();
				if(map != null)
					return map.get(field);
			}
		}
		
		return "";
	}
	
	@Override
	public Map<String, String> hgetAll(String key, Integer expiredTime, Reader<Map<String, String>> reader) {
		
		Map<String, String> map = null;
		
		if(cache.containsKey(key)){
			if(dbReader.containLock(key)){
				//当缓存中已经存在相应的KEY时，清理锁对象，避免累积
				dbReader.removeLock(key);
			}
			
			//CacheObject co = dbReader.convertDataType(key ,Map.class, (Map)cache.get(key),expiredTime, null, cache, CacheEnum.LOCAL, reader);
			map = (Map<String, String>)cache.get(key);
			
			String dataType = map.get(Yedis.DATA_TYPE);
			
			if("0".equalsIgnoreCase(dataType)){//假数据
				map = null;
			}else{
				//删除添加的内部数据
				map.remove(Yedis.CACHE_TIME_KEY);
				map.remove(Yedis.DATA_TYPE);
				map.remove(Yedis.EXPIRE_TIME_KEY);
				map.remove(Yedis.LOAD_SERVER);
				map.remove(Yedis.STRING_KEY);
			}
			
		}else{
			//缓存无KEY，需查询数据库
			if(reader != null){
				CacheObject result = dbReader.loadAndSetCache4Ehcache(key, cache, Map.class, expiredTime, reader);
				map = (Map<String, String>)result.getValue();
			}
		}
		
		return map;
	}
	
	public long hdel(String key, String field){
		if(cache.containsKey(key)){
			Map<String, String> map = (Map<String, String>)cache.get(key);
			map.remove(field);
			cache.put(key, map);
			
			return 1;
		}
		
		return -1;
	}

	@Override
	public void destroy() throws Exception {
		cacheManager.close();
	}
	
	class CacheListener implements CacheEventListener<String, Object> {

		@Override
		public void onEvent(CacheEvent<String,Object> event) {
			logger.info(event.getType().name() + "==" + event.getKey());
			
		}
		
	}
}

package com.ink.base.redis.core.db;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.ehcache.Cache;

import com.ink.base.log.util.YinkerLogger;
import com.ink.base.redis.client.CacheObject;
import com.ink.base.redis.client.Reader;
import com.ink.base.redis.client.Yedis;
import com.ink.base.redis.lock.ReadLock;
import com.ink.base.redis.support.CacheEnum;
import com.ink.base.redis.support.NamedThreadFactory;
import com.ink.base.redis.util.CacheTypeUtil;
import com.ink.base.utils.IpUtils;
import com.ink.base.utils.dateUtil.DateUtil;

import redis.clients.jedis.Jedis;

/**
 * 缓存穿透读库处理
 * @author zongtt
 * 2016年7月7日16:00:02
 */
public class DbReader {
	
	//数据读取加锁对象
	private static ConcurrentHashMap<String, ReadLock> lockMap = new ConcurrentHashMap<String,ReadLock>();
	private static YinkerLogger logger = YinkerLogger.getLogger(DbReader.class);
	private static DbReader dbReader = new DbReader();
	
	/**
	 * 过期数据更新线程
	 */
	private static ExecutorService dataExecutor = new ThreadPoolExecutor(0, 100,
            60L, TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(),new NamedThreadFactory("yedis-data-update", true));
	
	public static DbReader getInstance(){
		return dbReader;
	}
	
	private DbReader() {
		ScheduledExecutorService scheduled = Executors.newSingleThreadScheduledExecutor(new NamedThreadFactory("yedis-readerLock-heartbeat", true));
		//锁对象心跳监测,10分钟一次
		scheduled.scheduleWithFixedDelay(new ReadLockHeartBeat(), 1, 10, TimeUnit.MINUTES);
	}

	/**
	 * 从数据库加载并缓存数据
	 * @param rdk 缓存KEY
	 * @param jedis
	 * @param reader 
	 * @return
	 */
	public CacheObject loadAndSetCache4Redis(String rdk, Jedis jedis, Class<?> obj, Integer expiredTime, String exception,Reader reader,boolean expireOn){
		
		Object value = "";
		
		//生成数据库读锁实例
		ReadLock readLock = new ReadLock();
		//将锁实例放入lockMap中，若已存在锁实例，则返回原有实例
		ReadLock mapLock = lockMap.putIfAbsent(rdk, readLock);
		
		readLock = mapLock == null ? readLock : mapLock;
		
		synchronized(readLock){//加锁读取数据库
			
			if(jedis == null || StringUtils.isNotBlank(exception)){
				//避免空指针异常
				value = reader.readerFromDatabase();
				return new CacheObject(value, new Date());
			}
			
			//再次读取Redis，避免已经被其他线程读取过了
			Map<String, String> map = jedis.hgetAll(rdk);
			
			if(!map.isEmpty()){
				if(logger.isDebugEnabled()){
					logger.debug("成功获取数据库读锁，再次从缓存中获取数据KEY: "+ rdk);
				}
				//缓存刚加载，不进行过期策略检测
				value = convertDataType(rdk, obj, map,expiredTime, null,null, null, reader);
				
				return new CacheObject(value, map.get(Yedis.CACHE_TIME_KEY));
			}
			
			if(logger.isDebugEnabled()){
				logger.debug("从数据库加载缓存数据KEY: "+ rdk);
			}
			
			DBObject dbobj = getValueFromDB(expiredTime, reader);
			value = dbobj.getValue();
			
			jedis.hmset(rdk, dbobj.getMap());
			if(expireOn){
				jedis.expire(rdk,expiredTime);
			}
		}
		
		return new CacheObject(value, new Date());
	}
	
	/**
	 * 从数据库加载并缓存数据
	 * @param rdk 缓存KEY
	 * @param jedis
	 * @param reader 
	 * @return
	 */
	public CacheObject loadAndSetCache4Ehcache(String rdk, Cache<String, Object> cache, Class<?> obj, Integer expiredTime, Reader reader){
		
		Object value = "";
		//生成数据库读锁实例
		ReadLock readLock = new ReadLock();
		//将锁实例放入lockMap中，若已存在锁实例，则返回原有实例
		ReadLock mapLock = lockMap.putIfAbsent(rdk, readLock);
		
		readLock = mapLock == null ? readLock : mapLock;
		
		synchronized(readLock){//加锁读取数据库
			
			//再次读取cache，避免已经被其他线程读取过了
			@SuppressWarnings("unchecked")
			Map<String, String> map = (Map<String, String>)cache.get(rdk);
			
			if(map != null && !map.isEmpty()){
				if(logger.isDebugEnabled()){
					logger.debug("成功获取数据库读锁，再次从缓存中获取数据KEY: "+ rdk);
				}
				//缓存刚加载，不进行过期策略检测
				return convertDataType(rdk,obj, map,expiredTime, null,null, null, reader);
			}
			
			if(logger.isDebugEnabled()){
				logger.debug("从数据库加载缓存数据KEY: "+ rdk);
			}
			
			DBObject dbobj = getValueFromDB(expiredTime, reader);
			value = dbobj.getValue();
			cache.put(rdk, dbobj.getMap());
		}
		
		return new CacheObject(value, new Date());
	}
	
	/**
	 * 缓存KEY是否生成锁资源
	 * @param rdk
	 * @return
	 */
	public boolean containLock(String rdk){
		return lockMap.containsKey(rdk);
	}
	
	/**
	 * 删除缓存KEY对应的锁资源
	 * @param rdk
	 * @return
	 */
	public void removeLock(String rdk){
		lockMap.remove(rdk);
	}
	
	/**
	 * 将缓存中MAP转化为实体对象
	 * @param obj
	 * @param map
	 * @return
	 */
	public CacheObject convertDataType(final String rdk, final Class<?> obj, Map<String, String> map,final Integer expireTime, final Jedis jedis, final Cache<String, Object> cache, final CacheEnum ce, final Reader reader) {
		
		Object result = null;
		
		String dataType = map.get(Yedis.DATA_TYPE);
		String cacheTime = map.get(Yedis.CACHE_TIME_KEY);
		
		if("1".equalsIgnoreCase(dataType)){
			if (obj.equals(Map.class)) {
//				result = CacheTypeUtil.convertStringMapToObjectMap(map);
				result = map;
			} else if(obj.equals(String.class)){
				result = map.get(Yedis.STRING_KEY);
			} else {
				result = CacheTypeUtil.convertMapToObject(map, obj);
			}
			
			String expireTimeStr = map.get(Yedis.EXPIRE_TIME_KEY);
			
			if(StringUtils.isNotBlank(expireTimeStr)){
				final Long cacheExpireTime = Long.valueOf(expireTimeStr);
				if(ce != null && reader != null && cacheExpireTime > 0 && cacheExpireTime < System.currentTimeMillis()){
					//数据过期，需重新加载
					dataExecutor.execute(new Runnable() {
						@Override
						public void run() {
							try{
								if(ce.compareTo(CacheEnum.REDIS) == 0){
									updateRedisData(rdk, jedis, expireTime, reader);
								}else if(ce.compareTo(CacheEnum.LOCAL) == 0){
									//本地缓存时暂时屏蔽更新策略，本地缓存时服务器已出现异常，不应加重服务器负担
									//updateEhcacheData(rdk, cache, expireTime, reader);
								}
							}catch(RuntimeException e){
								e.printStackTrace();
							}
						}
					});
				}
			}
		}
			
		return new CacheObject(result, cacheTime);
	}
	
	/**
	 * 更新历史版本数据
	 * @return
	 */
	private void updateRedisData(String rdk, Jedis jedis, Integer expiredTime, Reader<Object> reader){
		
		//生成数据库读锁实例
		ReadLock readLock = new ReadLock();
		//TODO 此处可能存在问题，现针对所有不同的KEY进行加锁，可能存在KEY不同（即ID不同，前缀相同），执行的SQL语句相同，也会允许执行
		//将锁实例放入lockMap中，若已存在锁实例，则返回原有实例
		ReadLock mapLock = lockMap.putIfAbsent(rdk, readLock);
		
		readLock = mapLock == null ? readLock : mapLock;
		
		synchronized(readLock){//加锁读取数据库
			DBObject dbobj = getValueFromDB(expiredTime, reader);
			jedis.hmset(rdk, dbobj.getMap());
		}
	}
	
	/**
	 * 从数据库加载数据
	 * @param rdk 缓存KEY
	 * @param jedis
	 * @param reader 
	 * @return
	 */
	private void updateEhcacheData(String rdk, Cache<String, Object> cache, Integer expiredTime, Reader reader){
		
		//生成数据库读锁实例
		ReadLock readLock = new ReadLock();
		//将锁实例放入lockMap中，若已存在锁实例，则返回原有实例
		ReadLock mapLock = lockMap.putIfAbsent(rdk, readLock);
		
		readLock = mapLock == null ? readLock : mapLock;
		
		synchronized(readLock){//加锁读取数据库
			DBObject dbobj = getValueFromDB(expiredTime, reader);
			cache.put(rdk, dbobj.getMap());
		}
	}

	/**
	 * 将对象转换为存储到缓存中的MAP
	 * @param expiredTime
	 * @param value
	 * @return
	 */
	private DBObject getValueFromDB(Integer expiredTime, Reader reader) {
		
		//读取数据库
		Object value = reader.readerFromDatabase();
		
		Map<String, String> map = convertObjectToMap(expiredTime, value);;
		
		return new DBObject(value, map);
	}
	
	/**
	 * 将缓存对象转换为MAP
	 * @param expiredTime
	 * @param value
	 * @return
	 */
	public Map<String, String> convertObjectToMap(Integer expiredTime, Object value) {
		Map<String, String> map = null;
		
		//放入Redis中
		if(value == null){
			map = new HashMap<>();
		}else{
			if (value instanceof Map) {
				map = (Map<String, String>)value;
				if(map.isEmpty()){//为不改变原value的数值
					map = new HashMap<>();
				}else{
					map = CacheTypeUtil.convertObjectMapToStringMap((Map<String, Object>) value);
				}
			} else if (value instanceof String) {
				map = new HashMap<>();
				map.put(Yedis.STRING_KEY, (String)value);
			} else {
				map = CacheTypeUtil.convertObjToHashMap(value);
			}
		}
		
		if(map.isEmpty()){
			//数据库不存在，设置为假数据
			map.put(Yedis.DATA_TYPE, "0");
		}else{
			//真数据
			map.put(Yedis.DATA_TYPE, "1");
		}
		
		//过期时间
		if(expiredTime == 0 || expiredTime == null){
			map.put(Yedis.EXPIRE_TIME_KEY, "0");
		}else{
			map.put(Yedis.EXPIRE_TIME_KEY, String.valueOf(System.currentTimeMillis() + 1000 * expiredTime));
		}
		
		map.put(Yedis.CACHE_TIME_KEY, DateUtil.formatToYYYYMMDDMMHHSS(new Date()));
		map.put(Yedis.LOAD_SERVER, IpUtils.getLocalAddress());
		return map;
	}
	
	class DBObject {
		
		private Object value;
		private Map<String, String> map;

		public DBObject(Object value, Map<String, String> map) {
			super();
			this.value = value;
			this.map = map;
		}

		public Object getValue() {
			return value;
		}

		public Map<String, String> getMap() {
			return map;
		}
	}
	
	/**
	 * 读锁资源检测心跳
	 * @author YK-DZ-02851604
	 *
	 */
	class ReadLockHeartBeat implements Runnable{

		@Override
		public void run() {
			
			try{
				long now = System.currentTimeMillis();
				
				List<String> keyList = new ArrayList<String>(lockMap.keySet());
				
				if(logger.isDebugEnabled()){
					logger.debug("缓存并发读锁资源数:" + keyList.size());
				}
				
				for (String key : keyList) {
					ReadLock lock = lockMap.get(key);
					
					if(now > lock.getExpireTime()){
						lockMap.remove(key);
						if(logger.isDebugEnabled()){
							logger.debug("缓存并发读锁资源释放KEY: " + key);
						}
					}
				}
			}catch(RuntimeException exception){
				//捕获异常，避免定时调度失败
			}
		}
	}
}

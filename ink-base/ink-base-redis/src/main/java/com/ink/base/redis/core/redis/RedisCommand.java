package com.ink.base.redis.core.redis;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.exceptions.PersistenceException;

import com.ink.base.log.util.YinkerLogger;
import com.ink.base.redis.client.CacheObject;
import com.ink.base.redis.client.Reader;
import com.ink.base.redis.client.Yedis;
import com.ink.base.redis.core.CacheCommand;
import com.ink.base.redis.core.db.DbReader;
import com.ink.base.redis.support.CacheEnum;
import com.ink.base.redis.support.DataFrom;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * Redis服务实现
 * @author zongtt
 * 2016年7月7日13:56:50
 */
public class RedisCommand implements CacheCommand{
	
	private JedisPool jedisPool;
	private YinkerLogger logger = YinkerLogger.getLogger(RedisCommand.class);
	private DbReader dbReader = DbReader.getInstance();
    private String expirePolicy = EXPIRE_EXN;
    public static final String EXPIRE_EXN = "EXN";
    public static final String EXPIRE_EXO = "EXO";

    static {

    }
	
	@Override
	public CacheObject getObject(String rdk, Class<?> obj, Integer expiredTime, Reader<Object> reader) {
		CacheObject result = null;
		Jedis jedis = null;
        boolean expireOn = false;
        if(expirePolicy!=null && expirePolicy.equals(EXPIRE_EXN)){
            expireOn = true;
        }
		
		try {
			jedis = jedisPool.getResource();
			
			Map<String, String> map = jedis.hgetAll(rdk);
			
			if(map.isEmpty()){
				if(reader != null){
					result = dbReader.loadAndSetCache4Redis(rdk, jedis,obj,expiredTime,"",reader,expireOn);
				}
			}else {
				if(dbReader.containLock(rdk)){
					//当缓存中已经存在相应的KEY时，清理锁对象，避免累积
					dbReader.removeLock(rdk);
				}
				result = dbReader.convertDataType(rdk, obj, map ,expiredTime, jedis ,null ,CacheEnum.REDIS, reader);
			}
			
		} catch (PersistenceException pe) {
			//数据库异常，
			pe.printStackTrace();
		} catch (RuntimeException jce) {
			logger.error(jce.getMessage());
			result = dbReader.loadAndSetCache4Redis(rdk, jedis, obj, expiredTime, "RuntimeException",reader,expireOn);
		}finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return result;
	}
	
	@Override
	public boolean deleteObject(String rdk) {
		
		Jedis jedis = null;
		
		try {
			jedis = jedisPool.getResource();
			
			jedis.del(rdk);
			
		} catch (JedisConnectionException jce) {
			return false;
		}finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return true;
	}
	
	@Override
	public boolean cacheObject(String rdk, Object obj, Integer expiredTime, DataFrom df) {
		Jedis jedis = null;
		
		String result = "";
		
		Map<String, String> map = dbReader.convertObjectToMap(expiredTime, obj);
		
		try {
			jedis = jedisPool.getResource();
			result = jedis.hmset(rdk, map);
		} catch (Exception e) {
			logger.error("缓存对象出现异常", e);
			return false;
		} finally {
			if(expirePolicy!=null && expirePolicy.equals(EXPIRE_EXN)){
				jedis.expire(rdk,expiredTime);
			}
			if (jedis != null) {
				jedis.close();
			}
		}
		return "OK".equalsIgnoreCase(result);
	}
	
	@Override
	public long hset(String key, String field, String value, Integer expiredTime,DataFrom data) {
		Jedis jedis = null;
		
		try {
			jedis = jedisPool.getResource();
			if(jedis.exists(key)){
				return jedis.hset(key, field, value);
			}else{
				Map<String, String> map = new HashMap<String,String>();
				map.put(field, value);
				
				Map<String, String> result = dbReader.convertObjectToMap(expiredTime,map);
				jedis.hmset(key, result);
                if(expirePolicy!=null && expirePolicy.equals(EXPIRE_EXN)){
                    jedis.expire(key,expiredTime);
                }
				return 1;
			}
		} catch (Exception e) {
			logger.error("[ERROR] Cache.hset " + e.getLocalizedMessage());
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		
		return -1;
	}
	
	public boolean hmset(String key, Map<String, String> map,Integer expiredTime,DataFrom data){
		Jedis jedis = null;
		
		try {
			jedis = jedisPool.getResource();
			map = dbReader.convertObjectToMap(expiredTime, map);
			return "OK".equalsIgnoreCase(jedis.hmset(key, map));
		} catch (Exception e) {
			logger.error("[ERROR] Cache.hmset " + e.getLocalizedMessage());
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		
		return false;
	}
	
	public String hget(String key, String field, Integer expiredTime, Reader<Map<String, String>> reader){
        boolean expireOn = false;
        if(expirePolicy!=null && expirePolicy.equals(EXPIRE_EXN)){
            expireOn = true;
        }
		Jedis jedis = null;
		
		try {
			jedis = jedisPool.getResource();
			
			Map<String, String> map = jedis.hgetAll(key);
			
			if(map == null || map.isEmpty()){
				//缓存无KEY，需查询数据库
				if(reader != null){
					CacheObject result = dbReader.loadAndSetCache4Redis(key, jedis, Map.class, expiredTime, "", reader,expireOn);
					map = (Map<String, String>)result.getValue();
					if(map != null)
						return map.get(field);
				}
			}else{
				if(dbReader.containLock(key)){
					//当缓存中已经存在相应的KEY时，清理锁对象，避免累积
					dbReader.removeLock(key);
				}
				
				return map.get(field);
			}
		} catch (Exception e) {
			logger.error("[ERROR] Cache.hget " + e.getLocalizedMessage());
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}

		return "";
	}
	
	@Override
	public Map<String, String> hgetAll(String key, Integer expiredTime, Reader<Map<String, String>> reader) {
        boolean expireOn = false;
        if(expirePolicy!=null && expirePolicy.equals(EXPIRE_EXN)){
            expireOn = true;
        }

		Jedis jedis = null;
		
		try {
			jedis = jedisPool.getResource();
			
			Map<String, String> map = jedis.hgetAll(key);
			
			if(map == null || map.isEmpty()){
				//缓存无KEY，需查询数据库
				if(reader != null){
					CacheObject result = dbReader.loadAndSetCache4Redis(key, jedis, Map.class, expiredTime, "", reader,expireOn);
					map = (Map<String, String>)result.getValue();
					
					return map;
				}
			}else{
				if(dbReader.containLock(key)){
					//当缓存中已经存在相应的KEY时，清理锁对象，避免累积
					dbReader.removeLock(key);
				}
				
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
				
				return map;
			}
		} catch (Exception e) {
			logger.error("[ERROR] Cache.hget " + e.getLocalizedMessage());
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}

		return null;
	}
	
	public long hdel(String key, String field){
		Jedis jedis = null;
		
		try {
			jedis = jedisPool.getResource();
			return jedis.hdel(key, field);
		} catch (Exception e) {
			logger.error("[ERROR] Cache.zincrby " + e.getLocalizedMessage());
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}

		return -1;
	}
	
	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}

	@Override
	public void destroy() throws Exception {
		jedisPool.close();
	}

    public void setExpirePolicy(String expirePolicy) {
        this.expirePolicy = expirePolicy;
    }
}
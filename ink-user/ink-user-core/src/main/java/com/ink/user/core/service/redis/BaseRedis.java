package com.ink.user.core.service.redis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ink.user.util.ObjectBytesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.util.SafeEncoder;


/**
 * redis缓存基础服务类（提供操作redis的基础服务）
 */
public class BaseRedis {
	
	private static Logger logger = LoggerFactory.getLogger(BaseRedis.class);
	
	private final ObjectBytesUtil objectBytesUtil = new ObjectBytesUtil();
	
	@Autowired
	private ShardedJedisPool shardedJedisPool;
	
	@Autowired
	private JedisPool jedisPool;
	
//	/**
//	 * 缓存对象(新增和修改)
//	 * @param key 缓存服务标志
//	 * @param id 缓存对象Id
//	 * @param object 缓存对象
//	 * @return 成功：true；失败：false
//	 *
//	 */
//	@SuppressWarnings("unchecked")
//	public boolean cacheObject(String key,Object id ,Object object){
//		List<Object> list = new ArrayList<Object>();
//		Jedis jedis = JedisPool.getResource();
//		boolean result = false;
//		try {
//			Transaction trans = jedis.multi();
//			if(object instanceof Map){
//				trans.hmset(key+RedisCacheDef.KEY_RECORD+id,  ConvertUtil.convertObjectMapToStringMap((Map<String, Object>) object));
//			}else{
//				trans.hmset(key+RedisCacheDef.KEY_RECORD+id, ConvertUtil.convertObjToHashMap(object));
//			}
//			list = trans.exec();
//			if(list != null){
//				result = true;
//			}else{
//				result = false;
//			}
//		} catch (Exception e) {
//			logger.error("缓存对象出现异常",e);
//			result = false;
//		}finally{
//			if(jedis != null){
//				JedisPool.returnResource(jedis);
//			}
//		}
//		return result;
//	}
//	
//	/**
//	 * 缓存对象（排序）
//	 * @param key 缓存服务标志
//	 * @param id 缓存对象Id
//	 * @param object 缓存对象
//	 * @param score 排序分值，分数越大，越靠前
//	 * @param expiredTime 失效时间 0或者null代表永不失效
//	 * @return
//	 *
//	 */
//	@SuppressWarnings("unchecked")
//	public boolean cacheObjectWithSort(String key,Object id ,Object object,double score,Integer expiredTime){
//		List<Object> list = new ArrayList<Object>();
//		Jedis jedis = JedisPool.getResource();
//		boolean result = false;
//		try {
//			Transaction trans = jedis.multi();
//			if(object instanceof Map){
//				trans.hmset(key+RedisCacheDef.KEY_RECORD+id, ConvertUtil.convertObjectMapToStringMap((Map<String, Object>) object));
//			}else{
//				trans.hmset(key+RedisCacheDef.KEY_RECORD+id, ConvertUtil.convertObjToHashMap(object));
//			}
//			trans.zadd(key+RedisCacheDef.KEY_ACTIVE_LIST, score, id.toString());
//			if(expiredTime != null && expiredTime > 0){
//				trans.expire(key+RedisCacheDef.KEY_RECORD+id, expiredTime);
//			}
//			list = trans.exec();
//			if(list != null){
//				result = true;
//			}else{
//				result = false;
//			}
//		} catch (Exception e) {
//			logger.error("缓存对象出现异常",e);
//			result = false;
//		}finally{
//			if(jedis != null){
//				JedisPool.returnResource(jedis);
//			}
//		}
//		return result;
//	}
	
//	/**
//	 * 删除缓存对象
//	 * @param key 缓存服务标志
//	 * @param id 缓存对象Id
//	 * @return
//	 *
//	 */
//	public boolean deleteObject(String key,Object id){
//		List<Object> list = new ArrayList<Object>();
//		Jedis jedis = JedisPool.getResource();
//		boolean result = false;
//		try {
//			Transaction trans = jedis.multi();
//			trans.zrem(key + RedisCacheDef.KEY_ACTIVE_LIST, id.toString());
//			trans.del(key + RedisCacheDef.KEY_RECORD + id.toString());
//			list = trans.exec();
//			if(list != null){
//				result = true;
//			}else{
//				result = false;
//			}
//		} catch (Exception e) {
//			logger.error("缓存删除对象异常",e);
//			result = false;
//		}finally{
//			if(jedis != null){
//				JedisPool.returnResource(jedis);
//			}
//		}
//		return result;
//	}
	
//	/**
//	 * 获取缓存对象
//	 * @param key 缓存服务标志
//	 * @param id 缓存对象Id
//	 * @param obj
//	 * @return
//	 *
//	 */
//	public Object getObject(String key,Object id,Class<?> obj){
//		Object result = null;
//		Jedis jedis = JedisPool.getResource();
//		try {
//			Map<String, String> map = jedis.hgetAll(key + RedisCacheDef.KEY_RECORD + id);
//			if(obj.equals(Map.class)){
//				result = ConvertUtil.convertStringMapToObjectMap(map);
//			}else{
//				result = ConvertUtil.convertMapToObject(map, obj);
//			}
//		} catch (Exception e) {
//			logger.error("缓存获取对象异常",e);
//		}finally{
//			if(jedis != null){
//				JedisPool.returnResource(jedis);
//			}
//		}
//		return result;
//	}
	
//	/**
//	 * 获取缓存最新记录
//	 * @param key
//	 * @param obj
//	 * @return
//	 */
//	public Object getLatestObject(String key,Class<?> obj){
//		Object result = null;
//		Jedis jedis = JedisPool.getResource();
//		Set<String> idSet = jedis.zrevrange(key + RedisCacheDef.KEY_ACTIVE_LIST, 0, 0);
//		String latestd = "";
//		for (String id : idSet) {
//			latestd = id;
//		}
//		try {
//			Map<String, String> map = jedis.hgetAll(key + RedisCacheDef.KEY_RECORD + latestd);
//			if(obj.equals(Map.class)){
//				result = ConvertUtil.convertStringMapToObjectMap(map);
//			}else{
//				result = ConvertUtil.convertMapToObject(map, obj);
//			}
//		} catch (Exception e) {
//			logger.error("缓存获取对象异常",e);
//		}finally{
//			if(jedis != null){
//				JedisPool.returnResource(jedis);
//			}
//		}
//		return result;
//	}
	
//	/**
//	 * 清空缓存
//	 * @param key 缓存服务标志
//	 * @return
//	 *
//	 */
//	public boolean flushCache(String key){
//		Jedis jedis = JedisPool.getResource();
//		boolean result = false;
//		try {
//			Set<String> keys = jedis.keys(key + "*");
//			Object[] objectArray = keys.toArray();
//			String[] keyArray = new String[keys.size()];
//			for (int i = 0; i < objectArray.length; i++)
//				keyArray[i] = objectArray[i].toString();
//
//			Long flag = (long) 0;
//			if(keyArray.length > 0) {
//				flag = jedis.del(keyArray);
//			}
//			if(flag != null){
//				result = true;
//			}else{
//				result = false;
//			}
//		} catch (Exception e) {
//			logger.error("缓存清空出现异常",e);
//			result = false;
//		}finally{
//			if(jedis != null){
//				JedisPool.returnResource(jedis);
//			}
//		}
//		return result;
//	}
	
//	/**
//	 * 判断记录是否存在
//	 * @param key 缓存服务标志
//	 * @param id 缓存对象Id
//	 * @return
//	 *
//	 */
//	public boolean isRecordExits(String key,Object id){
//		Jedis jedis = JedisPool.getResource();
//		boolean result = false;
//		try {
//			result = jedis.exists(key + RedisCacheDef.KEY_RECORD + id);
//		} catch (Exception e) {
//			logger.error("判断缓存是否存在出现异常",e);
//		}finally{
//			if(jedis != null){
//				JedisPool.returnResource(jedis);
//			}
//		}
//		return result;
//	}
	
	
	
//	/**
//	 * 获取总记录数
//	 * @param key
//	 * @return
//	 *
//	 */
//	public int getCacheCount(String key){
//		Jedis jedis = JedisPool.getResource();
//		int count = 0;
//		try {
//			Set<String> activeSet = jedis.zrange(key + RedisCacheDef.KEY_ACTIVE_LIST, 0, -1);
//			if(activeSet != null){
//				count = activeSet.size();
//			}
//		} catch (Exception e) {
//			logger.error("获取缓存记录数异常",e);
//		}finally{
//			if(jedis != null){
//				JedisPool.returnResource(jedis);
//			}
//		}
//		return count;
//	}
	
//	/**
//	 * 缓存
//	 * @param key
//	 * @param value
//	 * @return
//	 * @throws Exception
//	 *
//	 */
//	public boolean setCache(String key, Object value) throws Exception{
//		return setByRedis(key, value, 0);
//	}
	
//	/**
//	 * 缓存并指定失效时间
//	 * @param key
//	 * @param value
//	 * @param expiredTime
//	 * @return
//	 * @throws Exception
//	 *
//	 */
//	public boolean setCache(String key, Object value, Integer expiredTime) throws Exception{
//		return setByRedis(key, value, expiredTime);
//	}
	
//	/**
//	 * 缓存获取指定key值数据
//	 * @param key
//	 * @return
//	 * @throws Exception
//	 *
//	 */
//	public Object getCache(String key) throws Exception{
//		Object result = getByRedis(key);
//		return result;
//	}
	
//	/**
//	 * 缓存删除指定key
//	 * @param key
//	 * @return
//	 * @throws Exception
//	 *
//	 * @author daijian.song
//	 * @create 2015-6-17 下午2:54:23
//	 * @version cache 2.0
//	 */
//	public boolean remove(String key) throws Exception{
//		return deleteByRedis(key);
//	}
//	
//	/**
//	 * 缓存删除
//	 * @param key
//	 * @return
//	 * @throws Exception
//	 *
//	 */
//	private boolean deleteByRedis(String key) throws Exception{
//		ShardedJedis jedis = (ShardedJedis)this.shardedJedisPool.getResource();
//		boolean tag = false;
//		try{
//			Long delStatus = jedis.del(RedisCacheDef.KEY_KEYVALUE + key);
//			if(delStatus == 1){
//				tag = true;
//			}
//		}catch(Exception e){
//			tag = false;
//			throw new Exception("缓存删除key("+key+")异常：" + e.getLocalizedMessage(), e);
//		}finally{
//			if(jedis != null){
//				this.shardedJedisPool.returnResource(jedis);
//			}
//		}
//		return tag;
//	}
	
//	/**
//	 * 缓存获取数据
//	 * @param key
//	 * @return
//	 * @throws Exception
//	 *
//	 */
//	private Object getByRedis(String key) throws Exception {
//		ShardedJedis jedis = (ShardedJedis)this.shardedJedisPool.getResource();
//		Object obj = null;
//		try{
//			byte[] bytes = jedis.get(SafeEncoder.encode(key));
//			obj = cacheHelper.bytesToObject(bytes);
//		}catch(Exception e){
//			throw new Exception("缓存获取key("+key+")异常：" + e.getLocalizedMessage(), e);
//		}finally{
//			if(jedis != null){
//				this.shardedJedisPool.returnResource(jedis);
//			}
//		}
//		return obj;
//	}

//	/**
//	 * 缓存数据
//	 * @param key
//	 * @param value
//	 * @param expiredTime
//	 * @return
//	 * @throws Exception
//	 *
//	 */
//	private boolean setByRedis(String key, Object value, Integer expiredTime) throws Exception {
//		ShardedJedis jedis = (ShardedJedis)this.shardedJedisPool.getResource();
//		boolean tag = false;
//		try{
//			String saveStatus = jedis.set(SafeEncoder.encode(key), cacheHelper.objectToBytes(value));
//			if(expiredTime != null && expiredTime > 0){
//				jedis.expire(SafeEncoder.encode(key), expiredTime);
//			}
//			if("OK".equals(saveStatus)){
//				tag = true;
//			}
//		}catch(Exception e){
//			tag = false;
//			throw new Exception("缓存保存key("+key+")异常："  + e.getLocalizedMessage(), e);
//		}finally{
//			if(jedis != null){
//				this.shardedJedisPool.returnResource(jedis);
//			}
//		}
//		return tag;
//	}

//    /**
//     * 在有序集合中添加一个元素
//     */
//    public void zadd(String key, double score, String value){
//        ShardedJedis jedis = (ShardedJedis) this.shardedJedisPool.getResource();
//        try {
//            jedis.zadd(key, score, value);
//        } catch (Exception e) {
//            logger.error("[ERROR] Cache.zadd " + e.getLocalizedMessage());
//        } finally {
//            this.shardedJedisPool.returnResource(jedis);
//        }
//    }

//    /**
//     * 增加有序集合某个值得权重
//     */
//    public double zincrby(String key, double score, String value){
//        ShardedJedis jedis = (ShardedJedis) this.shardedJedisPool.getResource();
//        try {
//            return jedis.zincrby(key, score, value);
//        } catch (Exception e) {
//            logger.error("[ERROR] Cache.zincrby " + e.getLocalizedMessage());
//        } finally {
//            this.shardedJedisPool.returnResource(jedis);
//        }
//        return 0;
//    }

//    /**
//     * 从小到大排序
//     */
//    public Set<String> zrange(String key, int start, int end){
//        ShardedJedis jedis = (ShardedJedis) this.shardedJedisPool.getResource();
//        try {
//            Set<String> set = jedis.zrange(key, start, end);
//            return set;
//        } catch (Exception e) {
//            logger.error("[ERROR] Cache.zrange " + e.getLocalizedMessage());
//        } finally {
//            this.shardedJedisPool.returnResource(jedis);
//        }
//        return null;
//    }

//    /**
//     * 从大到小排序
//     */
//    public Set<String> zrevrange(String key, int start, int end){
//        ShardedJedis jedis = (ShardedJedis) this.shardedJedisPool.getResource();
//        try {
//            return jedis.zrevrange(key, start, end);
//        } catch (Exception e) {
//            logger.error("[ERROR] Cache.zrevrange " + e.getLocalizedMessage());
//        } finally {
//            this.shardedJedisPool.returnResource(jedis);
//        }
//        return null;
//    }

//    /**
//     * 返回有序集合中某个值得排名（从大到小）
//     */
//    public long zrevrank(String key, String member){
//        ShardedJedis jedis = (ShardedJedis) this.shardedJedisPool.getResource();
//        try {
//            return jedis.zrevrank(key, member);
//        } catch (Exception e) {
//            logger.error("[ERROR] Cache.zrevrank " + e.getLocalizedMessage());
//        } finally {
//            this.shardedJedisPool.returnResource(jedis);
//        }
//        return -1;
//    }

//    /**
//     * 返回队列的成员总数
//     */
//    public long zcard(String key){
//        ShardedJedis jedis = (ShardedJedis) this.shardedJedisPool.getResource();
//        try {
//            return jedis.zcard(key);
//        } catch (Exception e) {
//            logger.error("[ERROR] Cache.scard " + e.getLocalizedMessage());
//        } finally {
//            this.shardedJedisPool.returnResource(jedis);
//        }
//        return -1;
//    }

//    /**
//     * 根据排名移除队列(从小到大)
//     * @param key       -- KEY
//     * @param start     -- 起始排名
//     * @param end       -- 截止排名
//     * @return
//     */
//    public long zremrangeByRank(String key, int start, int end){
//        ShardedJedis jedis = (ShardedJedis) this.shardedJedisPool.getResource();
//        try {
//            return jedis.zremrangeByRank(key, start, end);
//        } catch (Exception e) {
//            logger.error("[ERROR] Cache.zremrangeByRank " + e.getLocalizedMessage());
//        } finally {
//            this.shardedJedisPool.returnResource(jedis);
//        }
//        return -1;
//    }

//    /**
//     * 获取集合中某个成员的得分
//     * @param key       -- KEY
//     * @param member    -- 成员
//     * @return
//     */
//    public double zscore(String key, String member){
//        ShardedJedis jedis = (ShardedJedis) this.shardedJedisPool.getResource();
//        try {
//            return jedis.zscore(key, member);
//        } catch (Exception e) {
//            logger.error("[ERROR] Cache.zscore " + e.getLocalizedMessage());
//        } finally {
//            this.shardedJedisPool.returnResource(jedis);
//        }
//        return -1;
//    }

    /**
     * 将某个对象插入队列的表头
     * @param key       -- KEY
     * @param value     -- 对象
     * @return
     */
    public long lpush(String key, Object value){
        ShardedJedis jedis = (ShardedJedis) shardedJedisPool.getResource();
        try {
            return jedis.lpush(SafeEncoder.encode(key), objectBytesUtil.objectToBytes(value));
        } catch (Exception e) {
            logger.error("[ERROR] Cache.lpush " + e.getLocalizedMessage());
        } finally {
            this.shardedJedisPool.returnResource(jedis);
        }
        return -1;
    }

    /**
     * 将某个对象插入队列的表尾
     * @param key       -- KEY
     * @param value     -- 对象
     * @return
     */
    public long rpush(String key, Object value){
        ShardedJedis jedis = (ShardedJedis) this.shardedJedisPool.getResource();
        try {
            return jedis.rpush(SafeEncoder.encode(key), objectBytesUtil.objectToBytes(value));
        } catch (Exception e) {
        	e.printStackTrace();
            logger.error("[ERROR-getLocalizedMessage] Cache.lpush " + e.getLocalizedMessage());
        	logger.error("[ERROR-getMessage] Cache.lpush " + e.getMessage());
        } finally {
            this.shardedJedisPool.returnResource(jedis);
        }
        return -1;
    }
    /**
     * 删除队头元素
     * @param key       -- KEY
     * @return
     */
    public String lpop(String key){
        ShardedJedis jedis = (ShardedJedis) this.shardedJedisPool.getResource();
        try {
            return jedis.lpop(key);
            	
        } catch (Exception e) {
            logger.error("[ERROR] Cache.lpop " + e.getLocalizedMessage());
        } finally {
            this.shardedJedisPool.returnResource(jedis);
        }
        return "";
    }
    /**
     * 删除队尾元素
     * @param key       -- KEY
     * @return
     */
    public String rpop(String key){
        ShardedJedis jedis = (ShardedJedis) this.shardedJedisPool.getResource();
        try {
            return jedis.rpop(key);
            	
        } catch (Exception e) {
            logger.error("[ERROR] Cache.lpop " + e.getLocalizedMessage());
        } finally {
            this.shardedJedisPool.returnResource(jedis);
        }
        return "";
    }

    /**
     * 获取指定区间的元素
     * @param key       -- KEY
     * @param start     -- 开始下标
     * @param end       -- 结束下标
     * @return
     */
    @SuppressWarnings("rawtypes")
	public List lrange(String key, int start, int end){
        ShardedJedis jedis = (ShardedJedis) shardedJedisPool.getResource();
        try {
            List<byte[]> list = jedis.lrange(SafeEncoder.encode(key), start, end);
            List<Object> l = new ArrayList<>();
            for (byte[] bytes : list){
                l.add(objectBytesUtil.bytesToObject(bytes));
            }
            return l;
        } catch (Exception e) {
            logger.error("[ERROR] Cache.lrange " + e.getLocalizedMessage());
        } finally {
            this.shardedJedisPool.returnResource(jedis);
        }
        return null;
    }

    /**
     * 删除缓存
     */
    public long del(String key){
        ShardedJedis jedis = (ShardedJedis) shardedJedisPool.getResource();
        try {
            return jedis.del(key);
        } catch (Exception e) {
            logger.error("[ERROR] Cache.del " + e.getLocalizedMessage());
        } finally {
            this.shardedJedisPool.returnResource(jedis);
        }
        return 0;
    }

//    /**
//     * 计数器
//     */
//    public long incr(String key){
//        ShardedJedis jedis = (ShardedJedis) this.shardedJedisPool.getResource();
//        try {
//            return jedis.incr(key);
//        } catch (Exception e) {
//            logger.error("[ERROR] Cache.incr " + e.getLocalizedMessage());
//        } finally {
//            this.shardedJedisPool.returnResource(jedis);
//        }
//        return -1;
//    }

    /**
     * 设置超时时间
     * @param key       -- KEY
     * @param seconds   -- 秒数
     * @return
     */
    public long expire(String key, int seconds){
        ShardedJedis jedis = (ShardedJedis) this.shardedJedisPool.getResource();
        try {
            return jedis.expire(key, seconds);
        } catch (Exception e) {
            logger.error("[ERROR] Cache.incr " + e.getLocalizedMessage());
        } finally {
            this.shardedJedisPool.returnResource(jedis);
        }
        return -1;
    }
    
    /*************对map的操作****************/
    /**
     * 向redis中添加整个Map
     * @param key
     * @param map
     */
    public void hmset(String key, Map<String,String> map){
    	ShardedJedis jedis = shardedJedisPool.getResource();
		try {
			jedis.hmset(key, map);
		} catch (Exception e) {
			logger.error("向redis添加Map失败",e);
		}finally{
			if(jedis != null){
				shardedJedisPool.returnResource(jedis);
			}
		}
    }
    
    /**
     * 返回名称为key的hash中field对应的value
     * @param key
     * @param field
     * @return
     */
    public String hget(String key, String field){
    	ShardedJedis jedis = shardedJedisPool.getResource();
		try {
			return jedis.hget(key, field);
		} catch (Exception e) {
			logger.error("返回名称为key的hash中field对应的value失败",e);
		}finally{
			if(jedis != null){
				shardedJedisPool.returnResource(jedis);
			}
		}
    	return null;
    }
    
    public Map<String, String> hgetAll(String key){
    	ShardedJedis jedis = shardedJedisPool.getResource();
    	try{
    		return jedis.hgetAll(key);
    	}catch (Exception e) {
			logger.error("返回名称为key的hash中的所有值失败",e);
		}finally{
			if(jedis != null){
				shardedJedisPool.returnResource(jedis);
			}
		}
    	return null;
    }
    
    /**
     * 向key为key的map中添加字段，或者更新已有字段
     * @param key
     * @param field 要添加或更新的字段的key
     * @param value 要添加或更新的字段的value
     */
    public void hset(String key, String field, String value){
    	ShardedJedis jedis = shardedJedisPool.getResource();
		try {
			jedis.hset(key, field, value);
		} catch (Exception e) {
			logger.error("向key为key的map中添加字段，或者更新已有字段失败",e);
		}finally{
			if(jedis != null){
				shardedJedisPool.returnResource(jedis);
			}
		}
    }
    
    public void hdel(String key, String ... field){
    	ShardedJedis jedis = shardedJedisPool.getResource();
		try {
			jedis.hdel(key, field);
		} catch (Exception e) {
			logger.error("移除key为key的map中field字段失败",e);
		}finally{
			if(jedis != null){
				shardedJedisPool.returnResource(jedis);
			}
		}
    }
    /**************************************/

    public boolean exists(String key){
    	ShardedJedis jedis = shardedJedisPool.getResource();
		try {
			return jedis.exists(key);
		} catch (Exception e) {
			logger.error("向key为key的map中添加字段，或者更新已有字段失败",e);
		}finally{
			if(jedis != null){
				shardedJedisPool.returnResource(jedis);
			}
		}
		return false;
    }
    
    public long setnx(String key, String value){
    	ShardedJedis jedis = shardedJedisPool.getResource();
    	try{
    		return jedis.setnx(key, value);
    	} catch(Exception e){
    		logger.error("", e);
    		throw e;
    	} finally{
    		if(jedis != null){
				shardedJedisPool.returnResource(jedis);
			}
    	}
    }
    
    public String setex(String key, String value, int seconds){
    	ShardedJedis jedis = shardedJedisPool.getResource();
    	try{
    		return jedis.setex(key, seconds, value);
    	} catch(Exception e){
    		logger.error("", e);
    		throw e;
    	} finally{
    		if(jedis != null){
				shardedJedisPool.returnResource(jedis);
			}
    	}
    }
    
    public Object get(String key){
    	ShardedJedis jedis = shardedJedisPool.getResource();
    	try{
    		return jedis.get(key);
    	} catch(Exception e){
    		logger.error("", e);
    		throw e;
    	} finally{
    		if(jedis != null){
				shardedJedisPool.returnResource(jedis);
			}
    	}
    }
    
	public ShardedJedisPool getShardedJedisPool() {
		return shardedJedisPool;
	}

	public void setShardedJedisPool(ShardedJedisPool shardedJedisPool) {
		this.shardedJedisPool = shardedJedisPool;
	}

	public JedisPool getJedisPool() {
		return jedisPool;
	}

	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}
}

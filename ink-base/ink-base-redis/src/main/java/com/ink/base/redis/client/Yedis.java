package com.ink.base.redis.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.DisposableBean;

import com.ink.base.log.util.YinkerLogger;
import com.ink.base.redis.core.CacheCommand;
import com.ink.base.redis.core.ehcache.EhcacheCommand;
import com.ink.base.redis.support.CacheEnum;
import com.ink.base.redis.support.DataFrom;
import com.ink.base.redis.support.NamedThreadFactory;
import com.ink.base.redis.util.CacheTypeUtil;

import consts.RedisConstant;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;
import redis.clients.util.SafeEncoder;

/**
 * 缓存中不存在时，需要读取数据库时建议使用该客户端
 * 1、
 * @author zongtt
 * 2016年6月29日14:25:28
 */
public class Yedis implements DisposableBean{
	
	private YinkerLogger logger = YinkerLogger.getLogger(Yedis.class);
	
	//缓存类型:分布式缓存、本地缓存
	private CacheEnum cacheFlag = CacheEnum.REDIS;
	private CacheCommand cacheCommand;
	
	private CacheCommand bakCommand;
	
	private JedisPool jedisPool;
	private ScheduledExecutorService scheduled = Executors.newSingleThreadScheduledExecutor(new NamedThreadFactory("yedis-redis-heartbeat", true));

	public static final String CACHE_TIME_KEY = "yedis-cache-time";
	public static final String EXPIRE_TIME_KEY = "yedis-expire-time";
	public static final String DATA_TYPE = "yedis-data-type";
	public static final String STRING_KEY = "yedis-data-value";
	public static final String LOAD_SERVER = "yedis-cache-server";

	public Yedis() {
		scheduled.scheduleWithFixedDelay(new RedisServerHeartBeat(), 60, 10, TimeUnit.SECONDS);
	}

	/**
	 * 获取缓存数据
	 * @param key 缓存KEY前缀，建议以-结尾
	 * @param id 缓存对象的唯一标识
	 * @param obj 返回的实例类
	 * @param reader 当缓存不存在时，调用该接口获取数据，并存入缓存
	 * @return
	 */
	public CacheObject getObject(String key, String id, Class<?> obj, Reader<Object> reader){
		return getObject(key, id, obj, 0, reader);
	}
	
	/**
	 * 获取缓存数据
	 * 当调用缓存时，若发现缓存过期，则将旧版本缓存返回，发起异步线程更新缓存数据
	 * @param key 缓存KEY前缀，建议以-结尾
	 * @param id  缓存对象的唯一标识
	 * @param obj 返回的实例类
	 * @param expiredTime 过期时间，单位秒，仅当从数据库查询时有效，0或空时表示永不失效
	 * @param reader 当缓存不存在时，调用该接口获取数据，并存入缓存
	 * @return
	 */
	public CacheObject getObject(String key, String id, Class<?> obj,Integer expiredTime, Reader<Object> reader){
		
		//Redis数据存储结构   MAP(额外加入缓存时间、过期时间、数据类型)
		if(StringUtils.isBlank(key) || StringUtils.isBlank(id) || obj == null){
			return null;
		}
		
		//Redis中的真实KEY
		String rdk = key + RedisConstant.KEY_RECORD + id;
		
		return cacheCommand.getObject(rdk, obj, expiredTime, reader);
	}
	
	/**
	 * @param key 缓存KEY前缀，建议以-结尾
	 * @param id 缓存对象的唯一标识
	 * @param object 缓存对象
	 * @return 成功：true；失败：false
	 * 注意：本接口仅向redis存储数据，若redis连不上时操作失败
	 */
	public boolean cacheObject(String key, String id, Object obj) {
		return cacheObject(key, id, obj, 0, DataFrom.OTHER);
	}

	/**
	 * @param key 缓存KEY前缀，建议以-结尾
	 * @param id 缓存对象的唯一标识
	 * @param object 缓存对象
	 * @param df 数据来源，仅当来源为DB时，本地缓存启用时，才会存储到ehcache
	 * @return 成功：true；失败：false
	 */
	public boolean cacheObject(String key, String id, Object obj, DataFrom df) {
		return cacheObject(key, id, obj, 0, df);
	}
	
	/**
	 * 设置缓存数据
	 * @param key 缓存KEY前缀，建议以-结尾
	 * @param id 缓存对象的唯一标识
	 * @param object 缓存对象
	 * @param expiredTime 过期时间，单位秒
	 * @return 成功：true；失败：false
	 */
	public boolean cacheObject(String key, String id, Object obj, Integer expiredTime, DataFrom df) {

		if(StringUtils.isBlank(key) || StringUtils.isBlank(id) || obj == null || df == null){
			return false;
		}
		
		boolean result = false;
		
		String rdk = key + RedisConstant.KEY_RECORD + id;
		
		try{
			result = cacheCommand.cacheObject(rdk, obj, expiredTime, df);
		}catch(RuntimeException e){
			return false;
		}
		
		return result;
	}
	
	/**
	 * @param key 缓存KEY
	 * @param field
	 * @param value
	 * @param expiredTime 当KEY不存在时生效
	 * @param data 数据来源，仅当数据来源为DB时，本地缓存启用时，才会存储到ehcache
	 * 		 
	 * Returns:-1失败、1新增、0更新
	 */
	public long hset(String key, String field, String value, Integer expiredTime,DataFrom data){
		
		if(StringUtils.isBlank(key) || StringUtils.isBlank(field) || StringUtils.isBlank(value) || data == null){
			return -1;
		}
		
		return cacheCommand.hset(key, field, value,expiredTime, data);
	}
	
	/**
	 * @param key 缓存KEY
	 * @param field
	 * @param value
	 * @param data 数据来源，仅当数据来源为DB时，本地缓存启用时，才会存储到ehcache
	 * 		 
	 * Returns:-1失败、1新增、0更新
	 */
	public long hset(String key, String field, String value, DataFrom data){
		return hset(key, field, value, 0, data);
	}

	/**
	 * @param key
	 * @param map
	 * @param data 数据来源，仅当数据来源为DB时，本地缓存启用时，才会存储到ehcache
	 */
	public boolean hmset(String key, Map<String, String> map,DataFrom data){
		
		if(StringUtils.isBlank(key) || map == null || data == null){
			return false;
		}
		
		return cacheCommand.hmset(key, map,0, data);
	}
	
	/**
	 * 
	 * @param key
	 * @param reader
	 * @return
	 */
	public Map<String, String> hgetAll(String key, Reader<Map<String,String>> reader){
		return hgetAll(key, 0, reader);
	}
	
	/**
	 * 
	 * @param key
	 * @param expiredTime
	 * @param reader
	 * @return
	 */
	public Map<String, String> hgetAll(String key, Integer expiredTime,Reader<Map<String,String>> reader){
		
		if(StringUtils.isBlank(key)){
			return null;
		}
		
		return cacheCommand.hgetAll(key, expiredTime, reader);
	}
	
	/**
	 * @param key
	 * @param field
	 * @param reader 当缓存KEY不存在时，调用reader将该KEY对应的所有field查询出来，存储到缓存中
	 * @return
	 */
	public String hget(String key, String field, Reader<Map<String,String>> reader){
		return hget(key, field, 0, reader); 
	}
	
	/**
	 * @param key
	 * @param field
	 * @param expiredTime 过期时间，单位秒
	 * @param reader 当缓存KEY不存在时，调用reader将该KEY对应的所有field查询出来，存储到缓存中
	 * @return
	 */
	public String hget(String key, String field, Integer expiredTime, Reader<Map<String,String>> reader){
		
		if(StringUtils.isBlank(key) || StringUtils.isBlank(field)){
			return "";
		}
		
		return cacheCommand.hget(key, field,expiredTime,reader);
	}
	
	/**
	 * Returns:1成功、其他失败
	 */
	public long hdel(String key, String field){
		
		if(StringUtils.isBlank(key) || StringUtils.isBlank(field)){
			return -1;
		}
		
		return cacheCommand.hdel(key, field);
	}
	
	
	/**
	 * 清理缓存
	 * @param key
	 * @return
	 */
	public boolean deleteObject(String key,String id){
		//Redis中的真实KEY
		String rdk = key + RedisConstant.KEY_RECORD + id;
		return cacheCommand.deleteObject(rdk);
	}
	
	/**
	 * 删除缓存KEY
	 * @param key
	 * @return
	 */
	public boolean del(String key){
		return cacheCommand.deleteObject(key);
	}
	
	/**
	 * Specified by: exists(...) in JedisCommands
	 * Parameters:key Returns:Boolean reply, true if the key exists, otherwise false
	 */
	public boolean exists(String key) {
		Jedis jedis = null;
		
		boolean result = false;
		
		try {
			jedis = jedisPool.getResource();
			
			result = jedis.exists(key);
		} catch (Exception e) {
			logger.error("判断缓存是否存在出现异常", e);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		
		return result;
	}
	
	/**
	 * 在有序集合中添加一个元素
	 */
	public long zadd(String key, double score, String value) {
		Jedis jedis = null;
		
		try {
			jedis = jedisPool.getResource();

			return jedis.zadd(key, score, value);
		} catch (Exception e) {
			logger.error("[ERROR] Cache.zadd " + e.getLocalizedMessage());
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		
		return -1;
	}

	/**
	 * 增加有序集合某个值得权重
	 */
	public double zincrby(String key, double score, String value) {
		Jedis jedis = null;
		
		try {
			jedis = jedisPool.getResource();
			return jedis.zincrby(key, score, value);
		} catch (Exception e) {
			logger.error("[ERROR] Cache.zincrby " + e.getLocalizedMessage());
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		
		return 0;
	}

	/**
	 * 从小到大排序
	 */
	public Set<String> zrange(String key, int start, int end) {
		
		Jedis jedis = null;
		
		try {
			jedis = jedisPool.getResource();
			Set<String> set = jedis.zrange(key, start, end);
			return set;
		} catch (Exception e) {
			logger.error("[ERROR] Cache.zrange " + e.getLocalizedMessage());
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

	/**
	 * 从大到小排序
	 */
	public Set<String> zrevrange(String key, int start, int end) {
		
		Jedis jedis = null;
		
		try {
			jedis = jedisPool.getResource();
			return jedis.zrevrange(key, start, end);
		} catch (Exception e) {
			logger.error("[ERROR] Cache.zrevrange " + e.getLocalizedMessage());
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

	/**
	 * 返回有序集合中某个值得排名（从大到小）
	 */
	public long zrevrank(String key, String member) {
		Jedis jedis = null;
		
		try {
			jedis = jedisPool.getResource();
			Long l = jedis.zrevrank(key, member);
			if (l == null) {
				return -1;
			}
			
			return l;
		} catch (Exception e) {
			logger.error("[ERROR] Cache.zrevrank " + e.getLocalizedMessage());
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		
		return -1;
	}

	/**
	 * 返回队列的成员总数
	 */
	public long zcard(String key) {
		Jedis jedis = null;
		
		try {
			jedis = jedisPool.getResource();
			Long l = jedis.zcard(key);
			if (l == null) {
				return -1;
			}
			return l;
		} catch (Exception e) {
			logger.error("[ERROR] Cache.scard " + e.getLocalizedMessage());
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return -1;
	}

	/**
	 * 根据排名移除队列(从小到大)
	 * 
	 * @param key
	 *            -- KEY
	 * @param start
	 *            -- 起始排名
	 * @param end
	 *            -- 截止排名
	 * @return
	 */
	public long zremrangeByRank(String key, int start, int end) {
		
		Jedis jedis = null;
		
		try {
			jedis = jedisPool.getResource();
		
			Long l = jedis.zremrangeByRank(key, start, end);
			if (l == null) {
				return -1;
			}
			return l;
		} catch (Exception e) {
			logger.error("[ERROR] Cache.zremrangeByRank "
					+ e.getLocalizedMessage());
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return -1;
	}

	/**
	 * 获取集合中某个成员的得分
	 * 
	 * @param key
	 *            -- KEY
	 * @param member
	 *            -- 成员
	 * @return
	 */
	public double zscore(String key, String member) {
		
		Jedis jedis = null;
		
		try {
			jedis = jedisPool.getResource();
			return jedis.zscore(key, member);
		} catch (Exception e) {
			logger.error("[ERROR] Cache.zscore " + e.getLocalizedMessage());
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return -1;
	}

	/**
	 * 将某个都想插入队列的表头
	 * 
	 * @param key
	 *            -- KEY
	 * @param value
	 *            -- 对象
	 * @return
	 */
	public long lpush(String key, Object value) {
		Jedis jedis = null;
		
		try {
			jedis = jedisPool.getResource();
			Long l = jedis.lpush(SafeEncoder.encode(key),CacheTypeUtil.objectToBytes(value));
			if (l == null) {
				return -1;
			}
			return l;
		} catch (Exception e) {
			logger.error("[ERROR] Cache.lpush " + e.getLocalizedMessage());
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return -1;
	}

	/**
	 * 将某个都想插入队列的表尾
	 * 
	 * @param key
	 *            -- KEY
	 * @param value
	 *            -- 对象
	 * @return
	 */
	public long rpush(String key, Object value) {
		Jedis jedis = null;
		
		try {
			jedis = jedisPool.getResource();
			Long l = jedis.rpush(SafeEncoder.encode(key), CacheTypeUtil.objectToBytes(value));
			if (l == null) {
				return -1;
			}
			return l;
		} catch (Exception e) {
			logger.error("[ERROR] Cache.lpush " + e.getLocalizedMessage());
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return -1;
	}

	/**
	 * 移除并返回存于 key 的 list 的最后一个元素，即删除队尾元素并返回该元素
	 * 
	 * @param key
	 * @return
	 */
	public Object rpop(String key) {
		Jedis jedis = null;
		
		Object obj = null;
		
		try {
			jedis = jedisPool.getResource();
			byte[] bytes = jedis.rpop(SafeEncoder.encode(key));
			obj = CacheTypeUtil.bytesToObject(bytes);
		} catch (Exception e) {
			logger.error("[ERROR] Cache.rpop " + e.getLocalizedMessage());
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return obj;
	}

	/**
	 * 获取指定区间的元素
	 * 
	 * @param key
	 *            -- KEY
	 * @param start
	 *            -- 开始下标
	 * @param end
	 *            -- 结束下标
	 * @return
	 */
	public List lrange(String key, int start, int end) {
		Jedis jedis = null;
		
		try {
			jedis = jedisPool.getResource();
			List<byte[]> list = jedis.lrange(SafeEncoder.encode(key), start,
					end);
			List l = new ArrayList<>();
			for (byte[] bytes : list) {
				l.add(CacheTypeUtil.bytesToObject(bytes));
			}
			return l;
		} catch (Exception e) {
			logger.error("[ERROR] Cache.lrange " + e.getLocalizedMessage());
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return null;
	}
	
	/**
	 * Specified by: sadd(...) in JedisCommands
	 * @param key
	 * @param member
	 * @return
	 */
	public long sadd(String key, String member) {
		if(StringUtils.isBlank(key) || StringUtils.isBlank(member)){
			return -1;
		}
		
		List<String> list = new ArrayList<>();
		list.add(member);
		
		return sadd(key, list);
	}
	
	/**
	 * Specified by: sadd(...) in JedisCommands
	 * @param key
	 * @param list
	 * @return -1 if fail ,1 if the new element was added ,0 if the element was already a member of the set
	 */
	public long sadd(String key, List<String> list) {
		
		Response<Long> res = null;
		Jedis jedis = null;

		try {
			jedis = jedisPool.getResource();
			
			Pipeline pl = jedis.pipelined();
			
			for (String str : list) {
				res = pl.sadd(key, str);
			}
			pl.sync();
			
			Long num = res.get();
			
			if (num == null) {
				return -1;
			}
			
			return num.longValue();
			
		} catch (Exception e) {
			logger.error("[ERROR] Cache.lrange saddUsePipeline"
					+ e.getLocalizedMessage());
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		
		return 0;
	}
	
	public Long sdiffStore(String result, String setA, String setB) {
		
		Jedis jedis = null;

		try {
			jedis = jedisPool.getResource();
			Long l = jedis.sdiffstore(result, setA, setB);
			return l;
		} catch (Exception e) {
			logger.error("[ERROR]sdiffStore Cache.scard "
					+ e.getLocalizedMessage());
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return null;
	}
	
	/**
	 * 判断元素是否在set集合内
	 */
	public boolean sismember(String key, String member) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.sismember(key, member);
		} catch (Exception e) {
			logger.error("[ERROR] Cache.sismember " + e.getLocalizedMessage());
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return false;
	}

	/**
	 * 获取set集合内元素的数量
	 */
	public long scard(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			Long l = jedis.scard(key);
			if (l == null) {
				return -1;
			}
			return l;
		} catch (Exception e) {
			logger.error("[ERROR] Cache.scard " + e.getLocalizedMessage());
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return 0;
	}

	/**
	 * 获取set集合中的所有元素
	 */
	public Set<String> smembers(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.smembers(key);
		} catch (Exception e) {
			logger.error("[ERROR] Cache.scard " + e.getLocalizedMessage());
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

	/**
	 * 将元素从set集合中移除。返回新的集合大小
	 */
	public long smove(String key, String member) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			Long l = jedis.srem(key, member);
			if (l == null) {
				return -1;
			}
			return l;
		} catch (Exception e) {
			logger.error("[ERROR] Cache.scard " + e.getLocalizedMessage());
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return 0;
	}
	
	/**
	 * 计数器
	 */
	public long incr(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.incr(key);
		} catch (Exception e) {
			logger.error("[ERROR] Cache.incr " + e.getLocalizedMessage());
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return -1;
	}

	/**
	 * 自定义步数计数器
	 */
	public long incrBy(String key, int step) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.incrBy(key, step);
		} catch (Exception e) {
			logger.error("[ERROR] Cache.incr " + e.getLocalizedMessage());
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return -1;
	}

	/**
	 * 设置超时时间
	 * 
	 * @param key
	 *            -- KEY
	 * @param seconds
	 *            -- 秒数
	 * @return
	 */
	public long expire(String key, int seconds) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.expire(key, seconds);
		} catch (Exception e) {
			logger.error("[ERROR] Cache.incr " + e.getLocalizedMessage());
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return -1;
	}
	
	/**
	 * Redis服务监测
	 *
	 */
	class RedisServerHeartBeat implements Runnable {
		
		private int fail = 0;
		
		@Override
		public void run() {
			
			if(logger.isDebugEnabled()){
				logger.debug("Redis心跳监测服务");
			}
			
			if(jedisPool == null){
				return;
			}
			
			Jedis jedis = null;
			try{
				jedis = jedisPool.getResource();
				String ping = jedis.ping();

		        if("pong".equalsIgnoreCase(ping)){
		        	//Redis服务正常
		        	fail = 0;
		        	switchCache(CacheEnum.REDIS);
		        }else {
					fail++;
				}
			}catch(RuntimeException e){
				e.printStackTrace();
				fail++;
				logger.error("Redis服务异常，心跳监测失败"+fail+"次");
			}finally {
				if (jedis != null) {
					jedis.close();
				}
			}
			
			if(fail > 1){
				//Redis监测连续两次失败时，切换为本地服务
				switchCache(CacheEnum.LOCAL);
			}
		}
	}
	
	/**
	 * 缓存切换
	 * @param ce
	 */
	private void switchCache(CacheEnum ce) {
		
		if(!cacheFlag.equals(ce)){
			CacheCommand tmpCommand = cacheCommand;
    		cacheCommand = bakCommand;
    		bakCommand = tmpCommand;
    		cacheFlag = ce;
    		
    		logger.info("缓存心跳监测，自动切换成"+ce.getName()+"服务");
    		
    		if(ce == CacheEnum.REDIS){
    			((EhcacheCommand)bakCommand).removeCache();
    		}
		}
	}
	
	/**
	 * 手动切换成本地缓存，一般用于测试
	 */
	public void switchLocalCache(){
		if(cacheFlag.equals(CacheEnum.REDIS)){
			CacheCommand tmpCommand = cacheCommand;
    		cacheCommand = bakCommand;
    		bakCommand = tmpCommand;
    		cacheFlag = CacheEnum.LOCAL;
    		
    		logger.info("手动切换成"+cacheFlag.getName()+"服务");
    		
    		scheduled.shutdown();
		}
	}

	@Override
	public void destroy() throws Exception {
		jedisPool.destroy();
	}

	public void setBakCommand(CacheCommand bakCommand) {
		this.bakCommand = bakCommand;
	}

	public void setCacheCommand(CacheCommand cacheCommand) {
		this.cacheCommand = cacheCommand;
	}
	
	public JedisPool getJedisPool() {
		return jedisPool;
	}

	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}

}
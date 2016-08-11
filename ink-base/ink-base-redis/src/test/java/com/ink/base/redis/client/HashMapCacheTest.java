package com.ink.base.redis.client;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ink.base.redis.support.DataFrom;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-redis.xml")
public class HashMapCacheTest {
	
	@Autowired
	private Yedis yedis;

	@Test
	public void testHset() throws InterruptedException {
		
		yedis.del("test-hset");
		
		yedis.hset("test-hset", "cache-db", "redis-db", DataFrom.DB);
		
		String value = yedis.hget("test-hset", "cache-db", null);
		
		Assert.assertEquals("redis-db", value);

		yedis.hset("test-hset", "cache-other", "redis-other", DataFrom.OTHER);
		
		value = yedis.hget("test-hset", "cache-other", null);
		
		Assert.assertEquals("redis-other", value);


		//休眠30S，切换到ehcache
		yedis.switchLocalCache();
		
		yedis.hset("test-hset", "cache-db", "ehcache-db", DataFrom.DB);
		
		value = yedis.hget("test-hset", "cache-db", null);
		
		Assert.assertEquals("ehcache-db", value);

		yedis.hset("test-hset", "cache-other", "ehcache-other", DataFrom.OTHER);
		
		value = yedis.hget("test-hset", "cache-other", null);
		
		Assert.assertEquals("ehcache-other", value);
		
	}
	
	@Test
	public void testHmset() throws InterruptedException {
		
		String key = "test-hmset";
		
		yedis.del(key);
		
		boolean result = yedis.hmset(key, null, DataFrom.DB);
		
		Assert.assertEquals(result, false);
		
		result = yedis.hmset(key, new HashMap<String,String>(), DataFrom.DB);
		
		Assert.assertEquals(result, true);
		
		Map<String, String> map = new HashMap<>();
		map.put("cache-db", "redis-db");
		
		result = yedis.hmset(key, map, DataFrom.DB);

		Assert.assertEquals(result, true);
		
		String value = yedis.hget(key, "cache-db", null);
		
		Assert.assertEquals("redis-db", value);


		//休眠30S，切换到ehcache
		yedis.switchLocalCache();
		
		result = yedis.hmset(key, new HashMap<String,String>(), DataFrom.DB);
		
		Assert.assertEquals(result, true);
		
		map = new HashMap<>();
		map.put("cache-db", "ehcache-db");
		
		result = yedis.hmset(key, map, DataFrom.DB);

		Assert.assertEquals(result, true);
		
		value = yedis.hget(key, "cache-db", null);
		
		Assert.assertEquals("ehcache-db", value);
		
	}
	
	@Test
	public void testHget() {
		
		String key = "test-hget";
		
		String value = yedis.hget(key, "cache-db", new Reader<Map<String,String>>() {
			
			@Override
			public Map<String, String> readerFromDatabase() {
				return null;
			}
		});
		
		Assert.assertNull(value);
		
		yedis.del(key);
		
		value = yedis.hget(key, "cache-db", new Reader<Map<String,String>>() {
			
			@Override
			public Map<String, String> readerFromDatabase() {
				return new HashMap<String, String>();
			}
		});
		
		Assert.assertNull(value);
		yedis.del(key);
		
		value = yedis.hget(key, "cache-db", new Reader<Map<String,String>>() {
			
			@Override
			public Map<String, String> readerFromDatabase() {
				Map<String,String> map = new HashMap<>();
				map.put("cache-db", "redis-db");
				map.put("cache-db-1", "redis-db-1");
				return map;
			}
		});
		
		Assert.assertEquals("redis-db",value);
		
		
		yedis.switchLocalCache();
		
		value = yedis.hget(key, "cache-db", new Reader<Map<String,String>>() {
			
			@Override
			public Map<String, String> readerFromDatabase() {
				return null;
			}
		});
		
		Assert.assertEquals(value,"");
		
		yedis.del(key);
		
		value = yedis.hget(key, "cache-db", new Reader<Map<String,String>>() {
			
			@Override
			public Map<String, String> readerFromDatabase() {
				return new HashMap<String, String>();
			}
		});
		
		Assert.assertNull(value);
		yedis.del(key);
		
		value = yedis.hget(key, "cache-db", new Reader<Map<String,String>>() {
			
			@Override
			public Map<String, String> readerFromDatabase() {
				Map<String,String> map = new HashMap<>();
				map.put("cache-db", "ehcahce-db");
				map.put("cache-db-1", "ehcahce-db-1");
				return map;
			}
		});
		
		Assert.assertEquals("ehcahce-db",value);
		
		value = yedis.hget(key, "cache-db", new Reader<Map<String,String>>() {
			
			@Override
			public Map<String, String> readerFromDatabase() {
				Map<String,String> map = new HashMap<>();
				map.put("cache-db", "ehcahce-db");
				map.put("cache-db-1", "ehcahce-db-1");
				return map;
			}
		});
		
		Assert.assertEquals("ehcahce-db",value);
		
	}
	
	@Test
	public void testHgetAll() {
		
		String key = "test-hgetAll";
		
//		Map<String, String> value = yedis.hgetAll(key, new Reader<Map<String,String>>() {
//			
//			@Override
//			public Map<String, String> readerFromDatabase() {
//				return null;
//			}
//		});
//		
//		Assert.assertNull(value);
//		
//		yedis.del(key);
//		
//		value = yedis.hgetAll(key,new Reader<Map<String,String>>() {
//			
//			@Override
//			public Map<String, String> readerFromDatabase() {
//				return new HashMap<String, String>();
//			}
//		});
//		
//		Assert.assertTrue(value.isEmpty());
//		yedis.del(key);
//		
//		value = yedis.hgetAll(key, new Reader<Map<String,String>>() {
//			
//			@Override
//			public Map<String, String> readerFromDatabase() {
//				Map<String,String> map = new HashMap<>();
//				map.put("cache-db", "redis-db");
//				map.put("cache-db-1", "redis-db-1");
//				return map;
//			}
//		});
//		
//		Assert.assertEquals("redis-db",value.get("cache-db"));
//		
//		value = yedis.hgetAll(key, new Reader<Map<String,String>>() {
//			
//			@Override
//			public Map<String, String> readerFromDatabase() {
//				Map<String,String> map = new HashMap<>();
//				map.put("cache-db", "redis-db");
//				map.put("cache-db-1", "redis-db-1");
//				return map;
//			}
//		});
//		
//		Assert.assertEquals("redis-db",value.get("cache-db"));
//		
//		
		yedis.switchLocalCache();
//		
//		value = yedis.hgetAll(key, new Reader<Map<String,String>>() {
//			
//			@Override
//			public Map<String, String> readerFromDatabase() {
//				return null;
//			}
//		});
//		
//		Assert.assertNull(value);
//		
//		yedis.del(key);
//		
//		value = yedis.hgetAll(key,new Reader<Map<String,String>>() {
//			
//			@Override
//			public Map<String, String> readerFromDatabase() {
//				return new HashMap<String, String>();
//			}
//		});
//		
//		Assert.assertTrue(value.isEmpty());
//		yedis.del(key);
		
		Map<String, String> value = yedis.hgetAll(key, new Reader<Map<String,String>>() {
			
			@Override
			public Map<String, String> readerFromDatabase() {
				Map<String,String> map = new HashMap<>();
				map.put("cache-db", "ehcache-db");
				map.put("cache-db-1", "ehcache-db-1");
				return map;
			}
		});
		
		Assert.assertEquals("ehcache-db",value.get("cache-db"));
		
		value = yedis.hgetAll(key, new Reader<Map<String,String>>() {
			
			@Override
			public Map<String, String> readerFromDatabase() {
				Map<String,String> map = new HashMap<>();
				map.put("cache-db", "ehcache-db");
				map.put("cache-db-1", "ehcache-db-1");
				return map;
			}
		});
		
		Assert.assertEquals("ehcache-db",value.get("cache-db"));
		
	}
	
	@Test
	public void testHdel() {
		
		String key = "test-hdel";
		
		Map<String, String> map = new HashMap<>();
		
		map.put("test1", "1");
		map.put("test2", "2");
		
		yedis.hmset(key, map, DataFrom.DB);
		
		String value = yedis.hget(key, "test1", null);
		
//		Assert.assertEquals("1", value);
//		
//		yedis.hdel(key, "test1");
//		
//		value = yedis.hget(key, "test1", null);
//		
//		Assert.assertNull(value);
		
		
		yedis.switchLocalCache();
		
		yedis.hmset(key, map, DataFrom.DB);
		
		value = yedis.hget(key, "test1", null);
		
		Assert.assertEquals("1", value);
		
		yedis.hdel(key, "test1");
		
		value = yedis.hget(key, "test1", null);
		
		Assert.assertNull(value);
	}

}

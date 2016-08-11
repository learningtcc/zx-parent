package com.ink.base.redis.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ink.base.log.util.YinkerLogger;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-redis.xml")
public class CacheTest {
	
	@Autowired
	private Yedis yedis;
	
	private YinkerLogger logger = YinkerLogger.getLogger(CacheTest.class);
	
	@Test
	/**
	 * 测试缓存切换
	 */
	public void testSwitchCache() {
		
		for (int i = 0; i < 1000000 ; i++) {
			CacheObject value = yedis.getObject("redis-test-", String.valueOf(i), String.class,new Reader<Object>() {
				@Override
				public Object readerFromDatabase() {
					return "database";
				}
			});
			
			logger.info(Thread.currentThread().getName()+"="+Thread.currentThread().getId()+"读取数值" + value.getValue());
		}
	}
	
	
//	@Test
	/**
	 * 测试缓存更新策略
	 */
	public void testExpired() throws InterruptedException {
		
		for (int i = 0; i < 10000 ; i++) {
			CacheObject value = yedis.getObject("redis-test-", "123456", String.class,5,new Reader<Object>() {
				@Override
				public Object readerFromDatabase() {
					return "database";
				}
			});
			
			logger.info(Thread.currentThread().getName()+"="+Thread.currentThread().getId()+"读取数值" + value.getValue());
		}
		
		Thread.currentThread().sleep(120000);
	}
}
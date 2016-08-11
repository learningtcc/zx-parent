package com.ink.base.redis.client;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ink.base.log.util.YinkerLogger;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-redis.xml")
public class EhcacheTest {
	
	@Autowired
	private Yedis yedis;
	
	private ExecutorService es;
	
	private YinkerLogger logger = YinkerLogger.getLogger(EhcacheTest.class);
	
//	@Test
	/**
	 * 普通读测试
	 */
	public void testGet() {
		
		CacheObject value = yedis.getObject("ehcache-test-", "123456", String.class,new Reader<Object>() {
			@Override
			public Object readerFromDatabase() {
				return "database1";
			}
		});
		
		logger.info(Thread.currentThread().getName()+"="+Thread.currentThread().getId()+"读取数值" + value.getValue());
		
		value = yedis.getObject("ehcache-test-", "123457", String.class, new Reader<Object>() {
			@Override
			public Object readerFromDatabase() {
				return "database2";
			}
			});
		
		logger.info(Thread.currentThread().getName()+"="+Thread.currentThread().getId()+"读取数值" + value.getValue());
	}
	
//	@Test
	/**
	 * ehcache并发读测试
	 */
	public void testConcurrentRead() throws InterruptedException {
		
		final CountDownLatch end = new CountDownLatch(10000);
		
		for (int i = 0; i < 10000 ; i++) {
			es.execute(new Runnable() {
				@Override
				public void run() {
					
					CacheObject value = yedis.getObject("ehcache-test-", "123456", String.class,new Reader<Object>() {
						@Override
						public String readerFromDatabase() {
							return "database";
						}
					});
					
					logger.info(Thread.currentThread().getName()+"="+Thread.currentThread().getId()+"读取数值" + value.getValue());
					
					end.countDown();
				}
			});
		}
		
		end.await();
		
		logger.info("over");
		
	}
	
	
	@Test
	/**
	 * ehcache存储测试
	 */
	public void testCacheStore() throws InterruptedException {
		
		for (int i = 0; i < 100000 ; i++) {
			CacheObject value = yedis.getObject("ehcache-test-", "123456-"+i, String.class,new Reader<Object>() {
				@Override
				public Object readerFromDatabase() {
					return "database";
				}
			});
		}
		
		for (int i = 0; i < 100000 ; i++) {
			CacheObject value = yedis.getObject("ehcache-test-", "123456-"+i, String.class,new Reader<Object>() {
				@Override
				public Object readerFromDatabase() {
					return "database";
				}
			});
		}
		
		logger.info("over");
	}
	
	@Before
	public void setUp() throws Exception {
		es = Executors.newFixedThreadPool(10000);
	}

	@After
	public void tearDown() throws Exception {
		es.shutdown();
	}
}
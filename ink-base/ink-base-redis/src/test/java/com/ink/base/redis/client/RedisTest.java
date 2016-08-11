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
public class RedisTest {
	
	@Autowired
	private Yedis yedis;
	
	private ExecutorService es;
	
	private YinkerLogger logger = YinkerLogger.getLogger(RedisTest.class);
	
	@Test
	/**
	 * Redis普通读测试
	 */
	public void testGet() {
		
		CacheObject value = yedis.getObject("redis-test-", "123456", String.class,new Reader<Object>() {
			@Override
			public Object readerFromDatabase() {
				return "database";
			}
		});
		
		logger.info(Thread.currentThread().getName()+"="+Thread.currentThread().getId()+"读取数值" + value.getValue());
	}
	
//	@Test
	/**
	 * Redis并发读测试
	 */
	public void testConcurrentRead() throws InterruptedException {
		
		final CountDownLatch start = new CountDownLatch(10000);
		final CountDownLatch end = new CountDownLatch(10000);
		
		for (int i = 0; i < 10000 ; i++) {
			es.execute(new Runnable() {
				@Override
				public void run() {
					try {
						start.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					CacheObject value = yedis.getObject("redis-test-", "123456", String.class,new Reader<Object>() {
						@Override
						public String readerFromDatabase() {
							return "database";
						}
					});
					
					logger.info(Thread.currentThread().getName()+"="+Thread.currentThread().getId()+"读取数值" + value);
					
					end.countDown();
				}
			});
			
			start.countDown();
			
			System.out.println(start.getCount());
		}
		
		end.await();
		
		logger.info("over");
		
	}
	
	
//	@Test
	/**
	 * 锁心跳检测测试
	 */
	public void testLockHeartBeat() throws InterruptedException {
		
		
		for (int i = 0; i < 10000 ; i++) {
			CacheObject value = yedis.getObject("redis-test-", "123456-"+i, String.class,new Reader<Object>() {
				@Override
				public Object readerFromDatabase() {
					return "database";
				}
			});
			
//			logger.info("心跳检测数值"+value);
		}
		
//		es.shutdown();
//		es.awaitTermination(1, TimeUnit.HOURS);
		
		Thread.currentThread().sleep(100000);
		
		for (int i = 0; i < 10000 ; i++) {
			yedis.deleteObject("redis-test-", "123456-"+i);
		}
		
		logger.info("over");
		
	}
	
//	@Test
	/**
	 * Redis心跳监测
	 */
	public void testHeartBeat() throws InterruptedException {
		Thread.currentThread().sleep(300000);
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
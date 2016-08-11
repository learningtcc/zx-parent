package com.ink.base.redis.client;

import com.ink.base.log.util.YinkerLogger;
import com.ink.base.redis.support.DataFrom;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author haoyunfeng
 * @date 2016/8/2
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-redis.xml")
public class ExpireTest {

    @Autowired
    private Yedis yedis;

    private YinkerLogger logger = YinkerLogger.getLogger(ExpireTest.class);

    @Test
    /**
     * Redis普通读测试
     */
    public void testGet() {

        CacheObject value = yedis.getObject("redis-test-", "123456", String.class,10,new Reader<Object>() {
            @Override
            public Object readerFromDatabase() {
                return "database";
            }
        });

        logger.info(Thread.currentThread().getName()+"="+Thread.currentThread().getId()+"读取数值" + value.getValue());
    }

    @Test
    public void testCache(){
        yedis.cacheObject("redis-test","123456","haoyunfeng",10, DataFrom.OTHER);
    }

    @Test
    public void testhset(){
        yedis.hset("redis-test","123456","haoyunfeg",10,DataFrom.OTHER);
    }

    @Test
    public void testhmset(){
//        yedis.hmset()
    }
}

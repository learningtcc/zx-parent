package com.test;

import com.ink.base.utils.dateUtil.DateUtil;
import com.ink.monitor.core.service.IMongoLogChartServiceManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by aiyungui on 2016/5/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-context.xml"})
public class LogMongoTest {

    @Autowired
    private IMongoLogChartServiceManager mongoLogChartServiceManager;

    @Test
    public void saveMongoLogTest(){

        for (int i = 0; i < 100; i++) {
            mongoLogChartServiceManager.saveMinuteSysErrorLog("1006",new Date());
        }
    }

    @Test
    public void countSysErrorLogTest() throws ParseException {
        Date startDate = DateUtil.formatToyyyyMMddHHmmss("2016-07-19 17:54:00");
        Date endDate = DateUtil.formatToyyyyMMddHHmmss("2016-07-19 17:57:00");
       int count = mongoLogChartServiceManager.countSysErrorLog("1006",startDate,endDate);
        System.out.println("**********" + count);
    }
}

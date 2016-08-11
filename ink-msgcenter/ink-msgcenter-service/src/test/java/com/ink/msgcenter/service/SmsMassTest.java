package com.ink.msgcenter.service;

import com.ink.msgcenter.core.po.SmsLog;
import com.ink.msgcenter.external.sms.log.SmsLogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * Created by aiyungui on 2016/5/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/test-applicationContext.xml")
public class SmsMassTest {

    @Autowired
    SmsLogService smsLogMassService;

    @Test
    public void saveLogTest(){
        SmsLog smsLog = new SmsLog();
        smsLog.setChnCode("2016");
        smsLog.setMerctCode("2016");
        try {
            Thread.sleep(10000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//       String id = smsLogMassService.saveDb(smsLog);
//        System.out.println("*****************"+id);
    }
}

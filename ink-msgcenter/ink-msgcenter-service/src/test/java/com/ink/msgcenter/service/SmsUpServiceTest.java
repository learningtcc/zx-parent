package com.ink.msgcenter.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ink.msgcenter.external.sms.zw.job.SmsUplinkJob;

/**
 *
 * Created by aiyungui on 2016/5/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/test-applicationContext.xml")
public class SmsUpServiceTest {

    @Autowired
    private SmsUplinkJob smsUplink;

    @Test
    public void sendMessageTest(){

    	smsUplink.executeJob();
    }
}

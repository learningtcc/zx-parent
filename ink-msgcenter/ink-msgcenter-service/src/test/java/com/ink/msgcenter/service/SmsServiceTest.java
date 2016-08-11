package com.ink.msgcenter.service;

import com.alibaba.fastjson.JSONArray;
import com.ink.msgcenter.api.model.input.SmsExtInput;
import com.ink.msgcenter.api.model.input.SmsInput;
import com.ink.msgcenter.api.model.input.SmsMassInput;
import com.ink.msgcenter.api.model.output.MsgOutput;
import com.ink.msgcenter.api.service.SmsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * Created by aiyungui on 2016/5/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/test-applicationContext.xml")
public class SmsServiceTest {

    @Autowired
    private SmsService smsService;

    @Test
    public void sendMessageTest(){

        SmsInput smsInput = new SmsInput();
        smsInput.setTempId("4");
        smsInput.setBizId("2016");
        smsInput.setMerctCode("2016");
        smsInput.setMobile("18618180107");
        smsInput.setReportUrl("http://localhost:8080/cert");
        smsInput.setChnCode("507");
        smsInput.setSendTime(new Date());

        JSONArray jsonArray = new JSONArray();
        jsonArray.add("艾云桂");
        jsonArray.add("艾云桂");
        smsInput.setParamJson(jsonArray.toJSONString());

        smsService.sendSms(smsInput);
        jobTest();
    }

    @Test
    public void sendExtMessageTest(){

        SmsExtInput smsInput = new SmsExtInput();
        smsInput.setTempId("4");
        smsInput.setBizId("10000");
        smsInput.setMerctCode("10000");
        smsInput.setMobile("18618180107");
        smsInput.setReportUrl("http://localhost:8080/cert");
        smsInput.setChnCode("507");
        smsInput.setUpUrl("http://localhost:8080/cert");
        smsInput.setExtNo("2001");
        smsInput.setExtInfo("hello word");

        JSONArray jsonArray = new JSONArray();
        jsonArray.add("艾云桂");
        jsonArray.add("艾云桂");
        smsInput.setParamJson(jsonArray.toJSONString());

        MsgOutput output = smsService.sendSmsWithExt(smsInput);
        System.out.println(output);
        jobTest();
    }

    @Test
    public void sendMassMessageTest(){

        SmsMassInput massInput = new SmsMassInput();
        massInput.setTempId("4");
        massInput.setBizId("2016");
        massInput.setMerctCode("2016");
        List<String> mobiles = new ArrayList<String>();
        mobiles.add("18618180107");
        mobiles.add("18513440911");
        massInput.setReportUrl("http://localhost:8080/cert");
        massInput.setChnCode("507");
//        mobiles.add("13811945468");

        massInput.setMobileList(mobiles);
        JSONArray jsonArray = new JSONArray();
        jsonArray.add("测试群发");
        jsonArray.add("测试群发");
        massInput.setParamJson(jsonArray.toJSONString());

        MsgOutput output = smsService.sendMassSms(massInput);
        System.out.println(output);
        jobTest();
    }

    public void jobTest(){
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

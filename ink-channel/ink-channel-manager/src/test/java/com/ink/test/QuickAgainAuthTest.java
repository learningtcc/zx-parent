package com.ink.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ink.channel.api.model.in.QuickAgainValidCodeInput;
import com.ink.channel.api.model.out.QuickAgainValidCodeOutput;
import com.ink.channel.api.service.QuickAgainValidCodeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/channel-applicationContext.xml"})
public class QuickAgainAuthTest {
	 @Autowired
     private QuickAgainValidCodeService quickAgainValidCodeService;
	 @Test
     public void quickAgainAuth(){
    	 QuickAgainValidCodeInput input = new QuickAgainValidCodeInput();
    	 String orderNo = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+"dsaasd121";
 		 System.out.println(orderNo);
         input.setOrderNo(orderNo);
         input.setBankShort("PAYH");
         input.setAmount("0.01");
         input.setCardNo("6230580000021112391");//6230580000021112391//6226620208941286
         input.setUserName("刘欢");
         input.setIdType("01"); 
         input.setIdNo("371581199105253567");
         input.setPhoneNo("18612484909");
         input.setChannelId("10006");
         QuickAgainValidCodeOutput out = quickAgainValidCodeService.againValidCode(input);;
 		System.out.println("返回码："+out.getResCode());
 		System.out.println("返回描述："+out.getResMsg());
    	 
     }
}

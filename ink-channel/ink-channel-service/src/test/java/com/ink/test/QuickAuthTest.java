package com.yinker.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yinker.channel.api.model.in.QuickAuthInput;
import com.yinker.channel.api.model.out.QuickAuthOutput;
import com.yinker.channel.api.service.QuickAuthService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/channel-applicationContext.xml"})
public class QuickAuthTest {
	
	@Autowired
	private QuickAuthService quickAuthService;
	
	@Test
	public void testQuickAuth(){
		QuickAuthInput input = new QuickAuthInput();
        input.setOrderNo(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+"dsaasd121");
        input.setBankShort("PAYH");
        input.setAmount("1");
        input.setCardNo("6230580000021112391");
        input.setUserName("刘欢");
        input.setIdType("0");
        input.setIdNo("371581199105253567");
        input.setPhoneNo("18612484909");
        input.setChannelId("10004");
//        input.setChannelId("cmbcQuickAuthServiceImpl");
		QuickAuthOutput out = quickAuthService.quickAuth(input);
		System.out.println("返回码："+out.getResCode());
		System.out.println("返回描述："+out.getResMsg());
	}

}

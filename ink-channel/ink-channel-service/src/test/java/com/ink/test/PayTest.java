package com.yinker.test;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yinker.channel.api.model.in.PayAccountIn;
import com.yinker.channel.api.model.out.PayAccountOut;
import com.yinker.channel.api.service.Pay2AccountService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/channel-applicationContext.xml"})
public class PayTest {
	@Autowired
	private Pay2AccountService pay2AccountService;
	
	@Test
	public void test(){
		PayAccountIn payAccountIn=new PayAccountIn();
		payAccountIn.setAmount(new BigDecimal(1));
		payAccountIn.setOrderNo("1"+new Date().getTime());//"1"+new Date().getTime()//20160405614245dkkkjuuu
		payAccountIn.setIdentityid("201604271946051306");
		payAccountIn.setCallbackurl("aaa");
		payAccountIn.setAccountNo("6230580000021112391");//6214830102937876//6230580000021112391
		payAccountIn.setAccountName("刘欢");
		payAccountIn.setCertType("01");
//		payAccountIn.setSignId("20160316112504000000000000008786");
		payAccountIn.setCertNo("371581199105253567");
		payAccountIn.setBankShort("PAYH");
		payAccountIn.setChannelId("10005");
		PayAccountOut out = pay2AccountService.pay(payAccountIn);
		System.out.println(out.toString());
		/*PayAccountIn payAccountIn=new PayAccountIn();
		payAccountIn.setAmount(new BigDecimal(1));
		payAccountIn.setOrderNo("1"+new Date().getTime());//"1"+new Date().getTime()//20160405614245dkkkjuuu
		payAccountIn.setIdentityid("201603261412121000009649074");//201603261412121000009649074
		payAccountIn.setCallbackurl("aaa");
		payAccountIn.setAccountNo("6214830125435103");//6214830102937876//6230580000021112391
		payAccountIn.setAccountName("周响");
		payAccountIn.setCertType("01");
//		payAccountIn.setSignId("20160316112504000000000000008786");
		payAccountIn.setCertNo("130625199012122014");
		payAccountIn.setBankShort("ZSYH");
		payAccountIn.setChannelId("10005");*///翼支付 成功
		/*PayAccountIn payAccountIn=new PayAccountIn();
		payAccountIn.setAmount(new BigDecimal(1));
		payAccountIn.setOrderNo("1"+new Date().getTime());//"1"+new Date().getTime()//20160405614245dkkkjuuu
		payAccountIn.setIdentityid("201604271949318660");//201603261412121000009649074
		payAccountIn.setCallbackurl("aaa");
		payAccountIn.setAccountNo("6226910704306849");//6214830102937876//6230580000021112391
		payAccountIn.setAccountName("王宝");
		payAccountIn.setCertType("01");
//		payAccountIn.setSignId("20160316112504000000000000008786");
		payAccountIn.setCertNo("371581199105253567");
		payAccountIn.setBankShort("ZXYH");
		payAccountIn.setChannelId("10001");
//		payAccountIn.setChannelId("directBindPayService");//易宝支付 成功
//		payAccountIn.setChannelId("10002");//民生   成功collectionService
//		payAccountIn.setChannelId("10004");//快钱     商户权限不足
		PayAccountOut out = pay2AccountService.pay(payAccountIn);
		System.out.println(out.toString());*/
	}
}

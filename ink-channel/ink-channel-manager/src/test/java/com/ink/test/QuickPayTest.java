package com.ink.test;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ink.channel.api.model.in.QuickPayInput;
import com.ink.channel.api.model.out.QuickPayOutput;
import com.ink.channel.api.service.QuickPayService;

/**
 * 快捷支付测试类
 * @author huohb
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/channel-applicationContext.xml"})
public class QuickPayTest {
	
	@Autowired
	private QuickPayService quickPayService;
	
	@Test
	public void testQuickPay(){
		
		QuickPayInput input = new QuickPayInput();
		input.setOrderNo("3606241121100185");
		input.setValidCode("9361");
		input.setAmount(new BigDecimal("1"));
		input.setCardNo("6230580000021112391");
		input.setUserName("刘欢");
		input.setIdNo("371581199105253567");
		input.setIdType("01");
		input.setPhoneNo("18612484909");
		input.setBankShort("PAYH");
		input.setToken("12588063188315");
		input.setClientDate(new Date());
		input.setChannelId("10007");
		QuickPayOutput output = quickPayService.pay(input);
		System.out.println(output.getResCode());
		System.out.println(output.getResMsg());
	}

}

package com.ink.test;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ink.channel.api.model.in.QuickAgainPayInput;
import com.ink.channel.api.model.out.QuickPayOutput;
import com.ink.channel.api.service.QuickAgainPayService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/channel-applicationContext.xml"})
public class QuickAgainPayTest {
	@Autowired
	private QuickAgainPayService quickAgainPayService;
	@Test
	public void testQuickAgainPay(){
		QuickAgainPayInput input = new QuickAgainPayInput();
		input.setOrderNo("20160621181123dsaasd128");
		input.setValidCode("869849");
		input.setAmount(new BigDecimal("0.01"));
		input.setCardNo("6230580000021112391");
		input.setUserName("刘欢");
		input.setIdNo("371581199105253567");
		input.setIdType("01");
		input.setPhoneNo("18612484909");
		input.setBankShort("PAYH");
		input.setToken("231561");
		input.setClientDate(new Date());
        input.setChannelId("10006");
        QuickPayOutput out = quickAgainPayService.againPay(input);
		System.out.println("返回码："+out.getResCode());
		System.out.println("返回描述："+out.getResMsg());
	}
}

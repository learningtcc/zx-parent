package com.ink.test;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ink.channel.api.model.in.Pay2CardInput;
import com.ink.channel.api.model.out.Pay2CardOutput;
import com.ink.channel.api.service.Pay2CardService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/channel-applicationContext.xml"})
public class Pay2CardTest {
	
	@Autowired
	private Pay2CardService pay2CardService;
	
	@Test
	public void testPay2Card(){
		Pay2CardInput input = new Pay2CardInput();
		input.setCardNo("6230580000021112391");//6214830102937876//6230580000021112391
		input.setBankShort("PAYH");
		input.setIdNo("371581199105253567");//130430199104261513//371581199105253567
		input.setIdType("01");
		input.setUserName("刘欢");
		input.setAmount(new BigDecimal("1"));
//		input.setOrderNo(CmbcPayInfo.merchantNoRechargeAndCash+"1"+new Date().getTime());
		System.out.println("OrderNo:"+input.getOrderNo());
		input.setIdentityId("60021000000028547307");
		input.setChannelId("10002");//翼支付   成功
//		input.setChannelId("cmbcPay2CardServiceImpl");//民生  成功
//		input.setChannelId("yeepayPay2CardServiceImpl");//易宝   未开通委托结算功能
//		input.setChannelId("masPay2CardServiceImpl");//快钱 失败
//		input.setChannelId("bFPay2CardService");//宝付
		Pay2CardOutput out = pay2CardService.pay(input);
		System.out.println("返回码："+out.getResCode());
		System.out.println("返回描述："+out.getResMsg());
		System.out.println("返回描述："+out.getOrderNo());
		System.out.println("返回描述："+out.toString());
	}

}

package com.yinker.test;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yinker.channel.api.model.in.QuickAgainPayInput;
import com.yinker.channel.api.model.out.QuickPayOutput;
import com.yinker.channel.api.service.QuickAgainPayService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/channel-applicationContext.xml"})
public class QuickAgainPayTest {
	@Autowired
	private QuickAgainPayService quickAgainPayService;
	@Test
	public void testQuickAgainPay(){
		QuickAgainPayInput input = new QuickAgainPayInput();
        input.setOrderNo("20160524174220dsaasd121");
        input.setBankShort("PAYH");
        input.setAmount(new BigDecimal("1"));
        input.setCardNo("6230580000021112391");
        input.setPhoneNo("18612484909");
        input.setToken("413075120");
        input.setValidCode("018068");
        input.setChannelId("10004");
        QuickPayOutput out = quickAgainPayService.againPay(input);
		System.out.println("返回码："+out.getResCode());
		System.out.println("返回描述："+out.getResMsg());
	}
}

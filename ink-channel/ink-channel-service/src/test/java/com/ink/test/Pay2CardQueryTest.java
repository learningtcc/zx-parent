package com.yinker.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yinker.channel.api.model.in.Pay2CardQueryInput;
import com.yinker.channel.api.model.out.Pay2CardQueryOutput;
import com.yinker.channel.api.service.Pay2CardQueryService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/channel-applicationContext.xml"})
public class Pay2CardQueryTest {
	
	@Autowired
	private Pay2CardQueryService pay2CardQueryService;

	@Test
	public void testPay2CardQuery(){
		
		Pay2CardQueryInput input = new Pay2CardQueryInput();
		//input.setChannelId("cmbcPay2CardQueryServiceImpl");
		input.setChannelId("10005");
		input.setOrderNo("737488154646798337");//11463035012911//11463034532914
		Pay2CardQueryOutput out = pay2CardQueryService.query(input);
		
		System.out.println("返回码："+out.getResCode());
		System.out.println("返回描述："+out.getResMsg());
		System.out.println("订单状态："+out.getOrderStatus());
		
	}
}

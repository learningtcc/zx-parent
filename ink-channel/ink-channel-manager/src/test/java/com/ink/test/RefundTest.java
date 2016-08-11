package com.ink.test;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ink.channel.api.model.in.RefundInput;
import com.ink.channel.api.model.out.RefundOutput;
import com.ink.channel.api.service.RefundService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/channel-applicationContext.xml"})
public class RefundTest {
	
	@Autowired
	private RefundService refundService;
	
	@Test
	public void testRefund(){
		RefundInput input = new RefundInput();
		input.setAmount(new BigDecimal("1"));
		input.setChannelId("masRefundServiceImpl");
//		input.setChannelId("yeepayRefundServiceImpl");
		input.setEntryTime(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		input.setOrderNo("20160310140537dsaasd121");
		input.setOrigRefNumber("001501648568");
		RefundOutput out = refundService.refund(input);
		
		System.out.println("返回码："+out.getResCode());
		System.out.println("返回信息："+out.getResMsg());
	}

}

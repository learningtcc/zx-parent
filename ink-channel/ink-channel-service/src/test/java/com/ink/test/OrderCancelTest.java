package com.yinker.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yinker.channel.api.model.in.OrderCancelIn;
import com.yinker.channel.api.model.out.OrderCancelOut;
import com.yinker.channel.api.service.OrderCancelService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/channel-applicationContext.xml"})
public class OrderCancelTest {
	@Autowired
	private OrderCancelService orderCancelService;
	@Test
	public void test(){
		OrderCancelIn orderCancelIn=new OrderCancelIn();
		orderCancelIn.setAmount("1");
		orderCancelIn.setEntryTime("1460020543131");
		orderCancelIn.setOrgTranFlow("wewewwdddddddd");
		orderCancelIn.setOrderid("20160405614245revgtccgt");
		orderCancelIn.setChannelId("revokeTodayPayService");
		OrderCancelOut out = orderCancelService.orderCancel(orderCancelIn);
		System.out.println(out.getResCode());
		System.out.println(out.getResMsg());
	}
}

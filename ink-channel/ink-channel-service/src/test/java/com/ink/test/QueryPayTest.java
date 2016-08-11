package com.yinker.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yinker.channel.api.model.in.QueryPayAccountIn;
import com.yinker.channel.api.model.out.QueryPayAccountOut;
import com.yinker.channel.api.service.Pay2AccountQueryService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/channel-applicationContext.xml"})
public class QueryPayTest {
	@Autowired
	private Pay2AccountQueryService pay2AccountQueryService;
	@Test
	public void test(){
		QueryPayAccountIn in=new QueryPayAccountIn();
		in.setOrderNo("2016053111312213610");//11460023682360//170146002368191600
		in.setChannelId("10005");//翼支付
//		in.setChannelId("queryByTradeOrderService");//易宝
//		in.setChannelId("queryCollectionService");//民生
//		in.setChannelId("queryTradeOrderService");//快钱
		QueryPayAccountOut out = pay2AccountQueryService.accountQuery(in);
		System.out.println(out.toString());
	}
}

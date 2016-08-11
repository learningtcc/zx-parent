package com.ink.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ink.channel.api.enums.ChannelBizType;
import com.ink.channel.api.model.in.QueryPayAccountIn;
import com.ink.channel.api.model.out.QueryPayAccountOut;
import com.ink.channel.api.service.Pay2AccountQueryService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/channel-applicationContext.xml"})
public class QueryPayTest {
	@Autowired
	private Pay2AccountQueryService pay2AccountQueryService;
	@Test
	public void test(){
		QueryPayAccountIn in=new QueryPayAccountIn();
		in.setOrderNo("CF300003485111466653235545");//11460023682360//170146002368191600//CF300003485111464329992751//20160621181123dsaasd121
		in.setChannelId("10005");
		in.setQueryType(ChannelBizType.AUTHEN_NO_VALID_CODE_QUERY_PAY.getCode());
		QueryPayAccountOut out = pay2AccountQueryService.accountQuery(in);
		System.out.println(out.toString());
	}
}

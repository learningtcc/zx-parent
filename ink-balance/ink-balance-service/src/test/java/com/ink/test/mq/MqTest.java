package com.ink.test.mq;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/applicationContext.xml")
public class MqTest {
	@Resource
	AmqpTemplate amqpTemplate;

	@Test
	public void platformDataTest() {
		Map<String, Object> params = new HashMap<String, Object>();

		// 交易状态 1、待支付 2、支付成功 3、支付失败
		params.put("amt", "10.00");
		params.put("platformOrderNo", "10086110022");
		params.put("payTime", "2016-03-03 12:00:00");
		params.put("payStatus", "1");

		amqpTemplate.convertAndSend("yinker.balance.platform.notifyData", params);

	}
	@Test
	public void updatePlatformDataTest() {
		Map<String, Object> params = new HashMap<String, Object>();

		// 交易状态 1、待支付 2、支付成功 3、支付失败
		params.put("platformOrderNo", "10086110022");
		params.put("arrivedAmt", "10.00");
		params.put("channelNo", "1111");
		params.put("arrivedTime", "2016-03-31 12:00:00");
		//params.put("transNo", "");
		params.put("payStatus", "2");

		amqpTemplate.convertAndSend("yinker.balance.platform.updateData", params);

	}

	@Test
	public void batch() {
		for(int i=10020000;i<10070000;i++){
			//--------------------------------------
			Map<String, Object> params = new HashMap<String, Object>();
			// 交易状态 1、待支付 2、支付成功 3、支付失败
			params.put("amt", "10.00");
			params.put("platformOrderNo", i+"");
			params.put("payTime", "2016-03-03 12:00:00");
			params.put("payStatus", "1");

			amqpTemplate.convertAndSend("yinker.balance.platform.notifyData", params);

			//--------------------------------------

			Map<String, Object> params1 = new HashMap<String, Object>();
			// 交易状态 1、待支付 2、支付成功 3、支付失败
			params1.put("platformOrderNo", i+"");
			params1.put("arrivedAmt", "10.00");
			params1.put("channelNo", "1111");
			params1.put("arrivedTime", "2016-03-31 12:00:00");
			//params.put("transNo", "");
			params1.put("payStatus", "2");

			amqpTemplate.convertAndSend("yinker.balance.platform.updateData", params1);

		}

	}

}
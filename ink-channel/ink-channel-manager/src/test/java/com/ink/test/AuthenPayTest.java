package com.ink.test;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ink.base.utils.IdCodeGenerator;
import com.ink.channel.api.model.in.AuthenPayInput;
import com.ink.channel.api.model.out.AuthenPayOutput;
import com.ink.channel.api.service.AuthenPayService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/channel-applicationContext.xml"})
public class AuthenPayTest {
	@Autowired
    private IdCodeGenerator idCodeGenerator;
	@Autowired
	private AuthenPayService authenPayService;
	@Test
	public void testAuthenPay(){
		AuthenPayInput input=new AuthenPayInput();
		input.setAmount(new BigDecimal("1"));
		input.setIdentityid("201604271949318660");
		input.setOrderNo(idCodeGenerator.getId());
		input.setTradeDate(new Date());
		input.setChannelId("10005");
		AuthenPayOutput out = authenPayService.authenPay(input);
		System.out.println(out.toString());
	}
}

package com.ink.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ink.base.utils.IdCodeGenerator;
import com.ink.channel.api.model.in.AuthenBindCardInput;
import com.ink.channel.api.model.out.AuthenBindCardOutput;
import com.ink.channel.api.service.AuthenBindCardService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/channel-applicationContext.xml"})
public class AuthenBindCardTet {
	@Autowired
    private IdCodeGenerator idCodeGenerator;
	@Autowired
	private AuthenBindCardService authenBindCardService;
	@Test
	public void testAuthenBind(){
		AuthenBindCardInput input=new AuthenBindCardInput();
		 input.setAccountNo("6222020111122220000");
		  String repid = idCodeGenerator.getId();//"1001QP022016052602052699"
		  input.setOrderNo(repid);
		  input.setPhoneNo("18612484909");
		  input.setCertNo("320301198502169142");
		  input.setAccountName("张宝");
		  input.setBankShort("ZGGSYH");
		  input.setCertType("01");
		  input.setChannelId("10005");
		  AuthenBindCardOutput out = authenBindCardService.bindCard(input);
		  System.out.println(out.toString());
	}
}

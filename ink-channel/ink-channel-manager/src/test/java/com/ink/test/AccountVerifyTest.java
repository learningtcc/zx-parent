package com.ink.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ink.channel.core.minsheng.service.CmccAccountVerifyService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/channel-applicationContext.xml"})
public class AccountVerifyTest {
	
	@Autowired
	private CmccAccountVerifyService cmccAccountVerifyService;
	
	@Test
	public void testVerify(){
		cmccAccountVerifyService.queryHistory();
	}
}

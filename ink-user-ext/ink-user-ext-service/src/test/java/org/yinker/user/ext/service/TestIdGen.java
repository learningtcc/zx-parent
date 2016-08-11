package org.yinker.user.ext.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ink.user.ext.core.po.UserInfo;
import com.ink.user.ext.core.service.IUserInfoManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class TestIdGen {

	@Autowired
	private IUserInfoManager userInfoManager;
	@Test
	public void Test() throws Exception{
		UserInfo u = new UserInfo();
		u.setId(2l);
		userInfoManager.save(u);
		System.out.println("aa");

	}
}


package com.yinker.code;

import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yinker.channel.api.model.in.ValidCodeInput;
import com.yinker.channel.api.model.out.ValidCodeOutput;
import com.yinker.channel.api.service.ValidCodeService;
import com.yinker.channel.core.epro.RequestUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:dubbo/dubbo-consumer.xml" })
public class TestVaildCode {
	
	@Autowired
	private ValidCodeService validCodeService;
	
	@Test
	public void testCode(){
		ValidCodeInput authority = new ValidCodeInput();
	   	 /*authority.setUserName(null);*/
	   	 authority.setPhoneNo("1861248490934342342342");
        authority.setUserName("刘欢");
        authority.setIdNo("371581199105253567");
        authority.setIdType("01");
        authority.setCardNo("6230580000021112391");
        authority.setIdentityid("60021000000128549483");
        String repid = RequestUtil.getRequestId();
        System.out.println(repid);
        authority.setReqId(repid);
        authority.setPhoneNo("186");
        authority.setChannelId("10002");
        ValidCodeOutput code = validCodeService.getValidCode(authority);
		System.out.println(code.getResCode());
	}
}

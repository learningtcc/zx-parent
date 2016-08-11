package com.ink.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ink.channel.api.model.in.ValidCodeInput;
import com.ink.channel.api.model.out.ValidCodeOutput;
import com.ink.channel.api.service.ValidCodeService;
import com.ink.channel.core.epro.RequestUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/channel-applicationContext.xml" })
public class ValidCodeTest {
    @Autowired
    private ValidCodeService validCodeService;

    @Test
    public void getValidCode() {
    	 ValidCodeInput authority = new ValidCodeInput();
        /* authority.setUserName("周响");
         authority.setIdNo("130625199012122014");
         authority.setIdType("01");
         authority.setCardNo("6214830125435103");
         authority.setIdentityid("60021000000128549483");
         String repid = RequestUtil.getRequestId();
         System.out.println(repid);
         authority.setReqId(repid);
         authority.setPhoneNo("18701633161");
         authority.setBankShort("ZSYH");*/
    	 authority.setUserName("刘欢");
         authority.setIdNo("371581199105253567");
         authority.setIdType("01");
         authority.setCardNo("6230580000021112391");
         authority.setIdentityid("201604271949318660");
         String repid = RequestUtil.getRequestId();
         System.out.println(repid);
         authority.setReqId(repid);
         authority.setPhoneNo("18612484909");
         authority.setBankShort("PAYH");
    	 /*authority.setUserName("刘欢");
         authority.setIdNo("371581199105253567");
         authority.setIdType("01");
         authority.setCardNo("6230580000021112382");
         authority.setIdentityid("60021000000128549483");
         String repid = RequestUtil.getRequestId();
         System.out.println(repid);
         authority.setReqId(repid);
         authority.setPhoneNo("18612484909");
         authority.setBankShort("PAYH");*/
         authority.setChannelId("10005");
         ValidCodeOutput code = validCodeService.getValidCode(authority);//quickPayValidCodeService//eproValidCodeService
         System.out.println(code.getResCode());
    }
}
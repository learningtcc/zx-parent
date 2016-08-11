package com.yinker.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yinker.base.utils.IdCodeGenerator;
import com.yinker.channel.api.model.in.AuthorityInput;
import com.yinker.channel.api.model.out.AuthorityOutput;
import com.yinker.channel.api.service.AuthorityService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/channel-applicationContext.xml" })
public class AuthorityTest {
    @Autowired
    private IdCodeGenerator idCodeGenerator;
    @Autowired
    private AuthorityService authorityService;

    @Test
    public void test() {
        // cmbcAuthenticationService bestPaySignService eproAuthService
        // String realName = "霍洪波";
        // String idno = "130430199104261513";
        // String cardNo = "6214830102937876";

        AuthorityInput authority = new AuthorityInput();
        /*
         * authority.setValidCode("150393"); authority.setCardNo("6230580000021112543");
         * authority.setIdentityId("10001yzf2016050311051730135800"); String repid = "TZTBINDBANKCARD160503_111411569";
         * authority.setReqId(repid); authority.setPhoneNo("18612484909"); authority.setIdNo("371581199105254567");
         * authority.setUserName("刘欢"); authority.setBankShort("PAYH"); authority.setCardType("0");
         * authority.setIdType("01");
         */
        authority.setValidCode("914012");
        authority.setCardNo("6230580000021112391");
        authority.setIdentityId("990902882822929299342243");
        String repid = "TZTBINDBANKCARD160520_112704456";
        authority.setReqId(repid);
        authority.setPhoneNo("18612484909");
        authority.setIdNo("371581199105253567");
        authority.setUserName("刘欢");
        authority.setBankShort("PAYH");
        authority.setCardType("0");
        authority.setIdType("01");
        authority.setChannelId("10005");// bestPaySignService
        // authority.setChannelId("eproAuthService");
        // authority.setChannelId("cmbcAuthenticationService");
        // authority.setChannelId("quickPayAuth");
        AuthorityOutput out = authorityService.authorize(authority);
        System.out.println(out.getResCode());
        System.out.println(out.getResMsg());
    }

    public void test3() {
      System.out.println(idCodeGenerator.getId());
    }
}
package com.ink.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ink.channel.api.model.in.AuthorityInput;
import com.ink.channel.api.model.out.AuthorityOutput;
import com.ink.channel.api.service.AuthorityService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/channel-applicationContext.xml"})
public class AuthorityTest {
  @Autowired
  private AuthorityService authorityService;
  @Test
  public void test(){
      //cmbcAuthenticationService  bestPaySignService eproAuthService
      //String realName = "霍洪波";
      //String idno = "130430199104261513";
      //String cardNo = "6214830102937876";
      
	  AuthorityInput  authority=new AuthorityInput();
	  /*authority.setValidCode("150323");
	  authority.setCardNo("6230580000021112391");
	  authority.setIdentityId("1001cs201606060d4064530178746");
	  String repid = "TZTBINDBANKCARD160614_172205179";
      authority.setReqId(repid);
	  authority.setPhoneNo("18612484909");
      authority.setIdNo("371581199105253567");
      authority.setUserName("刘欢");
      authority.setBankShort("PAYH");
      //authority.setCardType("0");
      authority.setIdType("01");
      authority.setToken("133396");
      authority.setChannelId("10005");*/
	  authority.setValidCode("914012");
	  authority.setCardNo("6222020111122220000");
	  authority.setIdentityId("990902882822929299342243");//1001cs2016052602052630148050//990902882822929299342243
	  String repid = "73720873705589fffff14574";//"1001QP022016052602052699"
	  //String repid = RequestUtil.getRequestId();
      authority.setReqId(repid);
	  authority.setPhoneNo("18612484909");
      authority.setIdNo("320301198502169142");
      authority.setUserName("张宝");
      authority.setBankShort("ZGGSYH");
      authority.setCardType("0");
      authority.setIdType("01");
      authority.setToken("107898");
      authority.setChannelId("10005");//bestPaySignService
      //authority.setChannelId("eproAuthService");
      //authority.setChannelId("cmbcAuthenticationService");
      //authority.setChannelId("quickPayAuth");
      AuthorityOutput out=  authorityService.authorize(authority);
      System.out.println(out.getResCode());
      System.out.println(out.getResMsg());
  }
}
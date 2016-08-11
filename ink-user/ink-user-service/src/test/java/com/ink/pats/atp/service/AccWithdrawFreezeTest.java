package com.ink.pats.atp.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ink.base.utils.IdCodeGenerator;
import com.ink.user.api.model.in.AccWithdrawFreezeInput;
import com.ink.user.api.model.out.RetOutput;
import com.ink.user.api.service.IAccWithdrawFreezeService;
import com.ink.user.util.DateUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class AccWithdrawFreezeTest {

	
	@Autowired
	private IAccWithdrawFreezeService accWithdrawFreezeService;
	@Autowired
    IdCodeGenerator idCodeGenerator;
	
	@Test
	public void AWFTest() throws Exception{
		String txnCode = "AWF";
		String mchId="1000000000002";
		String custId="123456";
		String ordId = idCodeGenerator.getId();
		System.out.println("ordId="+ordId);
		String tradeDate = DateUtils.getDateTime();
		System.out.println("tradeDate="+tradeDate);
		String amt = "0.1";
		String custFee = "0.00";
		String sacType="0001";
		String cardNo="622880199437863789";
		String channelId="3000201529439671604";
		String mac = DigestUtils.md5Hex("com.ink.trade!user"+ordId);
		
		AccWithdrawFreezeInput dto = new AccWithdrawFreezeInput();
		dto.setTxnCode(txnCode);
		dto.setMchId(mchId);
		dto.setCustId(custId);
		dto.setOrdId(ordId);
		dto.setTradeDate(tradeDate);
		dto.setAmt(amt);
		dto.setCustFee(custFee);
		dto.setAccountType(sacType);
		dto.setCardNo(cardNo);
		dto.setChannelId(channelId);
		dto.setMac(mac);
		RetOutput ret = accWithdrawFreezeService.exec(dto);
		System.out.println(ret.toString());
	}
}

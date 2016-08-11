package com.ink.pats.atp.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ink.base.utils.IdCodeGenerator;
import com.ink.user.api.model.in.AccWithdrawAcceptInput;
import com.ink.user.api.model.out.RetOutput;
import com.ink.user.api.service.IAccWithdrawAcceptService;
import com.ink.user.util.DateUtils;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class AccWithdrawAcceptTest {

	
	@Autowired
	private IAccWithdrawAcceptService accWithdrawAcceptService;
	@Autowired
    IdCodeGenerator idCodeGenerator;
	
	@Test
	public void Acc30220Test() throws Exception{
		String txnCode = "AWA";//充值
		String mchId="1000000000002";
		String ordId = idCodeGenerator.getId();
		String tradeDate = DateUtils.getDateTime();
		String oriOrdId = "999155210002810000";
		String oritradeDate= "20160801152320";
		String channelId="3000201529439671604";
		String amt = "0.1";
		String custFee = "0.00";
		String oriTxnCode="AWF";
		String mac = DigestUtils.md5Hex("com.ink.trade!user"+ordId);
		
		AccWithdrawAcceptInput dto = new AccWithdrawAcceptInput();
		dto.setTxnCode(txnCode);
		dto.setMchId(mchId);
		dto.setOrdId(ordId);
		dto.setOriTradeDate(oritradeDate);
		dto.setOriOrdId(oriOrdId);
		dto.setTradeDate(tradeDate);
		dto.setChannelId(channelId);
		dto.setAmt(amt);
		dto.setCustFee(custFee);
		dto.setOriTxnCode(oriTxnCode);
		dto.setCustFee(custFee);
		dto.setMac(mac);
		RetOutput ret = accWithdrawAcceptService.exec(dto);
		System.out.println(ret.toString());
	}
}

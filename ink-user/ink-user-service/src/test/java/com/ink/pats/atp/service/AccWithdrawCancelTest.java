package com.ink.pats.atp.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ink.base.utils.IdCodeGenerator;
import com.ink.user.api.model.in.AccWithdrawCancelInput;
import com.ink.user.api.model.out.RetOutput;
import com.ink.user.api.service.IAccWithdrawCancelService;
import com.ink.user.util.DateUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class AccWithdrawCancelTest {
	
	@Autowired
	private IAccWithdrawCancelService accWithdrawCancelService;
	@Autowired
    IdCodeGenerator idCodeGenerator;
	
	@Test
	public void test() throws Exception{
		String txnCode = "AWC";
		String mchId="1000000000002";
		String ordId = idCodeGenerator.getId();
		String tradeDate = DateUtils.getDateTime();
		String oriOrdId = "999113553973700000";
		String oriTxnCode="AWF";
		String oritradeDate= "20160614101637";
		
		AccWithdrawCancelInput dto = new AccWithdrawCancelInput();
		dto.setTxnCode(txnCode);
		dto.setMchId(mchId);
		dto.setOrdId(ordId);
		dto.setOriOrdId(oriOrdId);
		dto.setOriTxnCode(oriTxnCode);
		dto.setOriTradeDate(oritradeDate);
		dto.setTradeDate(tradeDate);
		RetOutput ret = accWithdrawCancelService.exec(dto);
		System.out.println(ret.toString());
	}
}

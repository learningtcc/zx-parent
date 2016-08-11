package com.ink.pats.atp.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ink.base.utils.IdCodeGenerator;
import com.ink.user.api.model.in.UnbundCardInput;
import com.ink.user.api.model.out.UnbundCardOutput;
import com.ink.user.api.service.IUnbundCardService;
import com.ink.user.util.DateUtils;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class UnbundCardTest {

	@Autowired
	private IUnbundCardService unbundCardService;
	@Autowired
    IdCodeGenerator idCodeGenerator;
	@Test
	public void test() throws Exception{
		String txnCode = "UC";
		String mchId="1000000000002";
		String custId="20000610";
		String bindCardId="";
		String tradeDate = DateUtils.getDateTime();
		String cardNo = "622880199437863789";
		String depositType="APT0000001";
		
		UnbundCardInput dto = new UnbundCardInput();
		dto.setTxnCode(txnCode);
		dto.setMchId(mchId);
		dto.setCustId(custId);
		dto.setTradeDate(tradeDate);
		dto.setCardNo(cardNo);
		dto.setBindCardId(bindCardId);
		dto.setOrdId(idCodeGenerator.getId());
		UnbundCardOutput ret = unbundCardService.exec(dto);
		System.out.println(ret.toString());
	}
}

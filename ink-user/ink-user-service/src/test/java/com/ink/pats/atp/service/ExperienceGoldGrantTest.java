package com.ink.pats.atp.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ink.base.utils.IdCodeGenerator;
import com.ink.user.api.model.in.ExperienceGoldGrantInput;
import com.ink.user.api.model.out.RetOutput;
import com.ink.user.api.service.IExperienceGoldGrantService;
import com.ink.user.util.DateUtils;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class ExperienceGoldGrantTest {

	@Autowired
	private IExperienceGoldGrantService experienceGoldGrantService;
	@Autowired
    IdCodeGenerator idCodeGenerator;
	
	@Test
	public void Acc30250Test() throws Exception{
		String txnCode = "EGG";
		String mchId="1000000000002";
		String custId="20000610";
		String tradeDate = DateUtils.getDateTime();
		String amt = "1.00";
		String sacType="0004";
		String depositType="APT0000001";
		String ordId = idCodeGenerator.getId();
		
		ExperienceGoldGrantInput dto = new ExperienceGoldGrantInput();
		dto.setTxnCode(txnCode);
		dto.setMchId(mchId);
		dto.setCustId(custId);
		dto.setTradeDate(tradeDate);
		dto.setAmt(amt);
		dto.setAccountType(sacType);
		dto.setOrdId(ordId);
		RetOutput ret = experienceGoldGrantService.exec(dto);
		System.out.println(ret.toString());
	}
}

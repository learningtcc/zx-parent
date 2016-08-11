package com.ink.pats.atp.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ink.base.utils.IdCodeGenerator;
import com.ink.user.api.constants.AtpTnsConstant;
import com.ink.user.api.model.in.MchAcountTransferInput;
import com.ink.user.api.model.out.RetOutput;
import com.ink.user.api.service.IMchAcountTransferService;
import com.ink.user.util.DateUtils;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class MchAcountTransferTest {
	
	
	@Autowired
	private IMchAcountTransferService mchAcountTransferService;
	@Autowired
    IdCodeGenerator idCodeGenerator;
	
	@Test
	public void Acc37010Test() throws Exception{
		
		MchAcountTransferInput dto = new MchAcountTransferInput();
		dto.setTxnCode("MAT");
		dto.setOrdId(idCodeGenerator.getId());
		dto.setTradeDate(DateUtils.getDateTime());
		dto.setAmt("0.03");
		
		dto.setCustId("20000610");
		dto.setCustAccountType("0001");
		dto.setMchId("1000000000002");
		dto.setMchAccountType("0011");
		dto.setDir(AtpTnsConstant.DIR_CREDIT);
		RetOutput ret = mchAcountTransferService.exec(dto);
		System.out.println(ret.toString());
	}
}

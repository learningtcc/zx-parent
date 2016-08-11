package com.ink.pats.atp.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ink.base.utils.IdCodeGenerator;
import com.ink.user.api.model.in.CheckCustInput;
import com.ink.user.api.model.out.CheckCustOutput;
import com.ink.user.api.service.ICheckCustService;
import com.ink.user.util.DateUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class CheckCustTest {

	@Autowired
	private ICheckCustService checkCustService;
	@Autowired
    IdCodeGenerator idCodeGenerator;

	@Test
	public void acc33010Ttest() throws Exception {
		CheckCustInput dto = new CheckCustInput();
		dto.setTxnCode("CC");
		dto.setCustId("20000610");
		dto.setMchId("1000000000002");
		dto.setTradeDate(DateUtils.getDateTime());
		dto.setOrdId(idCodeGenerator.getId());
		CheckCustOutput  output = checkCustService.exec(dto);
		System.out.println(output.toString());
	}
}

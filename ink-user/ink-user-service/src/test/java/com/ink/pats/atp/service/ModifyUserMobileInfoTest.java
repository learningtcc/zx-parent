package com.ink.pats.atp.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ink.base.utils.IdCodeGenerator;
import com.ink.user.api.model.in.ModifyUserMobileInfoInput;
import com.ink.user.api.service.IModifyUserMobileInfoService;
import com.ink.user.util.DateUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class ModifyUserMobileInfoTest {

	@Autowired
	private IModifyUserMobileInfoService modifyUserMobileInfoService;
	@Autowired
    IdCodeGenerator idCodeGenerator;

	@Test
	public void test() throws Exception {
		ModifyUserMobileInfoInput dto = new ModifyUserMobileInfoInput();
		dto.setTxnCode("MUMI");
		dto.setOrdId(idCodeGenerator.getId());
		dto.setTradeDate(DateUtils.getDateTime());
		
		dto.setCustId("20000610");
		dto.setMchId("1000000000002");
		dto.setIdNo("130430199104261513");
		dto.setIdType("01");
		dto.setMblNo("18513440911");
		modifyUserMobileInfoService.exec(dto);
	}
}

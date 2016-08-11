package com.ink.pats.atp.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ink.base.utils.IdCodeGenerator;
import com.ink.user.api.model.in.ModifyUserBaseInfoInput;
import com.ink.user.api.service.IModifyUserBaseInfoService;
import com.ink.user.util.DateUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class ModifyUserBaseInfoTest {

	@Autowired
	private IModifyUserBaseInfoService modifyUserBaseInfoService;
	@Autowired
    IdCodeGenerator idCodeGenerator;

	/**
	 * 用户修改基本资料
	 * @throws Exception
     */
	@Test
	public void test() throws Exception {
		ModifyUserBaseInfoInput dto = new ModifyUserBaseInfoInput();
		dto.setCustName("custName");
		dto.setSex("M");
		dto.setBirthday("2016-01-01");
		dto.setTelNo("110");
		dto.setEmail("eamil");
		dto.setZipcode("1234567");
		dto.setAddress("互联网金融中心");
		dto.setTxnCode("MUBI");
		dto.setOrdId(idCodeGenerator.getId());
		dto.setTradeDate(DateUtils.getDateTime());
		
		dto.setCustId("123457");
		dto.setMchId("1000000000002");
		dto.setIdNo("13012519890327656");
		dto.setIdType("01");
		dto.setMblNo("18513441234");
		modifyUserBaseInfoService.exec(dto);
	}
}

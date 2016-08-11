package com.ink.pats.atp.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ink.base.utils.IdCodeGenerator;
import com.ink.user.api.model.in.QueryMchInfoInput;
import com.ink.user.api.model.out.QueryMchInfoOutput;
import com.ink.user.api.service.IQueryMchInfoService;
import com.ink.user.util.DateUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class QueryMchInfoTest {

	@Autowired
	private IQueryMchInfoService QueryMchInfoService;
	@Autowired
    IdCodeGenerator idCodeGenerator;

	@Test
	public void acc33010Ttest() throws Exception {
		QueryMchInfoInput dto = new QueryMchInfoInput();
		dto.setTxnCode("QMI");
		dto.setMchId("1000000000002");
		dto.setTradeDate(DateUtils.getDateTime());
		dto.setOrdId(idCodeGenerator.getId());
		QueryMchInfoOutput output = QueryMchInfoService.exec(dto);
		System.out.println(output.toString());
	}
}

package com.ink.pats.atp.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ink.base.utils.IdCodeGenerator;
import com.ink.user.api.model.in.QueryMchAccBalInput;
import com.ink.user.api.model.out.QueryMchAccBalOutput;
import com.ink.user.api.service.IQueryMchAccBalService;
import com.ink.user.util.DateUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class QueryMchAccBalTest {

	@Autowired
	private IQueryMchAccBalService queryMchAccBalService;
	@Autowired
    IdCodeGenerator idCodeGenerator;
	
	@Test
	public void test() throws Exception{
		QueryMchAccBalInput in = new QueryMchAccBalInput();
		in.setMchAccountType("0011");
		in.setMchId("1000000000002");
		in.setOrdId(idCodeGenerator.getId());
		in.setTradeDate(DateUtils.getDateTime());
		in.setTxnCode("QMAB");
		QueryMchAccBalOutput out = queryMchAccBalService.exec(in);
		System.out.println(out.toString());
	}
}

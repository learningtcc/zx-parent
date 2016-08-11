package com.ink.pats.atp.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ink.base.utils.IdCodeGenerator;
import com.ink.user.api.model.in.QueryTransResultInput;
import com.ink.user.api.model.out.QueryTransResultOutput;
import com.ink.user.api.service.IQueryTransResultService;
import com.ink.user.util.DateUtils;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class QueryTransResultTest {
	@Autowired
	private IQueryTransResultService queryTransResultService;
	@Autowired
    IdCodeGenerator idCodeGenerator;
	
	@Test
	public void Test() throws Exception{
		String txnCode = "QTR";//个人账户查询
		String mchId="1000000000002";
		String tradeDate = DateUtils.getDateTime();
		String ordId = idCodeGenerator.getId();
		String oriOrdId = "999112717200040000";
		String oriTxnCode = "AT";
		
		QueryTransResultInput dto = new QueryTransResultInput();
		dto.setTxnCode(txnCode);
		dto.setMchId(mchId);
		dto.setTradeDate(tradeDate);
		dto.setOrdId(ordId);
		dto.setOriOrdId(oriOrdId);
		dto.setOriTxnCode(oriTxnCode);
		QueryTransResultOutput ret= queryTransResultService.exec(dto);
		System.out.println(ret.toString());
	}

}

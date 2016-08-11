package com.ink.pats.atp.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ink.user.api.model.in.QueryCustAccBalInput;
import com.ink.user.api.model.out.QueryCustAccBalOutput;
import com.ink.user.api.service.IQueryCustAccBalService;
import com.ink.user.util.DateUtils;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class QueryCustAccBalTest {
	
	@Autowired
	private IQueryCustAccBalService queryCustAccBalService;
	
	@Test
	public void Acc38060Test() throws Exception{
		String txnCode = "QCAB";//个人账户查询
		String mchId="1000000000002";
		String custId="20000610";
		String accountType="";
		String tradeDate = DateUtils.getDateTime();
		String depositType="APT0000001";
		String memo="个人账户余额查询操作";
		
		QueryCustAccBalInput dto = new QueryCustAccBalInput();
		dto.setTxnCode(txnCode);
		dto.setMchId(mchId);
		dto.setCustId(custId);
		dto.setTradeDate(tradeDate);
		dto.setAccountType(accountType);
		dto.setMemo(memo);
		QueryCustAccBalOutput ret= queryCustAccBalService.exec(dto);
		System.out.println(ret.toString());
	}

}
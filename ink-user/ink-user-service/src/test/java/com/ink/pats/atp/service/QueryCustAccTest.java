package com.ink.pats.atp.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ink.user.api.model.in.QueryCustAccInput;
import com.ink.user.api.model.out.QueryCustAccOutput;
import com.ink.user.api.service.IQueryCustAccService;
import com.ink.user.util.DateUtils;

/**
 * 
 * @ClassName: QueryCustAccTest 
 * @Description: 个人账户查询 
 * @author guojie.gao 
 * @date 2015年12月1日 上午11:40:56 
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class QueryCustAccTest {
	
	@Autowired
	private IQueryCustAccService queryCustAccService;
	
	@Test
	public void Test() throws Exception{
		String txnCode = "QCA";//个人账户查询
		String mchId="1000000000002";
		String custId="20000610";
		//String accountType="0001";
		String tradeDate = DateUtils.getDateTime();
		
		QueryCustAccInput dto = new QueryCustAccInput();
		dto.setTxnCode(txnCode);
		dto.setMchId(mchId);
		dto.setCustId(custId);
		dto.setTradeDate(tradeDate);
		//dto.setAccountType(accountType);
		QueryCustAccOutput ret= queryCustAccService.exec(dto);
		System.out.println(ret.toString());
	}

}

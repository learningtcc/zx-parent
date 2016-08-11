package com.ink.pats.atp.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ink.base.utils.IdCodeGenerator;
import com.ink.user.api.model.InterestBean;
import com.ink.user.api.model.in.AccInterestInput;
import com.ink.user.api.service.IAccInterestService;
import com.ink.user.util.DateUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class AccInterestTest {

	@Autowired
	private IAccInterestService accInterestService;
	@Autowired
	private IdCodeGenerator idCodeGenerator;

	@Test
	public void test() throws Exception {
		String txnCode = "AI";// 内部转账
		String ordId = idCodeGenerator.getId();
		String tradeDate = DateUtils.getDateTime();
		String mchId = "1000000000002";

		AccInterestInput dto = new AccInterestInput();
		dto.setTxnCode(txnCode);
		dto.setId(ordId);
		dto.setTradeDate(tradeDate);
		dto.setMchId(mchId);
		List<InterestBean> list = getList();
		dto.setList(list);
		String ret = accInterestService.exec(dto).toString();
		System.out.println(ret);
		Thread.sleep(60000);
	}

	private List<InterestBean> getList() {
		List<InterestBean> list = new ArrayList<InterestBean>();
		InterestBean interest = new InterestBean();
		interest.setAccountType("0001");
		interest.setAmt("3.00");
		interest.setCustId("20000610");
		String ordId = idCodeGenerator.getId();
		interest.setOrdId(ordId);
		String tradeDate = DateUtils.getDateTime();
		interest.setTradeDate(tradeDate);
		interest.setUid("");
		list.add(interest);
		return list;
	}
}

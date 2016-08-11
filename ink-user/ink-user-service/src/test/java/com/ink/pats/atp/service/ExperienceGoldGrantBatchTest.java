package com.ink.pats.atp.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ink.base.utils.IdCodeGenerator;
import com.ink.user.api.model.GoldGrantBean;
import com.ink.user.api.model.in.ExperienceGoldGrantBatchInput;
import com.ink.user.api.service.IExperienceGoldGrantBatchService;
import com.ink.user.util.DateUtils;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class ExperienceGoldGrantBatchTest {
	
	@Autowired
	private IExperienceGoldGrantBatchService experienceGoldGrantBatchService;
	@Autowired
    IdCodeGenerator idCodeGenerator;
	
	@Test
	public void test() throws Exception{
		String txnCode = "EGGB";
		String ordId = idCodeGenerator.getId();
		String tradeDate = DateUtils.getDateTime();
		String mchId="1000000000002";

		ExperienceGoldGrantBatchInput dto = new ExperienceGoldGrantBatchInput();
		dto.setTxnCode(txnCode);
		dto.setId(ordId);
		dto.setTradeDate(tradeDate);
		dto.setMchId(mchId);
		List<GoldGrantBean> list = getList() ;
		dto.setList(list);
		String ret = experienceGoldGrantBatchService.exec(dto).toString();
		System.out.println(ret);
	}

	private List<GoldGrantBean> getList() {
		List<GoldGrantBean> list = new ArrayList<GoldGrantBean>();
		GoldGrantBean goldGrant = new GoldGrantBean();
		goldGrant.setAmt("1.00");
		goldGrant.setCustId("20000610");
		String ordId = idCodeGenerator.getId();
		goldGrant.setOrdId(ordId);
		String tradeDate = DateUtils.getDateTime();
		goldGrant.setTradeDate(tradeDate);
		goldGrant.setUid("");
		goldGrant.setAccountType("0004");
		list.add(goldGrant);
		return list;
	}
}


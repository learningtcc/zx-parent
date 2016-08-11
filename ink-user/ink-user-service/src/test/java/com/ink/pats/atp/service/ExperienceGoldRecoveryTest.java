package com.ink.pats.atp.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ink.base.utils.IdCodeGenerator;
import com.ink.user.api.model.GoldRecoveryBean;
import com.ink.user.api.model.in.ExperienceGoldRecoveryInput;
import com.ink.user.api.service.IExperienceGoldRecoveryService;
import com.ink.user.util.DateUtils;

/**
 * @Description: 体验金回收
 * @author wanghao^_^
 * @date 2016年6月13日 下午2:22:21
 * @version V1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class ExperienceGoldRecoveryTest {
	
	@Autowired
	private IExperienceGoldRecoveryService experienceGoldRecoveryService;
	@Autowired
    IdCodeGenerator idCodeGenerator;
	
	@Test
	public void test() throws Exception{
		String txnCode = "EGR";
		String ordId = idCodeGenerator.getId();
		String tradeDate = DateUtils.getDateTime();
		String mchId="1000000000002";

		ExperienceGoldRecoveryInput dto = new ExperienceGoldRecoveryInput();
		dto.setTxnCode(txnCode);
		dto.setId(ordId);
		dto.setTradeDate(tradeDate);
		dto.setMchId(mchId);
		List<GoldRecoveryBean> list = getList() ;
		dto.setList(list);
		String ret = experienceGoldRecoveryService.exec(dto).toString();
		System.out.println(ret);
	}

	private List<GoldRecoveryBean> getList() {
		List<GoldRecoveryBean> list = new ArrayList<GoldRecoveryBean>();
		GoldRecoveryBean goldRecorery = new GoldRecoveryBean();
		goldRecorery.setAmt("1.00");
		goldRecorery.setCustId("20000610");
		String ordId = idCodeGenerator.getId();
		goldRecorery.setOrdId(ordId);
		String tradeDate = DateUtils.getDateTime();
		goldRecorery.setTradeDate(tradeDate);
		goldRecorery.setUid("");
		list.add(goldRecorery);
		return list;
	}
}


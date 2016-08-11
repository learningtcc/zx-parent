package com.ink.pats.atp.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ink.base.utils.IdCodeGenerator;
import com.ink.user.api.model.in.CheckBindCardInput;
import com.ink.user.api.model.out.RetOutput;
import com.ink.user.api.service.ICheckBindCardService;
import com.ink.user.util.DateUtils;

/**
 * 
 * @ClassName: ACC38120Test
 * @Description: 个人绑卡前置检测单元测试类
 * @author wanghao
 * @date 2016年4月22日 上午10:32:22
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class CheckBindCardTest {

	@Autowired
	private ICheckBindCardService checkBindCardService;
	@Autowired
    IdCodeGenerator idCodeGenerator;

	@Test
	public void test() throws Exception {
		String txnCode = "CBC";
		String mchId = "1000000000002";
		String custId = "20000610";
		String tradeDate = DateUtils.getDateTime();
		String cardNo = "622880199437863777";
		String cardType = "0";
		String bankMblNo = "13800138001";
		String expDate = "";
		String cvn2 = "";
		String custName = "wanghao";
		String idType = "01";
		String idNo = "152801199309111111";
		String ordId = idCodeGenerator.getId();

		CheckBindCardInput dto = new CheckBindCardInput();
		dto.setTxnCode(txnCode);
		dto.setMchId(mchId);
		dto.setCustId(custId);
		dto.setTradeDate(tradeDate);
		dto.setCardNo(cardNo);
		dto.setCardType(cardType);
		dto.setBankMblNo(bankMblNo);
		dto.setExpDate(expDate);
		dto.setCvn2(cvn2);
		dto.setCustName(custName);
		dto.setIdType(idType);
		dto.setIdNo(idNo);
		dto.setCustName(custName);
		dto.setIdType(idType);
		dto.setIdNo(idNo);
		dto.setOrdId(ordId);
		RetOutput ret = checkBindCardService.exec(dto);
		System.out.println(ret.toString());
	}
}

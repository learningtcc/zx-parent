package com.ink.trade.service.test;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ink.trade.api.enums.TradeCurrency;
import com.ink.trade.api.model.in.PrepaidInput;
import com.ink.trade.api.model.out.PrepaidOutput;
import com.ink.trade.service.controller.WithdrawController;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class WithdrawControllerTest {
	
	@Autowired
	private WithdrawController withdrawController;
	
	@Test
	public void test(){
		PrepaidInput record = new PrepaidInput();

		record.setMerchantId("1000000000002");//商户号
		record.setAccountType("0001");//账户类型
		record.setAmt(new BigDecimal(1));//交易金额
		record.setBankShort("ZSYH");//
		record.setCardNo("6214830125935903");
		record.setCurrency(TradeCurrency.CNY);
		record.setIdNo("130625199012122014");
		record.setNoticeUrl("adress");
		record.setOrderId("123456");
		record.setPayType("");
		record.setPhoneNo("123");
		record.setRealName("周响");
		record.setTradeCode("提现");
		record.setUserId("21234567806");
		
		PrepaidOutput out = withdrawController.testWithdraw(record);
		System.out.println(out.toString());
		
	}

}

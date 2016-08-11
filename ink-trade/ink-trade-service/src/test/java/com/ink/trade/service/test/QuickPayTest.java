/**
 * yinker.com Copyright (c) 2016-2025 All Rights Reserved.
 *
 * @Description TODO
 * @author xuguoqi
 * @date 2016年4月25日 下午1:48:35
 */
package com.ink.trade.service.test;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ink.base.log.util.YinkerLogger;
import com.ink.trade.api.enums.PayType;
import com.ink.trade.api.enums.TradeType;
import com.ink.trade.api.model.in.TradeQuickAuthInput;
import com.ink.trade.api.model.in.TradeQuickPayInput;
import com.ink.trade.api.model.out.TradeQuickAuthOutput;
import com.ink.trade.api.model.out.TradeQuickPayOutput;
import com.ink.trade.api.service.ITradeQuickAuthService;
import com.ink.trade.api.service.ITradeQuickPayService;

/**
 * @Description TODO
 * @author xuguoqi
 * @date 2016年4月25日 下午1:48:35
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class QuickPayTest {
	YinkerLogger log = YinkerLogger.getLogger(QuickPayTest.class);

	@Autowired
	private ITradeQuickAuthService tradeQuickAuthService;
	
	@Autowired
	private ITradeQuickPayService tradeQuickPayService;

	@Test
	public void testQuickAuth(){
		//首次支付
		TradeQuickAuthInput quickAuthInput = new TradeQuickAuthInput();
		quickAuthInput.setMerchantId("1000000000002");
//		quickAuthInput.setBankShort("MSYH"); 
		quickAuthInput.setCardNo("6216910103639123");
//		quickAuthInput.setCardType(CardType.DebitCard);
//		quickAuthInput.setRealName("许国旗");
//		quickAuthInput.setIdNo("411425198804214219");
//		quickAuthInput.setIdType(IdType.IdentificationCard);
		quickAuthInput.setMerchantId("1000000000002");
		quickAuthInput.setPayType(PayType.QUICKPAY.getValue());
//		quickAuthInput.setPhoneNo("13552073712");
		quickAuthInput.setSignOrderId("888888888888888812946555");
        quickAuthInput.setTradeCode(TradeType.RECHARGE.getCode());
        quickAuthInput.setUserId("20000612");
//        quickAuthInput.setCvv2("");
        quickAuthInput.setAmt(new BigDecimal(0.01));
        quickAuthInput.setAccountType("0001");
		TradeQuickAuthOutput out = tradeQuickAuthService.quickAuth(quickAuthInput);
		System.out.println(out.getReponseCode());
		
	}
	
	@Test
	public void testQuickPay(){
		 TradeQuickPayInput quickPayInput=new TradeQuickPayInput();
	        quickPayInput.setCardNo("6216910103639123");
	        quickPayInput.setMerchantId("1000000000002");
	        quickPayInput.setPayType(PayType.QUICKPAY.getValue());
	        quickPayInput.setOrderId("8888888888888888124");
	        quickPayInput.setTradeCode(TradeType.RECHARGE.getCode());
	        quickPayInput.setUserId("20000612");
	        quickPayInput.setAccountType("0001");
	        quickPayInput.setValidCode("844109");
	        TradeQuickPayOutput out = tradeQuickPayService.quickPay(quickPayInput);
	        System.out.println(out.getReponseCode());

	}

}

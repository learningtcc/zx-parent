package com.ink.trade.service.test;

import java.math.BigDecimal;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ink.trade.api.enums.PayType;
import com.ink.trade.api.enums.TradeType;
import com.ink.trade.api.model.in.PrecollenctionInput;
import com.ink.trade.api.model.in.PrepaidInput;
import com.ink.trade.api.model.in.UnbindInput;
import com.ink.trade.api.model.out.PrecollectionOutput;
import com.ink.trade.api.model.out.PrepaidOutput;
import com.ink.trade.api.model.out.UnbindOutput;
import com.ink.trade.api.service.IPrecollectionService;
import com.ink.trade.api.service.IPrepaidService;
import com.ink.trade.api.service.IUnbindService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class WithDrawServiceTest {

	@Autowired
	private IPrepaidService withdrawService;
	@Autowired
	private IUnbindService unbindService;
	@Autowired
	private IPrecollectionService precollectionService;

	/**
	 * @date 2016年4月28日 上午10:28:13
	 * @Description: 余额提现
	 * @author wanghao
	 */
	@Test
	public void withdrawTest() {
		PrepaidInput record = new PrepaidInput();

		record.setMerchantId("1000000000002");//商户号
		record.setAccountType("0001");//账户类型
		record.setAmt(new BigDecimal(4));//交易金额
		record.setCardNo("");//银行卡号
		record.setOrderId(String.valueOf(System.currentTimeMillis()));//订单号，唯一
		record.setPayType(PayType.PAY.getValue());//代付支付类型
		record.setTradeCode(TradeType.WITHDRAW.getCode());//交易码，提现
		record.setUserId("20000610");//用户号
		record.setNoticeUrl("");//回调url
		try {
			PrepaidOutput rs = withdrawService.withdraw(record);
			System.out.println("+++++"+ToStringBuilder.reflectionToString(rs));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testCollection() {
		PrecollenctionInput record = new PrecollenctionInput();
		record.setMerchantId("1000000000002");
		record.setAccountType("0001");
		// record.setAccountType("0002");
		// record.setAccountType("0003");
		record.setAmt(new BigDecimal(4));
		record.setBankShort("ZSYH");
		record.setCardNo("6214830125435403");
		// record.setCurrency(TradeCurrency.CNY);
		// record.setIdNo("130625199012122014");
		// record.setInterestAmt(new BigDecimal(2));
		// record.setNoticeUrl("adress");
		record.setOrderId(String.valueOf(System.currentTimeMillis()));
		record.setPayType("DS");
		// record.setPhoneNo("123");
		// record.setRealName("周响");
		record.setTradeCode("I");
		// record.setTradeDate(new Date());
		record.setUserId("20000610");
		PrecollectionOutput output = precollectionService.recharge(record);
		System.out.println("+++++"+output);

	}

	@Test
	public void test6() {
		UnbindInput unbindInput=	new UnbindInput();
		unbindInput.setCardNo("6214830125435403");
		unbindInput.setMerchantId("1000000000002");
		unbindInput.setPayType(PayType.ALL.getValue());
		unbindInput.setUserId("20000610");
	UnbindOutput output=	unbindService.unbind(unbindInput);
	System.out.println(output);
	}
}

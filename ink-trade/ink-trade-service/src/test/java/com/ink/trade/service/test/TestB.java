package com.ink.trade.service.test;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ink.channel.api.enums.ChannelBizType;
import com.ink.channel.api.model.in.QueryPayAccountIn;
import com.ink.channel.api.model.out.QueryPayAccountOut;
import com.ink.channel.api.service.Pay2AccountQueryService;
import com.ink.channel.service.impl.Pay2AccountQueryServiceImpl;
import com.ink.trade.api.enums.CardType;
import com.ink.trade.api.enums.IdType;
import com.ink.trade.api.enums.PayType;
import com.ink.trade.api.enums.TradeType;
import com.ink.trade.api.model.in.PayDirectInput;
import com.ink.trade.api.model.in.PrecollenctionInput;
import com.ink.trade.api.model.in.PrepaidInput;
import com.ink.trade.api.model.in.SignApplyInput;
import com.ink.trade.api.model.in.SignConfirmInput;
import com.ink.trade.api.model.in.TradeQuickAuthInput;
import com.ink.trade.api.model.in.TradeQuickPayInput;
import com.ink.trade.api.model.in.UnbindInput;
import com.ink.trade.api.model.out.PayDirectOutput;
import com.ink.trade.api.model.out.PrecollectionOutput;
import com.ink.trade.api.model.out.PrepaidOutput;
import com.ink.trade.api.model.out.SignApplyOutput;
import com.ink.trade.api.model.out.SignConfirmOutput;
import com.ink.trade.api.model.out.TradeQuickAuthOutput;
import com.ink.trade.api.model.out.TradeQuickPayOutput;
import com.ink.trade.api.model.out.UnbindOutput;
import com.ink.trade.api.service.ICertifiedPayDirectService;
import com.ink.trade.api.service.IPrecollectionService;
import com.ink.trade.api.service.IPrepaidService;
import com.ink.trade.api.service.ISignApplyService;
import com.ink.trade.api.service.ISignConfirmService;
import com.ink.trade.api.service.ITradeQuickAuthService;
import com.ink.trade.api.service.ITradeQuickPayService;
import com.ink.trade.api.service.IUnbindService;
import com.ink.user.api.enums.AccSacTypeEnum;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class TestB {
	@Autowired
	private ITradeQuickAuthService tradeQuickAuthService;
	@Autowired
	private ITradeQuickPayService tradeQuickPayService;
	@Autowired
	private IPrepaidService withdrawService;
	@Autowired
	private IPrecollectionService iRechargeService;
	@Autowired
	private ICertifiedPayDirectService certifiedPayService;
	@Autowired
	private IUnbindService unbindService;
	@Autowired
	private ISignApplyService signApplyService;
    @Autowired
    private ISignConfirmService signConfirmService;

	@Test
	/**
	 * 绑卡申请
	 */
	public void testSignApply() {
		SignApplyInput signApplyInput = new SignApplyInput();
		signApplyInput.setBankShort("");//银行卡所属银行简码
		signApplyInput.setCardNo("");//卡号
		signApplyInput.setCardType(CardType.DebitCard);//卡类型
		signApplyInput.setRealName("");//银行卡开户名
		signApplyInput.setIdNo("");//证件号
		signApplyInput.setIdType(IdType.IdentificationCard);//证件类型
		signApplyInput.setMerchantId("1000000000002");//商户号
		signApplyInput.setPayType(PayType.ALL.getValue());//固定值
		signApplyInput.setPhoneNo("");//手机号
		signApplyInput.setSignOrderId("7374757516243025963");//订单号
		signApplyInput.setTradeCode(TradeType.SIGN.getCode());//交易码
		signApplyInput.setUserId("20000610");//用户号
		signApplyInput.setCvv2("");

		SignApplyOutput out = signApplyService.signApply(signApplyInput);
		System.out.println(out.toString());
	}

	/**
	 * 绑卡确认
	 */
	@Test
	public void signConfirm() {
		SignConfirmInput signConfirmInput = new SignConfirmInput();
		signConfirmInput.setPayType(PayType.ALL.getValue());//固定值
		signConfirmInput.setSignOrderId("7374757516243025963");//绑卡申请订单号
		signConfirmInput.setValidMessage("719559");//短信验证码
		signConfirmInput.setMerchantId("1000000000002");//商户号
		signConfirmInput.setUserId("20000610");//用户号
		signConfirmInput.setTradeCode(TradeType.SIGN.getCode());//交易码
		SignConfirmOutput out = signConfirmService.signConfirm(signConfirmInput);
		System.out.println(out);
	}

	@Test
	/**
	 * 快捷鉴权
	 */
	public void test1() {
		TradeQuickAuthInput quickAuthInput = new TradeQuickAuthInput();
		quickAuthInput.setAmt(new BigDecimal(0.01));
		quickAuthInput.setCardNo("");//银行卡卡号
//		quickAuthInput.setCardType(CardType.DebitCard);//银行卡类型
//		quickAuthInput.setIdNo("");//证件号
//		quickAuthInput.setIdType(IdType.IdentificationCard);//证件类型
		quickAuthInput.setMerchantId("1000000000002");//商户号
		quickAuthInput.setPayType(PayType.QUICKPAY.getValue());//支付类型
//		quickAuthInput.setPhoneNo("");//手机号
//		quickAuthInput.setRealName("");//用户名
		quickAuthInput.setSignOrderId("111199999931");//快捷鉴权订单号
		quickAuthInput.setTradeCode(TradeType.RECHARGE.getCode());//交易码
		quickAuthInput.setUserId("20000610");//用户号
		quickAuthInput.setAccountType("0001");//账户类型
		quickAuthInput.setNoticeUrl("");//回调地址
		TradeQuickAuthOutput output = tradeQuickAuthService.quickAuth(quickAuthInput);
		System.out.println("+++" + ToStringBuilder.reflectionToString(output));
	}

	@Test
	/**
	 * 快捷支付
	 */
	public void test2() {
		TradeQuickPayInput quickPayInput = new TradeQuickPayInput();
		quickPayInput.setCardNo("");//银行卡号
		quickPayInput.setMerchantId("1000000000002");//商户号
		quickPayInput.setPayType(PayType.QUICKPAY.getValue());//支付类型
		quickPayInput.setOrderId("111199999931");//快捷鉴权订单号
		quickPayInput.setTradeCode(TradeType.RECHARGE.getCode());//交易码
		quickPayInput.setUserId("20000610");//用户号
		quickPayInput.setAccountType("0001");//账户类型
		quickPayInput.setValidCode("656258");//短信验证码
		TradeQuickPayOutput output = tradeQuickPayService.quickPay(quickPayInput);
		System.out.println("+++" + ToStringBuilder.reflectionToString(output));
	}

	/**
	 * @date 2016年4月28日 上午10:28:13
	 * @Description: 代付
	 * @author wanghao
	 */
	@Test
	public void withdrawTest() {
		PrepaidInput record = new PrepaidInput();

		record.setMerchantId("1000000000002");// 商户号
		record.setAccountType("0001");// 账户类型
		record.setAmt(new BigDecimal(4));// 交易金额
		record.setCardNo("");// 银行卡号
		record.setOrderId(String.valueOf(System.currentTimeMillis()));// 订单号，唯一
		record.setPayType(PayType.PAY.getValue());// 代付支付类型
		record.setTradeCode(TradeType.WITHDRAW.getCode());// 交易码，提现
		record.setUserId("20000610");// 用户号
		record.setNoticeUrl("");// 回调url
		try {
			PrepaidOutput rs = withdrawService.withdraw(record);
			System.out.println("+++++" + ToStringBuilder.reflectionToString(rs));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	/**
	 * 代收
	 */
	public void test() {

		PrecollenctionInput input = new PrecollenctionInput();
		input.setAccountType(AccSacTypeEnum.CURRENT.getValue());// 账户类型
		input.setUserId("20000610");// 用户号
		input.setAmt(new BigDecimal("100"));// 金额
		// 订单号
		input.setOrderId(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()).concat(String.valueOf(new Random().nextInt(10000))));
		input.setCardNo("");// 银行卡号
		input.setMerchantId("1000000000002");// 商户号
		input.setPayType(PayType.COLLECT.getValue());// 代收-支付类型
		input.setTradeCode(TradeType.RECHARGE.getCode());// 交易码
		input.setNoticeUrl("");// 回调地址
		PrecollectionOutput output = iRechargeService.recharge(input);
		String result = ToStringBuilder.reflectionToString(output);
		System.out.println("-------:" + result);
	}

	@Test
	/**
	 * 认证
	 */
	public void test4() {
		PayDirectInput input = new PayDirectInput();
		input.setAccountType("0001");// 账户类型
		input.setAmt(new BigDecimal(1.0));// 交易金额
		input.setCardNo("");// 银行卡号
		input.setMerchantId("1000000000002");// 商户号-简理财
		// 订单号，唯一
		input.setOrderId(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()).concat(String.valueOf(new Random().nextInt(10000))));
		input.setPayType(PayType.AUTHPAY.getValue());// 认证支付类型
		input.setTradeCode(TradeType.RECHARGE.getCode());// 交易码，充值
		input.setUserId("20000610");// 用户号
		input.setNoticeUrl("");
		PayDirectOutput output = certifiedPayService.pay(input);
		System.out.println(ToStringBuilder.reflectionToString(output));
	}

	@Test
	/**
	 * 解绑
	 */
	public void test6() {
		UnbindInput unbindInput = new UnbindInput();
		unbindInput.setCardNo("6214830125435403");
		unbindInput.setMerchantId("1000000000002");
		unbindInput.setPayType(PayType.ALL.getValue());
		unbindInput.setUserId("20000610");
		UnbindOutput output = unbindService.unbind(unbindInput);
		System.out.println(output);
	}
	@Autowired
	private Pay2AccountQueryService pay2AccountQueryService;
	@Test
	public void test11(){
		QueryPayAccountIn in=new QueryPayAccountIn();
		in.setOrderNo("101138893119510000");//11460023682360//170146002368191600//CF300003485111464329992751//20160621181123dsaasd121
		in.setChannelId("10005");
		in.setMerchantNo("1000000000002");
		in.setQueryType(ChannelBizType.WITHDRAW_QUERY.getCode());
		QueryPayAccountOut out = pay2AccountQueryService.accountQuery(in);
		System.out.println(out.toString());
	}
	@Autowired
	private Pay2AccountQueryServiceImpl a;
	@Test
	public void test12(){
		a.getKey("10005", "a");
	}
}

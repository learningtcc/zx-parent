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

import com.ink.trade.api.enums.PayType;
import com.ink.trade.api.enums.TradeType;
import com.ink.trade.api.model.in.PayDirectInput;
import com.ink.trade.api.model.out.PayDirectOutput;
import com.ink.trade.api.service.ICertifiedPayDirectService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class CertifiedPayTest {
    @Autowired
    private ICertifiedPayDirectService certifiedPayService;
    @Test
    public void test1(){
        PayDirectInput input=new PayDirectInput();
        input.setAccountType("0001");//账户类型
        input.setAmt(new BigDecimal(1.0));//交易金额
        input.setCardNo("");//银行卡号
        input.setMerchantId("1000000000002");//商户号-简理财
        //订单号，唯一
        input.setOrderId(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()).concat(String.valueOf(new Random().nextInt(10000))));
        input.setPayType(PayType.AUTHPAY.getValue());//认证支付类型
        input.setTradeCode(TradeType.RECHARGE.getCode());//交易码，充值
        input.setUserId("20000610");//用户号
        input.setNoticeUrl("");
        PayDirectOutput output=  certifiedPayService.pay(input);
        System.out.println(ToStringBuilder.reflectionToString(output));
    }

}

package com.ink.trade.service.test;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ink.trade.api.enums.PayType;
import com.ink.trade.api.enums.RouteBusinessType;
import com.ink.trade.api.enums.TradeType;
import com.ink.trade.api.model.in.AsileRouteInput;
import com.ink.trade.api.model.in.OrderQueryInput;
import com.ink.trade.api.model.in.PrecollenctionInput;
import com.ink.trade.api.model.out.AsileRouteOutput;
import com.ink.trade.api.model.out.OrderQueryOutput;
import com.ink.trade.api.model.out.PrecollectionOutput;
import com.ink.trade.api.rule.IAsileRoute;
import com.ink.trade.api.service.IPrecollectionService;
import com.ink.trade.api.service.ITradeOrderQueryService;
import com.ink.user.api.enums.AccSacTypeEnum;

/**
 * Created by huohb on 2016/4/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class PayTest {

    @Autowired
    private IPrecollectionService iRechargeService;
    @Autowired
    private ITradeOrderQueryService iTradeOrderQueryService;
    @Autowired
    private IAsileRoute asileRoute;
    @Test
    public void test() {

        PrecollenctionInput input = new PrecollenctionInput();
        input.setAccountType(AccSacTypeEnum.CURRENT.getValue());//账户类型
        input.setUserId("20000610");//用户号
        input.setAmt(new BigDecimal("100"));//金额
        //订单号
        input.setOrderId(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()).concat(String.valueOf(new Random().nextInt(10000))));
        input.setCardNo("");//银行卡号
        input.setMerchantId("1000000000002");//商户号
        input.setPayType(PayType.COLLECT.getValue());//代收-支付类型
        input.setTradeCode(TradeType.RECHARGE.getCode());//交易码
        input.setNoticeUrl("");//回调地址
        PrecollectionOutput output = iRechargeService.recharge(input);
        String result = ToStringBuilder.reflectionToString(output);
        System.out.println("-------:"+result);
    }

    @Test
    public void orderQuery() {
        OrderQueryInput input = new OrderQueryInput();
        input.setMerchantId("1000000000002");
        input.setOrderId("20160505151708");
        OrderQueryOutput output = iTradeOrderQueryService.tradeOrderQuery(input);
        System.out.println(ToStringBuilder.reflectionToString(output));
    }

    @Test
    public void testRoute() {
        final CountDownLatch latch = new CountDownLatch(300);
        for(int i = 0 ;i < 300 ;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int j = 0 ;j < 50;j++){
                        AsileRouteInput condition = new AsileRouteInput();
                        condition.setAmt(new BigDecimal(1));
                        condition.setBankShort("ZSYH");
                        condition.setCardId("6214830102931111");
                        condition.setCardType("0");
                        condition.setMchId("1000000000002");
                        condition.setTradeDate(new Date());

                        condition.setRouteBusinessType(RouteBusinessType.PAYMENT);

                        AsileRouteOutput output = null;
                        try {
                            long start = System.currentTimeMillis();
                            output = asileRoute.getTradeAsile(condition);
                            long end = System.currentTimeMillis();
                            System.out.println("=====路由耗时====="+(end - start)+"ms");
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            latch.countDown();
                        }
                    }
                }
            }).start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("=======所有线程都执行完成=======");
    }


}

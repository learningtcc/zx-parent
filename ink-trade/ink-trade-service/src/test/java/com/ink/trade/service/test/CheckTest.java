package com.ink.trade.service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ink.base.log.util.YinkerLogger;
import com.ink.trade.api.model.in.BindCardQueryInput;
import com.ink.trade.api.model.in.SignConfirmInput;
import com.ink.trade.api.service.IBindCardQueryService;
import com.ink.trade.api.service.ISignConfirmService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class CheckTest {
    YinkerLogger logger=YinkerLogger.getLogger(CheckTest.class);
    @Autowired
    private IBindCardQueryService bindCardQuery;
    @Autowired
    private ISignConfirmService signConfirmService;

    @Test
    public void aa() {
        SignConfirmInput signConfirmInput = new SignConfirmInput();
        signConfirmInput.setSignOrderId("1462503395320");
        signConfirmInput.setValidMessage("123456");
        signConfirmInput.setMerchantId("1000000000002");
        signConfirmInput.setUserId("21234567806");
        signConfirmInput.setTradeCode("B");
        
        
        signConfirmService.signConfirm(signConfirmInput);
    }
    @Test
    public void test1(){
        logger.info("1001003", "10010030001", "testesaaa");
    }
    @Test
    public void test3(){
       BindCardQueryInput bindCardInput=new BindCardQueryInput();
       bindCardInput.setUserId("21234567806");
       bindCardInput.setMerchantId("1000000000002");
        bindCardQuery.bindCardQuery(bindCardInput);
    }
}

package com.ink.trade.service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ink.trade.api.enums.PayType;
import com.ink.trade.api.model.in.SignConfirmInput;
import com.ink.trade.api.model.out.SignConfirmOutput;
import com.ink.trade.api.service.ISignConfirmService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class SignConfirmTest {

    @Autowired
    private ISignConfirmService signConfirmService;
    @Test
    public void signConfirm() {
        SignConfirmInput signConfirmInput = new SignConfirmInput();
//        signConfirmInput.setVersion("1.0");
//        signConfirmInput.setPayType("99");
//        signConfirmInput.setSignOrderId("1464607343818");
//        signConfirmInput.setValidMessage("719559");
//        signConfirmInput.setVersion("1.0");
//        signConfirmInput.setMerchantId("1000000000002");
//        signConfirmInput.setUserId("2000038040001");
//        signConfirmInput.setTradeCode("B");
//        signConfirmInput.setPayType("");
        
//        signConfirmInput.setPayType("DK");
//        signConfirmInput.setSignOrderId("737475751624302597");
//        signConfirmInput.setValidMessage("719559");
//        signConfirmInput.setVersion("1.0");
//        signConfirmInput.setMerchantId("1000000000002");
//        signConfirmInput.setUserId("20000612");
//        signConfirmInput.setTradeCode("B");
        
        signConfirmInput.setPayType(PayType.ALL.getValue());
        signConfirmInput.setSignOrderId("7374757516243025963");
        signConfirmInput.setValidMessage("719559");
        signConfirmInput.setMerchantId("1000000000002");
        signConfirmInput.setUserId("20000610");
        signConfirmInput.setTradeCode("A");
        
        
      SignConfirmOutput out=  signConfirmService.signConfirm(signConfirmInput);
      System.out.println(out);
    }
}

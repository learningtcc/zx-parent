/**
 * yinker.com Copyright (c) 2016-2025 All Rights Reserved.
 *
 * @Description TODO
 * @author xuguoqi
 * @date 2016年4月25日 下午1:48:35
 */
package com.ink.trade.service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ink.base.log.util.YinkerLogger;
import com.ink.trade.api.enums.CardType;
import com.ink.trade.api.enums.IdType;
import com.ink.trade.api.model.in.SignApplyInput;
import com.ink.trade.api.model.out.SignApplyOutput;
import com.ink.trade.api.service.ISignApplyService;

/**
 * @Description TODO
 * @author xuguoqi
 * @date 2016年4月25日 下午1:48:35
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class SignApplyTest {
	YinkerLogger log = YinkerLogger.getLogger(SignApplyTest.class);

    @Autowired
    private ISignApplyService signApplyService;

    /**
     *
     * String mchId="1000000000002";
     String custId="21234567806";
     String custName="zhouxiang";
     String idType = "01";
     String idNo = "143102938471";
     String bankMblNo="13800138001";
     String cardType="0";//银行卡类型 0-借记卡 1-信用卡 2-准贷记卡 3-储蓄账户 4-企业结算账户 9-未知

     */


    @Test
    public void testSignApply() {
        SignApplyInput signApplyInput = new SignApplyInput();
//        signApplyInput.setVersion("1.0");
//        signApplyInput.setBankShort("ZSYH");
//        signApplyInput.setCardNo("6214830125435409");
//        signApplyInput.setCardType(CardType.DebitCard);
//        signApplyInput.setRealName("周响");
//        signApplyInput.setIdNo("130625199012122014");
//        signApplyInput.setIdType(IdType.IdentificationCard);
//        signApplyInput.setMerchantId("1000000000002");
//        signApplyInput.setPayType("DS");
//        signApplyInput.setPhoneNo("18701633161");
//        signApplyInput.setSignOrderId(String.valueOf(new Date().getTime()));
//        signApplyInput.setTradeCode("A");
//        signApplyInput.setUserId("2000038040001");
//        signApplyInput.setCvv2("");
        
//        signApplyInput.setVersion("1.0");
//        signApplyInput.setBankShort("GDYH");
//        signApplyInput.setCardNo("6226620208941286");
//        signApplyInput.setCardType(CardType.DebitCard);
//        signApplyInput.setRealName("刘欢");
//        signApplyInput.setIdNo("371581199105253567");
//        signApplyInput.setIdType(IdType.IdentificationCard);
//        signApplyInput.setMerchantId("1000000000002");
//        signApplyInput.setPayType("DK");
//        signApplyInput.setPhoneNo("15101663327");
//        signApplyInput.setSignOrderId("737475751624302597");
//        signApplyInput.setTradeCode("A");
//        signApplyInput.setUserId("20000612");
//        signApplyInput.setCvv2("");
        
        signApplyInput.setBankShort("CMB");
        signApplyInput.setCardNo("6214830125435402");
        signApplyInput.setCardType(CardType.DebitCard);
        signApplyInput.setRealName("周响");
        signApplyInput.setIdNo("130625199012122014");
        signApplyInput.setIdType(IdType.IdentificationCard);
        signApplyInput.setMerchantId("1000000000002");
        signApplyInput.setPayType("QB");
        signApplyInput.setPhoneNo("18701633161");
        signApplyInput.setSignOrderId("7374757516243025970");
        signApplyInput.setTradeCode("A");
        signApplyInput.setUserId("20000610");
        signApplyInput.setCvv2("");
        
       SignApplyOutput out= signApplyService.signApply(signApplyInput);
       System.out.println(out.toString());
    }
    
    

}

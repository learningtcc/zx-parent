package com.ink.pats.atp.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ink.base.utils.IdCodeGenerator;
import com.ink.user.api.model.in.AccRechargeInput;
import com.ink.user.api.model.out.RetOutput;
import com.ink.user.api.service.IAccRechargeService;
import com.ink.user.util.DateUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class AccRechargeTest {
	
	@Autowired
	private IAccRechargeService accRechargeService;
	@Autowired
    IdCodeGenerator idCodeGenerator;
	
	@Test
	public void TestAR() throws Exception{
		String txnCode = "AR";//充值
		String mchId="1000000000002";
		String custId="20000610";
		String ordId = idCodeGenerator.getId();
		String tradeDate = DateUtils.getDateTime();
		String amt = "100000";
		String custFee = "0.00";
		String sacType="0001";
		String cardNo="622880199437863789";
		String channelId="12";
		String mac = DigestUtils.md5Hex("com.ink.trade!user"+ordId);
		
		AccRechargeInput dto = new AccRechargeInput();
		dto.setTxnCode(txnCode);
		dto.setMchId(mchId);
		dto.setCustId(custId);
		dto.setOrdId(ordId);
		dto.setTradeDate(tradeDate);
		dto.setAmt(amt);
		dto.setCustFee(custFee);
		dto.setAccountType(sacType);
		dto.setCardNo(cardNo);
		dto.setChannelId(channelId);
		dto.setOrdId(ordId);
		dto.setMac(mac);
		RetOutput ret = accRechargeService.exec(dto);
		System.out.println("##################"+ret.toString());
//		for(int i = 0; i < 100; i++){//批量开户，仅创造测试用户数据使用
//			 ordId = idCodeGenerator.getId();
//			custId="20000610";
//			dto.setCustId(custId);
//			dto.setOrdId(ordId);
//			RetOutput ret = accRechargeService.exec(dto);
//			System.out.println("##################"+ret.toString());
//		}
	}
}

package com.ink.pats.atp.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ink.base.utils.IdCodeGenerator;
import com.ink.user.api.model.in.BindCardInput;
import com.ink.user.api.model.out.BindCardOutput;
import com.ink.user.api.service.IBindCardService;
import com.ink.user.util.DateUtils;

/**
 * 
 * @ClassName: ACC38070Test 
 * @Description: 个人绑卡单元测试类 
 * @author guojie.gao 
 * @date 2015年12月1日 上午11:37:02 
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class BindCardTest {
//
	
	@Autowired
	private IBindCardService bindCardService;
	@Autowired
    IdCodeGenerator idCodeGenerator;
	
	@Test
	public void test() throws Exception{
		String txnCode = "BC";//绑卡
		String mchId="1000000000002";
		String custId="20000610";
		String tradeDate = DateUtils.getDateTime();
		String cardNo = "622880199437863123";
		String cardType="0";
		String bankMblNo="13800138001";
		String expDate="";
		String cvn2 = "";
		String custName="zhouxiang";
		String idType = "01";
		String idNo = "143102938471";
		String ordId = idCodeGenerator.getId();
		
		BindCardInput dto = new BindCardInput();
		dto.setTxnCode(txnCode);
		dto.setMchId(mchId);
		dto.setCustId(custId);
		dto.setTradeDate(tradeDate);
		dto.setCardNo(cardNo);
		dto.setCardType(cardType);
		dto.setBankMblNo(bankMblNo);
		dto.setExpDate(expDate);
		dto.setCvn2(cvn2);
		dto.setCustName(custName);
		dto.setIdType(idType);
		dto.setIdNo(idNo);
		dto.setCustName(custName);
		dto.setIdType(idType);
		dto.setIdNo(idNo);
		dto.setOrdId(ordId);
		BindCardOutput ret= bindCardService.exec(dto);
		System.out.println(ret.toString());
	}
}

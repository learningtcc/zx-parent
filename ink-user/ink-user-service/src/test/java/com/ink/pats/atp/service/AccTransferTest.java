package com.ink.pats.atp.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ink.base.utils.IdCodeGenerator;
import com.ink.user.api.model.in.AccTransferInput;
import com.ink.user.api.model.out.RetOutput;
import com.ink.user.api.service.IAccTransferService;
import com.ink.user.util.DateUtils;

/**
 * @Description: 转账
 * @author wanghao^_^
 * @date 2016年6月13日 上午10:58:08
 * @version V1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class AccTransferTest {
	
	
	@Autowired
	private IAccTransferService accTransferService;
	@Autowired
    IdCodeGenerator idCodeGenerator;
	
	@Test
	public void ATTest() throws Exception{
		String txnCode = "AT";
		String mchId="1000000000002";
		String custId="20000610";
		String ordId = idCodeGenerator.getId();
		String tradeDate = DateUtils.getDateTime();
		String amt = "1.00";
		String custFee = "0.00";
		String sacType="0001";
		String agaSacType="0003";
		
		AccTransferInput dto = new AccTransferInput();
		dto.setTxnCode(txnCode);
		dto.setMchId(mchId);
		dto.setCustId(custId);
		dto.setOrdId(ordId);
		dto.setTradeDate(tradeDate);
		dto.setAmt(amt);
		dto.setAgaSacType(agaSacType);
		dto.setCustFee(custFee);
		dto.setAccountType(sacType);
		RetOutput ret = accTransferService.exec(dto);
		System.out.println(ret.toString());
	}
}

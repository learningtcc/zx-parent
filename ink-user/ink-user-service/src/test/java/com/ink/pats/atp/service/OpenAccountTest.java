package com.ink.pats.atp.service;

import org.hibernate.validator.constraints.Length;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ink.base.utils.IdCodeGenerator;
import com.ink.user.api.model.in.OpenAccountInput;
import com.ink.user.api.model.out.OpenAccountOutput;
import com.ink.user.api.service.IOpenAccountService;
import com.ink.user.util.DateUtils;


/**
 * 
 * @ClassName: OpenAccountTest 
 * @Description: 个人开户单元测试类 
 * @author guojie.gao 
 * @date 2015年12月1日 上午11:27:14 
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class OpenAccountTest {

	@Autowired
	private IOpenAccountService OpenAccountService;
	@Autowired
    IdCodeGenerator idCodeGenerator;
	
	@Test
	public void Test() throws Exception{
		String txnCode = "OA";//个人开户
		String mchId="1000000000002";
		String tradeDate = DateUtils.getDateTime();
		String custName = "whtest";
		String custType = "0";
		String idType = "01";
		String idNo="13012519890327656";
		String mblNo="18513441234";
		//String ordId = idCodeGenerator.getId();
		//String custId="123458";
//		OpenAccountInput dto = new OpenAccountInput();
//		dto.setTxnCode(txnCode);
//		dto.setMchId(mchId);
//		dto.setCustId(custId);
//		dto.setTradeDate(tradeDate);
//		dto.setCustName(custName);
//		dto.setIdType(idType);
//		dto.setIdNo(idNo);
//		dto.setMblNo(mblNo);
//		dto.setOrdId(ordId);
//		dto.setCustType(custType);
//		private String sex;
//		//生日
//		@Length(max=10)
//		private String birthday;
//		//固定电话
//		@Length(max=20)
//		private String telNo;
//		//邮箱
//		@Length(max=15)
//		private String email;
//		//邮编
//		@Length(max=6)
//		private String zipcode;
//		//地址
//		private String address;
//		dto.setSex("M");
//		dto.setBirthday("19930911");
//		dto.setTelNo("0103654891");
//		dto.setEmail("326144008@qq.com");
//		dto.setZipcode("010203");
//		dto.setAddress("极东魔术昼寝结社");
		//OpenAccountOutput ret= OpenAccountService.exec(dto);
		//System.out.println(ret.toString());
		for(int i = 0; i < 5; i++){//批量开户，仅创造测试用户数据使用
			String ordId = idCodeGenerator.getId();
			String custId="1123456" + i;
			OpenAccountInput dto = new OpenAccountInput();
			dto.setTxnCode(txnCode);
			dto.setMchId(mchId);
			dto.setCustId(custId);
			dto.setTradeDate(tradeDate);
			dto.setCustName(custName);
			dto.setIdType(idType);
			dto.setIdNo(idNo);
			dto.setMblNo(mblNo);
			dto.setOrdId(ordId);
			dto.setCustType(custType);
			OpenAccountOutput ret= OpenAccountService.exec(dto);
			System.out.println(ret.toString());
		}
	}
}

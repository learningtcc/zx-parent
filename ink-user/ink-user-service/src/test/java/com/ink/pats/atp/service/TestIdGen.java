//package com.ink.pats.atp.service;
//
//import java.util.Date;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.ink.base.utils.IdCodeGenerator;
//import com.ink.user.core.dao.IAccMccDao;
//import com.ink.user.core.po.AccCust;
//import com.ink.user.core.po.AccMcc;
//import com.ink.user.core.service.IAccCustManager;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:spring/applicationContext-test.xml")
//public class TestIdGen {
//
//	@Autowired
//	private IAccCustManager accCustManager;
//	@Autowired
//	private IAccMccDao accMccDao;
////	@Autowired
////    IdCodeGenerator idCodeGenerator;
//	
//	@Test
//	public void Acc38040Test() throws Exception{
//		
////		AccCust accCust = new AccCust();
//////		String generateId= idCodeGenerator.getId();
//////		accCust.setId(generateId);
////		accCust.setCustId("465735157614241");
////		accCust.setMchId(100000000000002l);
////		accCust.setCustType(0);
////		accCust.setUid(4654674545368l);
////		accCust.setCreateTime(new Date());
////		accCust.setLastUpdateTime(new Date());
////		int i = accCustManager.save(accCust);
////		System.out.println(accCust.toString());
////		System.out.println(i);
//		
//		AccMcc accMcc = new AccMcc();
//		accMcc.setMcc("3434123");
//		int i = accMccDao.save(accMcc);
//		System.out.println(accMcc.toString());
//	}
//}

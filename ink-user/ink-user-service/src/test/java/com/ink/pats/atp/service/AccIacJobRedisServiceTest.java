//package com.ink.pats.atp.service;
//
//import java.math.BigDecimal;
//import java.util.List;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.ink.user.core.model.redis.AccIacRedisBean;
//import com.ink.user.core.po.AccIac;
//import com.ink.user.core.po.TnsAce;
//import com.ink.user.core.service.redis.AccIacJobRedisService;
//
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
//public class AccIacJobRedisServiceTest {
//	@Autowired
//	private AccIacJobRedisService accIacJobRedisService;
//	@Test
//	public void cacheTest() throws Exception{
//		String txnCode = "AccIacJobRedisServiceTest";
//		AccIac accIac = new AccIac();
//		TnsAce tnsAce = new TnsAce();
//		tnsAce.setTxnCode(txnCode);
//		accIacJobRedisService.pushIacToRedisList(accIac, BigDecimal.ZERO, tnsAce);
//		
//		List<AccIacRedisBean> list = accIacJobRedisService.getAccIacRedisBeanListByTxnCode(txnCode);
//		System.out.println(list.size());
//		accIacJobRedisService.popAccIacRedisBeanList(txnCode);
//		list = accIacJobRedisService.getAccIacRedisBeanListByTxnCode(txnCode);
//		System.out.println(list.size());
//	}
//}

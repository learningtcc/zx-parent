//package com.ink.pats.atp.service;
//
//import java.util.List;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.ink.user.core.po.mongo.ExceptionLog;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
//public class ExceptionLogQueryTest {
//	
//	@Autowired
//	private ExceptionLogMapper exceptionLogDao;
//	@Test
//	public void test() throws Exception{
//		ExceptionLogFilter filter = new ExceptionLogFilter();
//		filter.setMethodName("updateAccPac");
//		List<ExceptionLog> list = exceptionLogDao.getExceptionLog(filter);
//		System.out.println(list.size());
//		for(ExceptionLog log : list){
//			System.out.println(log);
//		}
//	}
//}
//package com.ink.pats.atp.service;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.alibaba.fastjson.JSON;
//import com.ink.user.core.po.mongo.AccAccLog;
//import com.ink.user.util.IdWorker;
//
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
//public class MongoTest {
//
//	@Autowired
//	AccAccLogService accAccLogService;
//	
//	@Test
//	public void saveAccAccLog(){
//		AccAccLog accAccLog = new AccAccLog();
//		accAccLog.setId(IdWorker.getNextId());
//		accAccLog.setAccPacId("asdfasdf");
//		accAccLog.setCur("CNY");
//		String json = JSON.toJSONString(accAccLog);
//		accAccLogService.saveAccAccLog(json);
//	}
//}

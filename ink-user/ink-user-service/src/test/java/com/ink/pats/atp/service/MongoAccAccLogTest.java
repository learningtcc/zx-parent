package com.ink.pats.atp.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.ink.user.core.po.mongo.GoldRecoveryHistory;
import com.ink.user.core.service.mongo.IAccAccLogService;
import com.ink.user.core.service.mongo.IGoldGrantHistoryService;
import com.ink.user.core.service.mongo.IGoldRecoveryHistoryService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class MongoAccAccLogTest {
	
	@Autowired
	private IAccAccLogService accAccLogService;
	@Autowired
	private IGoldGrantHistoryService goldGrantHistoryService;
	@Autowired
	private IGoldRecoveryHistoryService goldRecoveryHistoryService;
	
//	@Test
//	public void Save() throws Exception {
//
//		for (int i = 1; i < 12; i++) {
//			AccAccLog aal = new AccAccLog();
//			aal.setPacId("555"+i);
//			aal.setDelFlag("0");
//			aal.setErrorCode("BBBB");
//			aal.setSacId("666"+i);
//			aal.setOpenDate("2016-06-04");
//			aal.setCreateTime("2016-06-07 18:23:22");
//			aal.setLastUpdateTime("2016-06-07 18:23:22");
//			aal.setSacType("0001");
//			aal.setStatus(1);
//			try {
//				accAccLogService.saveAccAccLog(JSON.toJSONString(aal));
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//
//	}
	
	// @Test
	// public void Test1() throws Exception{
	// AccAccLogFilter filter = new AccAccLogFilter();
	// filter.setPageNumber(1);
	// filter.setAccPacId("222");
	// filter.setErrorCode("BBBB");
	// List<AccAccLog> list = accAccLogService.getAccAccLogs(filter);
	// for(AccAccLog al : list){
	// System.out.println(al.toString());
	// }
	// }
	//
	// @Test
	// public void Test2() throws Exception{
	// AccAccLogFilter filter = new AccAccLogFilter();
	// filter.setPageNumber(1);
	// filter.setAccPacId("222");
	// long list = accAccLogService.getCount(filter);
	// System.out.println("--------------------"+list);
	// }
	
	//gold
//	@Test
//	public void saveGold(){
//		try {
//			for(int i = 0 ; i < 10 ; i++){
//				GoldGrantHistory ggh = new GoldGrantHistory();
//				ggh.setCreateTime("2016-06-07 18:23:22");
//				ggh.setLasAmt("10");
//				ggh.setCurAmt("10");
//				ggh.setCustId("123456"+i);
//				ggh.setMchId("100000002");
//				ggh.setPacId("111111"+i);
//				ggh.setRecoveryAmt("20");
//				ggh.setRetCode("success");
//				ggh.setSacType("00001");
//				ggh.setState("p");
//				ggh.setTxnCode("AR");
//				goldGrantHistoryService.saveGoldGrantHistory(JSON.toJSONString(ggh));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		
//	}
	
	@Test
	public void saveGoldR(){
		try {
			for(int i = 10 ; i < 22 ; i++){
				GoldRecoveryHistory ggh = new GoldRecoveryHistory();
				ggh.setCreateTime("2016-07-07 18:23:22");
				ggh.setLasAmt("10");
				ggh.setCurAmt("10");
				ggh.setCustId("123456"+i);
				ggh.setMchId("100000002");
				ggh.setPacId("111111"+i);
				ggh.setRecoveryAmt("20");
				ggh.setRetCode("success");
				ggh.setSacType("00001");
				ggh.setState("p");
				ggh.setTxnCode("AR");
				goldRecoveryHistoryService.saveGoldRecoveryHistory(JSON.toJSONString(ggh));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}

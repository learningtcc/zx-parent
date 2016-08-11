package com.ink.pats.atp.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ink.user.core.po.TnsTxn;
import com.ink.user.core.service.redis.TnsTxnCacheService;
import com.ink.user.core.service.tns.ITnsTxnService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class CacheTest {
	@Autowired
	@Qualifier("tnsTxnService")
	private ITnsTxnService tnsTxnService;
	@Autowired
	private TnsTxnCacheService tnsTxnCacheService;
	@Test
	public void cacheTest() throws Exception{
		String txnCode = "ACC30460";
		TnsTxn tnsTxn = tnsTxnService.checkTnsTxn(txnCode);
		System.out.println(tnsTxn);
		
		tnsTxnCacheService.removeTnsTxnCache(txnCode);
		
		tnsTxnService.checkTnsTxn(txnCode);
		System.out.println(tnsTxn);
	}
}
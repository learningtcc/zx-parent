package com.ink;

import com.ink.balance.core.batch.job.ChannelDataBatchJob;
import com.ink.balance.core.manager.ICheckBalanceManager;
import com.ink.balance.core.po.CheckBalance;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:yinker-balance-core.xml")
public class BatchChannelDataTest {
	
	@Autowired
	private ChannelDataBatchJob channelDataBatchJob;
	@Autowired
	private ICheckBalanceManager checkBalanceManager;

	@Test
	public void channelDataTest() {
		channelDataBatchJob.performJob("classpath:cmbc20160412.txt");
	}
	@Test
	public void checkBalance() {
		CheckBalance checkBalance=new CheckBalance();
		checkBalance.setBalanceSource(1);
		checkBalanceManager.save(checkBalance);


	}

}
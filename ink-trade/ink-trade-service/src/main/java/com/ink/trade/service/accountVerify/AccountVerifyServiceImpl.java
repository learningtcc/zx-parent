package com.ink.trade.service.accountVerify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.channel.core.minsheng.service.CmccAccountVerifyService;
import com.ink.job.AbstractBaseJob;

@Service("accountVerifyService")
public class AccountVerifyServiceImpl extends AbstractBaseJob{
	
	@Autowired
	private  CmccAccountVerifyService cmccAccountVerifyService;
	@Override
	public void execute() {
		cmccAccountVerifyService.queryHistory();
	}
	public void test(){
		cmccAccountVerifyService.queryHistory();
	}
}

package com.ink.trade.controller.accountVerify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ink.trade.service.accountVerify.AccountVerifyServiceImpl;
import com.ink.util.JobExecutorUtils;

@Controller
@RequestMapping("accountVerify")
public class AccountVerifyController {
	
	@Autowired
	private AccountVerifyServiceImpl accountVerifyService;
	
	@RequestMapping("cmbcAccountVerify")
	@ResponseBody
	public String cmbcAccountVerify(@RequestParam("key")String key, @RequestParam("unlockURL")String unlockURL, @RequestParam("jobSerialId")String jobSerialId){
		if(JobExecutorUtils.executeJob(accountVerifyService, key, unlockURL,jobSerialId)){
			return "SUCCESS";
		}
		return "";
	}
}

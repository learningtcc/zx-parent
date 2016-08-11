/**
 * yinker.com Copyright (c) 2016-2025 All Rights Reserved.
 * 
 * @Description TODO
 * @author xuguoqi
 * @date 2016年7月5日 下午5:03:11
 */
package com.ink.trade.controller.accountVerify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ink.trade.service.accountVerify.BoofooFileLoadJobService;
import com.ink.util.JobExecutorUtils;

/**
 * @Description TODO
 * @author xuguoqi
 * @date 2016年7月5日 下午5:03:11
 */
@Controller
@RequestMapping("boofoo")
public class BoofooFileLoadController {
	
	@Autowired
	private BoofooFileLoadJobService boofooFileLoadJobService;
	
	@RequestMapping("fileLoad")
	@ResponseBody
	public String boofooFileLoad(@RequestParam("key")String key, @RequestParam("unlockURL")String unlockURL, @RequestParam("jobSerialId")String jobSerialId){
		if(JobExecutorUtils.executeJob(boofooFileLoadJobService, key, unlockURL,jobSerialId)){
			return "SUCCESS";
		}
		return "";
	}

}

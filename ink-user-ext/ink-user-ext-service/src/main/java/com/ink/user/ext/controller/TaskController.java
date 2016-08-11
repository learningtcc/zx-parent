package com.ink.user.ext.controller;

import com.ink.user.ext.job.DeleteFileJob;
import com.ink.util.JobExecutorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description: 定时任务
 * @author wanghao
 * @date 2016年6月27日 下午4:19:31
 */
@Controller
@RequestMapping("task")
public class TaskController {
	@Autowired
	private DeleteFileJob deleteFileJob;
	
	@RequestMapping("/deleteFile")
	@ResponseBody
	public String doAccUnfrozenJob(final String key, final String unlockURL,final String jobSerialId){
		if(JobExecutorUtils.executeJob(deleteFileJob, key, unlockURL, jobSerialId)){
			// 任务已开始执行
		}
		return "success";
	}
}

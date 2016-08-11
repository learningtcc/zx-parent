package com.ink.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ink.user.service.job.AccUnfrozenJob;
import com.ink.util.JobExecutorUtils;

/**
 * 定时任务入口
 * @author yangchen
 * @date 2016年5月16日 上午10:49:11
 */
@Controller
@RequestMapping("/task")
public class TaskController {
//	@Autowired
//	private MchAccDayUpdateJob mchAccDayUpdateJob;
//	@Autowired
//	private MchAccPeriodUpdateJob mchPeriodUpdateJob;
	@Autowired
	private AccUnfrozenJob accUnfrozenJob;
//	/**
//	 * 按天更新商户账户余额
//	 * @param key
//	 * @param unlockURL
//	 * @return
//	 */
//	@RequestMapping("/mchAccDayUpdateJob")
//	@ResponseBody
//	public String doMchAccDayUpdate(final String key, final String unlockURL){
//		if(JobExecutorUtils.executeJob(mchAccDayUpdateJob, key, unlockURL)){
////				mchAccDayUpdateJob.execute(key, unlockURL);
//		}
//		return "success";
//	}
//	
//
//	/**
//	 * 按天更新商户账户余额
//	 * @param key
//	 * @param unlockURL
//	 * @return
//	 */
//	@RequestMapping("/mchAccPeriodUpdateJob")
//	@ResponseBody
//	public String doMchAccPeriodUpdate(final String key, final String unlockURL){
//		if(JobExecutorUtils.executeJob(mchPeriodUpdateJob, key, unlockURL)){
////			mchPeriodUpdateJob.execute(key, unlockURL);
//		}
//		return "success";
//	}
	
	@RequestMapping("/accUnfrozenJob")
	@ResponseBody
	public String doAccUnfrozenJob(final String key, final String unlockURL,final String jobSerialId){
		if(JobExecutorUtils.executeJob(accUnfrozenJob, key, unlockURL, jobSerialId)){
			// 任务已开始执行
		}
		return "success";
	}
}

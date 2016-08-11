package com.ink.admin.monitor.controller.job;

import com.ink.base.BaseController;
import com.ink.util.JobExecutorUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("monitor/task")
@Controller
public class JobController extends BaseController {

//	@Autowired
	private MonitorServiceJob monitorServiceJob;

	@RequestMapping(value="/monitorServiceJob")
	@ResponseBody
	public Object monitorServiceJob(String key,String unlockURL,String jobSerialId){

		//执行任务
		if(JobExecutorUtils.executeJob(monitorServiceJob, key,unlockURL,jobSerialId)){
			return "OK";
		}
		return "OK";
	}

}

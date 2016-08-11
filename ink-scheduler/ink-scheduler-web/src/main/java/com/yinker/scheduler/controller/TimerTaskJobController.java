
/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.yinker.scheduler.controller;

import com.yinker.base.BaseController;
import com.yinker.base.log.util.YinkerLogger;
import com.yinker.base.page.Page;
import com.ink.scheduler.core.po.TimerTaskJob;
import com.ink.scheduler.core.po.TimerTaskJobLog;
import com.ink.scheduler.core.query.TimerTaskJobQuery;
import com.ink.scheduler.core.service.ITimerTaskJobLogManager;
import com.ink.scheduler.core.service.ITimerTaskJobManager;
import com.ink.scheduler.core.service.impl.TaskServiceImpl;
import com.ink.scheduler.core.util.TaskJobConstants;
import org.apache.commons.lang.StringUtils;
import org.quartz.CronExpression;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@RequestMapping("job")
@Controller
public class TimerTaskJobController extends BaseController {

	private YinkerLogger loger=  YinkerLogger.getLogger(TimerTaskJobController.class) ;

	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String LIST_JSP= "job/list";
	protected static final String CREATE_JSP = "job/create";
	protected static final String EDIT_JSP = "job/edit";
	protected static final String SHOW_JSP = "job/show";
	//redirect paths,startWith: !
//	protected static final String LIST_ACTION = "!/TimerTaskJob/list.do";
	
	@Autowired
	private ITimerTaskJobManager timerTaskJobManager;

	@Autowired
	private ITimerTaskJobLogManager timerTaskJobLogManager;

	@Autowired
	private TaskServiceImpl taskService;

    /** 查询任务列表 */
	@RequestMapping(value="/list")
	public ModelAndView list() {
		TimerTaskJobQuery query = newQuery(TimerTaskJobQuery.class,DEFAULT_SORT_COLUMNS);
        //只查询status为运行的job
        query.setStatus(TaskJobConstants.STATUS_RUNNING);
		Page page = timerTaskJobManager.findPage(query);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(LIST_JSP);
		modelAndView.addObject("page", page);
		return modelAndView;
	}
	
	/** 进入新增页面*/
	@RequestMapping(value="/create")
	public ModelAndView create() {
		return new ModelAndView(CREATE_JSP);
	}
	
	/** 保存新增任务对象并添加到调度任务 */
	@RequestMapping(value="/save")
	@ResponseBody
	public Map<String,String> save(TimerTaskJob timerTaskJob) {
        //返回结果map
        Map<String,String> resultMap = new HashMap<String, String>();
        //校验任务名是否已存在
		String jobName = timerTaskJob.getJobName();
		TimerTaskJobQuery query = new TimerTaskJobQuery();
		List<TimerTaskJob> jobList = timerTaskJobManager.find(query);
		for(TimerTaskJob job : jobList){
			if(job.getJobName().equals(jobName)){
				loger.info("timer","add job","该任务名已存在："+timerTaskJob.getJobName());
                resultMap.put("result","false");
                resultMap.put("errorMsg","该任务名已存在");
				return resultMap;
			}
		}
        //校验时间表达式是否符合规则
        if(!CronExpression.isValidExpression(timerTaskJob.getCronExpression())){
            loger.info("timer","add job","时间表达式不符合规则："+timerTaskJob.getCronExpression());
            resultMap.put("result","false");
            resultMap.put("errorMsg","时间表达式不符合规则");
            return resultMap;
        }
        //设置默认jobClass
        timerTaskJob.setJobClass(TaskJobConstants.JOB_HTTP_DEFAULT_CLASS);
        //设置joburl为jobName
//        timerTaskJob.setJobUrl(jobName);

        //保存job到数据库
        timerTaskJobManager.save(timerTaskJob);
        //添加调度任务并执行
		try{
		    taskService.addJob(timerTaskJob);
		}catch (SchedulerException e){
            loger.error("timer","添加任务异常",e);
            resultMap.put("result","false");
            resultMap.put("errorMsg","添加任务失败");
            return resultMap;
		}
        resultMap.put("result","true");
		return resultMap;
	}
	
	/** 查看对象*/
	@RequestMapping(value="/show")
	public ModelAndView show(TimerTaskJob timerTaskJob) {
		TimerTaskJob model = timerTaskJobManager.getById(timerTaskJob.getJobId());
		ModelAndView mav = new ModelAndView(SHOW_JSP);
		mav.addObject("model", model);
		return mav;
	}
	
	/**进入更新页面*/
	@RequestMapping(value="/edit")
	public ModelAndView edit(TimerTaskJob timerTaskJob) {
		TimerTaskJob model = timerTaskJobManager.getById(timerTaskJob.getJobId());
		ModelAndView mav = new ModelAndView(EDIT_JSP);
		mav.addObject("model", model);
		return mav;
	}
	
	/**保存更新对象*/
	@RequestMapping(value="/update")
	@ResponseBody
	public Map<String,String> update(TimerTaskJob timerTaskJob)  {
        //返回结果map
        Map<String,String> resultMap = new HashMap<String, String>();
        try {
            TimerTaskJob targetJob = timerTaskJobManager.getById(timerTaskJob.getJobId());
            targetJob.setCronExpression(timerTaskJob.getCronExpression());
            targetJob.setDescription(timerTaskJob.getDescription());
            targetJob.setJobExecuteTime(timerTaskJob.getJobExecuteTime());
            targetJob.setValidityPeriod(timerTaskJob.getValidityPeriod());

            taskService.updateCronExpression(targetJob);
        }catch (SchedulerException e){
            loger.error("timer","更新任务异常",e);
            e.printStackTrace();
            resultMap.put("result","false");
            resultMap.put("errorMsg","更新任务失败");
            return resultMap;
        }
        resultMap.put("result","true");
		return resultMap;
	}

	/**保存更新对象*/
	@RequestMapping(value="/updateVersion")
	@ResponseBody
	public Map<String,String> updateVersion(String key,String reqId,String jobSerialId,String errorMsg){
		//返回结果map
		Map<String,String> resultMap = new HashMap<String, String>();
		TimerTaskJob timerTaskJob = timerTaskJobManager.getByJobName(key);
		timerTaskJob.setVersion(TaskJobConstants.VERSION_UNLOCK);
		int result = timerTaskJobManager.update(timerTaskJob);
		if(result > 0){
			resultMap.put("result","true");
		}else {
			resultMap.put("result","false");
			resultMap.put("errorMsg","更新版本错误");
		}

		//更新job调度记录
		TimerTaskJobLog timerTaskJobLog = timerTaskJobLogManager.getById(Integer.parseInt(jobSerialId));
		timerTaskJobLog.setEndTime(new Date());
		timerTaskJobLog.setReqId(reqId);
		if(StringUtils.isNotBlank(errorMsg)){
			timerTaskJobLog.setStatus(TaskJobConstants.JOB_EXECUTE_STATUS_ERROR);
			timerTaskJobLog.setErrorMsg(errorMsg);
		}else {
			timerTaskJobLog.setStatus(TaskJobConstants.JOB_EXECUTE_STATUS_SUCCESS);
		}
		timerTaskJobLogManager.update(timerTaskJobLog);

		return resultMap;
	}
	
	
	/**删除对象*/
	@RequestMapping(value="/delete")
	@ResponseBody
	public Map<String,String> delete(String items) {
        //返回结果map
        Map<String,String> resultMap = new HashMap<String, String>();
		if(items!=null){
			String[] idArray = items.split(",");
			for(String id: idArray){
                TimerTaskJob targetJob = timerTaskJobManager.getById(Integer.valueOf(id));
                taskService.deleteJob(targetJob);
			}
		}
        resultMap.put("result","true");
        return resultMap;
	}

	/**
	 * 暂停任务
	 * @throws Exception
	 */
	@RequestMapping("/pause")
    @ResponseBody
	public Map<String,String> pause(TimerTaskJob timerTaskJob){
		loger.info("timer","pause","pause job :"+ timerTaskJob.getJobId());
        //返回结果map
        Map<String,String> resultMap = new HashMap<String, String>();

		TimerTaskJob taskJob = timerTaskJobManager.getById(timerTaskJob.getJobId());
		//暂停任务
		if(taskService.pauseJob(taskJob)){
            resultMap.put("result", "true");
        }else{
            resultMap.put("result", "false");
            resultMap.put("errorMsg", "暂停任务失败");
        }
        return resultMap;
    }

	/**
	 * 恢复任务
	 * @throws Exception
	 */
	@RequestMapping("/resume")
    @ResponseBody
	public Map<String,String> resume(TimerTaskJob timerTaskJob){
		loger.info("timer","resume","resume job :"+ timerTaskJob.getJobId());
        //返回结果map
        Map<String,String> resultMap = new HashMap<String, String>();
		TimerTaskJob taskJob = timerTaskJobManager.getById(timerTaskJob.getJobId());
        //恢复任务
		if(taskService.resumeJob(taskJob)){
            resultMap.put("result", "true");
        }else{
            resultMap.put("result", "false");
            resultMap.put("errorMsg", "恢复任务失败");
        }
        return resultMap;
	}
}

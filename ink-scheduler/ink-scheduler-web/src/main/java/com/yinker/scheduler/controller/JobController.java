package com.yinker.scheduler.controller;

import com.yinker.base.BaseController;
import com.ink.scheduler.core.po.TimerTaskJob;
import com.ink.scheduler.core.service.ITaskService;
import com.ink.scheduler.core.service.ITimerTaskJobManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author haoyunfeng
 * @date 2016/6/16
 */
@Controller
public class JobController extends BaseController {

    @Autowired
    private ITimerTaskJobManager timerTaskJobManager;

    @Autowired
    private ITaskService taskService;

    /**
     * 立即执行调度任务接口
     * @param jobUrl
     * @param jobGroup
     * @return
     */
    @RequestMapping(value="/execute")
    @ResponseBody
    public String executeJob(String jobUrl,String jobGroup){

        TimerTaskJob timerTaskJob = timerTaskJobManager.getByJobUrlAndGroup(jobUrl,jobGroup);
        try{
            taskService.begainJob(timerTaskJob);
            return "SUCCESS";
        }catch (Exception e){
            return "ERROR";
        }
    }
}

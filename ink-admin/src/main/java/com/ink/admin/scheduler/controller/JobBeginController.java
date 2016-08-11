package com.ink.admin.scheduler.controller;

import com.ink.base.BaseController;
import com.ink.base.service.DubboBaseService;
import com.ink.scheduler.core.po.TimerTaskJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author haoyunfeng
 * @date 2016/6/16
 */
@RequestMapping("scheduler")
@Controller
public class JobBeginController extends BaseController {

//    @Autowired
//    private ITimerTaskJobManager timerTaskJobManager;
//
//    @Autowired
//    private ITaskService taskService;

    @Autowired
    private DubboBaseService schedulerDubboBaseService;

    /**
     * 立即执行调度任务接口
     * @param jobUrl
     * @param jobGroup
     * @return
     */
    @RequestMapping(value="/execute")
    @ResponseBody
    public String executeJob(String jobUrl,String jobGroup){

//        TimerTaskJob timerTaskJob = timerTaskJobManager.getByJobUrlAndGroup(jobUrl,jobGroup);
        TimerTaskJob timerTaskJob = null;
        try {
            timerTaskJob = (TimerTaskJob)schedulerDubboBaseService.executeDynamicMethod("timerTaskJobManager", "getByJobUrlAndGroup", jobUrl, jobGroup);
        }catch (Exception e){
            e.printStackTrace();
        }
        try{
            schedulerDubboBaseService.executeDynamicMethod("taskService", "begainJob", timerTaskJob);
//            taskService.begainJob(timerTaskJob);
            return "SUCCESS";
        }catch (Exception e){
            return "ERROR";
        }
    }
}

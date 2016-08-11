package com.yinker.controller;

import com.yinker.base.BaseController;
import com.yinker.base.log.util.YinkerLogger;
import com.yinker.job.BalanceJob;
import com.yinker.util.JobExecutorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 任务执行控制器（http）
 * @author haoyunfeng
 * @date 2016/4/14.
 */

@Controller
@RequestMapping("/task")
public class TaskController extends BaseController {

    public YinkerLogger loger=  YinkerLogger.getLogger(TaskController.class) ;

    @Autowired
    private BalanceJob balanceJob;

    @RequestMapping("/task")
    @ResponseBody
    public String task(final String key,final String unlockURL,final String jobSerialId){
        //记日志
        //执行任务
        if(JobExecutorUtils.executeJob(balanceJob,key,unlockURL,jobSerialId)){
            return "OK";
        }
        return "";
    }
}

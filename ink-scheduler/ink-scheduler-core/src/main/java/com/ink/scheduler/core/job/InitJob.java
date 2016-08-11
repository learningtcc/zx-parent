/**    
 *
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */

package com.ink.scheduler.core.job;

import com.ink.base.log.util.YinkerLogger;
import com.ink.scheduler.core.po.TimerTaskJob;
import com.ink.scheduler.core.query.TimerTaskJobQuery;
import com.ink.scheduler.core.service.ITaskService;
import com.ink.scheduler.core.service.ITimerTaskJobManager;
import com.ink.scheduler.core.util.TaskJobConstants;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class InitJob implements InitializingBean{

    public YinkerLogger loger=  YinkerLogger.getLogger(InitJob.class) ;

	@Autowired     
	private ITaskService taskService;

    //线程池大小
    private final int THREAD_POOL_SIZE = 20;

    @Autowired
    private ITimerTaskJobManager timerTaskJobManager;


    /**
     * 初始化加载job列表
     * @author haoyunfeng
     * @throws Exception
     */
	@Override
	public void afterPropertiesSet() throws Exception {
        // 加载DB 中读取JOB 列表
        loger.info("timer","init","InitJob  loading..... job START");
        //List<TimerTaskJob> jobList =  taskJobService.findAllJob();
        TimerTaskJobQuery query = new TimerTaskJobQuery();
        query.setStatus(TaskJobConstants.STATUS_RUNNING);
        List<TimerTaskJob> jobList = timerTaskJobManager.find(query);

        ExecutorService pool = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        for(TimerTaskJob job : jobList){
            // 此处采用线程池， 支持并发执行， 提高时间精准度
            final TimerTaskJob innerJob = job;
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        loger.info("timer","addJob","taskService.addJob  "+innerJob.getJobName());
                        taskService.addJob(innerJob);
                    }catch (SchedulerException e){
                        loger.error("timer","taskService.addJob Exception "+innerJob.getJobName(),e);
                    }
                }
            });
        }
        loger.info("timer","addJob","InitJob  loading..... job  END ");
	}
}

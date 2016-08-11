
package com.ink.scheduler.core.service.impl;

import com.ink.scheduler.core.po.TimerTaskJob;
import com.ink.scheduler.core.util.TaskJobConstants;
import com.ink.base.log.util.YinkerLogger;
import com.ink.scheduler.core.service.ITaskService;
import com.ink.scheduler.core.service.ITimerTaskJobManager;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 任务调度类
 * @author haoyunfeng
 * @date 2016.4.15
 */
@Service("taskService")
public class TaskServiceImpl implements ITaskService  {

    private YinkerLogger loger=  YinkerLogger.getLogger(TaskServiceImpl.class) ;

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    @Autowired
    private ITimerTaskJobManager timerTaskJobManager;


    /**
     * 新增任务并执行调度
     * @param timerTaskJob
     * @return
     * @throws SchedulerException
     */
    @SuppressWarnings("unchecked")
    public boolean addJob(TimerTaskJob timerTaskJob) throws SchedulerException {
        loger.info("timer","addJ Job","添加任务："+timerTaskJob.getJobName());
        if (timerTaskJob == null || !TaskJobConstants.STATUS_RUNNING.equals(timerTaskJob.getStatus())
                || TaskJobConstants.JOB_STATUS_NOT_RUNNING.equals(timerTaskJob.getJobStatus())) {
            loger.info("timer","addJ Job","任务状态为非运行或者暂停，不添加该任务："+timerTaskJob.getJobName()+"");
            return false;
        }

        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        TriggerKey triggerKey = TriggerKey.triggerKey(timerTaskJob.getJobName(), timerTaskJob.getJobGroup());
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(timerTaskJob.getCronExpression());
        Class<? extends Job> clazz = null;
        try {
            clazz = (Class<? extends Job>) Class.forName(timerTaskJob.getJobClass()).newInstance().getClass();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        // 按新的表达式构建一个新的trigger
        trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
        JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(timerTaskJob.getJobName(), timerTaskJob.getJobGroup()).build();
        jobDetail.getJobDataMap().put("scheduleJob", timerTaskJob);
        //执行调度
        scheduler.scheduleJob(jobDetail, trigger);
        return true;
    }

    /**
     * 暂停任务并更新数据库中任务状态
     * @param timerTaskJob
     * @return
     */
    public boolean pauseJob(TimerTaskJob timerTaskJob) {
        loger.info("timer","pause Job","暂停任务："+timerTaskJob.getJobName());
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobKey jobKey = JobKey.jobKey(timerTaskJob.getJobName(), timerTaskJob.getJobGroup());
        try {
            //更新数据库中job运行状态为暂停
            timerTaskJob.setJobStatus(TaskJobConstants.JOB_STATUS_NOT_RUNNING);
            timerTaskJobManager.update(timerTaskJob);
            //scheduler中暂停任务
            scheduler.pauseJob(jobKey);
            // JobDetail jobDetail = scheduler.getJobDetail(jobKey);
            return true;
        } catch (SchedulerException e) {
            loger.error("暂停任务失败"+timerTaskJob.getJobName(),e);
        }
        return false;
    }

    /**
     * 恢复job继续执行
     * @param timerTaskJob
     * @return
     */
    public boolean resumeJob(TimerTaskJob timerTaskJob) {
        loger.info("timer","resume Job","恢复任务："+timerTaskJob.getJobName());
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobKey jobKey = JobKey.jobKey(timerTaskJob.getJobName(), timerTaskJob.getJobGroup());
        try {
            //更新数据库中job运行状态为运行
            timerTaskJob.setJobStatus(TaskJobConstants.JOB_STATUS_RUNNING);
            timerTaskJobManager.update(timerTaskJob);
            //判断任务是否已在scheduler调度计划中
            List<TimerTaskJob> joblist = getSchedulerJob();
            boolean isContans = false;
            for(TimerTaskJob job :joblist){
                if(job.getJobName().equals(timerTaskJob.getJobName())){
                    isContans = true;
                    break;
                }
            }
            if(isContans){
                //如果已有此任务，从scheduler中恢复任务
                scheduler.resumeJob(jobKey);
                //更新时间表达式
                updateCronExpression(timerTaskJob);
            }else {
                //如果scheduler中没有该任务，则添加任务
                addJob(timerTaskJob);
            }

            return true;
        } catch (SchedulerException e) {
            loger.error("恢复任务失败"+timerTaskJob.getJobName(),e);
        }catch (Exception e ){
            loger.error("恢复任务失败"+timerTaskJob.getJobName(),e);
        }
        return false;
    }

    /**
     * 删除任务
     * @param timerTaskJob
     * @return
     */
    public boolean deleteJob(TimerTaskJob timerTaskJob) {
        loger.info("timer","delete Job","删除任务："+timerTaskJob.getJobName());
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobKey jobKey = JobKey.jobKey(timerTaskJob.getJobName(), timerTaskJob.getJobGroup());
        try {
            //更新数据库中job状态为删除
            loger.info("timer","delete Job","数据库中逻辑删除任务："+timerTaskJob.getJobName());
            timerTaskJob.setStatus(TaskJobConstants.STATUS_DELETE);
            loger.info("timer","delete Job","调度系统中删除任务："+timerTaskJob.getJobName());
            timerTaskJobManager.update(timerTaskJob);
            //scheduler中删除任务
            scheduler.deleteJob(jobKey);
            return true;
        } catch (SchedulerException e) {
            loger.error("删除任务失败"+timerTaskJob.getJobName(),e);
        }
        return false;
    }

    /**
     * 立即执行job（并行）
     * @param timerTaskJob
     * @throws SchedulerException
     */
    public void begainJob(TimerTaskJob timerTaskJob) throws SchedulerException {
        loger.info("timer","begain Job","强制执行任务："+timerTaskJob.getJobName());
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobKey jobKey = JobKey.jobKey(timerTaskJob.getJobName(), timerTaskJob.getJobGroup());
        //执行
        scheduler.triggerJob(jobKey);
    }

    /**
     * 更新job执行时间表达式
     * @param timerTaskJob
     * @throws SchedulerException
     */
    public void updateCronExpression(TimerTaskJob timerTaskJob) throws SchedulerException {
        loger.info("timer","update Job CronExpression","更新job："+timerTaskJob.getJobName());
        //更新数据库中表达式
        timerTaskJobManager.update(timerTaskJob);

        //非运行状态任务只更新数据库
        if(!timerTaskJob.getJobStatus().equals(TaskJobConstants.JOB_STATUS_RUNNING) ){
            loger.info("timer","update Job CronExpression","非运行状态job只更新数据库");
            return;
        }
        loger.info("timer","update Job CronExpression","更新job '"+timerTaskJob.getJobName()+"'时间表达式："+timerTaskJob.getCronExpression());
        //更新scheduler中job的表达式
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        TriggerKey triggerKey = TriggerKey.triggerKey(timerTaskJob.getJobName(), timerTaskJob.getJobGroup());
        // 获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        // 表达式调度构建器
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(timerTaskJob.getCronExpression());
        // 按新的cronExpression表达式重新构建trigger
        trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
        // 按新的trigger重新设置job执行
        scheduler.rescheduleJob(triggerKey, trigger);
    }

    /**
     * 取得正在运行的job
     * @return
     * @throws Exception
     */
    @Override
    public List<TimerTaskJob> getRunningJob() throws Exception {
        loger.info("timer","update Job CronExpression","取得正在运行的job列表");
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
        List<TimerTaskJob> jobList = new ArrayList<TimerTaskJob>(executingJobs.size());
        for (JobExecutionContext executingJob : executingJobs) {
            TimerTaskJob job = new TimerTaskJob();
            JobDetail jobDetail = executingJob.getJobDetail();
            JobKey jobKey = jobDetail.getKey();
            Trigger trigger = executingJob.getTrigger();
            job.setJobName(jobKey.getName());
            job.setJobGroup(jobKey.getGroup());
            job.setDescription("触发器:" + trigger.getKey());
            Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
            job.setJobStatus(triggerState.name());
            if (trigger instanceof CronTrigger) {
                CronTrigger cronTrigger = (CronTrigger) trigger;
                String cronExpression = cronTrigger.getCronExpression();
                job.setCronExpression(cronExpression);
            }
            jobList.add(job);
        }
        return jobList;
    }
    /**
     * 取得计划中的job
     * @return
     * @throws Exception
     */
    @Override
    public List<TimerTaskJob> getSchedulerJob() throws Exception {
        loger.info("timer","update Job CronExpression","取得计划中的job列表");
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
        Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
        List<TimerTaskJob> jobList = new ArrayList<TimerTaskJob>();
        for (JobKey jobKey : jobKeys) {
            List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
            for (Trigger trigger : triggers) {
                TimerTaskJob job = new TimerTaskJob();
                job.setJobName(jobKey.getName());
                job.setJobGroup(jobKey.getGroup());
                //job.setDesc("触发器:" + trigger.getKey());
                Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                job.setJobStatus(triggerState.name());
                if (trigger instanceof CronTrigger) {
                    CronTrigger cronTrigger = (CronTrigger) trigger;
                    String cronExpression = cronTrigger.getCronExpression();
                    job.setCronExpression(cronExpression);
                }
                jobList.add(job);
            }
        }
        return jobList;
    }
}

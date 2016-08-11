/**    
 *
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */

package com.ink.scheduler.core.job;

import com.ink.scheduler.core.po.TimerTaskJob;
import com.ink.scheduler.core.service.ITimerTaskJobLogManager;
import com.ink.scheduler.core.util.HttpClientUtils;
import com.ink.scheduler.core.util.TaskJobConstants;
import com.ink.base.log.util.YinkerLogger;
import com.ink.base.utils.context.SpringApplicationContext;
import com.ink.config.service.support.spring.CustomizedPropertyConfigurer;
import com.ink.scheduler.core.po.TimerTaskJobLog;
import com.ink.scheduler.core.service.ITimerTaskJobManager;
import org.apache.commons.httpclient.HttpStatus;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 定时任务通用任务类
 * @author  haoyunfeng
 * @date 2016.4.15
 */
@DisallowConcurrentExecution
public class YinkerJob extends TimerTaskJob implements Job {

    private YinkerLogger loger=  YinkerLogger.getLogger(YinkerJob.class) ;

    @Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
        //从applicationContext中取ITimerTaskJobManager和ITimerTaskJobLogManager
        ITimerTaskJobManager timerTaskJobManager =(ITimerTaskJobManager) SpringApplicationContext.getBean("timerTaskJobManager");
        ITimerTaskJobLogManager timerTaskJobLogManager =(ITimerTaskJobLogManager) SpringApplicationContext.getBean("timerTaskJobLogManager");

        //从applicationContext中取properties中配置的参数unlockURL
        CustomizedPropertyConfigurer configurer = (CustomizedPropertyConfigurer)SpringApplicationContext.getBean("customizedPropertyConfigurer");
        final String unlockURL = configurer.getProperty("unlockURL");
//        final String unlockURL = "http://localhost:9090/scheduler/job/updateVersion.do";
        String group =  arg0.getTrigger().getJobKey().getGroup();
        final String name=   arg0.getTrigger().getJobKey().getName(); //MQ NAME or HTTP URL

        //从数据库中取得任务
        TimerTaskJob timerTaskJob = timerTaskJobManager.getByJobName(name);
        Date validityPeriod = timerTaskJob.getValidityPeriod();
        loger.info("timer","executeJob","group:"+group+" name:"+name+" 开始执行任务");

        //通讯失败回滚job对象
        TimerTaskJob timerTaskJobRollBack = null;
        try {
            timerTaskJobRollBack = (TimerTaskJob) timerTaskJob.clone();
            //有效期不为空，任务已过期,不执行该任务
            if(validityPeriod != null && !checkValidPeriod(validityPeriod)){
                loger.info("timer","executeJob","group:"+group+" name:"+name+" 该任务已过期,不执行");
                return;
            }
            //任务isConcurrent状态为1,控制并发
            if( TaskJobConstants.CONCURRENT_IS.equals(timerTaskJob.getIsConcurrent())){
                //if version是锁状态
                if(TaskJobConstants.VERSION_LOCK.equals(timerTaskJob.getVersion()) ){
                    //取上次执行时间
                    Date previousTime = timerTaskJob.getPreviousTime() != null ? timerTaskJob.getPreviousTime():new Date();
                    //if version 超时,解锁
                    if(checkVersionExpire(timerTaskJob.getPreviousTime(),Integer.parseInt(timerTaskJob.getJobExecuteTime()))){
                        //version设为解锁状态
                        loger.info("timer","executeJob","group:"+group+" name:"+name+" 该任务已经超时，解锁");
                        timerTaskJob.setVersion(TaskJobConstants.VERSION_UNLOCK);
                        timerTaskJobManager.update(timerTaskJob);
                    }else{  //version未超时，记日志，暂不执行job
                        loger.info("timer","executeJob","group:"+group+" name:"+name+" 有任务在执行，暂不执行");
                        return;
                    }
                }
                //更新数据库timerTaskJob version，加锁
                loger.info("timer","executeJob","group:"+group+" name:"+name+" 准备调用任务url执行任务，加锁");
                timerTaskJob.setVersion(TaskJobConstants.VERSION_LOCK);
                timerTaskJobManager.update(timerTaskJob);
            }

            //更新job上次运行时间
            timerTaskJob.setPreviousTime(new Date());
            //更新job执行次数
            timerTaskJob.setRunCount(timerTaskJob.getRunCount()+1);
            //更新job第一次执行时间
            if(timerTaskJob.getFirstTime() == null){
                timerTaskJob.setFirstTime(new Date());
            }
            timerTaskJobManager.update(timerTaskJob);

            //记录调度日志
            final TimerTaskJobLog timerTaskJobLog = new TimerTaskJobLog();
            timerTaskJobLog.setStartTime(new Date());
            timerTaskJobLog.setJobId(timerTaskJob.getJobId());
            timerTaskJobLogManager.save(timerTaskJobLog);

            //调起任务服务
            int statusCode = HttpClientUtils.execute(
                    name,
                    new HashMap<String, Object>(){
                        {
                            put("key", name);
                            put("unlockURL",unlockURL);
                            put("jobSerialId",timerTaskJobLog.getId());
                        }
                    },
                    20000
            );
//            int statusCode = (int)resultMap.get(TaskJobConstants.JOB_HTTP_STATUS_CODE);
            //响应成功后更新任务执行时间和计数
            if(statusCode == HttpStatus.SC_OK){
                //do nothing
                //增加调度日志
            }else {
                //通讯失败,回滚
                loger.info("timer","executeJob","group:"+group+" name:"+name+" 通讯失败，回滚job");
                timerTaskJobManager.update(timerTaskJobRollBack);
            }
        }catch (Exception e){
            loger.error("timer","调度任务失败",e);
            if(null != timerTaskJobRollBack){
                timerTaskJobManager.update(timerTaskJobRollBack);
            }
        }
        loger.info("timer","executeJob","group:"+group+" name:"+name+"结束执行");
	}

    /**
     * 判断是否在有效期内，是则返回true
     * @param validityPeriod  有效期
     * @return
     */
    private static  boolean checkValidPeriod(Date validityPeriod){
        //如果当前日期早于有效期
        int result = compareDate(new Date(),validityPeriod);
        if(result < 0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 检查version锁是否过期
     * @param previousTime      任务上次执行时间
     * @param second            任务预计执行时间
     * @return                  true:过期; false:未过期;
     */
    private static boolean checkVersionExpire(Date previousTime,int second){
        //上次执行时间加 任务预计执行的时间
        Calendar c = new GregorianCalendar();
        c.setTime(previousTime);
        c.add(Calendar.SECOND,second);
        previousTime = c.getTime();

        int result = compareDate(new Date(),previousTime);
        if(result < 0){
            return false;
        }else{
            return true;
        }
    }

    /**
     * 比较日期
     * @param date1
     * @param date1
     * @return 0:等于；大于0：date1大于date2；小于0：date1小于date2
     */
    private static int compareDate(Date date1,Date date2){
        final SimpleDateFormat FORMAT = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
        String date1Str = FORMAT.format(date1);
        String date2Str = FORMAT.format(date2);
        return date1Str.compareTo(date2Str);
    }

}

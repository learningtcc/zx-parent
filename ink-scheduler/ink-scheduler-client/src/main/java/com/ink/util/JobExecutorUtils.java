package com.ink.util;

import com.ink.base.log.util.YinkerLogger;
import com.ink.job.AbstractBaseJob;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author haoyunfeng
 * @date 2016/4/20
 */
public class JobExecutorUtils {
    private static YinkerLogger loger=  YinkerLogger.getLogger(JobExecutorUtils.class);

    public static boolean executeJob(AbstractBaseJob job, String key,String unlockURL,String jobSerialId){
        try{
            loger.info("timer client","job execute","开始执行任务,key:"+key);
            job.setKey(key);
            job.setUnlockURL(unlockURL);
            job.setJobSerialId(jobSerialId);
            ExecutorService pool = Executors.newSingleThreadExecutor();
            pool.execute(job);
        }catch (Exception e){
            loger.error("执行任务"+key+"异常",e);
            return false;
        }
        return true;
    }
}

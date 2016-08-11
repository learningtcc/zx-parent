package com.ink.balance.core.batch.job;

import com.ink.balance.api.constants.LoggerCnst;
import com.ink.base.log.util.YinkerLogger;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.launch.JobLauncher;

/**
 * @author bo.chen
 * @Description springBatch批处理读取渠道对账文件
 * @date 2016年4月11日 上午11:03:07
 */
public class BooFooDataBatchJob {
    YinkerLogger loger = YinkerLogger.getLogger(BooFooDataBatchJob.class);

    private JobLocator jobLocator;

    private JobLauncher jobLauncher;

    /**
     * @param resource 资源路径
     * @return void
     * @Description 批处理job宝付
     * @author bo.chen
     * @date 2016年4月11日 上午11:10:58
     */
    public void performJob(String resource,String jobName) {
        loger.info(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.SPRING_BATCH_BUS,
                "开始宝付ChannelDataBatchJob", null);

        try {
            long startTime = System.currentTimeMillis(); // 获取开始时间
            JobExecution result = jobLauncher.run(jobLocator.getJob(jobName),
                    new JobParametersBuilder().addString("resource", resource).addString("executeTime", startTime + "").toJobParameters());
            loger.info(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.SPRING_BATCH_BUS,
                    "宝付ChannelData Job 完成 details : ",
                    result.toString());
            long endTime = System.currentTimeMillis(); // 获取结束时间
            loger.info(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.SPRING_BATCH_BUS,
                    "宝付ChannelData Job 运行时间： " + (endTime - startTime) + "ms", null);
        } catch (JobExecutionException ex) {
            loger.error(LoggerCnst.BALANCE_MOUDLE, LoggerCnst.GET_DATA_BUS,
                    "宝付ChannelData Job 异常:", ex,
                    null);
        }
        
    }

    public void setJobLocator(JobLocator jobLocator) {
        this.jobLocator = jobLocator;
    }

    public void setJobLauncher(JobLauncher jobLauncher) {
        this.jobLauncher = jobLauncher;
    }

}

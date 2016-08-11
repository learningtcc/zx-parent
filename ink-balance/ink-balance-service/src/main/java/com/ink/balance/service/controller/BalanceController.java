package com.ink.balance.service.controller;

import com.ink.balance.service.impl.service.ChannelDataServiceImpl;
import com.ink.balance.service.job.BalanceJob;
import com.ink.balance.service.job.BatchJob;
import com.ink.balance.service.job.BooBalanceJob;
import com.ink.balance.service.job.BooBatchJob;
import com.ink.balance.service.job.YinkerBalanceJob;
import com.ink.balance.service.job.YinkerBatchJob;
import com.ink.base.log.util.YinkerLogger;
import com.ink.util.JobExecutorUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 作者 :bo.chen
 * @version 创建时间：2016年04月13日 上午10:09:00
 * @description 描述：http请求-数据解析和对账
 */

@Controller
public class BalanceController {
    YinkerLogger logger = YinkerLogger.getLogger(ChannelDataServiceImpl.class);

    @Autowired
    private BatchJob batchJob;

    @Autowired
    private BalanceJob balanceJob;

    @Autowired
    private BooBatchJob booBatchJob;
    
    @Autowired
    private BooBalanceJob booBalanceJob;
    
    @Autowired
    private YinkerBalanceJob yinkerBalanceJob;
    
    @Autowired
    private YinkerBatchJob yinkerBatchJob;
    /**
     * @return String
     * @Description 对账执行接口
     * @author bo.chen
     * @date 2016年4月18日 下午5:16:44
     */
    @ResponseBody
    @RequestMapping(value = "/balanceData")
    public String balanceData(final String key,final String unlockURL,final String jobSerialId) {
        //执行任务
        if(JobExecutorUtils.executeJob(balanceJob,key,unlockURL,jobSerialId)){
            return "SUCCESS";
        }
        return "ERROR";
    }

    /**
     * @return String
     * @Description 读取对账文件接口
     * @author bo.chen
     * @date 2016年4月18日 下午5:16:44
     */
    @ResponseBody
    @RequestMapping(value = "/readBatch")
    public String readBatch(final String key,final String unlockURL,final String jobSerialId) throws Exception {
        //执行任务
        if(JobExecutorUtils.executeJob(batchJob,key,unlockURL,jobSerialId)){
            return "SUCCESS";
        }
        return "ERROR";
    }
    
    /**
     * 
    * @Title: readBooBatch 
    * @Description: 读取宝付渠道对账文件
    * @param @param key
    * @param @param unlockURL
    * @param @param jobSerialId
    * @param @return
    * @param @throws Exception
    * @return String 
    * @author zhaojie
    * @date 2016年7月8日 下午6:49:55
    * @throws
     */
    @ResponseBody
    @RequestMapping(value = "/readBooBatch")
    public String readBooBatch(final String key,final String unlockURL,final String jobSerialId) throws Exception {
        //执行任务
        if(JobExecutorUtils.executeJob(booBatchJob,key,unlockURL,jobSerialId)){
            return "SUCCESS";
        }
        return "ERROR";
    }
    /**
     * 
    * @Title: balanceBooData 
    * @Description: 宝付渠道对账
    * @param @param key
    * @param @param unlockURL
    * @param @param jobSerialId
    * @param @return
    * @return String 
    * @author zhaojie
    * @date 2016年7月8日 下午6:53:09
    * @throws
     */
    @ResponseBody
    @RequestMapping(value = "/balanceBooData")
    public String balanceBooData(final String key,final String unlockURL,final String jobSerialId) {
        //执行任务
        if(JobExecutorUtils.executeJob(booBalanceJob,key,unlockURL,jobSerialId)){
            return "SUCCESS";
        }
        return "ERROR";
    }
    /**
     * 
    * @Title: balanceYinkerData 
    * @Description: 银客懒猫自动对账方法
    * @param @param key
    * @param @param unlockURL
    * @param @param jobSerialId
    * @param @return
    * @return String 
    * @author zhaojie
    * @date 2016年7月13日 上午11:50:23
    * @throws
     */
    @ResponseBody
    @RequestMapping(value = "/balanceYinkerData")
    public String balanceYinkerData(final String key,final String unlockURL,final String jobSerialId) {
        //执行任务
        if(JobExecutorUtils.executeJob(yinkerBalanceJob,key,unlockURL,jobSerialId)){
            return "SUCCESS";
        }
        return "ERROR";
    }
    /**
     * 
    * @Title: readYinkerBatch 
    * @Description: 读取银客懒猫数据文件自动任务
    * @param @param key
    * @param @param unlockURL
    * @param @param jobSerialId
    * @param @return
    * @param @throws Exception
    * @return String 
    * @author zhaojie
    * @date 2016年7月13日 上午11:53:39
    * @throws
     */
    @ResponseBody
    @RequestMapping(value = "/readYinkerBatch")
    public String readYinkerBatch(final String key,final String unlockURL,final String jobSerialId) throws Exception {
        //执行任务
        if(JobExecutorUtils.executeJob(yinkerBatchJob,key,unlockURL,jobSerialId)){
            return "SUCCESS";
        }
        return "ERROR";
    }
}

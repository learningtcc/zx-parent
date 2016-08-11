package com.ink.msgcenter.external.sms.zw.job.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.ink.base.redis.client.Yedis;
import com.ink.msgcenter.api.constants.CacheConstant;
import com.ink.msgcenter.core.po.SmsChannel;
import com.ink.msgcenter.core.po.SmsReport;
import com.ink.msgcenter.core.query.SmsReportQuery;
import com.ink.msgcenter.core.service.ISmsReportManager;
import com.ink.msgcenter.external.sms.zw.job.service.RedisSmsStatusReportService;

/**
 * 筑望redis短信状态报告实现类
 * Created by aiyungui on 2016/7/4.
 */
@Service("redisSmsStatusReportService002")
public class ZwRedisSmsStatusReportServiceImpl implements RedisSmsStatusReportService {
    @Autowired
    private Yedis yedis;
    @Autowired
    private ISmsReportManager smsReportManager;
    @Autowired
    private ZwSmsReportLogService zwSmsReportLogService;

    @Override
    public void operateSmsReport(SmsChannel smsChannel) {
        //检查redis是否存在未更新的状态报告
        String redisKey = CacheConstant.MSG_STATUS_REPORT_LIST + "_" + smsChannel.getChnType();
        long smsCount = yedis.scard(redisKey);

        if (smsCount < 1){
            return ;
        }
        Map<String,List<Map<String,Object>>> reportListMap = new HashMap<String,List<Map<String,Object>>>();
        List<Map<String,Object>> reportMapList;

        Set<String> smsStatusSets = yedis.smembers(redisKey);

        for (String smsStatusJson : smsStatusSets){
            Map<String,String> smsResult = (Map<String, String>) JSON.parse(smsStatusJson);
            String reportMobile = smsResult.get("mobile");
            String reportTaskId = smsResult.get("taskid");
            if (reportListMap.containsKey(reportTaskId)){
                reportMapList = reportListMap.get(reportTaskId);
            }else{
                reportMapList = new ArrayList<Map<String,Object>>();
            }
            //1、获取系统记录短信报告
            SmsReportQuery smsReportQuery = new SmsReportQuery();
            smsReportQuery.setMobile(reportMobile);
            smsReportQuery.setTaskId(reportTaskId);
            List<SmsReport> smsReports = smsReportManager.find(smsReportQuery);
            if (!(null == smsReports || smsReports.isEmpty())){//还没有存储到数据库，不做任何操作
                SmsReport smsReport = smsReports.get(0);
                //操作短信状态报告相关日志
                zwSmsReportLogService.operateSmsReportLog(smsReport, smsResult);
                //从redis中移除
                yedis.smove(redisKey,smsStatusJson);

                Map<String,Object> reportMap = new HashMap<String,Object>();
                reportMap.put("smsReport",smsReport);
                reportMap.put("smsResult",smsResult);
                reportMapList.add(reportMap);
                reportListMap.put(reportTaskId,reportMapList);
            }
        }
        //通知业务系统
        zwSmsReportLogService.notifyBizSystem(reportListMap);
    }
}

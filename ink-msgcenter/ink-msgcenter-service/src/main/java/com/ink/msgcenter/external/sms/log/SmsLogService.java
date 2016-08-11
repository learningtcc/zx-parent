package com.ink.msgcenter.external.sms.log;

import com.alibaba.fastjson.JSON;
import com.ink.msgcenter.api.util.MsgCode;
import com.ink.msgcenter.cache.MerchantUtil;
import com.ink.msgcenter.core.po.SmsCode;
import com.ink.msgcenter.core.po.SmsLog;
import com.ink.msgcenter.core.po.SmsReport;
import com.ink.msgcenter.core.service.ISmsCodeManager;
import com.ink.msgcenter.core.service.ISmsReportManager;
import com.ink.msgcenter.util.HttpUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * 短信日志接口
 * Created by aiyungui on 2016/5/24.
 */
public abstract class SmsLogService {

    @Autowired
    private ISmsReportManager smsReportManager;
    @Autowired
    private MerchantUtil merchantUtil;
    @Autowired
    private ISmsCodeManager smsCodeManager;
    /**
     * 保存短信状态报告表
     * @param smsLog
     */
    public void saveSmsReport(SmsLog smsLog,String logId){

        if (!"1".equals(smsLog.getSendStatus())){
            return ;
        }

        SmsReport smsReport = new SmsReport();
        smsReport.setMerctCode(smsLog.getMerctCode());
        smsReport.setSmsType(smsLog.getSendType());
        smsReport.setLogId(logId);
        smsReport.setMobile(smsLog.getMobile());
        smsReport.setTaskId(smsLog.getTaskId());
        smsReport.setSmsId(smsLog.getSmsId());
        smsReport.setReportUrl(smsLog.getReportUrl());
        smsReport.setCreateTime(new Date());
        smsReportManager.save(smsReport);

    }

    /**
     * 通知业务系统
     * @param smsLog
     */
    public void notifyBizSystem(SmsLog smsLog){

        List<SmsLog> smsLogList = new ArrayList<SmsLog>();
        smsLogList.add(smsLog);
        notifyBizSystem(smsLogList);
    }
    /**
     * 通知业务系统
     * @param smsLogList
     */
    public void notifyBizSystem(List<SmsLog> smsLogList){
        if (null == smsLogList || smsLogList.isEmpty()){
            return;
        }
        String reportUrl = smsLogList.get(0).getReportUrl();
        if (StringUtils.isBlank(reportUrl)){
            return;
        }

        List<Map<String,String>> paramList = new ArrayList<Map<String,String>>();
       for(SmsLog smsLog : smsLogList){
           Map<String,String> dataMap = new HashMap<String,String>();
           String retCode;
           String retMsg = "";
           if("1".equalsIgnoreCase(smsLog.getSendStatus())){//发送成功
               retCode = MsgCode.SMS_SUBMIT_SUCCESS;
           }else{
               retCode = MsgCode.SMS_SUBMIT_FAIL;
               retMsg = smsLog.getSendException();
           }

           dataMap.put("retCode", retCode);
           dataMap.put("msgId", smsLog.getSmsId());
           dataMap.put("retMsg", retMsg);
           paramList.add(dataMap);
       }
        Map<String,String> paraMap = new HashMap<String,String>();
        paraMap.put("message", JSON.toJSONString(paramList));
        HttpUtil.sendHttpPost(reportUrl,paraMap);
    }

    /**
     * 保存短信上行日志信息
     * @param smsLog
     */
    public void saveSysCode (SmsLog smsLog){
        SmsCode smsCode = new SmsCode();
        smsCode.setSmsId(smsLog.getSmsId());
        smsCode.setSmsCode(smsLog.getSmsCode());
        smsCode.setMerctId(smsLog.getMerctId());
        smsCode.setMerctCode(smsLog.getMerctCode());
        smsCode.setTaskId(smsLog.getTaskId());
        smsCode.setMobile(smsLog.getMobile());
        smsCode.setStatus("0");
        smsCode.setExtInfo(smsLog.getExtInfo());
        smsCode.setUpUrl(smsLog.getUpUrl());
        smsCode.setCreateTime(new Date());
        smsCodeManager.save(smsCode);
    }

    /**
     * 保存Db(mysql mongo)
     * @param smsLog
     */
    public abstract void saveDb(SmsLog smsLog);

    /**
     * 统计发送日志
     * @param smsLog
     * @param sendCount
     * @param successCount
     * @param failCount
     */
    public abstract void recordSmsAnalyze(SmsLog smsLog, int sendCount, int successCount, int failCount);
}

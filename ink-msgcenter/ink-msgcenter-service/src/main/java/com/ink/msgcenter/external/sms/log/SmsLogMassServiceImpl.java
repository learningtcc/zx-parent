package com.ink.msgcenter.external.sms.log;

import com.ink.msgcenter.core.po.SmsLog;
import com.ink.msgcenter.core.po.SmsMongoLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 短信群发接口 日志
 * Created by aiyungui on 2016/5/24.
 */
@Component("smsLogMassService")
@Transactional
public class SmsLogMassServiceImpl extends SmsLogService{

    @Autowired
    private MongoTemplate mongoTemplate;
    /**
     * 保存Db(mongo)
     *
     * @param smsLog
     */
    @Override
    public void saveDb(SmsLog smsLog) {

        String[] mobiles = smsLog.getMobile().split(",");
        List<SmsLog> smsLogList = new ArrayList<SmsLog>();
        for (String mobile : mobiles){
            smsLog.setMobile(mobile);
            SmsMongoLog smsMongoLog = changeMongoLog(smsLog);
            mongoTemplate.insert(smsMongoLog,"sms_log_"+smsLog.getMerctCode());
            //保存状态报告
            saveSmsReport(smsLog, smsMongoLog.getId());
            //发送失败 通知调用业务系统
            if ("2".equals(smsLog.getSendStatus())){
                smsLogList.add(smsLog);
            }

        }

        if ("2".equals(smsLog.getSendStatus())){
                notifyBizSystem(smsLogList);
        }
    }

    /**
     * 统计发送日志
     *
     * @param smsLog
     * @param sendCount
     * @param successCount
     * @param failCount
     */
    @Override
    public void recordSmsAnalyze(SmsLog smsLog, int sendCount, int successCount, int failCount) {

    }

    private SmsMongoLog changeMongoLog(SmsLog smsLog){
        SmsMongoLog smsMongoLog = new SmsMongoLog();
        smsMongoLog.setChnCode(smsLog.getChnCode());
        smsMongoLog.setChnId(smsLog.getChnId());
        smsMongoLog.setMerctId(smsLog.getMerctId());
        smsMongoLog.setMerctCode(smsLog.getMerctCode());
        smsMongoLog.setTempId(smsLog.getTempId());
        smsMongoLog.setMobile(smsLog.getMobile());
        smsMongoLog.setSmsMsg(smsLog.getSmsMsg());
        smsMongoLog.setInfoCode(smsLog.getInfoCode());
        smsMongoLog.setSmsId(smsLog.getSmsId());
        smsMongoLog.setTaskId(smsLog.getTaskId());
        smsMongoLog.setResponseCode(smsLog.getResponseCode());
        smsMongoLog.setSmsType(smsLog.getSmsType());
        smsMongoLog.setSubmitTime(smsLog.getSubmitTime());
        smsMongoLog.setSendTime(smsLog.getSendTime());
        smsMongoLog.setFixTime(smsLog.getFixTime());
        smsMongoLog.setSendException(smsLog.getSendException());
        smsMongoLog.setSendStatus(smsLog.getSendStatus());
        smsMongoLog.setReportUrl(smsLog.getReportUrl());
        smsMongoLog.setUpUrl(smsLog.getUpUrl());

        return smsMongoLog;
    }
}

package com.ink.msgcenter.external.sms.log;

import com.ink.base.utils.dateUtil.DateUtil;
import com.ink.msgcenter.core.po.SmsAnalyze;
import com.ink.msgcenter.core.po.SmsLog;
import com.ink.msgcenter.core.service.ISmsAnalyzeManager;
import com.ink.msgcenter.core.service.ISmsLogManager;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSessionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 保存日志信息 单手机号
 * Created by aiyungui on 2016/5/24.
 */
@Component("smsLogSingleService")
@Transactional
public class SmsLogSingleServiceImpl extends SmsLogService {

    @Autowired
    private ISmsLogManager smsLogManager;
    @Autowired
    private ISmsAnalyzeManager smsAnalyzeManager;
    /**
     * 保存Db(mysql mongo)
     *
     * @param smsLog
     */
    @Override
    public void saveDb(SmsLog smsLog) {

        smsLogManager.save(smsLog);
        String logId = String.valueOf(smsLog.getId());

        if ("1".equalsIgnoreCase(smsLog.getSendType())){//单发短信
            if("1".equalsIgnoreCase(smsLog.getSendStatus())){//发送成功
                recordSmsAnalyze(smsLog, 1, 1, 0);
            }else{
                recordSmsAnalyze(smsLog, 1, 0, 1);
            }
            //保存短信上行日志信息
            if (StringUtils.isNotBlank(smsLog.getSmsCode())){
                saveSysCode(smsLog);
            }

        }

        //保存至短信状态报告表
        saveSmsReport(smsLog, logId);
        //发送失败 通知调用业务系统
        if("2".equals(smsLog.getSendStatus())){
            notifyBizSystem(smsLog);
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
        SmsAnalyze analyze = new SmsAnalyze();

        analyze.setChnCode(smsLog.getChnCode());
        analyze.setChnId(smsLog.getChnId());
        analyze.setAnalyzeDateString(DateUtil.formatToYYYYMMDD(new Date()));
        analyze.setSendCount((long) sendCount);
        analyze.setFailCount((long) failCount);
        analyze.setSuccessCount((long) successCount);

        int count = smsAnalyzeManager.updateSmsStatistics(analyze);
        if(count == 0){
            analyze.setSendCount((long) 1);
            try {
                synchronized (analyze){
                    smsAnalyzeManager.save(analyze);
                }
            } catch (SqlSessionException e) {
                analyze.setSendCount((long) sendCount);
                smsAnalyzeManager.updateSmsStatistics(analyze);
            }

        }
    }

}

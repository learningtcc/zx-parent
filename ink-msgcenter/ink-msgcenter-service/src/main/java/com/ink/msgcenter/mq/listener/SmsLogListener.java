package com.ink.msgcenter.mq.listener;

import com.ink.base.log.util.YinkerLogger;
import com.ink.base.utils.context.SpringApplicationContext;
import com.ink.msgcenter.api.constants.YkDataContant;
import com.ink.msgcenter.core.po.SmsLog;
import com.ink.msgcenter.external.sms.log.SmsLogService;

/**
 * 短信日志MQ监听器
 * Created by aiyungui on 2016/5/19.
 */
public class SmsLogListener {

    private YinkerLogger loger = YinkerLogger.getLogger(SmsLogListener.class);
    private SmsLogService smsLogService;
    public void init(String sendType){
        if ("1".equals(sendType)){
            smsLogService = (SmsLogService) SpringApplicationContext.getBean("smsLogSingleService");
        }else{
            smsLogService = (SmsLogService) SpringApplicationContext.getBean("smsLogMassService");
        }
    }

    /**
     * MQ 存储日志信息
     * @param smsLog
     */
    public void storeSmsLog(SmsLog smsLog){

        try{
            init(smsLog.getSendType());
            smsLogService.saveDb(smsLog);
        }catch (Exception e){
            loger.error(YkDataContant.MODULE_SMS_CODE, YkDataContant.INFO_SMS_DOWN_SEND_CODE,
                    "短信日志保存失败，日志信息："+smsLog+"，异常信息：" + e.getMessage(), e,"");
        }
        loger.info(YkDataContant.MODULE_SMS_CODE, YkDataContant.INFO_SMS_DOWN_SEND_CODE,
                "短信日志保存成功，" + smsLog);
    }
}

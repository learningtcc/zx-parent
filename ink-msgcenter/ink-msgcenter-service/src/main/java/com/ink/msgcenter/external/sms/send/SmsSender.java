package com.ink.msgcenter.external.sms.send;

import com.ink.msgcenter.core.po.SmsLog;
import com.ink.msgcenter.mq.SmsDTO;

/**
 * Created by aiyungui on 2016/5/26.
 */
public interface SmsSender {

    /**
     * 发送短信
     * @param smsDTO
     * @param content
     * @return
     * @throws Exception
     */
    public String send(SmsDTO smsDTO,String content)throws Exception;

    /**
     * 获取smsLog
     * @param smsDTO
     * @param content
     * @param result
     * @return
     * @throws Exception
     */
    public SmsLog getSmsLog(SmsDTO smsDTO,String content,String result)throws Exception;
}

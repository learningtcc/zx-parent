package com.ink.msgcenter.external.sms.zw.job.service;

import com.ink.msgcenter.core.po.SmsChannel;

/**
 * redis短信状态报告日志接口类
 * Created by aiyungui on 2016/7/4.
 */
public interface RedisSmsStatusReportService {

    void operateSmsReport(SmsChannel smsChannel);
}

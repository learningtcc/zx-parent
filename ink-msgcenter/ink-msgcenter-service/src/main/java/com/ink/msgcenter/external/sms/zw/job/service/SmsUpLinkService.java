package com.ink.msgcenter.external.sms.zw.job.service;

import com.ink.msgcenter.core.po.SmsChannel;

/**
 * 短信上行
 * Created by aiyungui on 2016/7/4.
 */
public interface SmsUpLinkService {

    void operateUpLink(SmsChannel record);
}

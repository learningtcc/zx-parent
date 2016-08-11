package com.ink.msgcenter.external.sms.zw.job;

import com.ink.base.log.job.JobLoggerable;
import com.ink.base.utils.context.SpringApplicationContext;
import com.ink.msgcenter.core.po.SmsChannel;
import com.ink.msgcenter.core.query.SmsChannelQuery;
import com.ink.msgcenter.core.service.ISmsChannelManager;
import com.ink.msgcenter.external.sms.zw.job.service.SmsChannelReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 短信状态报告接口
 * Created by aiyungui on 2016/5/20.
 */
@Component("smsStatusReportJob")
public class SmsStatusReportJob extends JobLoggerable {

    @Autowired
    private ISmsChannelManager smsChannelManager;

    /**
     * 执行任务业务逻辑信息
     */
    @Override
    protected void executeService() {

        //根据通道获取
        SmsChannelQuery smsChannelQuery = new SmsChannelQuery();
        smsChannelQuery.setStatus("0");
        List<SmsChannel> smsChannelList = smsChannelManager.find(smsChannelQuery);
        for (SmsChannel smsChannel : smsChannelList){
            try{
                SmsChannelReportService smsChannelReportService = (SmsChannelReportService) SpringApplicationContext.getBean("smsChannelReportService" + smsChannel.getChnType());
                if (smsChannelReportService != null){
                    smsChannelReportService.operateSmsReport(smsChannel);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}

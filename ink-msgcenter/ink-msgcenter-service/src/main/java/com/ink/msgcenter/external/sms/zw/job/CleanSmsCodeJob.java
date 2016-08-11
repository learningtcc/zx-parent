package com.ink.msgcenter.external.sms.zw.job;

import com.ink.base.log.job.JobLoggerable;
import com.ink.msgcenter.core.service.ISmsCodeManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 清楚短信上行失效数据信息
 * Created by aiyungui on 2016/7/4.
 */
@Component("clearSmsCodeJob")
public class CleanSmsCodeJob extends JobLoggerable {

    @Autowired
    private ISmsCodeManager smsCodeManager;

    /**
     * 执行任务业务逻辑信息
     */
    @Override
    protected void executeService() {
        smsCodeManager.deleteInvalidData();
    }
}

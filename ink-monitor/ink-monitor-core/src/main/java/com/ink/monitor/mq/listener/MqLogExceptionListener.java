package com.ink.monitor.mq.listener;

import com.ink.monitor.core.service.IMonitorMqLogManager;
import com.ink.base.log.rabbit.core.MessageLog;
import com.ink.base.log.util.YinkerLogger;
import com.ink.base.utils.context.SpringApplicationContext;
import com.ink.monitor.core.po.MonitorMqLog;
import org.apache.commons.beanutils.BeanUtils;

import java.util.Date;

/**
 * mq日志异常监听器
 * mq发送异常信息，在执行失败一定次数之后，出发此mq记入监控系统
 * Created by aiyungui on 2016/7/26.
 */
public class MqLogExceptionListener {

    private IMonitorMqLogManager monitorMqLogManager;
    private YinkerLogger yinkerLogger = YinkerLogger.getLogger(MqLogExceptionListener.class);

    private void init(){
        if (monitorMqLogManager ==null){
            monitorMqLogManager = (IMonitorMqLogManager) SpringApplicationContext.getBean("monitorMqLogManager");
        }
    }

    public void execute(MessageLog messageLog){
        try{
            if (messageLog == null){
                return;
            }

            init();

            MonitorMqLog monitorMqLog = new MonitorMqLog();
            BeanUtils.copyProperties(monitorMqLog,messageLog);
            monitorMqLog.setLogStatus("0");
            monitorMqLog.setCreateTime(new Date());
            monitorMqLogManager.save(monitorMqLog);
        }catch (Exception e){
            yinkerLogger.error("mq日志异常监听器出现异常，messageLog：" + messageLog.toString(),e);
        }
    }
}

package com.ink.admin.monitor.controller.job; /**
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ink.base.log.util.YinkerLogger;
import com.ink.base.service.DubboBaseService;
import com.ink.job.AbstractBaseJob;
import com.ink.monitor.core.po.MonitorService;
import com.ink.monitor.core.query.MonitorServiceQuery;

/**
 * @author  aiyungui
 * @version 1.0
 * 服务监控调度
 * Created by Lenovo on 2016/4/27.
 */
@Component("monitor/monitorServiceJob")
public class MonitorServiceJob extends AbstractBaseJob {

    private YinkerLogger loger = YinkerLogger.getLogger(MonitorServiceJob.class);

    @Autowired
    private DubboBaseService monitorDubboBaseService;

    @Override
    public void execute() {
        try{
            loger.info("服务监控调度","************start****************");
            MonitorServiceQuery monitorServiceQuery = new MonitorServiceQuery();
            monitorServiceQuery.setStatus("0");
            List<MonitorService> monitorServiceList = monitorDubboBaseService.find("iMonitorServiceManager",monitorServiceQuery);
            for (int i = 0; i < monitorServiceList.size(); i++) {
                MonitorService monitorService = monitorServiceList.get(i);
                if("http".equalsIgnoreCase(monitorService.getSourceType())){//http访问方式
                    monitorDubboBaseService.executeDynamicMethod("httpMonitorService","executeMonitorService",monitorService);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            loger.error("服务监控调度", "调度出现异常"+e.getMessage());
        }
        loger.info("服务监控调度","************end****************");
    }
}

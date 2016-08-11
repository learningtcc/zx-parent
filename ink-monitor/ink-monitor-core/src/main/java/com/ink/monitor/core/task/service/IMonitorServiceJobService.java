package com.ink.monitor.core.task.service;

import com.ink.monitor.core.po.MonitorService;
import com.ink.base.IBaseManager;

/**
 *  监控服务模版类
 * Created by aiyungui on 2016/4/27.
 */
public interface IMonitorServiceJobService extends IBaseManager<MonitorService, Integer> {

    void executeMonitorService(MonitorService monitorService)throws Exception;
}

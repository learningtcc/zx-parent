/**
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.monitor.core.service;

import com.ink.monitor.core.po.MonitorUserService;
import com.ink.base.IBaseManager;

import java.util.List;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 public interface IMonitorUserServiceManager extends IBaseManager<MonitorUserService, Integer>{

 List findMonitorServiceTree(MonitorUserService monitorUserService);

 int saveMonitorUserService(MonitorUserService monitorUserService);

 int deleteMonitorUserService(MonitorUserService monitorUserService);
}

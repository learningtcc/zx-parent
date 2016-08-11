/**
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.monitor.core.dao;

import com.ink.monitor.core.po.MonitorUser;
import com.ink.monitor.core.po.MonitorUserService;
import com.ink.base.EntityDao;

import java.util.List;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 

public interface IMonitorUserServiceDao extends EntityDao<MonitorUserService, Integer>{

    List findMonitorServiceTree(MonitorUserService monitorUserService);

    int deleteByUserId(MonitorUserService monitorUserService);


    List<MonitorUser> findServiceMonitorUser(MonitorUserService monitorUserService);

    int deleteMonitorUserService(MonitorUserService monitorUserService);

}
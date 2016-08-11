/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.monitor.core.dao;

import com.ink.base.EntityDao;
import com.ink.monitor.core.po.MonitorMqLog;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 

public interface IMonitorMqLogDao extends EntityDao<MonitorMqLog, Long>{

	public int updateNotNull(MonitorMqLog monitorMqLog);
}
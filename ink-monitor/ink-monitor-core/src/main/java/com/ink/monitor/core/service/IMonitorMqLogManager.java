/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.monitor.core.service;

import com.ink.monitor.core.po.MonitorMqLog;
import com.ink.base.IBaseManager;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 public interface IMonitorMqLogManager extends IBaseManager<MonitorMqLog, Long>{
	

		public int updateNotNull(MonitorMqLog monitorMqLog);

    /**
     * 再次发送MQ
     * @param monitorMqLog
     */
    String sendMq(MonitorMqLog monitorMqLog);
}

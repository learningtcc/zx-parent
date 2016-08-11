/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.monitor.core.dao.impl;

import com.ink.monitor.core.dao.IMonitorMqLogDao;
import org.springframework.stereotype.Repository;

import com.ink.base.dao.BaseIbatisDao;
import com.ink.monitor.core.po.MonitorMqLog;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Repository("monitorMqLogDao")
public class MonitorMqLogDaoImpl extends BaseIbatisDao<MonitorMqLog,Long> implements IMonitorMqLogDao {
	
	@Override
	public String getIbatisSqlMapNamespace() {
		return "MonitorMqLog";
	}
	
	@Override
	protected void prepareObjectForSave(MonitorMqLog entity) {
//		if(entity.getId()) == null) {
//			entity.setId(IdGenerator.genUUIDStr().longValue());
//		}
	}


	public int updateNotNull(MonitorMqLog monitorMqLog){
		return getSqlSession().update("MonitorMqLog.updateNotNull",monitorMqLog);
	}
}

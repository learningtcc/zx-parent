/**
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.monitor.core.dao.impl;

import com.ink.monitor.core.dao.IMonitorServiceDao;
import org.springframework.stereotype.Repository;

import com.ink.base.dao.BaseIbatisDao;
import com.ink.monitor.core.po.MonitorService;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Repository("monitorServiceDao")
public class MonitorServiceDaoImpl extends BaseIbatisDao<MonitorService,Integer> implements IMonitorServiceDao {
	
	@Override
	public String getIbatisSqlMapNamespace() {
		return "MonitorService";
	}
	
	@Override
	protected void prepareObjectForSave(MonitorService entity) {
//		if(entity.getId()) == null) {
//			entity.setId(IdGenerator.genUUIDStr().longValue());
//		}
	}

	@Override
	public int updateStatus(MonitorService monitorService) {
		return this.getSqlSessionSlave()
				.delete(getIbatisSqlMapNamespace() + ".updateStatus", monitorService);
	}
}

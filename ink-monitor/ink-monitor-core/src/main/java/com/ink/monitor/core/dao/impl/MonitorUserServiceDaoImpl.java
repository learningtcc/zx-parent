/**
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.monitor.core.dao.impl;

import com.ink.monitor.core.po.MonitorUser;
import org.springframework.stereotype.Repository;

import com.ink.base.dao.BaseIbatisDao;
import com.ink.monitor.core.dao.IMonitorUserServiceDao;
import com.ink.monitor.core.po.MonitorUserService;

import java.util.List;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Repository("monitorUserServiceDao")
public class MonitorUserServiceDaoImpl extends BaseIbatisDao<MonitorUserService,Integer> implements IMonitorUserServiceDao{
	
	@Override
	public String getIbatisSqlMapNamespace() {
		return "MonitorUserService";
	}
	
	@Override
	protected void prepareObjectForSave(MonitorUserService entity) {
//		if(entity.getId()) == null) {
//			entity.setId(IdGenerator.genUUIDStr().longValue());
//		}
	}

	@Override
	public List findMonitorServiceTree(MonitorUserService monitorUserService) {
		List monitorServiceList = this.getSqlSessionSlave()
				.selectList(getIbatisSqlMapNamespace() + ".findMonitorServiceTree", monitorUserService);
		return monitorServiceList;
	}

	@Override
	public int deleteByUserId(MonitorUserService monitorUserService) {
		return this.getSqlSessionSlave()
				.delete(getIbatisSqlMapNamespace() + ".deleteByUserId", monitorUserService);
	}

	@Override
	public List<MonitorUser> findServiceMonitorUser(MonitorUserService monitorUserService) {
		return this.getSqlSessionSlave()
				.selectList(getIbatisSqlMapNamespace() + ".findServiceMonitorUser", monitorUserService);
	}

	@Override
	public int deleteMonitorUserService(MonitorUserService monitorUserService) {
		return this.getSqlSessionSlave()
				.delete(getIbatisSqlMapNamespace() + ".deleteMonitorUserService", monitorUserService);
	}
}

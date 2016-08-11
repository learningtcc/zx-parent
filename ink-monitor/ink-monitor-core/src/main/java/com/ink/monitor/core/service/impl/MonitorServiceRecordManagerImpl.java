/**
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.monitor.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ink.base.BaseManager;
import com.ink.base.EntityDao;
import com.ink.monitor.core.po.MonitorServiceRecord;
import com.ink.monitor.core.service.IMonitorServiceRecordManager;
import com.ink.monitor.core.dao.IMonitorServiceRecordDao;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Service("monitorServiceRecordManager")
@Transactional
public class MonitorServiceRecordManagerImpl extends BaseManager<MonitorServiceRecord,Integer> implements IMonitorServiceRecordManager{

	@Autowired
	private IMonitorServiceRecordDao monitorServiceRecordDao;

	public EntityDao<MonitorServiceRecord, Integer> getEntityDao() {
		return this.monitorServiceRecordDao;
	}

	@Override
	public List<MonitorServiceRecord> getMonitorService(String sysCode) {
		
		return monitorServiceRecordDao.getMonitorService(sysCode);
	}

	@Override
	public MonitorServiceRecord getErrCount(String sysCode) {
		
		return monitorServiceRecordDao.getErrCount(sysCode);
	}

	@Override
	public List<MonitorServiceRecord> getErrCountByStatus(String sysCode) {
		return monitorServiceRecordDao.getErrCountByStatus(sysCode);
	}
	
}

/**
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.monitor.core.service.impl;

import com.ink.monitor.core.dao.IMonitorServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ink.base.BaseManager;
import com.ink.base.EntityDao;
import com.ink.monitor.core.po.MonitorService;
import com.ink.monitor.core.service.IMonitorServiceManager;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Service("monitorServiceManager")
@Transactional
public class MonitorServiceManagerImpl extends BaseManager<MonitorService,Integer> implements IMonitorServiceManager{

	@Autowired
	private IMonitorServiceDao monitorServiceDao;

	public EntityDao<MonitorService, Integer> getEntityDao() {
		return this.monitorServiceDao;
	}
	
}

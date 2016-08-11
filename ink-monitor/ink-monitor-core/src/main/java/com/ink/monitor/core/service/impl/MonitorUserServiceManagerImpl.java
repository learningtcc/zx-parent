/**
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.monitor.core.service.impl;

import com.ink.monitor.core.po.MonitorUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ink.base.BaseManager;
import com.ink.base.EntityDao;
import com.ink.monitor.core.service.IMonitorUserServiceManager;
import com.ink.monitor.core.dao.IMonitorUserServiceDao;

import java.util.ArrayList;
import java.util.List;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Service("monitorUserServiceManager")
@Transactional
public class MonitorUserServiceManagerImpl extends BaseManager<MonitorUserService,Integer> implements IMonitorUserServiceManager{

	@Autowired
	private IMonitorUserServiceDao monitorUserServiceDao;

	public EntityDao<MonitorUserService, Integer> getEntityDao() {
		return this.monitorUserServiceDao;
	}

	@Override
	public List findMonitorServiceTree(MonitorUserService monitorUserService) {
		return monitorUserServiceDao.findMonitorServiceTree( monitorUserService);
	}

	/**
	 *保存用户服务关联选择
	 * @param monitorUserService
	 * @return
	 */
	@Override
	public int saveMonitorUserService(MonitorUserService monitorUserService) {

		if(StringUtils.isBlank(monitorUserService.getServiceIds())){
			return monitorUserServiceDao.deleteByUserId(monitorUserService);
		}
		String[] serviceIds = monitorUserService.getServiceIds().substring(1).split(";");
		List<MonitorUserService> monitorUserServiceList = new ArrayList<MonitorUserService>();
		MonitorUserService saveMonitorInfo ;
		for (int i = 0; i < serviceIds.length; i++) {
			String[] choiceServices = serviceIds[i].split(",");
			saveMonitorInfo = new MonitorUserService();
			saveMonitorInfo.setUserId(monitorUserService.getUserId());
			saveMonitorInfo.setSysCode(choiceServices[0]);
			saveMonitorInfo.setServiceId(Integer.valueOf(choiceServices[1]));
			monitorUserServiceList.add(saveMonitorInfo);
		}

		monitorUserServiceDao.deleteByUserId(monitorUserService);

		return monitorUserServiceDao.saveBatch(monitorUserServiceList);
	}

	@Override
	public int deleteMonitorUserService(MonitorUserService monitorUserService) {
		return monitorUserServiceDao.deleteMonitorUserService(monitorUserService);
	}
}

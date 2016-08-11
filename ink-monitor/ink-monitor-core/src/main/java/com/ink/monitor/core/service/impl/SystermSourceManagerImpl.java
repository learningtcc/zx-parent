/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.monitor.core.service.impl;

import com.ink.monitor.core.dao.*;
import com.ink.monitor.core.po.*;
import com.ink.monitor.core.service.IMonitorModuleRuleManager;
import com.ink.monitor.core.dao.*;
import com.ink.monitor.core.po.*;
import com.ink.monitor.core.service.IMonitorInfoRuleManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ink.base.BaseManager;
import com.ink.base.EntityDao;
import com.ink.monitor.core.service.ISystermSourceManager;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Service("systermSourceManager")
@Transactional
public class SystermSourceManagerImpl extends BaseManager<SystermSource,Integer> implements ISystermSourceManager{

	@Autowired
	private ISystermSourceDao systermSourceDao;
	@Autowired
	private ISystermModuleDao systermModuleDao;
	@Autowired
	private ISystermInfoDao systermInfoDao;
	@Autowired
	private IMonitorModuleRuleManager monitorModuleRuleManager;
	@Autowired
	private IMonitorInfoRuleManager monitorInfoRuleManager;
	@Autowired
	private IMonitorServiceDao monitorServiceDao;
	@Autowired
	private IMonitorUserServiceDao monitorUserServiceDao;
//	@Autowired
//	private IMonitorUserRuleDao monitorUserRuleDao;


	public EntityDao<SystermSource, Integer> getEntityDao() {
		return this.systermSourceDao;
	}

	/**
	 * 更新信息
	 *
	 * @param systermSource 参数
	 * @return 更新行数
	 */
	@Override
	public void updateInfo(SystermSource systermSource) {
		systermSourceDao.update(systermSource);
		updateStatus(systermSource);
	}

	/**
	 * 删除信息
	 *
	 * @param id
	 */
	@Override
	public void deleteInfo(Integer id,String status) {

		SystermSource systermSource1 = new SystermSource();
		systermSource1.setId(id);
		systermSource1.setStatus(status);
		systermSourceDao.updateInfoStatus(systermSource1);

		SystermSource systermSource = systermSourceDao.getById(id);
		updateStatus(systermSource);
	}

	private void updateStatus(SystermSource systermSource){

		//更新功能模块状态
		SystermModule systermModule = new SystermModule();
		systermModule.setStatus(systermSource.getStatus());
		systermModule.setSystermCode(systermSource.getCode());
		systermModuleDao.updateStatus(systermModule);
		//更新业务模块状态
		SystermInfo systermInfo = new SystermInfo();
		systermInfo.setSystermCode(systermSource.getCode());
		systermInfo.setStatus(systermSource.getStatus());
		systermInfoDao.updateStatus(systermInfo);
		//更新功能模块监控状态
		MonitorModuleRule monitorModuleRule = new MonitorModuleRule();
		monitorModuleRule.setStatus(systermSource.getStatus());
		monitorModuleRule.setSysCode(systermSource.getCode());
		monitorModuleRuleManager.updateStatus(monitorModuleRule);
		//更新业务监控模块状态
		MonitorInfoRule monitorInfoRule = new MonitorInfoRule();
		monitorInfoRule.setStatus(systermSource.getStatus());
		monitorInfoRule.setSysCode(systermSource.getCode());
		monitorInfoRuleManager.updateStatus(monitorInfoRule);
		//更新监控服务状态
		MonitorService monitorService = new MonitorService();
		monitorService.setStatus(systermSource.getStatus());
		monitorService.setSysCode(systermSource.getCode());
		monitorServiceDao.updateStatus(monitorService);

		//更新用户监控服务权限信息
		MonitorUserService monitorUserService = new MonitorUserService();
		monitorUserService.setSysCode(systermSource.getCode());
		monitorUserServiceDao.deleteMonitorUserService(monitorUserService);

		//更新用户监控功能权限系
//		MonitorUserRule monitorUserRule = new MonitorUserRule();
//		monitorUserRule.setSysCode(systermSource.getCode());
//		monitorUserRuleDao.deleteMonitorUserRule(monitorUserRule);
	}
}

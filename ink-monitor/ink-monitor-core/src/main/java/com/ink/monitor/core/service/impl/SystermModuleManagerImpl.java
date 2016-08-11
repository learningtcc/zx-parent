/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.monitor.core.service.impl;

import com.ink.monitor.core.po.MonitorModuleRule;
import com.ink.monitor.core.service.IMonitorModuleRuleManager;
import com.ink.monitor.core.service.ISystermModuleManager;
import com.ink.base.BaseManager;
import com.ink.base.EntityDao;
import com.ink.monitor.core.dao.ISystermInfoDao;
import com.ink.monitor.core.dao.ISystermModuleDao;
import com.ink.monitor.core.po.MonitorInfoRule;
import com.ink.monitor.core.po.SystermInfo;
import com.ink.monitor.core.po.SystermModule;
import com.ink.monitor.core.service.IMonitorInfoRuleManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Service("systermModuleManager")
@Transactional
public class SystermModuleManagerImpl extends BaseManager<SystermModule,Integer> implements ISystermModuleManager {

	@Autowired
	private ISystermModuleDao systermModuleDao;
	@Autowired
	private ISystermInfoDao systermInfoDao;
	@Autowired
	private IMonitorModuleRuleManager monitorModuleRuleManager;
	@Autowired
	private IMonitorInfoRuleManager monitorInfoRuleManager;
//	@Autowired
//	private IMonitorUserRuleDao monitorUserRuleDao;

	public EntityDao<SystermModule, Integer> getEntityDao() {
		return this.systermModuleDao;
	}

	/**
	 * 更新信息
	 *
	 * @param systermModule 参数
	 * @return 更新行数
	 */
	@Override
	public void updateInfo(SystermModule systermModule) {

		systermModuleDao.update(systermModule);
		updateStatus(systermModule);
	}

	@Override
	public void deleteInfo(Integer id,String status) {
		SystermModule systermModule1 = new SystermModule();
		systermModule1.setId(id);
		systermModule1.setStatus(status);
		systermModuleDao.updateInfoStatus(systermModule1)
		;
		SystermModule systermModule = systermModuleDao.getById(id);
		updateStatus(systermModule);
	}

	private void updateStatus(SystermModule systermModule){
		//更新业务模块状态
		SystermInfo systermInfo = new SystermInfo();
		systermInfo.setModuleCode(systermModule.getCode());
		systermInfo.setStatus(systermModule.getStatus());
		systermInfoDao.updateStatus(systermInfo);

		//更新功能模块监控状态
		MonitorModuleRule monitorModuleRule = new MonitorModuleRule();
		monitorModuleRule.setStatus(systermModule.getStatus());
		monitorModuleRule.setSysCode(systermModule.getSystermCode());
		monitorModuleRule.setModuleCode(systermModule.getCode());
		monitorModuleRuleManager.updateStatus(monitorModuleRule);
		//更新业务监控模块状态
		MonitorInfoRule monitorInfoRule = new MonitorInfoRule();
		monitorInfoRule.setStatus(systermModule.getStatus());
		monitorInfoRule.setModuleCode(systermModule.getCode());
		monitorInfoRule.setSysCode(systermModule.getSystermCode());
		monitorInfoRuleManager.updateStatus(monitorInfoRule);

		//更新用户监控功能权限系
//		MonitorUserRule monitorUserRule = new MonitorUserRule();
//		monitorUserRule.setSysCode(systermModule.getCode());
//		monitorUserRuleDao.deleteMonitorUserRule(monitorUserRule);
	}
}

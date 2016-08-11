/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.monitor.core.service.impl;

import com.ink.base.BaseManager;
import com.ink.base.EntityDao;
import com.ink.monitor.core.dao.ISystermInfoDao;
import com.ink.monitor.core.po.MonitorInfoRule;
import com.ink.monitor.core.po.SystermInfo;
import com.ink.monitor.core.service.IMonitorInfoRuleManager;
import com.ink.monitor.core.service.ISystermInfoManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Service("systermInfoManager")
@Transactional
public class SystermInfoManagerImpl extends BaseManager<SystermInfo,Integer> implements ISystermInfoManager{

	@Autowired
	private ISystermInfoDao systermInfoDao;
	@Autowired
	private IMonitorInfoRuleManager monitorInfoRuleManager;
//	@Autowired
//	private IMonitorUserRuleDao monitorUserRuleDao;

	public EntityDao<SystermInfo, Integer> getEntityDao() {
		return this.systermInfoDao;
	}

	/**
	 * 更新信息
	 *
	 * @param systermInfo 参数
	 * @return 更新行数
	 */
	@Override
	public void updateInfo(SystermInfo systermInfo) {
		systermInfoDao.update(systermInfo);
		updateStatus(systermInfo);
	}

	@Override
	public void deleteInfo(Integer id,String status) {
		SystermInfo systermInfo1 = new SystermInfo();
		systermInfo1.setId(id);
		systermInfo1.setStatus(status);
		systermInfoDao.updateInfoStatus(systermInfo1);

		SystermInfo systermInfo = systermInfoDao.getById(id);
		updateStatus(systermInfo);
	}

	private void updateStatus(SystermInfo systermInfo){
		//更新业务监控
		MonitorInfoRule monitorInfoRule = new MonitorInfoRule();
		monitorInfoRule.setStatus(systermInfo.getStatus());
		monitorInfoRule.setInfoCode(systermInfo.getCode());
		monitorInfoRule.setSysCode(systermInfo.getSystermCode());
		monitorInfoRule.setModuleCode(systermInfo.getModuleCode());
		monitorInfoRuleManager.updateStatus(monitorInfoRule);

		//更新用户监控功能权限系
//		MonitorUserRule monitorUserRule = new MonitorUserRule();
//		monitorUserRule.setInfoCode(systermInfo.getCode());
//		monitorUserRuleDao.deleteMonitorUserRule(monitorUserRule);

	}
}

/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.monitor.core.service.impl;

import com.ink.monitor.core.dao.IMonitorInfoRuleDao;
import com.ink.monitor.core.po.MonitorInfoRule;
import com.ink.monitor.core.po.MonitorUserRule;
import com.ink.base.BaseManager;
import com.ink.base.EntityDao;
import com.ink.monitor.core.dao.IMonitorUserRuleDao;
import com.ink.monitor.core.query.MonitorInfoRuleQuery;
import com.ink.monitor.core.service.IMonitorInfoRuleManager;
import com.ink.monitor.log.rule.MonitorPolicy;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Service("monitorInfoRuleManager")
@Transactional
public class MonitorInfoRuleManagerImpl extends BaseManager<MonitorInfoRule,Integer> implements IMonitorInfoRuleManager{

	@Autowired
	private IMonitorInfoRuleDao monitorInfoRuleDao;
	@Autowired
	private MonitorPolicy monitorPolicy;
	@Autowired
	private IMonitorUserRuleDao monitorUserRuleDao;

	public EntityDao<MonitorInfoRule, Integer> getEntityDao() {
		return this.monitorInfoRuleDao;
	}

	@Override
	public int updateStatus(MonitorInfoRule monitorInfoRule) {
		int num = monitorInfoRuleDao.updateStatus(monitorInfoRule);
		saveMonitorInfoRuleRedis(monitorInfoRule,1);
		return num;
	}

	/**
	 * 保存信息
	 *
	 * @param monitorInfoRule
	 * @return
	 */
	@Override
	public int saveInfo(MonitorInfoRule monitorInfoRule) {
		int num = monitorInfoRuleDao.save(monitorInfoRule);
		saveMonitorInfoRuleRedis(monitorInfoRule,0);
		return num;
	}

	@Override
	public int deleteInfo(int id) {
		MonitorInfoRule monitorInfoRule = monitorInfoRuleDao.getById(id);
		//刷新缓存信息
		saveMonitorInfoRuleRedis(monitorInfoRule,2);
		//删除用户与功能监控信息
		MonitorUserRule monitorUserRule = new MonitorUserRule();
		monitorUserRule.setSysCode(monitorInfoRule.getSysCode());
		monitorUserRule.setModuleCode(monitorInfoRule.getModuleCode());
		monitorUserRule.setInfoCode(monitorInfoRule.getInfoCode());
		monitorUserRuleDao.deleteMonitorUserRule(monitorUserRule);

		return monitorInfoRuleDao.deleteById(id);
	}

	/**
	 * 获取业务模块信息：同时获取对应的代码名称
	 *
	 * @param monitorInfoRuleQuery
	 * @return
	 */
	@Override
	public List<Map<String, Object>> queryMonitorInfo(MonitorInfoRuleQuery monitorInfoRuleQuery) {
		return monitorInfoRuleDao.queryMonitorInfo(monitorInfoRuleQuery);
	}

	/**
	 * 保存业务监控redis缓存数据
	 * @param monitorInfoRule
	 */
	private void saveMonitorInfoRuleRedis(MonitorInfoRule monitorInfoRule,int saveFlag) {

		List<MonitorInfoRule> monitorInfoRules = new ArrayList<MonitorInfoRule>();
		if (StringUtils.isNotBlank(monitorInfoRule.getInfoCode())){
			monitorInfoRules.add(monitorInfoRule);
		}else if (StringUtils.isNotBlank(monitorInfoRule.getModuleCode())){
			MonitorInfoRuleQuery infoRuleQuery = new MonitorInfoRuleQuery();
			infoRuleQuery.setSysCode(monitorInfoRule.getSysCode());
			infoRuleQuery.setModuleCode(monitorInfoRule.getModuleCode());
			monitorInfoRules = monitorInfoRuleDao.find(infoRuleQuery);
		}else if(StringUtils.isNotBlank(monitorInfoRule.getSysCode())){
			MonitorInfoRuleQuery infoRuleQuery = new MonitorInfoRuleQuery();
			infoRuleQuery.setSysCode(monitorInfoRule.getSysCode());
			monitorInfoRules = monitorInfoRuleDao.find(infoRuleQuery);
		}

		if (null == monitorInfoRules || monitorInfoRules.isEmpty()){
			return;
		}
		for (MonitorInfoRule infoRule : monitorInfoRules){
			monitorPolicy.saveWarnRule(infoRule.getSysCode(),infoRule.getModuleCode(),
					infoRule.getInfoCode(),monitorInfoRule.getStatus(),saveFlag);
		}
	}
}

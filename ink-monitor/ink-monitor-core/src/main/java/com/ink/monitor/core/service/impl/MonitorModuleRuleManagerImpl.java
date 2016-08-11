/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.monitor.core.service.impl;

import com.ink.monitor.core.dao.IMonitorModuleRuleDao;
import com.ink.monitor.core.po.MonitorModuleRule;
import com.ink.monitor.core.po.MonitorUserRule;
import com.ink.monitor.core.query.MonitorModuleRuleQuery;
import com.ink.monitor.core.service.IMonitorModuleRuleManager;
import com.ink.monitor.core.dao.IMonitorUserRuleDao;
import com.ink.monitor.log.rule.MonitorPolicy;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ink.base.BaseManager;
import com.ink.base.EntityDao;

import java.util.List;
import java.util.Map;

/**
 * @author aiyungui
 * @version 1.0
 * @since 1.0
 */
 
@Service("monitorModuleRuleManager")
@Transactional
public class MonitorModuleRuleManagerImpl extends BaseManager<MonitorModuleRule,Integer> implements IMonitorModuleRuleManager {

	@Autowired
	private IMonitorModuleRuleDao monitorModuleRuleDao;
	@Autowired
	private MonitorPolicy monitorPolicy;
	@Autowired
	private IMonitorUserRuleDao monitorUserRuleDao;

	public EntityDao<MonitorModuleRule, Integer> getEntityDao() {
		return this.monitorModuleRuleDao;
	}

	@Override
	public int updateStatus(MonitorModuleRule monitorModuleRule) {
		int num = monitorModuleRuleDao.updateStatus(monitorModuleRule);
		saveMonitorModuleRuleRedis(monitorModuleRule, 1);
		return num;
	}

	@Override
	public int saveInfo(MonitorModuleRule monitorModuleRule) {

		int num = monitorModuleRuleDao.save(monitorModuleRule);
		saveMonitorModuleRuleRedis(monitorModuleRule,0);
		return num;
	}

	@Override
	public int deleteInfo(int id) {
		MonitorModuleRule monitorModuleRule = monitorModuleRuleDao.getById(id);
		//刷新缓存信息
		saveMonitorModuleRuleRedis(monitorModuleRule,2);
		//删除用户与功能监控信息
		MonitorUserRule monitorUserRule = new MonitorUserRule();
		monitorUserRule.setSysCode(monitorModuleRule.getSysCode());
		monitorUserRule.setModuleCode(monitorModuleRule.getModuleCode());
		monitorUserRuleDao.deleteMonitorUserRule(monitorUserRule);
		//删除功能监控信息
		return monitorModuleRuleDao.deleteById(id);
	}

	/**
	 * 获取功能模块信息：同时获取对应的代码名称
	 *
	 * @param monitorModuleRuleQuery
	 * @return
	 */
	@Override
	public List<Map<String, Object>> queryMonitorInfo(MonitorModuleRuleQuery monitorModuleRuleQuery) {
		return monitorModuleRuleDao.queryMonitorInfo(monitorModuleRuleQuery);
	}

	/**
	 * 保存模块监控redis缓存数据
	 * @param monitorModuleRule
	 */
	private void saveMonitorModuleRuleRedis(MonitorModuleRule monitorModuleRule,int saveFlag){

		if(StringUtils.isNotBlank(monitorModuleRule.getModuleCode())){//仅针对模块进行监控
			monitorPolicy.saveWarnRule(monitorModuleRule.getSysCode(),monitorModuleRule.getModuleCode(),
					null,monitorModuleRule.getStatus(),saveFlag);
		}else{//对平台系统下所有监控模块都进行更新
			MonitorModuleRuleQuery moduleRuleQuery = new MonitorModuleRuleQuery();
			moduleRuleQuery.setSysCode(monitorModuleRule.getSysCode());
			List<MonitorModuleRule> moduleRules = monitorModuleRuleDao.find(moduleRuleQuery);
			if (null == moduleRules || moduleRules.isEmpty()){
				return ;
			}

			for (int i = 0; i < moduleRules.size(); i++) {
				MonitorModuleRule moduleRule = moduleRules.get(i);
				monitorPolicy.saveWarnRule(moduleRule.getSysCode(),moduleRule.getModuleCode(),
						null,monitorModuleRule.getStatus(),saveFlag);
			}
		}
	}
}

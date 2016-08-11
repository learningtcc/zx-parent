/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.monitor.core.dao.impl;

import com.ink.monitor.core.po.MonitorModuleRule;
import com.ink.monitor.core.query.MonitorModuleRuleQuery;
import org.springframework.stereotype.Repository;

import com.ink.base.dao.BaseIbatisDao;
import com.ink.monitor.core.dao.IMonitorModuleRuleDao;

import java.util.List;
import java.util.Map;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Repository("monitorModuleRuleDao")
public class MonitorModuleRuleDaoImpl extends BaseIbatisDao<MonitorModuleRule,Integer> implements IMonitorModuleRuleDao{
	
	@Override
	public String getIbatisSqlMapNamespace() {
		return "MonitorModuleRule";
	}
	
	@Override
	protected void prepareObjectForSave(MonitorModuleRule entity) {
//		if(entity.getId()) == null) {
//			entity.setId(IdGenerator.genUUIDStr().longValue());
//		}
	}

	@Override
	public int updateStatus(MonitorModuleRule monitorModuleRule) {
		return this.getSqlSessionSlave()
				.delete(getIbatisSqlMapNamespace() + ".updateStatus", monitorModuleRule);
	}

	@Override
	public List<Map<String, Object>> queryMonitorInfo(MonitorModuleRuleQuery monitorModuleRuleQuery) {
		return this.getSqlSessionSlave()
				.selectList(getIbatisSqlMapNamespace() + ".queryMonitorInfo", monitorModuleRuleQuery);
	}
}

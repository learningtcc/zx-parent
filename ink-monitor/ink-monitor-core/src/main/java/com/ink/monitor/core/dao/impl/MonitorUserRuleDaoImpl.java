/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.monitor.core.dao.impl;

import com.ink.monitor.core.po.MonitorUserRule;
import org.springframework.stereotype.Repository;

import com.ink.base.dao.BaseIbatisDao;
import com.ink.monitor.core.dao.IMonitorUserRuleDao;

import java.util.List;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Repository("monitorUserRuleDao")
public class MonitorUserRuleDaoImpl extends BaseIbatisDao<MonitorUserRule,Integer> implements IMonitorUserRuleDao{
	
	@Override
	public String getIbatisSqlMapNamespace() {
		return "MonitorUserRule";
	}
	
	@Override
	protected void prepareObjectForSave(MonitorUserRule entity) {
//		if(entity.getId()) == null) {
//			entity.setId(IdGenerator.genUUIDStr().longValue());
//		}
	}

	@Override
	public List findMonitorInfoTree(MonitorUserRule monitorUserRule) throws Exception {
		List monitorInfoList = this.getSqlSessionSlave().selectList(getIbatisSqlMapNamespace() + ".findMonitorInfoTree",monitorUserRule);
		return monitorInfoList;
	}

	@Override
	public int updateMonitorInfo(MonitorUserRule monitorUserRule) throws Exception {
		return this.getSqlSessionSlave().update(getIbatisSqlMapNamespace() + ".updateMonitorInfo", monitorUserRule);
	}

	@Override
	public int deleteMonitorUserRule(MonitorUserRule monitorUserRule) {
		return this.getSqlSessionSlave().delete(getIbatisSqlMapNamespace() + ".deleteMonitorUserRule", monitorUserRule);
	}

	@Override
	public int deleteMonitorUserRuleByUserId(MonitorUserRule userRule) {
		return this.getSqlSessionSlave().delete(getIbatisSqlMapNamespace() + ".deleteMonitorUserRuleByUserId", userRule);
	}
}

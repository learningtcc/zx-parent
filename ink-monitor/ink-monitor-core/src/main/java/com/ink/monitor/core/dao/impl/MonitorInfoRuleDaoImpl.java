/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.monitor.core.dao.impl;

import com.ink.monitor.core.query.MonitorInfoRuleQuery;
import org.springframework.stereotype.Repository;

import com.ink.base.dao.BaseIbatisDao;
import com.ink.monitor.core.dao.IMonitorInfoRuleDao;
import com.ink.monitor.core.po.MonitorInfoRule;

import java.util.List;
import java.util.Map;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Repository("monitorInfoRuleDao")
public class MonitorInfoRuleDaoImpl extends BaseIbatisDao<MonitorInfoRule,Integer> implements IMonitorInfoRuleDao{
	
	@Override
	public String getIbatisSqlMapNamespace() {
		return "MonitorInfoRule";
	}
	
	@Override
	protected void prepareObjectForSave(MonitorInfoRule entity) {
//		if(entity.getId()) == null) {
//			entity.setId(IdGenerator.genUUIDStr().longValue());
//		}
	}

	@Override
	public int updateStatus(MonitorInfoRule monitorInfoRule) {
		return this.getSqlSessionSlave()
				.delete(getIbatisSqlMapNamespace() + ".updateStatus", monitorInfoRule);
	}

	@Override
	public List<Map<String, Object>> queryMonitorInfo(MonitorInfoRuleQuery monitorInfoRuleQuery) {
		return this.getSqlSessionSlave()
				.selectList(getIbatisSqlMapNamespace() + ".queryMonitorInfo", monitorInfoRuleQuery);
	}
}

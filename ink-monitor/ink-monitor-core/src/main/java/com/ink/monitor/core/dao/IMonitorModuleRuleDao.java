/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.monitor.core.dao;

import com.ink.monitor.core.po.MonitorModuleRule;
import com.ink.monitor.core.query.MonitorModuleRuleQuery;
import com.ink.base.EntityDao;

import java.util.List;
import java.util.Map;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 

public interface IMonitorModuleRuleDao extends EntityDao<MonitorModuleRule, Integer>{
    int updateStatus(MonitorModuleRule monitorModuleRule);

    List<Map<String,Object>> queryMonitorInfo(MonitorModuleRuleQuery monitorModuleRuleQuery);
}
/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.monitor.core.dao;

import com.ink.monitor.core.po.MonitorInfoRule;
import com.ink.base.EntityDao;
import com.ink.monitor.core.query.MonitorInfoRuleQuery;

import java.util.List;
import java.util.Map;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 

public interface IMonitorInfoRuleDao extends EntityDao<MonitorInfoRule, Integer>{

    int updateStatus(MonitorInfoRule monitorInfoRule);

    List<Map<String,Object>> queryMonitorInfo(MonitorInfoRuleQuery monitorInfoRuleQuery);
}
/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.monitor.core.dao;

import com.ink.monitor.core.po.MonitorUserRule;
import com.ink.base.EntityDao;

import java.util.List;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 

public interface IMonitorUserRuleDao extends EntityDao<MonitorUserRule, Integer>{

    /**
     * 取业务监控数据  树形结构获
     * @return
     * @throws Exception
     */
    List findMonitorInfoTree(MonitorUserRule monitorUserRule)throws Exception;


    int updateMonitorInfo(MonitorUserRule monitorUserRule)throws Exception;

    int deleteMonitorUserRule(MonitorUserRule monitorUserRule);

    int deleteMonitorUserRuleByUserId(MonitorUserRule userRule);
}
/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.monitor.core.service;

import com.ink.monitor.core.po.MonitorUserRule;
import com.ink.base.IBaseManager;

import java.util.List;


/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 public interface IMonitorUserRuleManager extends IBaseManager<MonitorUserRule, Integer>{

 /**
  * 取业务监控数据  树形结构获
  * @return
  * @throws Exception
  */
    List findMonitorInfoTree(MonitorUserRule monitorUserRule)throws Exception;

    /**
     * 保存用户前端选择监控数据
     * @param monitorUserRule
     * @return
     * @throws Exception
     */
    int saveMonitorUserRule(MonitorUserRule monitorUserRule)throws Exception;

    int deleteMonitorUserRule(MonitorUserRule monitorUserRule);
}

/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.monitor.core.service;

import com.ink.monitor.core.po.MonitorModuleRule;
import com.ink.base.IBaseManager;
import com.ink.monitor.core.query.MonitorModuleRuleQuery;
import  java.util.List;
import java.util.Map;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 public interface IMonitorModuleRuleManager extends IBaseManager<MonitorModuleRule, Integer>{

 int updateStatus(MonitorModuleRule monitorModuleRule);

 int saveInfo(MonitorModuleRule monitorModuleRule);

 int deleteInfo(int id);

 /**
  * 获取功能模块信息：同时获取对应的代码名称
  * @param monitorModuleRuleQuery
  * @return
  */
 List<Map<String,Object>> queryMonitorInfo(MonitorModuleRuleQuery monitorModuleRuleQuery);
}

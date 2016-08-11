/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.monitor.core.service;

import com.ink.monitor.core.po.MonitorInfoRule;
import com.ink.monitor.core.query.MonitorInfoRuleQuery;
import com.ink.base.IBaseManager;

import java.util.List;
import java.util.Map;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 public interface IMonitorInfoRuleManager extends IBaseManager<MonitorInfoRule, Integer>{


 /**
  * 更新状态信息
  * @param monitorInfoRule
  * @return
  */
  int updateStatus(MonitorInfoRule monitorInfoRule);

 /**
  * 保存信息
  * @param monitorInfoRule
  * @return
  */
 int saveInfo(MonitorInfoRule monitorInfoRule);

 int deleteInfo(int id);

 /**
  * 获取业务模块信息：同时获取对应的代码名称
  * @param monitorInfoRuleQuery
  * @return
  */
 List<Map<String,Object>> queryMonitorInfo(MonitorInfoRuleQuery monitorInfoRuleQuery);
}

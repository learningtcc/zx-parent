/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.monitor.core.service.impl;

import java.util.HashMap;
import java.util.List;

import com.ink.monitor.core.dao.IMonitorUserRuleDao;
import com.ink.monitor.core.po.MonitorUserRule;
import com.ink.monitor.core.query.MonitorUserRuleQuery;
import com.ink.monitor.log.rule.MonitorPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ink.base.BaseManager;
import com.ink.base.EntityDao;
import com.ink.monitor.core.po.MonitorUser;
import com.ink.monitor.core.service.IMonitorUserManager;
import com.ink.monitor.core.dao.IMonitorUserDao;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Service("monitorUserManager")
@Transactional
public class MonitorUserManagerImpl extends BaseManager<MonitorUser,java.lang.Integer> implements IMonitorUserManager{

	@Autowired
	private IMonitorUserDao monitorUserDao;
	@Autowired
	private IMonitorUserRuleDao monitorUserRuleDao;
	@Autowired
	private MonitorPolicy monitorPolicy;

	public EntityDao<MonitorUser, java.lang.Integer> getEntityDao() {
		return this.monitorUserDao;
	}
	
	
	/**
	 * 查询监控功能的用户信息
	 * @param sysCode
	 * @param moduleCode
	 * @param infoCode
	 * @return
	 */
	public List<HashMap<String, String>> queryUserByPolicy(String sysCode, String moduleCode){
		return monitorUserDao.queryUserByPolicy(sysCode, moduleCode);
	}

	@Override
	public int updatePassword(MonitorUser monitorUser) {
		return monitorUserDao.updatePassword(monitorUser);
	}

	@Override
	public void flushRedisUserRule(MonitorUser monitorUser){
		MonitorUserRuleQuery userRuleQuery = new MonitorUserRuleQuery();
		userRuleQuery.setUserId(monitorUser.getUserId());
		List<MonitorUserRule> userRuleList = monitorUserRuleDao.find(userRuleQuery);

		//3更新redis缓存
		for (MonitorUserRule ruleInfo : userRuleList) {
			monitorPolicy.saveWarnRule(ruleInfo.getSysCode(),ruleInfo.getModuleCode(),
					ruleInfo.getInfoCode(),"0",1);
		}
	}

}

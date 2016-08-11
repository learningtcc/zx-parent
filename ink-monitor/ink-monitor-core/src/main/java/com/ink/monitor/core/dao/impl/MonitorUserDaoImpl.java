/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.monitor.core.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.ink.monitor.core.po.MonitorUser;
import org.springframework.stereotype.Repository;

import com.ink.base.dao.BaseIbatisDao;
import com.ink.monitor.core.dao.IMonitorUserDao;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Repository("monitorUserDao")
public class MonitorUserDaoImpl extends BaseIbatisDao<MonitorUser,java.lang.Integer> implements IMonitorUserDao{
	
	@Override
	public String getIbatisSqlMapNamespace() {
		return "MonitorUser";
	}
	
	@Override
	protected void prepareObjectForSave(MonitorUser entity) {
//		if(entity.getUserId()) == null) {
//			entity.setUserId(IdGenerator.genUUIDStr().longValue());
//		}
	}
	
	public List<HashMap<String, String>> queryUserByPolicy(String sysCode, String moduleCode){
		HashMap<String, String> paramterMap = new HashMap<String, String>();
		paramterMap.put("sysCode", sysCode);
		paramterMap.put("moduleCode", moduleCode);
		
		return getSqlSessionSlave().selectList("MonitorUser.queryUserByPolicy",paramterMap);
	}

	@Override
	public int updatePassword(MonitorUser monitorUser) {
		return getSqlSessionSlave().update("MonitorUser.updatePassword", monitorUser);
	}

}

/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.monitor.core.dao;

import java.util.HashMap;
import java.util.List;

import com.ink.monitor.core.po.MonitorUser;
import com.ink.base.EntityDao;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 

public interface IMonitorUserDao extends EntityDao<MonitorUser, java.lang.Integer>{
	/**
	 * 查询监控功能的用户信息
	 * @param sysCode
	 * @param moduleCode
	 * @param infoCode
	 * @return
	 */
	public List<HashMap<String, String>> queryUserByPolicy(String sysCode, String moduleCode);

	int updatePassword(MonitorUser monitorUser);
}
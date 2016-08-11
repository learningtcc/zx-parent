/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.user.ext.core.dao.impl;

import java.util.HashMap;
import java.util.Map;

import com.ink.user.ext.core.po.UserMessageLog;
import org.springframework.stereotype.Repository;

import com.ink.base.dao.BaseIbatisDao;
import com.ink.user.ext.core.dao.IUserMessageLogDao;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Repository("userMessageLogDao")
public class UserMessageLogDaoImpl extends BaseIbatisDao<UserMessageLog,java.lang.Long> implements IUserMessageLogDao{
	
	@Override
	public String getIbatisSqlMapNamespace() {
		return "UserMessageLog";
	}
	
	@Override
	protected void prepareObjectForSave(UserMessageLog entity) {
//		if(entity.getId()) == null) {
//			entity.setId(IdGenerator.genUUIDStr().longValue());
//		}
	}

	@Override
	public int updateStatusByFileIdAndPhone(String fileId, String phone,
			int status) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("fileId", fileId);
		map.put("phone", phone);
		map.put("status", status);
		return getSqlSession().update(getIbatisSqlMapNamespace()+".updateStatusByFileIdAndPhone",map);
	}

}

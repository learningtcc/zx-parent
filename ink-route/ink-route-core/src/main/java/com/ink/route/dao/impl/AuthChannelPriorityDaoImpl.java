/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.route.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ink.base.dao.BaseIbatisDao;
import com.ink.route.api.model.po.AuthChannelPriority;
import com.ink.route.dao.IAuthChannelPriorityDao;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Repository("authChannelPriorityDao")
public class AuthChannelPriorityDaoImpl extends BaseIbatisDao<AuthChannelPriority,java.lang.Long> implements IAuthChannelPriorityDao{
	
	@Override
	public String getIbatisSqlMapNamespace() {
		return "AuthChannelPriority";
	}
	
	@Override
	protected void prepareObjectForSave(AuthChannelPriority entity) {
//		if(entity.getId()) == null) {
//			entity.setId(IdGenerator.genUUIDStr().longValue());
//		}
	}

	@Override
	public List<AuthChannelPriority> getAll() {
		return getSqlSessionSlave().selectList(getIbatisSqlMapNamespace().concat(".getAll"));
	}
	@Override
	public int updateNotNull(AuthChannelPriority authChannelPriority){
		return getSqlSession().update("AuthChannelPriority.updateNotNull",authChannelPriority);
	}
}

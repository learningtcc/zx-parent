/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.user.ext.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ink.base.BaseManager;
import com.ink.base.EntityDao;
import com.ink.user.ext.core.po.UserMessageLog;
import com.ink.user.ext.core.service.IUserMessageLogManager;
import com.ink.user.ext.core.dao.IUserMessageLogDao;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Service("userMessageLogManager")
@Transactional
public class UserMessageLogManagerImpl extends BaseManager<UserMessageLog,java.lang.Long> implements IUserMessageLogManager{

	@Autowired
	private IUserMessageLogDao userMessageLogDao;

	public EntityDao<UserMessageLog, java.lang.Long> getEntityDao() {
		return this.userMessageLogDao;
	}
	
}

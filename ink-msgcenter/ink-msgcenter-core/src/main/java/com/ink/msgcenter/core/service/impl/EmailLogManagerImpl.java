/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.msgcenter.core.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ink.base.BaseManager;
import com.ink.base.EntityDao;
import com.ink.msgcenter.core.po.EmailLog;
import com.ink.msgcenter.core.service.IEmailLogManager;
import com.ink.msgcenter.core.dao.IEmailLogDao;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Service("emailLogManager")
@Transactional
public class EmailLogManagerImpl extends BaseManager<EmailLog,java.lang.Long> implements IEmailLogManager{

	@Autowired
	private IEmailLogDao emailLogDao;

	public EntityDao<EmailLog, java.lang.Long> getEntityDao() {
		return this.emailLogDao;
	}

	@Override
	public EmailLog getEmailLogById(Long id, String merctId) {
		return emailLogDao.getEmailLogById(id,merctId);
	}

	@Override
	public Map<String, String> getEmailCount(String merctId) {
		return emailLogDao.getEmailCount(merctId);
	}
	
}

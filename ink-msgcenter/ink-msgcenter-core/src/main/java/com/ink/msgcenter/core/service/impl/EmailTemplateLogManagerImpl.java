/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.msgcenter.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ink.base.BaseManager;
import com.ink.base.EntityDao;
import com.ink.msgcenter.core.po.EmailTemplateLog;
import com.ink.msgcenter.core.service.IEmailTemplateLogManager;
import com.ink.msgcenter.core.dao.IEmailTemplateLogDao;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Service("emailTemplateLogManager")
@Transactional
public class EmailTemplateLogManagerImpl extends BaseManager<EmailTemplateLog,java.lang.Long> implements IEmailTemplateLogManager{

	@Autowired
	private IEmailTemplateLogDao emailTemplateLogDao;

	public EntityDao<EmailTemplateLog, java.lang.Long> getEntityDao() {
		return this.emailTemplateLogDao;
	}

	@Override
	public EmailTemplateLog getLastUpdateContent(Long id) {
		return emailTemplateLogDao.getLastUpdateContent(id);
	}

	@Override
	public EmailTemplateLog getPreviousContent(EmailTemplateLog log) {
		return emailTemplateLogDao.getPreviousContent(log);
	}
	
}

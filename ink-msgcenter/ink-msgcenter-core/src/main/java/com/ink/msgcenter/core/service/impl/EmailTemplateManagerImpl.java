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
import com.ink.msgcenter.core.po.EmailTemplate;
import com.ink.msgcenter.core.service.IEmailTemplateManager;
import com.ink.msgcenter.core.dao.IEmailTemplateDao;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Service("emailTemplateManager")
@Transactional
public class EmailTemplateManagerImpl extends BaseManager<EmailTemplate,java.lang.Long> implements IEmailTemplateManager{

	@Autowired
	private IEmailTemplateDao emailTemplateDao;

	public EntityDao<EmailTemplate, java.lang.Long> getEntityDao() {
		return this.emailTemplateDao;
	}
	
}

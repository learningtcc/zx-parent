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
import com.ink.msgcenter.core.po.SmsTemplate;
import com.ink.msgcenter.core.service.ISmsTemplateManager;
import com.ink.msgcenter.core.dao.ISmsTemplateDao;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Service("smsTemplateManager")
@Transactional
public class SmsTemplateManagerImpl extends BaseManager<SmsTemplate,java.lang.Long> implements ISmsTemplateManager{

	@Autowired
	private ISmsTemplateDao smsTemplateDao;

	public EntityDao<SmsTemplate, java.lang.Long> getEntityDao() {
		return this.smsTemplateDao;
	}
	
}

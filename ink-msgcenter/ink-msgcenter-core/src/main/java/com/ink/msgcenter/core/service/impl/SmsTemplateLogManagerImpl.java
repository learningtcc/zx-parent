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
import com.ink.msgcenter.core.po.SmsTemplateLog;
import com.ink.msgcenter.core.service.ISmsTemplateLogManager;
import com.ink.msgcenter.core.dao.ISmsTemplateLogDao;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Service("smsTemplateLogManager")
@Transactional
public class SmsTemplateLogManagerImpl extends BaseManager<SmsTemplateLog,java.lang.Long> implements ISmsTemplateLogManager{

	@Autowired
	private ISmsTemplateLogDao smsTemplateLogDao;

	public EntityDao<SmsTemplateLog, java.lang.Long> getEntityDao() {
		return this.smsTemplateLogDao;
	}

	@Override
	public SmsTemplateLog getLastUpdateContent(long tempId) {
		return smsTemplateLogDao.getLastUpdateContent(tempId);
	}

	@Override
	public SmsTemplateLog getPreviousContent(SmsTemplateLog log) {
		return smsTemplateLogDao.getPreviousContent(log);
	}
	
}

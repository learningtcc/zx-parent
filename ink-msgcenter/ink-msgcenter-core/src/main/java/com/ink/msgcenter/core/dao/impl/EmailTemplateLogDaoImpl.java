/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.msgcenter.core.dao.impl;

import org.springframework.stereotype.Repository;

import com.ink.base.dao.BaseIbatisDao;
import com.ink.msgcenter.core.dao.IEmailTemplateLogDao;
import com.ink.msgcenter.core.po.EmailTemplateLog;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Repository("emailTemplateLogDao")
public class EmailTemplateLogDaoImpl extends BaseIbatisDao<EmailTemplateLog,java.lang.Long> implements IEmailTemplateLogDao{
	
	@Override
	public String getIbatisSqlMapNamespace() {
		return "EmailTemplateLog";
	}
	
	@Override
	protected void prepareObjectForSave(EmailTemplateLog entity) {
//		if(entity.getId()) == null) {
//			entity.setId(IdGenerator.genUUIDStr().longValue());
//		}
	}

	@Override
	public EmailTemplateLog getLastUpdateContent(Long id) {
		return getSqlSession().selectOne(getIbatisSqlMapNamespace()+".getLastUpdateContent",id);
	}

	@Override
	public EmailTemplateLog getPreviousContent(EmailTemplateLog log) {
		return getSqlSession().selectOne(getIbatisSqlMapNamespace()+".getPreviousContent",log);
	}

}

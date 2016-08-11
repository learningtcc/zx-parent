/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.msgcenter.core.dao.impl;

import org.springframework.stereotype.Repository;

import com.ink.base.dao.BaseIbatisDao;
import com.ink.msgcenter.core.dao.ISmsTemplateLogDao;
import com.ink.msgcenter.core.po.SmsTemplateLog;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Repository("smsTemplateLogDao")
public class SmsTemplateLogDaoImpl extends BaseIbatisDao<SmsTemplateLog,java.lang.Long> implements ISmsTemplateLogDao{
	
	@Override
	public String getIbatisSqlMapNamespace() {
		return "SmsTemplateLog";
	}
	
	@Override
	protected void prepareObjectForSave(SmsTemplateLog entity) {
//		if(entity.getId()) == null) {
//			entity.setId(IdGenerator.genUUIDStr().longValue());
//		}
	}

	@Override
	public SmsTemplateLog getLastUpdateContent(long tempId) {
		return getSqlSession().selectOne(getIbatisSqlMapNamespace()+".getLastUpdateContent",tempId);
	}

	@Override
	public SmsTemplateLog getPreviousContent(SmsTemplateLog log) {
		return getSqlSession().selectOne(getIbatisSqlMapNamespace()+".getPreviousContent",log);
	}

}

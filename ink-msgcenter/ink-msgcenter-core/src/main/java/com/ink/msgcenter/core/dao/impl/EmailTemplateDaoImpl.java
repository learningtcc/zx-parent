/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.msgcenter.core.dao.impl;

import org.springframework.stereotype.Repository;

import com.ink.base.dao.BaseIbatisDao;
import com.ink.msgcenter.core.dao.IEmailTemplateDao;
import com.ink.msgcenter.core.po.EmailTemplate;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Repository("emailTemplateDao")
public class EmailTemplateDaoImpl extends BaseIbatisDao<EmailTemplate,java.lang.Long> implements IEmailTemplateDao{
	
	@Override
	public String getIbatisSqlMapNamespace() {
		return "EmailTemplate";
	}
	
	@Override
	protected void prepareObjectForSave(EmailTemplate entity) {
//		if(entity.getId()) == null) {
//			entity.setId(IdGenerator.genUUIDStr().longValue());
//		}
	}

}

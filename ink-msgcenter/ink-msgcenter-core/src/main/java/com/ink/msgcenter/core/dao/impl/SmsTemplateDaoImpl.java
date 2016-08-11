/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.msgcenter.core.dao.impl;

import org.springframework.stereotype.Repository;

import com.ink.base.dao.BaseIbatisDao;
import com.ink.msgcenter.core.dao.ISmsTemplateDao;
import com.ink.msgcenter.core.po.SmsTemplate;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Repository("smsTemplateDao")
public class SmsTemplateDaoImpl extends BaseIbatisDao<SmsTemplate,java.lang.Long> implements ISmsTemplateDao{
	
	@Override
	public String getIbatisSqlMapNamespace() {
		return "SmsTemplate";
	}
	
	@Override
	protected void prepareObjectForSave(SmsTemplate entity) {
//		if(entity.getId()) == null) {
//			entity.setId(IdGenerator.genUUIDStr().longValue());
//		}
	}

}

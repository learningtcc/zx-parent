/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.msgcenter.core.dao.impl;

import org.springframework.stereotype.Repository;

import com.ink.base.dao.BaseIbatisDao;
import com.ink.msgcenter.core.dao.ISmsReportDao;
import com.ink.msgcenter.core.po.SmsReport;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Repository("smsReportDao")
public class SmsReportDaoImpl extends BaseIbatisDao<SmsReport,String> implements ISmsReportDao{
	
	@Override
	public String getIbatisSqlMapNamespace() {
		return "SmsReport";
	}
	
	@Override
	protected void prepareObjectForSave(SmsReport entity) {
//		if(entity.getTaskId()) == null) {
//			entity.setTaskId(IdGenerator.genUUIDStr().longValue());
//		}
	}

	@Override
	public int deleteInfo(SmsReport smsReport) {
		return getSqlSession().update(getIbatisSqlMapNamespace()+".deleteInfo",smsReport);
	}
}

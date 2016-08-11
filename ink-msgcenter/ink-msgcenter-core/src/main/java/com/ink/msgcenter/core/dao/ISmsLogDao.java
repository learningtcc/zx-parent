/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.msgcenter.core.dao;

import java.util.Map;

import com.ink.base.EntityDao;
import com.ink.msgcenter.core.po.SmsLog;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 

public interface ISmsLogDao extends EntityDao<SmsLog, java.lang.Long>{

	public SmsLog getSmsLogById(Long id, String merctCode);

	public Map<String, String> getSmsCount(String merctCode);

	int updateSmsReportInfo(SmsLog smsLog);
}
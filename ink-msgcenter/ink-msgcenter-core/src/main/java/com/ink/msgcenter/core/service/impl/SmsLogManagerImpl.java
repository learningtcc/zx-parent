/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.msgcenter.core.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ink.base.BaseManager;
import com.ink.base.EntityDao;
import com.ink.msgcenter.core.po.SmsLog;
import com.ink.msgcenter.core.service.ISmsLogManager;
import com.ink.msgcenter.core.dao.ISmsLogDao;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Service("smsLogManager")
@Transactional
public class SmsLogManagerImpl extends BaseManager<SmsLog,java.lang.Long> implements ISmsLogManager{

	@Autowired
	private ISmsLogDao smsLogDao;

	public EntityDao<SmsLog, java.lang.Long> getEntityDao() {
		return this.smsLogDao;
	}

	@Override
	public SmsLog getSmsLogById(Long id, String merctCode) {
		return smsLogDao.getSmsLogById(id, merctCode);
	}

	@Override
	public Map<String, String> getSmsCount(String merctCode) {
		return smsLogDao.getSmsCount(merctCode);
	}

	/**
	 * 更新短信日志报告信息
	 *
	 * @param smsLog
	 */
	@Override
	public int updateSmsReportInfo(SmsLog smsLog) {
		return smsLogDao.updateSmsReportInfo(smsLog);
	}

}

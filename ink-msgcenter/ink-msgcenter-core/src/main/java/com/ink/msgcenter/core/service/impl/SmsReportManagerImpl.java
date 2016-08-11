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
import com.ink.msgcenter.core.po.SmsReport;
import com.ink.msgcenter.core.service.ISmsReportManager;
import com.ink.msgcenter.core.dao.ISmsReportDao;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Service("smsReportManager")
@Transactional
public class SmsReportManagerImpl extends BaseManager<SmsReport,String> implements ISmsReportManager{

	@Autowired
	private ISmsReportDao smsReportDao;

	public EntityDao<SmsReport, String> getEntityDao() {
		return this.smsReportDao;
	}

	/**
	 * 删除状态报告信息
	 *
	 * @param smsReport
	 */
	@Override
	public int deleteInfo(SmsReport smsReport) {
		return smsReportDao.deleteInfo(smsReport);
	}
}

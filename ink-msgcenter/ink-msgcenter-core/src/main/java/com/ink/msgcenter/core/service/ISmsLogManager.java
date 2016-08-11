/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.msgcenter.core.service;

import com.ink.msgcenter.core.po.SmsLog;

import java.util.Map;

import com.ink.base.IBaseManager;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 public interface ISmsLogManager extends IBaseManager<SmsLog, java.lang.Long>{

	public SmsLog getSmsLogById(Long id, String merctCode);
	public Map<String, String> getSmsCount(String merctCode);

	/**
	 * 更新短信日志报告信息
	 * @param smsLog
	 */
	int updateSmsReportInfo(SmsLog smsLog);
}

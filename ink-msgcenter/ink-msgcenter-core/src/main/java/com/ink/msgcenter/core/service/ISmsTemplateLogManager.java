/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.msgcenter.core.service;

import com.ink.msgcenter.core.po.SmsTemplateLog;
import com.ink.base.IBaseManager;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 public interface ISmsTemplateLogManager extends IBaseManager<SmsTemplateLog, java.lang.Long>{

	public SmsTemplateLog getLastUpdateContent(long tempId);

	public SmsTemplateLog getPreviousContent(SmsTemplateLog log);
	
}

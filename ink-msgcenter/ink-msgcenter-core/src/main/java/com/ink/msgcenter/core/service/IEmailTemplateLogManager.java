/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.msgcenter.core.service;

import com.ink.msgcenter.core.po.EmailTemplateLog;
import com.ink.base.IBaseManager;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 public interface IEmailTemplateLogManager extends IBaseManager<EmailTemplateLog, java.lang.Long>{

	EmailTemplateLog getLastUpdateContent(Long id);

	EmailTemplateLog getPreviousContent(EmailTemplateLog log);
	
}

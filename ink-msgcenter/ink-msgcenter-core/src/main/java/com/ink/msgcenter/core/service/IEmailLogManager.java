/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.msgcenter.core.service;

import java.util.Map;

import com.ink.base.IBaseManager;
import com.ink.msgcenter.core.po.EmailLog;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 public interface IEmailLogManager extends IBaseManager<EmailLog, java.lang.Long>{
	 
	public EmailLog getEmailLogById(Long id, String merctCode);
	
	public Map<String, String> getEmailCount(String merctCode);
	
}
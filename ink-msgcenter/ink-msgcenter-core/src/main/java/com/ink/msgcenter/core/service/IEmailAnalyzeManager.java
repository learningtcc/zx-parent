/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.msgcenter.core.service;

import com.ink.msgcenter.core.po.EmailAnalyze;
import java.util.Map;
import java.util.HashMap;
import com.ink.base.IBaseManager;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 public interface IEmailAnalyzeManager extends IBaseManager<EmailAnalyze, Long>{

	public EmailAnalyze getByChnId(String chnId);

	public Map<String, Object> getTotalByChnId(String chnId);
	
	/**
	 * 更新统计数据
	 */
	public int updateEmailStatistics(HashMap<String, Object> paramMap);
	
}

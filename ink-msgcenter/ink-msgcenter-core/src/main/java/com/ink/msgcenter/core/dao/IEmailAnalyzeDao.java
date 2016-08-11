/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.msgcenter.core.dao;

import java.util.Map;
import java.util.HashMap;
import com.ink.base.EntityDao;
import com.ink.msgcenter.core.po.EmailAnalyze;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 

public interface IEmailAnalyzeDao extends EntityDao<EmailAnalyze, Long>{

	public EmailAnalyze getByChnId(String chnId);
	
	/**
	 * 更新统计数据
	 */
	public int updateEmailStatistics(HashMap<String, Object> paramMap);

	public Map<String, Object> getTotalByChnId(String chnId);

}
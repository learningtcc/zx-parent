/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.msgcenter.core.dao;

import java.util.Map;

import com.ink.base.EntityDao;
import com.ink.msgcenter.core.po.SmsAnalyze;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 

public interface ISmsAnalyzeDao extends EntityDao<SmsAnalyze, Long>{

	public SmsAnalyze getByChnId(String chnId);

	int updateSmsStatistics(SmsAnalyze analyze);

	public Map<String, Object> getTotalByChnId(String chnId);
}
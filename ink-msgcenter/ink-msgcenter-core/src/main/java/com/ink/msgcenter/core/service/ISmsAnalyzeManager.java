/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.msgcenter.core.service;

import java.util.Map;

import com.ink.base.IBaseManager;
import com.ink.msgcenter.core.po.SmsAnalyze;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 public interface ISmsAnalyzeManager extends IBaseManager<SmsAnalyze, Long>{
	
	 public SmsAnalyze getByChnId(String chnId);

    /**
     * 更新统计信息
     * @param analyze
     * @return
     */
    int updateSmsStatistics(SmsAnalyze analyze);

	public Map<String, Object> getTotalByChnId(String chnId);
}

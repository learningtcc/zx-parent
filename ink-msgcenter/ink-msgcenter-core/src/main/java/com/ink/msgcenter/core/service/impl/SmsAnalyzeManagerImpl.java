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
import com.ink.msgcenter.core.dao.ISmsAnalyzeDao;
import com.ink.msgcenter.core.po.SmsAnalyze;
import com.ink.msgcenter.core.service.ISmsAnalyzeManager;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Service("smsAnalyzeManager")
@Transactional
public class SmsAnalyzeManagerImpl extends BaseManager<SmsAnalyze,Long> implements ISmsAnalyzeManager{

	@Autowired
	private ISmsAnalyzeDao smsAnalyzeDao;

	public EntityDao<SmsAnalyze, Long> getEntityDao() {
		return this.smsAnalyzeDao;
	}

	@Override
	public SmsAnalyze getByChnId(String chnId) {
		return smsAnalyzeDao.getByChnId(chnId);
	}

	/**
	 * 更新统计信息
	 *
	 * @param analyze
	 * @return
	 */
	@Override
	public int updateSmsStatistics(SmsAnalyze analyze) {
		return smsAnalyzeDao.updateSmsStatistics(analyze);
	}

	@Override
	public Map<String, Object> getTotalByChnId(String chnId) {
		return smsAnalyzeDao.getTotalByChnId(chnId);
	}

}

/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.msgcenter.core.service.impl;

import java.util.Map;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ink.base.BaseManager;
import com.ink.base.EntityDao;
import com.ink.msgcenter.core.po.EmailAnalyze;
import com.ink.msgcenter.core.service.IEmailAnalyzeManager;
import com.ink.msgcenter.core.dao.IEmailAnalyzeDao;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Service("emailAnalyzeManager")
@Transactional
public class EmailAnalyzeManagerImpl extends BaseManager<EmailAnalyze,Long> implements IEmailAnalyzeManager{

	@Autowired
	private IEmailAnalyzeDao emailAnalyzeDao;

	public EntityDao<EmailAnalyze, Long> getEntityDao() {
		return this.emailAnalyzeDao;
	}

	@Override
	public EmailAnalyze getByChnId(String chnId) {
		return emailAnalyzeDao.getByChnId(chnId);
	}

	@Override
	public Map<String, Object> getTotalByChnId(String chnId) {
		return emailAnalyzeDao.getTotalByChnId(chnId);
	}
	
	/**
	 * 更新统计数据
	 */
	public int updateEmailStatistics(HashMap<String, Object> paramMap){
		return emailAnalyzeDao.updateEmailStatistics(paramMap);
	}
	
}

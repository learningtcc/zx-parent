/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.msgcenter.core.dao.impl;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;

import com.ink.base.dao.BaseIbatisDao;
import com.ink.msgcenter.core.dao.IEmailAnalyzeDao;
import com.ink.msgcenter.core.po.EmailAnalyze;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Repository("emailAnalyzeDao")
public class EmailAnalyzeDaoImpl extends BaseIbatisDao<EmailAnalyze,Long> implements IEmailAnalyzeDao{
	
	@Override
	public String getIbatisSqlMapNamespace() {
		return "EmailAnalyze";
	}
	
	@Override
	protected void prepareObjectForSave(EmailAnalyze entity) {
//		if(entity.getId()) == null) {
//			entity.setId(IdGenerator.genUUIDStr().longValue());
//		}
	}

	@Override
	public EmailAnalyze getByChnId(String chnId) {
		return getSqlSession().selectOne(getIbatisSqlMapNamespace()+".getByChnId",chnId);
	}

	@Override
	public Map<String, Object> getTotalByChnId(String chnId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("chnId", chnId);
		return getSqlSession().selectMap(getIbatisSqlMapNamespace()+".getTotalByChnId",map,"key");
	}

	
	/**
	 * 更新统计数据
	 */
	public int updateEmailStatistics(HashMap<String, Object> paramMap) {
		return getSqlSession().update("EmailAnalyze.updateEmailStatics", paramMap);
		
	}

}

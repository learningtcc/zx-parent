/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.msgcenter.core.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ink.base.dao.BaseIbatisDao;
import com.ink.msgcenter.core.dao.ISmsAnalyzeDao;
import com.ink.msgcenter.core.po.SmsAnalyze;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Repository("smsAnalyzeDao")
public class SmsAnalyzeDaoImpl extends BaseIbatisDao<SmsAnalyze,Long> implements ISmsAnalyzeDao{
	
	@Override
	public String getIbatisSqlMapNamespace() {
		return "SmsAnalyze";
	}
	
	@Override
	protected void prepareObjectForSave(SmsAnalyze entity) {
//		if(entity.getId()) == null) {
//			entity.setId(IdGenerator.genUUIDStr().longValue());
//		}
	}

	@Override
	public SmsAnalyze getByChnId(String chnId) {
		return getSqlSession().selectOne(getIbatisSqlMapNamespace()+".getByChnId",chnId);
	}

	@Override
	public int updateSmsStatistics(SmsAnalyze analyze) {
		return getSqlSession().update(getIbatisSqlMapNamespace()+".updateSmsStatistics",analyze);
	}

	@Override
	public Map<String, Object> getTotalByChnId(String chnId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("chnId", chnId);
		return getSqlSession().selectMap(getIbatisSqlMapNamespace()+".getTotalByChnId",map,"key");
	}

}

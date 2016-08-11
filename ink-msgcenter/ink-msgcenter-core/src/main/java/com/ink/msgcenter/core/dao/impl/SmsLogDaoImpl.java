/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.msgcenter.core.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ink.base.dao.BaseIbatisDao;
import com.ink.msgcenter.core.dao.ISmsLogDao;
import com.ink.msgcenter.core.po.SmsLog;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Repository("smsLogDao")
public class SmsLogDaoImpl extends BaseIbatisDao<SmsLog,java.lang.Long> implements ISmsLogDao{
	
	@Override
	public String getIbatisSqlMapNamespace() {
		return "SmsLog";
	}
	
	@Override
	protected void prepareObjectForSave(SmsLog entity) {
//		if(entity.getId()) == null) {
//			entity.setId(IdGenerator.genUUIDStr().longValue());
//		}
	}

	@Override
	public SmsLog getSmsLogById(Long id, String merctCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("merctCode", merctCode);
		return getSqlSession().selectOne(getIbatisSqlMapNamespace()+".getSmsLogById",map);
	}

	@Override
	public Map<String, String> getSmsCount(String merctCode) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("merctCode", merctCode);
		return getSqlSession().selectMap(getIbatisSqlMapNamespace()+".getSmsCount", map, "key");
	}

	@Override
	public int updateSmsReportInfo(SmsLog smsLog) {
		return getSqlSession().update(getIbatisSqlMapNamespace()+".updateSmsReportInfo",smsLog);
	}

}

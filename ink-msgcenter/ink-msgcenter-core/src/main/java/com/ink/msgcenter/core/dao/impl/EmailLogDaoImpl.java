/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.msgcenter.core.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ink.base.dao.BaseIbatisDao;
import com.ink.msgcenter.core.dao.IEmailLogDao;
import com.ink.msgcenter.core.po.EmailLog;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Repository("emailLogDao")
public class EmailLogDaoImpl extends BaseIbatisDao<EmailLog,java.lang.Long> implements IEmailLogDao{
	
	@Override
	public String getIbatisSqlMapNamespace() {
		return "EmailLog";
	}
	
	@Override
	protected void prepareObjectForSave(EmailLog entity) {
//		if(entity.getId()) == null) {
//			entity.setId(IdGenerator.genUUIDStr().longValue());
//		}
	}

	@Override
	public EmailLog getEmailLogById(Long id, String merctCode) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("merctCode", merctCode);
		return getSqlSession().selectOne(getIbatisSqlMapNamespace()+".getEmailLogById", map);
	}

	@Override
	public Map<String, String> getEmailCount(String merctCode) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("merctCode", merctCode);
		return getSqlSession().selectMap(getIbatisSqlMapNamespace()+".getEmailCount", map, "key");
	}

	@Override
	public int saveBatch(List<EmailLog> list) throws DataAccessException {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("merctCode", list.get(0).getMerctCode());
		paramMap.put("logList", list);
		int sct=  getSqlSession().insert(getInsertBatchStatement(),  paramMap);
	    return sct;
	}
	
	

}

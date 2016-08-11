/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.user.core.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ink.base.dao.BaseIbatisDao;
import com.ink.user.core.dao.IAccLimitDao;
import com.ink.user.core.po.AccLimit;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Repository("accLimitDao")
public class AccLimitDaoImpl extends BaseIbatisDao<AccLimit,java.lang.Long> implements IAccLimitDao{
	
	@Override
	public String getIbatisSqlMapNamespace() {
		return "AccLimit";
	}
	
	@Override
	protected void prepareObjectForSave(AccLimit entity) {
//		if(entity.getId()) == null) {
//			entity.setId(IdGenerator.genUUIDStr().longValue());
//		}
	}
	
	public AccLimit selectByIdForUpdate(Long id){
		return getSqlSession().selectOne(getIbatisSqlMapNamespace()+".selectByIdForUpdate",id);
	}
	
	public AccLimit selectBySacIdAndTradeDateForUpdate(String sacId, String tradeDate){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sacId", sacId);
		map.put("tradeDate", tradeDate);
		return getSqlSession().selectOne(getIbatisSqlMapNamespace()+".selectBySacIdAndTradeDateForUpdate",map);
	}
	
	public AccLimit selectByPacIdAndTradeDateForUpdate(String pacId, String tradeDate){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pacId", pacId);
		map.put("tradeDate", tradeDate);
		return getSqlSession().selectOne(getIbatisSqlMapNamespace()+".selectByPacIdAndTradeDateForUpdate",map);
	}

}

/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.user.core.dao.impl;

import java.util.HashMap;
import java.util.Map;

import com.ink.user.core.dao.IAccTypeMchLimitDao;
import com.ink.user.core.po.AccTypeMchLimit;
import org.springframework.stereotype.Repository;

import com.ink.base.dao.BaseIbatisDao;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Repository("accTypeMchLimitDao")
public class AccTypeMchLimitDaoImpl extends BaseIbatisDao<AccTypeMchLimit,java.lang.Long> implements IAccTypeMchLimitDao {
	
	@Override
	public String getIbatisSqlMapNamespace() {
		return "AccTypeMchLimit";
	}
	
	@Override
	protected void prepareObjectForSave(AccTypeMchLimit entity) {
//		if(entity.getId()) == null) {
//			entity.setId(IdGenerator.genUUIDStr().longValue());
//		}
	}
	@Override
	public AccTypeMchLimit getByMchIdAndSacType(Long mchId, String sacType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mchId", mchId);
		map.put("sacType", sacType);
		return getSqlSessionSlave().selectOne(
				"AccTypeMchLimit.getByMchIdAndSacType", map);
	}

}

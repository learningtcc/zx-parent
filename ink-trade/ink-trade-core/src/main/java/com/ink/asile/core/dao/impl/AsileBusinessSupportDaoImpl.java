/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.asile.core.dao.impl;

import org.springframework.stereotype.Repository;

import com.ink.asile.core.dao.IAsileBusinessSupportDao;
import com.ink.asile.core.po.AsileBusinessSupport;
import com.ink.base.dao.BaseIbatisDao;

import java.util.List;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Repository("asileBusinessSupportDao")
public class AsileBusinessSupportDaoImpl extends BaseIbatisDao<AsileBusinessSupport,java.lang.Long> implements IAsileBusinessSupportDao{
	
	@Override
	public String getIbatisSqlMapNamespace() {
		return "AsileBusinessSupport";
	}
	
	@Override
	protected void prepareObjectForSave(AsileBusinessSupport entity) {
//		if(entity.getId()) == null) {
//			entity.setId(IdGenerator.genUUIDStr().longValue());
//		}
	}

	@Override
	public List<AsileBusinessSupport> getByMerNo(String merNo) {
		return getSqlSessionSlave().selectList(getIbatisSqlMapNamespace().concat(".getByMerNo"),merNo);
	}
	@Override
	public int updateNotNull(AsileBusinessSupport asileBusinessSupport){
		return getSqlSession().update("AsileBusinessSupport.updateNotNull",asileBusinessSupport);
	}
}

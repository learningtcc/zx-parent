/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.monitor.core.dao.impl;

import org.springframework.stereotype.Repository;

import com.ink.base.dao.BaseIbatisDao;
import com.ink.monitor.core.dao.ISystermSourceDao;
import com.ink.monitor.core.po.SystermSource;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Repository("systermSourceDao")
public class SystermSourceDaoImpl extends BaseIbatisDao<SystermSource,Integer> implements ISystermSourceDao{
	
	@Override
	public String getIbatisSqlMapNamespace() {
		return "SystermSource";
	}
	
	@Override
	protected void prepareObjectForSave(SystermSource entity) {
//		if(entity.getId()) == null) {
//			entity.setId(IdGenerator.genUUIDStr().longValue());
//		}
	}

	@Override
	public int updateInfoStatus(SystermSource systermSource) {
		return this.getSqlSessionSlave()
				.update(getIbatisSqlMapNamespace() + ".updateInfoStatus", systermSource);
	}
}

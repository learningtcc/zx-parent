/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.monitor.core.dao.impl;

import com.ink.monitor.core.dao.ISystermModuleDao;
import com.ink.monitor.core.po.SystermModule;
import org.springframework.stereotype.Repository;

import com.ink.base.dao.BaseIbatisDao;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Repository("systermModuleDao")
public class SystermModuleDaoImpl extends BaseIbatisDao<SystermModule,Integer> implements ISystermModuleDao {
	
	@Override
	public String getIbatisSqlMapNamespace() {
		return "SystermModule";
	}
	
	@Override
	protected void prepareObjectForSave(SystermModule entity) {
//		if(entity.getId()) == null) {
//			entity.setId(IdGenerator.genUUIDStr().longValue());
//		}
	}

	@Override
	public int updateStatus(SystermModule systermModule) {
		return this.getSqlSessionSlave()
				.update(getIbatisSqlMapNamespace() + ".updateStatus", systermModule);
	}

	@Override
	public int updateInfoStatus(SystermModule systermModule) {
		return this.getSqlSessionSlave()
				.update(getIbatisSqlMapNamespace() + ".updateInfoStatus", systermModule);
	}
}

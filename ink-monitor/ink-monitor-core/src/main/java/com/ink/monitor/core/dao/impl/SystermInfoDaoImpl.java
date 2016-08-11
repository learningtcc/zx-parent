/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.monitor.core.dao.impl;

import org.springframework.stereotype.Repository;

import com.ink.base.dao.BaseIbatisDao;
import com.ink.monitor.core.dao.ISystermInfoDao;
import com.ink.monitor.core.po.SystermInfo;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Repository("systermInfoDao")
public class SystermInfoDaoImpl extends BaseIbatisDao<SystermInfo,Integer> implements ISystermInfoDao{
	
	@Override
	public String getIbatisSqlMapNamespace() {
		return "SystermInfo";
	}
	
	@Override
	protected void prepareObjectForSave(SystermInfo entity) {
//		if(entity.getId()) == null) {
//			entity.setId(IdGenerator.genUUIDStr().longValue());
//		}
	}

	@Override
	public int updateStatus(SystermInfo systermInfo) {
		return this.getSqlSessionSlave()
				.delete(getIbatisSqlMapNamespace() + ".updateStatus", systermInfo);
	}

	@Override
	public int updateInfoStatus(SystermInfo systermInfo) {

		return this.getSqlSessionSlave()
				.update(getIbatisSqlMapNamespace() + ".updateInfoStatus", systermInfo);
	}
}

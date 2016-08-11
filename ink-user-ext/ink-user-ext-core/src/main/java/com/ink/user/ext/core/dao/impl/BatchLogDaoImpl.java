/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.user.ext.core.dao.impl;

import org.springframework.stereotype.Repository;

import com.ink.base.dao.BaseIbatisDao;
import com.ink.user.ext.core.dao.IBatchLogDao;
import com.ink.user.ext.core.po.BatchLog;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Repository("batchLogDao")
public class BatchLogDaoImpl extends BaseIbatisDao<BatchLog,java.lang.Long> implements IBatchLogDao{
	
	@Override
	public String getIbatisSqlMapNamespace() {
		return "BatchLog";
	}
	
	@Override
	protected void prepareObjectForSave(BatchLog entity) {
//		if(entity.getId()) == null) {
//			entity.setId(IdGenerator.genUUIDStr().longValue());
//		}
	}

}

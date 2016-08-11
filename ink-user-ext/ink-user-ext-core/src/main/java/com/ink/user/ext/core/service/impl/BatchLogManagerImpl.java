/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.user.ext.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ink.base.BaseManager;
import com.ink.base.EntityDao;
import com.ink.user.ext.core.po.BatchLog;
import com.ink.user.ext.core.service.IBatchLogManager;
import com.ink.user.ext.core.dao.IBatchLogDao;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Service("batchLogManager")
@Transactional
public class BatchLogManagerImpl extends BaseManager<BatchLog,java.lang.Long> implements IBatchLogManager{

	@Autowired
	private IBatchLogDao batchLogDao;

	public EntityDao<BatchLog, java.lang.Long> getEntityDao() {
		return this.batchLogDao;
	}
	
}

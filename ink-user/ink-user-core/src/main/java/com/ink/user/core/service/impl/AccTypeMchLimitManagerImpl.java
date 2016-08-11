/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.user.core.service.impl;

import com.ink.user.core.dao.IAccTypeMchLimitDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ink.base.BaseManager;
import com.ink.base.EntityDao;
import com.ink.user.core.po.AccTypeMchLimit;
import com.ink.user.core.service.IAccTypeMchLimitManager;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Service("accTypeMchLimitManager")
@Transactional
public class AccTypeMchLimitManagerImpl extends BaseManager<AccTypeMchLimit,java.lang.Long> implements IAccTypeMchLimitManager{

	@Autowired
	private IAccTypeMchLimitDao accTypeMchLimitDao;

	public EntityDao<AccTypeMchLimit, java.lang.Long> getEntityDao() {
		return this.accTypeMchLimitDao;
	}
	
}

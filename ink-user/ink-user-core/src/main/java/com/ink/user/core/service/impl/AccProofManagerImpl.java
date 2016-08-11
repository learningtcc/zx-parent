/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.user.core.service.impl;

import com.ink.user.core.service.IAccProofManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ink.base.BaseManager;
import com.ink.base.EntityDao;
import com.ink.user.core.po.AccProof;
import com.ink.user.core.dao.IAccProofDao;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Service("accProofManager")
@Transactional
public class AccProofManagerImpl extends BaseManager<AccProof,java.lang.Long> implements IAccProofManager {

	@Autowired
	private IAccProofDao accProofDao;

	public EntityDao<AccProof, java.lang.Long> getEntityDao() {
		return this.accProofDao;
	}
	
}

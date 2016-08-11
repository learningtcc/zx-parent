/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.user.core.service.impl;

import com.ink.user.core.dao.IAccCustDao;
import com.ink.user.core.service.IAccCustManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ink.base.BaseManager;
import com.ink.base.EntityDao;
import com.ink.user.core.po.AccCust;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Service("accCustManager")
@Transactional
public class AccCustManagerImpl extends BaseManager<AccCust,Long> implements IAccCustManager {

	@Autowired
	private IAccCustDao accCustDao;

	public EntityDao<AccCust, Long> getEntityDao() {
		return this.accCustDao;
	}
	
}

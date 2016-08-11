/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.user.core.service.impl;

import com.ink.user.core.dao.IAccSacTypeDao;
import com.ink.user.core.po.AccSacType;
import com.ink.user.core.service.IAccSacTypeManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ink.base.BaseManager;
import com.ink.base.EntityDao;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Service("accSacTypeManager")
@Transactional
public class AccSacTypeManagerImpl extends BaseManager<AccSacType,Long> implements IAccSacTypeManager {

	@Autowired
	private IAccSacTypeDao accSacTypeDao;

	public EntityDao<AccSacType, Long> getEntityDao() {
		return this.accSacTypeDao;
	}
	
//	@Transactional(readOnly=true)
//	public AccSacType getBySacType(String v) {
//		return accSacTypeDao.getBySacType(v);
//	}	
	
}

/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.cert.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ink.base.BaseManager;
import com.ink.base.EntityDao;
import com.ink.cert.core.po.CertLog;
import com.ink.cert.core.service.ICertLogManager;
import com.ink.cert.core.dao.ICertLogDao;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Service("certLogManager")
@Transactional
public class CertLogManagerImpl extends BaseManager<CertLog,Long> implements ICertLogManager{

	@Autowired
	private ICertLogDao certLogDao;

	public EntityDao<CertLog, Long> getEntityDao() {
		return this.certLogDao;
	}
	
}

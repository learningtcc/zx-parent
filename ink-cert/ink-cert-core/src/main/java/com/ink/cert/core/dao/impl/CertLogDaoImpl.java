/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.cert.core.dao.impl;

import org.springframework.stereotype.Repository;

import com.ink.base.dao.BaseIbatisDao;
import com.ink.cert.core.dao.ICertLogDao;
import com.ink.cert.core.po.CertLog;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Repository("certLogDao")
public class CertLogDaoImpl extends BaseIbatisDao<CertLog,Long> implements ICertLogDao{
	
	@Override
	public String getIbatisSqlMapNamespace() {
		return "CertLog";
	}
	
	@Override
	protected void prepareObjectForSave(CertLog entity) {
//		if(entity.getId()) == null) {
//			entity.setId(IdGenerator.genUUIDStr().longValue());
//		}
	}

}

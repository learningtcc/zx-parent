/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.cert.core.dao.impl;

import com.ink.base.dao.BaseIbatisDao;
import com.ink.cert.core.dao.ICertInfoDao;
import com.ink.cert.core.po.CertInfo;
import org.springframework.stereotype.Repository;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Repository("certInfoDao")
public class CertInfoDaoImpl extends BaseIbatisDao<CertInfo,Integer> implements ICertInfoDao{
	
	@Override
	public String getIbatisSqlMapNamespace() {
		return "CertInfo";
	}
	
	@Override
	protected void prepareObjectForSave(CertInfo entity) {
//		if(entity.getId()) == null) {
//			entity.setId(IdGenerator.genUUIDStr().longValue());
//		}
	}

	@Override
	public int updateStatus(CertInfo certInfo) {
		return this.getSqlSession().update(this.getIbatisSqlMapNamespace()+".updateStatus",certInfo);
	}
}

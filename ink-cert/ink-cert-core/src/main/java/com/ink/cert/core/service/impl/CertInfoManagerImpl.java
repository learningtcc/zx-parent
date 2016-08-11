/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.cert.core.service.impl;

import com.ink.cert.core.dao.ICertInfoDao;
import com.ink.cert.core.po.CertInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ink.base.BaseManager;
import com.ink.base.EntityDao;
import com.ink.cert.core.service.ICertInfoManager;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Service("certInfoManager")
@Transactional
public class CertInfoManagerImpl extends BaseManager<CertInfo,Integer> implements ICertInfoManager{

	@Autowired
	private ICertInfoDao certInfoDao;

	public EntityDao<CertInfo, Integer> getEntityDao() {
		return this.certInfoDao;
	}

	/**
	 * 更新状态
	 *
	 * @param certInfo
	 */
	@Override
	public int updateStatus(CertInfo certInfo) {
		return certInfoDao.updateStatus(certInfo);
	}
}

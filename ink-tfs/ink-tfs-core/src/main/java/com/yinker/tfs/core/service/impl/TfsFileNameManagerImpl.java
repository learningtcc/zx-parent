/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.yinker.tfs.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yinker.base.BaseManager;
import com.yinker.base.EntityDao;
import com.yinker.tfs.core.po.TfsFileName;
import com.yinker.tfs.core.service.ITfsFileNameManager;
import com.yinker.tfs.core.dao.ITfsFileNameDao;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Service("tfsFileNameManager")
@Transactional
public class TfsFileNameManagerImpl extends BaseManager<TfsFileName,Long> implements ITfsFileNameManager{

	@Autowired
	private ITfsFileNameDao tfsFileNameDao;

	public EntityDao<TfsFileName, Long> getEntityDao() {
		return this.tfsFileNameDao;
	}

	@Override
	public TfsFileName findByFileName(String fileName, String suffix, String sourceCode, String moduleCode) {
		return tfsFileNameDao.findByFileName(fileName,suffix,sourceCode,moduleCode);
	}
}

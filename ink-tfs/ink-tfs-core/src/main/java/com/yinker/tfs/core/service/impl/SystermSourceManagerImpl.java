/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.yinker.tfs.core.service.impl;

import com.yinker.base.BaseManager;
import com.yinker.base.EntityDao;
import com.yinker.tfs.core.dao.ISystermSourceDao;
import com.yinker.tfs.core.po.SystermSource;
import com.yinker.tfs.core.service.ISystermSourceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Service("systermSourceManager")
@Transactional
public class SystermSourceManagerImpl extends BaseManager<SystermSource,Integer> implements ISystermSourceManager {

	@Autowired
	private ISystermSourceDao systermSourceDao;

	public EntityDao<SystermSource, Integer> getEntityDao() {
		return this.systermSourceDao;
	}

}

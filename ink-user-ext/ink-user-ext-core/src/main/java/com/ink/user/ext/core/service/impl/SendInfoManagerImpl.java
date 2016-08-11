/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.user.ext.core.service.impl;

import java.util.List;

import com.ink.user.ext.core.dao.ISendInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ink.base.BaseManager;
import com.ink.base.EntityDao;
import com.ink.user.ext.core.po.SendInfo;
import com.ink.user.ext.core.service.ISendInfoManager;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Service("sendInfoManager")
@Transactional
public class SendInfoManagerImpl extends BaseManager<SendInfo,java.lang.Long> implements ISendInfoManager{

	@Autowired
	private ISendInfoDao sendInfoDao;

	public EntityDao<SendInfo, java.lang.Long> getEntityDao() {
		return this.sendInfoDao;
	}

	@Override
	public List<String> getFilePathListByDate(String start, String end) {
		return sendInfoDao.getFilePathListByDate(start, end);
	}

	@Override
	public int updateByFilePath(String filePath, Integer status) {
		return sendInfoDao.updateByFilePath(filePath, status);
	}
	
}

/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.user.ext.core.service.impl;

import com.ink.user.ext.core.dao.IUserInfoDao;
import com.ink.user.ext.core.po.UserInfo;
import com.ink.user.ext.core.service.IUserInfoManager;
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

@Service("userInfoManager")
@Transactional
public class UserInfoManagerImpl extends BaseManager<UserInfo, Long> implements IUserInfoManager {

	@Autowired
	private IUserInfoDao userInfoDao;

	public EntityDao<UserInfo, java.lang.Long> getEntityDao() {
		return this.userInfoDao;
	}

}

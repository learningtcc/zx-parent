/**
 * yinker.com Copyright (c) 2016-2025 All Rights Reserved.
 * 
 * @Description TODO
 * @author xuguoqi
 * @date 2016年5月3日 下午3:19:51
 */
package com.ink.trade.core.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ink.base.BaseManager;
import com.ink.base.EntityDao;
import com.ink.trade.core.dao.IAuthDao;
import com.ink.trade.core.manager.IAuthManager;
import com.ink.trade.core.po.Auth;

/**
 * @Description 绑卡关系业务实现
 * @author xuguoqi
 * @date 2016年5月3日 下午3:19:51
 */
@Service(value="authManager")
@Transactional
public class AuthManagerImpl extends BaseManager<Auth, Long> implements IAuthManager {

	@Autowired
	private IAuthDao authDao;
	
	/**
	 * @Description
	 * @author xuguoqi
	 * @date 2016年5月3日 下午3:20:44
	 * @return  
	 */
	@Override
	protected EntityDao<Auth, Long> getEntityDao() {
		return authDao;
	}

	/**
	 * @Description 
	 * @author xuguoqi
	 * @date 2016年5月3日 下午4:34:07
	 * @param auth
	 * @return  
	 */
	@Override
	public Auth getByUserIdAndCardNo(Auth auth) {
		return authDao.getByUserIdAndCardNo(auth);
	}

	@Override
	public int saveOrUpdate(Auth auth) {
		return authDao.saveOrUpdate(auth);
	}
	@Override
	public int updateNotNull(Auth auth){
		return authDao.updateNotNull(auth);
	}
}

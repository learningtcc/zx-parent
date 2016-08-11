package com.ink.trade.core.dao;

import com.ink.base.EntityDao;
import com.ink.trade.core.po.Auth;

public interface IAuthDao extends EntityDao<Auth, Long> {
	/**
	 * 根据用户号和卡号查询用户的绑卡关系
	 * 
	 * @param auth
	 * @return
	 */
	Auth getByUserIdAndCardNo(Auth auth);

	int saveOrUpdate(Auth auth);
	
	public int updateNotNull(Auth auth);
}

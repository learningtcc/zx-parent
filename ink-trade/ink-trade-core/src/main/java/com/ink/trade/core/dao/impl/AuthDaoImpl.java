package com.ink.trade.core.dao.impl;

import org.springframework.stereotype.Repository;

import com.ink.base.dao.BaseIbatisDao;
import com.ink.trade.core.dao.IAuthDao;
import com.ink.trade.core.po.Auth;

@Repository(value = "authDao")
public class AuthDaoImpl extends BaseIbatisDao<Auth, Long> implements IAuthDao {

	/**
	 * @Description TODO
	 * @author xuguoqi
	 * @date 2016年5月3日 下午4:09:58
	 * @return
	 */
	@Override
	public String getIbatisSqlMapNamespace() {
		return "Auth";
	}

	@Override
	public Auth getByUserIdAndCardNo(Auth auth) {
		return this.getSqlSession().selectOne(getIbatisSqlMapNamespace().concat(".getByUserIdAndCardNo"), auth);
	}

	@Override
	public int saveOrUpdate(Auth auth) {
		return this.getSqlSession().insert(getIbatisSqlMapNamespace().concat(".saveOrUpdate"), auth);
	}
	@Override
	public int updateNotNull(Auth auth){
		return getSqlSession().update("Auth.updateNotNull",auth);
	}
}

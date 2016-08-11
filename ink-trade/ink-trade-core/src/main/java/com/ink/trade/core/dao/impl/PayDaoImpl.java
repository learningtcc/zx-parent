package com.ink.trade.core.dao.impl;


import org.springframework.stereotype.Repository;

import com.ink.base.dao.BaseIbatisDao;
import com.ink.trade.core.dao.IPayDao;
import com.ink.trade.core.po.Pay;
@Repository
public class PayDaoImpl extends BaseIbatisDao<Pay, Long> implements IPayDao{
	@Override
	public String getIbatisSqlMapNamespace() {
		return "Pay";
	}

	@Override
	public Pay findPayOrder(Pay pay) {
		return getSqlSession().selectOne(getIbatisSqlMapNamespace().concat(".findPayOrder"),pay);
	}

	@Override
	public int updateStatus(Pay pay) {
		return getSqlSession().update(getIbatisSqlMapNamespace().concat(".updateStatus"),pay);
	}
	@Override
	public int updateNotNull(Pay pay){
		return getSqlSession().update("Pay.updateNotNull",pay);
	}
}

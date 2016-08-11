package com.ink.trade.core.dao.impl;


import org.springframework.stereotype.Repository;

import com.ink.base.dao.BaseIbatisDao;
import com.ink.trade.core.dao.IPayLogDao;
import com.ink.trade.core.po.PayLog;
@Repository
public class PayLogDaoImpl extends BaseIbatisDao<PayLog, Long> implements IPayLogDao{
	@Override
	public String getIbatisSqlMapNamespace() {
		return "PayLog";
	}
	@Override
	public int updateNotNull(PayLog payLog){
		return getSqlSession().update("PayLog.updateNotNull",payLog);
	}
}

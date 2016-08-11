package com.ink.trade.core.dao.impl;

import org.springframework.stereotype.Repository;

import com.ink.base.dao.BaseIbatisDao;
import com.ink.trade.core.dao.ITradeLogDao;
import com.ink.trade.core.po.TradeLog;
@Repository
public class TradeLogImpl extends BaseIbatisDao<TradeLog, Long> implements ITradeLogDao{
	@Override
	public String getIbatisSqlMapNamespace() {
		return "TradeLog";
	}
	@Override
	public int updateNotNull(TradeLog tradeLog){
		return getSqlSession().update("TradeLog.updateNotNull",tradeLog);
	}
}

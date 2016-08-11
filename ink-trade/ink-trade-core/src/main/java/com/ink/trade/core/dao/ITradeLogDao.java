package com.ink.trade.core.dao;

import com.ink.base.EntityDao;
import com.ink.trade.core.po.TradeLog;

public interface ITradeLogDao extends EntityDao<TradeLog, Long> {
	public int updateNotNull(TradeLog tradeLog);
}

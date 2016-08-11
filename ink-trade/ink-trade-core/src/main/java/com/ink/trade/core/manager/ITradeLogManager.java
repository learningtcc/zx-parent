package com.ink.trade.core.manager;

import com.ink.base.IBaseManager;
import com.ink.trade.core.po.TradeLog;


/**
 * Created by huohb on 2016/4/19.
 * 交易订单流水Manager
 */
public interface ITradeLogManager extends IBaseManager<TradeLog, Long> {
	public int updateNotNull(TradeLog tradeLog);
}


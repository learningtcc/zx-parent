package com.ink.trade.core.manager;

import com.ink.base.IBaseManager;
import com.ink.trade.core.po.PayLog;

/**
 * Created by huohb on 2016/4/19.
 * 支付日志Manager
 */
public interface IPayLogManager extends IBaseManager<PayLog,Long>{
	public int updateNotNull(PayLog payLog);
}

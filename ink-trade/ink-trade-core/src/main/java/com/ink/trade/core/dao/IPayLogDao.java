package com.ink.trade.core.dao;


import com.ink.base.EntityDao;
import com.ink.trade.core.po.PayLog;

public interface IPayLogDao extends EntityDao<PayLog, Long> {
	public int updateNotNull(PayLog payLog);
}

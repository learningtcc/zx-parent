package com.ink.trade.core.dao;

import java.util.List;

import com.ink.base.EntityDao;
import com.ink.trade.core.po.FailerLog;

public interface IFailerLogDao extends EntityDao<FailerLog, Long> {

	public List<FailerLog> findFailerLogByTime(int time);
	

	public int updateNotNull(FailerLog failerLog);
}

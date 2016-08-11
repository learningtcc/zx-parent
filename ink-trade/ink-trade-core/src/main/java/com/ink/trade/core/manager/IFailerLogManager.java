package com.ink.trade.core.manager;

import java.util.List;

import com.ink.base.IBaseManager;
import com.ink.trade.core.po.FailerLog;


public interface IFailerLogManager extends IBaseManager<FailerLog, Long> {

	public List<FailerLog> findFailerLogByTime(int time);
	public int updateNotNull(FailerLog failerLog);
}

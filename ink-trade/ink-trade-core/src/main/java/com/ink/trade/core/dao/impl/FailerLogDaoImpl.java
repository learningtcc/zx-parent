package com.ink.trade.core.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ink.base.IdGenerator;
import com.ink.base.dao.BaseIbatisDao;
import com.ink.trade.core.dao.IFailerLogDao;
import com.ink.trade.core.po.FailerLog;

@Repository("failerLogDao")
public class FailerLogDaoImpl extends BaseIbatisDao<FailerLog, Long> implements IFailerLogDao{

	@Override
	public String getIbatisSqlMapNamespace() {
		return "FailerLog";
	}

	@Override
	protected void prepareObjectForSave(FailerLog entity) {
		if (entity.getId() == null) {
			entity.setId(IdGenerator.genUUIDStr().longValue());
		}
	}
	
	@Override
	public List<FailerLog> findFailerLogByTime(int time) {
		return getSqlSessionSlave().selectList("FailerLog.findFailerLogByTime",time);
	}
	@Override
	public int updateNotNull(FailerLog failerLog){
		return getSqlSession().update("FailerLog.updateNotNull",failerLog);
	}
}

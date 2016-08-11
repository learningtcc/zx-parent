package com.ink.trade.core.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ink.base.BaseManager;
import com.ink.base.EntityDao;
import com.ink.trade.core.dao.IFailerLogDao;
import com.ink.trade.core.manager.IFailerLogManager;
import com.ink.trade.core.po.FailerLog;

@Service("failerLogManager")
@Transactional
public class FailerLogManagerImpl extends BaseManager<FailerLog, Long>
		implements IFailerLogManager {

	@Autowired
	private IFailerLogDao failerLogDao;

	@Override
	protected EntityDao<FailerLog, Long> getEntityDao() {
		return failerLogDao;
	}

	@Override
	public List<FailerLog> findFailerLogByTime(int time) {
		return failerLogDao.findFailerLogByTime(time);
	}
	@Override
	public int updateNotNull(FailerLog failerLog){
		return failerLogDao.updateNotNull(failerLog);
	}
}

/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.scheduler.core.dao.impl;

import com.ink.scheduler.core.dao.ITimerTaskJobLogDao;
import com.ink.scheduler.core.po.TimerTaskJobLog;
import org.springframework.stereotype.Repository;

import com.ink.base.dao.BaseIbatisDao;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Repository("timerTaskJobLogDao")
public class TimerTaskJobLogDaoImpl extends BaseIbatisDao<TimerTaskJobLog,Integer> implements ITimerTaskJobLogDao {
	
	@Override
	public String getIbatisSqlMapNamespace() {
		return "TimerTaskJobLog";
	}
	
	@Override
	protected void prepareObjectForSave(TimerTaskJobLog entity) {
//		if(entity.getId()) == null) {
//			entity.setId(IdGenerator.genUUIDStr().longValue());
//		}
	}

}

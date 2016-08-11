/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.scheduler.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ink.base.BaseManager;
import com.ink.base.EntityDao;
import com.ink.scheduler.core.po.TimerTaskJobLog;
import com.ink.scheduler.core.service.ITimerTaskJobLogManager;
import com.ink.scheduler.core.dao.ITimerTaskJobLogDao;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Service("timerTaskJobLogManager")
@Transactional
public class TimerTaskJobLogManagerImpl extends BaseManager<TimerTaskJobLog,Integer> implements ITimerTaskJobLogManager{

	@Autowired
	private ITimerTaskJobLogDao timerTaskJobLogDao;

	public EntityDao<TimerTaskJobLog, Integer> getEntityDao() {
		return this.timerTaskJobLogDao;
	}
	
}

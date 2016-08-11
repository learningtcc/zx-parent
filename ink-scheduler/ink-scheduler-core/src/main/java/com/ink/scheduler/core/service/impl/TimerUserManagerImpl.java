/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.scheduler.core.service.impl;

import com.ink.scheduler.core.service.ITimerUserManager;
import com.ink.scheduler.core.dao.ITimerUserDao;
import com.ink.scheduler.core.po.TimerUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ink.base.BaseManager;
import com.ink.base.EntityDao;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Service("timerUserManager")
@Transactional
public class TimerUserManagerImpl extends BaseManager<TimerUser,Integer> implements ITimerUserManager {

	@Autowired
	private ITimerUserDao timerUserDao;

	public EntityDao<TimerUser, Integer> getEntityDao() {
		return this.timerUserDao;
	}

	@Override
	public TimerUser getByUserName(String userName) {
		return timerUserDao.getByUserName(userName);
	}
}

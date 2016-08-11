/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.scheduler.core.dao.impl;

import com.ink.scheduler.core.dao.ITimerUserDao;
import com.ink.scheduler.core.po.TimerUser;
import org.springframework.stereotype.Repository;

import com.ink.base.dao.BaseIbatisDao;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Repository("timerUserDao")
public class TimerUserDaoImpl extends BaseIbatisDao<TimerUser,Integer> implements ITimerUserDao {
	
	@Override
	public String getIbatisSqlMapNamespace() {
		return "TimerUser";
	}
	
	@Override
	protected void prepareObjectForSave(TimerUser entity) {
//		if(entity.getId()) == null) {
//			entity.setId(IdGenerator.genUUIDStr().longValue());
//		}
	}

	@Override
	public TimerUser getByUserName(String userName) {
		TimerUser timerUser = getSqlSessionSlave().selectOne(getIbatisSqlMapNamespace()+".getByUserName", userName);
		return timerUser;
	}
}

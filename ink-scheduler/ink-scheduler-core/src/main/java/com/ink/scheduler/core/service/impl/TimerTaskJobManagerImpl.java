/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.scheduler.core.service.impl;

import com.ink.scheduler.core.po.TimerTaskJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ink.base.BaseManager;
import com.ink.base.EntityDao;
import com.ink.scheduler.core.service.ITimerTaskJobManager;
import com.ink.scheduler.core.dao.ITimerTaskJobDao;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Service("timerTaskJobManager")
@Transactional
public class TimerTaskJobManagerImpl extends BaseManager<TimerTaskJob,Integer> implements ITimerTaskJobManager{

	@Autowired
	private ITimerTaskJobDao timerTaskJobDao;

	public EntityDao<TimerTaskJob, Integer> getEntityDao() {
		return this.timerTaskJobDao;
	}

    public TimerTaskJob getByJobName(String jobName){
        return timerTaskJobDao.getByJobName(jobName);
    }

	@Override
	public int updateByCondition(TimerTaskJob entity) {
		return timerTaskJobDao.updateByCondition(entity);
	}

	@Override
	public TimerTaskJob getByJobUrlAndGroup(String jobUrl, String jobGroup) {
		//TODO 根据jobUrl和jobGroup取得job
		return timerTaskJobDao.getByJobUrlAndGroup(jobUrl,jobGroup);
	}
}

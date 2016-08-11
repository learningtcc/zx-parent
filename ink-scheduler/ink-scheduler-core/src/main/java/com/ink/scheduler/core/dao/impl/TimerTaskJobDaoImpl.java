/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.scheduler.core.dao.impl;

import com.ink.base.dao.BaseIbatisDao;
import com.ink.scheduler.core.dao.ITimerTaskJobDao;
import com.ink.scheduler.core.po.TimerTaskJob;
import org.springframework.stereotype.Repository;


import java.util.HashMap;
import java.util.Map;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Repository("timerTaskJobDao")
public class TimerTaskJobDaoImpl extends BaseIbatisDao<TimerTaskJob,Integer> implements ITimerTaskJobDao {
	
	@Override
	public String getIbatisSqlMapNamespace() {
		return "TimerTaskJob";
	}
	
	@Override
	protected void prepareObjectForSave(TimerTaskJob entity) {
//		if(entity.getJobId()) == null) {
//			entity.setJobId(IdGenerator.genUUIDStr().longValue());
//		}
	}

	public TimerTaskJob getByJobName(String jobName){
		TimerTaskJob timerTaskJob = getSqlSessionSlave().selectOne(getIbatisSqlMapNamespace()+".getByJobName", jobName);
		return timerTaskJob;
	}

	public int updateByCondition(TimerTaskJob entity){
		int upct=getSqlSession().update(getIbatisSqlMapNamespace()+".updateByCondition", entity);
		return upct;
	}

	@Override
	public TimerTaskJob getByJobUrlAndGroup(String jobUrl, String jobGroup) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("jobUrl",jobUrl);
		map.put("jobGroup",jobGroup);
		return getSqlSessionSlave().selectOne(getIbatisSqlMapNamespace()+".getByJobUrlAndGroup", map);
	}
}

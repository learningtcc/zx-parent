/**
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.monitor.core.dao.impl;

import com.ink.monitor.core.dao.IMonitorServiceRecordDao;
import com.ink.monitor.core.query.MonitorServiceRecordQuery;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ink.base.dao.BaseIbatisDao;
import com.ink.monitor.core.po.MonitorServiceRecord;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */

@Repository("monitorServiceRecordDao")
public class MonitorServiceRecordDaoImpl extends BaseIbatisDao<MonitorServiceRecord,Integer> implements IMonitorServiceRecordDao {

	@Override
	public String getIbatisSqlMapNamespace() {
		return "MonitorServiceRecord";
	}

	@Override
	protected void prepareObjectForSave(MonitorServiceRecord entity) {
//		if(entity.getId()) == null) {
//			entity.setId(IdGenerator.genUUIDStr().longValue());
//		}
	}

	/**
	 * 获取最近一次出现问题的记录
	 *
	 * @param monitorServiceRecord bean
	 * @return bean
	 * @throws Exception
	 */
	@Override
	public MonitorServiceRecord findLastErrorServiceRecord(MonitorServiceRecordQuery monitorServiceRecord) throws Exception {

		return this.getSqlSessionSlave()
				.selectOne(getIbatisSqlMapNamespace() + ".findLastErrorServiceRecord", monitorServiceRecord);
	}

	@Override
	public List<MonitorServiceRecord> getMonitorService(String sysCode) {
		
		return this.getSqlSessionSlave().selectList(getIbatisSqlMapNamespace() + ".getMonitorService", sysCode);
	}

	@Override
	public MonitorServiceRecord getErrCount(String sysCode) {
		return this.getSqlSessionSlave().selectOne(getIbatisSqlMapNamespace() + ".getErrCount", sysCode);

	}

	@Override
	public List<MonitorServiceRecord> getErrCountByStatus(String sysCode) {
		return this.getSqlSessionSlave().selectList(getIbatisSqlMapNamespace() + ".getErrCountByStatus", sysCode);
	}


}

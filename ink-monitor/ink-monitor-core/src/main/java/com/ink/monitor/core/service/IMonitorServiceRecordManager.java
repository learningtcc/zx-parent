/**
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.monitor.core.service;

import com.ink.monitor.core.po.MonitorServiceRecord;

import java.util.List;

import com.ink.base.IBaseManager;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 public interface IMonitorServiceRecordManager extends IBaseManager<MonitorServiceRecord, Integer>{


	List<MonitorServiceRecord> getMonitorService(String sysCode);

	/**
	 * 查询平台报错总数
	 * @param sysCode
	 * @return
	 */
	MonitorServiceRecord getErrCount(String sysCode);

	/**
	 * 按照解决，未解决，过期未处理查询系统报错次数
	 * @param code
	 * @return
	 */
	List<MonitorServiceRecord> getErrCountByStatus(String code);
	
}

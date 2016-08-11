/**
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.monitor.core.dao;

import java.util.List;

import com.ink.base.EntityDao;
import com.ink.monitor.core.po.MonitorServiceRecord;
import com.ink.monitor.core.query.MonitorServiceRecordQuery;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 

public interface IMonitorServiceRecordDao extends EntityDao<MonitorServiceRecord, Integer>{

    /**
     * ��ȡ���һ�γ�������ļ�¼
     * @param monitorServiceRecord bean
     * @return bean
     * @throws Exception
     */
    MonitorServiceRecord findLastErrorServiceRecord(MonitorServiceRecordQuery monitorServiceRecord)throws Exception;

    /**
     * 根据平台编号查询监控数据
     * @param sysCode
     * @return
     */
	List<MonitorServiceRecord> getMonitorService(String sysCode);

	/**
	 * 根据平台编号查询平台错误
	 * @param sysCode
	 * @return
	 */
	MonitorServiceRecord getErrCount(String sysCode);
	/**
	 * 根据平台编号查询平台错误:分类
	 * @param sysCode
	 * @return
	 */
	List<MonitorServiceRecord> getErrCountByStatus(String sysCode);

}
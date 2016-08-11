/**
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.monitor.core.service;

import java.util.Date;

/**
 * mongo存储各系统错误日志信息用于图表展示
 * Created by aiyungui on 2016/4/28.
 */
public interface IMongoLogChartServiceManager {

    /**
     *记录分钟系统日志到mongo
     * @param logDate
     * @param sysCode
     */
    void saveMinuteSysErrorLog(String sysCode,Date logDate);

    /**
     * 统计时间间隔内系统共出错次数
     * @param sysCode
     * @param startMinute
     * @param endMinute
     * @return
     */
    int countSysErrorLog(String sysCode,Date startMinute,Date endMinute);
}

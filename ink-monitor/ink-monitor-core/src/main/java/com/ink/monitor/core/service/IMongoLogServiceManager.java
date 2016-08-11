/**
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.monitor.core.service;

import com.ink.monitor.core.po.MongoLog;
import com.ink.base.page.Page;
import com.ink.monitor.core.query.MongoLogQuery;

/**
 * Created by aiyungui on 2016/4/28.
 */
public interface IMongoLogServiceManager {

    /**
     * 查询系统日志信息
     * @param logQuery
     * @return
     * @throws Exception
     */
    Page<MongoLog> findSystemLogInfo(MongoLogQuery logQuery)throws Exception;

    /**
     * 根据ID查询系统日志
     * @param source
     * @param _id
     * @return
     * @throws Exception
     */
    MongoLog getById(String source,String _id)throws Exception;
}

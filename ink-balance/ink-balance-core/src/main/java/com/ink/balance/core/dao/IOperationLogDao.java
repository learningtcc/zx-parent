package com.ink.balance.core.dao;

import com.ink.balance.core.po.OperationLog;
import com.ink.base.EntityDao;

/**
 * @author bo.chen
 * @Description 操作日志数据接口dao
 * @date 2016年4月11日 上午11:03:07
 */
public interface IOperationLogDao extends EntityDao<OperationLog, Integer> {
    int deleteByPrimaryKey(Long id);

    int insert(OperationLog record);

    int insertSelective(OperationLog record);

    OperationLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OperationLog record);

    int updateByPrimaryKey(OperationLog record);
}
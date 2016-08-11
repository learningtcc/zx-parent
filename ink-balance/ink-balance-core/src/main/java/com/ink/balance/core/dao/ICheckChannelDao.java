package com.ink.balance.core.dao;

import com.ink.balance.core.po.CheckChannel;
import com.ink.base.EntityDao;

/**
 * @author bo.chen
 * @Description 总对账数据接口dao
 * @date 2016年4月11日 上午11:03:07
 */
public interface ICheckChannelDao extends EntityDao<CheckChannel, Long> {
    int deleteByPrimaryKey(Long id);

    int insert(CheckChannel record);

    int insertSelective(CheckChannel record);

    CheckChannel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CheckChannel record);

    int updateByPrimaryKey(CheckChannel record);
}
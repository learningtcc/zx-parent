package com.ink.balance.core.dao;

import java.util.List;

import com.ink.balance.core.po.CheckChannel;
import com.ink.balance.core.po.PlatformData;
import com.ink.balance.core.query.PlatformDataQuery;
import com.ink.base.EntityDao;

/**
 * @author bo.chen
 * @Description 平台数据接口dao
 * @date 2016年4月11日 上午11:03:07
 */
public interface IPlatformDataDao extends EntityDao<PlatformData, Long> {
    int deleteByPrimaryKey(Long id);

    int insert(PlatformData record);

    int insertSelective(PlatformData record);

    PlatformData selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlatformData record);

    int updateByPrimaryKey(PlatformData record);

    /**
     * 获取平台数据
     */
    List<PlatformData> getPlatformDataList(PlatformDataQuery query);

    /**
     * 根据平台订单号更改平台数据
     */
    int updatePlatformByPlatformOrderNo(PlatformDataQuery query);

    /**
     * 获取平台本次参加对账数据List（包括了前期驻留的数据）
     */
    List<PlatformData> getPlatformBalanceList(PlatformDataQuery query);

    /**
     * 获取平台对账总笔数和总金额
     */
    CheckChannel getPlatformSumAmtAndCount(PlatformDataQuery query);

    /**
     * MQ的插入队列saveOrUpdate
     */
    int firstQueuesaveOrUpdate(PlatformData platformData);

    /**
     * MQ的更新队列saveOrUpdate
     */
    int secondQueuesaveOrUpdate(PlatformData platformData);
}
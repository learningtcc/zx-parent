package com.ink.balance.core.dao;

import java.util.List;

import com.ink.balance.core.po.ChannelData;
import com.ink.balance.core.po.CheckChannel;
import com.ink.balance.core.query.ChannelDataQuery;
import com.ink.base.EntityDao;

/**
 * @author bo.chen
 * @Description 渠道数据接口dao
 * @date 2016年4月11日 上午11:03:07
 */
public interface IChannelDataDao extends EntityDao<ChannelData, Long> {
    int deleteByPrimaryKey(Long id);

    int insert(ChannelData record);

    int insertSelective(ChannelData record);

    ChannelData selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ChannelData record);

    int updateByPrimaryKey(ChannelData record);

    /**
     * 批量插入渠道数据（暂时没用）
     */
    int batchInsert(List<ChannelData> list);

    /**
     * 获取渠道数据List
     */
    List<ChannelData> getChannelDataList(ChannelDataQuery query);

    /**
     * 根据平台订单号更新渠道数据
     */
    int updateChannelByPlatformOrderNo(ChannelDataQuery query);

    /**
     * 获取渠道对账总笔数和总金额
     */
    CheckChannel getChannelSumAmtAndCount(ChannelDataQuery query);

    /**
     * 获取渠道本次参加对账数据List（包括了前期驻留的数据）
     */
    List<ChannelData> getChannelBalanceList(ChannelDataQuery query);


}
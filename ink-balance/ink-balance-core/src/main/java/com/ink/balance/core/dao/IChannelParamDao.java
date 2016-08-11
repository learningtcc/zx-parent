package com.ink.balance.core.dao;

import java.util.List;

import com.ink.balance.core.po.ChannelParam;
import com.ink.balance.core.query.ChannelParamQuery;
import com.ink.base.EntityDao;

/**
 * @author bo.chen
 * @Description 渠道参数接口dao
 * @date 2016年4月11日 上午11:03:07
 */
public interface IChannelParamDao extends EntityDao<ChannelParam, Long> {
    int deleteByPrimaryKey(Long id);

    int insert(ChannelParam record);

    int insertSelective(ChannelParam record);

    ChannelParam selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ChannelParam record);

    int updateByPrimaryKey(ChannelParam record);

    /**
     * 获取渠道参数信息
     */
    ChannelParam getChannelParam(ChannelParamQuery query);
    /**
     * 获取渠道集合
    * @Title: getChannelParamList 
    * @Description: 
    * @param @param query
    * @param @return
    * @return List<ChannelParam> 
    * @author zhaojie
    * @date 2016年7月18日 下午4:52:03
    * @throws
     */
    List<ChannelParam> getChannelParamList(ChannelParamQuery query);
}
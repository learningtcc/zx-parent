package com.ink.balance.core.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ink.balance.core.dao.IChannelParamDao;
import com.ink.balance.core.po.ChannelParam;
import com.ink.balance.core.po.PlatformData;
import com.ink.balance.core.query.ChannelParamQuery;
import com.ink.base.dao.BaseIbatisDao;

/**
 * @author 作者 :bo.chen
 * @version 创建时间：2016年3月29日 上午11:46:53
 * @description 描述：渠道参数实现dao
 */
@Repository("channelParamDao")
public class ChannelParamDaoImpl extends BaseIbatisDao<ChannelParam, Long>
        implements IChannelParamDao {

    @Override
    public String getIbatisSqlMapNamespace() {
        return "ChannelParam";
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(ChannelParam record) {
        return 0;
    }

    @Override
    public int insertSelective(ChannelParam record) {
        return 0;
    }

    @Override
    public ChannelParam selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(ChannelParam record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(ChannelParam record) {
        return 0;
    }

    /**
     * 通过ChannelParamQuery查询渠道参数对象
     */
    @Override
    public ChannelParam getChannelParam(ChannelParamQuery query) {
        return getSqlSession().selectOne("ChannelParam.getChannelParam",
                query);
    }
    /**
     * 通过ChannelParamQuery查询所有渠道
     */
    @Override
    public List<ChannelParam> getChannelParamList(ChannelParamQuery query) {
    	 List<ChannelParam> channelParamList = getSqlSession().selectList(
                 "ChannelParam.getChannelParamList", query);
         return channelParamList;
    }
}

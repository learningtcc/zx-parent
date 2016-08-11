package com.ink.balance.core.dao.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ink.balance.core.dao.IChannelDataDao;
import com.ink.balance.core.po.ChannelData;
import com.ink.balance.core.po.CheckChannel;
import com.ink.balance.core.query.ChannelDataQuery;
import com.ink.base.dao.BaseIbatisDao;

/**
 * @author 作者 :bo.chen
 * @version 创建时间：2016年3月29日 上午11:46:53
 * @description 描述：渠道数据实现dao
 */
@Repository("channelDataDao")
public class ChannelDataDaoImpl extends BaseIbatisDao<ChannelData, Long>
        implements IChannelDataDao {

    @Override
    public String getIbatisSqlMapNamespace() {
        return "ChannelData";
    }

    @Override
    public void deleteByEntity(ChannelData arg0) {

    }

    @Override
    public ChannelData getByEntity(ChannelData arg0) {
        return null;
    }

    @Override
    public int save(ChannelData arg0) throws DataAccessException {
        return 0;
    }

    @Override
    public int saveBatch(List<ChannelData> arg0) throws DataAccessException {
        return 0;
    }

    @Override
    public int update(ChannelData arg0) throws DataAccessException {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(ChannelData record) {
        return 0;
    }

    @Override
    public int insertSelective(ChannelData record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(ChannelData record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(ChannelData record) {
        return 0;
    }

    /**
     * 批量插入（暂时没用）
     */
    @Override
    public int batchInsert(List<ChannelData> list) {
        return 0;
    }

    /**
     * 通过主键获取ChannelData对象
     */
    @Override
    public ChannelData selectByPrimaryKey(Long id) {
        return (ChannelData) getSqlSession().selectOne(
                "ChannelData.selectByPrimaryKey", id);
    }

    /**
     * 通过ChannelDataQuery对象获取ChannelData的List集合
     */
    @Override
    public List<ChannelData> getChannelDataList(ChannelDataQuery query) {
        return getSqlSession().selectList("ChannelData.getChannelDataList",
                query);
    }

    /**
     * 通过ChannelDataQuery对象更新ChannelData数据
     */
    @Override
    public int updateChannelByPlatformOrderNo(ChannelDataQuery query) {
        return getSqlSession().update(
                "ChannelData.updateChannelByPlatformOrderNo", query);
    }

    /**
     * 获取渠道对账的总金额和总笔数
     */
    @Override
    public CheckChannel getChannelSumAmtAndCount(ChannelDataQuery query) {
        return getSqlSession().selectOne("ChannelData.getChannelSumAmtAndCount",
                query);
    }

    /**
     * 获取渠道本次参加对账数据List（包括了前期驻留的数据）
     */
    @Override
    public List<ChannelData> getChannelBalanceList(ChannelDataQuery query) {
        return getSqlSession().selectList("ChannelData.getChannelBalanceList",
                query);
    }

}

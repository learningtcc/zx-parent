package com.ink.balance.core.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ink.balance.core.dao.IPlatformDataDao;
import com.ink.balance.core.po.CheckChannel;
import com.ink.balance.core.po.PlatformData;
import com.ink.balance.core.query.PlatformDataQuery;
import com.ink.base.dao.BaseIbatisDao;

/**
 * @author 作者 :bo.chen
 * @version 创建时间：2016年3月29日 上午11:46:53
 * @description 描述：平台数据实现dao
 */
@Repository("platformDataDao")
public class PlatformDataDaoImpl extends BaseIbatisDao<PlatformData, Long>
        implements IPlatformDataDao {

    @Override
    public String getIbatisSqlMapNamespace() {
        return "PlatformData";
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(PlatformData record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(PlatformData record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(PlatformData record) {
        return 0;
    }

    /**
     * 向平台表中插入记录
     */
    @Override
    public int insertSelective(PlatformData record) {
        return getSqlSession().insert(
                "PlatformData.insertSelective", record);
    }

    /**
     * 根据主键查询平台数据
     */
    @Override
    public PlatformData selectByPrimaryKey(Long id) {
        return (PlatformData) getSqlSession().selectOne(
                "PlatformData.selectByPrimaryKey", id);
    }

    /**
     * 根据query对象查询平台数据List集合
     */
    @Override
    public List<PlatformData> getPlatformDataList(PlatformDataQuery query) {
        List<PlatformData> platformDataList = getSqlSession().selectList(
                "PlatformData.getPlatformDataList", query);
        return platformDataList;
    }

    /**
     * 根据query对象更新平台数据
     */
    @Override
    public int updatePlatformByPlatformOrderNo(PlatformDataQuery query) {
        return getSqlSession().update(
                "PlatformData.updatePlatformByPlatformOrderNo", query);
    }

    /**
     * 获取平台本次参加对账数据List（包括了前期驻留的数据）
     */
    @Override
    public List<PlatformData> getPlatformBalanceList(PlatformDataQuery query) {
        List<PlatformData> platformDataList = getSqlSession().selectList(
                "PlatformData.getPlatformBalanceList", query);
        return platformDataList;
    }

    /**
     * 获取平台对账的总笔数和总金额
     */
    @Override
    public CheckChannel getPlatformSumAmtAndCount(PlatformDataQuery query) {
        return getSqlSession().selectOne("PlatformData.getPlatformSumAmtAndCount",
                query);
    }

    /**
     * MQ的插入队列saveOrUpdate
     */
    @Override
    public int firstQueuesaveOrUpdate(PlatformData platformData) {
        return getSqlSession().insert(
                "PlatformData.firstQueuesaveOrUpdate", platformData);
    }

    /**
     * MQ的更新队列saveOrUpdate
     */
    @Override
    public int secondQueuesaveOrUpdate(PlatformData platformData) {
        return getSqlSession().insert(
                "PlatformData.secondQueuesaveOrUpdate", platformData);
    }

}

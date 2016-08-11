package com.ink.balance.core.dao.impl;

import org.springframework.stereotype.Repository;

import com.ink.balance.core.dao.ICheckChannelDao;
import com.ink.balance.core.po.CheckChannel;
import com.ink.base.dao.BaseIbatisDao;

/**
 * @author 作者 :bo.chen
 * @version 创建时间：2016年3月29日 上午11:46:53
 * @description 描述：总对账数据实现dao
 */
@Repository("checkChannelDao")
public class CheckChannelDaoImpl extends BaseIbatisDao<CheckChannel, Long>
        implements ICheckChannelDao {

    @Override
    public String getIbatisSqlMapNamespace() {
        return "CheckChannel";
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(CheckChannel record) {
        return 0;
    }

    @Override
    public CheckChannel selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public int updateByPrimaryKey(CheckChannel record) {
        return 0;
    }

    /**
     * 通过CheckChannel更新调账对象
     */
    @Override
    public int updateByPrimaryKeySelective(CheckChannel record) {
        return getSqlSession().update(
                "CheckChannel.updateByPrimaryKeySelective", record);
    }

    /**
     * 插入调账表记录
     */
    @Override
    public int insertSelective(CheckChannel record) {
        return getSqlSession().insert("CheckChannel.insertSelective", record);
    }

}

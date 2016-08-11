package com.ink.balance.core.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ink.balance.core.dao.ICheckDifferenceDao;
import com.ink.balance.core.po.CheckDifference;
import com.ink.base.dao.BaseIbatisDao;

/**
 * @author 作者 :bo.chen
 * @version 创建时间：2016年3月29日 上午11:46:53
 * @description 描述：差异数据实现dao
 */
@Repository("checkDifferenceDao")
public class CheckDifferenceDaoImpl extends BaseIbatisDao<CheckDifference, Long>
        implements ICheckDifferenceDao {

    @Override
    public String getIbatisSqlMapNamespace() {
        return "CheckDifference";
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(CheckDifference record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(CheckDifference record) {
        return 0;
    }

    /**
     * 根据主键查询记录
     */
    @Override
    public CheckDifference selectByPrimaryKey(Long id) {
        return getSqlSession().selectOne("CheckDifference.selectByPrimaryKey", id);
    }

    /**
     * 根据record更新差异表对象
     */
    @Override
    public int updateByPrimaryKeySelective(CheckDifference record) {
        return getSqlSession().update(
                "CheckDifference.updateByPrimaryKeySelective", record);
    }

    /**
     * 根据平台订单号查询差异表list集合
     */
    @Override
    public List<CheckDifference> selectByPlatformOrderNo(String platformOrderNo) {
        return getSqlSession().selectList("CheckDifference.selectByPlatformOrderNo", platformOrderNo);
    }

    /**
     * 插入差异表数据
     */
    @Override
    public int insertSelective(CheckDifference record) {
        return getSqlSession().insert("CheckDifference.insertSelective", record);
    }


}

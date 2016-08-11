package com.ink.balance.core.dao.impl;

import com.ink.balance.core.dao.ICheckBalanceDao;
import com.ink.balance.core.po.CheckBalance;
import com.ink.base.dao.BaseIbatisDao;

import org.springframework.stereotype.Repository;

/**
 * @author 作者 :bo.chen
 * @version 创建时间：2016年3月29日 上午11:46:53
 * @description 描述：调账数据实现dao
 */
@Repository("checkBalanceDao")
public class CheckBalanceDaoImpl extends BaseIbatisDao<CheckBalance, Long>
        implements ICheckBalanceDao {

    @Override
    public String getIbatisSqlMapNamespace() {
        return "CheckBalance";
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(CheckBalance record) {
        return 0;
    }

    @Override
    public int insertSelective(CheckBalance record) {
        return 0;
    }

    @Override
    public CheckBalance selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(CheckBalance record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(CheckBalance record) {
        return 0;
    }

    /**
     * 通过platformOrderNo查询调账表详情
     */
    @Override
    public CheckBalance getBalanceDetails(String platformOrderNo) {
        return getSqlSession().selectOne("CheckBalance.getBalanceDetails", platformOrderNo);
    }


}

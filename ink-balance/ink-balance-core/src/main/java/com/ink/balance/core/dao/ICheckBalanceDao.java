package com.ink.balance.core.dao;

import com.ink.balance.core.po.CheckBalance;
import com.ink.base.EntityDao;

/**
 * @author bo.chen
 * @Description 调账数据接口dao
 * @date 2016年4月11日 上午11:03:07
 */
public interface ICheckBalanceDao extends EntityDao<CheckBalance, Long> {
    int deleteByPrimaryKey(Long id);

    int insert(CheckBalance record);

    int insertSelective(CheckBalance record);

    CheckBalance selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CheckBalance record);

    int updateByPrimaryKey(CheckBalance record);
    
    /**
     * 通过平台订单号获取调账信息
     */
    CheckBalance getBalanceDetails(String platformOrderNo);


}
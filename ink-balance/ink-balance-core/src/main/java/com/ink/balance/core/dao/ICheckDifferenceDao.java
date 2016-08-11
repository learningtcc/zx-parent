package com.ink.balance.core.dao;

import com.ink.balance.core.po.CheckDifference;
import com.ink.base.EntityDao;

import java.util.List;

/**
 * @author bo.chen
 * @Description 差异数据接口dao
 * @date 2016年4月11日 上午11:03:07
 */
public interface ICheckDifferenceDao extends EntityDao<CheckDifference, Long> {
    int deleteByPrimaryKey(Long id);

    int insert(CheckDifference record);

    int insertSelective(CheckDifference record);

    CheckDifference selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CheckDifference record);

    int updateByPrimaryKey(CheckDifference record);

    /**
     * 通过平台订单号获取差异表list集合
     */
    List<CheckDifference> selectByPlatformOrderNo(String platformOrderNo);
}
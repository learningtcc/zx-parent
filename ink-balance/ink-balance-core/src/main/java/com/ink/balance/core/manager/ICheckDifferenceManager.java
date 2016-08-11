package com.ink.balance.core.manager;

import com.ink.balance.core.po.CheckDifference;
import com.ink.base.IBaseManager;

import java.util.List;

/**
 * @author 作者 :bo.chen
 * @version 创建时间：2016年3月29日 下午1:26:56
 * @description 描述：差异数据参数manager接口
 */
public interface ICheckDifferenceManager extends
        IBaseManager<CheckDifference, Long> {

    /**
     * 插入差异数据
     */
    int insertCheckDifference(CheckDifference checkDifference);

    /**
     * 更新差异数据
     */
    int updateByPrimaryKeySelective(CheckDifference record);

    /**
     * 通过主键查询差异数据
     */
    CheckDifference selectByPrimaryKey(Long id);

    /**
     * 通过平台订单号查询差异数据List集合
     */
    List<CheckDifference> selectByPlatformOrderNo(String platformOrderNo);
}

package com.ink.balance.core.manager;

import com.ink.balance.core.po.CheckBalance;
import com.ink.base.IBaseManager;


/**
 * @author 作者 :bo.chen
 * @version 创建时间：2016年4月28日 下午1:26:56
 * @description 描述：调账数据参数manager接口
 */
public interface ICheckBalanceManager extends IBaseManager<CheckBalance, Long> {

    /**
     * 根据主键获取调账对象
     */
    CheckBalance selectByPrimaryKey(Long id);

    /**
     * 根据平台订单号获取调账对象
     */
    CheckBalance getBalanceDetails(String platformOrderNo);
}

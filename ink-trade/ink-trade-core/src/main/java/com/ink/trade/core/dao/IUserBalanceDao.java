/**
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.trade.core.dao;

import com.ink.base.EntityDao;
import com.ink.trade.core.po.UserBalance;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */


public interface IUserBalanceDao extends EntityDao<UserBalance, java.lang.Long> {
    /**
     * 根据商户号和商户用户号查询用户余额
     * @param userBalance
     * @return
     */
    UserBalance getByMerNoAndUserId(UserBalance userBalance);

    /**
     * 更新用户余额
     * @param userBalance
     * @return
     */
    int updateAmount(UserBalance userBalance);
    
    public int updateNotNull(UserBalance userBalance);
}
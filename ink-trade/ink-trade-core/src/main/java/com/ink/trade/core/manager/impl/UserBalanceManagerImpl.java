/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.trade.core.manager.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ink.base.BaseManager;
import com.ink.base.EntityDao;
import com.ink.base.log.util.YinkerLogger;
import com.ink.trade.core.dao.IUserBalanceDao;
import com.ink.trade.core.manager.IUserBalanceManager;
import com.ink.trade.core.po.UserBalance;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */

@Service("userBalanceManager")
@Transactional
public class UserBalanceManagerImpl extends BaseManager<UserBalance, java.lang.Long> implements IUserBalanceManager {
    YinkerLogger logger=YinkerLogger.getLogger(UserBalanceManagerImpl.class);
    @Autowired
    private IUserBalanceDao userBalanceDao;

    public EntityDao<UserBalance, java.lang.Long> getEntityDao() {
        return this.userBalanceDao;
    }

    @Override
    public UserBalance getByMerNoAndUserId(UserBalance userBalance) {
        UserBalance queryResult = userBalanceDao.getByMerNoAndUserId(userBalance);
        if (queryResult == null) {
            UserBalance insertParam = new UserBalance();
            insertParam.setBalance(BigDecimal.ZERO);
            insertParam.setMchId(userBalance.getMchId());
            insertParam.setUserId(userBalance.getUserId());
            Date sysdate = new Date();
            insertParam.setCreateTime(sysdate);
            insertParam.setLastUpdatetime(sysdate);
            insertParam.setVersion(1);
            try {
                save(insertParam);
                queryResult=insertParam;
            } catch (Exception ex) {
                logger.info("并发插入用户汇总余额异常，"+ex.getMessage());
                queryResult = userBalanceDao.getByMerNoAndUserId(userBalance);
            }
        }
        return queryResult;
    }

    @Override
    public int updateAmount(UserBalance userBalance) {
        return userBalanceDao.updateAmount(userBalance);
    }
    @Override
    public int updateNotNull(UserBalance userBalance){
		return userBalanceDao.updateNotNull(userBalance);
	}
}

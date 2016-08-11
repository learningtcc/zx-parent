/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.trade.core.dao.impl;

import org.springframework.stereotype.Repository;

import com.ink.base.dao.BaseIbatisDao;
import com.ink.trade.core.dao.IUserBalanceDao;
import com.ink.trade.core.po.UserBalance;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Repository("userBalanceDao")
public class UserBalanceDaoImpl extends BaseIbatisDao<UserBalance,java.lang.Long> implements IUserBalanceDao{
	
	@Override
	public String getIbatisSqlMapNamespace() {
		return "UserBalance";
	}
	
	@Override
	protected void prepareObjectForSave(UserBalance entity) {
//		if(entity.getId()) == null) {
//			entity.setId(IdGenerator.genUUIDStr().longValue());
//		}
	}

    @Override
    public UserBalance getByMerNoAndUserId(UserBalance userBalance) {
        return getSqlSessionSlave().selectOne(getIbatisSqlMapNamespace().concat(".getByMerNoAndUserId"),userBalance);
    }

    @Override
    public int updateAmount(UserBalance userBalance) {
        return getSqlSession().update(getIbatisSqlMapNamespace().concat(".updateAmount"),userBalance);
    }
    @Override
    public int updateNotNull(UserBalance userBalance){
		return getSqlSession().update("UserBalance.updateNotNull",userBalance);
	}
}

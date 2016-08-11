package com.ink.trade.core.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ink.base.dao.BaseIbatisDao;
import com.ink.trade.core.dao.IAuthOrderDao;
import com.ink.trade.core.po.AuthOrder;
@Repository
public class AuthOrderDaoImpl extends BaseIbatisDao<AuthOrder,Long> implements IAuthOrderDao{
	@Override
    public String getIbatisSqlMapNamespace() {
        return "AuthOrder";
    }

    @Override
    public AuthOrder getOrderByOrderId(String authOrderId) {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("authOrderId", authOrderId);
        Object obj = getSqlSessionSlave().selectOne(getIbatisSqlMapNamespace().concat(".getOrderByOrderId"), param);
        return (AuthOrder)obj;
    }
    @Override
    public int updateNotNull(AuthOrder authOrder){
		return getSqlSession().update("AuthOrder.updateNotNull",authOrder);
	}
}

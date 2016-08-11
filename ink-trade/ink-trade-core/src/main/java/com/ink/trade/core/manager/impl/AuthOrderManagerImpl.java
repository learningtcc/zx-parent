package com.ink.trade.core.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ink.base.BaseManager;
import com.ink.base.EntityDao;
import com.ink.trade.core.dao.IAuthOrderDao;
import com.ink.trade.core.manager.IAuthOrderManager;
import com.ink.trade.core.po.AuthOrder;
@Service("authOrderManager")
@Transactional
public class AuthOrderManagerImpl extends BaseManager<AuthOrder, Long> implements IAuthOrderManager{
    @Autowired
    private IAuthOrderDao authDao;
    @Override
    protected EntityDao<AuthOrder, Long> getEntityDao() {
        return authDao;
    }
    public IAuthOrderDao getAuthDao() {
        return authDao;
    }
    public void setAuthDao(IAuthOrderDao authDao) {
        this.authDao = authDao;
    }
    @Override
    public AuthOrder getOrderByOrderId(String authOrderId){
        return authDao.getOrderByOrderId(authOrderId);
    }
    @Override
    public int updateNotNull(AuthOrder authOrder){
		return authDao.updateNotNull(authOrder);
	}
}

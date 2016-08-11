/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.trade.core.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ink.base.BaseManager;
import com.ink.base.EntityDao;
import com.ink.trade.core.dao.IMchAuthDao;
import com.ink.trade.core.manager.IMchAuthManager;
import com.ink.trade.core.po.MchAuth;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Service("mchAuthManager")
@Transactional
public class MchAuthManagerImpl extends BaseManager<MchAuth,java.lang.Long> implements IMchAuthManager{

	@Autowired
	private IMchAuthDao mchAuthDao;

	public EntityDao<MchAuth, java.lang.Long> getEntityDao() {
		return this.mchAuthDao;
	}

    @Override
    public MchAuth getByMchIdPayType(String mchId, String payType) {
       return mchAuthDao.getByMchIdPayType(mchId, payType);
    }
    @Override
    public int updateNotNull(MchAuth mchAuth){
		return mchAuthDao.updateNotNull(mchAuth);
	}
}

/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.trade.core.manager.impl;

import com.ink.base.BaseManager;
import com.ink.base.EntityDao;
import com.ink.trade.core.dao.IUnknownOrderDao;
import com.ink.trade.core.manager.IUnknownOrderManager;
import com.ink.trade.core.po.UnknownOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Service("unknownOrderManager")
@Transactional
public class UnknownOrderManagerImpl extends BaseManager<UnknownOrder,Long> implements IUnknownOrderManager {

	@Autowired
	private IUnknownOrderDao unknownOrderDao;

	public EntityDao<UnknownOrder, Long> getEntityDao() {
		return this.unknownOrderDao;
	}

    @Override
    public int remarkOrder(Map<String,Object> param) {
        return unknownOrderDao.remarkOrder(param);
    }

    @Override
    public List<UnknownOrder> queryTask(Map<String,Object> param) {
        return unknownOrderDao.queryTask(param);
    }
    @Override
    public int updateNotNull(UnknownOrder unknownOrder){
		return unknownOrderDao.updateNotNull(unknownOrder);
	}
}

/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.trade.core.dao.impl;

import com.ink.base.dao.BaseIbatisDao;
import com.ink.trade.core.dao.IUnknownOrderDao;
import com.ink.trade.core.po.UnknownOrder;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Repository("unknownOrderDao")
public class UnknownOrderDaoImpl extends BaseIbatisDao<UnknownOrder,Long> implements IUnknownOrderDao{
	
	@Override
	public String getIbatisSqlMapNamespace() {
		return "UnknownOrder";
	}
	
	@Override
	protected void prepareObjectForSave(UnknownOrder entity) {
//		if(entity.getId()) == null) {
//			entity.setId(IdGenerator.genUUIDStr().longValue());
//		}
	}

    @Override
    public int remarkOrder(Map<String,Object> param) {
        return this.getSqlSession().update(this.getIbatisSqlMapNamespace().concat(".remarkOrder"),param);
    }

    @Override
    public List<UnknownOrder> queryTask(Map<String,Object> param) {
        return this.getSqlSession().selectList(this.getIbatisSqlMapNamespace().concat(".queryTask"),param);
    }
    @Override
    public int updateNotNull(UnknownOrder unknownOrder){
		return getSqlSession().update("UnknownOrder.updateNotNull",unknownOrder);
	}
}

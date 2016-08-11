package com.ink.trade.core.dao.impl;

import com.ink.base.dao.BaseIbatisDao;
import com.ink.trade.core.dao.ITradeOrderDao;
import com.ink.trade.core.po.TradeOrder;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TradeOrderDaoImpl extends BaseIbatisDao<TradeOrder, Long>
		implements ITradeOrderDao {
	@Override
	public String getIbatisSqlMapNamespace() {
		return "TradeOrder";
	}

	@Override
	public List<TradeOrder> findTradeOrders(TradeOrder record) {
		return getSqlSession().selectList("TradeOrder.findTradeOrders", record);
	}

    @Override
    public TradeOrder getByMerNoAndMerOrderNo(TradeOrder record) {
        return getSqlSession().selectOne(getIbatisSqlMapNamespace().concat(".getByMerNoAndMerOrderNo"),record);
    }

	@Override
	public int updateStatus(TradeOrder order) {
		return getSqlSession().update(getIbatisSqlMapNamespace().concat(".updateStatus"),order);
	}
	@Override
	public int updateNotNull(TradeOrder tradeOrder){
		return getSqlSession().update("TradeOrder.updateNotNull",tradeOrder);
	}
}

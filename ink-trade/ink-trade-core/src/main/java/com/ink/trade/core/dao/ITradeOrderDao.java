package com.ink.trade.core.dao;

import com.ink.base.EntityDao;
import com.ink.trade.core.po.TradeOrder;

import java.util.List;

public interface ITradeOrderDao extends EntityDao<TradeOrder, Long> {

	public List<TradeOrder> findTradeOrders(TradeOrder record);

    /**
     * 根据商户号和商户订单号查询交易订单
     * @param record
     * @return
     */
    public TradeOrder getByMerNoAndMerOrderNo(TradeOrder record);

    /**
     * 更新订单状态
     * @param order
     * @return
     */
    int updateStatus(TradeOrder order);
    
    public int updateNotNull(TradeOrder tradeOrder);
}

package com.ink.trade.core.manager;

import com.ink.base.IBaseManager;
import com.ink.trade.core.po.TradeOrder;

import java.util.List;

public interface ITradeOrderManager extends IBaseManager<TradeOrder, Long> {

	public List<TradeOrder> findTradeOrders(TradeOrder record);

    public TradeOrder getByMerNoAndMerOrderNo(TradeOrder record);

    /**
     * 保存交易订单并生成交易流水
     * @param order
     * @return
     */
    int saveOrderAndCreateLog(TradeOrder order);

    /**
     * 更新交易订单并生成交易流水
     * @param order
     * @return
     */
    int updateOrderAndCreateLog(TradeOrder order);

    /**
     * 更新订单状态为终态并生成交易流水
     * @param order
     * @return
     */
    int updateStatusAndCreateLog(TradeOrder order);
    
    public int updateNotNull(TradeOrder tradeOrder);
}

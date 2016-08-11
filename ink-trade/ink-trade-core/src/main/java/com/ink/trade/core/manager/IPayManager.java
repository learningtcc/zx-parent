package com.ink.trade.core.manager;

import com.ink.base.IBaseManager;
import com.ink.trade.core.po.Pay;

public interface IPayManager extends IBaseManager<Pay, Long> {
	public Pay findPayOrder(Pay pay);

    /**
     * 生成支付订单并创建支付订单流水
     * @param pay
     * @return
     */
    int savePayAndCreateLog(Pay pay);

    /**
     * 更新支付订单并创建支付订单流水
     * @param pay
     * @return
     */
    int updatePayAndCreateLog(Pay pay);

    /**
     * 更新订单状态并创建支付订单流水
     * @param pay
     * @return
     */
    int updateStatusAndCreateLog(Pay pay);
    
    public int updateNotNull(Pay pay);
}

package com.ink.trade.core.exception.orderfail;

import com.ink.trade.core.cnst.TradeRespConstant;

public class OrderUniqueException extends OrderFailException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3640299703030790542L;

	public OrderUniqueException() {
		super(TradeRespConstant.TRADE_Order_0001,TradeRespConstant.TRADE_Order_0001_MSG);
	}

}

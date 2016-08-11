package com.ink.trade.core.exception.orderfail;

import com.ink.trade.core.cnst.TradeRespConstant;

public class OrderStatusErrorException extends OrderFailException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -496858562458303619L;
	public OrderStatusErrorException(){
		super(TradeRespConstant.ORDER_STATUS_ERROR, TradeRespConstant.ORDER_STATUS_ERROR_MSG);
	}

}

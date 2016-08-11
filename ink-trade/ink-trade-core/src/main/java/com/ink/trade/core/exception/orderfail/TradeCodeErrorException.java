package com.ink.trade.core.exception.orderfail;

import com.ink.trade.core.cnst.TradeRespConstant;

public class TradeCodeErrorException extends OrderFailException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3661659217402392356L;
	public TradeCodeErrorException(){
		super(TradeRespConstant.TRADE_CODE_ERROR, TradeRespConstant.TRADE_CODE_ERROR_MSG);
	}
}

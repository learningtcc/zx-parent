package com.ink.trade.core.exception.tradesystem;

import com.ink.trade.core.cnst.TradeRespConstant;
import com.ink.trade.core.exception.TradeException;

public class TradeSystemException extends TradeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8949074473869961847L;
    public TradeSystemException(){
    	super(TradeRespConstant.TRADE_SYSERROR, TradeRespConstant.TRADE_SYSERROR_MSG);
    }
}

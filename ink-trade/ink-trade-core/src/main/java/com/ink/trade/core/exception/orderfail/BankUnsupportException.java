package com.ink.trade.core.exception.orderfail;

import com.ink.trade.core.cnst.TradeRespConstant;

public class BankUnsupportException extends OrderFailException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5389995649931148944L;
	public BankUnsupportException(){
		super(TradeRespConstant.BANK_UNSUPPORT,TradeRespConstant.BANK_UNSUPPORT_MSG);
	}

}

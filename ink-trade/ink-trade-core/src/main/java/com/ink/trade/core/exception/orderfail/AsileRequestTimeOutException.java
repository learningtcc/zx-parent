package com.ink.trade.core.exception.orderfail;

import com.ink.trade.core.cnst.TradeRespConstant;

public class AsileRequestTimeOutException extends OrderFailException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2747653926931557263L;

	public AsileRequestTimeOutException() {
     super(TradeRespConstant.CHANNEL_REQUEST_TIMEOUT, TradeRespConstant.CHANNEL_REQUEST_TIMEOUT_MSG);
	}
}

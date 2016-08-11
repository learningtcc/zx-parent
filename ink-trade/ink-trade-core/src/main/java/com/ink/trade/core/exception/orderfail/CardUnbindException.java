package com.ink.trade.core.exception.orderfail;

import com.ink.trade.core.cnst.TradeRespConstant;

public class CardUnbindException extends OrderFailException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -170250865263443181L;
   public CardUnbindException(){
	   super(TradeRespConstant.CARD_NOT_BIND, TradeRespConstant.CARD_NOT_BIND_MSG);
   }
}

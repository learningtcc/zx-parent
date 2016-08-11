package com.ink.trade.core.exception.orderfail;

import com.ink.trade.core.exception.TradeException;

public class OrderFailException extends TradeException{

	/**
	 * 
	 */
private static final long serialVersionUID = -3932018080516302128L;
public OrderFailException(){}
 public OrderFailException(String message){
	 super(message);
 }
 public OrderFailException(final String code,final String message){
	 super(code,message);
 }
}

package com.ink.trade.core.exception.orderfail;



public class RouteFailException extends OrderFailException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3778215515794325027L;
  public RouteFailException(String code,String message){
	  super(code, message);
  }
}

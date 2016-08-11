package com.ink.trade.core.exception.orderfail;

public class UserMerchantIllegalException extends OrderFailException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 607029138722071634L;
	public UserMerchantIllegalException(String code,String message){
		super(code, message);
	}
}

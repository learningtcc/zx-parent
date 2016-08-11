package com.ink.trade.core.exception.orderfail;


public class CertifiedSignException extends OrderFailException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8742613629069407682L;
	public CertifiedSignException(String code,String message){
		super(code,message);
	}
}

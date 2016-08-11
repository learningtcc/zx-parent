package com.ink.base.exception;


import com.ink.base.cnst.YinkerConst;

/**
 * 数据库异常：不唯一异常
 */
public class NonuniquenessException extends FreightException {

	private static final long serialVersionUID = 1L;
	
	public NonuniquenessException() {
		super(YinkerConst.ALIAS_EXCEPTION_CODE_DB_NONUNIQUENESS,YinkerConst.ALIAS_EXCEPTION_MSG_DB_NONUNIQUENESS);
	}
	
	public NonuniquenessException(String message) {
		super(YinkerConst.ALIAS_EXCEPTION_CODE_DB_NONUNIQUENESS,message);
	}
	
	public NonuniquenessException(String message,Throwable cause) {
		super(YinkerConst.ALIAS_EXCEPTION_CODE_DB_NONUNIQUENESS,message,cause);
	}
	
}

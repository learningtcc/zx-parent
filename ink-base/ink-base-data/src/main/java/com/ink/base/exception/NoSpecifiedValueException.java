package com.ink.base.exception;


import com.ink.base.cnst.YinkerConst;

/**
 * 业务异常：未设置值异常
 */
public class NoSpecifiedValueException extends FreightException {

	private static final long serialVersionUID = 1L;
	
	public NoSpecifiedValueException() {
		super(YinkerConst.ALIAS_EXCEPTION_CODE_BIZ_NOSPECIFIEDVALUE,YinkerConst.ALIAS_EXCEPTION_MSG_BIZ_NOSPECIFIEDVALUE);
	}
	
	public NoSpecifiedValueException(String message) {
		super(YinkerConst.ALIAS_EXCEPTION_CODE_BIZ_NOSPECIFIEDVALUE,message);
	}
	public NoSpecifiedValueException(String message,Throwable cause) {
		super(YinkerConst.ALIAS_EXCEPTION_CODE_BIZ_NOSPECIFIEDVALUE,message,cause);
	}
	
}

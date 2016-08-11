package com.ink.base.exception;


import com.ink.base.cnst.YinkerConst;

/**
 * 数据库异常：键值重复异常
 */
public class DuplicatePrimaryKeyException extends FreightException {

	private static final long serialVersionUID = 1L;
	
	public DuplicatePrimaryKeyException() {
		super(YinkerConst.ALIAS_EXCEPTION_CODE_DB_DUPLICATEKEY,YinkerConst.ALIAS_EXCEPTION_MSG_DB_DUPLICATEKEY);
	}
	
	public DuplicatePrimaryKeyException(String message) {
		super(YinkerConst.ALIAS_EXCEPTION_CODE_DB_DUPLICATEKEY,message);
	}
	
	public DuplicatePrimaryKeyException(String message,Throwable cause) {
		super(YinkerConst.ALIAS_EXCEPTION_CODE_DB_DUPLICATEKEY,message,cause);
	}
	
}

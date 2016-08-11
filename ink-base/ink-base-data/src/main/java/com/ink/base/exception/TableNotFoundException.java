package com.ink.base.exception;


import com.ink.base.cnst.YinkerConst;

/**
 * 数据库异常：表不存在异常
 */
public class TableNotFoundException extends FreightException {

	private static final long serialVersionUID = 1L;
	
	public TableNotFoundException() {
		super(YinkerConst.ALIAS_EXCEPTION_CODE_DB_TABLENOTFOUND,YinkerConst.ALIAS_EXCEPTION_MSG_DB_TABLENOTFOUND);
	}
	
	public TableNotFoundException(String message) {
		super(YinkerConst.ALIAS_EXCEPTION_CODE_DB_TABLENOTFOUND,message);
	}
	public TableNotFoundException(String message,Throwable cause) {
		super(YinkerConst.ALIAS_EXCEPTION_CODE_DB_TABLENOTFOUND,message,cause);
	}
}

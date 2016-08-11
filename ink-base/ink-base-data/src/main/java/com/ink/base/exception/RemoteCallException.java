package com.ink.base.exception;


import com.ink.base.cnst.YinkerConst;

/**
 * 业务异常：调用第三方服务异常
 */
public class RemoteCallException extends FreightException {

	private static final long serialVersionUID = 1L;
	
	public RemoteCallException() {
		super(YinkerConst.ALIAS_EXCEPTION_CODE_BIZ_REMOTECALL, YinkerConst.ALIAS_EXCEPTION_MSG_BIZ_REMOTECALL);
	}
	
	public RemoteCallException(String message) {
		super(YinkerConst.ALIAS_EXCEPTION_CODE_BIZ_REMOTECALL,message);
	}
	
	public RemoteCallException(String message,Throwable cause) {
		super(YinkerConst.ALIAS_EXCEPTION_CODE_BIZ_REMOTECALL,message,cause);
	}
	
}

package com.ink.base.redis.exception;

/**
 * ehcache异常
 * @author zongtt
 * 2016年7月12日14:30:11
 */
public class EhcacheRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 2332128885516654921L;

	public EhcacheRuntimeException() {
		super();
	}

	public EhcacheRuntimeException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public EhcacheRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public EhcacheRuntimeException(String message) {
		super(message);
	}

	public EhcacheRuntimeException(Throwable cause) {
		super(cause);
	}
}


package com.ink.user.core.exception;

/**
 * 
 * @ClassName: MsgException 
 * @Description: 消息提示异常 
 * @author guojie.gao 
 * @date 2015年10月15日 下午12:32:13 
 *
 */

public class MsgException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public MsgException() {
		super();
	}

	public MsgException(String msg) {
		super(msg);
	}
}

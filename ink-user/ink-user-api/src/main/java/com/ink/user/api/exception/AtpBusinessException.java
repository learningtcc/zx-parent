
package com.ink.user.api.exception;


/**
 * 
 * @ClassName: AtpBusinessException 
 * @Description: 基金业务异常 
 * @author guojie.gao 
 * @date 2015年10月15日 下午12:32:34 
 *
 */

public class AtpBusinessException extends RuntimeException {
	
	/** 
	 * @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 1L;

	private String code;
	
	private String message;
	
	
	public AtpBusinessException(){
		super();
	}
	
	public AtpBusinessException(String message){
		super(message);
	}
	
	public AtpBusinessException(final String code, final String message){
		super();
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}

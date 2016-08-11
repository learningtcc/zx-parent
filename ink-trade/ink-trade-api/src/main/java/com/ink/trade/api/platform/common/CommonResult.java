/**
 * ink.com Copyright (c) 2016-2025 All Rights Reserved.
 * 
 * @Description TODO
 * @author xuguoqi
 * @date 2016年7月7日 下午3:28:23
 */
package com.ink.trade.api.platform.common;

import java.io.Serializable;

/**
 * @Description TODO
 * @author xuguoqi
 * @date 2016年7月7日 下午3:28:23
 */
public class CommonResult<T> implements Serializable{

	/**  
	 * @since JDK 1.7  
	 */  
	
	private static final long serialVersionUID = -989314151426514577L;
	
	/**
     * 成功失败码：0为成功，其他：失败（六位数正整形）：默认0
     */
	private int code = 0;
	
	/**
     * 成功失败信息：默认成功
     */
	private String message = "success";
	
	/**
	 * 返回业务数据, 如果没有返回数据时，可定义为String类型，赋予""空值
	 */
	private T bussinessObj;

	public CommonResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CommonResult(int code, String message, T bussinessObj) {
		super();
		this.code = code;
		this.message = message;
		this.bussinessObj = bussinessObj;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getBussinessObj() {
		return bussinessObj;
	}

	public void setBussinessObj(T bussinessObj) {
		this.bussinessObj = bussinessObj;
	}

	@Override
	public String toString() {
		return "CommonResult [code=" + code + ", message=" + message + ", bussinessObj=" + bussinessObj + "]";
	}
	
	

}

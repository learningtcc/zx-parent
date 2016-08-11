package com.ink.channel.core.boofoopay.response;

import java.io.Serializable;

public class RootContent implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5350656299041424624L;
	
	private String return_code;//响应码
	
	private String return_msg;//响应信息
	
	public String getReturn_code() {
		return return_code;
	}
	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}
	public String getReturn_msg() {
		return return_msg;
	}
	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}
	
}

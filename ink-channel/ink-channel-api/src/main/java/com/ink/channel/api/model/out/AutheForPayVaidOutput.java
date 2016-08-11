package com.ink.channel.api.model.out;

import java.io.Serializable;

public class AutheForPayVaidOutput implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3284145006909691568L;
	
	private String resCode;// 返回码
	
	private String resMsg;// 返回信息
	
	private String token;// 令牌

	private String orderStatus;//订单状态
	
	public String getResCode() {
		return resCode;
	}

	public void setResCode(String resCode) {
		this.resCode = resCode;
	}

	public String getResMsg() {
		return resMsg;
	}

	public void setResMsg(String resMsg) {
		this.resMsg = resMsg;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
}

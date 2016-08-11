package com.ink.channel.api.model.out;

import java.io.Serializable;

public class AutheBindCardValidOutput implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -626101579797820483L;
	
	private String resCode;// 返回码
	
	private String resMsg;// 返回信息
	
	private String token;// 令牌

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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AutheBindCardValidOutput [resCode=");
		builder.append(resCode);
		builder.append(", resMsg=");
		builder.append(resMsg);
		builder.append(", token=");
		builder.append(token);
		builder.append("]");
		return builder.toString();
	}
}

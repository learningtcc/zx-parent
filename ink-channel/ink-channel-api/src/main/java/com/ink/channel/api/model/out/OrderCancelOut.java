package com.ink.channel.api.model.out;

import java.io.Serializable;

public class OrderCancelOut implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5926413543330334273L;
	
	private String resCode;// 响应码
	
	private String resMsg;// 响应消息

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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrderCancelOut [resCode=");
		builder.append(resCode);
		builder.append(", resMsg=");
		builder.append(resMsg);
		builder.append("]");
		return builder.toString();
	}
}

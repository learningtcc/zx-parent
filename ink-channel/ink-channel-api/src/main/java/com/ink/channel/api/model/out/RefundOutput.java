package com.ink.channel.api.model.out;

import java.io.Serializable;
/**
 * 退款输出参数
 * @author huohb
 *
 */
public class RefundOutput implements Serializable {

	private static final long serialVersionUID = 3956407705652014048L;

	private String resCode;// 返回码

	private String resMsg;// 返回描述

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
		builder.append("RefundOutput [resCode=");
		builder.append(resCode);
		builder.append(", resMsg=");
		builder.append(resMsg);
		builder.append("]");
		return builder.toString();
	}
}

package com.ink.channel.core.bestpay.response;

import java.io.Serializable;

public class EPayBaseResponseBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String code;
	
	private String msg;
	
	private String reqSeq;//请求流水

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getReqSeq() {
		return reqSeq;
	}

	public void setReqSeq(String reqSeq) {
		this.reqSeq = reqSeq;
	}
	
}

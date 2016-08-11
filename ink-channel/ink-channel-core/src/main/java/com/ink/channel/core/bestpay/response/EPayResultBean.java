package com.ink.channel.core.bestpay.response;

import java.io.Serializable;

public class EPayResultBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String code;
	
	private String msg;
	
	private EPayBaseResponseBean result;

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

	public EPayBaseResponseBean getResult() {
		return result;
	}

	public void setResult(EPayBaseResponseBean result) {
		this.result = result;
	}
}

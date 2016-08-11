package com.ink.channel.core.bestpay.response;

import java.io.Serializable;

/**
 * 翼支付请求返回消息实体
 * @author huohb
 *
 */
public class EPayPostResponseBean<T extends EPayBaseResponseBean> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String sign;
	
	private T data;

	public String getSign() {
		return sign;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
}

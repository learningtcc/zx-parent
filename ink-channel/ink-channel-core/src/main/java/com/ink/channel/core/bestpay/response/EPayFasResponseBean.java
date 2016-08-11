package com.ink.channel.core.bestpay.response;
/**
 * 翼支付代扣支付返回消息类
 * @author huohb
 *
 */
public class EPayFasResponseBean extends EPayBaseResponseBean {

	private static final long serialVersionUID = 1L;
	
	private String result;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}

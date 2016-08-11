package com.ink.channel.core.bestpay.response;
/**
 * 翼支付付款到银行卡账户返回消息类
 * @author huohb
 *
 */
public class EPayToCardResponseBean extends EPayBaseResponseBean {

	private static final long serialVersionUID = 1L;
	
	private String result;// 交易订单号

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}

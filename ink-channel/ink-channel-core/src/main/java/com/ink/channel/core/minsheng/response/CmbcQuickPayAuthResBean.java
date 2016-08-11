package com.ink.channel.core.minsheng.response;

import com.ink.channel.core.minsheng.request.CmbcBodyBean;

public class CmbcQuickPayAuthResBean extends CmbcBodyBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3937286715427753779L;
	private String merOrderId;
	private String custId;
	private String phoneToken;// 令牌（快捷支付的时候需要）
	
	public String getPhoneToken() {
		return phoneToken;
	}
	public void setPhoneToken(String phoneToken) {
		this.phoneToken = phoneToken;
	}
	public String getMerOrderId() {
		return merOrderId;
	}
	public void setMerOrderId(String merOrderId) {
		this.merOrderId = merOrderId;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	
}

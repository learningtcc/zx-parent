package com.ink.channel.core.minsheng.response;

import com.ink.channel.core.minsheng.request.CmbcBodyBean;

public class CmbcValidateCodeResBean extends CmbcBodyBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6358592580613404126L;
	
	private String merOrderId;//订单号

	private String custId;//客户号

	private String phoneToken;//令牌信息

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

	public String getPhoneToken() {
		return phoneToken;
	}

	public void setPhoneToken(String phoneToken) {
		this.phoneToken = phoneToken;
	}
	
	
}

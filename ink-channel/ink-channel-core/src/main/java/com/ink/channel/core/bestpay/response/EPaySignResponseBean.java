package com.ink.channel.core.bestpay.response;

public class EPaySignResponseBean extends EPayBaseResponseBean {
	
	private static final long serialVersionUID = 1L;
	
	private String signId;

	public String getSignId() {
		return signId;
	}

	public void setSignId(String signId) {
		this.signId = signId;
	}
}

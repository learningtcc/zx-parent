package com.ink.channel.core.minsheng.response;

import com.ink.channel.core.minsheng.request.CmbcBodyBean;

public class CmbcCollectionResBean extends CmbcBodyBean{
	/**
	 * 
	 */
	private static final long serialVersionUID = -36770346441758917L;
	private String tranState;//状态码

	public String getTranState() {
		return tranState;
	}

	public void setTranState(String tranState) {
		this.tranState = tranState;
	}
	
}

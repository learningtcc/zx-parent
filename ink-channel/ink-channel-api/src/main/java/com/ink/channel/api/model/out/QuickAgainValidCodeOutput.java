package com.ink.channel.api.model.out;

import java.io.Serializable;

public class QuickAgainValidCodeOutput implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 219976378320608585L;
	
	private String resCode;// 返回码
	
	private String resMsg;// 返回信息
	
	private String token;// 令牌
	
	private String orderNo;//订单号
	
	private String orgTranFlow;//流水号

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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrgTranFlow() {
		return orgTranFlow;
	}

	public void setOrgTranFlow(String orgTranFlow) {
		this.orgTranFlow = orgTranFlow;
	}
}

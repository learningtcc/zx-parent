package com.ink.channel.core.bestpay.request;

import java.io.Serializable;
/**
 * 翼支付请求消息基类
 * @author huohb
 *
 */
public class EPayBaseRequestBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String reqIp;// 请求IP
	
	private String platCode;// 接入平台号
	
	private String reqSeq;// 请求流水号
	
	private String custCode;// 客户编号

	public String getReqIp() {
		return reqIp;
	}

	public void setReqIp(String reqIp) {
		this.reqIp = reqIp;
	}

	public String getPlatCode() {
		return platCode;
	}

	public void setPlatCode(String platCode) {
		this.platCode = platCode;
	}

	public String getReqSeq() {
		return reqSeq;
	}

	public void setReqSeq(String reqSeq) {
		this.reqSeq = reqSeq;
	}

	public String getCustCode() {
		return custCode;
	}

	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}
}

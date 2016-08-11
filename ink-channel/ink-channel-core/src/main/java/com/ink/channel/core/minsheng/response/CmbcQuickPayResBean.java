package com.ink.channel.core.minsheng.response;

import com.ink.channel.core.minsheng.request.CmbcBodyBean;

public class CmbcQuickPayResBean extends CmbcBodyBean{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5852894702033109324L;
	private String tranRespCode;
	private String merOrderId;
	private String custId;
	private String refNo;
	private String storableCardNo;
	private String amount;
	public String getTranRespCode() {
		return tranRespCode;
	}
	public void setTranRespCode(String tranRespCode) {
		this.tranRespCode = tranRespCode;
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
	public String getRefNo() {
		return refNo;
	}
	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}
	public String getStorableCardNo() {
		return storableCardNo;
	}
	public void setStorableCardNo(String storableCardNo) {
		this.storableCardNo = storableCardNo;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	
}

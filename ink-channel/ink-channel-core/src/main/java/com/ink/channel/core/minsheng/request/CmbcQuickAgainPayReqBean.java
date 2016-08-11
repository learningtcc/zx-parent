package com.ink.channel.core.minsheng.request;

public class CmbcQuickAgainPayReqBean extends CmbcBodyBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6883546877348955045L;
	private String merOrderId;
	private String subject;
	private String bankNo;
	private String amount;
	private String custId;
	private String storableCardNo;
	private String backUrl;
	private String msgExt;
	public String getMerOrderId() {
		return merOrderId;
	}
	public void setMerOrderId(String merOrderId) {
		this.merOrderId = merOrderId;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBankNo() {
		return bankNo;
	}
	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getStorableCardNo() {
		return storableCardNo;
	}
	public void setStorableCardNo(String storableCardNo) {
		this.storableCardNo = storableCardNo;
	}
	public String getBackUrl() {
		return backUrl;
	}
	public void setBackUrl(String backUrl) {
		this.backUrl = backUrl;
	}
	public String getMsgExt() {
		return msgExt;
	}
	public void setMsgExt(String msgExt) {
		this.msgExt = msgExt;
	}
	
}

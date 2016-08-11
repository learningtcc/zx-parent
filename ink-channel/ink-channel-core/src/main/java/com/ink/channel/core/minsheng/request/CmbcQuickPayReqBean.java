package com.ink.channel.core.minsheng.request;

public class CmbcQuickPayReqBean extends CmbcBodyBean{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1672150315060584834L;
	private String merOrderId;
	private String subject;
	private String bankNo;
	private String cardNo;
	private String expiredDate;
	private String cvv2;
	private String amount;
	private String custName;
	private String custIdNo;
	private String custIdType;
	private String saveCustFlag;
	private String custId;
	private String phoneNo;
	private String phoneVerCode;
	private String phoneToken;
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
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getExpiredDate() {
		return expiredDate;
	}
	public void setExpiredDate(String expiredDate) {
		this.expiredDate = expiredDate;
	}
	public String getCvv2() {
		return cvv2;
	}
	public void setCvv2(String cvv2) {
		this.cvv2 = cvv2;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustIdNo() {
		return custIdNo;
	}
	public void setCustIdNo(String custIdNo) {
		this.custIdNo = custIdNo;
	}
	public String getCustIdType() {
		return custIdType;
	}
	public void setCustIdType(String custIdType) {
		this.custIdType = custIdType;
	}
	public String getSaveCustFlag() {
		return saveCustFlag;
	}
	public void setSaveCustFlag(String saveCustFlag) {
		this.saveCustFlag = saveCustFlag;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getPhoneVerCode() {
		return phoneVerCode;
	}
	public void setPhoneVerCode(String phoneVerCode) {
		this.phoneVerCode = phoneVerCode;
	}
	public String getPhoneToken() {
		return phoneToken;
	}
	public void setPhoneToken(String phoneToken) {
		this.phoneToken = phoneToken;
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

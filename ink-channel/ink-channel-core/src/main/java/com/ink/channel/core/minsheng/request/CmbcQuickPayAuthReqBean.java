package com.ink.channel.core.minsheng.request;

public class CmbcQuickPayAuthReqBean extends CmbcBodyBean{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5164640306794751828L;
	private String merOrderId;
	private String custId;// 客户号
	private String custName;// 持卡人姓名
	private String custIdNo;// 持卡人证件号
	private String custIdType;// 持卡人证件类型
	private String cardNo;// 卡号
	private String storableCardNo;// 短卡号
	private String bankNo; // 支付银行号
	private String expiredDate;// 卡有效期
	private String cvv2;// CVV2
	private String amount;// 充值金额(默认充值5元)
	private String phoneNo;// 手机号码
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
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getStorableCardNo() {
		return storableCardNo;
	}
	public void setStorableCardNo(String storableCardNo) {
		this.storableCardNo = storableCardNo;
	}
	public String getBankNo() {
		return bankNo;
	}
	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
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
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
}

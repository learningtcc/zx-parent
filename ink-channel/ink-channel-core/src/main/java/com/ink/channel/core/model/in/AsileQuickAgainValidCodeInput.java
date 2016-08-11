package com.ink.channel.core.model.in;

import java.io.Serializable;

public class AsileQuickAgainValidCodeInput implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6550455513421398028L;

	private String orderNo;// 订单号
	
	private String bankShort;// 银行编码（英文缩写）
	
	private String amount;// 交易金额
	
	private String cardNo;// 卡号
	
	private String userName;// 持卡人姓名
	
	private String idType;// 证件类型
	
	private String idNo;// 证件号码
	
	private String phoneNo;// 手机号

	private String identityId;//客户号
	
	private String merchantNo;//商户号
	
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getBankShort() {
		return bankShort;
	}

	public void setBankShort(String bankShort) {
		this.bankShort = bankShort;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getIdentityId() {
		return identityId;
	}

	public void setIdentityId(String identityId) {
		this.identityId = identityId;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AsileQuickAgainValidCodeInput [orderNo=");
		builder.append(orderNo);
		builder.append(", bankShort=");
		builder.append(bankShort);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", cardNo=");
		builder.append(cardNo);
		builder.append(", userName=");
		builder.append(userName);
		builder.append(", idType=");
		builder.append(idType);
		builder.append(", idNo=");
		builder.append(idNo);
		builder.append(", phoneNo=");
		builder.append(phoneNo);
		builder.append(", identityId=");
		builder.append(identityId);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append("]");
		return builder.toString();
	}
}

package com.ink.channel.api.model.in;


import java.io.Serializable;

import javax.validation.constraints.Digits;

import org.hibernate.validator.constraints.NotEmpty;

public class AuthenBindCardInput implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7061967461630020071L;
	@NotEmpty
	private String orderNo;//外部系统订单号
	@NotEmpty
	private String accountName;// 姓名
	@NotEmpty
	@Digits(fraction = 0, integer = 30)
	private String accountNo;// 银行账号
	@NotEmpty
	private String channelId;//通道id
	@NotEmpty
	private String certNo;// 证件号
	@NotEmpty
	private String certType;// 开户证件类型
	@NotEmpty
	private String bankShort;// 银行名称
	@NotEmpty
	private String phoneNo;//手机号
	@NotEmpty
	private String merchantNo;//商户号
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getCertNo() {
		return certNo;
	}
	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}
	public String getCertType() {
		return certType;
	}
	public void setCertType(String certType) {
		this.certType = certType;
	}
	public String getBankShort() {
		return bankShort;
	}
	public void setBankShort(String bankShort) {
		this.bankShort = bankShort;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
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
		builder.append("AuthenBindCardInput [orderNo=");
		builder.append(orderNo);
		builder.append(", accountName=");
		builder.append(accountName);
		builder.append(", accountNo=");
		builder.append(accountNo);
		builder.append(", channelId=");
		builder.append(channelId);
		builder.append(", certNo=");
		builder.append(certNo);
		builder.append(", certType=");
		builder.append(certType);
		builder.append(", bankShort=");
		builder.append(bankShort);
		builder.append(", phoneNo=");
		builder.append(phoneNo);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append("]");
		return builder.toString();
	}
	
}

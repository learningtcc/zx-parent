package com.ink.channel.api.model.in;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class PayAccountIn implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1657421382725444756L;
	@NotNull
	private BigDecimal amount;//金额
	@NotEmpty
	private String orderNo;//外部系统订单号
	@NotEmpty
	private String accountName;// 姓名
	@NotEmpty
	@Digits(fraction = 0, integer = 30)
	private String accountNo;// 银行账号

	private String phoneNo;//手机号
	@NotEmpty
	private String certNo;// 证件号
	@NotEmpty
	private String certType;// 开户证件类型
	@NotEmpty
	private String bankShort;// 银行简码
	@NotEmpty
	private String callbackurl;//回调地址
	
	private Date tradeDate;//交易时间
	
	@NotEmpty
	private String merchantNo;//商户号
	
	private String channelId;//通道id

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

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

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
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

	public String getCallbackurl() {
		return callbackurl;
	}

	public void setCallbackurl(String callbackurl) {
		this.callbackurl = callbackurl;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public Date getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}
}

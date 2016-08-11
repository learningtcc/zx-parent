package com.ink.channel.core.model.in;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AsilePayAccountIn implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1657421382725444756L;
	
	private BigDecimal amount;//金额
	
	private String orderNo;//外部系统订单号
	
	private String accountName;// 姓名
	
	private String accountNo;// 银行账号

	private String phoneNo;//手机号
	
	private String certNo;// 证件号
	
	private String certType;// 开户证件类型
	
	private String bankShort;// 银行简码
	
	private Date tradeDate;//交易时间
	
	private String merchantNo;//商户号

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

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public Date getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}
}

package com.ink.channel.core.model.in;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AsileQuickAgainPayInput implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2262316351029988305L;
	
	private String orderNo;// 订单号
	
	private String orgTranFlow;//流水号
	
	private String bankShort;// 银行编码（英文缩写）
	
	private BigDecimal amount;// 支付金额

	private String cardNo;// 卡号
	
	private String phoneNo;// 银行卡预留手机号
	
	private String token;// 令牌
	
	private String validCode;// 验证码
	
	private String userName;// 姓名
	
	private String idNo;// 证件号码
	
	private String idType;// 证件号码
	
	private Date clientDate;//订单发送日期

	private String identityId;//客户号
	
	private String merchantNo;//商户号
	
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getValidCode() {
		return validCode;
	}

	public void setValidCode(String validCode) {
		this.validCode = validCode;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
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

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getBankShort() {
		return bankShort;
	}

	public void setBankShort(String bankShort) {
		this.bankShort = bankShort;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getClientDate() {
		return clientDate;
	}

	public void setClientDate(Date clientDate) {
		this.clientDate = clientDate;
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

	public String getOrgTranFlow() {
		return orgTranFlow;
	}

	public void setOrgTranFlow(String orgTranFlow) {
		this.orgTranFlow = orgTranFlow;
	}
}

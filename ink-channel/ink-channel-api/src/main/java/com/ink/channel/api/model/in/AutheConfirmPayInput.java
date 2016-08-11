package com.ink.channel.api.model.in;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;

public class AutheConfirmPayInput implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 900741461074912145L;
	@NotEmpty
	private String orderNo;// 订单号
	@NotEmpty
	private String bankShort;// 银行编码（英文缩写）
	@NotEmpty
	private BigDecimal amount;// 支付金额
	@NotEmpty
	private String cardNo;// 卡号
	@NotEmpty
	private String phoneNo;// 手机号
	
	private String token;// 令牌
	@NotEmpty
	private String validCode;// 验证码
	@NotEmpty
	private String channelId;// 渠道号
	@NotEmpty
	private String userName;// 姓名
	@NotEmpty
	private String idNo;// 证件号码
	@NotEmpty
	private String idType;// 证件号码
	@NotEmpty
	private Date clientDate;//订单发送日期
	@NotEmpty
    private String identityId;//客户号
	@NotEmpty
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
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getValidCode() {
		return validCode;
	}
	public void setValidCode(String validCode) {
		this.validCode = validCode;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
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
}

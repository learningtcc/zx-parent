package com.ink.channel.api.model.in;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class AutheConfirmBindCardInput implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7366388231968323050L;
	@NotEmpty
	private String orderNo;// 订单号
	@NotEmpty
	private String validCode;// 验证码
	@NotNull
	private BigDecimal amount;// 支付金额
	@NotEmpty
	@Digits(fraction = 0, integer = 30)
	private String cardNo;// 卡号
	@NotEmpty
	private String userName;// 姓名
	@NotEmpty
	private String idNo;// 证件号码
	@NotEmpty
	private String idType;// 证件号码
	@NotEmpty
	@Digits(fraction = 0, integer = 11)
	private String phoneNo;// 银行卡预留手机号
	@NotEmpty
	private String bankShort;// 银行编码（英文缩写）
	@NotEmpty
	private String channelId;// 渠道号
	@NotEmpty
	private String token;// 令牌
	@NotEmpty
	private Date clientDate;//订单发送日期
	@NotEmpty
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
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
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
	public String getMerchantNo() {
		return merchantNo;
	}
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}
}

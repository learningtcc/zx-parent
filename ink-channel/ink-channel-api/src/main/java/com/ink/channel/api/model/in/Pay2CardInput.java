package com.ink.channel.api.model.in;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
/**
 * 代付输入参数类
 * @author huohb
 *
 */
public class Pay2CardInput implements Serializable {

	private static final long serialVersionUID = -8520953159394555821L;
	@NotNull
	private BigDecimal amount;// 代付金额
	@NotEmpty
	private String orderNo;// 订单号
	@NotEmpty
	@Digits(fraction = 0, integer = 30)
	private String cardNo;// 卡号
	@NotEmpty
	private String identityId;// 渠道开户标识
	@NotEmpty
	private String idType;// 证件类型
	@NotEmpty
	private String idNo;// 证件号
	@NotEmpty
	private String userName;// 持卡人姓名
	@NotEmpty
	private String bankShort;// 银行英文缩写
	@NotEmpty
	private String channelId;// 渠道号
	@NotEmpty
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

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getIdentityId() {
		return identityId;
	}

	public void setIdentityId(String identityId) {
		this.identityId = identityId;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Pay2CardInput [amount=");
		builder.append(amount);
		builder.append(", orderNo=");
		builder.append(orderNo);
		builder.append(", cardNo=");
		builder.append(cardNo);
		builder.append(", identityId=");
		builder.append(identityId);
		builder.append(", idType=");
		builder.append(idType);
		builder.append(", idNo=");
		builder.append(idNo);
		builder.append(", userName=");
		builder.append(userName);
		builder.append(", bankShort=");
		builder.append(bankShort);
		builder.append(", channelId=");
		builder.append(channelId);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append("]");
		return builder.toString();
	}
}

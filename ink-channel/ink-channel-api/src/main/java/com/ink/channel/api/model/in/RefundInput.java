package com.ink.channel.api.model.in;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
/**
 * 退款输入参数
 * @author huohb
 *
 */
public class RefundInput implements Serializable {

	private static final long serialVersionUID = 4489886491922475882L;
	@NotNull
	private BigDecimal amount;// 交易金额
	@NotEmpty
	private String entryTime;// 商户端交易时间
	@NotEmpty
	private String orderNo;// 商户订单号
	@NotEmpty
	private String origRefNumber;// 原订单号
	@NotEmpty
	private String channelId;// 渠道号
	@NotEmpty
	private String merchantNo;//商户号
	
	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(String entryTime) {
		this.entryTime = entryTime;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrigRefNumber() {
		return origRefNumber;
	}

	public void setOrigRefNumber(String origRefNumber) {
		this.origRefNumber = origRefNumber;
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
		builder.append("RefundInput [amount=");
		builder.append(amount);
		builder.append(", entryTime=");
		builder.append(entryTime);
		builder.append(", orderNo=");
		builder.append(orderNo);
		builder.append(", origRefNumber=");
		builder.append(origRefNumber);
		builder.append(", channelId=");
		builder.append(channelId);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append("]");
		return builder.toString();
	}
}

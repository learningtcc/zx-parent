package com.ink.channel.api.model.in;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class AuthenPayInput implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7808699368938737060L;
	@NotNull
	private BigDecimal amount;//金额
	@NotEmpty
	private String orderNo;//外部系统订单号
	@NotEmpty
	private String identityid;//用户标识    最长50位，商户生成的用户唯一标识
	@NotEmpty
	private String channelId;//通道id
	@NotNull
	private Date tradeDate;//交易时间
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
	public String getIdentityid() {
		return identityid;
	}
	public void setIdentityid(String identityid) {
		this.identityid = identityid;
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
	public String getMerchantNo() {
		return merchantNo;
	}
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AuthenPayInput [amount=");
		builder.append(amount);
		builder.append(", orderNo=");
		builder.append(orderNo);
		builder.append(", identityid=");
		builder.append(identityid);
		builder.append(", channelId=");
		builder.append(channelId);
		builder.append(", tradeDate=");
		builder.append(tradeDate);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append("]");
		return builder.toString();
	}
	
}

package com.ink.channel.api.model.in;

import java.io.Serializable;

import javax.validation.constraints.Digits;

import org.hibernate.validator.constraints.NotEmpty;

public class OrderCancelIn implements Serializable{

	private static final long serialVersionUID = -7207271959319766664L;
	@NotEmpty
	private String orderid;//订单号
	@NotEmpty
	private String entryTime;//交易时间
	@NotEmpty
	private String orgTranFlow;//流水号
	@NotEmpty
	@Digits(fraction = 2, integer = 14)
	private String amount;//交易金额
	@NotEmpty
	private String channelId;//通道Id
	@NotEmpty
	private String merchantNo;//商户号
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getEntryTime() {
		return entryTime;
	}
	public void setEntryTime(String entryTime) {
		this.entryTime = entryTime;
	}
	public String getOrgTranFlow() {
		return orgTranFlow;
	}
	public void setOrgTranFlow(String orgTranFlow) {
		this.orgTranFlow = orgTranFlow;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
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
		builder.append("OrderCancelIn [orderid=");
		builder.append(orderid);
		builder.append(", entryTime=");
		builder.append(entryTime);
		builder.append(", orgTranFlow=");
		builder.append(orgTranFlow);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", channelId=");
		builder.append(channelId);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append("]");
		return builder.toString();
	}
}

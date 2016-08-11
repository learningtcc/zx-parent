package com.ink.channel.core.model.in;

import java.io.Serializable;
import java.util.Date;

public class AsileQueryPayAccountIn implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1430031284787979663L;
	
	private String orderNo;//订单号
	
	private Date tradeDate;//交易时间
	
	private String orgTranFlow;//流水号
	
	private String channelId;//通道Id
	
	private String merchantNo;//商户号
	
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Date getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}

	public String getOrgTranFlow() {
		return orgTranFlow;
	}

	public void setOrgTranFlow(String orgTranFlow) {
		this.orgTranFlow = orgTranFlow;
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
		builder.append("AsileQueryPayAccountIn [orderNo=");
		builder.append(orderNo);
		builder.append(", tradeDate=");
		builder.append(tradeDate);
		builder.append(", orgTranFlow=");
		builder.append(orgTranFlow);
		builder.append(", channelId=");
		builder.append(channelId);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append("]");
		return builder.toString();
	}
}

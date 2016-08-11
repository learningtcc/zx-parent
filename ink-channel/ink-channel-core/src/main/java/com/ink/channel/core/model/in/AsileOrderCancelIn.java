package com.ink.channel.core.model.in;

import java.io.Serializable;

public class AsileOrderCancelIn implements Serializable{

	private static final long serialVersionUID = -7207271959319766664L;
	
	private String orderid;//订单号
	
	private String entryTime;//交易时间
	
	private String orgTranFlow;//流水号
	
	private String amount;//交易金额
	
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
	public String getMerchantNo() {
		return merchantNo;
	}
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}
}

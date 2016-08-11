package com.ink.channel.core.model.out;

import java.io.Serializable;

public class AsilePayAccountOut implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1488263700810644560L;
	
	private String resCode;// 响应码
	
	private String resMsg;// 响应描述
	
	private String orderNo;//传入的原订单号
	
	private String orgTranFlow;//交易响应流水号
	
	private String orderStatus;//订单状态
	
	private String amount;//成功金额
	
	public String getResCode() {
		return resCode;
	}
	public void setResCode(String resCode) {
		this.resCode = resCode;
	}
	public String getResMsg() {
		return resMsg;
	}
	public void setResMsg(String resMsg) {
		this.resMsg = resMsg;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getOrgTranFlow() {
		return orgTranFlow;
	}
	public void setOrgTranFlow(String orgTranFlow) {
		this.orgTranFlow = orgTranFlow;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
}

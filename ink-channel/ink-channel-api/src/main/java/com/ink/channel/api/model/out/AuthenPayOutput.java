package com.ink.channel.api.model.out;

import java.io.Serializable;

public class AuthenPayOutput implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -176992772270965183L;
	private String resCode;
    private String resMsg;
    private String amount;//金额
    private String orderNo;//传入的原订单号
	private String orgTranFlow;//（交易响应流水号）
	private String orderStatus;//订单状态
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
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
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
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AuthenPayOutput [resCode=");
		builder.append(resCode);
		builder.append(", resMsg=");
		builder.append(resMsg);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", orderNo=");
		builder.append(orderNo);
		builder.append(", orgTranFlow=");
		builder.append(orgTranFlow);
		builder.append(", orderStatus=");
		builder.append(orderStatus);
		builder.append("]");
		return builder.toString();
	}
}

package com.ink.channel.api.model.out;

import java.io.Serializable;
/**
 * 代付输出参数类
 * @author huohb
 *
 */
public class Pay2CardOutput implements Serializable {

	private static final long serialVersionUID = -1618510924712065293L;
	
	private String resCode;// 返回码
	
	private String resMsg;// 返回描述

	private String orgTranFlow;//交易响应流水号
	
	private String orderNo;//交易订单号
	
	private String amount;//交易金额
	
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

	public String getOrgTranFlow() {
		return orgTranFlow;
	}

	public void setOrgTranFlow(String orgTranFlow) {
		this.orgTranFlow = orgTranFlow;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
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
		builder.append("Pay2CardOutput [resCode=");
		builder.append(resCode);
		builder.append(", resMsg=");
		builder.append(resMsg);
		builder.append(", orgTranFlow=");
		builder.append(orgTranFlow);
		builder.append(", orderNo=");
		builder.append(orderNo);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", orderStatus=");
		builder.append(orderStatus);
		builder.append("]");
		return builder.toString();
	}
}

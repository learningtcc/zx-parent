package com.ink.channel.core.model.out;

import java.io.Serializable;
/**
 * 快捷支付输出参数
 * @author huohb
 *
 */
public class AsileQuickPayOutput implements Serializable{
	
	private static final long serialVersionUID = -4411242740499307238L;
	
	private String resCode;// 返回码
	
	private String resMsg;// 返回信息
	
	private String orderNo;//传入的原订单号
	
	private String orgTranFlow;//（交易响应流水号）
	
	private String identityId;//绑定id
	
	private String resStatus;//响应状态
	
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

	public String getIdentityId() {
		return identityId;
	}

	public void setIdentityId(String identityId) {
		this.identityId = identityId;
	}

	public String getResStatus() {
		return resStatus;
	}

	public void setResStatus(String resStatus) {
		this.resStatus = resStatus;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AsileQuickPayOutput [resCode=");
		builder.append(resCode);
		builder.append(", resMsg=");
		builder.append(resMsg);
		builder.append(", orderNo=");
		builder.append(orderNo);
		builder.append(", orgTranFlow=");
		builder.append(orgTranFlow);
		builder.append(", identityId=");
		builder.append(identityId);
		builder.append(", resStatus=");
		builder.append(resStatus);
		builder.append(", amount=");
		builder.append(amount);
		builder.append("]");
		return builder.toString();
	}
}

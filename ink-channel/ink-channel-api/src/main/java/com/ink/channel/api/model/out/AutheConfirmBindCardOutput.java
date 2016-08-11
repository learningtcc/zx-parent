package com.ink.channel.api.model.out;

import java.io.Serializable;

public class AutheConfirmBindCardOutput implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8716321456197545587L;
	
	private String resCode;// 返回码

	private String resMsg;// 返回信息
	
	private String orderNo;//传入的原订单号
	
	private String orgTranFlow;//（交易响应流水号）
	
    private String identityId;

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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AutheConfirmBindCardOutput [resCode=");
		builder.append(resCode);
		builder.append(", resMsg=");
		builder.append(resMsg);
		builder.append(", orderNo=");
		builder.append(orderNo);
		builder.append(", orgTranFlow=");
		builder.append(orgTranFlow);
		builder.append(", identityId=");
		builder.append(identityId);
		builder.append("]");
		return builder.toString();
	}
}

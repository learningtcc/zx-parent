package com.ink.channel.api.model.out;

import java.io.Serializable;

public class AutheConfirmPayOutput implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1912671666627395332L;
	private String resCode;// 返回码

	private String resMsg;// 返回信息
	
	private String orderNo;//传入的原订单号
	
	private String orgTranFlow;//（交易响应流水号）
	
    private String identityId;
    
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

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
}

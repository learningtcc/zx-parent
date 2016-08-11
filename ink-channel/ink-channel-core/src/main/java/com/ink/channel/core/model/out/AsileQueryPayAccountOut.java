package com.ink.channel.core.model.out;

import java.io.Serializable;

public class AsileQueryPayAccountOut implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7036721987941808818L;
	
	private String resCode;//响应码
	
    private String resMsg;//响应信息
    
    private String amount;//交易金额
    
    private String orderNo;//订单号
    
    private String orgTranFlow;//流水号
    
    private String tradeDate;//交易时间
	
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

	public String getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	
}

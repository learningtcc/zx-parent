package com.ink.channel.core.model.in;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 退款输入参数类
 * @author huohb
 *
 */
public class AsileRefundInput implements Serializable{

	private static final long serialVersionUID = 1539555450645195900L;
	
	private BigDecimal amount;// 交易金额
	
	private String entryTime;// 商户端交易时间
	
	private String orderNo;// 商户订单号

	private String origRefNumber;// 原订单号

	private String merchantNo;//商户号
	
	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(String entryTime) {
		this.entryTime = entryTime;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrigRefNumber() {
		return origRefNumber;
	}

	public void setOrigRefNumber(String origRefNumber) {
		this.origRefNumber = origRefNumber;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}
}

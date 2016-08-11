package com.ink.channel.core.model.in;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AsileAuthenPayInput implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7299066561224292217L;
	private BigDecimal amount;//金额
	private String orderNo;//外部系统订单号
	private String identityid;//用户标识    最长50位，商户生成的用户唯一标识
	private Date tradeDate;
	private String merchantNo;//商户号
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getIdentityid() {
		return identityid;
	}
	public void setIdentityid(String identityid) {
		this.identityid = identityid;
	}
	public Date getTradeDate() {
		return tradeDate;
	}
	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}
	public String getMerchantNo() {
		return merchantNo;
	}
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}
}

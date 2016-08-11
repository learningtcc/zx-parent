package com.ink.user.api.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class GoldGrantBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 502981378187594650L;
	@NotNull
	@Length(max = 64)
	private String oriId;
	// 客户编号
	@NotNull
	@Length(max = 40)
	private String custId;
	// 平台用户id
	@Length(max = 24)
	private String uid;
	@NotNull
	@Length(max = 64)
	private String ordId;
	// 订单日期(YYYYMMDD)
	@NotNull
	@Length(max = 14)
	private String tradeDate;
	@NotNull
	@Length(max = 16)
	private String amt;
	// 用于充值的子账户种类 0001-个人现金账户
	@Length(max = 4)
	private String accountType;

	public String getOriId() {
		return oriId;
	}

	public void setOriId(String oriId) {
		this.oriId = oriId;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getOrdId() {
		return ordId;
	}

	public void setOrdId(String ordId) {
		this.ordId = ordId;
	}

	public String getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}

	public String getAmt() {
		return amt;
	}

	public void setAmt(String amt) {
		this.amt = amt;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GoldRecoveryBean [oriId=");
		builder.append(oriId);
		builder.append(", custId=");
		builder.append(custId);
		builder.append(", uid=");
		builder.append(uid);
		builder.append(", ordId=");
		builder.append(ordId);
		builder.append(", tradeDate=");
		builder.append(tradeDate);
		builder.append(", amt=");
		builder.append(amt);
		builder.append("]");
		return builder.toString();
	}

}

package com.ink.user.api.model.in;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class ModifyUserIdInfoInput implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1634949460119880119L;

	// 交易代码
	@NotNull
	@Length(max = 8)
	private String txnCode;
	// 商户编号（托管商户编号）
	@NotNull
	@Length(max = 24)
	private String mchId;
	// 客户编号
	@NotNull
	@Length(max = 40)
	private String custId;
	// 订单日期(YYYYMMDD)
	@NotNull
	@Length(max = 14)
	private String tradeDate;
	// 请求流水号
	@NotNull
	@Length(max = 64)
	private String ordId;
	// 原始证件类型
	@NotNull
	@Length(max = 2)
	private String oriIdType;
	// 原始证件号
	@NotNull
	@Length(max = 18)
	private String oriIdNo;
	// 新证件类型
	@NotNull
	@Length(max = 2)
	private String idType;
	// 新证件号
	@NotNull
	@Length(max = 18)
	private String idNo;
	// 手机号
	@NotNull
	@Length(max = 11)
	private String mblNo;
	public String getTxnCode() {
		return txnCode;
	}
	public void setTxnCode(String txnCode) {
		this.txnCode = txnCode;
	}
	public String getMchId() {
		return mchId;
	}
	public void setMchId(String mchId) {
		this.mchId = mchId;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getTradeDate() {
		return tradeDate;
	}
	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}
	public String getOrdId() {
		return ordId;
	}
	public void setOrdId(String ordId) {
		this.ordId = ordId;
	}
	public String getOriIdType() {
		return oriIdType;
	}
	public void setOriIdType(String oriIdType) {
		this.oriIdType = oriIdType;
	}
	public String getOriIdNo() {
		return oriIdNo;
	}
	public void setOriIdNo(String oriIdNo) {
		this.oriIdNo = oriIdNo;
	}
	public String getIdType() {
		return idType;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public String getMblNo() {
		return mblNo;
	}
	public void setMblNo(String mblNo) {
		this.mblNo = mblNo;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ModifyUserIdInfoInput [txnCode=");
		builder.append(txnCode);
		builder.append(", mchId=");
		builder.append(mchId);
		builder.append(", custId=");
		builder.append(custId);
		builder.append(", tradeDate=");
		builder.append(tradeDate);
		builder.append(", ordId=");
		builder.append(ordId);
		builder.append(", oriIdType=");
		builder.append(oriIdType);
		builder.append(", oriIdNo=");
		builder.append(oriIdNo);
		builder.append(", idType=");
		builder.append(idType);
		builder.append(", idNo=");
		builder.append(idNo);
		builder.append(", mblNo=");
		builder.append(mblNo);
		builder.append("]");
		return builder.toString();
	}

	
}

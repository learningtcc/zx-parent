package com.ink.user.api.model.in;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class MchAcountTransferInput implements Serializable {

	/**
	* @Fields serialVersionUID : TODO
	*/
	private static final long serialVersionUID = -8047933769190493176L;
	// 交易代码
	@NotNull
	@Length(max = 8)
	private String txnCode;
	// 请求流水号
	@NotNull
	@Length(max = 64)
	private String ordId;
	// 商户编号（托管商户编号）
	@NotNull
	@Length(max = 24)
	private String mchId;
	// 商户账户类型
	@NotNull
	@Length(max = 4)
	private String mchAccountType;
	// 客户编号
	@NotNull
	@Length(max = 40)
	private String custId;
	// 客户账户类型
	@NotNull
	@Length(max = 4)
	private String custAccountType;
	// 交易金额
	@NotNull
	@Length(max = 16)
	private String amt;
	// 交易时间
	@NotNull
	@Length(max = 14)
	private String tradeDate;
	// 资金方向
	@NotNull
	@Length(max = 1)
	private String dir;

	public String getTxnCode() {
		return txnCode;
	}

	public void setTxnCode(String txnCode) {
		this.txnCode = txnCode;
	}

	public String getOrdId() {
		return ordId;
	}

	public void setOrdId(String ordId) {
		this.ordId = ordId;
	}

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getMchAccountType() {
		return mchAccountType;
	}

	public void setMchAccountType(String mchAccountType) {
		this.mchAccountType = mchAccountType;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getCustAccountType() {
		return custAccountType;
	}

	public void setCustAccountType(String custAccountType) {
		this.custAccountType = custAccountType;
	}

	public String getAmt() {
		return amt;
	}

	public void setAmt(String amt) {
		this.amt = amt;
	}

	public String getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MchAcountTransferInput [txnCode=");
		builder.append(txnCode);
		builder.append(", ordId=");
		builder.append(ordId);
		builder.append(", mchId=");
		builder.append(mchId);
		builder.append(", mchAccountType=");
		builder.append(mchAccountType);
		builder.append(", custId=");
		builder.append(custId);
		builder.append(", custAccountType=");
		builder.append(custAccountType);
		builder.append(", amt=");
		builder.append(amt);
		builder.append(", tradeDate=");
		builder.append(tradeDate);
		builder.append(", dir=");
		builder.append(dir);
		builder.append("]");
		return builder.toString();
	}

}

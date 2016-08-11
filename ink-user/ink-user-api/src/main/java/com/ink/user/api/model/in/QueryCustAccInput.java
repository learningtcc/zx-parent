package com.ink.user.api.model.in;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/**
 * 
 * @author 作者 :chenbo
 * @version 创建时间：2015年10月20日 下午5:33:07
 * @description 描述：个人账户余额查询
 * 
 */
public class QueryCustAccInput implements Serializable {

	private static final long serialVersionUID = -2472779304438502264L;
	// 交易代码
	@NotNull
	@Length(max = 8)
	private String txnCode;
	// 商户号
	@NotNull
	@Length(max = 24)
	private String mchId;
	// 客户号
	@NotNull
	@Length(max = 40)
	private String custId;
	// 账户类型
	@Length(max = 4)
	private String accountType;
	// 交易时间
	@NotNull
	@Length(max = 16)
	private String tradeDate;
	
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
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getTradeDate() {
		return tradeDate;
	}
	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("QueryCustAccInput [txnCode=");
		builder.append(txnCode);
		builder.append(", mchId=");
		builder.append(mchId);
		builder.append(", custId=");
		builder.append(custId);
		builder.append(", accountType=");
		builder.append(accountType);
		builder.append(", tradeDate=");
		builder.append(tradeDate);
		builder.append("]");
		return builder.toString();
	}
	
}

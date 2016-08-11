package com.ink.user.api.model.in;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/**
 * @Description: 体验金发放
 * @author wanghao^_^
 * @date 2016年6月13日 上午11:25:24
 * @version V1.0
 */
public class ExperienceGoldGrantInput implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -1441962959646685763L;

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
	//请求流水号
	@NotNull
	@Length(max = 64)
	private String ordId;
	// 订单日期(YYYYMMDD)
	@NotNull
	@Length(max = 14)
	private String tradeDate;
	
	// 实际记账金额，以元为单位，精确到小数点后2位。
	@NotNull
	@Length(max = 16)
	private String amt;
	// 用于充值的子账户种类 0001-个人现金账户
	@Length(max = 4)
	private String accountType;
	
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
		builder.append("ExperienceGoldGrantInput [txnCode=");
		builder.append(txnCode);
		builder.append(", mchId=");
		builder.append(mchId);
		builder.append(", custId=");
		builder.append(custId);
		builder.append(", ordId=");
		builder.append(ordId);
		builder.append(", tradeDate=");
		builder.append(tradeDate);
		builder.append(", amt=");
		builder.append(amt);
		builder.append(", accountType=");
		builder.append(accountType);
		builder.append("]");
		return builder.toString();
	}

}

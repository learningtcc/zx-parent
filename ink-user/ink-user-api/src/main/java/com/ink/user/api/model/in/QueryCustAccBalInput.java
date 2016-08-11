package com.ink.user.api.model.in;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 *
 * @author 作者 :chenbo
 * @version 创建时间：2015年10月20日 下午5:33:07
 * @description 描述：个人账户余额查询
 *
 */
public class QueryCustAccBalInput implements Serializable {

	private static final long serialVersionUID = -2472779304438502264L;
	// 交易代码
	@NotNull
	@Length(max = 8)
	private String txnCode;
	// 商户编号（托管商户编号）
	@NotNull
	@Length(max=24)
	private String mchId;
	// 客户编号
	@NotNull
	@Length(max=40)
	private String custId;
	// 账户类型
	@Length(max = 4)
	private String accountType;
	// 订单日期(YYYYMMDDHHMMSS)
	@NotNull
	@Length(max = 14)
	private String tradeDate;
	// 备注
	private String memo;

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

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("QueryCustAccBalInput [txnCode=");
		builder.append(txnCode);
		builder.append(", mchId=");
		builder.append(mchId);
		builder.append(", custId=");
		builder.append(custId);
		builder.append(", accountType=");
		builder.append(accountType);
		builder.append(", tradeDate=");
		builder.append(tradeDate);
		builder.append(", memo=");
		builder.append(memo);
		builder.append("]");
		return builder.toString();
	}

}

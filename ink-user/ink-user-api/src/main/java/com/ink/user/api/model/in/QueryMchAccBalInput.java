package com.ink.user.api.model.in;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/**
 * @Description: 查询商户账户余额
 * @author wanghao^_^
 * @date 2016年6月13日 下午4:10:20
 * @version V1.0
 */
public class QueryMchAccBalInput implements Serializable {

	/**
	* @Fields serialVersionUID : TODO
	*/
	private static final long serialVersionUID = 6689138838781742719L;
	// 交易代码
	@NotNull
	@Length(max = 8)
	private String txnCode;
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
	// 交易时间
	@NotNull
	@Length(max = 14)
	private String tradeDate;

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

	public String getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("QueryMchAccBalInput [txnCode=");
		builder.append(txnCode);
		builder.append(", ordId=");
		builder.append(ordId);
		builder.append(", mchId=");
		builder.append(mchId);
		builder.append(", mchAccountType=");
		builder.append(mchAccountType);
		builder.append(", tradeDate=");
		builder.append(tradeDate);
		builder.append("]");
		return builder.toString();
	}

}

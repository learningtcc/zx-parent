package com.ink.user.api.model.in;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
/**
 * @Description: 交易结果查询
 * @author wanghao^_^
 * @date 2016年6月13日 上午11:03:17
 * @version V1.0
 */
public class QueryTransResultInput implements Serializable{

	/**
	* @Fields serialVersionUID : TODO
	*/
	private static final long serialVersionUID = -8308107877129817011L;
	//交易代码
	@NotNull
	@Length(max = 8)
	private String txnCode;
	//请求流水号
	@NotNull
	@Length(max = 64)
	private String ordId;
	//交易日期
	@NotNull
	@Length(max = 14)
	private String tradeDate;
	//商户
	@NotNull
	@Length(max = 24)
	private String mchId;
	//原交易代码
	@NotNull
	@Length(max = 8)
	private String oriTxnCode;
	//原请求流水号
	@NotNull
	@Length(max = 64)
	private String oriOrdId;
	
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
	public String getTradeDate() {
		return tradeDate;
	}
	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}
	public String getMchId() {
		return mchId;
	}
	public void setMchId(String mchId) {
		this.mchId = mchId;
	}
	public String getOriTxnCode() {
		return oriTxnCode;
	}
	public void setOriTxnCode(String oriTxnCode) {
		this.oriTxnCode = oriTxnCode;
	}
	public String getOriOrdId() {
		return oriOrdId;
	}
	public void setOriOrdId(String oriOrdId) {
		this.oriOrdId = oriOrdId;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("QueryTransResultInput [txnCode=");
		builder.append(txnCode);
		builder.append(", ordId=");
		builder.append(ordId);
		builder.append(", tradeDate=");
		builder.append(tradeDate);
		builder.append(", mchId=");
		builder.append(mchId);
		builder.append(", oriTxnCode=");
		builder.append(oriTxnCode);
		builder.append(", oriOrdId=");
		builder.append(oriOrdId);
		builder.append("]");
		return builder.toString();
	}
	
}

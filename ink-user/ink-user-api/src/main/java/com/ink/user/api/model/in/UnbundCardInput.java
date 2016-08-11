package com.ink.user.api.model.in;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/**
 * 
 * @author 作者 :bo.chen
 * @version 创建时间：2015年10月21日 下午2:33:07
 * @description 描述：个人账户银行卡解绑（ACC38080）
 * 
 */
public class UnbundCardInput implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3208299863849300075L;
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
	// 请求流水号
	@NotNull
	@Length(max = 64)
	private String ordId;
	// 绑卡ID
	@Length(max = 24)
	private String bindCardId;
	// 银行卡号
	@NotNull
	@Length(max = 32)
	private String cardNo;
	// 订单日期(YYYYMMDD)
	@NotNull
	@Length(max = 14)
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

	public String getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}

	public String getBindCardId() {
		return bindCardId;
	}

	public void setBindCardId(String bindCardId) {
		this.bindCardId = bindCardId;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getOrdId() {
		return ordId;
	}

	public void setOrdId(String ordId) {
		this.ordId = ordId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UnbundCardInput [txnCode=");
		builder.append(txnCode);
		builder.append(", mchId=");
		builder.append(mchId);
		builder.append(", custId=");
		builder.append(custId);
		builder.append(", ordId=");
		builder.append(ordId);
		builder.append(", bindCardId=");
		builder.append(bindCardId);
		builder.append(", cardNo=");
		builder.append(cardNo);
		builder.append(", tradeDate=");
		builder.append(tradeDate);
		builder.append("]");
		return builder.toString();
	}

}

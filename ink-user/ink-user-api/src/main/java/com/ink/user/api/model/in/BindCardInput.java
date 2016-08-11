package com.ink.user.api.model.in;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/**
 * 
 * @author 作者 :bo.chen
 * @version 创建时间：2015年10月20日 下午2:33:07
 * @description 描述：个人账户银行卡绑定（ACC38070）
 * 
 */
public class BindCardInput implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3327208167377241951L;
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
	// 交易时间
	@NotNull
	@Length(max = 14)
	private String tradeDate;
	// 客户名称
	@NotNull
	@Length(max = 30)
	private String custName;
	// 证件类型
	@NotNull
	@Length(max = 2)
	private String idType;
	// 证件号
	@NotNull
	@Length(max = 18)
	private String idNo;
	// 银行卡类型
	@NotNull
	@Length(max = 4)
	private String cardType;
	// 银行卡号
	@NotNull
	@Length(max = 32)
	private String cardNo;
	// 银行预留手机号
	@NotNull
	@Length(max = 11)
	private String bankMblNo;
	// 信用卡有效期
	@Length(max = 4)
	private String expDate;
	// 信用卡验证码
	@Length(max = 3)
	private String cvn2;

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

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getBankMblNo() {
		return bankMblNo;
	}

	public void setBankMblNo(String bankMblNo) {
		this.bankMblNo = bankMblNo;
	}

	public String getExpDate() {
		return expDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

	public String getCvn2() {
		return cvn2;
	}

	public void setCvn2(String cvn2) {
		this.cvn2 = cvn2;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
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

	public String getOrdId() {
		return ordId;
	}

	public void setOrdId(String ordId) {
		this.ordId = ordId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BindCardInput [txnCode=");
		builder.append(txnCode);
		builder.append(", mchId=");
		builder.append(mchId);
		builder.append(", custId=");
		builder.append(custId);
		builder.append(", ordId=");
		builder.append(ordId);
		builder.append(", tradeDate=");
		builder.append(tradeDate);
		builder.append(", custName=");
		builder.append(custName);
		builder.append(", idType=");
		builder.append(idType);
		builder.append(", idNo=");
		builder.append(idNo);
		builder.append(", cardType=");
		builder.append(cardType);
		builder.append(", cardNo=");
		builder.append(cardNo);
		builder.append(", bankMblNo=");
		builder.append(bankMblNo);
		builder.append(", expDate=");
		builder.append(expDate);
		builder.append(", cvn2=");
		builder.append(cvn2);
		builder.append("]");
		return builder.toString();
	}

}

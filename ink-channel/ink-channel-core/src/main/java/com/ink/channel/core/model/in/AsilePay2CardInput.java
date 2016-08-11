package com.ink.channel.core.model.in;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 代付输入参数类
 * 
 * @author huohb
 *
 */
public class AsilePay2CardInput implements Serializable {

	private static final long serialVersionUID = -943549966892777634L;
	
	private BigDecimal amount;// 代付金额
	
	private String orderNo;// 订单号
	
	private String cardNo;// 卡号
	
	private String identityId;// 渠道开户标识
	
	private String idType;// 证件类型
	
	private String idNo;// 证件号

	private String userName;// 持卡人姓名

	private String bankShort;// 银行英文缩写

	private String merchantNo;//商户号
	
	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getIdentityId() {
		return identityId;
	}

	public void setIdentityId(String identityId) {
		this.identityId = identityId;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBankShort() {
		return bankShort;
	}

	public void setBankShort(String bankShort) {
		this.bankShort = bankShort;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}
}

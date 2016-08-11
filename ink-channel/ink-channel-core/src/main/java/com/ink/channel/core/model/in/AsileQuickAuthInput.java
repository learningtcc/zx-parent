package com.ink.channel.core.model.in;

import java.io.Serializable;
/**
 * 快捷支付鉴权输入参数类
 * @author huohb
 *
 */
public class AsileQuickAuthInput implements Serializable {

	private static final long serialVersionUID = -4730689162403655257L;
	
	private String orderNo;// 订单号
	
	private String bankShort;// 银行编码（英文缩写）
	
	private String amount;// 交易金额
	
	private String cardNo;// 卡号
	
	private String userName;// 持卡人姓名
	
	private String idType;// 证件类型
	
	private String idNo;// 证件号码
	
	private String phoneNo;// 手机号
	
	private String merchantNo;//商户号
	
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getBankShort() {
		return bankShort;
	}

	public void setBankShort(String bankShort) {
		this.bankShort = bankShort;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}
}

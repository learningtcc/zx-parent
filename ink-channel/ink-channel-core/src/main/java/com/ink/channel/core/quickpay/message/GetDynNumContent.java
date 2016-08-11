package com.ink.channel.core.quickpay.message;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 快钱快捷支付鉴权消息类
 * @author huohb
 *
 */
public class GetDynNumContent {
	
	private String externalRefNumber;// 外部跟踪号
	
	private String cardHolderName;// 持卡人姓名
	
	private String idType;// 证件类型
	
	private String cardHolderId;// 客户身份证号
	
	private String pan;// 卡号
	
	private String bankId;// 银行编码
	
	private String phoneNO;// 手机号
	
	private String amount;// 交易金额
	
	private String merchantId;// 商户号
	
	private String terminalId;// 终端号
	
	private String customerId;// 客户号
	
	private String responseCode;// 返回码
	
	private String storablePan;// 缩略卡号（前6后4）
	
	private String token;// 令牌

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getStorablePan() {
		return storablePan;
	}

	public void setStorablePan(String storablePan) {
		this.storablePan = storablePan;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getExternalRefNumber() {
		return externalRefNumber;
	}

	public void setExternalRefNumber(String externalRefNumber) {
		this.externalRefNumber = externalRefNumber;
	}

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getCardHolderId() {
		return cardHolderId;
	}

	public void setCardHolderId(String cardHolderId) {
		this.cardHolderId = cardHolderId;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String getPhoneNO() {
		return phoneNO;
	}

	public void setPhoneNO(String phoneNO) {
		this.phoneNO = phoneNO;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}

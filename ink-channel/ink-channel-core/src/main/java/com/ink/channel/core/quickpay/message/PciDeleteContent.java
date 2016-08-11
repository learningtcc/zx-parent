package com.ink.channel.core.quickpay.message;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import org.apache.commons.lang.builder.ToStringBuilder;

@XmlAccessorType(XmlAccessType.FIELD)
public class PciDeleteContent {

	@XmlElement
	private String merchantId;//商户号
	@XmlElement
	private String customerId;//用户唯一标示
	@XmlElement
	private String storablePan;//缩略卡号
	@XmlElement
	private String pan;//银行卡号
	@XmlElement
	private String bankId;//银行英文缩写
	@XmlElement
	private String responseCode;//返回码
	@XmlElement
	private String responseTextMessage;//返回信息

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getStorablePan() {
		return storablePan;
	}

	public void setStorablePan(String storablePan) {
		this.storablePan = storablePan;
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

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseTextMessage() {
		return responseTextMessage;
	}

	public void setResponseTextMessage(String responseTextMessage) {
		this.responseTextMessage = responseTextMessage;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}

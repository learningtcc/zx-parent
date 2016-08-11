package com.ink.channel.core.quickpay.message;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import org.apache.commons.lang.builder.ToStringBuilder;
/**
 * 快钱签约认证消息类
 * @author huohb
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class IndAuthDynVerifyContent {

	@XmlElement
	private String merchantId;// 商户号

	@XmlElement
	private String customerId;// 客户号

	@XmlElement
	private String externalRefNumber;// 外部跟踪号

	@XmlElement
	private String pan;// 卡号

	@XmlElement
	private String phoneNO;// 手机号

	@XmlElement
	private String validCode;// 验证码

	@XmlElement
	private String token;// 令牌

	@XmlElement
	private String storablePan;// 缩略卡号（卡号前六位+卡号后四位）

	@XmlElement
	private String responseCode;// 返回码

	@XmlElement
	private String responseTextMessage;// 返回信息

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

	public String getExternalRefNumber() {
		return externalRefNumber;
	}

	public void setExternalRefNumber(String externalRefNumber) {
		this.externalRefNumber = externalRefNumber;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getPhoneNO() {
		return phoneNO;
	}

	public void setPhoneNO(String phoneNO) {
		this.phoneNO = phoneNO;
	}

	public String getValidCode() {
		return validCode;
	}

	public void setValidCode(String validCode) {
		this.validCode = validCode;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getStorablePan() {
		return storablePan;
	}

	public void setStorablePan(String storablePan) {
		this.storablePan = storablePan;
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

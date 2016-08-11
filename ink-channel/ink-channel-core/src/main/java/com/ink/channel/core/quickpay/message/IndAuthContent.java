package com.ink.channel.core.quickpay.message;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
/**
 * 快钱签约申请消息类
 * @author huohb
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="indAuthContent")
public class IndAuthContent {

	@XmlElement
	private String merchantId;// 商户号
	@XmlElement
	private String terminalId; // 终端号，快钱提供
	@XmlElement
	private String customerId;// 客户号，用户id
	@XmlElement
	private String externalRefNumber;// 外部跟踪编号（externalRefNumber） 表的id
	@XmlElement
	private String storablePan;// 缩略卡号
	@XmlElement
	private String token;// 令牌信息
	@XmlElement
	private String responseCode;// 应答码
	@XmlElement
	private String responseTextMessage;// 应答码文本消息
	@XmlElement
	private String pan;// 此为加密过的信用卡的卡号，是快捷交易鉴权的交易要素之一。
	@XmlElement
	private String cardHolderName;// 客户姓名
	@XmlElement
	private String idType;// 证件类型
	@XmlElement
	private String cardHolderId;// 持卡人身份证号
	@XmlElement
	private String expiredDate;// 卡效期
	@XmlElement
	private String cvv2;// 卡校验码
	@XmlElement
	private String phoneNO;// 手机号
	@XmlElement
	private String validCode;// 手机验证码

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

	public String getExternalRefNumber() {
		return externalRefNumber;
	}

	public void setExternalRefNumber(String externalRefNumber) {
		this.externalRefNumber = externalRefNumber;
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

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
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

	public String getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(String expiredDate) {
		this.expiredDate = expiredDate;
	}

	public String getCvv2() {
		return cvv2;
	}

	public void setCvv2(String cvv2) {
		this.cvv2 = cvv2;
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

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}

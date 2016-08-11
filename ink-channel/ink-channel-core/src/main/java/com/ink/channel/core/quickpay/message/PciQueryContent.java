package com.ink.channel.core.quickpay.message;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import org.apache.commons.lang.builder.ToStringBuilder;

@XmlAccessorType(XmlAccessType.FIELD)
public class PciQueryContent {

	@XmlElement
	private String merchantId;//商户号
	
	@XmlElement
	private String customerId;//客户号
	
	@XmlElement
	private String cardType;//卡类型 0001信用卡  0002 借记卡
	
	@XmlElement
	private String responseCode;//响应类型
	
	@XmlElementWrapper(name="pciInfos")
	@XmlElement(name="pciInfo")
	private List<PciInfo> pciInfo;//

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

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public List<PciInfo> getPciInfo() {
		return pciInfo;
	}

	public void setPciInfo(List<PciInfo> pciInfo) {
		this.pciInfo = pciInfo;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	
	
	
}

package com.ink.channel.core.quickpay.message;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import org.apache.commons.lang.builder.ToStringBuilder;

@XmlAccessorType(XmlAccessType.FIELD)
public class PciInfo {

	@XmlElement
	private String bankId;// 银行的英文缩写
	@XmlElement
	private String storablePan;// 缩略卡号
	@XmlElement
	private String shortPhoneNo;//缩略手机号
	@XmlElement
	private String phoneNO;// 手机号

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String getStorablePan() {
		return storablePan;
	}

	public void setStorablePan(String storablePan) {
		this.storablePan = storablePan;
	}

	public String getShortPhoneNo() {
		return shortPhoneNo;
	}

	public void setShortPhoneNo(String shortPhoneNo) {
		this.shortPhoneNo = shortPhoneNo;
	}

	public String getPhoneNO() {
		return phoneNO;
	}

	public void setPhoneNO(String phoneNO) {
		this.phoneNO = phoneNO;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}

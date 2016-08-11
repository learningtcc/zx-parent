package com.ink.channel.core.quickpay.message;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 快钱卡信息查询返回消息类
 * 
 * @author huohb
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class CardInfoContent {

	private String cardNo;// 卡号

	@XmlElement
	private String cardOrg;// 卡组织编号
	@XmlElement
	private String cardType;// 卡类型
	@XmlElement
	private String issuer;// 发卡行名称
	@XmlElement
	private String validFlag;// 快钱 VPOS-CNP 是否支持 1、快钱支持，2、快钱不支持

	/*
	 * 3位位图，表示是否需要提供相应的信息进行验证 第 1 位:表示需要输入CVV2， 第 2 位:表示要输入持卡人姓名 第 3
	 * 位:表示要输入持卡人身份证号 如果只需要 CVV2 则该位图为： 001 如果只需要持卡人姓名： 010 如果需要身份证号和姓名则为： 011
	 * 如果都需要则为： 111 如果都不需要则为：000
	 */
	@XmlElement
	private String validateType;
	@XmlElement
	private String bankId;// 银行代码
	
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getCardOrg() {
		return cardOrg;
	}
	public void setCardOrg(String cardOrg) {
		this.cardOrg = cardOrg;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getIssuer() {
		return issuer;
	}
	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}
	public String getValidFlag() {
		return validFlag;
	}
	public void setValidFlag(String validFlag) {
		this.validFlag = validFlag;
	}
	public String getValidateType() {
		return validateType;
	}
	public void setValidateType(String validateType) {
		this.validateType = validateType;
	}
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}

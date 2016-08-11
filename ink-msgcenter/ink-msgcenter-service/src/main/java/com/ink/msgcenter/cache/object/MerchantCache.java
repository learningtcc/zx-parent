package com.ink.msgcenter.cache.object;

import java.io.Serializable;

public class MerchantCache implements Serializable {

	private static final long serialVersionUID = -4631989949876374093L;
	private Long id;
	
	/**
	 * 商户编号
	 */
	private String merctSn;
	/**
	 * 通道类型
	 */
	private String chnType;
	
	/**
	 * 邮件通道代码，当配置多个通道时以逗号隔开
	 */
	private String mailChnCode;
	
	/**
	 * 短信通道代码，当配置多个通道时以逗号隔开
	 */
	private String smsChnCode;
	
	private String emailNotifyUrl;
	
	private String smsReportUrl;
	
	private String smsUpUrl;

	public String getMerctSn() {
		return merctSn;
	}

	public void setMerctSn(String merctSn) {
		this.merctSn = merctSn;
	}

	public String getChnType() {
		return chnType;
	}

	public void setChnType(String chnType) {
		this.chnType = chnType;
	}

	public String getMailChnCode() {
		return mailChnCode;
	}

	public void setMailChnCode(String mailChnCode) {
		this.mailChnCode = mailChnCode;
	}

	public String getSmsChnCode() {
		return smsChnCode;
	}

	public void setSmsChnCode(String smsChnCode) {
		this.smsChnCode = smsChnCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmailNotifyUrl() {
		return emailNotifyUrl;
	}

	public String getSmsReportUrl() {
		return smsReportUrl;
	}

	public void setSmsReportUrl(String smsReportUrl) {
		this.smsReportUrl = smsReportUrl;
	}

	public void setEmailNotifyUrl(String emailNotifyUrl) {
		this.emailNotifyUrl = emailNotifyUrl;
	}

	public String getSmsUpUrl() {
		return smsUpUrl;
	}

	public void setSmsUpUrl(String smsUpUrl) {
		this.smsUpUrl = smsUpUrl;
	}
}
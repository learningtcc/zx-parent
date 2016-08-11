package com.ink.msgcenter.mq;

import java.io.Serializable;
import java.util.Date;

public class EmailDTO implements Serializable{
	
	private static final long serialVersionUID = -309405152722371743L;

	/**
	 * 商户号
	 */
	private String merctCode;
	
	/**
	 * 模板ID
	 */
	private String tempId;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 业务单号
	 */
	private String bizId;
	
	private String paramJson;
	
	private String msgId;
	
	/**
	 * 业务系统的提交时间
	 */
	private Date submitTime;

	public String getMerctCode() {
		return merctCode;
	}

	public void setMerctCode(String merctCode) {
		this.merctCode = merctCode;
	}

	public String getTempId() {
		return tempId;
	}

	public void setTempId(String tempId) {
		this.tempId = tempId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBizId() {
		return bizId;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
	}

	public String getParamJson() {
		return paramJson;
	}

	public void setParamJson(String paramJson) {
		this.paramJson = paramJson;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public Date getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}

	@Override
	public String toString() {
		return "EmailDTO{" +
				"merctCode='" + merctCode + '\'' +
				", tempId='" + tempId + '\'' +
				", email='" + email + '\'' +
				", bizId='" + bizId + '\'' +
				", paramJson='" + paramJson + '\'' +
				", msgId='" + msgId + '\'' +
				", submitTime=" + submitTime +
				'}';
	}
}
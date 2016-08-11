package com.ink.msgcenter.api.model.input;

public class EmailInput extends MsgInput {

	private static final long serialVersionUID = 461069321435835173L;
	
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
}
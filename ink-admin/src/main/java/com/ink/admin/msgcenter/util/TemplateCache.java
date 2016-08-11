package com.ink.admin.msgcenter.util;

public class TemplateCache {
	
	/**
	 * 商户ID
	 */
	private String merctId;
	
	/**
	 * 商户代码
	 */
	private String merctCode;
	
	/**
	 * 模板名称
	 */
	private String tempName;
	
	/**
	 * 模板内容
	 */
	private String tempContent;
	
	/**
	 * 解析方式
	 */
	private String parseMethod;
	
	/**
	 * 邮件主题
	 */
	private String subject;

	public String getMerctId() {
		return merctId;
	}

	public void setMerctId(String merctId) {
		this.merctId = merctId;
	}

	public String getMerctCode() {
		return merctCode;
	}

	public void setMerctCode(String merctCode) {
		this.merctCode = merctCode;
	}

	public String getTempName() {
		return tempName;
	}

	public void setTempName(String tempName) {
		this.tempName = tempName;
	}

	public String getTempContent() {
		return tempContent;
	}

	public void setTempContent(String tempContent) {
		this.tempContent = tempContent;
	}

	public String getParseMethod() {
		return parseMethod;
	}

	public void setParseMethod(String parseMethod) {
		this.parseMethod = parseMethod;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
}
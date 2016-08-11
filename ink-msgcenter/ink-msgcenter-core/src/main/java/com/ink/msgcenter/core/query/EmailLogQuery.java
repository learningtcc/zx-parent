/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.msgcenter.core.query;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Calendar;

import com.ink.base.BaseQuery;

/**
 * @author  zongtt
 * @version 1.0
 * @since 1.0
 */

public class EmailLogQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    
	/** id */
	private java.lang.Long id;
	/** 商户ID */
	private java.lang.String merctId;
	/** 商户代码 */
	private java.lang.String merctCode;
	/** 通道ID */
	private java.lang.Long chnId;
	/** 通道代码 */
	private java.lang.String chnCode;
	/** 模板ID */
	private java.lang.Long tempId;
	/** 邮箱 */
	private java.lang.String email;
	/** 邮箱Id */
	private java.lang.String emailId;
	/** 邮箱类型 */
	private java.lang.String emailType;
	/** 主题 */
	private java.lang.String subject;
	/** 邮件内容 */
	private java.lang.String mailMsg;
	/** 业务单号 */
	private java.lang.String infoCode;
	/** 发送状态 */
	private java.lang.String sendStatus;
	/** 提交时间 */
	private java.util.Date submitTimeBegin;
	private java.util.Date submitTimeEnd;
	/** 发送时间 */
	private java.util.Date sendTime;
	/** 异常信息 */
	private java.lang.String sendException;
	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.String getMerctId() {
		return this.merctId;
	}
	
	public void setMerctId(java.lang.String value) {
		this.merctId = value;
	}
	
	public java.lang.String getMerctCode() {
		return this.merctCode;
	}
	
	public void setMerctCode(java.lang.String value) {
		this.merctCode = value;
	}
	
	public java.lang.Long getChnId() {
		return this.chnId;
	}
	
	public void setChnId(java.lang.Long value) {
		this.chnId = value;
	}
	
	public java.lang.String getChnCode() {
		return this.chnCode;
	}
	
	public void setChnCode(java.lang.String value) {
		this.chnCode = value;
	}
	
	public java.lang.Long getTempId() {
		return this.tempId;
	}
	
	public void setTempId(java.lang.Long value) {
		this.tempId = value;
	}
	
	public java.lang.String getEmail() {
		return this.email;
	}
	
	public void setEmail(java.lang.String value) {
		this.email = value;
	}
	
	public java.lang.String getSubject() {
		return this.subject;
	}
	
	public void setSubject(java.lang.String value) {
		this.subject = value;
	}
	
	public java.lang.String getMailMsg() {
		return this.mailMsg;
	}
	
	public void setMailMsg(java.lang.String value) {
		this.mailMsg = value;
	}
	
	public java.lang.String getInfoCode() {
		return this.infoCode;
	}
	
	public void setInfoCode(java.lang.String value) {
		this.infoCode = value;
	}
	
	public java.lang.String getSendStatus() {
		return this.sendStatus;
	}
	
	public void setSendStatus(java.lang.String value) {
		this.sendStatus = value;
	}
	
	public java.util.Date getSubmitTimeBegin() {
		return this.submitTimeBegin;
	}
	
	public void setSubmitTimeBegin(java.util.Date value) {
		this.submitTimeBegin = value;
	}	
	
	public java.util.Date getSubmitTimeEnd() {
		return this.submitTimeEnd;
	}
	
	public java.lang.String getEmailId() {
		return emailId;
	}

	public void setEmailId(java.lang.String emailId) {
		this.emailId = emailId;
	}

	public java.lang.String getEmailType() {
		return emailType;
	}

	public void setEmailType(java.lang.String emailType) {
		this.emailType = emailType;
	}
	

	public void setSubmitTimeEnd(java.util.Date value) {
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.submitTimeEnd = calendar.getTime();
		}else {
			this.submitTimeEnd = value;
		}
	}
	
	public java.util.Date getSendTime() {
		return this.sendTime;
	}
	
	public void setSendTime(java.util.Date value) {
		this.sendTime = value;
	}
	
	public java.lang.String getSendException() {
		return this.sendException;
	}
	
	public void setSendException(java.lang.String value) {
		this.sendException = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}


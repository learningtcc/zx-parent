/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.msgcenter.core.po;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.ink.base.util.DateConvertUtils;
import com.ink.base.BaseEntity;

/**
 * @author  zongtt
 * @version 1.0
 * @since 1.0
 */

public class EmailLog extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "邮件日志";
//	
//	public static final String ALIAS_ID = "id";
//	
//	public static final String ALIAS_MERCT_ID = "商户ID";
//	
//	public static final String ALIAS_MERCT_CODE = "商户代码";
//	
//	public static final String ALIAS_CHN_ID = "通道ID";
//	
//	public static final String ALIAS_CHN_CODE = "通道代码";
//	
//	public static final String ALIAS_TEMP_ID = "模板ID";
//	
//	public static final String ALIAS_EMAIL = "邮箱";
//	
//	public static final String ALIAS_SUBJECT = "主题";
//	
//	public static final String ALIAS_MAIL_MSG = "邮件内容";
//	
//	public static final String ALIAS_INFO_CODE = "业务单号";
//	
//	public static final String ALIAS_SEND_STATUS = "发送状态";
//	
//	public static final String ALIAS_SUBMIT_TIME = "提交时间";
//	
//	public static final String ALIAS_SEND_TIME = "发送时间";
//	
//	public static final String ALIAS_SEND_EXCEPTION = "异常信息";
//	
	
	//date formats
	public static final String FORMAT_SUBMIT_TIME = DATE_TIME_FORMAT;
	public static final String FORMAT_SEND_TIME = DATE_TIME_FORMAT;
	
	//columns START
	//id
	private Long id;
	//商户ID
	private String merctId;
	//商户代码
	private String merctCode;
	//通道ID
	private Long chnId;
	//通道代码
	private String chnCode;
	//模板ID
	private Long tempId;
	//邮箱
	private String email;
	//邮箱ID
	private String emailId;
	//邮箱类型
	private String emailType;
	//主题
	private String subject;
	//邮件内容
	private String mailMsg;
	//业务单号
	private String infoCode;
	//发送状态
	private String sendStatus;
	//提交时间
	private java.util.Date submitTime;
	//发送时间
	private java.util.Date sendTime;
	//异常信息
	private String sendException;
	//columns END

	public EmailLog(){
	}

	public EmailLog(
		java.lang.Long id
	){
		this.id = id;
	}

	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.Long getId() {
		return this.id;
	}
	public void setMerctId(java.lang.String value) {
		this.merctId = value;
	}
	
	public java.lang.String getMerctId() {
		return this.merctId;
	}
	public void setMerctCode(java.lang.String value) {
		this.merctCode = value;
	}
	
	public java.lang.String getMerctCode() {
		return this.merctCode;
	}
	public void setChnId(java.lang.Long value) {
		this.chnId = value;
	}
	
	public java.lang.Long getChnId() {
		return this.chnId;
	}
	public void setChnCode(java.lang.String value) {
		this.chnCode = value;
	}
	
	public java.lang.String getChnCode() {
		return this.chnCode;
	}
	public void setTempId(java.lang.Long value) {
		this.tempId = value;
	}
	
	public java.lang.Long getTempId() {
		return this.tempId;
	}
	public void setEmail(java.lang.String value) {
		this.email = value;
	}
	
	public java.lang.String getEmail() {
		return this.email;
	}
	public void setSubject(java.lang.String value) {
		this.subject = value;
	}
	
	public java.lang.String getSubject() {
		return this.subject;
	}
	public void setMailMsg(java.lang.String value) {
		this.mailMsg = value;
	}
	
	public java.lang.String getMailMsg() {
		return this.mailMsg;
	}
	public void setInfoCode(java.lang.String value) {
		this.infoCode = value;
	}
	
	public java.lang.String getInfoCode() {
		return this.infoCode;
	}
	public void setSendStatus(java.lang.String value) {
		this.sendStatus = value;
	}
	
	public java.lang.String getSendStatus() {
		return this.sendStatus;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getEmailType() {
		return emailType;
	}

	public void setEmailType(String emailType) {
		this.emailType = emailType;
	}

	public String getSubmitTimeString() {
		return DateConvertUtils.format(getSubmitTime(), FORMAT_SUBMIT_TIME);
	}
	public void setSubmitTimeString(String value) {
		setSubmitTime(DateConvertUtils.parse(value, FORMAT_SUBMIT_TIME,java.util.Date.class));
	}
	
	public void setSubmitTime(java.util.Date value) {
		this.submitTime = value;
	}
	
	public java.util.Date getSubmitTime() {
		return this.submitTime;
	}
	public String getSendTimeString() {
		return DateConvertUtils.format(getSendTime(), FORMAT_SEND_TIME);
	}
	public void setSendTimeString(String value) {
		setSendTime(DateConvertUtils.parse(value, FORMAT_SEND_TIME,java.util.Date.class));
	}
	
	public void setSendTime(java.util.Date value) {
		this.sendTime = value;
	}
	
	public java.util.Date getSendTime() {
		return this.sendTime;
	}
	public void setSendException(java.lang.String value) {
		this.sendException = value;
	}
	
	public java.lang.String getSendException() {
		return this.sendException;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EmailLog [id=");
		builder.append(id);
		builder.append(", merctId=");
		builder.append(merctId);
		builder.append(", merctCode=");
		builder.append(merctCode);
		builder.append(", chnId=");
		builder.append(chnId);
		builder.append(", chnCode=");
		builder.append(chnCode);
		builder.append(", tempId=");
		builder.append(tempId);
		builder.append(", email=");
		builder.append(email);
		builder.append(", eamilId=");
		builder.append(emailId);
		builder.append(", emailId=");
		builder.append(emailType);
		builder.append(", emailType=");
		builder.append(subject);
		builder.append(", mailMsg=");
		builder.append(mailMsg);
		builder.append(", infoCode=");
		builder.append(infoCode);
		builder.append(", sendStatus=");
		builder.append(sendStatus);
		builder.append(", submitTime=");
		builder.append(submitTime);
		builder.append(", sendTime=");
		builder.append(sendTime);
		builder.append(", sendException=");
		builder.append(sendException);
		builder.append("]");
		return builder.toString();
	}

	public String toString(String separator) {
		StringBuffer sb = new StringBuffer();

			sb.append("id:").append(id).append(separator);
			sb.append("商户ID:").append(merctId).append(separator);
			sb.append("商户代码:").append(merctCode).append(separator);
			sb.append("通道ID:").append(chnId).append(separator);
			sb.append("通道代码:").append(chnCode).append(separator);
			sb.append("模板ID:").append(tempId).append(separator);
			sb.append("邮箱:").append(email).append(separator);
			sb.append("主题:").append(subject).append(separator);
			sb.append("邮件内容:").append(mailMsg).append(separator);
			sb.append("业务单号:").append(infoCode).append(separator);
			sb.append("发送状态:").append(sendStatus).append(separator);
			sb.append("提交时间:").append(getSubmitTimeString()).append(separator);
			sb.append("发送时间:").append(getSendTimeString()).append(separator);
			sb.append("异常信息:").append(sendException).append(separator);
		
		return sb.toString();
	}
	
	
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof EmailLog == false) return false;
		if(this == obj) return true;
		EmailLog other = (EmailLog)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}


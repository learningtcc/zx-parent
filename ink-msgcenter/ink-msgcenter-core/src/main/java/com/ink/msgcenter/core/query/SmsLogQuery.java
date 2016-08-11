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

public class SmsLogQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    
	/** id */
	private java.lang.Long id;
	/** merctId */
	private java.lang.String merctId;
	/** merctCode */
	private java.lang.String merctCode;
	/** chnId */
	private java.lang.Long chnId;
	/** chnCode */
	private java.lang.String chnCode;
	/** tempId */
	private java.lang.Long tempId;
	/** mobile */
	private java.lang.String mobile;
	/** smsMsg */
	private java.lang.String smsMsg;
	/** infoCode */
	private java.lang.String infoCode;
	/** smsCode */
	private java.lang.String smsCode;

	private String extInfo;
	/** priority */
	private java.lang.Integer priority;
	/** smsId */
	private java.lang.String smsId;
	/** taskId */
	private java.lang.String taskId;
	/** sendStatus */
	private java.lang.String sendStatus;
	/** responseCode */
	private java.lang.String responseCode;
	/** smsType */
	private java.lang.String smsType;
	/** reportStatus */
	private java.lang.String reportStatus;
	/** submitTime */
	private java.util.Date submitTimeBegin;
	private java.util.Date submitTimeEnd;
	/** sendTime */
	private java.util.Date sendTime;
	/** fixTime */
	private java.util.Date fixTimeBegin;
	private java.util.Date fixTimeEnd;
	/** reportTime */
	private java.util.Date reportTimeBegin;
	private java.util.Date reportTimeEnd;
	/** sendException */
	private java.lang.String sendException;
	/*状态通知url*/
	private String reportCode;
	/*上行通知*/
	private String upUrl;
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
	
	public java.lang.String getMobile() {
		return this.mobile;
	}
	
	public void setMobile(java.lang.String value) {
		this.mobile = value;
	}
	
	public java.lang.String getSmsMsg() {
		return this.smsMsg;
	}
	
	public void setSmsMsg(java.lang.String value) {
		this.smsMsg = value;
	}
	
	public java.lang.String getInfoCode() {
		return this.infoCode;
	}
	
	public void setInfoCode(java.lang.String value) {
		this.infoCode = value;
	}
	
	public java.lang.String getSmsCode() {
		return this.smsCode;
	}
	
	public void setSmsCode(java.lang.String value) {
		this.smsCode = value;
	}
	
	public java.lang.Integer getPriority() {
		return this.priority;
	}
	
	public void setPriority(java.lang.Integer value) {
		this.priority = value;
	}
	
	public java.lang.String getSmsId() {
		return this.smsId;
	}
	
	public void setSmsId(java.lang.String value) {
		this.smsId = value;
	}
	
	public java.lang.String getTaskId() {
		return this.taskId;
	}
	
	public void setTaskId(java.lang.String value) {
		this.taskId = value;
	}
	
	public java.lang.String getSendStatus() {
		return this.sendStatus;
	}
	
	public void setSendStatus(java.lang.String value) {
		this.sendStatus = value;
	}
	
	public java.lang.String getResponseCode() {
		return this.responseCode;
	}
	
	public void setResponseCode(java.lang.String value) {
		this.responseCode = value;
	}
	
	public java.lang.String getSmsType() {
		return this.smsType;
	}
	
	public void setSmsType(java.lang.String value) {
		this.smsType = value;
	}
	
	public java.lang.String getReportStatus() {
		return this.reportStatus;
	}
	
	public void setReportStatus(java.lang.String value) {
		this.reportStatus = value;
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
	
	public java.util.Date getFixTimeBegin() {
		return this.fixTimeBegin;
	}
	
	public void setFixTimeBegin(java.util.Date value) {
		this.fixTimeBegin = value;
	}	
	
	public java.util.Date getFixTimeEnd() {
		return this.fixTimeEnd;
	}
	
	public void setFixTimeEnd(java.util.Date value) {
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.fixTimeEnd = calendar.getTime();
		}else {
			this.fixTimeEnd = value;
		}
	}
	
	public java.util.Date getReportTimeBegin() {
		return this.reportTimeBegin;
	}
	
	public void setReportTimeBegin(java.util.Date value) {
		this.reportTimeBegin = value;
	}	
	
	public java.util.Date getReportTimeEnd() {
		return this.reportTimeEnd;
	}
	
	public void setReportTimeEnd(java.util.Date value) {
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.reportTimeEnd = calendar.getTime();
		}else {
			this.reportTimeEnd = value;
		}
	}
	
	public java.lang.String getSendException() {
		return this.sendException;
	}
	
	public void setSendException(java.lang.String value) {
		this.sendException = value;
	}

	public String getExtInfo() {
		return extInfo;
	}

	public void setExtInfo(String extInfo) {
		this.extInfo = extInfo;
	}

	public String getReportCode() {
		return reportCode;
	}

	public void setReportCode(String reportCode) {
		this.reportCode = reportCode;
	}

	public String getUpUrl() {
		return upUrl;
	}

	public void setUpUrl(String upUrl) {
		this.upUrl = upUrl;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}


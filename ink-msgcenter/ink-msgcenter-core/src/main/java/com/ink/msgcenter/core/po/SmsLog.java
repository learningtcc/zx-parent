/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.msgcenter.core.po;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.ink.base.util.DateConvertUtils;
import com.ink.base.BaseEntity;

/**
 * @author  zongtt
 * @version 1.0
 * @since 1.0
 */

public class SmsLog extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "短信日志";
//	
//	public static final String ALIAS_ID = "id";
//	
//	public static final String ALIAS_MERCT_ID = "merctId";
//	
//	public static final String ALIAS_MERCT_CODE = "merctCode";
//	
//	public static final String ALIAS_CHN_ID = "chnId";
//	
//	public static final String ALIAS_CHN_CODE = "chnCode";
//	
//	public static final String ALIAS_TEMP_ID = "tempId";
//	
//	public static final String ALIAS_MOBILE = "mobile";
//	
//	public static final String ALIAS_SMS_MSG = "smsMsg";
//	
//	public static final String ALIAS_INFO_CODE = "infoCode";
//	
//	public static final String ALIAS_SMS_CODE = "smsCode";
//	
//	public static final String ALIAS_PRIORITY = "priority";
//	
//	public static final String ALIAS_SMS_ID = "smsId";
//	
//	public static final String ALIAS_TASK_ID = "taskId";
//	
//	public static final String ALIAS_SEND_STATUS = "sendStatus";
//	
//	public static final String ALIAS_RESPONSE_CODE = "responseCode";
//	
//	public static final String ALIAS_SMS_TYPE = "smsType";
//	
//	public static final String ALIAS_REPORT_STATUS = "reportStatus";
//	
//	public static final String ALIAS_SUBMIT_TIME = "submitTime";
//	
//	public static final String ALIAS_SEND_TIME = "sendTime";
//	
//	public static final String ALIAS_FIX_TIME = "fixTime";
//	
//	public static final String ALIAS_REPORT_TIME = "reportTime";
//	
//	public static final String ALIAS_SEND_EXCEPTION = "sendException";
//	
	
	//date formats
	public static final String FORMAT_SUBMIT_TIME = DATE_TIME_FORMAT;
	public static final String FORMAT_SEND_TIME = DATE_TIME_FORMAT;
	public static final String FORMAT_FIX_TIME = DATE_TIME_FORMAT;
	public static final String FORMAT_REPORT_TIME = DATE_TIME_FORMAT;
	
	//columns START
	//id
	private Long id;
	//merctId
	private Long merctId;
	//merctCode
	private String merctCode;
	//chnId
	private Long chnId;
	//chnCode
	private String chnCode;
	//tempId
	private Long tempId;
	//mobile
	private String mobile;
	//smsMsg
	private String smsMsg;
	//infoCode
	private String infoCode;
	//smsCode
	private String smsCode;
	private String extInfo;
	//priority
	private Integer priority;
	//smsId
	private String smsId;
	//taskId
	private String taskId;
	//sendStatus
	private String sendStatus;
	//responseCode
	private String responseCode;
	//smsType
	private String smsType;
	//reportStatus
	private String reportStatus;
	//submitTime
	private java.util.Date submitTime;
	//sendTime
	private java.util.Date sendTime;
	//fixTime
	private java.util.Date fixTime;
	//reportTime
	private java.util.Date reportTime;
	//sendException
	private String sendException;

	/*短信类型 1普通短信 2营销短信*/
	private String sendType;
	/*状态通知url*/
	private String reportUrl;
	/*上行通知*/
	private String upUrl;
	//columns END

	public SmsLog(){
	}

	public SmsLog(
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

	public Long getMerctId() {
		return merctId;
	}

	public void setMerctId(Long merctId) {
		this.merctId = merctId;
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
	public void setMobile(java.lang.String value) {
		this.mobile = value;
	}
	
	public java.lang.String getMobile() {
		return this.mobile;
	}
	public void setSmsMsg(java.lang.String value) {
		this.smsMsg = value;
	}
	
	public java.lang.String getSmsMsg() {
		return this.smsMsg;
	}
	public void setInfoCode(java.lang.String value) {
		this.infoCode = value;
	}
	
	public java.lang.String getInfoCode() {
		return this.infoCode;
	}
	public void setSmsCode(java.lang.String value) {
		this.smsCode = value;
	}
	
	public java.lang.String getSmsCode() {
		return this.smsCode;
	}
	public void setPriority(java.lang.Integer value) {
		this.priority = value;
	}
	
	public java.lang.Integer getPriority() {
		return this.priority;
	}
	public void setSmsId(java.lang.String value) {
		this.smsId = value;
	}
	
	public java.lang.String getSmsId() {
		return this.smsId;
	}
	public void setTaskId(java.lang.String value) {
		this.taskId = value;
	}
	
	public java.lang.String getTaskId() {
		return this.taskId;
	}
	public void setSendStatus(java.lang.String value) {
		this.sendStatus = value;
	}
	
	public java.lang.String getSendStatus() {
		return this.sendStatus;
	}
	public void setResponseCode(java.lang.String value) {
		this.responseCode = value;
	}
	
	public java.lang.String getResponseCode() {
		return this.responseCode;
	}
	public void setSmsType(java.lang.String value) {
		this.smsType = value;
	}
	
	public java.lang.String getSmsType() {
		return this.smsType;
	}
	public void setReportStatus(java.lang.String value) {
		this.reportStatus = value;
	}
	
	public java.lang.String getReportStatus() {
		return this.reportStatus;
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
	public String getFixTimeString() {
		return DateConvertUtils.format(getFixTime(), FORMAT_FIX_TIME);
	}
	public void setFixTimeString(String value) {
		setFixTime(DateConvertUtils.parse(value, FORMAT_FIX_TIME,java.util.Date.class));
	}
	
	public void setFixTime(java.util.Date value) {
		this.fixTime = value;
	}
	
	public java.util.Date getFixTime() {
		return this.fixTime;
	}
	public String getReportTimeString() {
		return DateConvertUtils.format(getReportTime(), FORMAT_REPORT_TIME);
	}
	public void setReportTimeString(String value) {
		setReportTime(DateConvertUtils.parse(value, FORMAT_REPORT_TIME,java.util.Date.class));
	}

	public String getExtInfo() {
		return extInfo;
	}

	public void setExtInfo(String extInfo) {
		this.extInfo = extInfo;
	}

	public void setReportTime(java.util.Date value) {
		this.reportTime = value;
	}
	
	public java.util.Date getReportTime() {
		return this.reportTime;
	}
	public void setSendException(java.lang.String value) {
		this.sendException = value;
	}

	public String getSendType() {
		return sendType;
	}

	public void setSendType(String sendType) {
		this.sendType = sendType;
	}

	public java.lang.String getSendException() {
		return this.sendException;
	}

	public String getReportUrl() {
		return reportUrl;
	}

	public void setReportUrl(String reportUrl) {
		this.reportUrl = reportUrl;
	}

	public String getUpUrl() {
		return upUrl;
	}

	public void setUpUrl(String upUrl) {
		this.upUrl = upUrl;
	}

	@Override
	public String toString() {
		return "SmsLog{" +
				"id=" + id +
				", merctId=" + merctId +
				", merctCode='" + merctCode + '\'' +
				", chnId=" + chnId +
				", chnCode='" + chnCode + '\'' +
				", tempId=" + tempId +
				", mobile='" + mobile + '\'' +
				", smsMsg='" + smsMsg + '\'' +
				", infoCode='" + infoCode + '\'' +
				", smsCode='" + smsCode + '\'' +
				", extInfo='" + extInfo + '\'' +
				", priority=" + priority +
				", smsId='" + smsId + '\'' +
				", taskId='" + taskId + '\'' +
				", sendStatus='" + sendStatus + '\'' +
				", responseCode='" + responseCode + '\'' +
				", smsType='" + smsType + '\'' +
				", reportStatus='" + reportStatus + '\'' +
				", submitTime=" + submitTime +
				", sendTime=" + sendTime +
				", fixTime=" + fixTime +
				", reportTime=" + reportTime +
				", sendException='" + sendException + '\'' +
				", sendType='" + sendType + '\'' +
				", reportUrl='" + reportUrl + '\'' +
				", upUrl='" + upUrl + '\'' +
				'}';
	}

	public String toString(String separator) {
		StringBuffer sb = new StringBuffer();

			sb.append("id:").append(id).append(separator);
			sb.append("merctId:").append(merctId).append(separator);
			sb.append("merctCode:").append(merctCode).append(separator);
			sb.append("chnId:").append(chnId).append(separator);
			sb.append("chnCode:").append(chnCode).append(separator);
			sb.append("tempId:").append(tempId).append(separator);
			sb.append("mobile:").append(mobile).append(separator);
			sb.append("smsMsg:").append(smsMsg).append(separator);
			sb.append("infoCode:").append(infoCode).append(separator);
			sb.append("smsCode:").append(smsCode).append(separator);
			sb.append("extInfo:").append(extInfo).append(separator);
			sb.append("priority:").append(priority).append(separator);
			sb.append("smsId:").append(smsId).append(separator);
			sb.append("taskId:").append(taskId).append(separator);
			sb.append("sendStatus:").append(sendStatus).append(separator);
			sb.append("responseCode:").append(responseCode).append(separator);
			sb.append("smsType:").append(smsType).append(separator);
			sb.append("reportStatus:").append(reportStatus).append(separator);
			sb.append("submitTime:").append(getSubmitTimeString()).append(separator);
			sb.append("sendTime:").append(getSendTimeString()).append(separator);
			sb.append("fixTime:").append(getFixTimeString()).append(separator);
			sb.append("reportTime:").append(getReportTimeString()).append(separator);
			sb.append("sendException:").append(sendException).append(separator);
		
		return sb.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof SmsLog == false) return false;
		if(this == obj) return true;
		SmsLog other = (SmsLog)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}


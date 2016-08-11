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

public class SmsReport extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "SmsReport";
//	
//	public static final String ALIAS_TASK_ID = "任务ID";
//	
//	public static final String ALIAS_MOBILE = "手机号码";
//	
//	public static final String ALIAS_LOG_ID = "日志ID";
//	
//	public static final String ALIAS_MERCT_CODE = "商户ID";
//	
//	public static final String ALIAS_SMS_TYPE = "短信类型(1普通短信，2营销短信)";
//	
//	public static final String ALIAS_CREATE_TIME = "createTime";
//	
	
	//date formats
	public static final String FORMAT_CREATE_TIME = DATE_FORMAT;
	
	//columns START
	//任务ID
	private String taskId;
	//手机号码
	private String mobile;
	//日志ID
	private String logId;
	//商户ID
	private String merctCode;
	//短信类型(1普通短信，2营销短信)
	private String smsType;
	//createTime
	private java.util.Date createTime;
	/*短信ID*/
	private String smsId;
	/*状态报告URL*/
	private String reportUrl;
	//columns END

	public SmsReport(){
	}

	public SmsReport(
		String taskId,
		String mobile
	){
		this.taskId = taskId;
		this.mobile = mobile;
	}

	public void setTaskId(String value) {
		this.taskId = value;
	}
	
	public String getTaskId() {
		return this.taskId;
	}
	public void setMobile(String value) {
		this.mobile = value;
	}
	
	public String getMobile() {
		return this.mobile;
	}
	public void setLogId(String value) {
		this.logId = value;
	}
	
	public String getLogId() {
		return this.logId;
	}
	public void setMerctCode(String value) {
		this.merctCode = value;
	}
	
	public String getMerctCode() {
		return this.merctCode;
	}
	public void setSmsType(String value) {
		this.smsType = value;
	}
	
	public String getSmsType() {
		return this.smsType;
	}
	public String getCreateTimeString() {
		return DateConvertUtils.format(getCreateTime(), FORMAT_CREATE_TIME);
	}
	public void setCreateTimeString(String value) {
		setCreateTime(DateConvertUtils.parse(value, FORMAT_CREATE_TIME,java.util.Date.class));
	}
	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}

	public String getSmsId() {
		return smsId;
	}

	public void setSmsId(String smsId) {
		this.smsId = smsId;
	}

	public String getReportUrl() {
		return reportUrl;
	}

	public void setReportUrl(String reportUrl) {
		this.reportUrl = reportUrl;
	}

	@Override
	public String toString() {
		return "SmsReport{" +
				"taskId='" + taskId + '\'' +
				", mobile='" + mobile + '\'' +
				", logId='" + logId + '\'' +
				", merctCode='" + merctCode + '\'' +
				", smsType='" + smsType + '\'' +
				", createTime=" + createTime +
				", smsId='" + smsId + '\'' +
				", reportUrl='" + reportUrl + '\'' +
				'}';
	}

	public String toString(String separator) {
		StringBuffer sb = new StringBuffer();

			sb.append("任务ID:").append(taskId).append(separator);
			sb.append("手机号码:").append(mobile).append(separator);
			sb.append("日志ID:").append(logId).append(separator);
			sb.append("商户ID:").append(merctCode).append(separator);
			sb.append("短信类型(1普通短信，2营销短信):").append(smsType).append(separator);
			sb.append("createTime:").append(getCreateTimeString()).append(separator);
		
		return sb.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getTaskId())
			.append(getMobile())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof SmsReport == false) return false;
		if(this == obj) return true;
		SmsReport other = (SmsReport)obj;
		return new EqualsBuilder()
			.append(getTaskId(),other.getTaskId())
			.append(getMobile(),other.getMobile())
			.isEquals();
	}
}


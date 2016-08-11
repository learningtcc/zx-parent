/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.scheduler.core.po;

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

public class TimerTaskJobLog extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "TimerTaskJobLog";
//	
//	public static final String ALIAS_ID = "id";
//	
//	public static final String ALIAS_JOB_ID = "任务ID";
//	
//	public static final String ALIAS_START_TIME = "任务开始时间";
//	
//	public static final String ALIAS_END_TIME = "任务结束时间";
//	
//	public static final String ALIAS_STATUS = "任务执行结果";
//	
//	public static final String ALIAS_REQ_ID = "业务流水ID";
//	
//	public static final String ALIAS_ERROR_MSG = "错误信息";
//	
	
	//date formats
	public static final String FORMAT_START_TIME = DATE_FORMAT;
	public static final String FORMAT_END_TIME = DATE_FORMAT;
	
	//columns START
	//id
	private Integer id;
	//任务ID
	private Integer jobId;
	//任务开始时间
	private java.util.Date startTime;
	//任务结束时间
	private java.util.Date endTime;
	//任务执行结果
	private String status;
	//业务流水ID
	private String reqId;
	//错误信息
	private String errorMsg;
	//columns END

	//任务名称
	private String jobName;
	/** 任务描述 */
	private String description;

	public TimerTaskJobLog(){
	}

	public TimerTaskJobLog(
		Integer id
	){
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public void setId(Integer value) {
		this.id = value;
	}
	
	public Integer getId() {
		return this.id;
	}
	public void setJobId(Integer value) {
		this.jobId = value;
	}
	
	public Integer getJobId() {
		return this.jobId;
	}
	public String getStartTimeString() {
		return DateConvertUtils.format(getStartTime(), FORMAT_START_TIME);
	}
	public void setStartTimeString(String value) {
		setStartTime(DateConvertUtils.parse(value, FORMAT_START_TIME,java.util.Date.class));
	}
	
	public void setStartTime(java.util.Date value) {
		this.startTime = value;
	}
	
	public java.util.Date getStartTime() {
		return this.startTime;
	}
	public String getEndTimeString() {
		return DateConvertUtils.format(getEndTime(), FORMAT_END_TIME);
	}
	public void setEndTimeString(String value) {
		setEndTime(DateConvertUtils.parse(value, FORMAT_END_TIME,java.util.Date.class));
	}
	
	public void setEndTime(java.util.Date value) {
		this.endTime = value;
	}
	
	public java.util.Date getEndTime() {
		return this.endTime;
	}
	public void setStatus(String value) {
		this.status = value;
	}
	
	public String getStatus() {
		return this.status;
	}
	public void setReqId(String value) {
		this.reqId = value;
	}
	
	public String getReqId() {
		return this.reqId;
	}
	public void setErrorMsg(String value) {
		this.errorMsg = value;
	}
	
	public String getErrorMsg() {
		return this.errorMsg;
	}
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("JobId",getJobId())
			.append("StartTime",getStartTime())
			.append("EndTime",getEndTime())
			.append("Status",getStatus())
			.append("ReqId",getReqId())
			.append("ErrorMsg",getErrorMsg())
			.toString();
	}
	
	public String toString(String separator) {
		StringBuffer sb = new StringBuffer();

			sb.append("id:").append(id).append(separator);
			sb.append("任务ID:").append(jobId).append(separator);
			sb.append("任务开始时间:").append(getStartTimeString()).append(separator);
			sb.append("任务结束时间:").append(getEndTimeString()).append(separator);
			sb.append("任务执行结果:").append(status).append(separator);
			sb.append("业务流水ID:").append(reqId).append(separator);
			sb.append("错误信息:").append(errorMsg).append(separator);
		
		return sb.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TimerTaskJobLog == false) return false;
		if(this == obj) return true;
		TimerTaskJobLog other = (TimerTaskJobLog)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
	
	public String getPkValue() {
		if(getId() != null){
			return String.valueOf(getId());
		}
		
		return "";
	}
}


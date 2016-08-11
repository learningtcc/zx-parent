/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.scheduler.core.query;

import com.ink.base.BaseQuery;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Calendar;


/**
 * @author  zongtt
 * @version 1.0
 * @since 1.0
 */

public class TimerTaskJobQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    
	/** jobId */
	private Integer jobId;
	/** 任务名称 */
	private String jobName;
	/** jobGroup */
	private String jobGroup;
	/** status */
	private String status;
	/** jobStatus */
	private String jobStatus;
	/** isConcurrent */
	private String isConcurrent;
	/** description */
	private String description;
	/** jobClass */
	private String jobClass;
	/** methodName */
	private String methodName;
	/** firstTime */
	private java.util.Date firstTimeBegin;
	private java.util.Date firstTimeEnd;
	/** previousTime */
	private java.util.Date previousTimeBegin;
	private java.util.Date previousTimeEnd;
	/** validityPeriod */
	private java.util.Date validityPeriodBegin;
	private java.util.Date validityPeriodEnd;
	/** jobUrl */
	private String jobUrl;
	/** runCount */
	private Integer runCount;
	/** cronExpression */
	private String cronExpression;
	/** jobExecuteTime */
	private String jobExecuteTime;
	/** version */
	private String version;
	public Integer getJobId() {
		return this.jobId;
	}
	
	public void setJobId(Integer value) {
		this.jobId = value;
	}
	
	public String getJobName() {
		return this.jobName;
	}
	
	public void setJobName(String value) {
		this.jobName = value;
	}
	
	public String getJobGroup() {
		return this.jobGroup;
	}
	
	public void setJobGroup(String value) {
		this.jobGroup = value;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public void setStatus(String value) {
		this.status = value;
	}
	
	public String getJobStatus() {
		return this.jobStatus;
	}
	
	public void setJobStatus(String value) {
		this.jobStatus = value;
	}
	
	public String getIsConcurrent() {
		return this.isConcurrent;
	}
	
	public void setIsConcurrent(String value) {
		this.isConcurrent = value;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String value) {
		this.description = value;
	}
	
	public String getJobClass() {
		return this.jobClass;
	}
	
	public void setJobClass(String value) {
		this.jobClass = value;
	}
	
	public String getMethodName() {
		return this.methodName;
	}
	
	public void setMethodName(String value) {
		this.methodName = value;
	}
	
	public java.util.Date getFirstTimeBegin() {
		return this.firstTimeBegin;
	}
	
	public void setFirstTimeBegin(java.util.Date value) {
		this.firstTimeBegin = value;
	}	
	
	public java.util.Date getFirstTimeEnd() {
		return this.firstTimeEnd;
	}
	
	public void setFirstTimeEnd(java.util.Date value) {
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.firstTimeEnd = calendar.getTime();
		}else {
			this.firstTimeEnd = value;
		}
	}
	
	public java.util.Date getPreviousTimeBegin() {
		return this.previousTimeBegin;
	}
	
	public void setPreviousTimeBegin(java.util.Date value) {
		this.previousTimeBegin = value;
	}	
	
	public java.util.Date getPreviousTimeEnd() {
		return this.previousTimeEnd;
	}
	
	public void setPreviousTimeEnd(java.util.Date value) {
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.previousTimeEnd = calendar.getTime();
		}else {
			this.previousTimeEnd = value;
		}
	}
	
	public java.util.Date getValidityPeriodBegin() {
		return this.validityPeriodBegin;
	}
	
	public void setValidityPeriodBegin(java.util.Date value) {
		this.validityPeriodBegin = value;
	}	
	
	public java.util.Date getValidityPeriodEnd() {
		return this.validityPeriodEnd;
	}
	
	public void setValidityPeriodEnd(java.util.Date value) {
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.validityPeriodEnd = calendar.getTime();
		}else {
			this.validityPeriodEnd = value;
		}
	}
	
	public String getJobUrl() {
		return this.jobUrl;
	}
	
	public void setJobUrl(String value) {
		this.jobUrl = value;
	}
	
	public Integer getRunCount() {
		return this.runCount;
	}
	
	public void setRunCount(Integer value) {
		this.runCount = value;
	}
	
	public String getCronExpression() {
		return this.cronExpression;
	}
	
	public void setCronExpression(String value) {
		this.cronExpression = value;
	}

	public String getJobExecuteTime() {
		return jobExecuteTime;
	}

	public void setJobExecuteTime(String jobExecuteTime) {
		this.jobExecuteTime = jobExecuteTime;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}


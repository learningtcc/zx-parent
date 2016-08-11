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

public class TimerTaskJob extends BaseEntity implements java.io.Serializable,Cloneable {
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }public static final String TABLE_ALIAS = "TimerTaskJob";
//	
//	public static final String ALIAS_JOB_ID = "jobId";
//	
//	public static final String ALIAS_JOB_NAME = "任务名称";
//	
//	public static final String ALIAS_JOB_GROUP = "jobGroup";
//	
//	public static final String ALIAS_STATUS = "status";
//	
//	public static final String ALIAS_JOB_STATUS = "jobStatus";
//	
//	public static final String ALIAS_IS_CONCURRENT = "isConcurrent";
//	
//	public static final String ALIAS_DESCRIPTION = "description";
//	
//	public static final String ALIAS_JOB_CLASS = "jobClass";
//	
//	public static final String ALIAS_METHOD_NAME = "methodName";
//	
//	public static final String ALIAS_FIRST_TIME = "firstTime";
//	
//	public static final String ALIAS_PREVIOUS_TIME = "previousTime";
//	
//	public static final String ALIAS_VALIDITY_PERIOD = "validityPeriod";
//	
//	public static final String ALIAS_JOB_URL = "jobUrl";
//	
//	public static final String ALIAS_RUN_COUNT = "runCount";
//	
//	public static final String ALIAS_CRON_EXPRESSION = "cronExpression";
//	
	
	//date formats
	public static final String FORMAT_FIRST_TIME = DATE_FORMAT;
	public static final String FORMAT_PREVIOUS_TIME = DATE_FORMAT;
	public static final String FORMAT_VALIDITY_PERIOD = DATE_FORMAT;
	
	//columns START
	/** 任务id */
	private Integer jobId;
	/** 任务名称 */
	private String jobName;
	/** 任务分组，任务名称+组名称应该是唯一的 */
	private String jobGroup;
	/** 任务初始状态 0禁用 1启用 2删除 */
	private String status;
	/** 任务调度状态 0运行 1暂停  */
	private String jobStatus;
	/** 任务是否有状态（并发） 1有 默认为1 */
	private String isConcurrent;
	/** 任务描述 */
	private String description;
	/** 任务调用类名，包名+类名，通过类反射调用 ，如果spingId为空，则按jobClass查找   */
	private String jobClass;
	/** 任务调用的方法名 */
	private String methodName;
	/** 启动时间 */
	private java.util.Date firstTime;
	/** 前一次运行时间 */
	private java.util.Date previousTime;
	/** 任务有效时间 */
	private java.util.Date validityPeriod;
	/** 任务url */
	private String jobUrl;
	/** 任务执行次数 */
	private Integer runCount;
	/** 任务运行时间表达式 */
	private String cronExpression;
	/** 任务预计执行时间 */
	private String jobExecuteTime;
	/** 任务版本（锁） */
	private String version;
	//columns END

	public TimerTaskJob(){
	}

	public TimerTaskJob(
		Integer jobId
	){
		this.jobId = jobId;
	}

	public void setJobId(Integer value) {
		this.jobId = value;
	}
	
	public Integer getJobId() {
		return this.jobId;
	}
	public void setJobName(String value) {
		this.jobName = value;
	}
	
	public String getJobName() {
		return this.jobName;
	}
	public void setJobGroup(String value) {
		this.jobGroup = value;
	}
	
	public String getJobGroup() {
		return this.jobGroup;
	}
	public void setStatus(String value) {
		this.status = value;
	}
	
	public String getStatus() {
		return this.status;
	}
	public void setJobStatus(String value) {
		this.jobStatus = value;
	}
	
	public String getJobStatus() {
		return this.jobStatus;
	}
	public void setIsConcurrent(String value) {
		this.isConcurrent = value;
	}
	
	public String getIsConcurrent() {
		return this.isConcurrent;
	}
	public void setDescription(String value) {
		this.description = value;
	}
	
	public String getDescription() {
		return this.description;
	}
	public void setJobClass(String value) {
		this.jobClass = value;
	}
	
	public String getJobClass() {
		return this.jobClass;
	}
	public void setMethodName(String value) {
		this.methodName = value;
	}
	
	public String getMethodName() {
		return this.methodName;
	}
	public String getFirstTimeString() {
		return DateConvertUtils.format(getFirstTime(), FORMAT_FIRST_TIME);
	}
	public void setFirstTimeString(String value) {
		setFirstTime(DateConvertUtils.parse(value, FORMAT_FIRST_TIME,java.util.Date.class));
	}
	
	public void setFirstTime(java.util.Date value) {
		this.firstTime = value;
	}
	
	public java.util.Date getFirstTime() {
		return this.firstTime;
	}
	public String getPreviousTimeString() {
		return DateConvertUtils.format(getPreviousTime(), FORMAT_PREVIOUS_TIME);
	}
	public void setPreviousTimeString(String value) {
		setPreviousTime(DateConvertUtils.parse(value, FORMAT_PREVIOUS_TIME,java.util.Date.class));
	}
	
	public void setPreviousTime(java.util.Date value) {
		this.previousTime = value;
	}
	
	public java.util.Date getPreviousTime() {
		return this.previousTime;
	}
	public String getValidityPeriodString() {
		return DateConvertUtils.format(getValidityPeriod(), FORMAT_VALIDITY_PERIOD);
	}
	public void setValidityPeriodString(String value) {
		setValidityPeriod(DateConvertUtils.parse(value, FORMAT_VALIDITY_PERIOD,java.util.Date.class));
	}
	
	public void setValidityPeriod(java.util.Date value) {
		this.validityPeriod = value;
	}
	
	public java.util.Date getValidityPeriod() {
		return this.validityPeriod;
	}
	public void setJobUrl(String value) {
		this.jobUrl = value;
	}
	
	public String getJobUrl() {
		return this.jobUrl;
	}
	public void setRunCount(Integer value) {
		this.runCount = value;
	}
	
	public Integer getRunCount() {
		return this.runCount;
	}
	public void setCronExpression(String value) {
		this.cronExpression = value;
	}
	
	public String getCronExpression() {
		return this.cronExpression;
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
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
			.append("JobId",getJobId())
			.append("JobName",getJobName())
			.append("JobGroup",getJobGroup())
			.append("Status",getStatus())
			.append("JobStatus",getJobStatus())
			.append("IsConcurrent",getIsConcurrent())
			.append("Description",getDescription())
			.append("JobClass",getJobClass())
			.append("MethodName",getMethodName())
			.append("FirstTime",getFirstTime())
			.append("PreviousTime",getPreviousTime())
			.append("ValidityPeriod",getValidityPeriod())
			.append("JobUrl",getJobUrl())
			.append("RunCount",getRunCount())
			.append("CronExpression",getCronExpression())
			.toString();
	}
	
	public String toString(String separator) {
		StringBuffer sb = new StringBuffer();

			sb.append(jobId+":").append(jobId).append(separator);
			sb.append(jobName+":").append(jobName).append(separator);
			sb.append(jobGroup+":").append(jobGroup).append(separator);
			sb.append(status+":").append(status).append(separator);
			sb.append(jobStatus+":").append(jobStatus).append(separator);
			sb.append(isConcurrent+":").append(isConcurrent).append(separator);
			sb.append(description+":").append(description).append(separator);
			sb.append(jobClass+":").append(jobClass).append(separator);
			sb.append(methodName+":").append(methodName).append(separator);
			sb.append(firstTime+":").append(getFirstTimeString()).append(separator);
			sb.append(previousTime+":").append(getPreviousTimeString()).append(separator);
			sb.append(validityPeriod+":").append(getValidityPeriodString()).append(separator);
			sb.append(jobUrl+":").append(jobUrl).append(separator);
			sb.append(runCount+":").append(runCount).append(separator);
			sb.append(cronExpression+":").append(cronExpression).append(separator);
		
		return sb.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getJobId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TimerTaskJob == false) return false;
		if(this == obj) return true;
		TimerTaskJob other = (TimerTaskJob)obj;
		return new EqualsBuilder()
			.append(getJobId(),other.getJobId())
			.isEquals();
	}
}


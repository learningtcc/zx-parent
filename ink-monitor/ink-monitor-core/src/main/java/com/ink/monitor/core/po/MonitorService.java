/**
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.monitor.core.po;

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

public class MonitorService extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;

	//alias
	public static final String TABLE_ALIAS = "MonitorService";
//	
//	public static final String ALIAS_ID = "id";
//	
//	public static final String ALIAS_SYS_CODE = "平台系统编码";
//	
//	public static final String ALIAS_SOURCE_TYPE = "资源类型1为http，其它待定";
//	
//	public static final String ALIAS_SOURCE_NAME = "资源名称";
//	
//	public static final String ALIAS_SOURCE_URL = "资源路径";
//	
//	public static final String ALIAS_MONITOR_STATUS = "监控状态(不是200为不可用)，监控的多个状态用‘,’分割。不是200且不是当前状态的则进行日志记录，否则用数据表记录";
//	
//	public static final String ALIAS_WARN_THRESHOLD = "预警阀值，为0时无阀值限制，数值需大于0";
//	
//	public static final String ALIAS_WARN_FREQUENCY = "预警频次，频次为-1时表示不限制次数，为0时表示不报警";
//	
//	public static final String ALIAS_STATUS = "是否有效0有效1删除";
//	
//	public static final String ALIAS_OPERATE_TIME = "最后操作时间";
//	

	//date formats
	public static final String FORMAT_OPERATE_TIME = DATE_FORMAT;

	//columns START
	//id
	private Integer id;
	//平台系统编码
	private String sysCode;
	//资源类型1为http，其它待定
	private String sourceType;
	//资源名称
	private String sourceName;
	//资源路径
	private String sourceUrl;
	//监控状态(不是200为不可用)，监控的多个状态用‘,’分割。不是200且不是当前状态的则进行日志记录，否则用数据表记录
	private String monitorStatus;
	//预警阀值，为0时无阀值限制，数值需大于0
	private Integer warnThreshold;
	//预警频次，频次为-1时表示不限制次数，为0时表示不报警
	private Integer warnFrequency;
	//是否有效0有效1删除
	private String status;
	//最后操作时间
	private java.util.Date operateTime;
	//columns END

	public MonitorService(){
	}

	public MonitorService(
			java.lang.Integer id
	){
		this.id = id;
	}

	public void setId(java.lang.Integer value) {
		this.id = value;
	}

	public java.lang.Integer getId() {
		return this.id;
	}
	public void setSysCode(java.lang.String value) {
		this.sysCode = value;
	}

	public java.lang.String getSysCode() {
		return this.sysCode;
	}
	public void setSourceType(java.lang.String value) {
		this.sourceType = value;
	}

	public java.lang.String getSourceType() {
		return this.sourceType;
	}
	public void setSourceName(java.lang.String value) {
		this.sourceName = value;
	}

	public java.lang.String getSourceName() {
		return this.sourceName;
	}
	public void setSourceUrl(java.lang.String value) {
		this.sourceUrl = value;
	}

	public java.lang.String getSourceUrl() {
		return this.sourceUrl;
	}
	public void setMonitorStatus(java.lang.String value) {
		this.monitorStatus = value;
	}

	public java.lang.String getMonitorStatus() {
		return this.monitorStatus;
	}
	public void setWarnThreshold(java.lang.Integer value) {
		this.warnThreshold = value;
	}

	public java.lang.Integer getWarnThreshold() {
		return this.warnThreshold;
	}
	public void setWarnFrequency(java.lang.Integer value) {
		this.warnFrequency = value;
	}

	public java.lang.Integer getWarnFrequency() {
		return this.warnFrequency;
	}
	public void setStatus(java.lang.String value) {
		this.status = value;
	}

	public java.lang.String getStatus() {
		return this.status;
	}
	public String getOperateTimeString() {
		return DateConvertUtils.format(getOperateTime(), FORMAT_OPERATE_TIME);
	}
	public void setOperateTimeString(String value) {
		setOperateTime(DateConvertUtils.parse(value, FORMAT_OPERATE_TIME,java.util.Date.class));
	}

	public void setOperateTime(java.util.Date value) {
		this.operateTime = value;
	}

	public java.util.Date getOperateTime() {
		return this.operateTime;
	}
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("Id",getId())
				.append("SysCode",getSysCode())
				.append("SourceType",getSourceType())
				.append("SourceName",getSourceName())
				.append("SourceUrl",getSourceUrl())
				.append("MonitorStatus",getMonitorStatus())
				.append("WarnThreshold",getWarnThreshold())
				.append("WarnFrequency",getWarnFrequency())
				.append("Status",getStatus())
				.append("OperateTime",getOperateTime())
				.toString();
	}

	public String toString(String separator) {
		StringBuffer sb = new StringBuffer();

		sb.append("id:").append(id).append(separator);
		sb.append("平台系统编码:").append(sysCode).append(separator);
		sb.append("资源类型1为http，其它待定:").append(sourceType).append(separator);
		sb.append("资源名称:").append(sourceName).append(separator);
		sb.append("资源路径:").append(sourceUrl).append(separator);
		sb.append("监控状态(不是200为不可用)，监控的多个状态用‘,’分割。不是200且不是当前状态的则进行日志记录，否则用数据表记录:").append(monitorStatus).append(separator);
		sb.append("预警阀值，为0时无阀值限制，数值需大于0:").append(warnThreshold).append(separator);
		sb.append("预警频次，频次为-1时表示不限制次数，为0时表示不报警:").append(warnFrequency).append(separator);
		sb.append("是否有效0有效1删除:").append(status).append(separator);
		sb.append("最后操作时间:").append(getOperateTimeString()).append(separator);

		return sb.toString();
	}

	public int hashCode() {
		return new HashCodeBuilder()
				.append(getId())
				.toHashCode();
	}

	public boolean equals(Object obj) {
		if(obj instanceof MonitorService == false) return false;
		if(this == obj) return true;
		MonitorService other = (MonitorService)obj;
		return new EqualsBuilder()
				.append(getId(),other.getId())
				.isEquals();
	}
}


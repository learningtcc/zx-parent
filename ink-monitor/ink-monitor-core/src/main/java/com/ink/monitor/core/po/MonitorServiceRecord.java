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

public class MonitorServiceRecord extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;

	//alias
	public static final String TABLE_ALIAS = "MonitorServiceRecord";
//	
//	public static final String ALIAS_ID = "id";
//	
//	public static final String ALIAS_SERVICE_ID = "serviceId";
//	
//	public static final String ALIAS_SYS_CODE = "平台系统编号";
//	
//	public static final String ALIAS_SOURCE_URL = "资源路径";
//	
//	public static final String ALIAS_VISIT_STATUS = "访问状态";
//	
//	public static final String ALIAS_ERROR_DESC = "错误描述";
//	
//	public static final String ALIAS_FIRST_ERROR_TIME = "第一次发现问题时间";
//	
//	public static final String ALIAS_ERROR_COUNT = "错误次数";
//	
//	public static final String ALIAS_STATUS = "状态0未解决1已解决";
//	
//	public static final String ALIAS_SAFE_TIME = "问题解决时间";
//	
//	public static final String ALIAS_OPERATE_TIME = "最后操作时间";
//	

	//date formats
	public static final String FORMAT_FIRST_ERROR_TIME = DATE_TIME_FORMAT;
	public static final String FORMAT_SAFE_TIME = DATE_TIME_FORMAT;
	public static final String FORMAT_OPERATE_TIME = DATE_TIME_FORMAT;

	//columns START
	//id
	private Integer id;
	//serviceId
	private Integer serviceId;
	//平台系统编号
	private String sysCode;
	//资源路径
	private String sourceUrl;
	//访问状态
	private String visitStatus;
	//错误描述
	private String errorDesc;
	//第一次发现问题时间
	private java.util.Date firstErrorTime;
	//错误次数
	private Integer errorCount;
	//状态0未解决1已解决
	private String status;
	//问题解决时间
	private java.util.Date safeTime;
	//最后操作时间
	private java.util.Date operateTime;
	//columns END

	public MonitorServiceRecord(){
	}

	public MonitorServiceRecord(
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
	public void setServiceId(java.lang.Integer value) {
		this.serviceId = value;
	}

	public java.lang.Integer getServiceId() {
		return this.serviceId;
	}
	public void setSysCode(java.lang.String value) {
		this.sysCode = value;
	}

	public java.lang.String getSysCode() {
		return this.sysCode;
	}
	public void setSourceUrl(java.lang.String value) {
		this.sourceUrl = value;
	}

	public java.lang.String getSourceUrl() {
		return this.sourceUrl;
	}
	public void setVisitStatus(java.lang.String value) {
		this.visitStatus = value;
	}

	public java.lang.String getVisitStatus() {
		return this.visitStatus;
	}
	public void setErrorDesc(java.lang.String value) {
		this.errorDesc = value;
	}

	public java.lang.String getErrorDesc() {
		return this.errorDesc;
	}
	public String getFirstErrorTimeString() {
		return DateConvertUtils.format(getFirstErrorTime(), FORMAT_FIRST_ERROR_TIME);
	}
	public void setFirstErrorTimeString(String value) {
		setFirstErrorTime(DateConvertUtils.parse(value, FORMAT_FIRST_ERROR_TIME,java.util.Date.class));
	}

	public void setFirstErrorTime(java.util.Date value) {
		this.firstErrorTime = value;
	}

	public java.util.Date getFirstErrorTime() {
		return this.firstErrorTime;
	}
	public void setErrorCount(java.lang.Integer value) {
		this.errorCount = value;
	}

	public java.lang.Integer getErrorCount() {
		return this.errorCount;
	}
	public void setStatus(java.lang.String value) {
		this.status = value;
	}

	public java.lang.String getStatus() {
		return this.status;
	}
	public String getSafeTimeString() {
		return DateConvertUtils.format(getSafeTime(), FORMAT_SAFE_TIME);
	}
	public void setSafeTimeString(String value) {
		setSafeTime(DateConvertUtils.parse(value, FORMAT_SAFE_TIME,java.util.Date.class));
	}

	public void setSafeTime(java.util.Date value) {
		this.safeTime = value;
	}

	public java.util.Date getSafeTime() {
		return this.safeTime;
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
				.append("ServiceId",getServiceId())
				.append("SysCode",getSysCode())
				.append("SourceUrl",getSourceUrl())
				.append("VisitStatus",getVisitStatus())
				.append("ErrorDesc",getErrorDesc())
				.append("FirstErrorTime",getFirstErrorTime())
				.append("ErrorCount",getErrorCount())
				.append("Status",getStatus())
				.append("SafeTime",getSafeTime())
				.append("OperateTime",getOperateTime())
				.toString();
	}

	public String toString(String separator) {
		StringBuffer sb = new StringBuffer();

		sb.append("id:").append(id).append(separator);
		sb.append("serviceId:").append(serviceId).append(separator);
		sb.append("平台系统编号:").append(sysCode).append(separator);
		sb.append("资源路径:").append(sourceUrl).append(separator);
		sb.append("访问状态:").append(visitStatus).append(separator);
		sb.append("错误描述:").append(errorDesc).append(separator);
		sb.append("第一次发现问题时间:").append(getFirstErrorTimeString()).append(separator);
		sb.append("错误次数:").append(errorCount).append(separator);
		sb.append("状态0未解决1已解决:").append(status).append(separator);
		sb.append("问题解决时间:").append(getSafeTimeString()).append(separator);
		sb.append("最后操作时间:").append(getOperateTimeString()).append(separator);

		return sb.toString();
	}

	public int hashCode() {
		return new HashCodeBuilder()
				.append(getId())
				.toHashCode();
	}

	public boolean equals(Object obj) {
		if(obj instanceof MonitorServiceRecord == false) return false;
		if(this == obj) return true;
		MonitorServiceRecord other = (MonitorServiceRecord)obj;
		return new EqualsBuilder()
				.append(getId(),other.getId())
				.isEquals();
	}
}


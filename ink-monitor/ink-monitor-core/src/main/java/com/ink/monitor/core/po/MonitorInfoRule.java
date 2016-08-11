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

public class MonitorInfoRule extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "MonitorInfoRule";
//	
//	public static final String ALIAS_ID = "id";
//	
//	public static final String ALIAS_SYS_CODE = "系统代码";
//	
//	public static final String ALIAS_MODULE_CODE = "模块代码";
//	
//	public static final String ALIAS_INFO_CODE = "业务代码";
//	
//	public static final String ALIAS_WARN_INTERVAL = "预警间隔（单位分钟），如15表示15分钟内该模块报警超过阈值未达到频次时需报警，数值为正整数";
//	
//	public static final String ALIAS_WARN_THRESHOLD = "预警间隔（单位分钟），如15表示15分钟内该模块报警超过阈值未达到频次时需报警，数值为正整数";
//	
//	public static final String ALIAS_WARN_FREQUENCY = "预警间隔（单位分钟），如15表示15分钟内该模块报警超过阈值未达到频次时需报警，数值为正整数";
//	
//	public static final String ALIAS_CURRENT_WARN_COUNT = "当前已报警次数";
//	
//	public static final String ALIAS_CREATE_TIME = "创建时间";
//	
//	public static final String ALIAS_UPDATE_TIME = "更新时间";
//	
	
	//date formats
	public static final String FORMAT_CREATE_TIME = DATE_FORMAT;
	public static final String FORMAT_UPDATE_TIME = DATE_FORMAT;
	
	//columns START
	//id
	private Integer id;
	//系统代码
	private String sysCode;
	//模块代码
	private String moduleCode;
	//业务代码
	private String infoCode;
	//预警间隔（单位分钟），如15表示15分钟内该模块报警超过阈值未达到频次时需报警，数值为正整数
	private Integer warnInterval;
	//预警间隔（单位分钟），如15表示15分钟内该模块报警超过阈值未达到频次时需报警，数值为正整数
	private Integer warnThreshold;
	//预警间隔（单位分钟），如15表示15分钟内该模块报警超过阈值未达到频次时需报警，数值为正整数
	private Integer warnFrequency;
	//当前已报警次数
	private Integer currentWarnCount;
	//创建时间
	private java.util.Date createTime;
	//更新时间
	private java.util.Date updateTime;
	//	状态（0，1）
	private String status;
	//columns END

	public MonitorInfoRule(){
	}

	public MonitorInfoRule(
		Integer id
	){
		this.id = id;
	}

	public void setId(Integer value) {
		this.id = value;
	}
	
	public Integer getId() {
		return this.id;
	}
	public void setSysCode(String value) {
		this.sysCode = value;
	}
	
	public String getSysCode() {
		return this.sysCode;
	}
	public void setModuleCode(String value) {
		this.moduleCode = value;
	}
	
	public String getModuleCode() {
		return this.moduleCode;
	}
	public void setInfoCode(String value) {
		this.infoCode = value;
	}
	
	public String getInfoCode() {
		return this.infoCode;
	}
	public void setWarnInterval(Integer value) {
		this.warnInterval = value;
	}
	
	public Integer getWarnInterval() {
		return this.warnInterval;
	}
	public void setWarnThreshold(Integer value) {
		this.warnThreshold = value;
	}
	
	public Integer getWarnThreshold() {
		return this.warnThreshold;
	}
	public void setWarnFrequency(Integer value) {
		this.warnFrequency = value;
	}
	
	public Integer getWarnFrequency() {
		return this.warnFrequency;
	}
	public void setCurrentWarnCount(Integer value) {
		this.currentWarnCount = value;
	}
	
	public Integer getCurrentWarnCount() {
		return this.currentWarnCount;
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
	public String getUpdateTimeString() {
		return DateConvertUtils.format(getUpdateTime(), FORMAT_UPDATE_TIME);
	}
	public void setUpdateTimeString(String value) {
		setUpdateTime(DateConvertUtils.parse(value, FORMAT_UPDATE_TIME,java.util.Date.class));
	}
	
	public void setUpdateTime(java.util.Date value) {
		this.updateTime = value;
	}
	
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("SysCode",getSysCode())
			.append("ModuleCode",getModuleCode())
			.append("InfoCode",getInfoCode())
			.append("WarnInterval",getWarnInterval())
			.append("WarnThreshold",getWarnThreshold())
			.append("WarnFrequency",getWarnFrequency())
			.append("CurrentWarnCount",getCurrentWarnCount())
			.append("CreateTime",getCreateTime())
			.append("UpdateTime",getUpdateTime())
			.toString();
	}
	
	public String toString(String separator) {
		StringBuffer sb = new StringBuffer();

			sb.append("id:").append(id).append(separator);
			sb.append("系统代码:").append(sysCode).append(separator);
			sb.append("模块代码:").append(moduleCode).append(separator);
			sb.append("业务代码:").append(infoCode).append(separator);
			sb.append("预警间隔（单位分钟），如15表示15分钟内该模块报警超过阈值未达到频次时需报警，数值为正整数:").append(warnInterval).append(separator);
			sb.append("预警间隔（单位分钟），如15表示15分钟内该模块报警超过阈值未达到频次时需报警，数值为正整数:").append(warnThreshold).append(separator);
			sb.append("预警间隔（单位分钟），如15表示15分钟内该模块报警超过阈值未达到频次时需报警，数值为正整数:").append(warnFrequency).append(separator);
			sb.append("当前已报警次数:").append(currentWarnCount).append(separator);
			sb.append("创建时间:").append(getCreateTimeString()).append(separator);
			sb.append("更新时间:").append(getUpdateTimeString()).append(separator);
		
		return sb.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof MonitorInfoRule == false) return false;
		if(this == obj) return true;
		MonitorInfoRule other = (MonitorInfoRule)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}


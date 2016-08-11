/**
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.monitor.core.query;

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

public class MonitorServiceQuery extends BaseQuery implements Serializable {
	private static final long serialVersionUID = 3148176768559230877L;

	/** id */
	private java.lang.Integer id;
	/** 平台系统编码 */
	private java.lang.String sysCode;
	/** 资源类型1为http，其它待定 */
	private java.lang.String sourceType;
	/** 资源名称 */
	private java.lang.String sourceName;
	/** 资源路径 */
	private java.lang.String sourceUrl;
	/** 监控状态(不是200为不可用)，监控的多个状态用‘,’分割。不是200且不是当前状态的则进行日志记录，否则用数据表记录 */
	private java.lang.String monitorStatus;
	/** 预警阀值，为0时无阀值限制，数值需大于0 */
	private java.lang.Integer warnThreshold;
	/** 预警频次，频次为-1时表示不限制次数，为0时表示不报警 */
	private java.lang.Integer warnFrequency;
	/** 是否有效0有效1删除 */
	private java.lang.String status;
	/** 最后操作时间 */
	private java.util.Date operateTimeBegin;
	private java.util.Date operateTimeEnd;
	public java.lang.Integer getId() {
		return this.id;
	}

	public void setId(java.lang.Integer value) {
		this.id = value;
	}

	public java.lang.String getSysCode() {
		return this.sysCode;
	}

	public void setSysCode(java.lang.String value) {
		this.sysCode = value;
	}

	public java.lang.String getSourceType() {
		return this.sourceType;
	}

	public void setSourceType(java.lang.String value) {
		this.sourceType = value;
	}

	public java.lang.String getSourceName() {
		return this.sourceName;
	}

	public void setSourceName(java.lang.String value) {
		this.sourceName = value;
	}

	public java.lang.String getSourceUrl() {
		return this.sourceUrl;
	}

	public void setSourceUrl(java.lang.String value) {
		this.sourceUrl = value;
	}

	public java.lang.String getMonitorStatus() {
		return this.monitorStatus;
	}

	public void setMonitorStatus(java.lang.String value) {
		this.monitorStatus = value;
	}

	public java.lang.Integer getWarnThreshold() {
		return this.warnThreshold;
	}

	public void setWarnThreshold(java.lang.Integer value) {
		this.warnThreshold = value;
	}

	public java.lang.Integer getWarnFrequency() {
		return this.warnFrequency;
	}

	public void setWarnFrequency(java.lang.Integer value) {
		this.warnFrequency = value;
	}

	public java.lang.String getStatus() {
		return this.status;
	}

	public void setStatus(java.lang.String value) {
		this.status = value;
	}

	public java.util.Date getOperateTimeBegin() {
		return this.operateTimeBegin;
	}

	public void setOperateTimeBegin(java.util.Date value) {
		this.operateTimeBegin = value;
	}

	public java.util.Date getOperateTimeEnd() {
		return this.operateTimeEnd;
	}

	public void setOperateTimeEnd(java.util.Date value) {
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.operateTimeEnd = calendar.getTime();
		}else {
			this.operateTimeEnd = value;
		}
	}


	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}

}


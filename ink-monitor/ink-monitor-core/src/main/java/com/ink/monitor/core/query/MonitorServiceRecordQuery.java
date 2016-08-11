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

public class MonitorServiceRecordQuery extends BaseQuery implements Serializable {
	private static final long serialVersionUID = 3148176768559230877L;

	/** id */
	private java.lang.Integer id;
	/** serviceId */
	private java.lang.Integer serviceId;
	/** 平台系统编号 */
	private java.lang.String sysCode;
	/** 资源路径 */
	private java.lang.String sourceUrl;
	/** 访问状态 */
	private java.lang.String visitStatus;
	/** 错误描述 */
	private java.lang.String errorDesc;
	/** 第一次发现问题时间 */
	private java.util.Date firstErrorTimeBegin;
	private java.util.Date firstErrorTimeEnd;
	/** 错误次数 */
	private java.lang.Integer errorCount;
	/** 状态0未解决1已解决 */
	private java.lang.String status;
	/** 问题解决时间 */
	private java.util.Date safeTimeBegin;
	private java.util.Date safeTimeEnd;
	/** 最后操作时间 */
	private java.util.Date operateTimeBegin;
	private java.util.Date operateTimeEnd;
	public java.lang.Integer getId() {
		return this.id;
	}

	public void setId(java.lang.Integer value) {
		this.id = value;
	}

	public java.lang.Integer getServiceId() {
		return this.serviceId;
	}

	public void setServiceId(java.lang.Integer value) {
		this.serviceId = value;
	}

	public java.lang.String getSysCode() {
		return this.sysCode;
	}

	public void setSysCode(java.lang.String value) {
		this.sysCode = value;
	}

	public java.lang.String getSourceUrl() {
		return this.sourceUrl;
	}

	public void setSourceUrl(java.lang.String value) {
		this.sourceUrl = value;
	}

	public java.lang.String getVisitStatus() {
		return this.visitStatus;
	}

	public void setVisitStatus(java.lang.String value) {
		this.visitStatus = value;
	}

	public java.lang.String getErrorDesc() {
		return this.errorDesc;
	}

	public void setErrorDesc(java.lang.String value) {
		this.errorDesc = value;
	}

	public java.util.Date getFirstErrorTimeBegin() {
		return this.firstErrorTimeBegin;
	}

	public void setFirstErrorTimeBegin(java.util.Date value) {
		this.firstErrorTimeBegin = value;
	}

	public java.util.Date getFirstErrorTimeEnd() {
		return this.firstErrorTimeEnd;
	}

	public void setFirstErrorTimeEnd(java.util.Date value) {
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.firstErrorTimeEnd = calendar.getTime();
		}else {
			this.firstErrorTimeEnd = value;
		}
	}

	public java.lang.Integer getErrorCount() {
		return this.errorCount;
	}

	public void setErrorCount(java.lang.Integer value) {
		this.errorCount = value;
	}

	public java.lang.String getStatus() {
		return this.status;
	}

	public void setStatus(java.lang.String value) {
		this.status = value;
	}

	public java.util.Date getSafeTimeBegin() {
		return this.safeTimeBegin;
	}

	public void setSafeTimeBegin(java.util.Date value) {
		this.safeTimeBegin = value;
	}

	public java.util.Date getSafeTimeEnd() {
		return this.safeTimeEnd;
	}

	public void setSafeTimeEnd(java.util.Date value) {
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.safeTimeEnd = calendar.getTime();
		}else {
			this.safeTimeEnd = value;
		}
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


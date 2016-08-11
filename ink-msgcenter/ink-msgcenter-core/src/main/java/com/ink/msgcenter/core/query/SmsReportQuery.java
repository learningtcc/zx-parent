/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.msgcenter.core.query;

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

public class SmsReportQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    
	/** 任务ID */
	private String taskId;
	/** 手机号码 */
	private String mobile;
	/** 日志ID */
	private String logId;
	/** 商户ID */
	private String merctCode;
	/** 短信类型(1普通短信，2营销短信) */
	private String smsType;
	/*短信ID*/
	private String smsId;
	/*状态报告Url*/
	private String reportUrl;
	/** createTime */
	private java.util.Date createTimeBegin;
	private java.util.Date createTimeEnd;
	public String getTaskId() {
		return this.taskId;
	}
	
	public void setTaskId(String value) {
		this.taskId = value;
	}
	
	public String getMobile() {
		return this.mobile;
	}
	
	public void setMobile(String value) {
		this.mobile = value;
	}
	
	public String getLogId() {
		return this.logId;
	}
	
	public void setLogId(String value) {
		this.logId = value;
	}
	
	public String getMerctCode() {
		return this.merctCode;
	}
	
	public void setMerctCode(String value) {
		this.merctCode = value;
	}
	
	public String getSmsType() {
		return this.smsType;
	}
	
	public void setSmsType(String value) {
		this.smsType = value;
	}
	
	public java.util.Date getCreateTimeBegin() {
		return this.createTimeBegin;
	}
	
	public void setCreateTimeBegin(java.util.Date value) {
		this.createTimeBegin = value;
	}	
	
	public java.util.Date getCreateTimeEnd() {
		return this.createTimeEnd;
	}
	
	public void setCreateTimeEnd(java.util.Date value) {
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.createTimeEnd = calendar.getTime();
		}else {
			this.createTimeEnd = value;
		}
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

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}


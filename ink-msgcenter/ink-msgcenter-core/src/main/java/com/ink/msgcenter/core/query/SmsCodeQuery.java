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

public class SmsCodeQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    
	/** id */
	private java.lang.Long id;
	/** 短信ID */
	private java.lang.String smsId;
	/** 短信代码 */
	private java.lang.String smsCode;
	/** 商户ID */
	private java.lang.Long merctId;
	/** 商户代码 */
	private java.lang.String merctCode;
	/** 发送序列ID */
	private java.lang.String taskId;
	/** 手机号 */
	private java.lang.String mobile;
	/** 扩展信息 */
	private java.lang.String extInfo;
	/** 状态0有效1失效 */
	private java.lang.String status;
	/*上行url*/
	private String upUrl;
	/** 创建时间 */
	private java.util.Date createTimeBegin;
	private java.util.Date createTimeEnd;
	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.String getSmsId() {
		return this.smsId;
	}
	
	public void setSmsId(java.lang.String value) {
		this.smsId = value;
	}
	
	public java.lang.String getSmsCode() {
		return this.smsCode;
	}
	
	public void setSmsCode(java.lang.String value) {
		this.smsCode = value;
	}
	
	public java.lang.Long getMerctId() {
		return this.merctId;
	}
	
	public void setMerctId(java.lang.Long value) {
		this.merctId = value;
	}
	
	public java.lang.String getMerctCode() {
		return this.merctCode;
	}
	
	public void setMerctCode(java.lang.String value) {
		this.merctCode = value;
	}
	
	public java.lang.String getTaskId() {
		return this.taskId;
	}
	
	public void setTaskId(java.lang.String value) {
		this.taskId = value;
	}
	
	public java.lang.String getMobile() {
		return this.mobile;
	}
	
	public void setMobile(java.lang.String value) {
		this.mobile = value;
	}
	
	public java.lang.String getExtInfo() {
		return this.extInfo;
	}
	
	public void setExtInfo(java.lang.String value) {
		this.extInfo = value;
	}
	
	public java.lang.String getStatus() {
		return this.status;
	}
	
	public void setStatus(java.lang.String value) {
		this.status = value;
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

	public String getUpUrl() {
		return upUrl;
	}

	public void setUpUrl(String upUrl) {
		this.upUrl = upUrl;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}


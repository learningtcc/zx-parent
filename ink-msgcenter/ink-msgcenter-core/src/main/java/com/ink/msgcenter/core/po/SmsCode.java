/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.msgcenter.core.po;

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

public class SmsCode extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "SmsCode";
//	
//	public static final String ALIAS_ID = "id";
//	
//	public static final String ALIAS_SMS_ID = "短信ID";
//	
//	public static final String ALIAS_SMS_CODE = "短信代码";
//	
//	public static final String ALIAS_MERCT_ID = "商户ID";
//	
//	public static final String ALIAS_MERCT_CODE = "商户代码";
//	
//	public static final String ALIAS_TASK_ID = "发送序列ID";
//	
//	public static final String ALIAS_MOBILE = "手机号";
//	
//	public static final String ALIAS_EXT_INFO = "扩展信息";
//	
//	public static final String ALIAS_STATUS = "状态0有效1失效";
//	
//	public static final String ALIAS_CREATE_TIME = "创建时间";
//	
	
	//date formats
	public static final String FORMAT_CREATE_TIME = DATE_TIME_FORMAT;
	
	//columns START
	//id
	private Long id;
	//短信ID
	private String smsId;
	//短信代码
	private String smsCode;
	//商户ID
	private Long merctId;
	//商户代码
	private String merctCode;
	//发送序列ID
	private String taskId;
	//手机号
	private String mobile;
	//扩展信息
	private String extInfo;
	//状态0有效1失效
	private String status;
	//创建时间
	private java.util.Date createTime;
	/*上行url*/
	private String upUrl;
	//columns END

	public SmsCode(){
	}

	public SmsCode(
		java.lang.Long id
	){
		this.id = id;
	}

	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.Long getId() {
		return this.id;
	}
	public void setSmsId(java.lang.String value) {
		this.smsId = value;
	}
	
	public java.lang.String getSmsId() {
		return this.smsId;
	}
	public void setSmsCode(java.lang.String value) {
		this.smsCode = value;
	}
	
	public java.lang.String getSmsCode() {
		return this.smsCode;
	}
	public void setMerctId(java.lang.Long value) {
		this.merctId = value;
	}
	
	public java.lang.Long getMerctId() {
		return this.merctId;
	}
	public void setMerctCode(java.lang.String value) {
		this.merctCode = value;
	}
	
	public java.lang.String getMerctCode() {
		return this.merctCode;
	}
	public void setTaskId(java.lang.String value) {
		this.taskId = value;
	}
	
	public java.lang.String getTaskId() {
		return this.taskId;
	}
	public void setMobile(java.lang.String value) {
		this.mobile = value;
	}
	
	public java.lang.String getMobile() {
		return this.mobile;
	}
	public void setExtInfo(java.lang.String value) {
		this.extInfo = value;
	}
	
	public java.lang.String getExtInfo() {
		return this.extInfo;
	}
	public void setStatus(java.lang.String value) {
		this.status = value;
	}
	
	public java.lang.String getStatus() {
		return this.status;
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

	public String getUpUrl() {
		return upUrl;
	}

	public void setUpUrl(String upUrl) {
		this.upUrl = upUrl;
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("SmsId",getSmsId())
			.append("SmsCode",getSmsCode())
			.append("MerctId",getMerctId())
			.append("MerctCode",getMerctCode())
			.append("TaskId",getTaskId())
			.append("Mobile",getMobile())
			.append("ExtInfo",getExtInfo())
			.append("Status",getStatus())
			.append("upUrl", getUpUrl())
			.append("CreateTime",getCreateTime())
			.toString();
	}
	
	public String toString(String separator) {
		StringBuffer sb = new StringBuffer();

			sb.append("id:").append(id).append(separator);
			sb.append("短信ID:").append(smsId).append(separator);
			sb.append("短信代码:").append(smsCode).append(separator);
			sb.append("商户ID:").append(merctId).append(separator);
			sb.append("商户代码:").append(merctCode).append(separator);
			sb.append("发送序列ID:").append(taskId).append(separator);
			sb.append("手机号:").append(mobile).append(separator);
			sb.append("扩展信息:").append(extInfo).append(separator);
			sb.append("状态0有效1失效:").append(status).append(separator);
			sb.append("创建时间:").append(getCreateTimeString()).append(separator);
		
		return sb.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof SmsCode == false) return false;
		if(this == obj) return true;
		SmsCode other = (SmsCode)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}


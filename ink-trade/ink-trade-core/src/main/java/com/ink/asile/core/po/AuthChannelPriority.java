/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.asile.core.po;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.ink.base.BaseEntity;
import com.ink.base.util.DateConvertUtils;

/**
 * @author  zongtt
 * @version 1.0
 * @since 1.0
 */

public class AuthChannelPriority extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "AuthChannelPriority";
//	
//	public static final String ALIAS_ID = "主键";
//	
//	public static final String ALIAS_CHANNEL_NO = "渠道号";
//	
//	public static final String ALIAS_PRIORITY = "优先级，数值越小，优先级越高";
//	
//	public static final String ALIAS_CREATE_TIME = "创建时间";
//	
//	public static final String ALIAS_LAST_UPDATE_TIME = "最后更新时间";
//	
//	public static final String ALIAS_OPERATOR = "操作者";
//	
	
	//date formats
	public static final String FORMAT_CREATE_TIME = DATE_FORMAT;
	public static final String FORMAT_LAST_UPDATE_TIME = DATE_FORMAT;
	
	//columns START
	//主键
	private Long id;
	//渠道号
	private String channelNo;
	//优先级，数值越小，优先级越高
	private Integer priority;
	//创建时间
	private java.util.Date createTime;
	//最后更新时间
	private java.util.Date lastUpdateTime;
	//操作者
	private String operator;
	//columns END

	public AuthChannelPriority(){
	}

	public AuthChannelPriority(
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
	public void setChannelNo(java.lang.String value) {
		this.channelNo = value;
	}
	
	public java.lang.String getChannelNo() {
		return this.channelNo;
	}
	public void setPriority(java.lang.Integer value) {
		this.priority = value;
	}
	
	public java.lang.Integer getPriority() {
		return this.priority;
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
	public String getLastUpdateTimeString() {
		return DateConvertUtils.format(getLastUpdateTime(), FORMAT_LAST_UPDATE_TIME);
	}
	public void setLastUpdateTimeString(String value) {
		setLastUpdateTime(DateConvertUtils.parse(value, FORMAT_LAST_UPDATE_TIME,java.util.Date.class));
	}
	
	public void setLastUpdateTime(java.util.Date value) {
		this.lastUpdateTime = value;
	}
	
	public java.util.Date getLastUpdateTime() {
		return this.lastUpdateTime;
	}
	public void setOperator(java.lang.String value) {
		this.operator = value;
	}
	
	public java.lang.String getOperator() {
		return this.operator;
	}
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("ChannelNo",getChannelNo())
			.append("Priority",getPriority())
			.append("CreateTime",getCreateTime())
			.append("LastUpdateTime",getLastUpdateTime())
			.append("Operator",getOperator())
			.toString();
	}
	
	public String toString(String separator) {
		StringBuffer sb = new StringBuffer();

			sb.append("主键:").append(id).append(separator);
			sb.append("渠道号:").append(channelNo).append(separator);
			sb.append("优先级，数值越小，优先级越高:").append(priority).append(separator);
			sb.append("创建时间:").append(getCreateTimeString()).append(separator);
			sb.append("最后更新时间:").append(getLastUpdateTimeString()).append(separator);
			sb.append("操作者:").append(operator).append(separator);
		
		return sb.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof AuthChannelPriority == false) return false;
		if(this == obj) return true;
		AuthChannelPriority other = (AuthChannelPriority)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}


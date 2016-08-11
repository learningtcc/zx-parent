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

public class MonitorUser extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "MonitorUser";
//	
//	public static final String ALIAS_USER_ID = "用户ID";
//	
//	public static final String ALIAS_USER_NAME = "账号";
//	
//	public static final String ALIAS_FULL_NAME = "姓名";
//	
//	public static final String ALIAS_MOBILE = "手机号";
//	
//	public static final String ALIAS_EMAIL = "邮箱";
//	
//	public static final String ALIAS_STATUS = "状态（0正常、1停用、2删除）";
//	
//	public static final String ALIAS_CREATE_TIME = "创建时间";
//	
//	public static final String ALIAS_UPDATE_TIME = "更新时间";
//	
	
	//date formats
	public static final String FORMAT_CREATE_TIME = DATE_TIME_FORMAT;
	public static final String FORMAT_UPDATE_TIME = DATE_TIME_FORMAT;
	
	//columns START
	//用户ID
	private Integer userId;
	//账号
	private String userName;
	//姓名
	private String fullName;
	//手机号
	private String mobile;
	//邮箱
	private String email;
	//告警类型 0短信、1邮件
	private String warnType;
	//状态（0正常、1停用、2删除）
	private String status;
	//创建时间
	private java.util.Date createTime;
	//更新时间
	private java.util.Date updateTime;
	//登录密码
	private String password;
	//columns END

	public MonitorUser(){
	}

	public MonitorUser(
		java.lang.Integer userId
	){
		this.userId = userId;
	}

	public void setUserId(java.lang.Integer value) {
		this.userId = value;
	}
	
	public java.lang.Integer getUserId() {
		return this.userId;
	}
	public void setUserName(java.lang.String value) {
		this.userName = value;
	}
	
	public java.lang.String getUserName() {
		return this.userName;
	}
	public void setFullName(java.lang.String value) {
		this.fullName = value;
	}
	
	public java.lang.String getFullName() {
		return this.fullName;
	}
	public void setMobile(java.lang.String value) {
		this.mobile = value;
	}
	
	public java.lang.String getMobile() {
		return this.mobile;
	}
	public void setEmail(java.lang.String value) {
		this.email = value;
	}
	
	public java.lang.String getEmail() {
		return this.email;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
			.append("UserId",getUserId())
			.append("UserName",getUserName())
			.append("FullName",getFullName())
			.append("Mobile",getMobile())
			.append("Email",getEmail())
			.append("WarnType",getWarnType())
			.append("Status",getStatus())
			.append("CreateTime",getCreateTime())
			.append("UpdateTime",getUpdateTime())
			.toString();
	}
	
	public String toString(String separator) {
		StringBuffer sb = new StringBuffer();

			sb.append("用户ID:").append(userId).append(separator);
			sb.append("账号:").append(userName).append(separator);
			sb.append("姓名:").append(fullName).append(separator);
			sb.append("手机号:").append(mobile).append(separator);
			sb.append("邮箱:").append(email).append(separator);
			sb.append("告警类型:").append(warnType).append(separator);
			sb.append("状态（0正常、1停用、2删除）:").append(status).append(separator);
			sb.append("创建时间:").append(getCreateTimeString()).append(separator);
			sb.append("更新时间:").append(getUpdateTimeString()).append(separator);
		
		return sb.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getUserId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof MonitorUser == false) return false;
		if(this == obj) return true;
		MonitorUser other = (MonitorUser)obj;
		return new EqualsBuilder()
			.append(getUserId(),other.getUserId())
			.isEquals();
	}

	public String getWarnType() {
		return warnType;
	}

	public void setWarnType(String warnType) {
		this.warnType = warnType;
	}
}
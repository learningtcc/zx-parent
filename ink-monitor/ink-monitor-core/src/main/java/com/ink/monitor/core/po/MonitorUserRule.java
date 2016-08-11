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

public class MonitorUserRule extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "MonitorUserRule";
//	
//	public static final String ALIAS_ID = "id";
//	
//	public static final String ALIAS_USER_ID = "用户ID";
//	
//	public static final String ALIAS_SYS_CODE = "系统代码";
//	
//	public static final String ALIAS_MODULE_CODE = "模块代码";
//	
//	public static final String ALIAS_INFO_CODE = "业务代码";
//	
//	public static final String ALIAS_CREATE_TIME = "创建时间";
//	
	
	//date formats
	public static final String FORMAT_CREATE_TIME = DATE_FORMAT;
	
	//columns START
	//id
	private Integer id;
	//用户ID
	private Integer userId;
	//系统代码
	private String sysCode;
	//模块代码
	private String moduleCode;
	//业务代码
	private String infoCode;
	//创建时间
	private java.util.Date createTime;
	//columns END

	public MonitorUserRule(){
	}

	public MonitorUserRule(
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
	public void setUserId(Integer value) {
		this.userId = value;
	}
	
	public Integer getUserId() {
		return this.userId;
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
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("UserId",getUserId())
			.append("SysCode",getSysCode())
			.append("ModuleCode",getModuleCode())
			.append("InfoCode",getInfoCode())
			.append("CreateTime",getCreateTime())
			.toString();
	}
	
	public String toString(String separator) {
		StringBuffer sb = new StringBuffer();

			sb.append("id:").append(id).append(separator);
			sb.append("用户ID:").append(userId).append(separator);
			sb.append("系统代码:").append(sysCode).append(separator);
			sb.append("模块代码:").append(moduleCode).append(separator);
			sb.append("业务代码:").append(infoCode).append(separator);
			sb.append("创建时间:").append(getCreateTimeString()).append(separator);
		
		return sb.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId()+getSysCode()+getModuleCode()+getInfoCode())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof MonitorUserRule == false) return false;
		if(this == obj) return true;
		MonitorUserRule other = (MonitorUserRule)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}


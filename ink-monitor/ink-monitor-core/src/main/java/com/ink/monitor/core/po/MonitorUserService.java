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

public class MonitorUserService extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "MonitorUserService";
//	
//	public static final String ALIAS_ID = "id";
//	
//	public static final String ALIAS_SERVICE_ID = "服务id";
//	
//	public static final String ALIAS_USER_ID = "用户id";
//	
//	public static final String ALIAS_SYS_CODE = "平台系统编号";
//	
//	public static final String ALIAS_CREATE_TIME = "创建时间";
//	
	
	//date formats
	public static final String FORMAT_CREATE_TIME = DATE_FORMAT;
	
	//columns START
	//id
	private Integer id;
	//服务id
	private Integer serviceId;
	//用户id
	private Integer userId;
	//平台系统编号
	private String sysCode;
	//创建时间
	private java.util.Date createTime;
	//columns END

	private String serviceIds;

	public MonitorUserService(){
	}

	public MonitorUserService(
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
	public void setServiceId(Integer value) {
		this.serviceId = value;
	}
	
	public Integer getServiceId() {
		return this.serviceId;
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
	public String getCreateTimeString() {
		return DateConvertUtils.format(getCreateTime(), FORMAT_CREATE_TIME);
	}
	public void setCreateTimeString(String value) {
		setCreateTime(DateConvertUtils.parse(value, FORMAT_CREATE_TIME,java.util.Date.class));
	}

	public String getServiceIds() {
		return serviceIds;
	}

	public void setServiceIds(String serviceIds) {
		this.serviceIds = serviceIds;
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
			.append("ServiceId",getServiceId())
			.append("UserId",getUserId())
			.append("SysCode",getSysCode())
			.append("CreateTime",getCreateTime())
			.toString();
	}
	
	public String toString(String separator) {
		StringBuffer sb = new StringBuffer();

			sb.append("id:").append(id).append(separator);
			sb.append("服务id:").append(serviceId).append(separator);
			sb.append("用户id:").append(userId).append(separator);
			sb.append("平台系统编号:").append(sysCode).append(separator);
			sb.append("创建时间:").append(getCreateTimeString()).append(separator);
		
		return sb.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof MonitorUserService == false) return false;
		if(this == obj) return true;
		MonitorUserService other = (MonitorUserService)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}


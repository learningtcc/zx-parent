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

public class SystermInfo extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "SystermInfo";
//	
//	public static final String ALIAS_ID = "id";
//	
//	public static final String ALIAS_NAME = "业务名";
//	
//	public static final String ALIAS_CODE = "业务码";
//	
//	public static final String ALIAS_STATUS = "状态";
//	
//	public static final String ALIAS_CREATE_TIME = "创建时间";
//	
//	public static final String ALIAS_UPDATE_TIME = "更新时间";
//	
//	public static final String ALIAS_OP_USER = "操作人";
//	
//	public static final String ALIAS_OP_DESC = "描述";
//	
//	public static final String ALIAS_SYSTERM_CODE = "系统码";
//	
//	public static final String ALIAS_MODULE_CODE = "模块码";
//	
	
	//date formats
	public static final String FORMAT_CREATE_TIME = DATE_FORMAT;
	public static final String FORMAT_UPDATE_TIME = DATE_FORMAT;
	
	//columns START
	//id
	private Integer id;
	//业务名
	private String name;
	//业务码
	private String code;
	//状态
	private String status;
	//创建时间
	private java.util.Date createTime;
	//更新时间
	private java.util.Date updateTime;
	//操作人
	private String opUser;
	//描述
	private String opDesc;
	//系统码
	private String systermCode;
	//模块码
	private String moduleCode;
	//columns END

	public SystermInfo(){
	}

	public SystermInfo(
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
	public void setName(String value) {
		this.name = value;
	}
	
	public String getName() {
		return this.name;
	}
	public void setCode(String value) {
		this.code = value;
	}
	
	public String getCode() {
		return this.code;
	}
	public void setStatus(String value) {
		this.status = value;
	}
	
	public String getStatus() {
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
	public void setOpUser(String value) {
		this.opUser = value;
	}
	
	public String getOpUser() {
		return this.opUser;
	}
	public void setOpDesc(String value) {
		this.opDesc = value;
	}
	
	public String getOpDesc() {
		return this.opDesc;
	}
	public void setSystermCode(String value) {
		this.systermCode = value;
	}
	
	public String getSystermCode() {
		return this.systermCode;
	}
	public void setModuleCode(String value) {
		this.moduleCode = value;
	}
	
	public String getModuleCode() {
		return this.moduleCode;
	}
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Name",getName())
			.append("Code",getCode())
			.append("Status",getStatus())
			.append("CreateTime",getCreateTime())
			.append("UpdateTime",getUpdateTime())
			.append("OpUser",getOpUser())
			.append("OpDesc",getOpDesc())
			.append("SystermCode",getSystermCode())
			.append("ModuleCode",getModuleCode())
			.toString();
	}
	
	public String toString(String separator) {
		StringBuffer sb = new StringBuffer();

			sb.append("id:").append(id).append(separator);
			sb.append("业务名:").append(name).append(separator);
			sb.append("业务码:").append(code).append(separator);
			sb.append("状态:").append(status).append(separator);
			sb.append("创建时间:").append(getCreateTimeString()).append(separator);
			sb.append("更新时间:").append(getUpdateTimeString()).append(separator);
			sb.append("操作人:").append(opUser).append(separator);
			sb.append("描述:").append(opDesc).append(separator);
			sb.append("系统码:").append(systermCode).append(separator);
			sb.append("模块码:").append(moduleCode).append(separator);
		
		return sb.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof SystermInfo == false) return false;
		if(this == obj) return true;
		SystermInfo other = (SystermInfo)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}


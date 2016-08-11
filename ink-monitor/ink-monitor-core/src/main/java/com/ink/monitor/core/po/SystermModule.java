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

public class SystermModule extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "SystermModule";
//	
//	public static final String ALIAS_ID = "id";
//	
//	public static final String ALIAS_SYSTERM_CODE = "系统码";
//	
//	public static final String ALIAS_NAME = "模块名称";
//	
//	public static final String ALIAS_CODE = "模块码";
//	
//	public static final String ALIAS_STATUS = "状态（0，1）";
//	
//	public static final String ALIAS_CREATE_TIME = "createTime";
//	
//	public static final String ALIAS_UPDATE_TIME = "updateTime";
//	
//	public static final String ALIAS_OP_USER = "操作人";
//	
//	public static final String ALIAS_OP_DESC = "描述";
//	
	
	//date formats
	public static final String FORMAT_CREATE_TIME = DATE_FORMAT;
	public static final String FORMAT_UPDATE_TIME = DATE_FORMAT;
	
	//columns START
	//id
	private Integer id;
	//系统码
	private String systermCode;
	//模块名称
	private String name;
	//模块码
	private String code;
	//状态（0，1）
	private String status;
	//createTime
	private java.util.Date createTime;
	//updateTime
	private java.util.Date updateTime;
	//操作人
	private String opUser;
	//描述
	private String opDesc;
	//columns END

	public SystermModule(){
	}

	public SystermModule(
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
	public void setSystermCode(String value) {
		this.systermCode = value;
	}
	
	public String getSystermCode() {
		return this.systermCode;
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
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("SystermCode",getSystermCode())
			.append("Name",getName())
			.append("Code",getCode())
			.append("Status",getStatus())
			.append("CreateTime",getCreateTime())
			.append("UpdateTime",getUpdateTime())
			.append("OpUser",getOpUser())
			.append("OpDesc",getOpDesc())
			.toString();
	}
	
	public String toString(String separator) {
		StringBuffer sb = new StringBuffer();

			sb.append("id:").append(id).append(separator);
			sb.append("系统码:").append(systermCode).append(separator);
			sb.append("模块名称:").append(name).append(separator);
			sb.append("模块码:").append(code).append(separator);
			sb.append("状态（0，1）:").append(status).append(separator);
			sb.append("createTime:").append(getCreateTimeString()).append(separator);
			sb.append("updateTime:").append(getUpdateTimeString()).append(separator);
			sb.append("操作人:").append(opUser).append(separator);
			sb.append("描述:").append(opDesc).append(separator);
		
		return sb.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof SystermModule == false) return false;
		if(this == obj) return true;
		SystermModule other = (SystermModule)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}


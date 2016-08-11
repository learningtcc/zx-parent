/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.yinker.tfs.core.po;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.yinker.base.util.DateConvertUtils;
import com.yinker.base.BaseEntity;

/**
 * @author  zongtt
 * @version 1.0
 * @since 1.0
 */

public class TfsFileName extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "TfsFileName";
//	
//	public static final String ALIAS_ID = "id";
//	
//	public static final String ALIAS_SOURCE_CODE = "系统代码";
//	
//	public static final String ALIAS_MODULE_CODE = "模块代码";
//	
//	public static final String ALIAS_TFS_NAME = "tfs文件名";
//	
//	public static final String ALIAS_FILE_NAME = "文件原始名";
//	
//	public static final String ALIAS_SUFFIX = "文件后缀";
//	
//	public static final String ALIAS_STATUS = "文件状态 0：正常；1：隐藏";
//	
//	public static final String ALIAS_CREATE_TIME = "创建时间";
//	
//	public static final String ALIAS_UPDATE_TIME = "更新时间";
//	
	
	//date formats
	public static final String FORMAT_CREATE_TIME = DATE_FORMAT;
	public static final String FORMAT_UPDATE_TIME = DATE_FORMAT;
	
	//columns START
	//id
	private Long id;
	//系统代码
	private String sourceCode;
	//模块代码
	private String moduleCode;
	//tfs文件名
	private String tfsName;
	//文件原始名
	private String fileName;
	//文件后缀
	private String suffix;
	//文件状态 0：正常；1：隐藏
	private String status;
	//创建时间
	private java.util.Date createTime;
	//更新时间
	private java.util.Date updateTime;
	//文件上传人
	private String uploader;
	//columns END

	public TfsFileName(){
	}

	public TfsFileName(
		Long id
	){
		this.id = id;
	}

	public void setId(Long value) {
		this.id = value;
	}
	
	public Long getId() {
		return this.id;
	}
	public void setSourceCode(String value) {
		this.sourceCode = value;
	}
	
	public String getSourceCode() {
		return this.sourceCode;
	}
	public void setModuleCode(String value) {
		this.moduleCode = value;
	}
	
	public String getModuleCode() {
		return this.moduleCode;
	}
	public void setTfsName(String value) {
		this.tfsName = value;
	}
	
	public String getTfsName() {
		return this.tfsName;
	}
	public void setFileName(String value) {
		this.fileName = value;
	}
	
	public String getFileName() {
		return this.fileName;
	}
	public void setSuffix(String value) {
		this.suffix = value;
	}
	
	public String getSuffix() {
		return this.suffix;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getUploader() {
		return uploader;
	}

	public void setUploader(String uploader) {
		this.uploader = uploader;
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("SourceCode",getSourceCode())
			.append("ModuleCode",getModuleCode())
			.append("TfsName",getTfsName())
			.append("FileName",getFileName())
			.append("Suffix",getSuffix())
			.append("Status",getStatus())
			.append("CreateTime",getCreateTime())
			.append("UpdateTime",getUpdateTime())
			.toString();
	}
	
	public String toString(String separator) {
		StringBuffer sb = new StringBuffer();

			sb.append("id:").append(id).append(separator);
			sb.append("系统代码:").append(sourceCode).append(separator);
			sb.append("模块代码:").append(moduleCode).append(separator);
			sb.append("tfs文件名:").append(tfsName).append(separator);
			sb.append("文件原始名:").append(fileName).append(separator);
			sb.append("文件后缀:").append(suffix).append(separator);
			sb.append("文件状态 0：正常；1：隐藏:").append(status).append(separator);
			sb.append("创建时间:").append(getCreateTimeString()).append(separator);
			sb.append("更新时间:").append(getUpdateTimeString()).append(separator);
		
		return sb.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TfsFileName == false) return false;
		if(this == obj) return true;
		TfsFileName other = (TfsFileName)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}


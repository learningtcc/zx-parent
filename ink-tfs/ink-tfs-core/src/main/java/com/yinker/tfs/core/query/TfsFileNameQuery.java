/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.yinker.tfs.core.query;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Calendar;

import com.yinker.base.BaseQuery;

/**
 * @author  zongtt
 * @version 1.0
 * @since 1.0
 */

public class TfsFileNameQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    
	/** id */
	private Long id;
	/** 系统代码 */
	private String sourceCode;
	/** 模块代码 */
	private String moduleCode;
	/** tfs文件名 */
	private String tfsName;
	/** 文件原始名 */
	private String fileName;
	/** 文件后缀 */
	private String suffix;
	/** 文件状态 0：正常；1：隐藏 */
	private String status;
	/** 创建时间 */
	private java.util.Date createTimeBegin;
	private java.util.Date createTimeEnd;
	/** 更新时间 */
	private java.util.Date updateTimeBegin;
	private java.util.Date updateTimeEnd;
	/**文件上传人*/
	private String uploader;
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long value) {
		this.id = value;
	}
	
	public String getSourceCode() {
		return this.sourceCode;
	}
	
	public void setSourceCode(String value) {
		this.sourceCode = value;
	}
	
	public String getModuleCode() {
		return this.moduleCode;
	}
	
	public void setModuleCode(String value) {
		this.moduleCode = value;
	}
	
	public String getTfsName() {
		return this.tfsName;
	}
	
	public void setTfsName(String value) {
		this.tfsName = value;
	}
	
	public String getFileName() {
		return this.fileName;
	}
	
	public void setFileName(String value) {
		this.fileName = value;
	}
	
	public String getSuffix() {
		return this.suffix;
	}
	
	public void setSuffix(String value) {
		this.suffix = value;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
	
	public java.util.Date getUpdateTimeBegin() {
		return this.updateTimeBegin;
	}
	
	public void setUpdateTimeBegin(java.util.Date value) {
		this.updateTimeBegin = value;
	}	
	
	public java.util.Date getUpdateTimeEnd() {
		return this.updateTimeEnd;
	}
	
	public void setUpdateTimeEnd(java.util.Date value) {
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.updateTimeEnd = calendar.getTime();
		}else {
			this.updateTimeEnd = value;
		}
	}

	public String getUploader() {
		return uploader;
	}

	public void setUploader(String uploader) {
		this.uploader = uploader;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}


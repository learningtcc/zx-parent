/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.monitor.core.query;

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

public class SystermModuleQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    
	/** id */
	private Integer id;
	/** 系统码 */
	private String systermCode;
	/** 模块名称 */
	private String name;
	/** 模块码 */
	private String code;
	/** 状态（0，1） */
	private String status;
	/** createTime */
	private java.util.Date createTimeBegin;
	private java.util.Date createTimeEnd;
	/** updateTime */
	private java.util.Date updateTimeBegin;
	private java.util.Date updateTimeEnd;
	/** 操作人 */
	private String opUser;
	/** 描述 */
	private String opDesc;
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer value) {
		this.id = value;
	}
	
	public String getSystermCode() {
		return this.systermCode;
	}
	
	public void setSystermCode(String value) {
		this.systermCode = value;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String value) {
		this.name = value;
	}
	
	public String getCode() {
		return this.code;
	}
	
	public void setCode(String value) {
		this.code = value;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public void setStatus(String value) {
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
	
	public String getOpUser() {
		return this.opUser;
	}
	
	public void setOpUser(String value) {
		this.opUser = value;
	}
	
	public String getOpDesc() {
		return this.opDesc;
	}
	
	public void setOpDesc(String value) {
		this.opDesc = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}


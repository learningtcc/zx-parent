/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.basic.core.query;

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

public class BasicResCodeQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    
	/** 主键 */
	private java.lang.Long id;
	/** 平台响应码 */
	private java.lang.String resCode;
	/** 平台响应信息 */
	private java.lang.String resMsg;
	/** 是否客户原因 */
	private java.lang.String isCustReason;
	/** 是否可以重提 */
	private java.lang.String isAgain;
	/** 描述 */
	private java.lang.String remark;
	/** 创建时间 */
	private java.util.Date createTimeBegin;
	private java.util.Date createTimeEnd;
	/** createrId */
	private java.lang.Long createrId;
	/** 创建人 */
	private java.lang.String createrName;
	/** updateTime */
	private java.util.Date updateTimeBegin;
	private java.util.Date updateTimeEnd;
	/** updaterId */
	private java.lang.Long updaterId;
	/** updaterName */
	private java.lang.String updaterName;
	/** 是否删除 */
	private java.lang.String isDelete;
	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.String getResCode() {
		return this.resCode;
	}
	
	public void setResCode(java.lang.String value) {
		this.resCode = value;
	}
	
	public java.lang.String getResMsg() {
		return this.resMsg;
	}
	
	public void setResMsg(java.lang.String value) {
		this.resMsg = value;
	}
	
	public java.lang.String getIsCustReason() {
		return this.isCustReason;
	}
	
	public void setIsCustReason(java.lang.String value) {
		this.isCustReason = value;
	}
	
	public java.lang.String getIsAgain() {
		return this.isAgain;
	}
	
	public void setIsAgain(java.lang.String value) {
		this.isAgain = value;
	}
	
	public java.lang.String getRemark() {
		return this.remark;
	}
	
	public void setRemark(java.lang.String value) {
		this.remark = value;
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
	
	public java.lang.Long getCreaterId() {
		return this.createrId;
	}
	
	public void setCreaterId(java.lang.Long value) {
		this.createrId = value;
	}
	
	public java.lang.String getCreaterName() {
		return this.createrName;
	}
	
	public void setCreaterName(java.lang.String value) {
		this.createrName = value;
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
	
	public java.lang.Long getUpdaterId() {
		return this.updaterId;
	}
	
	public void setUpdaterId(java.lang.Long value) {
		this.updaterId = value;
	}
	
	public java.lang.String getUpdaterName() {
		return this.updaterName;
	}
	
	public void setUpdaterName(java.lang.String value) {
		this.updaterName = value;
	}
	
	public java.lang.String getIsDelete() {
		return this.isDelete;
	}
	
	public void setIsDelete(java.lang.String value) {
		this.isDelete = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}


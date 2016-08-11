/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.asile.core.query;

import java.io.Serializable;
import java.util.Calendar;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.ink.base.BaseQuery;

/**
 * @author  zongtt
 * @version 1.0
 * @since 1.0
 */

public class AsileInfoQuery extends BaseQuery implements Serializable {
    
	/**  
	 * @since JDK 1.7  
	 */  
	
	private static final long serialVersionUID = -3549407342652350273L;
	/** id */
	private java.lang.Long id;
	/** asileName */
	private java.lang.String asileName;
	/** asileCode */
	private java.lang.String asileCode;
	/** asileBankType */
	private java.lang.String asileBankType;
	/** 费率名称 */
	private java.lang.Long asileFeeName;
	/** 费率编号 */
	private java.lang.Long asileFeeNo;
	/** asileStatus */
	private java.lang.String asileStatus;
	/** transType */
	private java.lang.String transType;
	/** asileProductCode */
	private java.lang.String asileProductCode;
	/** asileProductName */
	private java.lang.String asileProductName;
	/** asileAuditStatus */
	private java.lang.String asileAuditStatus;
	/** remark */
	private java.lang.String remark;
	/** createTime */
	private java.util.Date createTimeBegin;
	private java.util.Date createTimeEnd;
	/** createrId */
	private java.lang.Long createrId;
	/** createrName */
	private java.lang.String createrName;
	/** updateTime */
	private java.util.Date updateTimeBegin;
	private java.util.Date updateTimeEnd;
	/** updaterId */
	private java.lang.Long updaterId;
	/** updaterName */
	private java.lang.String updaterName;
	/** isDelete */
	private java.lang.Integer isDelete;
	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.String getAsileName() {
		return this.asileName;
	}
	
	public void setAsileName(java.lang.String value) {
		this.asileName = value;
	}
	
	public java.lang.String getAsileCode() {
		return this.asileCode;
	}
	
	public void setAsileCode(java.lang.String value) {
		this.asileCode = value;
	}
	
	public java.lang.String getAsileBankType() {
		return this.asileBankType;
	}
	
	public void setAsileBankType(java.lang.String value) {
		this.asileBankType = value;
	}
	
	public java.lang.Long getAsileFeeName() {
		return this.asileFeeName;
	}
	
	public void setAsileFeeName(java.lang.Long value) {
		this.asileFeeName = value;
	}
	
	public java.lang.Long getAsileFeeNo() {
		return this.asileFeeNo;
	}
	
	public void setAsileFeeNo(java.lang.Long value) {
		this.asileFeeNo = value;
	}
	
	public java.lang.String getAsileStatus() {
		return this.asileStatus;
	}
	
	public void setAsileStatus(java.lang.String value) {
		this.asileStatus = value;
	}
	
	public java.lang.String getTransType() {
		return this.transType;
	}
	
	public void setTransType(java.lang.String value) {
		this.transType = value;
	}
	
	public java.lang.String getAsileProductCode() {
		return this.asileProductCode;
	}
	
	public void setAsileProductCode(java.lang.String value) {
		this.asileProductCode = value;
	}
	
	public java.lang.String getAsileProductName() {
		return this.asileProductName;
	}
	
	public void setAsileProductName(java.lang.String value) {
		this.asileProductName = value;
	}
	
	public java.lang.String getAsileAuditStatus() {
		return this.asileAuditStatus;
	}
	
	public void setAsileAuditStatus(java.lang.String value) {
		this.asileAuditStatus = value;
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
	
	public java.lang.Integer getIsDelete() {
		return this.isDelete;
	}
	
	public void setIsDelete(java.lang.Integer value) {
		this.isDelete = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}


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

public class BasicBankQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    
	/** id */
	private java.lang.Long id;
	/** isDelete */
	private java.lang.String isDelete;
	/** bankName */
	private java.lang.String bankName;
	/** bankShortName */
	private java.lang.String bankShortName;
	/** version */
	private java.lang.Integer version;
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
	/** bankCityCode */
	private java.lang.String bankCityCode;
	/** bankCcpcCode */
	private java.lang.String bankCcpcCode;
	/** bankLevel */
	private java.lang.String bankLevel;
	/** subBankName */
	private java.lang.String subBankName;
	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.String getIsDelete() {
		return this.isDelete;
	}
	
	public void setIsDelete(java.lang.String value) {
		this.isDelete = value;
	}
	
	public java.lang.String getBankName() {
		return this.bankName;
	}
	
	public void setBankName(java.lang.String value) {
		this.bankName = value;
	}
	
	public java.lang.String getBankShortName() {
		return this.bankShortName;
	}
	
	public void setBankShortName(java.lang.String value) {
		this.bankShortName = value;
	}
	
	public java.lang.Integer getVersion() {
		return this.version;
	}
	
	public void setVersion(java.lang.Integer value) {
		this.version = value;
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
	
	public java.lang.String getBankCityCode() {
		return this.bankCityCode;
	}
	
	public void setBankCityCode(java.lang.String value) {
		this.bankCityCode = value;
	}
	
	public java.lang.String getBankCcpcCode() {
		return this.bankCcpcCode;
	}
	
	public void setBankCcpcCode(java.lang.String value) {
		this.bankCcpcCode = value;
	}
	
	public java.lang.String getBankLevel() {
		return this.bankLevel;
	}
	
	public void setBankLevel(java.lang.String value) {
		this.bankLevel = value;
	}
	
	public java.lang.String getSubBankName() {
		return this.subBankName;
	}
	
	public void setSubBankName(java.lang.String value) {
		this.subBankName = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}


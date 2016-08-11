/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.trade.api.platform.basic.model.in;

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

public class MchBankQueryInput extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    
	/** id */
	private java.lang.Long id;
	/** 商户号 */
	private java.lang.String mchId;
	/** 银行名称 */
	private java.lang.String bankName;
	/** 银行简码 */
	private java.lang.String bankShort;
	/** 是否删除 0未删除1已删除 */
	private java.lang.String isDelete;
	/** 创建时间 */
	private java.util.Date createTimeBegin;
	private java.util.Date createTimeEnd;
	/** 最后更新时间 */
	private java.util.Date lastupdateTimeBegin;
	private java.util.Date lastupdateTimeEnd;
	/** 操作人 */
	private java.lang.String operator;
	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.String getMchId() {
		return this.mchId;
	}
	
	public void setMchId(java.lang.String value) {
		this.mchId = value;
	}
	
	public java.lang.String getBankName() {
		return this.bankName;
	}
	
	public void setBankName(java.lang.String value) {
		this.bankName = value;
	}
	
	public java.lang.String getBankShort() {
		return this.bankShort;
	}
	
	public void setBankShort(java.lang.String value) {
		this.bankShort = value;
	}
	
	public java.lang.String getIsDelete() {
		return this.isDelete;
	}
	
	public void setIsDelete(java.lang.String value) {
		this.isDelete = value;
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
	
	public java.util.Date getLastupdateTimeBegin() {
		return this.lastupdateTimeBegin;
	}
	
	public void setLastupdateTimeBegin(java.util.Date value) {
		this.lastupdateTimeBegin = value;
	}	
	
	public java.util.Date getLastupdateTimeEnd() {
		return this.lastupdateTimeEnd;
	}
	
	public void setLastupdateTimeEnd(java.util.Date value) {
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.lastupdateTimeEnd = calendar.getTime();
		}else {
			this.lastupdateTimeEnd = value;
		}
	}
	
	public java.lang.String getOperator() {
		return this.operator;
	}
	
	public void setOperator(java.lang.String value) {
		this.operator = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}


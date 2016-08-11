/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.asile.core.query;

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

public class AsileBusinessQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    
	/** 主键 */
	private java.lang.Long id;
	/** 业务code */
	private java.lang.String businessCode;
	/** 业务名称 */
	private java.lang.String businessName;
	/** 备注 */
	private java.lang.String remark;
	/** 创建时间 */
	private java.util.Date createTimeBegin;
	private java.util.Date createTimeEnd;
	/** 1同步0异步 */
	private java.lang.Integer isSyn;
	/** 渠道编号 */
	private java.lang.String asileCode;
	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.String getBusinessCode() {
		return this.businessCode;
	}
	
	public void setBusinessCode(java.lang.String value) {
		this.businessCode = value;
	}
	
	public java.lang.String getBusinessName() {
		return this.businessName;
	}
	
	public void setBusinessName(java.lang.String value) {
		this.businessName = value;
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
	
	public java.lang.Integer getIsSyn() {
		return this.isSyn;
	}
	
	public void setIsSyn(java.lang.Integer value) {
		this.isSyn = value;
	}
	
	public java.lang.String getAsileCode() {
		return this.asileCode;
	}
	
	public void setAsileCode(java.lang.String value) {
		this.asileCode = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}


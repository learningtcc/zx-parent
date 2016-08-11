/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.trade.api.platform.asile.model.in;

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

public class MchAuthQueryInput extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    
	/** 主键id */
	private java.lang.Long id;
	/** 商户号 */
	private java.lang.String mchId;
	/** 授权的支付方式 */
	private java.lang.String payType;
	/** 1 启用 2 停用 */
	private java.lang.Integer status;
	/** 备注 */
	private java.lang.String remark;
	/** 扩展字段1 */
	private java.lang.String ext1;
	/** 扩展字段2 */
	private java.lang.String ext2;
	/** 创建时间 */
	private java.util.Date createTimeBegin;
	private java.util.Date createTimeEnd;
	/** 操作人 */
	private java.lang.String operator;
	/** 最后更新时间 */
	private java.util.Date lastupdateTimeBegin;
	private java.util.Date lastupdateTimeEnd;
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
	
	public java.lang.String getPayType() {
		return this.payType;
	}
	
	public void setPayType(java.lang.String value) {
		this.payType = value;
	}
	
	public java.lang.Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(java.lang.Integer value) {
		this.status = value;
	}
	
	public java.lang.String getRemark() {
		return this.remark;
	}
	
	public void setRemark(java.lang.String value) {
		this.remark = value;
	}
	
	public java.lang.String getExt1() {
		return this.ext1;
	}
	
	public void setExt1(java.lang.String value) {
		this.ext1 = value;
	}
	
	public java.lang.String getExt2() {
		return this.ext2;
	}
	
	public void setExt2(java.lang.String value) {
		this.ext2 = value;
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
	
	public java.lang.String getOperator() {
		return this.operator;
	}
	
	public void setOperator(java.lang.String value) {
		this.operator = value;
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
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}


/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.user.core.query;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.ink.base.BaseQuery;

/**
 * @author  zongtt
 * @version 1.0
 * @since 1.0
 */

public class AccTypeMchLimitQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    
	/** 数据库表主键 */
	private java.lang.Long id;
	/** 子帐户种类 */
	private java.lang.String sacType;
	/** mchId */
	private java.lang.String mchId;
	/** 日提现金额上限 */
	private BigDecimal dayCashlmtAmt;
	/** 日提现笔数上限 */
	private java.lang.Integer dayCashlmtCnt;
	/** 状态 1-启用 2-停用 9-注销 */
	private java.lang.Boolean status;
	/** version */
	private java.lang.Integer version;
	/** 最后修改时间 */
	private java.util.Date lastUpdateTimeBegin;
	private java.util.Date lastUpdateTimeEnd;
	/** 创建时间 */
	private java.util.Date createTimeBegin;
	private java.util.Date createTimeEnd;
	/** delFlag */
	private java.lang.String delFlag;
	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.String getSacType() {
		return this.sacType;
	}
	
	public void setSacType(java.lang.String value) {
		this.sacType = value;
	}
	
	public java.lang.String getMchId() {
		return this.mchId;
	}
	
	public void setMchId(java.lang.String value) {
		this.mchId = value;
	}
	
	public BigDecimal getDayCashlmtAmt() {
		return this.dayCashlmtAmt;
	}
	
	public void setDayCashlmtAmt(BigDecimal value) {
		this.dayCashlmtAmt = value;
	}
	
	public java.lang.Integer getDayCashlmtCnt() {
		return this.dayCashlmtCnt;
	}
	
	public void setDayCashlmtCnt(java.lang.Integer value) {
		this.dayCashlmtCnt = value;
	}
	
	public java.lang.Boolean getStatus() {
		return this.status;
	}
	
	public void setStatus(java.lang.Boolean value) {
		this.status = value;
	}
	
	public java.lang.Integer getVersion() {
		return this.version;
	}
	
	public void setVersion(java.lang.Integer value) {
		this.version = value;
	}
	
	public java.util.Date getLastUpdateTimeBegin() {
		return this.lastUpdateTimeBegin;
	}
	
	public void setLastUpdateTimeBegin(java.util.Date value) {
		this.lastUpdateTimeBegin = value;
	}	
	
	public java.util.Date getLastUpdateTimeEnd() {
		return this.lastUpdateTimeEnd;
	}
	
	public void setLastUpdateTimeEnd(java.util.Date value) {
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.lastUpdateTimeEnd = calendar.getTime();
		}else {
			this.lastUpdateTimeEnd = value;
		}
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
	
	public java.lang.String getDelFlag() {
		return this.delFlag;
	}
	
	public void setDelFlag(java.lang.String value) {
		this.delFlag = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}


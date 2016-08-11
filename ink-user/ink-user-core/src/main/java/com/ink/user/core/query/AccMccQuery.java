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

public class AccMccQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    
	/** 数据库表主键 */
	private java.lang.Long id;
	/** 创建人 */
	private java.lang.String owner;
	/** 创建人所属机构 */
	private java.lang.String ownerGroup;
	/** 创建时间 */
	private java.util.Date createTimeBegin;
	private java.util.Date createTimeEnd;
	/** 最后修改时间 */
	private java.util.Date lastUpdateTimeBegin;
	private java.util.Date lastUpdateTimeEnd;
	/** 商户类别 */
	private java.lang.String mcc;
	/** 适用范围 */
	private java.lang.String mccRange;
	/** 大类 */
	private java.lang.String masterType;
	/** 细类 */
	private java.lang.String detailType;
	/** 手续费收取模式 1-固定金额收取 2-按比率收取 */
	private java.lang.Boolean feeMode;
	/** 固定手续费金额 */
	private BigDecimal feeFixAmt;
	/** 固定手续费费率, 单位为% */
	private BigDecimal feeRate;
	/** 手续费收取最高金额 */
	private BigDecimal feeMaxAmt;
	/** 备注 */
	private java.lang.String memo;
	/** version */
	private java.lang.Integer version;
	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.String getOwner() {
		return this.owner;
	}
	
	public void setOwner(java.lang.String value) {
		this.owner = value;
	}
	
	public java.lang.String getOwnerGroup() {
		return this.ownerGroup;
	}
	
	public void setOwnerGroup(java.lang.String value) {
		this.ownerGroup = value;
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
	
	public java.lang.String getMcc() {
		return this.mcc;
	}
	
	public void setMcc(java.lang.String value) {
		this.mcc = value;
	}
	
	public java.lang.String getMccRange() {
		return this.mccRange;
	}
	
	public void setMccRange(java.lang.String value) {
		this.mccRange = value;
	}
	
	public java.lang.String getMasterType() {
		return this.masterType;
	}
	
	public void setMasterType(java.lang.String value) {
		this.masterType = value;
	}
	
	public java.lang.String getDetailType() {
		return this.detailType;
	}
	
	public void setDetailType(java.lang.String value) {
		this.detailType = value;
	}
	
	public java.lang.Boolean getFeeMode() {
		return this.feeMode;
	}
	
	public void setFeeMode(java.lang.Boolean value) {
		this.feeMode = value;
	}
	
	public BigDecimal getFeeFixAmt() {
		return this.feeFixAmt;
	}
	
	public void setFeeFixAmt(BigDecimal value) {
		this.feeFixAmt = value;
	}
	
	public BigDecimal getFeeRate() {
		return this.feeRate;
	}
	
	public void setFeeRate(BigDecimal value) {
		this.feeRate = value;
	}
	
	public BigDecimal getFeeMaxAmt() {
		return this.feeMaxAmt;
	}
	
	public void setFeeMaxAmt(BigDecimal value) {
		this.feeMaxAmt = value;
	}
	
	public java.lang.String getMemo() {
		return this.memo;
	}
	
	public void setMemo(java.lang.String value) {
		this.memo = value;
	}
	
	public java.lang.Integer getVersion() {
		return this.version;
	}
	
	public void setVersion(java.lang.Integer value) {
		this.version = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}


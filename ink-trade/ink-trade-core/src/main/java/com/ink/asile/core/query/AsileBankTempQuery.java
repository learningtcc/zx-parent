/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.asile.core.query;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.ink.base.BaseQuery;

/**
 * @author  zongtt
 * @version 1.0
 * @since 1.0
 */

public class AsileBankTempQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    
	/** 主键 */
	private java.lang.Long id;
	/** 通道名称 */
	private java.lang.String asileName;
	/** 通道code */
	private java.lang.String asileCode;
	/** 银行名称 */
	private java.lang.String bankName;
	/** 银行code */
	private java.lang.String bankCode;
	/** 支持银行卡类型 */
	private java.lang.String asileBankType;
	/** 支付类型 */
	private java.lang.String asilePayType;
	//单笔上限
	private BigDecimal asileAmtStart;
	//单笔下限
	private BigDecimal asileAmtEnd;
	/** 单笔限额 */
	private BigDecimal asileCrashLimit;
	/** 单卡单日限额 */
	private BigDecimal cardCrashDayLimit;
	/** 单卡单月限额 */
	private BigDecimal cardCrashMonthLimit;
	/** 单卡单日限额 */
	private java.lang.Integer cardDayLimits;
	/** 备注 */
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
	/** 通道服务开始时间 */
	private java.util.Date asileServiceBeginTime;
	/** 通道服务结束时间 */
	private java.util.Date asileServiceEndTime;
	private Date asileServiceBeginTimeBegin;
	private Date asileServiceBeginTimeEnd;
	private Date asileServiceEndTimeBegin;
	private Date asileServiceEndTimeEnd;
	/** 优先级 */
	private java.lang.Integer priority;
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
	
	public java.lang.String getBankName() {
		return this.bankName;
	}
	
	public void setBankName(java.lang.String value) {
		this.bankName = value;
	}
	
	public BigDecimal getAsileAmtStart() {
		return asileAmtStart;
	}

	public void setAsileAmtStart(BigDecimal asileAmtStart) {
		this.asileAmtStart = asileAmtStart;
	}

	public BigDecimal getAsileAmtEnd() {
		return asileAmtEnd;
	}

	public void setAsileAmtEnd(BigDecimal asileAmtEnd) {
		this.asileAmtEnd = asileAmtEnd;
	}

	public java.lang.String getBankCode() {
		return this.bankCode;
	}
	
	public void setBankCode(java.lang.String value) {
		this.bankCode = value;
	}
	
	public java.lang.String getAsileBankType() {
		return this.asileBankType;
	}
	
	public void setAsileBankType(java.lang.String value) {
		this.asileBankType = value;
	}
	
	public java.lang.String getAsilePayType() {
		return this.asilePayType;
	}
	
	public void setAsilePayType(java.lang.String value) {
		this.asilePayType = value;
	}
	
	public BigDecimal getAsileCrashLimit() {
		return this.asileCrashLimit;
	}
	
	public void setAsileCrashLimit(BigDecimal value) {
		this.asileCrashLimit = value;
	}
	
	public BigDecimal getCardCrashDayLimit() {
		return this.cardCrashDayLimit;
	}
	
	public void setCardCrashDayLimit(BigDecimal value) {
		this.cardCrashDayLimit = value;
	}
	
	public BigDecimal getCardCrashMonthLimit() {
		return this.cardCrashMonthLimit;
	}
	
	public void setCardCrashMonthLimit(BigDecimal value) {
		this.cardCrashMonthLimit = value;
	}
	
	public java.lang.Integer getCardDayLimits() {
		return this.cardDayLimits;
	}
	
	public void setCardDayLimits(java.lang.Integer value) {
		this.cardDayLimits = value;
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
	
	public java.util.Date getAsileServiceBeginTime() {
		return this.asileServiceBeginTime;
	}
	
	public void setAsileServiceBeginTime(java.util.Date value) {
		this.asileServiceBeginTime = value;
	}
	
	public java.util.Date getAsileServiceEndTime() {
		return this.asileServiceEndTime;
	}
	
	public void setAsileServiceEndTime(java.util.Date value) {
		this.asileServiceEndTime = value;
	}
	
	public java.lang.Integer getPriority() {
		return this.priority;
	}
	
	public void setPriority(java.lang.Integer value) {
		this.priority = value;
	}
	

	public Date getAsileServiceBeginTimeBegin() {
		return asileServiceBeginTimeBegin;
	}

	public void setAsileServiceBeginTimeBegin(Date asileServiceBeginTimeBegin) {
		this.asileServiceBeginTimeBegin = asileServiceBeginTimeBegin;
	}

	public Date getAsileServiceBeginTimeEnd() {
		return asileServiceBeginTimeEnd;
	}

	public void setAsileServiceBeginTimeEnd(Date asileServiceBeginTimeEnd) {
		this.asileServiceBeginTimeEnd = asileServiceBeginTimeEnd;
	}

	public Date getAsileServiceEndTimeBegin() {
		return asileServiceEndTimeBegin;
	}

	public void setAsileServiceEndTimeBegin(Date asileServiceEndTimeBegin) {
		this.asileServiceEndTimeBegin = asileServiceEndTimeBegin;
	}

	public Date getAsileServiceEndTimeEnd() {
		return asileServiceEndTimeEnd;
	}

	public void setAsileServiceEndTimeEnd(Date asileServiceEndTimeEnd) {
		this.asileServiceEndTimeEnd = asileServiceEndTimeEnd;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}


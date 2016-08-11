/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.user.core.query;

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

public class AccProofQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    
	/** 数据库表主键 */
	private java.lang.Long id;
	/** 创建时间 */
	private Date createTimeBegin;
	private Date createTimeEnd;
	/** 最后修改时间 */
	private Date lastUpdateTimeBegin;
	private Date lastUpdateTimeEnd;
	/** uid */
	private java.lang.Long uid;
	/** 主账号 */
	private java.lang.Long pacId;
	/** 子帐户号 */
	private java.lang.Long sacId;
	/** 子帐户种类 */
	private java.lang.String sacType;
	/** 币种 */
	private java.lang.String cur;
	/** 之前金额 */
	private BigDecimal beforeBal;
	/** 当前余额 */
	private BigDecimal curBal;
	/** txnCode */
	private java.lang.String txnCode;
	/** 修改金额 */
	private BigDecimal modifyBal;
	/** 预留字段1 */
	private java.lang.String filler1;
	/** 预留字段2 */
	private java.lang.String filler2;
	/** 预留字段3 */
	private java.lang.String filler3;
	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public Date getCreateTimeBegin() {
		return this.createTimeBegin;
	}
	
	public void setCreateTimeBegin(Date value) {
		this.createTimeBegin = value;
	}	
	
	public Date getCreateTimeEnd() {
		return this.createTimeEnd;
	}
	
	public void setCreateTimeEnd(Date value) {
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.createTimeEnd = calendar.getTime();
		}else {
			this.createTimeEnd = value;
		}
	}
	
	public Date getLastUpdateTimeBegin() {
		return this.lastUpdateTimeBegin;
	}
	
	public void setLastUpdateTimeBegin(Date value) {
		this.lastUpdateTimeBegin = value;
	}	
	
	public Date getLastUpdateTimeEnd() {
		return this.lastUpdateTimeEnd;
	}
	
	public void setLastUpdateTimeEnd(Date value) {
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.lastUpdateTimeEnd = calendar.getTime();
		}else {
			this.lastUpdateTimeEnd = value;
		}
	}
	
	public java.lang.Long getUid() {
		return this.uid;
	}
	
	public void setUid(java.lang.Long value) {
		this.uid = value;
	}
	
	public java.lang.Long getPacId() {
		return this.pacId;
	}
	
	public void setPacId(java.lang.Long value) {
		this.pacId = value;
	}
	
	public java.lang.Long getSacId() {
		return this.sacId;
	}
	
	public void setSacId(java.lang.Long value) {
		this.sacId = value;
	}
	
	public java.lang.String getSacType() {
		return this.sacType;
	}
	
	public void setSacType(java.lang.String value) {
		this.sacType = value;
	}
	
	public java.lang.String getCur() {
		return this.cur;
	}
	
	public void setCur(java.lang.String value) {
		this.cur = value;
	}
	
	public BigDecimal getBeforeBal() {
		return this.beforeBal;
	}
	
	public void setBeforeBal(BigDecimal value) {
		this.beforeBal = value;
	}
	
	public BigDecimal getCurBal() {
		return this.curBal;
	}
	
	public void setCurBal(BigDecimal value) {
		this.curBal = value;
	}
	
	public java.lang.String getTxnCode() {
		return this.txnCode;
	}
	
	public void setTxnCode(java.lang.String value) {
		this.txnCode = value;
	}
	
	public BigDecimal getModifyBal() {
		return this.modifyBal;
	}
	
	public void setModifyBal(BigDecimal value) {
		this.modifyBal = value;
	}
	
	public java.lang.String getFiller1() {
		return this.filler1;
	}
	
	public void setFiller1(java.lang.String value) {
		this.filler1 = value;
	}
	
	public java.lang.String getFiller2() {
		return this.filler2;
	}
	
	public void setFiller2(java.lang.String value) {
		this.filler2 = value;
	}
	
	public java.lang.String getFiller3() {
		return this.filler3;
	}
	
	public void setFiller3(java.lang.String value) {
		this.filler3 = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}


/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.user.core.query;

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

public class AccCustProofQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    
	/** 数据库表主键 */
	private java.lang.Long id;
	/** 创建时间 */
	private java.util.Date createTimeBegin;
	private java.util.Date createTimeEnd;
	/** 最后修改时间 */
	private java.util.Date lastUpdateTimeBegin;
	private java.util.Date lastUpdateTimeEnd;
	/** uid */
	private java.lang.Long uid;
	/** 修改前的证件类型 */
	private java.lang.String beforeIdType;
	/** 修改前的证件号码 */
	private java.lang.String beforeIdNo;
	/** 修改后的证件类型 */
	private java.lang.String idType;
	/** 修改后的证件号码 */
	private java.lang.String idNo;
	/** 修改前的手机号 */
	private java.lang.String beforeMblNo;
	/** 修改后的手机号 */
	private java.lang.String mblNo;
	/** 客户ID */
	private java.lang.Long custId;
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
	
	public java.lang.Long getUid() {
		return this.uid;
	}
	
	public void setUid(java.lang.Long value) {
		this.uid = value;
	}
	
	public java.lang.String getBeforeIdType() {
		return this.beforeIdType;
	}
	
	public void setBeforeIdType(java.lang.String value) {
		this.beforeIdType = value;
	}
	
	public java.lang.String getBeforeIdNo() {
		return this.beforeIdNo;
	}
	
	public void setBeforeIdNo(java.lang.String value) {
		this.beforeIdNo = value;
	}
	
	public java.lang.String getIdType() {
		return this.idType;
	}
	
	public void setIdType(java.lang.String value) {
		this.idType = value;
	}
	
	public java.lang.String getIdNo() {
		return this.idNo;
	}
	
	public void setIdNo(java.lang.String value) {
		this.idNo = value;
	}
	
	public java.lang.String getBeforeMblNo() {
		return this.beforeMblNo;
	}
	
	public void setBeforeMblNo(java.lang.String value) {
		this.beforeMblNo = value;
	}
	
	public java.lang.String getMblNo() {
		return this.mblNo;
	}
	
	public void setMblNo(java.lang.String value) {
		this.mblNo = value;
	}
	
	public java.lang.Long getCustId() {
		return this.custId;
	}
	
	public void setCustId(java.lang.Long value) {
		this.custId = value;
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


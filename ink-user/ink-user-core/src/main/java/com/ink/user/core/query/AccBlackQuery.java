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

public class AccBlackQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    
	/** 数据库表主键 */
	private java.lang.Long id;
	/** 版本号 */
	private java.lang.Integer version;
	/** 开户日期 */
	private java.util.Date openDateBegin;
	private java.util.Date openDateEnd;
	/** 销户日期 */
	private java.util.Date closeDateBegin;
	private java.util.Date closeDateEnd;
	/** 状态 1-启用 2-停用 9-注销 */
	private java.lang.Integer status;
	/** 删除标识 0-正常 1-删除 */
	private java.lang.Boolean delFlag;
	/** 创建时间 */
	private java.util.Date createTimeBegin;
	private java.util.Date createTimeEnd;
	/** 最后修改时间 */
	private java.util.Date lastUpdateTimeBegin;
	private java.util.Date lastUpdateTimeEnd;
	/** accId */
	private java.lang.Long accId;
	/** accTypeId */
	private java.lang.String accTypeId;
	/** txnCode */
	private java.lang.String txnCode;
	/** accDepositType */
	private java.lang.String accDepositType;
	/** blackWhy */
	private java.lang.String blackWhy;
	/** blackStartDate */
	private java.util.Date blackStartDate;
	/** blackEndDate */
	private java.util.Date blackEndDate;
	/** blackStartTime */
	private java.util.Date blackStartTime;
	/** blackEndTime */
	private java.util.Date blackEndTime;
	/** blackType */
	private java.lang.String blackType;
	/** accessIp */
	private java.lang.String accessIp;
	/** 客户号 */
	private java.lang.Long uid;
	/** 姓名 */
	private java.lang.String custName;
	/** 联系移动电话 */
	private java.lang.String mblNo;
	/** 证件号码 */
	private java.lang.String idNo;
	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.Integer getVersion() {
		return this.version;
	}
	
	public void setVersion(java.lang.Integer value) {
		this.version = value;
	}
	
	public java.util.Date getOpenDateBegin() {
		return this.openDateBegin;
	}
	
	public void setOpenDateBegin(java.util.Date value) {
		this.openDateBegin = value;
	}	
	
	public java.util.Date getOpenDateEnd() {
		return this.openDateEnd;
	}
	
	public void setOpenDateEnd(java.util.Date value) {
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.openDateEnd = calendar.getTime();
		}else {
			this.openDateEnd = value;
		}
	}
	
	public java.util.Date getCloseDateBegin() {
		return this.closeDateBegin;
	}
	
	public void setCloseDateBegin(java.util.Date value) {
		this.closeDateBegin = value;
	}	
	
	public java.util.Date getCloseDateEnd() {
		return this.closeDateEnd;
	}
	
	public void setCloseDateEnd(java.util.Date value) {
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.closeDateEnd = calendar.getTime();
		}else {
			this.closeDateEnd = value;
		}
	}
	
	public java.lang.Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(java.lang.Integer value) {
		this.status = value;
	}
	
	public java.lang.Boolean getDelFlag() {
		return this.delFlag;
	}
	
	public void setDelFlag(java.lang.Boolean value) {
		this.delFlag = value;
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
	
	public java.lang.Long getAccId() {
		return this.accId;
	}
	
	public void setAccId(java.lang.Long value) {
		this.accId = value;
	}
	
	public java.lang.String getAccTypeId() {
		return this.accTypeId;
	}
	
	public void setAccTypeId(java.lang.String value) {
		this.accTypeId = value;
	}
	
	public java.lang.String getTxnCode() {
		return this.txnCode;
	}
	
	public void setTxnCode(java.lang.String value) {
		this.txnCode = value;
	}
	
	public java.lang.String getAccDepositType() {
		return this.accDepositType;
	}
	
	public void setAccDepositType(java.lang.String value) {
		this.accDepositType = value;
	}
	
	public java.lang.String getBlackWhy() {
		return this.blackWhy;
	}
	
	public void setBlackWhy(java.lang.String value) {
		this.blackWhy = value;
	}
	
	public java.util.Date getBlackStartDate() {
		return this.blackStartDate;
	}
	
	public void setBlackStartDate(java.util.Date value) {
		this.blackStartDate = value;
	}
	
	public java.util.Date getBlackEndDate() {
		return this.blackEndDate;
	}
	
	public void setBlackEndDate(java.util.Date value) {
		this.blackEndDate = value;
	}
	
	public java.util.Date getBlackStartTime() {
		return this.blackStartTime;
	}
	
	public void setBlackStartTime(java.util.Date value) {
		this.blackStartTime = value;
	}
	
	public java.util.Date getBlackEndTime() {
		return this.blackEndTime;
	}
	
	public void setBlackEndTime(java.util.Date value) {
		this.blackEndTime = value;
	}
	
	public java.lang.String getBlackType() {
		return this.blackType;
	}
	
	public void setBlackType(java.lang.String value) {
		this.blackType = value;
	}
	
	public java.lang.String getAccessIp() {
		return this.accessIp;
	}
	
	public void setAccessIp(java.lang.String value) {
		this.accessIp = value;
	}
	
	public java.lang.Long getUid() {
		return this.uid;
	}
	
	public void setUid(java.lang.Long value) {
		this.uid = value;
	}
	
	public java.lang.String getCustName() {
		return this.custName;
	}
	
	public void setCustName(java.lang.String value) {
		this.custName = value;
	}
	
	public java.lang.String getMblNo() {
		return this.mblNo;
	}
	
	public void setMblNo(java.lang.String value) {
		this.mblNo = value;
	}
	
	public java.lang.String getIdNo() {
		return this.idNo;
	}
	
	public void setIdNo(java.lang.String value) {
		this.idNo = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}


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

public class AccIacQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    
	/** 数据库表主键 */
	private java.lang.Long id;
	/** version */
	private java.lang.Integer version;
	/** 内部账户号 */
	private java.lang.Long iacId;
	/** 币种 */
	private java.lang.String cur;
	/** 上级科目号 */
	private java.lang.String upItemId;
	/** 内部账户类型 1-商户账户 2-机构账户 3-科目账户 */
	private java.lang.Boolean iacType;
	/** 昨日余额 日终批处理更新 */
	private BigDecimal lstBal;
	/** 当前余额 */
	private BigDecimal curBal;
	/** 临时余额 日终使用 */
	private BigDecimal tmpBal;
	/** 最低余额 */
	private BigDecimal minBal;
	/** 余额方向 */
	private java.lang.String balDir;
	/** 交易方向 */
	private java.lang.Boolean txnDir;
	/** 开户日期 */
	private java.util.Date openDateBegin;
	private java.util.Date openDateEnd;
	/** 销户日期 */
	private java.util.Date closeDateBegin;
	private java.util.Date closeDateEnd;
	/** 状态 1-启用 2-停用 9-注销 */
	private java.lang.Boolean status;
	/** 内部账户认证信息 */
	private java.lang.String iacMac;
	/** 删除标识 0-正常 1-删除 */
	private java.lang.Boolean delFlag;
	/** 预留字段1 */
	private java.lang.String filler1;
	/** 预留字段2 */
	private java.lang.String filler2;
	/** 预留字段3 */
	private java.lang.String filler3;
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
	/** mchId */
	private java.lang.Long mchId;
	/** orgId */
	private java.lang.Long orgId;
	/** accDepositType */
	private java.lang.String accDepositType;
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
	
	public java.lang.Long getIacId() {
		return this.iacId;
	}
	
	public void setIacId(java.lang.Long value) {
		this.iacId = value;
	}
	
	public java.lang.String getCur() {
		return this.cur;
	}
	
	public void setCur(java.lang.String value) {
		this.cur = value;
	}
	
	public java.lang.String getUpItemId() {
		return this.upItemId;
	}
	
	public void setUpItemId(java.lang.String value) {
		this.upItemId = value;
	}
	
	public java.lang.Boolean getIacType() {
		return this.iacType;
	}
	
	public void setIacType(java.lang.Boolean value) {
		this.iacType = value;
	}
	
	public BigDecimal getLstBal() {
		return this.lstBal;
	}
	
	public void setLstBal(BigDecimal value) {
		this.lstBal = value;
	}
	
	public BigDecimal getCurBal() {
		return this.curBal;
	}
	
	public void setCurBal(BigDecimal value) {
		this.curBal = value;
	}
	
	public BigDecimal getTmpBal() {
		return this.tmpBal;
	}
	
	public void setTmpBal(BigDecimal value) {
		this.tmpBal = value;
	}
	
	public BigDecimal getMinBal() {
		return this.minBal;
	}
	
	public void setMinBal(BigDecimal value) {
		this.minBal = value;
	}
	
	public java.lang.String getBalDir() {
		return this.balDir;
	}
	
	public void setBalDir(java.lang.String value) {
		this.balDir = value;
	}
	
	public java.lang.Boolean getTxnDir() {
		return this.txnDir;
	}
	
	public void setTxnDir(java.lang.Boolean value) {
		this.txnDir = value;
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
	
	public java.lang.Boolean getStatus() {
		return this.status;
	}
	
	public void setStatus(java.lang.Boolean value) {
		this.status = value;
	}
	
	public java.lang.String getIacMac() {
		return this.iacMac;
	}
	
	public void setIacMac(java.lang.String value) {
		this.iacMac = value;
	}
	
	public java.lang.Boolean getDelFlag() {
		return this.delFlag;
	}
	
	public void setDelFlag(java.lang.Boolean value) {
		this.delFlag = value;
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
	
	public java.lang.Long getMchId() {
		return this.mchId;
	}
	
	public void setMchId(java.lang.Long value) {
		this.mchId = value;
	}
	
	public java.lang.Long getOrgId() {
		return this.orgId;
	}
	
	public void setOrgId(java.lang.Long value) {
		this.orgId = value;
	}
	
	public java.lang.String getAccDepositType() {
		return this.accDepositType;
	}
	
	public void setAccDepositType(java.lang.String value) {
		this.accDepositType = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}


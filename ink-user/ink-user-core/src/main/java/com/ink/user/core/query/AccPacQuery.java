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

public class AccPacQuery extends BaseQuery implements Serializable {
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
	/** 主账号 */
	private java.lang.String pacId;
	/** 商户信息表主键 */
	private java.lang.Long accProdId;
	/** 产品编号(帐户级别) 0001-未实名账户 0002-已实名账户 0003-VIP帐户 0004-公司账户 */
	private java.lang.String prodId;
	/** 客户类型 0-个人 1-单位 */
	private java.lang.Boolean custType;
	/** 日收入金额累计 */
	private BigDecimal dayInAmt;
	/** 日收入笔数累计 */
	private java.lang.Integer dayInCnt;
	/** 日支出金额累计 */
	private BigDecimal dayPayAmt;
	/** 日支出笔数累计 */
	private java.lang.Integer dayPayCnt;
	/** 月收入金额累计 */
	private BigDecimal monInAmt;
	/** 月收入笔数累计 */
	private java.lang.Integer monInCnt;
	/** 月支出金额累计 */
	private BigDecimal monPayAmt;
	/** 月支出笔数累计 */
	private java.lang.Integer monPayCnt;
	/** 日免签支付金额累计 */
	private BigDecimal dayNopswdAmt;
	/** 日免签支付笔数累计 */
	private java.lang.Integer dayNopswdCnt;
	/** 日收入金额上限 */
	private BigDecimal dayInlmtAmt;
	/** 日收入笔数上限 */
	private java.lang.Integer dayInlmtCnt;
	/** 日支出金额上限 */
	private BigDecimal dayPaylmtAmt;
	/** 日支出笔数上限 */
	private java.lang.Integer dayPaylmtCnt;
	/** 月收入金额上限 */
	private BigDecimal monInlmtAmt;
	/** 月收入笔数上限 */
	private java.lang.Integer monInlmtCnt;
	/** 月支出金额上限 */
	private BigDecimal monPaylmtAmt;
	/** 月支出笔数上限 */
	private java.lang.Integer monPaylmtCnt;
	/** 日免签支付金额上限 */
	private BigDecimal dayNopswdlmtAmt;
	/** 日免签支付笔数上限 */
	private java.lang.Integer dayNopswdlmtCnt;
	/** 开户日期 */
	private java.util.Date openDateBegin;
	private java.util.Date openDateEnd;
	/** 销户日期 */
	private java.util.Date closeDateBegin;
	private java.util.Date closeDateEnd;
	/** 状态 1-启用 2-停用 9-注销 */
	private java.lang.Boolean status;
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
	
	public java.lang.String getPacId() {
		return this.pacId;
	}
	
	public void setPacId(java.lang.String value) {
		this.pacId = value;
	}
	
	public java.lang.Long getAccProdId() {
		return this.accProdId;
	}
	
	public void setAccProdId(java.lang.Long value) {
		this.accProdId = value;
	}
	
	public java.lang.String getProdId() {
		return this.prodId;
	}
	
	public void setProdId(java.lang.String value) {
		this.prodId = value;
	}
	
	public java.lang.Boolean getCustType() {
		return this.custType;
	}
	
	public void setCustType(java.lang.Boolean value) {
		this.custType = value;
	}
	
	public BigDecimal getDayInAmt() {
		return this.dayInAmt;
	}
	
	public void setDayInAmt(BigDecimal value) {
		this.dayInAmt = value;
	}
	
	public java.lang.Integer getDayInCnt() {
		return this.dayInCnt;
	}
	
	public void setDayInCnt(java.lang.Integer value) {
		this.dayInCnt = value;
	}
	
	public BigDecimal getDayPayAmt() {
		return this.dayPayAmt;
	}
	
	public void setDayPayAmt(BigDecimal value) {
		this.dayPayAmt = value;
	}
	
	public java.lang.Integer getDayPayCnt() {
		return this.dayPayCnt;
	}
	
	public void setDayPayCnt(java.lang.Integer value) {
		this.dayPayCnt = value;
	}
	
	public BigDecimal getMonInAmt() {
		return this.monInAmt;
	}
	
	public void setMonInAmt(BigDecimal value) {
		this.monInAmt = value;
	}
	
	public java.lang.Integer getMonInCnt() {
		return this.monInCnt;
	}
	
	public void setMonInCnt(java.lang.Integer value) {
		this.monInCnt = value;
	}
	
	public BigDecimal getMonPayAmt() {
		return this.monPayAmt;
	}
	
	public void setMonPayAmt(BigDecimal value) {
		this.monPayAmt = value;
	}
	
	public java.lang.Integer getMonPayCnt() {
		return this.monPayCnt;
	}
	
	public void setMonPayCnt(java.lang.Integer value) {
		this.monPayCnt = value;
	}
	
	public BigDecimal getDayNopswdAmt() {
		return this.dayNopswdAmt;
	}
	
	public void setDayNopswdAmt(BigDecimal value) {
		this.dayNopswdAmt = value;
	}
	
	public java.lang.Integer getDayNopswdCnt() {
		return this.dayNopswdCnt;
	}
	
	public void setDayNopswdCnt(java.lang.Integer value) {
		this.dayNopswdCnt = value;
	}
	
	public BigDecimal getDayInlmtAmt() {
		return this.dayInlmtAmt;
	}
	
	public void setDayInlmtAmt(BigDecimal value) {
		this.dayInlmtAmt = value;
	}
	
	public java.lang.Integer getDayInlmtCnt() {
		return this.dayInlmtCnt;
	}
	
	public void setDayInlmtCnt(java.lang.Integer value) {
		this.dayInlmtCnt = value;
	}
	
	public BigDecimal getDayPaylmtAmt() {
		return this.dayPaylmtAmt;
	}
	
	public void setDayPaylmtAmt(BigDecimal value) {
		this.dayPaylmtAmt = value;
	}
	
	public java.lang.Integer getDayPaylmtCnt() {
		return this.dayPaylmtCnt;
	}
	
	public void setDayPaylmtCnt(java.lang.Integer value) {
		this.dayPaylmtCnt = value;
	}
	
	public BigDecimal getMonInlmtAmt() {
		return this.monInlmtAmt;
	}
	
	public void setMonInlmtAmt(BigDecimal value) {
		this.monInlmtAmt = value;
	}
	
	public java.lang.Integer getMonInlmtCnt() {
		return this.monInlmtCnt;
	}
	
	public void setMonInlmtCnt(java.lang.Integer value) {
		this.monInlmtCnt = value;
	}
	
	public BigDecimal getMonPaylmtAmt() {
		return this.monPaylmtAmt;
	}
	
	public void setMonPaylmtAmt(BigDecimal value) {
		this.monPaylmtAmt = value;
	}
	
	public java.lang.Integer getMonPaylmtCnt() {
		return this.monPaylmtCnt;
	}
	
	public void setMonPaylmtCnt(java.lang.Integer value) {
		this.monPaylmtCnt = value;
	}
	
	public BigDecimal getDayNopswdlmtAmt() {
		return this.dayNopswdlmtAmt;
	}
	
	public void setDayNopswdlmtAmt(BigDecimal value) {
		this.dayNopswdlmtAmt = value;
	}
	
	public java.lang.Integer getDayNopswdlmtCnt() {
		return this.dayNopswdlmtCnt;
	}
	
	public void setDayNopswdlmtCnt(java.lang.Integer value) {
		this.dayNopswdlmtCnt = value;
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


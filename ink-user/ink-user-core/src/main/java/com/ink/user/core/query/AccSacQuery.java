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

public class AccSacQuery extends BaseQuery implements Serializable {
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
	/** 子帐户号 */
	private java.lang.String sacId;
	/** 主帐户控制表主键 */
	private java.lang.Long accPacId;
	/** 主账号 */
	private java.lang.String pacId;
	/** 子帐户类型表主键 */
	private java.lang.Long accSacTypeId;
	/** 子帐户种类 */
	private java.lang.String sacType;
	/** 日充值金额累计 */
	private BigDecimal dayVarchargeAmt;
	/** 日充值笔数累计 */
	private java.lang.Integer dayVarchargeCnt;
	/** 日消费金额累计 */
	private BigDecimal dayPayAmt;
	/** 日消费笔数累计 */
	private java.lang.Integer dayPayCnt;
	/** 日转入金额累计 */
	private BigDecimal dayInAmt;
	/** 日转入笔数累计 */
	private java.lang.Integer dayInCnt;
	/** 日转出金额累计 */
	private BigDecimal dayOutAmt;
	/** 日转出笔数累计 */
	private java.lang.Integer dayOutCnt;
	/** 日提现金额累计 */
	private BigDecimal dayCashAmt;
	/** 日提现笔数累计 */
	private java.lang.Integer dayCashCnt;
	/** 月充值金额累计 */
	private BigDecimal monVarchargeAmt;
	/** 月充值笔数累计 */
	private java.lang.Integer monVarchargeCnt;
	/** 月消费金额累计 */
	private BigDecimal monPayAmt;
	/** 月消费笔数累计 */
	private java.lang.Integer monPayCnt;
	/** 月转入金额累计 */
	private BigDecimal monInAmt;
	/** 月转入笔数累计 */
	private java.lang.Integer monInCnt;
	/** 月转出金额累计 */
	private BigDecimal monOutAmt;
	/** 月转出笔数累计 */
	private java.lang.Integer monOutCnt;
	/** 月提现金额累计 */
	private BigDecimal monCashAmt;
	/** 月提现笔数累计 */
	private java.lang.Integer monCashCnt;
	/** 日充值金额上限 */
	private BigDecimal dayVarchargelmtAmt;
	/** 日充值笔数上限 */
	private java.lang.Integer dayVarchargelmtCnt;
	/** 日消费金额上限 */
	private BigDecimal dayPaylmtAmt;
	/** 日消费笔数上限 */
	private java.lang.Integer dayPaylmtCnt;
	/** 日转入金额上限 */
	private BigDecimal dayInlmtAmt;
	/** 日转入笔数上限 */
	private java.lang.Integer dayInlmtCnt;
	/** 日转出金额上限 */
	private BigDecimal dayOutlmtAmt;
	/** 日转出笔数上限 */
	private java.lang.Integer dayOutlmtCnt;
	/** 日提现金额上限 */
	private BigDecimal dayCashlmtAmt;
	/** 日提现笔数上限 */
	private java.lang.Integer dayCashlmtCnt;
	/** 月充值金额上限 */
	private BigDecimal monVarchargelmtAmt;
	/** 月充值笔数上限 */
	private java.lang.Integer monVarchargelmtCnt;
	/** 月消费金额上限 */
	private BigDecimal monPaylmtAmt;
	/** 月消费笔数上限 */
	private java.lang.Integer monPaylmtCnt;
	/** 月转入金额上限 */
	private BigDecimal monInlmtAmt;
	/** 月转入笔数上限 */
	private java.lang.Integer monInlmtCnt;
	/** 月转出金额上限 */
	private BigDecimal monOutlmtAmt;
	/** 月转出笔数上限 */
	private java.lang.Integer monOutlmtCnt;
	/** 月提现金额上限 */
	private BigDecimal monCashlmtAmt;
	/** 月提现笔数上限 */
	private java.lang.Integer monCashlmtCnt;
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
	
	public java.lang.String getSacId() {
		return this.sacId;
	}
	
	public void setSacId(java.lang.String value) {
		this.sacId = value;
	}
	
	public java.lang.Long getAccPacId() {
		return this.accPacId;
	}
	
	public void setAccPacId(java.lang.Long value) {
		this.accPacId = value;
	}
	
	public java.lang.String getPacId() {
		return this.pacId;
	}
	
	public void setPacId(java.lang.String value) {
		this.pacId = value;
	}
	
	public java.lang.Long getAccSacTypeId() {
		return this.accSacTypeId;
	}
	
	public void setAccSacTypeId(java.lang.Long value) {
		this.accSacTypeId = value;
	}
	
	public java.lang.String getSacType() {
		return this.sacType;
	}
	
	public void setSacType(java.lang.String value) {
		this.sacType = value;
	}
	
	public BigDecimal getDayVarchargeAmt() {
		return this.dayVarchargeAmt;
	}
	
	public void setDayVarchargeAmt(BigDecimal value) {
		this.dayVarchargeAmt = value;
	}
	
	public java.lang.Integer getDayVarchargeCnt() {
		return this.dayVarchargeCnt;
	}
	
	public void setDayVarchargeCnt(java.lang.Integer value) {
		this.dayVarchargeCnt = value;
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
	
	public BigDecimal getDayOutAmt() {
		return this.dayOutAmt;
	}
	
	public void setDayOutAmt(BigDecimal value) {
		this.dayOutAmt = value;
	}
	
	public java.lang.Integer getDayOutCnt() {
		return this.dayOutCnt;
	}
	
	public void setDayOutCnt(java.lang.Integer value) {
		this.dayOutCnt = value;
	}
	
	public BigDecimal getDayCashAmt() {
		return this.dayCashAmt;
	}
	
	public void setDayCashAmt(BigDecimal value) {
		this.dayCashAmt = value;
	}
	
	public java.lang.Integer getDayCashCnt() {
		return this.dayCashCnt;
	}
	
	public void setDayCashCnt(java.lang.Integer value) {
		this.dayCashCnt = value;
	}
	
	public BigDecimal getMonVarchargeAmt() {
		return this.monVarchargeAmt;
	}
	
	public void setMonVarchargeAmt(BigDecimal value) {
		this.monVarchargeAmt = value;
	}
	
	public java.lang.Integer getMonVarchargeCnt() {
		return this.monVarchargeCnt;
	}
	
	public void setMonVarchargeCnt(java.lang.Integer value) {
		this.monVarchargeCnt = value;
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
	
	public BigDecimal getMonOutAmt() {
		return this.monOutAmt;
	}
	
	public void setMonOutAmt(BigDecimal value) {
		this.monOutAmt = value;
	}
	
	public java.lang.Integer getMonOutCnt() {
		return this.monOutCnt;
	}
	
	public void setMonOutCnt(java.lang.Integer value) {
		this.monOutCnt = value;
	}
	
	public BigDecimal getMonCashAmt() {
		return this.monCashAmt;
	}
	
	public void setMonCashAmt(BigDecimal value) {
		this.monCashAmt = value;
	}
	
	public java.lang.Integer getMonCashCnt() {
		return this.monCashCnt;
	}
	
	public void setMonCashCnt(java.lang.Integer value) {
		this.monCashCnt = value;
	}
	
	public BigDecimal getDayVarchargelmtAmt() {
		return this.dayVarchargelmtAmt;
	}
	
	public void setDayVarchargelmtAmt(BigDecimal value) {
		this.dayVarchargelmtAmt = value;
	}
	
	public java.lang.Integer getDayVarchargelmtCnt() {
		return this.dayVarchargelmtCnt;
	}
	
	public void setDayVarchargelmtCnt(java.lang.Integer value) {
		this.dayVarchargelmtCnt = value;
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
	
	public BigDecimal getDayOutlmtAmt() {
		return this.dayOutlmtAmt;
	}
	
	public void setDayOutlmtAmt(BigDecimal value) {
		this.dayOutlmtAmt = value;
	}
	
	public java.lang.Integer getDayOutlmtCnt() {
		return this.dayOutlmtCnt;
	}
	
	public void setDayOutlmtCnt(java.lang.Integer value) {
		this.dayOutlmtCnt = value;
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
	
	public BigDecimal getMonVarchargelmtAmt() {
		return this.monVarchargelmtAmt;
	}
	
	public void setMonVarchargelmtAmt(BigDecimal value) {
		this.monVarchargelmtAmt = value;
	}
	
	public java.lang.Integer getMonVarchargelmtCnt() {
		return this.monVarchargelmtCnt;
	}
	
	public void setMonVarchargelmtCnt(java.lang.Integer value) {
		this.monVarchargelmtCnt = value;
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
	
	public BigDecimal getMonOutlmtAmt() {
		return this.monOutlmtAmt;
	}
	
	public void setMonOutlmtAmt(BigDecimal value) {
		this.monOutlmtAmt = value;
	}
	
	public java.lang.Integer getMonOutlmtCnt() {
		return this.monOutlmtCnt;
	}
	
	public void setMonOutlmtCnt(java.lang.Integer value) {
		this.monOutlmtCnt = value;
	}
	
	public BigDecimal getMonCashlmtAmt() {
		return this.monCashlmtAmt;
	}
	
	public void setMonCashlmtAmt(BigDecimal value) {
		this.monCashlmtAmt = value;
	}
	
	public java.lang.Integer getMonCashlmtCnt() {
		return this.monCashlmtCnt;
	}
	
	public void setMonCashlmtCnt(java.lang.Integer value) {
		this.monCashlmtCnt = value;
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


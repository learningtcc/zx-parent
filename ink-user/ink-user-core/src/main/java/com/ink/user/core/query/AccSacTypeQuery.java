/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.user.core.query;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import com.ink.base.BaseQuery;

/**
 * @author  zongtt
 * @version 1.0
 * @since 1.0
 */

public class AccSacTypeQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    
	/** 数据库表主键 */
	private Long id;
	/** 创建时间 */
	private Date createTimeBegin;
	private Date createTimeEnd;
	/** 最后修改时间 */
	private Date lastUpdateTimeBegin;
	private Date lastUpdateTimeEnd;
	/** 子帐户种类 */
	private String sacType;
	/** 子帐户名称 */
	private String sacName;
	/** 币种 */
	private String cur;
	/** 日充值金额上限 */
	private BigDecimal dayVarchargelmtAmt;
	/** 日充值笔数上限 */
	private Integer dayVarchargelmtCnt;
	/** 日消费金额上限 */
	private BigDecimal dayPaylmtAmt;
	/** 日消费笔数上限 */
	private Integer dayPaylmtCnt;
	/** 日转入金额上限 */
	private BigDecimal dayInlmtAmt;
	/** 日转入笔数上限 */
	private Integer dayInlmtCnt;
	/** 日转出金额上限 */
	private BigDecimal dayOutlmtAmt;
	/** 日转出笔数上限 */
	private Integer dayOutlmtCnt;
	/** 日提现金额上限 */
	private BigDecimal dayCashlmtAmt;
	/** 日提现笔数上限 */
	private Integer dayCashlmtCnt;
	/** 月充值金额上限 */
	private BigDecimal monVarchargelmtAmt;
	/** 月充值笔数上限 */
	private Integer monVarchargelmtCnt;
	/** 月消费金额上限 */
	private BigDecimal monPaylmtAmt;
	/** 月消费笔数上限 */
	private Integer monPaylmtCnt;
	/** 月转入金额上限 */
	private BigDecimal monInlmtAmt;
	/** 月转入笔数上限 */
	private Integer monInlmtCnt;
	/** 月转出金额上限 */
	private BigDecimal monOutlmtAmt;
	/** 月转出笔数上限 */
	private Integer monOutlmtCnt;
	/** 月提现金额上限 */
	private BigDecimal monCashlmtAmt;
	/** 月提现笔数上限 */
	private Integer monCashlmtCnt;
	/** 状态 1-启用 2-停用 9-注销 */
	private Integer status;
	/** version */
	private Integer version;
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long value) {
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
	
	public String getSacType() {
		return this.sacType;
	}
	
	public void setSacType(String value) {
		this.sacType = value;
	}
	
	public String getSacName() {
		return this.sacName;
	}
	
	public void setSacName(String value) {
		this.sacName = value;
	}
	
	public String getCur() {
		return this.cur;
	}
	
	public void setCur(String value) {
		this.cur = value;
	}
	
	public BigDecimal getDayVarchargelmtAmt() {
		return this.dayVarchargelmtAmt;
	}
	
	public void setDayVarchargelmtAmt(BigDecimal value) {
		this.dayVarchargelmtAmt = value;
	}
	
	public Integer getDayVarchargelmtCnt() {
		return this.dayVarchargelmtCnt;
	}
	
	public void setDayVarchargelmtCnt(Integer value) {
		this.dayVarchargelmtCnt = value;
	}
	
	public BigDecimal getDayPaylmtAmt() {
		return this.dayPaylmtAmt;
	}
	
	public void setDayPaylmtAmt(BigDecimal value) {
		this.dayPaylmtAmt = value;
	}
	
	public Integer getDayPaylmtCnt() {
		return this.dayPaylmtCnt;
	}
	
	public void setDayPaylmtCnt(Integer value) {
		this.dayPaylmtCnt = value;
	}
	
	public BigDecimal getDayInlmtAmt() {
		return this.dayInlmtAmt;
	}
	
	public void setDayInlmtAmt(BigDecimal value) {
		this.dayInlmtAmt = value;
	}
	
	public Integer getDayInlmtCnt() {
		return this.dayInlmtCnt;
	}
	
	public void setDayInlmtCnt(Integer value) {
		this.dayInlmtCnt = value;
	}
	
	public BigDecimal getDayOutlmtAmt() {
		return this.dayOutlmtAmt;
	}
	
	public void setDayOutlmtAmt(BigDecimal value) {
		this.dayOutlmtAmt = value;
	}
	
	public Integer getDayOutlmtCnt() {
		return this.dayOutlmtCnt;
	}
	
	public void setDayOutlmtCnt(Integer value) {
		this.dayOutlmtCnt = value;
	}
	
	public BigDecimal getDayCashlmtAmt() {
		return this.dayCashlmtAmt;
	}
	
	public void setDayCashlmtAmt(BigDecimal value) {
		this.dayCashlmtAmt = value;
	}
	
	public Integer getDayCashlmtCnt() {
		return this.dayCashlmtCnt;
	}
	
	public void setDayCashlmtCnt(Integer value) {
		this.dayCashlmtCnt = value;
	}
	
	public BigDecimal getMonVarchargelmtAmt() {
		return this.monVarchargelmtAmt;
	}
	
	public void setMonVarchargelmtAmt(BigDecimal value) {
		this.monVarchargelmtAmt = value;
	}
	
	public Integer getMonVarchargelmtCnt() {
		return this.monVarchargelmtCnt;
	}
	
	public void setMonVarchargelmtCnt(Integer value) {
		this.monVarchargelmtCnt = value;
	}
	
	public BigDecimal getMonPaylmtAmt() {
		return this.monPaylmtAmt;
	}
	
	public void setMonPaylmtAmt(BigDecimal value) {
		this.monPaylmtAmt = value;
	}
	
	public Integer getMonPaylmtCnt() {
		return this.monPaylmtCnt;
	}
	
	public void setMonPaylmtCnt(Integer value) {
		this.monPaylmtCnt = value;
	}
	
	public BigDecimal getMonInlmtAmt() {
		return this.monInlmtAmt;
	}
	
	public void setMonInlmtAmt(BigDecimal value) {
		this.monInlmtAmt = value;
	}
	
	public Integer getMonInlmtCnt() {
		return this.monInlmtCnt;
	}
	
	public void setMonInlmtCnt(Integer value) {
		this.monInlmtCnt = value;
	}
	
	public BigDecimal getMonOutlmtAmt() {
		return this.monOutlmtAmt;
	}
	
	public void setMonOutlmtAmt(BigDecimal value) {
		this.monOutlmtAmt = value;
	}
	
	public Integer getMonOutlmtCnt() {
		return this.monOutlmtCnt;
	}
	
	public void setMonOutlmtCnt(Integer value) {
		this.monOutlmtCnt = value;
	}
	
	public BigDecimal getMonCashlmtAmt() {
		return this.monCashlmtAmt;
	}
	
	public void setMonCashlmtAmt(BigDecimal value) {
		this.monCashlmtAmt = value;
	}
	
	public Integer getMonCashlmtCnt() {
		return this.monCashlmtCnt;
	}
	
	public void setMonCashlmtCnt(Integer value) {
		this.monCashlmtCnt = value;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getVersion() {
		return this.version;
	}
	
	public void setVersion(Integer value) {
		this.version = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}


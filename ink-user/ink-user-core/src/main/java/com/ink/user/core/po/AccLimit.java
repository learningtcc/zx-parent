package com.ink.user.core.po;

import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.ink.base.BaseEntity;

/**
 * @author  zongtt
 * @version 1.0
 * @since 1.0
 */

public class AccLimit extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "AccLimit";
//	
//	public static final String ALIAS_ID = "物理主键";
//	
//	public static final String ALIAS_PAC_ID = "主账户ID";
//	
//	public static final String ALIAS_SAC_ID = "子账户ID";
//	
//	public static final String ALIAS_SAC_TYPE = "子账户类型";
//	
//	public static final String ALIAS_TRADE_DATE = "记账日期";
//	
//	public static final String ALIAS_DAY_CASH_AMT = "日提现金额累计";
//	
//	public static final String ALIAS_DAY_CASH_CNT = "日提现笔数累计";
//	
//	public static final String ALIAS_DAY_CASHLMT_AMT = "日提现金额上限";
//	
//	public static final String ALIAS_DAY_CASHLMT_CNT = "日提现笔数上限";
//	
//	public static final String ALIAS_VERSION = "version";
//	
	
	//date formats
	
	//columns START
	//物理主键
	private Long id;
	//主账户ID
	private Long pacId;
	//子账户ID
	private Long sacId;
	//子账户类型
	private String sacType;
	//记账日期
	private String tradeDate;
	//日提现金额累计
	private BigDecimal dayCashAmt;
	//日提现笔数累计
	private Integer dayCashCnt;
	//日提现金额上限
	private BigDecimal dayCashlmtAmt;
	//日提现笔数上限
	private Integer dayCashlmtCnt;
	//version
	private Integer version;
	//columns END

	public AccLimit(){
	}

	public AccLimit(
		java.lang.Long id
	){
		this.id = id;
	}

	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.Long getId() {
		return this.id;
	}
	public void setPacId(java.lang.Long value) {
		this.pacId = value;
	}
	
	public java.lang.Long getPacId() {
		return this.pacId;
	}
	public void setSacId(java.lang.Long value) {
		this.sacId = value;
	}
	
	public java.lang.Long getSacId() {
		return this.sacId;
	}
	public void setSacType(java.lang.String value) {
		this.sacType = value;
	}
	
	public java.lang.String getSacType() {
		return this.sacType;
	}
	public void setTradeDate(java.lang.String value) {
		this.tradeDate = value;
	}
	
	public java.lang.String getTradeDate() {
		return this.tradeDate;
	}
	public void setDayCashAmt(BigDecimal value) {
		this.dayCashAmt = value;
	}
	
	public BigDecimal getDayCashAmt() {
		return this.dayCashAmt;
	}
	public void setDayCashCnt(java.lang.Integer value) {
		this.dayCashCnt = value;
	}
	
	public java.lang.Integer getDayCashCnt() {
		return this.dayCashCnt;
	}
	public void setDayCashlmtAmt(BigDecimal value) {
		this.dayCashlmtAmt = value;
	}
	
	public BigDecimal getDayCashlmtAmt() {
		return this.dayCashlmtAmt;
	}
	public void setDayCashlmtCnt(java.lang.Integer value) {
		this.dayCashlmtCnt = value;
	}
	
	public java.lang.Integer getDayCashlmtCnt() {
		return this.dayCashlmtCnt;
	}
	public void setVersion(java.lang.Integer value) {
		this.version = value;
	}
	
	public java.lang.Integer getVersion() {
		return this.version;
	}
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("PacId",getPacId())
			.append("SacId",getSacId())
			.append("SacType",getSacType())
			.append("TradeDate",getTradeDate())
			.append("DayCashAmt",getDayCashAmt())
			.append("DayCashCnt",getDayCashCnt())
			.append("DayCashlmtAmt",getDayCashlmtAmt())
			.append("DayCashlmtCnt",getDayCashlmtCnt())
			.append("Version",getVersion())
			.toString();
	}
	
	public String toString(String separator) {
		StringBuffer sb = new StringBuffer();

			sb.append("物理主键:").append(id).append(separator);
			sb.append("主账户ID:").append(pacId).append(separator);
			sb.append("子账户ID:").append(sacId).append(separator);
			sb.append("子账户类型:").append(sacType).append(separator);
			sb.append("记账日期:").append(tradeDate).append(separator);
			sb.append("日提现金额累计:").append(dayCashAmt).append(separator);
			sb.append("日提现笔数累计:").append(dayCashCnt).append(separator);
			sb.append("日提现金额上限:").append(dayCashlmtAmt).append(separator);
			sb.append("日提现笔数上限:").append(dayCashlmtCnt).append(separator);
			sb.append("version:").append(version).append(separator);
		
		return sb.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof AccLimit == false) return false;
		if(this == obj) return true;
		AccLimit other = (AccLimit)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}


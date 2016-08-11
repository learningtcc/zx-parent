/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.user.core.po;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.ink.base.BaseEntity;
import com.ink.base.util.DateConvertUtils;

/**
 * @author  zongtt
 * @version 1.0
 * @since 1.0
 */

public class AccProof extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "AccProof";
//	
//	public static final String ALIAS_ID = "数据库表主键";
//	
//	public static final String ALIAS_CREATE_TIME = "创建时间";
//	
//	public static final String ALIAS_LAST_UPDATE_TIME = "最后修改时间";
//	
//	public static final String ALIAS_UID = "uid";
//	
//	public static final String ALIAS_PAC_ID = "主账号";
//	
//	public static final String ALIAS_SAC_ID = "子帐户号";
//	
//	public static final String ALIAS_SAC_TYPE = "子帐户种类";
//	
//	public static final String ALIAS_CUR = "币种";
//	
//	public static final String ALIAS_BEFORE_BAL = "之前金额";
//	
//	public static final String ALIAS_CUR_BAL = "当前余额";
//	
//	public static final String ALIAS_TXN_CODE = "txnCode";
//	
//	public static final String ALIAS_MODIFY_BAL = "修改金额";
//	
//	public static final String ALIAS_FILLER1 = "预留字段1";
//	
//	public static final String ALIAS_FILLER2 = "预留字段2";
//	
//	public static final String ALIAS_FILLER3 = "预留字段3";
//	
	
	//date formats
	public static final String FORMAT_CREATE_TIME = DATE_TIME_FORMAT;
	public static final String FORMAT_LAST_UPDATE_TIME = DATE_TIME_FORMAT;
	
	//columns START
	//数据库表主键
	private Long id;
	//创建时间
	private Date createTime;
	//最后修改时间
	private Date lastUpdateTime;
	//uid
	private Long uid;
	//主账号
	private Long pacId;
	//子帐户号
	private Long sacId;
	//子帐户种类
	private String sacType;
	//币种
	private String cur;
	//之前金额
	private BigDecimal beforeBal;
	//当前余额
	private BigDecimal curBal;
	//txnCode
	private String txnCode;
	//修改金额
	private BigDecimal modifyBal;
	//预留字段1
	private String filler1;
	//预留字段2
	private String filler2;
	//预留字段3
	private String filler3;
	//columns END

	public AccProof(){
	}

	public AccProof(
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
	public String getCreateTimeString() {
		return DateConvertUtils.format(getCreateTime(), FORMAT_CREATE_TIME);
	}
	public void setCreateTimeString(String value) {
		setCreateTime(DateConvertUtils.parse(value, FORMAT_CREATE_TIME,Date.class));
	}
	
	public void setCreateTime(Date value) {
		this.createTime = value;
	}
	
	public Date getCreateTime() {
		return this.createTime;
	}
	public String getLastUpdateTimeString() {
		return DateConvertUtils.format(getLastUpdateTime(), FORMAT_LAST_UPDATE_TIME);
	}
	public void setLastUpdateTimeString(String value) {
		setLastUpdateTime(DateConvertUtils.parse(value, FORMAT_LAST_UPDATE_TIME,Date.class));
	}
	
	public void setLastUpdateTime(Date value) {
		this.lastUpdateTime = value;
	}
	
	public Date getLastUpdateTime() {
		return this.lastUpdateTime;
	}
	public void setUid(java.lang.Long value) {
		this.uid = value;
	}
	
	public java.lang.Long getUid() {
		return this.uid;
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
	public void setCur(java.lang.String value) {
		this.cur = value;
	}
	
	public java.lang.String getCur() {
		return this.cur;
	}
	public void setBeforeBal(BigDecimal value) {
		this.beforeBal = value;
	}
	
	public BigDecimal getBeforeBal() {
		return this.beforeBal;
	}
	public void setCurBal(BigDecimal value) {
		this.curBal = value;
	}
	
	public BigDecimal getCurBal() {
		return this.curBal;
	}
	public void setTxnCode(java.lang.String value) {
		this.txnCode = value;
	}
	
	public java.lang.String getTxnCode() {
		return this.txnCode;
	}
	public void setModifyBal(BigDecimal value) {
		this.modifyBal = value;
	}
	
	public BigDecimal getModifyBal() {
		return this.modifyBal;
	}
	public void setFiller1(java.lang.String value) {
		this.filler1 = value;
	}
	
	public java.lang.String getFiller1() {
		return this.filler1;
	}
	public void setFiller2(java.lang.String value) {
		this.filler2 = value;
	}
	
	public java.lang.String getFiller2() {
		return this.filler2;
	}
	public void setFiller3(java.lang.String value) {
		this.filler3 = value;
	}
	
	public java.lang.String getFiller3() {
		return this.filler3;
	}
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("CreateTime",getCreateTime())
			.append("LastUpdateTime",getLastUpdateTime())
			.append("Uid",getUid())
			.append("PacId",getPacId())
			.append("SacId",getSacId())
			.append("SacType",getSacType())
			.append("Cur",getCur())
			.append("BeforeBal",getBeforeBal())
			.append("CurBal",getCurBal())
			.append("TxnCode",getTxnCode())
			.append("ModifyBal",getModifyBal())
			.append("Filler1",getFiller1())
			.append("Filler2",getFiller2())
			.append("Filler3",getFiller3())
			.toString();
	}
	
	public String toString(String separator) {
		StringBuffer sb = new StringBuffer();

			sb.append("数据库表主键:").append(id).append(separator);
			sb.append("创建时间:").append(getCreateTimeString()).append(separator);
			sb.append("最后修改时间:").append(getLastUpdateTimeString()).append(separator);
			sb.append("uid:").append(uid).append(separator);
			sb.append("主账号:").append(pacId).append(separator);
			sb.append("子帐户号:").append(sacId).append(separator);
			sb.append("子帐户种类:").append(sacType).append(separator);
			sb.append("币种:").append(cur).append(separator);
			sb.append("之前金额:").append(beforeBal).append(separator);
			sb.append("当前余额:").append(curBal).append(separator);
			sb.append("txnCode:").append(txnCode).append(separator);
			sb.append("修改金额:").append(modifyBal).append(separator);
			sb.append("预留字段1:").append(filler1).append(separator);
			sb.append("预留字段2:").append(filler2).append(separator);
			sb.append("预留字段3:").append(filler3).append(separator);
		
		return sb.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof AccProof == false) return false;
		if(this == obj) return true;
		AccProof other = (AccProof)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
	
	public String getPkValue() {
		if(getId() != null){
			return String.valueOf(getId());
		}
		
		return "";
	}
}


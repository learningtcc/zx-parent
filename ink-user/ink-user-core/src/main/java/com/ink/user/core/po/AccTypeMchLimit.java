/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.user.core.po;

import java.math.BigDecimal;
import java.util.Date;

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

public class AccTypeMchLimit extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "商户限额控制表";
//	
//	public static final String ALIAS_ID = "数据库表主键";
//	
//	public static final String ALIAS_SAC_TYPE = "子帐户种类";
//	
//	public static final String ALIAS_MCH_ID = "mchId";
//	
//	public static final String ALIAS_DAY_CASHLMT_AMT = "日提现金额上限";
//	
//	public static final String ALIAS_DAY_CASHLMT_CNT = "日提现笔数上限";
//	
//	public static final String ALIAS_STATUS = "状态 1-启用 2-停用 9-注销";
//	
//	public static final String ALIAS_VERSION = "version";
//	
//	public static final String ALIAS_LAST_UPDATE_TIME = "最后修改时间";
//	
//	public static final String ALIAS_CREATE_TIME = "创建时间";
//	
//	public static final String ALIAS_DEL_FLAG = "delFlag";
//	
	
	//date formats
	public static final String FORMAT_LAST_UPDATE_TIME = DATE_TIME_FORMAT;
	public static final String FORMAT_CREATE_TIME = DATE_TIME_FORMAT;
	
	//columns START
	//数据库表主键
	private Long id;
	//子帐户种类
	private String sacType;
	//mchId
	private Long mchId;
	//日提现金额上限
	private BigDecimal dayCashlmtAmt;
	//日提现笔数上限
	private Integer dayCashlmtCnt;
	//状态 1-启用 2-停用 9-注销
	private Integer status;
	//version
	private Long version;
	//最后修改时间
	private Date lastUpdateTime;
	//创建时间
	private Date createTime;
	//delFlag
	private Integer delFlag;
	//columns END

	public AccTypeMchLimit(){
	}

	public AccTypeMchLimit(
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
	public void setSacType(String value) {
		this.sacType = value;
	}
	
	public String getSacType() {
		return this.sacType;
	}
	public void setMchId(java.lang.Long value) {
		this.mchId = value;
	}
	
	public java.lang.Long getMchId() {
		return this.mchId;
	}
	public void setDayCashlmtAmt(BigDecimal value) {
		this.dayCashlmtAmt = value;
	}
	
	public BigDecimal getDayCashlmtAmt() {
		return this.dayCashlmtAmt;
	}
	public void setDayCashlmtCnt(Integer value) {
		this.dayCashlmtCnt = value;
	}
	
	public Integer getDayCashlmtCnt() {
		return this.dayCashlmtCnt;
	}
	public void setStatus(java.lang.Integer value) {
		this.status = value;
	}
	
	public java.lang.Integer getStatus() {
		return this.status;
	}
	public void setVersion(java.lang.Long value) {
		this.version = value;
	}
	
	public java.lang.Long getVersion() {
		return this.version;
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
	public void setDelFlag(java.lang.Integer value) {
		this.delFlag = value;
	}
	
	public java.lang.Integer getDelFlag() {
		return this.delFlag;
	}
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("SacType",getSacType())
			.append("MchId",getMchId())
			.append("DayCashlmtAmt",getDayCashlmtAmt())
			.append("DayCashlmtCnt",getDayCashlmtCnt())
			.append("Status",getStatus())
			.append("Version",getVersion())
			.append("LastUpdateTime",getLastUpdateTime())
			.append("CreateTime",getCreateTime())
			.append("DelFlag",getDelFlag())
			.toString();
	}
	
	public String toString(String separator) {
		StringBuffer sb = new StringBuffer();

			sb.append("数据库表主键:").append(id).append(separator);
			sb.append("子帐户种类:").append(sacType).append(separator);
			sb.append("mchId:").append(mchId).append(separator);
			sb.append("日提现金额上限:").append(dayCashlmtAmt).append(separator);
			sb.append("日提现笔数上限:").append(dayCashlmtCnt).append(separator);
			sb.append("状态 1-启用 2-停用 9-注销:").append(status).append(separator);
			sb.append("version:").append(version).append(separator);
			sb.append("最后修改时间:").append(getLastUpdateTimeString()).append(separator);
			sb.append("创建时间:").append(getCreateTimeString()).append(separator);
			sb.append("delFlag:").append(delFlag).append(separator);
		
		return sb.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public String getPkValue() {
		if(getId() != null){
			return String.valueOf(getId());
		}
		
		return "";
	}
}


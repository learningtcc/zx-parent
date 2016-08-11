/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.trade.core.po;

import com.ink.base.BaseEntity;
import com.ink.base.util.DateConvertUtils;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * @author  zongtt
 * @version 1.0
 * @since 1.0
 */

public class UserBalance extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "UserBalance";
//	
//	public static final String ALIAS_ID = "主键";
//	
//	public static final String ALIAS_MCH_ID = "商户号";
//	
//	public static final String ALIAS_USER_ID = "用户号";
//	
//	public static final String ALIAS_BALANCE = "余额";
//	
//	public static final String ALIAS_CREATE_TIME = "创建时间";
//	
//	public static final String ALIAS_LAST_UPDATETIME = "最后更新时间";
//	
//	public static final String ALIAS_VERSION = "版本号";
//	
	
	//date formats
	public static final String FORMAT_CREATE_TIME = DATE_FORMAT;
	public static final String FORMAT_LAST_UPDATETIME = DATE_FORMAT;
	
	//columns START
	//主键
	private Long id;
	//商户号
	private String mchId;
	//用户号
	private String userId;
	//余额
	private BigDecimal balance;
	//创建时间
	private java.util.Date createTime;
	//最后更新时间
	private java.util.Date lastUpdatetime;
	//版本号
	private Integer version;
	//columns END

	public UserBalance(){
	}

	public UserBalance(
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
	public void setMchId(java.lang.String value) {
		this.mchId = value;
	}
	
	public java.lang.String getMchId() {
		return this.mchId;
	}
	public void setUserId(java.lang.String value) {
		this.userId = value;
	}
	
	public java.lang.String getUserId() {
		return this.userId;
	}
	public void setBalance(BigDecimal value) {
		this.balance = value;
	}
	
	public BigDecimal getBalance() {
		return this.balance;
	}
	public String getCreateTimeString() {
		return DateConvertUtils.format(getCreateTime(), FORMAT_CREATE_TIME);
	}
	public void setCreateTimeString(String value) {
		setCreateTime(DateConvertUtils.parse(value, FORMAT_CREATE_TIME,java.util.Date.class));
	}
	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	public String getLastUpdatetimeString() {
		return DateConvertUtils.format(getLastUpdatetime(), FORMAT_LAST_UPDATETIME);
	}
	public void setLastUpdatetimeString(String value) {
		setLastUpdatetime(DateConvertUtils.parse(value, FORMAT_LAST_UPDATETIME,java.util.Date.class));
	}
	
	public void setLastUpdatetime(java.util.Date value) {
		this.lastUpdatetime = value;
	}
	
	public java.util.Date getLastUpdatetime() {
		return this.lastUpdatetime;
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
			.append("MchId",getMchId())
			.append("UserId",getUserId())
			.append("Balance",getBalance())
			.append("CreateTime",getCreateTime())
			.append("LastUpdatetime",getLastUpdatetime())
			.append("Version",getVersion())
			.toString();
	}
	
	public String toString(String separator) {
		StringBuffer sb = new StringBuffer();

			sb.append("主键:").append(id).append(separator);
			sb.append("商户号:").append(mchId).append(separator);
			sb.append("用户号:").append(userId).append(separator);
			sb.append("余额:").append(balance).append(separator);
			sb.append("创建时间:").append(getCreateTimeString()).append(separator);
			sb.append("最后更新时间:").append(getLastUpdatetimeString()).append(separator);
			sb.append("版本号:").append(version).append(separator);
		
		return sb.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof UserBalance == false) return false;
		if(this == obj) return true;
		UserBalance other = (UserBalance)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}


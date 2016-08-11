/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.trade.core.po;

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

public class MchBank extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "MchBank";
//	
//	public static final String ALIAS_ID = "id";
//	
//	public static final String ALIAS_MCH_ID = "商户号";
//	
//	public static final String ALIAS_BANK_NAME = "银行名称";
//	
//	public static final String ALIAS_BANK_SHORT = "银行简码";
//	
//	public static final String ALIAS_IS_DELETE = "是否删除 0未删除1已删除";
//	
//	public static final String ALIAS_CREATE_TIME = "创建时间";
//	
//	public static final String ALIAS_LASTUPDATE_TIME = "最后更新时间";
//	
//	public static final String ALIAS_OPERATOR = "操作人";
//	
	
	//date formats
	public static final String FORMAT_CREATE_TIME = DATE_FORMAT;
	public static final String FORMAT_LASTUPDATE_TIME = DATE_FORMAT;
	
	//columns START
	//id
	private Long id;
	//商户号
	private String mchId;
	//银行名称
	private String bankName;
	//银行简码
	private String bankShort;
	//是否删除 0未删除1已删除
	private String isDelete;
	//创建时间
	private java.util.Date createTime;
	//最后更新时间
	private java.util.Date lastupdateTime;
	//操作人
	private String operator;
	//columns END

	public MchBank(){
	}

	public MchBank(
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
	public void setBankName(java.lang.String value) {
		this.bankName = value;
	}
	
	public java.lang.String getBankName() {
		return this.bankName;
	}
	public void setBankShort(java.lang.String value) {
		this.bankShort = value;
	}
	
	public java.lang.String getBankShort() {
		return this.bankShort;
	}
	public void setIsDelete(java.lang.String value) {
		this.isDelete = value;
	}
	
	public java.lang.String getIsDelete() {
		return this.isDelete;
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
	public String getLastupdateTimeString() {
		return DateConvertUtils.format(getLastupdateTime(), FORMAT_LASTUPDATE_TIME);
	}
	public void setLastupdateTimeString(String value) {
		setLastupdateTime(DateConvertUtils.parse(value, FORMAT_LASTUPDATE_TIME,java.util.Date.class));
	}
	
	public void setLastupdateTime(java.util.Date value) {
		this.lastupdateTime = value;
	}
	
	public java.util.Date getLastupdateTime() {
		return this.lastupdateTime;
	}
	public void setOperator(java.lang.String value) {
		this.operator = value;
	}
	
	public java.lang.String getOperator() {
		return this.operator;
	}
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("MchId",getMchId())
			.append("BankName",getBankName())
			.append("BankShort",getBankShort())
			.append("IsDelete",getIsDelete())
			.append("CreateTime",getCreateTime())
			.append("LastupdateTime",getLastupdateTime())
			.append("Operator",getOperator())
			.toString();
	}
	
	public String toString(String separator) {
		StringBuffer sb = new StringBuffer();

			sb.append("id:").append(id).append(separator);
			sb.append("商户号:").append(mchId).append(separator);
			sb.append("银行名称:").append(bankName).append(separator);
			sb.append("银行简码:").append(bankShort).append(separator);
			sb.append("是否删除 0未删除1已删除:").append(isDelete).append(separator);
			sb.append("创建时间:").append(getCreateTimeString()).append(separator);
			sb.append("最后更新时间:").append(getLastupdateTimeString()).append(separator);
			sb.append("操作人:").append(operator).append(separator);
		
		return sb.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof MchBank == false) return false;
		if(this == obj) return true;
		MchBank other = (MchBank)obj;
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


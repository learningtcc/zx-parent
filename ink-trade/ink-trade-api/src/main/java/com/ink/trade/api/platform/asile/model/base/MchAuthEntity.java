/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.trade.api.platform.asile.model.base;

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

public class MchAuthEntity extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "MchAuth";
//	
//	public static final String ALIAS_ID = "主键id";
//	
//	public static final String ALIAS_MCH_ID = "商户号";
//	
//	public static final String ALIAS_PAY_TYPE = "授权的支付方式";
//	
//	public static final String ALIAS_STATUS = "1 启用 2 停用";
//	
//	public static final String ALIAS_REMARK = "备注";
//	
//	public static final String ALIAS_EXT1 = "扩展字段1";
//	
//	public static final String ALIAS_EXT2 = "扩展字段2";
//	
//	public static final String ALIAS_CREATE_TIME = "创建时间";
//	
//	public static final String ALIAS_OPERATOR = "操作人";
//	
//	public static final String ALIAS_LASTUPDATE_TIME = "最后更新时间";
//	
	
	//date formats
	public static final String FORMAT_CREATE_TIME = DATE_FORMAT;
	public static final String FORMAT_LASTUPDATE_TIME = DATE_FORMAT;
	
	//columns START
	//主键id
	private Long id;
	//商户号
	private String mchId;
	//授权的支付方式
	private String payType;
	//1 启用 2 停用
	private Integer status;
	//备注
	private String remark;
	//扩展字段1
	private String ext1;
	//扩展字段2
	private String ext2;
	//创建时间
	private java.util.Date createTime;
	//操作人
	private String operator;
	//最后更新时间
	private java.util.Date lastupdateTime;
	//columns END

	public MchAuthEntity(){
	}

	public MchAuthEntity(
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
	public void setPayType(java.lang.String value) {
		this.payType = value;
	}
	
	public java.lang.String getPayType() {
		return this.payType;
	}
	public void setStatus(java.lang.Integer value) {
		this.status = value;
	}
	
	public java.lang.Integer getStatus() {
		return this.status;
	}
	public void setRemark(java.lang.String value) {
		this.remark = value;
	}
	
	public java.lang.String getRemark() {
		return this.remark;
	}
	public void setExt1(java.lang.String value) {
		this.ext1 = value;
	}
	
	public java.lang.String getExt1() {
		return this.ext1;
	}
	public void setExt2(java.lang.String value) {
		this.ext2 = value;
	}
	
	public java.lang.String getExt2() {
		return this.ext2;
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
	public void setOperator(java.lang.String value) {
		this.operator = value;
	}
	
	public java.lang.String getOperator() {
		return this.operator;
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
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("MchId",getMchId())
			.append("PayType",getPayType())
			.append("Status",getStatus())
			.append("Remark",getRemark())
			.append("Ext1",getExt1())
			.append("Ext2",getExt2())
			.append("CreateTime",getCreateTime())
			.append("Operator",getOperator())
			.append("LastupdateTime",getLastupdateTime())
			.toString();
	}
	
	public String toString(String separator) {
		StringBuffer sb = new StringBuffer();

			sb.append("主键id:").append(id).append(separator);
			sb.append("商户号:").append(mchId).append(separator);
			sb.append("授权的支付方式:").append(payType).append(separator);
			sb.append("1 启用 2 停用:").append(status).append(separator);
			sb.append("备注:").append(remark).append(separator);
			sb.append("扩展字段1:").append(ext1).append(separator);
			sb.append("扩展字段2:").append(ext2).append(separator);
			sb.append("创建时间:").append(getCreateTimeString()).append(separator);
			sb.append("操作人:").append(operator).append(separator);
			sb.append("最后更新时间:").append(getLastupdateTimeString()).append(separator);
		
		return sb.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof MchAuthEntity == false) return false;
		if(this == obj) return true;
		MchAuthEntity other = (MchAuthEntity)obj;
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


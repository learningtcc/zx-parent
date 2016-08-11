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

public class AsileBusinessEntity extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "AsileBusiness";
//	
//	public static final String ALIAS_ID = "主键";
//	
//	public static final String ALIAS_BUSINESS_CODE = "业务code";
//	
//	public static final String ALIAS_BUSINESS_NAME = "业务名称";
//	
//	public static final String ALIAS_REMARK = "备注";
//	
//	public static final String ALIAS_CREATE_TIME = "创建时间";
//	
//	public static final String ALIAS_IS_SYN = "1同步0异步";
//	
//	public static final String ALIAS_ASILE_CODE = "渠道编号";
//	
	
	//date formats
	public static final String FORMAT_CREATE_TIME = DATE_FORMAT;
	
	//columns START
	//主键
	private Long id;
	//业务code
	private String businessCode;
	//业务名称
	private String businessName;
	//备注
	private String remark;
	//创建时间
	private java.util.Date createTime;
	//1同步0异步
	private Integer isSyn;
	//渠道编号
	private String asileCode;
	//columns END

	public AsileBusinessEntity(){
	}

	public AsileBusinessEntity(
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
	public void setBusinessCode(java.lang.String value) {
		this.businessCode = value;
	}
	
	public java.lang.String getBusinessCode() {
		return this.businessCode;
	}
	public void setBusinessName(java.lang.String value) {
		this.businessName = value;
	}
	
	public java.lang.String getBusinessName() {
		return this.businessName;
	}
	public void setRemark(java.lang.String value) {
		this.remark = value;
	}
	
	public java.lang.String getRemark() {
		return this.remark;
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
	public void setIsSyn(java.lang.Integer value) {
		this.isSyn = value;
	}
	
	public java.lang.Integer getIsSyn() {
		return this.isSyn;
	}
	public void setAsileCode(java.lang.String value) {
		this.asileCode = value;
	}
	
	public java.lang.String getAsileCode() {
		return this.asileCode;
	}
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("BusinessCode",getBusinessCode())
			.append("BusinessName",getBusinessName())
			.append("Remark",getRemark())
			.append("CreateTime",getCreateTime())
			.append("IsSyn",getIsSyn())
			.append("AsileCode",getAsileCode())
			.toString();
	}
	
	public String toString(String separator) {
		StringBuffer sb = new StringBuffer();

			sb.append("主键:").append(id).append(separator);
			sb.append("业务code:").append(businessCode).append(separator);
			sb.append("业务名称:").append(businessName).append(separator);
			sb.append("备注:").append(remark).append(separator);
			sb.append("创建时间:").append(getCreateTimeString()).append(separator);
			sb.append("1同步0异步:").append(isSyn).append(separator);
			sb.append("渠道编号:").append(asileCode).append(separator);
		
		return sb.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof AsileBusinessEntity == false) return false;
		if(this == obj) return true;
		AsileBusinessEntity other = (AsileBusinessEntity)obj;
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


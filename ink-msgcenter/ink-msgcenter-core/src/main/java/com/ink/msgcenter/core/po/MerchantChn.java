/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.msgcenter.core.po;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.ink.base.util.DateConvertUtils;
import com.ink.base.BaseEntity;

/**
 * @author  zongtt
 * @version 1.0
 * @since 1.0
 */

public class MerchantChn extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "MerchantChn";
//	
//	public static final String ALIAS_ID = "ID";
//	
//	public static final String ALIAS_MERCT_ID = "商户ID";
//	
//	public static final String ALIAS_MERCT_CODE = "商户代码";
//	
//	public static final String ALIAS_CHN_TYPE = "通道类型";
//	
//	public static final String ALIAS_CHN_ID = "通道ID";
//	
//	public static final String ALIAS_CHN_CODE = "通道代码";
//	
//	public static final String ALIAS_STATUS = "状态(0正常1商户停用2通道停用)";
//	
//	public static final String ALIAS_CREATOR_ID = "创建人ID";
//	
//	public static final String ALIAS_CREATOR_NAME = "创建人姓名";
//	
//	public static final String ALIAS_CREATE_TIME = "创建时间";
//	
	
	//date formats
	public static final String FORMAT_CREATE_TIME = DATE_FORMAT;
	
	//columns START
	//ID
	private Long id;
	//商户ID
	private Long merctId;
	//商户代码
	private String merctCode;
	//通道类型
	private String chnType;
	//通道ID
	private Long chnId;
	//通道代码
	private String chnCode;
	//状态(0正常1商户停用2通道停用)
	private String status;
	//创建人ID
	private String creatorId;
	//创建人姓名
	private String creatorName;
	//创建时间
	private java.util.Date createTime;
	//columns END

	public MerchantChn(){
	}

	public MerchantChn(
		Long id
	){
		this.id = id;
	}

	public void setId(Long value) {
		this.id = value;
	}
	
	public Long getId() {
		return this.id;
	}
	public void setMerctId(Long value) {
		this.merctId = value;
	}
	
	public Long getMerctId() {
		return this.merctId;
	}
	public void setMerctCode(String value) {
		this.merctCode = value;
	}
	
	public String getMerctCode() {
		return this.merctCode;
	}
	public void setChnType(String value) {
		this.chnType = value;
	}
	
	public String getChnType() {
		return this.chnType;
	}
	public void setChnId(Long value) {
		this.chnId = value;
	}
	
	public Long getChnId() {
		return this.chnId;
	}
	public void setChnCode(String value) {
		this.chnCode = value;
	}
	
	public String getChnCode() {
		return this.chnCode;
	}
	public void setStatus(String value) {
		this.status = value;
	}
	
	public String getStatus() {
		return this.status;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public void setCreatorName(String value) {
		this.creatorName = value;
	}
	
	public String getCreatorName() {
		return this.creatorName;
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
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("MerctId",getMerctId())
			.append("MerctCode",getMerctCode())
			.append("ChnType",getChnType())
			.append("ChnId",getChnId())
			.append("ChnCode",getChnCode())
			.append("Status",getStatus())
			.append("CreatorId",getCreatorId())
			.append("CreatorName",getCreatorName())
			.append("CreateTime",getCreateTime())
			.toString();
	}
	
	public String toString(String separator) {
		StringBuffer sb = new StringBuffer();

			sb.append("ID:").append(id).append(separator);
			sb.append("商户ID:").append(merctId).append(separator);
			sb.append("商户代码:").append(merctCode).append(separator);
			sb.append("通道类型:").append(chnType).append(separator);
			sb.append("通道ID:").append(chnId).append(separator);
			sb.append("通道代码:").append(chnCode).append(separator);
			sb.append("状态(0正常1商户停用2通道停用):").append(status).append(separator);
			sb.append("创建人ID:").append(creatorId).append(separator);
			sb.append("创建人姓名:").append(creatorName).append(separator);
			sb.append("创建时间:").append(getCreateTimeString()).append(separator);
		
		return sb.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof MerchantChn == false) return false;
		if(this == obj) return true;
		MerchantChn other = (MerchantChn)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}


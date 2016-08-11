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

public class SmsChannel extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "SmsChannel";
//	
//	public static final String ALIAS_ID = "ID";
//	
//	public static final String ALIAS_NAME = "通道名称";
//	
//	public static final String ALIAS_CHN_CODE = "通道代码(自动生成不能重复，初始代码100，自增)";
//	
//	public static final String ALIAS_CHN_TYPE = "通道类型(例如：亿美通道、卓望通道，用于第三方通道接口对接卓望通道：001)";
//	
//	public static final String ALIAS_CHN_PARAM = "通道参数";
//	
//	public static final String ALIAS_REMARK = "通道备注";
//	
//	public static final String ALIAS_PRIORITY_LEVEL = "通道优先级(数值越低优先级越高，最高优先级为1)";
//	
//	public static final String ALIAS_STATUS = "通道状态(0正常、1停用、2删除)";
//	
//	public static final String ALIAS_CREATOR_ID = "创建人ID";
//	
//	public static final String ALIAS_CREATOR_NAME = "创建人姓名";
//	
//	public static final String ALIAS_CREATE_TIME = "创建时间";
//	
//	public static final String ALIAS_UPDATOR_ID = "更新人ID";
//	
//	public static final String ALIAS_UPDATOR_NAME = "更新人姓名";
//	
//	public static final String ALIAS_UPDATE_TIME = "更新时间";
//	
	
	//date formats
	public static final String FORMAT_CREATE_TIME = DATE_TIME_FORMAT;
	public static final String FORMAT_UPDATE_TIME = DATE_TIME_FORMAT;
	
	//columns START
	//ID
	private Long id;
	//通道名称
	private String name;
	//通道代码(自动生成不能重复，初始代码100，自增)
	private String chnCode;
	//通道类型(例如：亿美通道、卓望通道，用于第三方通道接口对接卓望通道：001)
	private String chnType;
	//通道参数
	private String chnParam;
	//通道备注
	private String remark;
	//通道优先级(数值越低优先级越高，最高优先级为1)
	private Integer priorityLevel;
	//通道状态(0正常、1停用、2删除)
	private String status;
	//创建人ID
	private String creatorId;
	//创建人姓名
	private String creatorName;
	//创建时间
	private java.util.Date createTime;
	//更新人ID
	private String updatorId;
	//更新人姓名
	private String updatorName;
	//更新时间
	private java.util.Date updateTime;
	//columns END

	public SmsChannel(){
	}

	public SmsChannel(
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
	public void setName(String value) {
		this.name = value;
	}
	
	public String getName() {
		return this.name;
	}
	public void setChnCode(String value) {
		this.chnCode = value;
	}
	
	public String getChnCode() {
		return this.chnCode;
	}
	public void setChnType(String value) {
		this.chnType = value;
	}
	
	public String getChnType() {
		return this.chnType;
	}
	public void setChnParam(String value) {
		this.chnParam = value;
	}
	
	public String getChnParam() {
		return this.chnParam;
	}
	public void setRemark(String value) {
		this.remark = value;
	}
	
	public String getRemark() {
		return this.remark;
	}
	public void setPriorityLevel(Integer value) {
		this.priorityLevel = value;
	}
	
	public Integer getPriorityLevel() {
		return this.priorityLevel;
	}
	public void setStatus(String value) {
		this.status = value;
	}
	
	public String getStatus() {
		return this.status;
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

	public void setUpdatorName(String value) {
		this.updatorName = value;
	}

	public String getUpdatorId() {
		return updatorId;
	}

	public void setUpdatorId(String updatorId) {
		this.updatorId = updatorId;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public String getUpdatorName() {
		return this.updatorName;
	}
	public String getUpdateTimeString() {
		return DateConvertUtils.format(getUpdateTime(), FORMAT_UPDATE_TIME);
	}
	public void setUpdateTimeString(String value) {
		setUpdateTime(DateConvertUtils.parse(value, FORMAT_UPDATE_TIME,java.util.Date.class));
	}
	
	public void setUpdateTime(java.util.Date value) {
		this.updateTime = value;
	}
	
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Name",getName())
			.append("ChnCode",getChnCode())
			.append("ChnType",getChnType())
			.append("ChnParam",getChnParam())
			.append("Remark",getRemark())
			.append("PriorityLevel",getPriorityLevel())
			.append("Status",getStatus())
			.append("CreatorId",getCreatorId())
			.append("CreatorName",getCreatorName())
			.append("CreateTime",getCreateTime())
			.append("UpdatorId",getUpdatorId())
			.append("UpdatorName",getUpdatorName())
			.append("UpdateTime",getUpdateTime())
			.toString();
	}
	
	public String toString(String separator) {
		StringBuffer sb = new StringBuffer();

			sb.append("ID:").append(id).append(separator);
			sb.append("通道名称:").append(name).append(separator);
			sb.append("通道代码(自动生成不能重复，初始代码100，自增):").append(chnCode).append(separator);
			sb.append("通道类型(例如：亿美通道、卓望通道，用于第三方通道接口对接卓望通道：001):").append(chnType).append(separator);
			sb.append("通道参数:").append(chnParam).append(separator);
			sb.append("通道备注:").append(remark).append(separator);
			sb.append("通道优先级(数值越低优先级越高，最高优先级为1):").append(priorityLevel).append(separator);
			sb.append("通道状态(0正常、1停用、2删除):").append(status).append(separator);
			sb.append("创建人ID:").append(creatorId).append(separator);
			sb.append("创建人姓名:").append(creatorName).append(separator);
			sb.append("创建时间:").append(getCreateTimeString()).append(separator);
			sb.append("更新人ID:").append(updatorId).append(separator);
			sb.append("更新人姓名:").append(updatorName).append(separator);
			sb.append("更新时间:").append(getUpdateTimeString()).append(separator);
		
		return sb.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof SmsChannel == false) return false;
		if(this == obj) return true;
		SmsChannel other = (SmsChannel)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}


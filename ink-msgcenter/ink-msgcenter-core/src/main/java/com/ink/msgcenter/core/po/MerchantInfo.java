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

public class MerchantInfo extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "MerchantInfo";
//	
//	public static final String ALIAS_ID = "ID";
//	
//	public static final String ALIAS_NAME = "商户名称";
//	
//	public static final String ALIAS_SN = "商户编号";
//	
//	public static final String ALIAS_REMARK = "商户描述";
//	
//	public static final String ALIAS_STATUS = "商户状态(0正常1停用2删除)";
//	
//	public static final String ALIAS_CHANNEL_TYPE = "通道类型";
//	
//	public static final String ALIAS_EMAIL_NOTIFY = "邮件通知";
//	
//	public static final String ALIAS_SMS_NOTIFY = "短信通知";
//	
//	public static final String ALIAS_CREATOR_ID = "创建人ID";
//	
//	public static final String ALIAS_CREATE_TIME = "创建时间";
//	
//	public static final String ALIAS_CREATOR_NAME = "创建人姓名";
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
	//商户名称
	private String name;
	//商户编号
	private String sn;
	//商户描述
	private String remark;
	//商户状态(0正常1停用2删除)
	private String status;
	//通道类型
	private String channelType;
	//邮件通知
	private String emailNotify;
	//短信通知
	private String smsNotify;
	//创建人ID
	private String creatorId;
	//创建时间
	private java.util.Date createTime;
	//创建人姓名
	private String creatorName;
	//更新人ID
	private String updatorId;
	//更新人姓名
	private String updatorName;
	//更新时间
	private java.util.Date updateTime;
	//columns END

	public MerchantInfo(){
	}

	public MerchantInfo(
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
	public void setSn(String value) {
		this.sn = value;
	}
	
	public String getSn() {
		return this.sn;
	}
	public void setRemark(String value) {
		this.remark = value;
	}
	
	public String getRemark() {
		return this.remark;
	}
	public void setStatus(String value) {
		this.status = value;
	}
	
	public String getStatus() {
		return this.status;
	}
	public void setChannelType(String value) {
		this.channelType = value;
	}
	
	public String getChannelType() {
		return this.channelType;
	}
	public void setEmailNotify(String value) {
		this.emailNotify = value;
	}
	
	public String getEmailNotify() {
		return this.emailNotify;
	}
	public void setSmsNotify(String value) {
		this.smsNotify = value;
	}
	
	public String getSmsNotify() {
		return this.smsNotify;
	}
	public void setCreatorId(String value) {
		this.creatorId = value;
	}
	
	public String getCreatorId() {
		return this.creatorId;
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
	public void setCreatorName(String value) {
		this.creatorName = value;
	}
	
	public String getCreatorName() {
		return this.creatorName;
	}
	public void setUpdatorId(String value) {
		this.updatorId = value;
	}
	
	public String getUpdatorId() {
		return this.updatorId;
	}
	public void setUpdatorName(String value) {
		this.updatorName = value;
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
			.append("Sn",getSn())
			.append("Remark",getRemark())
			.append("Status",getStatus())
			.append("ChannelType",getChannelType())
			.append("EmailNotify",getEmailNotify())
			.append("SmsNotify",getSmsNotify())
			.append("CreatorId",getCreatorId())
			.append("CreateTime",getCreateTime())
			.append("CreatorName",getCreatorName())
			.append("UpdatorId",getUpdatorId())
			.append("UpdatorName",getUpdatorName())
			.append("UpdateTime",getUpdateTime())
			.toString();
	}
	
	public String toString(String separator) {
		StringBuffer sb = new StringBuffer();

			sb.append("ID:").append(id).append(separator);
			sb.append("商户名称:").append(name).append(separator);
			sb.append("商户编号:").append(sn).append(separator);
			sb.append("商户描述:").append(remark).append(separator);
			sb.append("商户状态(0正常1停用2删除):").append(status).append(separator);
			sb.append("通道类型:").append(channelType).append(separator);
			sb.append("邮件通知:").append(emailNotify).append(separator);
			sb.append("短信通知:").append(smsNotify).append(separator);
			sb.append("创建人ID:").append(creatorId).append(separator);
			sb.append("创建时间:").append(getCreateTimeString()).append(separator);
			sb.append("创建人姓名:").append(creatorName).append(separator);
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
		if(obj instanceof MerchantInfo == false) return false;
		if(this == obj) return true;
		MerchantInfo other = (MerchantInfo)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}


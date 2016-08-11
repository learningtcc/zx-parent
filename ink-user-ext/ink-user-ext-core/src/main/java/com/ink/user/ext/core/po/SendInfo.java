/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.user.ext.core.po;

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

public class SendInfo extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "发送信息";
//	
//	public static final String ALIAS_ID = "id";
//	
//	public static final String ALIAS_FILE_ID = "文件id";
//	
//	public static final String ALIAS_FILE_PATH = "文件路径";
//	
//	public static final String ALIAS_ORIGINAL_NAME = "原始名称";
//	
//	public static final String ALIAS_SEND_TIME = "发送时间";
//	
//	public static final String ALIAS_SEND_TYPE = "发送类型";
//	
//	public static final String ALIAS_OPERATOR_NAME = "操作员名称";
//	
//	public static final String ALIAS_EVENT_INFO = "活动信息";
//	
//	public static final String ALIAS_MCH_ID = "商户号";
//	
//	public static final String ALIAS_STATUS = "状态";
//	
//	public static final String ALIAS_CREATE_TIME = "createTime";
//	
//	public static final String ALIAS_UPDATE_TIME = "updateTime";
//	
//	public static final String ALIAS_DEL_FLAG = "delFlag";
//	
	
	//date formats
	public static final String FORMAT_SEND_TIME = DATE_TIME_FORMAT;
	public static final String FORMAT_CREATE_TIME = DATE_TIME_FORMAT;
	public static final String FORMAT_UPDATE_TIME = DATE_TIME_FORMAT;
	
	//columns START
	//id
	private Long id;
	//文件id
	private Long fileId;
	//文件路径
	private String filePath;
	//原始名称
	private String originalName;
	//发送时间
	private Date sendTime;
	//发送类型
	private Integer sendType;
	//操作员名称
	private String operatorName;
	//活动信息
	private String eventInfo;
	//商户号
	private Long mchId;
	//状态
	private Integer status;
	//createTime
	private Date createTime;
	//updateTime
	private Date updateTime;
	//delFlag
	private Integer delFlag;
	//columns END

	public SendInfo(){
	}

	public SendInfo(
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
	public void setFileId(java.lang.Long value) {
		this.fileId = value;
	}
	
	public java.lang.Long getFileId() {
		return this.fileId;
	}
	public void setFilePath(java.lang.String value) {
		this.filePath = value;
	}
	
	public java.lang.String getFilePath() {
		return this.filePath;
	}
	public void setOriginalName(java.lang.String value) {
		this.originalName = value;
	}
	
	public java.lang.String getOriginalName() {
		return this.originalName;
	}
	public String getSendTimeString() {
		return DateConvertUtils.format(getSendTime(), FORMAT_SEND_TIME);
	}
	public void setSendTimeString(String value) {
		setSendTime(DateConvertUtils.parse(value, FORMAT_SEND_TIME,Date.class));
	}
	
	public void setSendTime(Date value) {
		this.sendTime = value;
	}
	
	public Date getSendTime() {
		return this.sendTime;
	}
	public void setSendType(java.lang.Integer value) {
		this.sendType = value;
	}
	
	public java.lang.Integer getSendType() {
		return this.sendType;
	}
	public void setOperatorName(java.lang.String value) {
		this.operatorName = value;
	}
	
	public java.lang.String getOperatorName() {
		return this.operatorName;
	}
	public void setEventInfo(java.lang.String value) {
		this.eventInfo = value;
	}
	
	public java.lang.String getEventInfo() {
		return this.eventInfo;
	}
	public void setMchId(java.lang.Long value) {
		this.mchId = value;
	}
	
	public java.lang.Long getMchId() {
		return this.mchId;
	}
	public void setStatus(java.lang.Integer value) {
		this.status = value;
	}
	
	public java.lang.Integer getStatus() {
		return this.status;
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
	public String getUpdateTimeString() {
		return DateConvertUtils.format(getUpdateTime(), FORMAT_UPDATE_TIME);
	}
	public void setUpdateTimeString(String value) {
		setUpdateTime(DateConvertUtils.parse(value, FORMAT_UPDATE_TIME,Date.class));
	}
	
	public void setUpdateTime(Date value) {
		this.updateTime = value;
	}
	
	public Date getUpdateTime() {
		return this.updateTime;
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
			.append("FileId",getFileId())
			.append("FilePath",getFilePath())
			.append("OriginalName",getOriginalName())
			.append("SendTime",getSendTime())
			.append("SendType",getSendType())
			.append("OperatorName",getOperatorName())
			.append("EventInfo",getEventInfo())
			.append("MchId",getMchId())
			.append("Status",getStatus())
			.append("CreateTime",getCreateTime())
			.append("UpdateTime",getUpdateTime())
			.append("DelFlag",getDelFlag())
			.toString();
	}
	
	public String toString(String separator) {
		StringBuffer sb = new StringBuffer();

			sb.append("id:").append(id).append(separator);
			sb.append("文件id:").append(fileId).append(separator);
			sb.append("文件路径:").append(filePath).append(separator);
			sb.append("原始名称:").append(originalName).append(separator);
			sb.append("发送时间:").append(getSendTimeString()).append(separator);
			sb.append("发送类型:").append(sendType).append(separator);
			sb.append("操作员名称:").append(operatorName).append(separator);
			sb.append("活动信息:").append(eventInfo).append(separator);
			sb.append("商户号:").append(mchId).append(separator);
			sb.append("状态:").append(status).append(separator);
			sb.append("createTime:").append(getCreateTimeString()).append(separator);
			sb.append("updateTime:").append(getUpdateTimeString()).append(separator);
			sb.append("delFlag:").append(delFlag).append(separator);
		
		return sb.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof SendInfo == false) return false;
		if(this == obj) return true;
		SendInfo other = (SendInfo)obj;
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


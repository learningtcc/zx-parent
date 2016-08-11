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

public class BatchLog extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "批次信息";
//	
//	public static final String ALIAS_ID = "id";
//	
//	public static final String ALIAS_FILE_ID = "文件号";
//	
//	public static final String ALIAS_BATCH_ID = "批次号";
//	
//	public static final String ALIAS_ITEM_COUNT = "条目数";
//	
//	public static final String ALIAS_SEND_TIME = "发送时间";
//	
//	public static final String ALIAS_SEND_TYPE = "发送类型";
//	
//	public static final String ALIAS_MSG_TYPE = "消息类型";
//	
//	public static final String ALIAS_MSG_CHANNEL = "信息渠道";
//	
//	public static final String ALIAS_MSG_TEMPLATE = "信息模板";
//	
//	public static final String ALIAS_CREATE_TIME = "创建时间";
//	
//	public static final String ALIAS_UPDATE_TIME = "更新时间";
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
	//文件号
	private Long fileId;
	//批次号
	private String batchId;
	//条目数
	private Integer itemCount;
	//发送时间
	private Date sendTime;
	//发送类型
	private String sendType;
	//消息类型
	private String msgType;
	//信息渠道
	private String msgChannel;
	//信息模板
	private String msgTemplate;
	//创建时间
	private Date createTime;
	//更新时间
	private Date updateTime;
	//delFlag
	private Integer delFlag;
	//columns END

	public BatchLog(){
	}

	public BatchLog(
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
	public void setBatchId(java.lang.String value) {
		this.batchId = value;
	}
	
	public java.lang.String getBatchId() {
		return this.batchId;
	}
	public void setItemCount(java.lang.Integer value) {
		this.itemCount = value;
	}
	
	public java.lang.Integer getItemCount() {
		return this.itemCount;
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
	public void setSendType(java.lang.String value) {
		this.sendType = value;
	}
	
	public java.lang.String getSendType() {
		return this.sendType;
	}
	public void setMsgType(java.lang.String value) {
		this.msgType = value;
	}
	
	public java.lang.String getMsgType() {
		return this.msgType;
	}
	public void setMsgChannel(java.lang.String value) {
		this.msgChannel = value;
	}
	
	public java.lang.String getMsgChannel() {
		return this.msgChannel;
	}
	public void setMsgTemplate(java.lang.String value) {
		this.msgTemplate = value;
	}
	
	public java.lang.String getMsgTemplate() {
		return this.msgTemplate;
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
			.append("BatchId",getBatchId())
			.append("ItemCount",getItemCount())
			.append("SendTime",getSendTime())
			.append("SendType",getSendType())
			.append("MsgType",getMsgType())
			.append("MsgChannel",getMsgChannel())
			.append("MsgTemplate",getMsgTemplate())
			.append("CreateTime",getCreateTime())
			.append("UpdateTime",getUpdateTime())
			.append("DelFlag",getDelFlag())
			.toString();
	}
	
	public String toString(String separator) {
		StringBuffer sb = new StringBuffer();

			sb.append("id:").append(id).append(separator);
			sb.append("文件号:").append(fileId).append(separator);
			sb.append("批次号:").append(batchId).append(separator);
			sb.append("条目数:").append(itemCount).append(separator);
			sb.append("发送时间:").append(getSendTimeString()).append(separator);
			sb.append("发送类型:").append(sendType).append(separator);
			sb.append("消息类型:").append(msgType).append(separator);
			sb.append("信息渠道:").append(msgChannel).append(separator);
			sb.append("信息模板:").append(msgTemplate).append(separator);
			sb.append("创建时间:").append(getCreateTimeString()).append(separator);
			sb.append("更新时间:").append(getUpdateTimeString()).append(separator);
			sb.append("delFlag:").append(delFlag).append(separator);
		
		return sb.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BatchLog == false) return false;
		if(this == obj) return true;
		BatchLog other = (BatchLog)obj;
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


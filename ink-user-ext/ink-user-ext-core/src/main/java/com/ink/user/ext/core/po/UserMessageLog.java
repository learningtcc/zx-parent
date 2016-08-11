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

public class UserMessageLog extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "用户发送日志";
//	
//	public static final String ALIAS_ID = "id";
//	
//	public static final String ALIAS_FILE_ID = "文件号";
//	
//	public static final String ALIAS_USER_ID = "用户id";
//	
//	public static final String ALIAS_MCH_ID = "商户号";
//	
//	public static final String ALIAS_NAME = "姓名";
//	
//	public static final String ALIAS_PHONE = "手机号";
//	
//	public static final String ALIAS_STATUS = "发送状态";
//	
//	public static final String ALIAS_EVENT_INFO = "营销活动信息";
//	
//	public static final String ALIAS_MSG_CHANNEL = "信息渠道";
//	
//	public static final String ALIAS_MSG_TEMPLATE = "信息模板";
//	
//	public static final String ALIAS_CREATE_TIME = "创建时间";
//	
//	public static final String ALIAS_UPDATE_TIME = "更新时间";
//	
//	public static final String ALIAS_DEL_FLAG = "删除标识";
//	
	
	//date formats
	public static final String FORMAT_CREATE_TIME = DATE_TIME_FORMAT;
	public static final String FORMAT_UPDATE_TIME = DATE_TIME_FORMAT;
	
	//columns START
	//id
	private Long id;
	//文件号
	private Long fileId;
	//用户id
	private Long userId;
	//商户号
	private Long mchId;
	//姓名
	private String name;
	//手机号
	private String phone;
	//发送状态
	private Integer status;
	//营销活动信息
	private String eventInfo;
	//信息渠道
	private String msgChannel;
	//信息模板
	private String msgTemplate;
	//创建时间
	private Date createTime;
	//更新时间
	private Date updateTime;
	//删除标识
	private Integer delFlag;
	//columns END

	public UserMessageLog(){
	}

	public UserMessageLog(
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
	public void setUserId(java.lang.Long value) {
		this.userId = value;
	}
	
	public java.lang.Long getUserId() {
		return this.userId;
	}
	public void setMchId(java.lang.Long value) {
		this.mchId = value;
	}
	
	public java.lang.Long getMchId() {
		return this.mchId;
	}
	public void setName(java.lang.String value) {
		this.name = value;
	}
	
	public java.lang.String getName() {
		return this.name;
	}
	public void setPhone(java.lang.String value) {
		this.phone = value;
	}
	
	public java.lang.String getPhone() {
		return this.phone;
	}
	public void setStatus(java.lang.Integer value) {
		this.status = value;
	}
	
	public java.lang.Integer getStatus() {
		return this.status;
	}
	public void setEventInfo(java.lang.String value) {
		this.eventInfo = value;
	}
	
	public java.lang.String getEventInfo() {
		return this.eventInfo;
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
			.append("UserId",getUserId())
			.append("MchId",getMchId())
			.append("Name",getName())
			.append("Phone",getPhone())
			.append("Status",getStatus())
			.append("EventInfo",getEventInfo())
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
			sb.append("用户id:").append(userId).append(separator);
			sb.append("商户号:").append(mchId).append(separator);
			sb.append("姓名:").append(name).append(separator);
			sb.append("手机号:").append(phone).append(separator);
			sb.append("发送状态:").append(status).append(separator);
			sb.append("营销活动信息:").append(eventInfo).append(separator);
			sb.append("信息渠道:").append(msgChannel).append(separator);
			sb.append("信息模板:").append(msgTemplate).append(separator);
			sb.append("创建时间:").append(getCreateTimeString()).append(separator);
			sb.append("更新时间:").append(getUpdateTimeString()).append(separator);
			sb.append("删除标识:").append(delFlag).append(separator);
		
		return sb.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof UserMessageLog == false) return false;
		if(this == obj) return true;
		UserMessageLog other = (UserMessageLog)obj;
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


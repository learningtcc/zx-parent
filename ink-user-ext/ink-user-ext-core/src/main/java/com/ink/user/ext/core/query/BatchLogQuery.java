/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.user.ext.core.query;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.ink.base.BaseQuery;

/**
 * @author  zongtt
 * @version 1.0
 * @since 1.0
 */

public class BatchLogQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    
	/** id */
	private java.lang.Long id;
	/** 文件号 */
	private java.lang.Long fileId;
	/** 批次号 */
	private java.lang.String batchId;
	/** 条目数 */
	private java.lang.Integer itemCount;
	/** 发送时间 */
	private Date sendTime;
	/** 发送类型 */
	private java.lang.String sendType;
	/** 消息类型 */
	private java.lang.String msgType;
	/** 信息渠道 */
	private java.lang.String msgChannel;
	/** 信息模板 */
	private java.lang.String msgTemplate;
	/** 创建时间 */
	private Date createTimeBegin;
	private Date createTimeEnd;
	/** 更新时间 */
	private Date updateTimeBegin;
	private Date updateTimeEnd;
	/** delFlag */
	private java.lang.Integer delFlag;
	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.Long getFileId() {
		return this.fileId;
	}
	
	public void setFileId(java.lang.Long value) {
		this.fileId = value;
	}
	
	public java.lang.String getBatchId() {
		return this.batchId;
	}
	
	public void setBatchId(java.lang.String value) {
		this.batchId = value;
	}
	
	public java.lang.Integer getItemCount() {
		return this.itemCount;
	}
	
	public void setItemCount(java.lang.Integer value) {
		this.itemCount = value;
	}
	
	public Date getSendTime() {
		return this.sendTime;
	}
	
	public void setSendTime(Date value) {
		this.sendTime = value;
	}
	
	public java.lang.String getSendType() {
		return this.sendType;
	}
	
	public void setSendType(java.lang.String value) {
		this.sendType = value;
	}
	
	public java.lang.String getMsgType() {
		return this.msgType;
	}
	
	public void setMsgType(java.lang.String value) {
		this.msgType = value;
	}
	
	public java.lang.String getMsgChannel() {
		return this.msgChannel;
	}
	
	public void setMsgChannel(java.lang.String value) {
		this.msgChannel = value;
	}
	
	public java.lang.String getMsgTemplate() {
		return this.msgTemplate;
	}
	
	public void setMsgTemplate(java.lang.String value) {
		this.msgTemplate = value;
	}
	
	public Date getCreateTimeBegin() {
		return this.createTimeBegin;
	}
	
	public void setCreateTimeBegin(Date value) {
		this.createTimeBegin = value;
	}	
	
	public Date getCreateTimeEnd() {
		return this.createTimeEnd;
	}
	
	public void setCreateTimeEnd(Date value) {
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.createTimeEnd = calendar.getTime();
		}else {
			this.createTimeEnd = value;
		}
	}
	
	public Date getUpdateTimeBegin() {
		return this.updateTimeBegin;
	}
	
	public void setUpdateTimeBegin(Date value) {
		this.updateTimeBegin = value;
	}	
	
	public Date getUpdateTimeEnd() {
		return this.updateTimeEnd;
	}
	
	public void setUpdateTimeEnd(Date value) {
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.updateTimeEnd = calendar.getTime();
		}else {
			this.updateTimeEnd = value;
		}
	}
	
	public java.lang.Integer getDelFlag() {
		return this.delFlag;
	}
	
	public void setDelFlag(java.lang.Integer value) {
		this.delFlag = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}


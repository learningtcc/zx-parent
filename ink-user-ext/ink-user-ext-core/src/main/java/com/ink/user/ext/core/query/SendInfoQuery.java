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

public class SendInfoQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    
	/** id */
	private java.lang.Long id;
	/** 文件id */
	private java.lang.Long fileId;
	/** 文件路径 */
	private java.lang.String filePath;
	/** 原始名称 */
	private java.lang.String originalName;
	/** 发送时间 */
	private Date sendTime;
	/** 发送类型 */
	private java.lang.Integer sendType;
	/** 操作员名称 */
	private java.lang.String operatorName;
	/** 活动信息 */
	private java.lang.String eventInfo;
	/** 商户号 */
	private java.lang.Long mchId;
	/** 状态 */
	private java.lang.Integer status;
	/** createTime */
	private Date createTimeBegin;
	private Date createTimeEnd;
	/** updateTime */
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
	
	public java.lang.String getFilePath() {
		return this.filePath;
	}
	
	public void setFilePath(java.lang.String value) {
		this.filePath = value;
	}
	
	public java.lang.String getOriginalName() {
		return this.originalName;
	}
	
	public void setOriginalName(java.lang.String value) {
		this.originalName = value;
	}
	
	public Date getSendTime() {
		return this.sendTime;
	}
	
	public void setSendTime(Date value) {
		this.sendTime = value;
	}
	
	public java.lang.Integer getSendType() {
		return this.sendType;
	}
	
	public void setSendType(java.lang.Integer value) {
		this.sendType = value;
	}
	
	public java.lang.String getOperatorName() {
		return this.operatorName;
	}
	
	public void setOperatorName(java.lang.String value) {
		this.operatorName = value;
	}
	
	public java.lang.String getEventInfo() {
		return this.eventInfo;
	}
	
	public void setEventInfo(java.lang.String value) {
		this.eventInfo = value;
	}
	
	public java.lang.Long getMchId() {
		return this.mchId;
	}
	
	public void setMchId(java.lang.Long value) {
		this.mchId = value;
	}
	
	public java.lang.Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(java.lang.Integer value) {
		this.status = value;
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


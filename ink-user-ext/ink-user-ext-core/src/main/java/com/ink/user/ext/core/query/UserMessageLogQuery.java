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

public class UserMessageLogQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    
	/** id */
	private java.lang.Long id;
	/** 文件号 */
	private java.lang.Long fileId;
	/** 用户id */
	private java.lang.Long userId;
	/** 商户号 */
	private java.lang.Long mchId;
	/** 姓名 */
	private java.lang.String name;
	/** 手机号 */
	private java.lang.String phone;
	/** 发送状态 */
	private java.lang.Integer status;
	/** 营销活动信息 */
	private java.lang.String eventInfo;
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
	/** 删除标识 */
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
	
	public java.lang.Long getUserId() {
		return this.userId;
	}
	
	public void setUserId(java.lang.Long value) {
		this.userId = value;
	}
	
	public java.lang.Long getMchId() {
		return this.mchId;
	}
	
	public void setMchId(java.lang.Long value) {
		this.mchId = value;
	}
	
	public java.lang.String getName() {
		return this.name;
	}
	
	public void setName(java.lang.String value) {
		this.name = value;
	}
	
	public java.lang.String getPhone() {
		return this.phone;
	}
	
	public void setPhone(java.lang.String value) {
		this.phone = value;
	}
	
	public java.lang.Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(java.lang.Integer value) {
		this.status = value;
	}
	
	public java.lang.String getEventInfo() {
		return this.eventInfo;
	}
	
	public void setEventInfo(java.lang.String value) {
		this.eventInfo = value;
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


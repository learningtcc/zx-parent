/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.msgcenter.core.query;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Calendar;

import com.ink.base.BaseQuery;

/**
 * @author  zongtt
 * @version 1.0
 * @since 1.0
 */

public class MerchantInfoQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    
	/** ID */
	private Long id;
	/** 商户名称 */
	private String name;
	/** 商户编号 */
	private String sn;
	/** 商户描述 */
	private String remark;
	/** 商户状态(0正常1停用2删除) */
	private String status;
	/** 通道类型 */
	private String channelType;
	/** 邮件通知 */
	private String emailNotify;
	/** 短信通知 */
	private String smsNotify;
	/** 创建人ID */
	private String creatorId;
	/** 创建时间 */
	private java.util.Date createTimeBegin;
	private java.util.Date createTimeEnd;
	/** 创建人姓名 */
	private String creatorName;
	/** 更新人ID */
	private String updatorId;
	/** 更新人姓名 */
	private String updatorName;
	/** 更新时间 */
	private java.util.Date updateTimeBegin;
	private java.util.Date updateTimeEnd;
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long value) {
		this.id = value;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String value) {
		this.name = value;
	}
	
	public String getSn() {
		return this.sn;
	}
	
	public void setSn(String value) {
		this.sn = value;
	}
	
	public String getRemark() {
		return this.remark;
	}
	
	public void setRemark(String value) {
		this.remark = value;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public void setStatus(String value) {
		this.status = value;
	}
	
	public String getChannelType() {
		return this.channelType;
	}
	
	public void setChannelType(String value) {
		this.channelType = value;
	}
	
	public String getEmailNotify() {
		return this.emailNotify;
	}
	
	public void setEmailNotify(String value) {
		this.emailNotify = value;
	}
	
	public String getSmsNotify() {
		return this.smsNotify;
	}
	
	public void setSmsNotify(String value) {
		this.smsNotify = value;
	}
	
	public String getCreatorId() {
		return this.creatorId;
	}
	
	public void setCreatorId(String value) {
		this.creatorId = value;
	}
	
	public java.util.Date getCreateTimeBegin() {
		return this.createTimeBegin;
	}
	
	public void setCreateTimeBegin(java.util.Date value) {
		this.createTimeBegin = value;
	}	
	
	public java.util.Date getCreateTimeEnd() {
		return this.createTimeEnd;
	}
	
	public void setCreateTimeEnd(java.util.Date value) {
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.createTimeEnd = calendar.getTime();
		}else {
			this.createTimeEnd = value;
		}
	}
	
	public String getCreatorName() {
		return this.creatorName;
	}
	
	public void setCreatorName(String value) {
		this.creatorName = value;
	}
	
	public String getUpdatorId() {
		return this.updatorId;
	}
	
	public void setUpdatorId(String value) {
		this.updatorId = value;
	}
	
	public String getUpdatorName() {
		return this.updatorName;
	}
	
	public void setUpdatorName(String value) {
		this.updatorName = value;
	}
	
	public java.util.Date getUpdateTimeBegin() {
		return this.updateTimeBegin;
	}
	
	public void setUpdateTimeBegin(java.util.Date value) {
		this.updateTimeBegin = value;
	}	
	
	public java.util.Date getUpdateTimeEnd() {
		return this.updateTimeEnd;
	}
	
	public void setUpdateTimeEnd(java.util.Date value) {
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.updateTimeEnd = calendar.getTime();
		}else {
			this.updateTimeEnd = value;
		}
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}


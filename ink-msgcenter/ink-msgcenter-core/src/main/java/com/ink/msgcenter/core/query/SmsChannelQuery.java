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

public class SmsChannelQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    
	/** ID */
	private Long id;
	/** 通道名称 */
	private String name;
	/** 通道代码(自动生成不能重复，初始代码100，自增) */
	private String chnCode;
	/** 通道类型(例如：亿美通道、卓望通道，用于第三方通道接口对接卓望通道：001) */
	private String chnType;
	/** 通道参数 */
	private String chnParam;
	/** 通道备注 */
	private String remark;
	/** 通道优先级(数值越低优先级越高，最高优先级为1) */
	private Integer priorityLevel;
	/** 通道状态(0正常、1停用、2删除) */
	private String status;
	/** 创建人ID */
	private String creatorId;
	/** 创建人姓名 */
	private String creatorName;
	/** 创建时间 */
	private java.util.Date createTimeBegin;
	private java.util.Date createTimeEnd;
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
	
	public String getChnCode() {
		return this.chnCode;
	}
	
	public void setChnCode(String value) {
		this.chnCode = value;
	}
	
	public String getChnType() {
		return this.chnType;
	}
	
	public void setChnType(String value) {
		this.chnType = value;
	}
	
	public String getChnParam() {
		return this.chnParam;
	}
	
	public void setChnParam(String value) {
		this.chnParam = value;
	}
	
	public String getRemark() {
		return this.remark;
	}
	
	public void setRemark(String value) {
		this.remark = value;
	}
	
	public Integer getPriorityLevel() {
		return this.priorityLevel;
	}
	
	public void setPriorityLevel(Integer value) {
		this.priorityLevel = value;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public void setStatus(String value) {
		this.status = value;
	}

	public String getCreatorName() {
		return this.creatorName;
	}
	
	public void setCreatorName(String value) {
		this.creatorName = value;
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

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public String getUpdatorId() {
		return updatorId;
	}

	public void setUpdatorId(String updatorId) {
		this.updatorId = updatorId;
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


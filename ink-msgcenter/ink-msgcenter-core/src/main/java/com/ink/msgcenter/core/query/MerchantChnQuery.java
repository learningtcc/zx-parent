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

public class MerchantChnQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    
	/** ID */
	private Long id;
	/** 商户ID */
	private Long merctId;
	/** 商户代码 */
	private String merctCode;
	/** 通道类型 */
	private String chnType;
	/** 通道ID */
	private Long chnId;
	/** 通道代码 */
	private String chnCode;
	/** 状态(0正常1商户停用2通道停用) */
	private String status;
	/** 创建人ID */
	private Long creatorId;
	/** 创建人姓名 */
	private String creatorName;
	/** 创建时间 */
	private java.util.Date createTimeBegin;
	private java.util.Date createTimeEnd;
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long value) {
		this.id = value;
	}
	
	public Long getMerctId() {
		return this.merctId;
	}
	
	public void setMerctId(Long value) {
		this.merctId = value;
	}
	
	public String getMerctCode() {
		return this.merctCode;
	}
	
	public void setMerctCode(String value) {
		this.merctCode = value;
	}
	
	public String getChnType() {
		return this.chnType;
	}
	
	public void setChnType(String value) {
		this.chnType = value;
	}
	
	public Long getChnId() {
		return this.chnId;
	}
	
	public void setChnId(Long value) {
		this.chnId = value;
	}
	
	public String getChnCode() {
		return this.chnCode;
	}
	
	public void setChnCode(String value) {
		this.chnCode = value;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public void setStatus(String value) {
		this.status = value;
	}
	
	public Long getCreatorId() {
		return this.creatorId;
	}
	
	public void setCreatorId(Long value) {
		this.creatorId = value;
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
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}


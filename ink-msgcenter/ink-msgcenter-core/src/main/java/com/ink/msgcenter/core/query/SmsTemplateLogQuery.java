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

public class SmsTemplateLogQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    
	/** id */
	private java.lang.Long id;
	/** 模板ID */
	private java.lang.Long templateId;
	/** 商户ID */
	private java.lang.Long merctId;
	/** 商户代码 */
	private java.lang.String merctCode;
	/** 模板名称 */
	private java.lang.String tempName;
	/** 模板内容 */
	private java.lang.String tempContent;
	/** 解析方式 */
	private java.lang.String parseMethod;
	/** 模板参数 */
	private java.lang.String tempParam;
	/** 模板备注 */
	private java.lang.String tempRemark;
	/** 修改内容 */
	private java.lang.String logRemark;
	private String status;
	/** 操作人ID */
	private java.lang.String operatorId;
	/** 操作人姓名 */
	private java.lang.String operatorName;
	/** 操作时间 */
	private java.util.Date operateTimeBegin;
	private java.util.Date operateTimeEnd;
	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.Long getTemplateId() {
		return this.templateId;
	}
	
	public void setTemplateId(java.lang.Long value) {
		this.templateId = value;
	}
	
	public java.lang.Long getMerctId() {
		return this.merctId;
	}
	
	public void setMerctId(java.lang.Long value) {
		this.merctId = value;
	}
	
	public java.lang.String getMerctCode() {
		return this.merctCode;
	}
	
	public void setMerctCode(java.lang.String value) {
		this.merctCode = value;
	}
	
	public java.lang.String getTempName() {
		return this.tempName;
	}
	
	public void setTempName(java.lang.String value) {
		this.tempName = value;
	}
	
	public java.lang.String getTempContent() {
		return this.tempContent;
	}
	
	public void setTempContent(java.lang.String value) {
		this.tempContent = value;
	}
	
	public java.lang.String getParseMethod() {
		return this.parseMethod;
	}
	
	public void setParseMethod(java.lang.String value) {
		this.parseMethod = value;
	}
	
	public java.lang.String getTempParam() {
		return this.tempParam;
	}
	
	public void setTempParam(java.lang.String value) {
		this.tempParam = value;
	}
	
	public java.lang.String getTempRemark() {
		return this.tempRemark;
	}
	
	public void setTempRemark(java.lang.String value) {
		this.tempRemark = value;
	}
	
	public java.lang.String getLogRemark() {
		return this.logRemark;
	}
	
	public void setLogRemark(java.lang.String value) {
		this.logRemark = value;
	}
	
	public java.lang.String getOperatorId() {
		return this.operatorId;
	}
	
	public void setOperatorId(java.lang.String value) {
		this.operatorId = value;
	}
	
	public java.lang.String getOperatorName() {
		return this.operatorName;
	}
	
	public void setOperatorName(java.lang.String value) {
		this.operatorName = value;
	}
	
	public java.util.Date getOperateTimeBegin() {
		return this.operateTimeBegin;
	}
	
	public void setOperateTimeBegin(java.util.Date value) {
		this.operateTimeBegin = value;
	}	
	
	public java.util.Date getOperateTimeEnd() {
		return this.operateTimeEnd;
	}
	
	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setOperateTimeEnd(java.util.Date value) {
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.operateTimeEnd = calendar.getTime();
		}else {
			this.operateTimeEnd = value;
		}
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}


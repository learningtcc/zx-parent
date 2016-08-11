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

public class EmailTemplateQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    
	/** id */
	private java.lang.Long id;
	/** 商户ID */
	private java.lang.Long merctId;
	/** 商户代码 */
	private java.lang.String merctCode;
	/** 模板名称 */
	private java.lang.String tempName;
	/** 邮件主题 */
	private java.lang.String mailSubject;
	/** 模板内容 */
	private java.lang.String tempContent;
	/** tempType */
	private java.lang.String tempType;
	/** parseMethod */
	private java.lang.String parseMethod;
	/** 模板备注 */
	private java.lang.String tempRemark;
	/** 模板参数 */
	private java.lang.String tempParam;
	/** -1待审批             0正常             2删除 */
	private java.lang.String status;
	/** creatorId */
	private java.lang.String creatorId;
	/** creatorName */
	private java.lang.String creatorName;
	/** createTime */
	private java.util.Date createTimeBegin;
	private java.util.Date createTimeEnd;
	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
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
	
	public java.lang.String getMailSubject() {
		return this.mailSubject;
	}
	
	public void setMailSubject(java.lang.String value) {
		this.mailSubject = value;
	}
	
	public java.lang.String getTempContent() {
		return this.tempContent;
	}
	
	public void setTempContent(java.lang.String value) {
		this.tempContent = value;
	}
	
	public java.lang.String getTempType() {
		return this.tempType;
	}
	
	public void setTempType(java.lang.String value) {
		this.tempType = value;
	}
	
	public java.lang.String getParseMethod() {
		return this.parseMethod;
	}
	
	public void setParseMethod(java.lang.String value) {
		this.parseMethod = value;
	}
	
	public java.lang.String getTempRemark() {
		return this.tempRemark;
	}
	
	public void setTempRemark(java.lang.String value) {
		this.tempRemark = value;
	}
	
	public java.lang.String getTempParam() {
		return this.tempParam;
	}
	
	public void setTempParam(java.lang.String value) {
		this.tempParam = value;
	}
	
	public java.lang.String getStatus() {
		return this.status;
	}
	
	public void setStatus(java.lang.String value) {
		this.status = value;
	}
	
	public java.lang.String getCreatorId() {
		return this.creatorId;
	}
	
	public void setCreatorId(java.lang.String value) {
		this.creatorId = value;
	}
	
	public java.lang.String getCreatorName() {
		return this.creatorName;
	}
	
	public void setCreatorName(java.lang.String value) {
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


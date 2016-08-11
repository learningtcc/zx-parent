/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.msgcenter.core.po;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.ink.base.util.DateConvertUtils;
import com.ink.base.BaseEntity;

/**
 * @author  zongtt
 * @version 1.0
 * @since 1.0
 */

public class EmailTemplateLog extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "邮件模板日志";
//	
//	public static final String ALIAS_ID = "id";
//	
//	public static final String ALIAS_TEMPLATE_ID = "模板ID";
//	
//	public static final String ALIAS_MERCT_ID = "商户ID";
//	
//	public static final String ALIAS_MERCT_CODE = "商户代码";
//	
//	public static final String ALIAS_TEMP_NAME = "模板名称";
//	
//	public static final String ALIAS_TEMP_CONTENT = "模板内容";
//	
//	public static final String ALIAS_TEMP_TYPE = "模板类型";
//	
//	public static final String ALIAS_TEMP_PARAM = "模板参数";
//	
//	public static final String ALIAS_PARSE_METHOD = "解析方式";
//	
//	public static final String ALIAS_TEMP_REMARK = "模板备注";
//	
//	public static final String ALIAS_LOG_REMARK = "修改内容";
//	
//	public static final String ALIAS_OPERATOR_ID = "operatorId";
//	
//	public static final String ALIAS_OPERATOR_NAME = "operatorName";
//	
//	public static final String ALIAS_OPERATE_TIME = "operateTime";
//	
	
	//date formats
	public static final String FORMAT_OPERATE_TIME = DATE_TIME_FORMAT;
	
	//columns START
	//id
	private Long id;
	//模板ID
	private Long templateId;
	//商户ID
	private Long merctId;
	//商户代码
	private String merctCode;
	//模板名称
	private String tempName;
	//模板内容
	private String tempContent;
	//模板类型
	private String tempType;
	//模板参数
	private String tempParam;
	//解析方式
	private String parseMethod;
	//模板备注
	private String tempRemark;
	//修改内容
	private String logRemark;
	private String status;
	//操作人ID
	private String operatorId;
	//操作人姓名
	private String operatorName;
	//操作时间
	private java.util.Date operateTime;
	//columns END

	public EmailTemplateLog(){
	}

	public EmailTemplateLog(
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
	public void setTemplateId(java.lang.Long value) {
		this.templateId = value;
	}
	
	public java.lang.Long getTemplateId() {
		return this.templateId;
	}
	public void setMerctId(java.lang.Long value) {
		this.merctId = value;
	}
	
	public java.lang.Long getMerctId() {
		return this.merctId;
	}
	public void setMerctCode(java.lang.String value) {
		this.merctCode = value;
	}
	
	public java.lang.String getMerctCode() {
		return this.merctCode;
	}
	public void setTempName(java.lang.String value) {
		this.tempName = value;
	}
	
	public java.lang.String getTempName() {
		return this.tempName;
	}
	public void setTempContent(java.lang.String value) {
		this.tempContent = value;
	}
	
	public java.lang.String getTempContent() {
		return this.tempContent;
	}
	public void setTempType(java.lang.String value) {
		this.tempType = value;
	}
	
	public java.lang.String getTempType() {
		return this.tempType;
	}
	public void setTempParam(java.lang.String value) {
		this.tempParam = value;
	}
	
	public java.lang.String getTempParam() {
		return this.tempParam;
	}
	public void setParseMethod(java.lang.String value) {
		this.parseMethod = value;
	}
	
	public java.lang.String getParseMethod() {
		return this.parseMethod;
	}
	public void setTempRemark(java.lang.String value) {
		this.tempRemark = value;
	}
	
	public java.lang.String getTempRemark() {
		return this.tempRemark;
	}
	public void setLogRemark(java.lang.String value) {
		this.logRemark = value;
	}
	
	public java.lang.String getLogRemark() {
		return this.logRemark;
	}
	public void setOperatorId(java.lang.String value) {
		this.operatorId = value;
	}
	
	public java.lang.String getOperatorId() {
		return this.operatorId;
	}
	public void setOperatorName(java.lang.String value) {
		this.operatorName = value;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public java.lang.String getOperatorName() {
		return this.operatorName;
	}
	public String getOperateTimeString() {
		return DateConvertUtils.format(getOperateTime(), FORMAT_OPERATE_TIME);
	}
	public void setOperateTimeString(String value) {
		setOperateTime(DateConvertUtils.parse(value, FORMAT_OPERATE_TIME,java.util.Date.class));
	}
	
	public void setOperateTime(java.util.Date value) {
		this.operateTime = value;
	}
	
	public java.util.Date getOperateTime() {
		return this.operateTime;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EmailTemplateLog [id=");
		builder.append(id);
		builder.append(", templateId=");
		builder.append(templateId);
		builder.append(", merctId=");
		builder.append(merctId);
		builder.append(", merctCode=");
		builder.append(merctCode);
		builder.append(", tempName=");
		builder.append(tempName);
		builder.append(", tempContent=");
		builder.append(tempContent);
		builder.append(", tempType=");
		builder.append(tempType);
		builder.append(", tempParam=");
		builder.append(tempParam);
		builder.append(", parseMethod=");
		builder.append(parseMethod);
		builder.append(", tempRemark=");
		builder.append(tempRemark);
		builder.append(", logRemark=");
		builder.append(logRemark);
		builder.append(", status=");
		builder.append(status);
		builder.append(", operatorId=");
		builder.append(operatorId);
		builder.append(", operatorName=");
		builder.append(operatorName);
		builder.append(", operateTime=");
		builder.append(operateTime);
		builder.append("]");
		return builder.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof EmailTemplateLog == false) return false;
		if(this == obj) return true;
		EmailTemplateLog other = (EmailTemplateLog)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}


/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.msgcenter.core.po;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.ink.base.BaseEntity;
import com.ink.base.util.DateConvertUtils;
import com.ink.msgcenter.core.annotation.FieldMeta;

/**
 * @author  zongtt
 * @version 1.0
 * @since 1.0
 */

public class EmailTemplate extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "邮件模板";
//	
//	public static final String ALIAS_ID = "id";
//	
//	public static final String ALIAS_MERCT_ID = "商户ID";
//	
//	public static final String ALIAS_MERCT_CODE = "商户代码";
//	
//	public static final String ALIAS_TEMP_NAME = "模板名称";
//	
//	public static final String ALIAS_MAIL_SUBJECT = "邮件主题";
//	
//	public static final String ALIAS_TEMP_CONTENT = "模板内容";
//	
//	public static final String ALIAS_TEMP_TYPE = "tempType";
//	
//	public static final String ALIAS_PARSE_METHOD = "parseMethod";
//	
//	public static final String ALIAS_TEMP_REMARK = "模板备注";
//	
//	public static final String ALIAS_TEMP_PARAM = "模板参数";
//	
//	public static final String ALIAS_STATUS = "-1待审批             0正常             2删除";
//	
//	public static final String ALIAS_CREATOR_ID = "creatorId";
//	
//	public static final String ALIAS_CREATOR_NAME = "creatorName";
//	
//	public static final String ALIAS_CREATE_TIME = "createTime";
//	
	
	//date formats
	public static final String FORMAT_CREATE_TIME = DATE_TIME_FORMAT;
	
	//columns START
	//id
	private Long id;
	//商户ID
	@FieldMeta("商户ID")
	private Long merctId;
	//商户代码
	@FieldMeta("商户代码")
	private String merctCode;
	//模板名称
	@FieldMeta("模板名称")
	private String tempName;
	//邮件主题
	@FieldMeta("邮件主题")
	private String mailSubject;
	//模板内容
	@FieldMeta("模板内容")
	private String tempContent;
	//tempType
	@FieldMeta("模板类型")
	private String tempType;
	//parseMethod
	@FieldMeta("解析方式")
	private String parseMethod;
	//模板备注
	@FieldMeta("模板备注")
	private String tempRemark;
	//模板参数
	@FieldMeta("模板参数")
	private String tempParam;
	//-1待审批             0正常             2删除
	@FieldMeta("模板状态")
	private String status;
	//creatorId
	private String creatorId;
	//creatorName
	private String creatorName;
	//createTime
	private Date createTime;
	//columns END

	public EmailTemplate(){
	}

	public EmailTemplate(
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
	public void setMailSubject(java.lang.String value) {
		this.mailSubject = value;
	}
	
	public java.lang.String getMailSubject() {
		return this.mailSubject;
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
	public void setTempParam(java.lang.String value) {
		this.tempParam = value;
	}
	
	public java.lang.String getTempParam() {
		return this.tempParam;
	}
	public void setStatus(java.lang.String value) {
		this.status = value;
	}
	
	public java.lang.String getStatus() {
		return this.status;
	}
	public void setCreatorId(java.lang.String value) {
		this.creatorId = value;
	}
	
	public java.lang.String getCreatorId() {
		return this.creatorId;
	}
	public void setCreatorName(java.lang.String value) {
		this.creatorName = value;
	}
	
	public java.lang.String getCreatorName() {
		return this.creatorName;
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
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("MerctId",getMerctId())
			.append("MerctCode",getMerctCode())
			.append("TempName",getTempName())
			.append("MailSubject",getMailSubject())
			.append("TempContent",getTempContent())
			.append("TempType",getTempType())
			.append("ParseMethod",getParseMethod())
			.append("TempRemark",getTempRemark())
			.append("TempParam",getTempParam())
			.append("Status",getStatus())
			.append("CreatorId",getCreatorId())
			.append("CreatorName",getCreatorName())
			.append("CreateTime",getCreateTime())
			.toString();
	}
	
	public String toString(String separator) {
		StringBuffer sb = new StringBuffer();

			sb.append("id:").append(id).append(separator);
			sb.append("商户ID:").append(merctId).append(separator);
			sb.append("商户代码:").append(merctCode).append(separator);
			sb.append("模板名称:").append(tempName).append(separator);
			sb.append("邮件主题:").append(mailSubject).append(separator);
			sb.append("模板内容:").append(tempContent).append(separator);
			sb.append("tempType:").append(tempType).append(separator);
			sb.append("parseMethod:").append(parseMethod).append(separator);
			sb.append("模板备注:").append(tempRemark).append(separator);
			sb.append("模板参数:").append(tempParam).append(separator);
			sb.append("-1待审批             0正常             2删除:").append(status).append(separator);
			sb.append("creatorId:").append(creatorId).append(separator);
			sb.append("creatorName:").append(creatorName).append(separator);
			sb.append("createTime:").append(getCreateTimeString()).append(separator);
		
		return sb.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof EmailTemplate == false) return false;
		if(this == obj) return true;
		EmailTemplate other = (EmailTemplate)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}


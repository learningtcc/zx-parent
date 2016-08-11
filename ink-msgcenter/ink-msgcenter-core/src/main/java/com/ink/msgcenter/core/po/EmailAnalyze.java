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

public class EmailAnalyze extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "EmailAnalyze";
//	
//	public static final String ALIAS_ID = "ID";
//	
//	public static final String ALIAS_CHN_ID = "通道ID";
//	
//	public static final String ALIAS_CHN_CODE = "通道代码";
//	
//	public static final String ALIAS_SEND_COUNT = "发送总数";
//	
//	public static final String ALIAS_SUCCESS_COUNT = "成功总数";
//	
//	public static final String ALIAS_FAIL_COUNT = "失败总数";
//	
//	public static final String ALIAS_ANALYZE_DATE = "统计日期";
//	
	
	//date formats
	public static final String FORMAT_ANALYZE_DATE = DATE_FORMAT;
	
	//columns START
	//ID
	private Long id;
	//通道ID
	private Long chnId;
	//通道代码
	private String chnCode;
	//发送总数
	private Long sendCount;
	//成功总数
	private Long successCount;
	//失败总数
	private Long failCount;
	//统计日期
	private java.util.Date analyzeDate;
	//columns END

	public EmailAnalyze(){
	}

	public EmailAnalyze(
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
	public void setChnId(java.lang.Long value) {
		this.chnId = value;
	}
	
	public java.lang.Long getChnId() {
		return this.chnId;
	}
	public void setChnCode(java.lang.String value) {
		this.chnCode = value;
	}
	
	public java.lang.String getChnCode() {
		return this.chnCode;
	}
	public void setSendCount(java.lang.Long value) {
		this.sendCount = value;
	}
	
	public java.lang.Long getSendCount() {
		return this.sendCount;
	}
	public void setSuccessCount(java.lang.Long value) {
		this.successCount = value;
	}
	
	public java.lang.Long getSuccessCount() {
		return this.successCount;
	}
	public void setFailCount(java.lang.Long value) {
		this.failCount = value;
	}
	
	public java.lang.Long getFailCount() {
		return this.failCount;
	}
	public String getAnalyzeDateString() {
		return DateConvertUtils.format(getAnalyzeDate(), FORMAT_ANALYZE_DATE);
	}
	public void setAnalyzeDateString(String value) {
		setAnalyzeDate(DateConvertUtils.parse(value, FORMAT_ANALYZE_DATE,java.util.Date.class));
	}
	
	public void setAnalyzeDate(java.util.Date value) {
		this.analyzeDate = value;
	}
	
	public java.util.Date getAnalyzeDate() {
		return this.analyzeDate;
	}
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("ChnId",getChnId())
			.append("ChnCode",getChnCode())
			.append("SendCount",getSendCount())
			.append("SuccessCount",getSuccessCount())
			.append("FailCount",getFailCount())
			.append("AnalyzeDate",getAnalyzeDate())
			.toString();
	}
	
	public String toString(String separator) {
		StringBuffer sb = new StringBuffer();

			sb.append("ID:").append(id).append(separator);
			sb.append("通道ID:").append(chnId).append(separator);
			sb.append("通道代码:").append(chnCode).append(separator);
			sb.append("发送总数:").append(sendCount).append(separator);
			sb.append("成功总数:").append(successCount).append(separator);
			sb.append("失败总数:").append(failCount).append(separator);
			sb.append("统计日期:").append(getAnalyzeDateString()).append(separator);
		
		return sb.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof EmailAnalyze == false) return false;
		if(this == obj) return true;
		EmailAnalyze other = (EmailAnalyze)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}


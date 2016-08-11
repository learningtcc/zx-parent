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

public class SmsAnalyze extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "SmsAnalyze";
//	
//	public static final String ALIAS_ID = "id";
//	
//	public static final String ALIAS_CHN_ID = "通道ID";
//	
//	public static final String ALIAS_CHN_CODE = "通道代码";
//	
//	public static final String ALIAS_SEND_COUNT = "发送总数";
//	
//	public static final String ALIAS_SUCCESS_COUNT = "成功总数";
//	
//	public static final String ALIAS_DELIVE_COUNT = "送达总数";
//	
//	public static final String ALIAS_UP_COUNT = "上行总数";
//	
//	public static final String ALIAS_FAIL_COUNT = "失败总数";
//	
//	public static final String ALIAS_ANALYZE_DATE = "统计日期";
//	
	
	//date formats
	public static final String FORMAT_ANALYZE_DATE = DATE_FORMAT;
	
	//columns START
	//id
	private Long id;
	//通道ID
	private Long chnId;
	//通道代码
	private String chnCode;
	//发送总数
	private Long sendCount;
	//成功总数
	private Long successCount;
	//送达总数
	private Long deliveCount;
	//上行总数
	private Long upCount;
	//失败总数
	private Long failCount;
	//统计日期
	private java.util.Date analyzeDate;
	//columns END

	public SmsAnalyze(){
	}

	public SmsAnalyze(
		Long id
	){
		this.id = id;
	}

	public void setId(Long value) {
		this.id = value;
	}
	
	public Long getId() {
		return this.id;
	}
	public void setChnId(Long value) {
		this.chnId = value;
	}
	
	public Long getChnId() {
		return this.chnId;
	}
	public void setChnCode(String value) {
		this.chnCode = value;
	}
	
	public String getChnCode() {
		return this.chnCode;
	}
	public void setSendCount(Long value) {
		this.sendCount = value;
	}
	
	public Long getSendCount() {
		return this.sendCount;
	}
	public void setSuccessCount(Long value) {
		this.successCount = value;
	}
	
	public Long getSuccessCount() {
		return this.successCount;
	}
	public void setDeliveCount(Long value) {
		this.deliveCount = value;
	}
	
	public Long getDeliveCount() {
		return this.deliveCount;
	}
	public void setUpCount(Long value) {
		this.upCount = value;
	}
	
	public Long getUpCount() {
		return this.upCount;
	}
	public void setFailCount(Long value) {
		this.failCount = value;
	}
	
	public Long getFailCount() {
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
			.append("DeliveCount",getDeliveCount())
			.append("UpCount",getUpCount())
			.append("FailCount",getFailCount())
			.append("AnalyzeDate",getAnalyzeDate())
			.toString();
	}
	
	public String toString(String separator) {
		StringBuffer sb = new StringBuffer();

			sb.append("id:").append(id).append(separator);
			sb.append("通道ID:").append(chnId).append(separator);
			sb.append("通道代码:").append(chnCode).append(separator);
			sb.append("发送总数:").append(sendCount).append(separator);
			sb.append("成功总数:").append(successCount).append(separator);
			sb.append("送达总数:").append(deliveCount).append(separator);
			sb.append("上行总数:").append(upCount).append(separator);
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
		if(obj instanceof SmsAnalyze == false) return false;
		if(this == obj) return true;
		SmsAnalyze other = (SmsAnalyze)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}


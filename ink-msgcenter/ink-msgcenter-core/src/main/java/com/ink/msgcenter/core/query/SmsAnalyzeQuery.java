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

public class SmsAnalyzeQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    
	/** id */
	private Long id;
	/** 通道ID */
	private Long chnId;
	/** 通道代码 */
	private String chnCode;
	/** 发送总数 */
	private Long sendCount;
	/** 成功总数 */
	private Long successCount;
	/** 送达总数 */
	private Long deliveCount;
	/** 上行总数 */
	private Long upCount;
	/** 失败总数 */
	private Long failCount;
	/** 统计日期 */
	private java.util.Date analyzeDateBegin;
	private java.util.Date analyzeDateEnd;
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long value) {
		this.id = value;
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
	
	public Long getSendCount() {
		return this.sendCount;
	}
	
	public void setSendCount(Long value) {
		this.sendCount = value;
	}
	
	public Long getSuccessCount() {
		return this.successCount;
	}
	
	public void setSuccessCount(Long value) {
		this.successCount = value;
	}
	
	public Long getDeliveCount() {
		return this.deliveCount;
	}
	
	public void setDeliveCount(Long value) {
		this.deliveCount = value;
	}
	
	public Long getUpCount() {
		return this.upCount;
	}
	
	public void setUpCount(Long value) {
		this.upCount = value;
	}
	
	public Long getFailCount() {
		return this.failCount;
	}
	
	public void setFailCount(Long value) {
		this.failCount = value;
	}
	
	public java.util.Date getAnalyzeDateBegin() {
		return this.analyzeDateBegin;
	}
	
	public void setAnalyzeDateBegin(java.util.Date value) {
		this.analyzeDateBegin = value;
	}	
	
	public java.util.Date getAnalyzeDateEnd() {
		return this.analyzeDateEnd;
	}
	
	public void setAnalyzeDateEnd(java.util.Date value) {
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.analyzeDateEnd = calendar.getTime();
		}else {
			this.analyzeDateEnd = value;
		}
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}


/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.scheduler.core.query;

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

public class TimerTaskJobLogQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    
	/** id */
	private Integer id;
	/** 任务ID */
	private Integer jobId;

	/** 任务开始时间 */
	private java.util.Date startTimeBegin;
	private java.util.Date startTimeEnd;
	/** 任务结束时间 */
	private java.util.Date endTimeBegin;
	private java.util.Date endTimeEnd;
	/** 任务执行结果 */
	private String status;
	/** 业务流水ID */
	private String reqId;
	/** 错误信息 */
	private String errorMsg;

	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer value) {
		this.id = value;
	}
	
	public Integer getJobId() {
		return this.jobId;
	}
	
	public void setJobId(Integer value) {
		this.jobId = value;
	}

	public java.util.Date getStartTimeBegin() {
		return this.startTimeBegin;
	}

	public void setStartTimeBegin(java.util.Date value) {
		this.startTimeBegin = value;
	}

	public java.util.Date getStartTimeEnd() {
		return this.startTimeEnd;
	}

	public void setStartTimeEnd(java.util.Date value) {
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.startTimeEnd = calendar.getTime();
		}else {
			this.startTimeEnd = value;
		}
	}

	public java.util.Date getEndTimeBegin() {
		return this.endTimeBegin;
	}

	public void setEndTimeBegin(java.util.Date value) {
		this.endTimeBegin = value;
	}

	public java.util.Date getEndTimeEnd() {
		return this.endTimeEnd;
	}

	public void setEndTimeEnd(java.util.Date value) {
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.endTimeEnd = calendar.getTime();
		}else {
			this.endTimeEnd = value;
		}
	}

	public String getStatus() {
		return this.status;
	}
	
	public void setStatus(String value) {
		this.status = value;
	}
	
	public String getReqId() {
		return this.reqId;
	}
	
	public void setReqId(String value) {
		this.reqId = value;
	}
	
	public String getErrorMsg() {
		return this.errorMsg;
	}
	
	public void setErrorMsg(String value) {
		this.errorMsg = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}


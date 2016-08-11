/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.monitor.core.query;

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

public class MonitorInfoRuleQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    
	/** id */
	private Integer id;
	/** 系统代码 */
	private String sysCode;
	/** 模块代码 */
	private String moduleCode;
	/** 业务代码 */
	private String infoCode;
	/** 预警间隔（单位分钟），如15表示15分钟内该模块报警超过阈值未达到频次时需报警，数值为正整数 */
	private Integer warnInterval;
	/** 预警间隔（单位分钟），如15表示15分钟内该模块报警超过阈值未达到频次时需报警，数值为正整数 */
	private Integer warnThreshold;
	/** 预警间隔（单位分钟），如15表示15分钟内该模块报警超过阈值未达到频次时需报警，数值为正整数 */
	private Integer warnFrequency;
	/** 当前已报警次数 */
	private Integer currentWarnCount;
	/** 创建时间 */
	private java.util.Date createTimeBegin;
	private java.util.Date createTimeEnd;
	/** 更新时间 */
	private java.util.Date updateTimeBegin;
	private java.util.Date updateTimeEnd;
//	状态（0，1）
	private String status;
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer value) {
		this.id = value;
	}
	
	public String getSysCode() {
		return this.sysCode;
	}
	
	public void setSysCode(String value) {
		this.sysCode = value;
	}
	
	public String getModuleCode() {
		return this.moduleCode;
	}
	
	public void setModuleCode(String value) {
		this.moduleCode = value;
	}
	
	public String getInfoCode() {
		return this.infoCode;
	}
	
	public void setInfoCode(String value) {
		this.infoCode = value;
	}
	
	public Integer getWarnInterval() {
		return this.warnInterval;
	}
	
	public void setWarnInterval(Integer value) {
		this.warnInterval = value;
	}
	
	public Integer getWarnThreshold() {
		return this.warnThreshold;
	}
	
	public void setWarnThreshold(Integer value) {
		this.warnThreshold = value;
	}
	
	public Integer getWarnFrequency() {
		return this.warnFrequency;
	}
	
	public void setWarnFrequency(Integer value) {
		this.warnFrequency = value;
	}
	
	public Integer getCurrentWarnCount() {
		return this.currentWarnCount;
	}
	
	public void setCurrentWarnCount(Integer value) {
		this.currentWarnCount = value;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}


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

public class MonitorUserRuleQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    
	/** id */
	private Integer id;
	/** 用户ID */
	private Integer userId;
	/** 系统代码 */
	private String sysCode;
	/** 模块代码 */
	private String moduleCode;
	/** 业务代码 */
	private String infoCode;
	/** 创建时间 */
	private java.util.Date createTimeBegin;
	private java.util.Date createTimeEnd;
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer value) {
		this.id = value;
	}
	
	public Integer getUserId() {
		return this.userId;
	}
	
	public void setUserId(Integer value) {
		this.userId = value;
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


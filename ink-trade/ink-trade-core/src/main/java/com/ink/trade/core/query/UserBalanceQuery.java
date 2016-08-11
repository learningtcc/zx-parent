/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.trade.core.query;

import com.ink.base.BaseQuery;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

/**
 * @author  zongtt
 * @version 1.0
 * @since 1.0
 */

public class UserBalanceQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    
	/** 主键 */
	private java.lang.Long id;
	/** 商户号 */
	private java.lang.String mchId;
	/** 用户号 */
	private java.lang.String userId;
	/** 余额 */
	private BigDecimal balance;
	/** 创建时间 */
	private java.util.Date createTimeBegin;
	private java.util.Date createTimeEnd;
	/** 最后更新时间 */
	private java.util.Date lastUpdatetimeBegin;
	private java.util.Date lastUpdatetimeEnd;
	/** 版本号 */
	private java.lang.Integer version;
	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.String getMchId() {
		return this.mchId;
	}
	
	public void setMchId(java.lang.String value) {
		this.mchId = value;
	}
	
	public java.lang.String getUserId() {
		return this.userId;
	}
	
	public void setUserId(java.lang.String value) {
		this.userId = value;
	}
	
	public BigDecimal getBalance() {
		return this.balance;
	}
	
	public void setBalance(BigDecimal value) {
		this.balance = value;
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
	
	public java.util.Date getLastUpdatetimeBegin() {
		return this.lastUpdatetimeBegin;
	}
	
	public void setLastUpdatetimeBegin(java.util.Date value) {
		this.lastUpdatetimeBegin = value;
	}	
	
	public java.util.Date getLastUpdatetimeEnd() {
		return this.lastUpdatetimeEnd;
	}
	
	public void setLastUpdatetimeEnd(java.util.Date value) {
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.lastUpdatetimeEnd = calendar.getTime();
		}else {
			this.lastUpdatetimeEnd = value;
		}
	}
	
	public java.lang.Integer getVersion() {
		return this.version;
	}
	
	public void setVersion(java.lang.Integer value) {
		this.version = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}


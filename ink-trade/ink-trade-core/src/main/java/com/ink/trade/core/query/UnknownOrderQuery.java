/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.trade.core.query;

import com.ink.base.BaseQuery;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Calendar;

/**
 * @author  zongtt
 * @version 1.0
 * @since 1.0
 */

public class UnknownOrderQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    
	/** 主键 */
	private Long id;
	/** 支付订单表主键 */
	private Long payId;
	/** 支付订单号 */
	private String payOrderNo;
	/** 订单状态 */
	private Integer orderStatus;
	/** 查询订单状态 */
	private Integer queryStatus;
	/** 落地渠道号 */
	private String channelNo;
	/** 任务标记位（唯一标识某台机器） */
	private String executeRemark;
	/** 任务执行次数 */
	private Integer executeCount;
	/** 订单时间 */
	private java.util.Date orderTimeBegin;
	private java.util.Date orderTimeEnd;
	/** 最后更新时间 */
	private java.util.Date lastUpdateTimeBegin;
	private java.util.Date lastUpdateTimeEnd;
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long value) {
		this.id = value;
	}
	
	public Long getPayId() {
		return this.payId;
	}
	
	public void setPayId(Long value) {
		this.payId = value;
	}
	
	public String getPayOrderNo() {
		return this.payOrderNo;
	}
	
	public void setPayOrderNo(String value) {
		this.payOrderNo = value;
	}
	
	public Integer getOrderStatus() {
		return this.orderStatus;
	}
	
	public void setOrderStatus(Integer value) {
		this.orderStatus = value;
	}
	
	public Integer getQueryStatus() {
		return this.queryStatus;
	}
	
	public void setQueryStatus(Integer value) {
		this.queryStatus = value;
	}
	
	public String getChannelNo() {
		return this.channelNo;
	}
	
	public void setChannelNo(String value) {
		this.channelNo = value;
	}
	
	public String getExecuteRemark() {
		return this.executeRemark;
	}
	
	public void setExecuteRemark(String value) {
		this.executeRemark = value;
	}
	
	public Integer getExecuteCount() {
		return this.executeCount;
	}
	
	public void setExecuteCount(Integer value) {
		this.executeCount = value;
	}
	
	public java.util.Date getOrderTimeBegin() {
		return this.orderTimeBegin;
	}
	
	public void setOrderTimeBegin(java.util.Date value) {
		this.orderTimeBegin = value;
	}	
	
	public java.util.Date getOrderTimeEnd() {
		return this.orderTimeEnd;
	}
	
	public void setOrderTimeEnd(java.util.Date value) {
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.orderTimeEnd = calendar.getTime();
		}else {
			this.orderTimeEnd = value;
		}
	}
	
	public java.util.Date getLastUpdateTimeBegin() {
		return this.lastUpdateTimeBegin;
	}
	
	public void setLastUpdateTimeBegin(java.util.Date value) {
		this.lastUpdateTimeBegin = value;
	}	
	
	public java.util.Date getLastUpdateTimeEnd() {
		return this.lastUpdateTimeEnd;
	}
	
	public void setLastUpdateTimeEnd(java.util.Date value) {
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.lastUpdateTimeEnd = calendar.getTime();
		}else {
			this.lastUpdateTimeEnd = value;
		}
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}


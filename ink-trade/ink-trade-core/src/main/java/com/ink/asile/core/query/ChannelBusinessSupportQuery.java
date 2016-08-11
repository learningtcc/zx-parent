/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.asile.core.query;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.ink.base.BaseQuery;

/**
 * @author  zongtt
 * @version 1.0
 * @since 1.0
 */

public class ChannelBusinessSupportQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    
	/** id */
	private java.lang.Long id;
	/** 渠道编号 */
	private java.lang.String channelNo;
	/** 是否支持代收（Y-支持，N-不支持） */
	private java.lang.String isCollectSupport;
	/** 是否支持快捷（Y-支持，N-不支持） */
	private java.lang.String isQuickpaySupport;
	/** 是否支持代付（Y-支持，N-不支持） */
	private java.lang.String isPaymentSupport;
	/** 是否支持网关（Y-支持，N-不支持） */
	private java.lang.String isGatewaySupport;
	/** 代收签约通道是否发短信（Y-发，N-不发） */
	private java.lang.String isCollectSignSms;
	/** 代付签约通道是否发短信（Y-发，N-不发） */
	private java.lang.String isPaymentSignSms;
	/** 代收支付通道是否发短信（Y-发，N-不发） */
	private java.lang.String isCollectPaySms;
	/** 快捷支付通道是否发短信（Y-发，N-不发） */
	private java.lang.String isQuickPaySms;
	/** 代付通道是否发短信（Y-发，N-不发） */
	private java.lang.String isPaymentPaySms;
	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.String getChannelNo() {
		return this.channelNo;
	}
	
	public void setChannelNo(java.lang.String value) {
		this.channelNo = value;
	}
	
	public java.lang.String getIsCollectSupport() {
		return this.isCollectSupport;
	}
	
	public void setIsCollectSupport(java.lang.String value) {
		this.isCollectSupport = value;
	}
	
	public java.lang.String getIsQuickpaySupport() {
		return this.isQuickpaySupport;
	}
	
	public void setIsQuickpaySupport(java.lang.String value) {
		this.isQuickpaySupport = value;
	}
	
	public java.lang.String getIsPaymentSupport() {
		return this.isPaymentSupport;
	}
	
	public void setIsPaymentSupport(java.lang.String value) {
		this.isPaymentSupport = value;
	}
	
	public java.lang.String getIsGatewaySupport() {
		return this.isGatewaySupport;
	}
	
	public void setIsGatewaySupport(java.lang.String value) {
		this.isGatewaySupport = value;
	}
	
	public java.lang.String getIsCollectSignSms() {
		return this.isCollectSignSms;
	}
	
	public void setIsCollectSignSms(java.lang.String value) {
		this.isCollectSignSms = value;
	}
	
	public java.lang.String getIsPaymentSignSms() {
		return this.isPaymentSignSms;
	}
	
	public void setIsPaymentSignSms(java.lang.String value) {
		this.isPaymentSignSms = value;
	}
	
	public java.lang.String getIsCollectPaySms() {
		return this.isCollectPaySms;
	}
	
	public void setIsCollectPaySms(java.lang.String value) {
		this.isCollectPaySms = value;
	}
	
	public java.lang.String getIsQuickPaySms() {
		return this.isQuickPaySms;
	}
	
	public void setIsQuickPaySms(java.lang.String value) {
		this.isQuickPaySms = value;
	}
	
	public java.lang.String getIsPaymentPaySms() {
		return this.isPaymentPaySms;
	}
	
	public void setIsPaymentPaySms(java.lang.String value) {
		this.isPaymentPaySms = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}


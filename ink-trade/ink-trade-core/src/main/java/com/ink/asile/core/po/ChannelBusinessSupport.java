/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.asile.core.po;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.ink.base.BaseEntity;
import com.ink.base.util.DateConvertUtils;

/**
 * @author  zongtt
 * @version 1.0
 * @since 1.0
 */

public class ChannelBusinessSupport extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "ChannelBusinessSupport";
//	
//	public static final String ALIAS_ID = "id";
//	
//	public static final String ALIAS_CHANNEL_NO = "渠道编号";
//	
//	public static final String ALIAS_IS_COLLECT_SUPPORT = "是否支持代收（Y-支持，N-不支持）";
//	
//	public static final String ALIAS_IS_QUICKPAY_SUPPORT = "是否支持快捷（Y-支持，N-不支持）";
//	
//	public static final String ALIAS_IS_PAYMENT_SUPPORT = "是否支持代付（Y-支持，N-不支持）";
//	
//	public static final String ALIAS_IS_GATEWAY_SUPPORT = "是否支持网关（Y-支持，N-不支持）";
//	
//	public static final String ALIAS_IS_COLLECT_SIGN_SMS = "代收签约通道是否发短信（Y-发，N-不发）";
//	
//	public static final String ALIAS_IS_PAYMENT_SIGN_SMS = "代付签约通道是否发短信（Y-发，N-不发）";
//	
//	public static final String ALIAS_IS_COLLECT_PAY_SMS = "代收支付通道是否发短信（Y-发，N-不发）";
//	
//	public static final String ALIAS_IS_QUICK_PAY_SMS = "快捷支付通道是否发短信（Y-发，N-不发）";
//	
//	public static final String ALIAS_IS_PAYMENT_PAY_SMS = "代付通道是否发短信（Y-发，N-不发）";
//	
//	public static final String ALIAS_CREATE_TIME = "创建时间";
//	
//	public static final String ALIAS_LAST_UPDATE_TIME = "最后更新时间";
//	
//	public static final String ALIAS_CREATER = "创建人";
//	
//	public static final String ALIAS_UPDATER = "更新人";
//	
	
	//date formats
	public static final String FORMAT_CREATE_TIME = DATE_FORMAT;
	public static final String FORMAT_LAST_UPDATE_TIME = DATE_FORMAT;
	
	//columns START
	//id
	private Long id;
	//渠道编号
	private String channelNo;
	//是否支持代收（Y-支持，N-不支持）
	private String isCollectSupport;
	//是否支持快捷（Y-支持，N-不支持）
	private String isQuickpaySupport;
	//是否支持代付（Y-支持，N-不支持）
	private String isPaymentSupport;
	//是否支持网关（Y-支持，N-不支持）
	private String isGatewaySupport;
	//是否支持认证支付（Y-支持，N-不支持）
	private String isCertifiedpaySupport;
	//代收签约通道是否发短信（Y-发，N-不发）
	private String isCollectSignSms;
	//代付签约通道是否发短信（Y-发，N-不发）
	private String isPaymentSignSms;
	//代收支付通道是否发短信（Y-发，N-不发）
	private String isCollectPaySms;
	//快捷支付通道是否发短信（Y-发，N-不发）
	private String isQuickPaySms;
	//代付通道是否发短信（Y-发，N-不发）
	private String isPaymentPaySms;
	//认证支付是否发短信
	private String isCertifiedpaySms;
	//创建时间
	private java.util.Date createTime;
	//最后更新时间
	private java.util.Date lastUpdateTime;
	//创建人
	private String creater;
	//更新人
	private String updater;
	//columns END

	public ChannelBusinessSupport(){
	}

	public ChannelBusinessSupport(
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
	public void setChannelNo(java.lang.String value) {
		this.channelNo = value;
	}
	
	public java.lang.String getChannelNo() {
		return this.channelNo;
	}
	public void setIsCollectSupport(java.lang.String value) {
		this.isCollectSupport = value;
	}
	
	public java.lang.String getIsCollectSupport() {
		return this.isCollectSupport;
	}
	public void setIsQuickpaySupport(java.lang.String value) {
		this.isQuickpaySupport = value;
	}
	
	public java.lang.String getIsQuickpaySupport() {
		return this.isQuickpaySupport;
	}
	public void setIsPaymentSupport(java.lang.String value) {
		this.isPaymentSupport = value;
	}
	
	public java.lang.String getIsPaymentSupport() {
		return this.isPaymentSupport;
	}
	public void setIsGatewaySupport(java.lang.String value) {
		this.isGatewaySupport = value;
	}
	
	public java.lang.String getIsGatewaySupport() {
		return this.isGatewaySupport;
	}
	public void setIsCollectSignSms(java.lang.String value) {
		this.isCollectSignSms = value;
	}
	
	public java.lang.String getIsCollectSignSms() {
		return this.isCollectSignSms;
	}
	public void setIsPaymentSignSms(java.lang.String value) {
		this.isPaymentSignSms = value;
	}
	
	public java.lang.String getIsPaymentSignSms() {
		return this.isPaymentSignSms;
	}
	public void setIsCollectPaySms(java.lang.String value) {
		this.isCollectPaySms = value;
	}
	
	public java.lang.String getIsCollectPaySms() {
		return this.isCollectPaySms;
	}
	public void setIsQuickPaySms(java.lang.String value) {
		this.isQuickPaySms = value;
	}
	
	public java.lang.String getIsQuickPaySms() {
		return this.isQuickPaySms;
	}
	public void setIsPaymentPaySms(java.lang.String value) {
		this.isPaymentPaySms = value;
	}
	
	public java.lang.String getIsPaymentPaySms() {
		return this.isPaymentPaySms;
	}
	public String getCreateTimeString() {
		return DateConvertUtils.format(getCreateTime(), FORMAT_CREATE_TIME);
	}
	public void setCreateTimeString(String value) {
		setCreateTime(DateConvertUtils.parse(value, FORMAT_CREATE_TIME,java.util.Date.class));
	}
	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	public String getLastUpdateTimeString() {
		return DateConvertUtils.format(getLastUpdateTime(), FORMAT_LAST_UPDATE_TIME);
	}
	public void setLastUpdateTimeString(String value) {
		setLastUpdateTime(DateConvertUtils.parse(value, FORMAT_LAST_UPDATE_TIME,java.util.Date.class));
	}
	
	public void setLastUpdateTime(java.util.Date value) {
		this.lastUpdateTime = value;
	}
	
	public java.util.Date getLastUpdateTime() {
		return this.lastUpdateTime;
	}
	public void setCreater(java.lang.String value) {
		this.creater = value;
	}
	
	public java.lang.String getCreater() {
		return this.creater;
	}
	public void setUpdater(java.lang.String value) {
		this.updater = value;
	}
	
	public java.lang.String getUpdater() {
		return this.updater;
	}

	public String getIsCertifiedpaySupport() {
		return isCertifiedpaySupport;
	}

	public void setIsCertifiedpaySupport(String isCertifiedpaySupport) {
		this.isCertifiedpaySupport = isCertifiedpaySupport;
	}

	public String getIsCertifiedpaySms() {
		return isCertifiedpaySms;
	}

	public void setIsCertifiedpaySms(String isCertifiedpaySms) {
		this.isCertifiedpaySms = isCertifiedpaySms;
	}

	@Override
	public String toString() {
		return "ChannelBusinessSupport{" +
				"id=" + id +
				", channelNo='" + channelNo + '\'' +
				", isCollectSupport='" + isCollectSupport + '\'' +
				", isQuickpaySupport='" + isQuickpaySupport + '\'' +
				", isPaymentSupport='" + isPaymentSupport + '\'' +
				", isGatewaySupport='" + isGatewaySupport + '\'' +
				", isCertifiedpaySupport='" + isCertifiedpaySupport + '\'' +
				", isCollectSignSms='" + isCollectSignSms + '\'' +
				", isPaymentSignSms='" + isPaymentSignSms + '\'' +
				", isCollectPaySms='" + isCollectPaySms + '\'' +
				", isQuickPaySms='" + isQuickPaySms + '\'' +
				", isPaymentPaySms='" + isPaymentPaySms + '\'' +
				", isCertifiedpaySms='" + isCertifiedpaySms + '\'' +
				", createTime=" + createTime +
				", lastUpdateTime=" + lastUpdateTime +
				", creater='" + creater + '\'' +
				", updater='" + updater + '\'' +
				'}';
	}

	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ChannelBusinessSupport == false) return false;
		if(this == obj) return true;
		ChannelBusinessSupport other = (ChannelBusinessSupport)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}


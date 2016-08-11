package com.ink.channel.core.model;

import java.io.Serializable;
/**
 * 获取渠道和平台返回码关联关系请求输入参数
 * @author huohb
 *
 */
public class ChanPlatResCodeInput implements Serializable{
	
	private static final long serialVersionUID = 8521647114132697327L;

	private String channelId;// 渠道号
	
	private String resCode;// 返回码
	
	private String resMsg;// 返回信息
	
	private String bizId;// 业务编码
	
	private String orderNo;// 订单号

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getResCode() {
		return resCode;
	}

	public void setResCode(String resCode) {
		this.resCode = resCode;
	}

	public String getResMsg() {
		return resMsg;
	}

	public void setResMsg(String resMsg) {
		this.resMsg = resMsg;
	}

	public String getBizId() {
		return bizId;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
	}
}

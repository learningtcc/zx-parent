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

public class MonitorMqLogQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    
	/** ID */
	private Long id;
	/** 系统代码 */
	private String sysCode;
	/** MQ交换器 */
	private String exchange;
	/** MQ路由 */
	private String routingKey;
	/** 消息ID */
	private String msgId;
	/** MQ IP */
	private String rabbitAddress;
	/** MQ端口 */
	private Integer rabbitPort;
	/** 本地IP */
	private String localAddress;
	/** 消息异常 */
	private String msgException;
	/** 请求流水 */
	private String requestId;
	/** 消息类型 */
	private String msgType;
	/** 字符串消息 */
	private String msgText;
	/** 序列化消息 */
	private byte[] msgObject;
	/** 状态：0未解决、1已解决 */
	private String logStatus;
	/** 创建时间 */
	private java.util.Date createTimeBegin;
	private java.util.Date createTimeEnd;
	/** 处理时间 */
	private java.util.Date fixedTimeBegin;
	private java.util.Date fixedTimeEnd;
	/*类名*/
	private String className;
	/*应用名*/
	private String appName;
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long value) {
		this.id = value;
	}
	
	public String getSysCode() {
		return this.sysCode;
	}
	
	public void setSysCode(String value) {
		this.sysCode = value;
	}
	
	public String getExchange() {
		return this.exchange;
	}
	
	public void setExchange(String value) {
		this.exchange = value;
	}
	
	public String getRoutingKey() {
		return this.routingKey;
	}
	
	public void setRoutingKey(String value) {
		this.routingKey = value;
	}
	
	public String getMsgId() {
		return this.msgId;
	}
	
	public void setMsgId(String value) {
		this.msgId = value;
	}
	
	public String getRabbitAddress() {
		return this.rabbitAddress;
	}
	
	public void setRabbitAddress(String value) {
		this.rabbitAddress = value;
	}
	
	public Integer getRabbitPort() {
		return this.rabbitPort;
	}
	
	public void setRabbitPort(Integer value) {
		this.rabbitPort = value;
	}
	
	public String getLocalAddress() {
		return this.localAddress;
	}
	
	public void setLocalAddress(String value) {
		this.localAddress = value;
	}
	
	public String getMsgException() {
		return this.msgException;
	}
	
	public void setMsgException(String value) {
		this.msgException = value;
	}
	
	public String getRequestId() {
		return this.requestId;
	}
	
	public void setRequestId(String value) {
		this.requestId = value;
	}
	
	public String getMsgType() {
		return this.msgType;
	}
	
	public void setMsgType(String value) {
		this.msgType = value;
	}
	
	public String getMsgText() {
		return this.msgText;
	}
	
	public void setMsgText(String value) {
		this.msgText = value;
	}
	
	public byte[] getMsgObject() {
		return this.msgObject;
	}
	
	public void setMsgObject(byte[] value) {
		this.msgObject = value;
	}
	
	public String getLogStatus() {
		return this.logStatus;
	}
	
	public void setLogStatus(String value) {
		this.logStatus = value;
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
	
	public java.util.Date getFixedTimeBegin() {
		return this.fixedTimeBegin;
	}
	
	public void setFixedTimeBegin(java.util.Date value) {
		this.fixedTimeBegin = value;
	}	
	
	public java.util.Date getFixedTimeEnd() {
		return this.fixedTimeEnd;
	}
	
	public void setFixedTimeEnd(java.util.Date value) {
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.fixedTimeEnd = calendar.getTime();
		}else {
			this.fixedTimeEnd = value;
		}
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}


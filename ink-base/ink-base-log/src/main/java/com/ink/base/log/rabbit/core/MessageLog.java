package com.ink.base.log.rabbit.core;

import java.io.Serializable;

public class MessageLog implements Serializable{
	
	private static final long serialVersionUID = 7667946978160606368L;

	private String sysCode;
	
	private String exchange;
	
	private String routingKey;
	
	private String msgId;
	
	private String rabbitAddress;
	
	private Integer rabbitPort;
	
	private String localAddress;
	
	private String msgException;
	
	private String requestId;
	
	private String msgType;
	
	private String msgText;
	
	private byte[] msgObject;
	
	private String className;
	
	private String appName;

	public String getSysCode() {
		return sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public String getRoutingKey() {
		return routingKey;
	}

	public void setRoutingKey(String routingKey) {
		this.routingKey = routingKey;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getRabbitAddress() {
		return rabbitAddress;
	}

	public void setRabbitAddress(String rabbitAddress) {
		this.rabbitAddress = rabbitAddress;
	}

	public Integer getRabbitPort() {
		return rabbitPort;
	}

	public void setRabbitPort(Integer rabbitPort) {
		this.rabbitPort = rabbitPort;
	}

	public String getLocalAddress() {
		return localAddress;
	}

	public void setLocalAddress(String localAddress) {
		this.localAddress = localAddress;
	}

	public String getMsgException() {
		return msgException;
	}

	public void setMsgException(String msgException) {
		this.msgException = msgException;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getMsgText() {
		return msgText;
	}

	public void setMsgText(String msgText) {
		this.msgText = msgText;
	}

	public byte[] getMsgObject() {
		return msgObject;
	}

	public void setMsgObject(byte[] msgObject) {
		this.msgObject = msgObject;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassName() {
		return className;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}
}

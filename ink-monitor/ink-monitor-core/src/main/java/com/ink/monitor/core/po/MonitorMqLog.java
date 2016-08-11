/**
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.monitor.core.po;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.ink.base.util.DateConvertUtils;
import com.ink.base.BaseEntity;

/**
 * @author zongtt
 * @version 1.0
 * @since 1.0
 */

public class MonitorMqLog extends BaseEntity implements java.io.Serializable {
    private static final long serialVersionUID = 5454155825314635342L;

    //alias
    public static final String TABLE_ALIAS = "MonitorMqLog";
//	
//	public static final String ALIAS_ID = "ID";
//	
//	public static final String ALIAS_SYS_CODE = "系统代码";
//	
//	public static final String ALIAS_EXCHANGE = "MQ交换器";
//	
//	public static final String ALIAS_ROUTING_KEY = "MQ路由";
//	
//	public static final String ALIAS_MSG_ID = "消息ID";
//	
//	public static final String ALIAS_RABBIT_ADDRESS = "MQ IP";
//	
//	public static final String ALIAS_RABBIT_PORT = "MQ端口";
//	
//	public static final String ALIAS_LOCAL_ADDRESS = "本地IP";
//	
//	public static final String ALIAS_MSG_EXCEPTION = "消息异常";
//	
//	public static final String ALIAS_REQUEST_ID = "请求流水";
//	
//	public static final String ALIAS_MSG_TYPE = "消息类型";
//	
//	public static final String ALIAS_MSG_TEXT = "字符串消息";
//	
//	public static final String ALIAS_MSG_OBJECT = "序列化消息";
//	
//	public static final String ALIAS_LOG_STATUS = "状态：0未解决、1已解决";
//	
//	public static final String ALIAS_CREATE_TIME = "创建时间";
//	
//	public static final String ALIAS_FIXED_TIME = "处理时间";
//	

    //date formats
    public static final String FORMAT_CREATE_TIME = DATE_FORMAT;
    public static final String FORMAT_FIXED_TIME = DATE_FORMAT;

    //columns START
    //ID
    private Long id;
    //系统代码
    private String sysCode;
    //MQ交换器
    private String exchange;
    //MQ路由
    private String routingKey;
    //消息ID
    private String msgId;
    //MQ IP
    private String rabbitAddress;
    //MQ端口
    private Integer rabbitPort;
    //本地IP
    private String localAddress;
    //消息异常
    private String msgException;
    //请求流水
    private String requestId;
    //消息类型
    private String msgType;
    //字符串消息
    private String msgText;
    //序列化消息
    private byte[] msgObject;
    //状态：0未解决、1已解决
    private String logStatus;
    //创建时间
    private java.util.Date createTime;
    //处理时间
    private java.util.Date fixedTime;
    /*类名*/
    private String className;
    //应用名称
    private String appName;
    //columns END

    public MonitorMqLog() {
    }

    public MonitorMqLog(
            Long id
    ) {
        this.id = id;
    }

    public void setId(Long value) {
        this.id = value;
    }

    public Long getId() {
        return this.id;
    }

    public void setSysCode(String value) {
        this.sysCode = value;
    }

    public String getSysCode() {
        return this.sysCode;
    }

    public void setExchange(String value) {
        this.exchange = value;
    }

    public String getExchange() {
        return this.exchange;
    }

    public void setRoutingKey(String value) {
        this.routingKey = value;
    }

    public String getRoutingKey() {
        return this.routingKey;
    }

    public void setMsgId(String value) {
        this.msgId = value;
    }

    public String getMsgId() {
        return this.msgId;
    }

    public void setRabbitAddress(String value) {
        this.rabbitAddress = value;
    }

    public String getRabbitAddress() {
        return this.rabbitAddress;
    }

    public void setRabbitPort(Integer value) {
        this.rabbitPort = value;
    }

    public Integer getRabbitPort() {
        return this.rabbitPort;
    }

    public void setLocalAddress(String value) {
        this.localAddress = value;
    }

    public String getLocalAddress() {
        return this.localAddress;
    }

    public void setMsgException(String value) {
        this.msgException = value;
    }

    public String getMsgException() {
        return this.msgException;
    }

    public void setRequestId(String value) {
        this.requestId = value;
    }

    public String getRequestId() {
        return this.requestId;
    }

    public void setMsgType(String value) {
        this.msgType = value;
    }

    public String getMsgType() {
        return this.msgType;
    }

    public void setMsgText(String value) {
        this.msgText = value;
    }

    public String getMsgText() {
        return this.msgText;
    }

    public void setMsgObject(byte[] value) {
        this.msgObject = value;
    }

    public byte[] getMsgObject() {
        return this.msgObject;
    }

    public void setLogStatus(String value) {
        this.logStatus = value;
    }

    public String getLogStatus() {
        return this.logStatus;
    }

    public String getCreateTimeString() {
        return DateConvertUtils.format(getCreateTime(), FORMAT_CREATE_TIME);
    }

    public void setCreateTimeString(String value) {
        setCreateTime(DateConvertUtils.parse(value, FORMAT_CREATE_TIME, java.util.Date.class));
    }

    public void setCreateTime(java.util.Date value) {
        this.createTime = value;
    }

    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    public String getFixedTimeString() {
        return DateConvertUtils.format(getFixedTime(), FORMAT_FIXED_TIME);
    }

    public void setFixedTimeString(String value) {
        setFixedTime(DateConvertUtils.parse(value, FORMAT_FIXED_TIME, java.util.Date.class));
    }

    public void setFixedTime(java.util.Date value) {
        this.fixedTime = value;
    }

    public java.util.Date getFixedTime() {
        return this.fixedTime;
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
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("Id", getId())
                .append("SysCode", getSysCode())
                .append("AppName", getAppName())
                .append("Exchange", getExchange())
                .append("RoutingKey", getRoutingKey())
                .append("MsgId", getMsgId())
                .append("RabbitAddress", getRabbitAddress())
                .append("RabbitPort", getRabbitPort())
                .append("LocalAddress", getLocalAddress())
                .append("MsgException", getMsgException())
                .append("RequestId", getRequestId())
                .append("MsgType", getMsgType())
                .append("MsgText", getMsgText())
                .append("MsgObject", getMsgObject())
                .append("className", getClassName())
                .append("LogStatus", getLogStatus())
                .append("CreateTime", getCreateTime())
                .append("FixedTime", getFixedTime())
                .toString();
    }

    public String toString(String separator) {
        StringBuffer sb = new StringBuffer();

        sb.append("ID:").append(id).append(separator);
        sb.append("系统代码:").append(sysCode).append(separator);
        sb.append("应用名称:").append(appName).append(separator);
        sb.append("MQ交换器:").append(exchange).append(separator);
        sb.append("MQ路由:").append(routingKey).append(separator);
        sb.append("消息ID:").append(msgId).append(separator);
        sb.append("MQ IP:").append(rabbitAddress).append(separator);
        sb.append("MQ端口:").append(rabbitPort).append(separator);
        sb.append("本地IP:").append(localAddress).append(separator);
        sb.append("消息异常:").append(msgException).append(separator);
        sb.append("请求流水:").append(requestId).append(separator);
        sb.append("消息类型:").append(msgType).append(separator);
        sb.append("字符串消息:").append(msgText).append(separator);
        sb.append("序列化消息:").append(msgObject).append(separator);
        sb.append("类名:").append(className).append(separator);
        sb.append("状态：0未解决、1已解决:").append(logStatus).append(separator);
        sb.append("创建时间:").append(getCreateTimeString()).append(separator);
        sb.append("处理时间:").append(getFixedTimeString()).append(separator);

        return sb.toString();
    }

    public int hashCode() {
        return new HashCodeBuilder()
                .append(getId())
                .toHashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof MonitorMqLog == false) return false;
        if (this == obj) return true;
        MonitorMqLog other = (MonitorMqLog) obj;
        return new EqualsBuilder()
                .append(getId(), other.getId())
                .isEquals();
    }

    public String getPkValue() {
        if (getId() != null) {
            return String.valueOf(getId());
        }

        return "";
    }
}


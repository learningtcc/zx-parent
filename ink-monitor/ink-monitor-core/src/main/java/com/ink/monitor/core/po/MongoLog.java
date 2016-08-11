package com.ink.monitor.core.po;

import java.io.Serializable;

/**
 * Created by aiyungui on 2016/4/29.
 */
public class MongoLog implements Serializable {

    private static final long serialVersionUID = 5454155825314635342L;

    private String _id;
    private String source;
    private String logLevel;
    private String logTime;
    private String logSeq;
    private String reqContext;
    private String resContext;
    private String userId;
    private String userName;
    private String reqIp;
    private String serverIp;
    private String requestId;
    private String requestUrl;
    private String sessionId;
    private String module;
    private String infoId;
    private String tradeId;
    private String threadName;
    private String logger;
    private String message;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getLogTime() {
        return logTime;
    }

    public void setLogTime(String logTime) {
        this.logTime = logTime;
    }

    public String getLogSeq() {
        return logSeq;
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public void setLogSeq(String logSeq) {
        this.logSeq = logSeq;
    }

    public String getReqContext() {
        return reqContext;
    }

    public void setReqContext(String reqContext) {
        this.reqContext = reqContext;
    }

    public String getResContext() {
        return resContext;
    }

    public void setResContext(String resContext) {
        this.resContext = resContext;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getReqIp() {
        return reqIp;
    }

    public void setReqIp(String reqIp) {
        this.reqIp = reqIp;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getInfoId() {
        return infoId;
    }

    public void setInfoId(String infoId) {
        this.infoId = infoId;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public String getLogger() {
        return logger;
    }

    public void setLogger(String logger) {
        this.logger = logger;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MongoLogQuery{" +
                "_id='" + _id + '\'' +
                ", source='" + source + '\'' +
                ", logTime='" + logTime + '\'' +
                ", logSeq='" + logSeq + '\'' +
                ", reqContext='" + reqContext + '\'' +
                ", resContext='" + resContext + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", reqIp='" + reqIp + '\'' +
                ", requestId='" + requestId + '\'' +
                ", requestUrl='" + requestUrl + '\'' +
                ", sessionId='" + sessionId + '\'' +
                ", module='" + module + '\'' +
                ", infoId='" + infoId + '\'' +
                ", tradeId='" + tradeId + '\'' +
                ", threadName='" + threadName + '\'' +
                ", logger='" + logger + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}

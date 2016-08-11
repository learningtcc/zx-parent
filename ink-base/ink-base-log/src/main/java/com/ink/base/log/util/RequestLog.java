package com.ink.base.log.util;

import java.io.Serializable;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.slf4j.MDC;

/**
 * 请求日志对象
 * @author zongtt
 * @since 2012年3月19日 16:16:28
 */
public class RequestLog implements Serializable{
	
	private static final long serialVersionUID = 7481944953040707597L;
	
	private static YinkerLogger loger = YinkerLogger.getLogger(RequestLog.class);
	
	private static final String localIp = IpUtils.getLocalAddress();

	private String userId;
	
	private String userName;
	
	private String reqId;
	//请求IP
	private String reqIp;
	//响应IP
	private String resIp;
	
	private String reqContext;
	
	private String reqUri;
	
	private String logSeq;
	
	private String sessionId ;
	
	/**
	 * 获取请求日志参数对象
	 * @return
	 */
	public static RequestLog getInstance(){
		
		RequestLog log = new RequestLog();
		log.userId = MDC.get(LogConst.USER_ID);
		log.reqId = MDC.get(LogConst.REQ_ID);
		if(StringUtils.isBlank(log.reqId)){
			log.reqId = StringUtils.remove(UUID.randomUUID().toString(),"-");
		}
		log.reqIp = localIp;
		//当前调用hessian服务的服务器ip，而非用户请求服务器的IP
		log.resIp = "";
		//调用hessia接口时，当前的应用作为请求应用
//		log.reqContext = MDC.get(LogConst.REQ_CONTEXT);
		log.reqContext = loger.getLogContext();
		log.reqUri = MDC.get(LogConst.REQ_URI);
		log.userName = MDC.get(LogConst.USER_NAME);
		log.logSeq = MDC.get(LogConst.LOG_SEQ);
		log.sessionId = MDC.get(LogConst.SESSION_ID) ;
		
		return log;
	}
	
	public String toString() {
		
		StringBuilder sb = new StringBuilder(250);
		sb.append("[userId=").append(userId).append(", ");
		sb.append("userName=").append(userName).append(", ");
		sb.append("reqId=").append(reqId).append(", ");
		sb.append("reqIp=").append(reqIp).append(", ");
		sb.append("resIp=").append("").append(", ");
		sb.append("reqContext=").append(reqContext).append(", ");
		sb.append("reqUri=").append(reqUri).append(", ");
		sb.append("logSeq=").append(logSeq).append(", ");
		sb.append("sessonId=").append(sessionId).append("]");
		
		return sb.toString();
	}
	
	public String encodeBase64(){
		
		StringBuilder sb = new StringBuilder(250);
		
		sb.append(userId).append("|");
		sb.append(userName).append("|");
		sb.append(reqId).append("|");
		sb.append(reqIp).append("|");
		sb.append(resIp).append("|");
		sb.append(reqContext).append("|");
		sb.append(reqUri).append("|");
		sb.append(logSeq).append("|");
		sb.append(sessionId) ;
		
		return Base64.encodeBase64URLSafeString(sb.toString().getBytes());
	}
	
	/**
	 * 获取模拟数据
	 * @return
	 */
	public static RequestLog getAnologData(){
		RequestLog log = new RequestLog();
		
		log.userId = "28b6031106454f3ab58a1038f291f1f9";
		log.userName = "guest";
		log.reqId = "d33a93a2fce642fb8e8016c603c34455";
		log.reqContext = "test";
		log.reqUri = "/user/list.do";
		
		return log;
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

	public String getReqId() {
		return reqId;
	}

	public void setReqId(String reqId) {
		this.reqId = reqId;
	}

	public String getReqIp() {
		return reqIp;
	}

	public void setReqIp(String reqIp) {
		this.reqIp = reqIp;
	}

	public String getResIp() {
		return resIp;
	}

	public void setResIp(String resIp) {
		this.resIp = resIp;
	}

	public String getReqContext() {
		return reqContext;
	}

	public void setReqContext(String reqContext) {
		this.reqContext = reqContext;
	}

	public String getReqUri() {
		return reqUri;
	}

	public void setReqUri(String reqUri) {
		this.reqUri = reqUri;
	}

	public String getLogSeq() {
		return logSeq;
	}

	public void setLogSeq(String logSeq) {
		this.logSeq = logSeq;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
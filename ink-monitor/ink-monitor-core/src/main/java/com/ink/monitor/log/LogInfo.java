package com.ink.monitor.log;

import java.util.Date;

import com.ink.base.utils.dateUtil.DateUtil;

public class LogInfo {

	private String logLevel;
	private Date logTime;
	private String userName;
	private String requestIp;
	private String requestId;
	private String serverIp;
	private String reqContext;
	private String systemSource;
	private String url;
	private String logClass;
	private String msg;
	private String moduleCode;
	private String tradeId;
	private String infoCode;
	
	public String getLogLevel() {
		return logLevel;
	}
	public void setLogLevel(String logLevel) {
		this.logLevel = logLevel;
	}
	public Date getLogTime() {
		return logTime;
	}
	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRequestIp() {
		return requestIp;
	}
	public void setRequestIp(String requestIp) {
		this.requestIp = requestIp;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getServerIp() {
		return serverIp;
	}
	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}
	public String getReqContext() {
		return reqContext;
	}
	public void setReqContext(String reqContext) {
		this.reqContext = reqContext;
	}
	public String getSystemSource() {
		return systemSource;
	}
	public void setSystemSource(String systemSource) {
		this.systemSource = systemSource;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getLogClass() {
		return logClass;
	}
	public void setLogClass(String logClass) {
		this.logClass = logClass;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getModuleCode() {
		return moduleCode;
	}
	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}
	public String getTradeId() {
		return tradeId;
	}
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}
	public String getInfoCode() {
		return infoCode;
	}
	public void setInfoCode(String infoCode) {
		this.infoCode = infoCode;
	}
	
	public String toString(String separator) {
		StringBuffer sb = new StringBuffer();

			sb.append("日志级别:").append(logLevel).append(separator);
			sb.append("日志时间:").append(DateUtil.formatToYYYYMMDDMMHHSS(logTime)).append(separator);
			sb.append("用户账号:").append(userName).append(separator);
			sb.append("用户IP:").append(requestIp).append(separator);
			sb.append("请求流水:").append(requestId).append(separator);
			sb.append("服务器IP:").append(serverIp).append(separator);
			sb.append("请求工程:").append(reqContext).append(separator);
			sb.append("请求URL:").append(url).append(separator);
			sb.append("系统代码:").append(systemSource).append(separator);
			sb.append("功能代码:").append(moduleCode).append(separator);
			sb.append("业务代码:").append(infoCode).append(separator);
			sb.append("报错类:").append(logClass).append(separator);
			sb.append("交易流水:").append(tradeId).append(separator);
			sb.append("报错消息:").append(msg).append(separator);
		
		return sb.toString();
	}
}

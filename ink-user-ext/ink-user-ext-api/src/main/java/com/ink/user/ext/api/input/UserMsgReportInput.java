package com.ink.user.ext.api.input;

/**
 * 用户短信状态回执对象
 * @author yangchen
 * @date 2016年6月27日 下午2:23:42
 */
public class UserMsgReportInput {
	private String msgId;
	
	private String mobile;
	
	private String retCode;
	
	private String submitTime;

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getRetCode() {
		return retCode;
	}

	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}

	public String getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserMsgReportInput [msgId=");
		builder.append(msgId);
		builder.append(", mobile=");
		builder.append(mobile);
		builder.append(", retCode=");
		builder.append(retCode);
		builder.append(", submitTime=");
		builder.append(submitTime);
		builder.append("]");
		return builder.toString();
	}

	
}

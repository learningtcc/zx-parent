package com.ink.msgcenter.mq;

import com.ink.msgcenter.cache.object.MerchantCache;
import com.ink.msgcenter.cache.object.SmsChnCache;

import java.io.Serializable;
import java.util.Date;

public class SmsDTO implements Serializable{


	private static final long serialVersionUID = -930004528371301588L;
	/**
	 * 商户号
	 */
	private String merctCode;
	
	/**
	 * 模板ID
	 */
	private String tempId;

	/**
	 * 业务单号
	 */
	private String bizId;
	
	private String paramJson;
	
	private String msgId;

	/*手机号*/
	private String mobile;

	/*发送时间*/
	private Date sendTime;

	/*商户信息*/
	private MerchantCache merchantCache;
	/*短信通道缓存*/
	private SmsChnCache smsChnCache;
	/*扩展信息*/
	private String extInfo;
	/*扩展号*/
	private String extNo;
	/**
	 * 业务系统的提交时间
	 */
	private Date submitTime;

	/*提交短信供应商时间*/
	private Date requestTime;

	/*短信类型 1普通短信 2营销短信*/
	private String sendType;
	/*通道代码*/
	private String chnCode;
	/*状态通知url*/
	private String reportUrl;
	/*上行通知url*/
	private String upUrl;

	public String getMerctCode() {
		return merctCode;
	}

	public void setMerctCode(String merctCode) {
		this.merctCode = merctCode;
	}

	public String getTempId() {
		return tempId;
	}

	public void setTempId(String tempId) {
		this.tempId = tempId;
	}

	public String getBizId() {
		return bizId;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
	}

	public String getParamJson() {
		return paramJson;
	}

	public void setParamJson(String paramJson) {
		this.paramJson = paramJson;
	}

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

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public MerchantCache getMerchantCache() {
		return merchantCache;
	}

	public void setMerchantCache(MerchantCache merchantCache) {
		this.merchantCache = merchantCache;
	}

	public SmsChnCache getSmsChnCache() {
		return smsChnCache;
	}

	public void setSmsChnCache(SmsChnCache smsChnCache) {
		this.smsChnCache = smsChnCache;
	}

	public Date getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}

	public String getExtInfo() {
		return extInfo;
	}

	public void setExtInfo(String extInfo) {
		this.extInfo = extInfo;
	}

	public String getExtNo() {
		return extNo;
	}

	public void setExtNo(String extNo) {
		this.extNo = extNo;
	}

	public String getSendType() {
		return sendType;
	}

	public void setSendType(String sendType) {
		this.sendType = sendType;
	}

	public Date getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}

	public String getChnCode() {
		return chnCode;
	}

	public void setChnCode(String chnCode) {
		this.chnCode = chnCode;
	}

	public String getReportUrl() {
		return reportUrl;
	}

	public void setReportUrl(String reportUrl) {
		this.reportUrl = reportUrl;
	}

	public String getUpUrl() {
		return upUrl;
	}

	public void setUpUrl(String upUrl) {
		this.upUrl = upUrl;
	}

	@Override
	public String toString() {
		return "SmsDTO{" +
				"merctCode='" + merctCode + '\'' +
				", tempId='" + tempId + '\'' +
				", bizId='" + bizId + '\'' +
				", paramJson='" + paramJson + '\'' +
				", msgId='" + msgId + '\'' +
				", mobile='" + mobile + '\'' +
				", sendTime=" + sendTime +
				", merchantCache=" + merchantCache +
				", smsChnCache=" + smsChnCache +
				", extInfo='" + extInfo + '\'' +
				", extNo='" + extNo + '\'' +
				", submitTime=" + submitTime +
				", requestTime=" + requestTime +
				", sendType='" + sendType + '\'' +
				", chnCode='" + chnCode + '\'' +
				", reportUrl='" + reportUrl + '\'' +
				", upUrl='" + upUrl + '\'' +
				'}';
	}
}

package com.ink.msgcenter.core.po;

import java.io.Serializable;
import java.util.Date;

import com.ink.base.BaseEntity;
import com.ink.base.util.DateConvertUtils;

/**
 * mogodb 日志bean
 * Created by aiyungui on 2016/5/24.
 */
public class SmsMongoLog extends BaseEntity implements Serializable{

    private static final long serialVersionUID = 1044759010499159319L;
    
    public static final String FORMAT_SUBMIT_TIME = DATE_TIME_FORMAT;
	public static final String FORMAT_SEND_TIME = DATE_TIME_FORMAT;
	public static final String FORMAT_FIX_TIME = DATE_TIME_FORMAT;
	public static final String FORMAT_REPORT_TIME = DATE_TIME_FORMAT;
	
    private String id;
    //merctId
    private Long merctId;
    //merctCode
    private String merctCode;
    //chnId
    private Long chnId;
    //chnCode
    private String chnCode;
    //tempId
    private Long tempId;
    //mobile
    private String mobile;
    //smsMsg
    private String smsMsg;
    //infoCode
    private String infoCode;
    //smsCode
    private String smsCode;
    //priority
    private Integer priority;
    //smsId
    private String smsId;
    //taskId
    private String taskId;
    //sendStatus
    private String sendStatus;
    //responseCode
    private String responseCode;
    //smsType
    private String smsType;
    //reportStatus
    private String reportStatus;
    //submitTime
    private Date submitTime;
    //sendTime
    private Date sendTime;
    //fixTime
    private Date fixTime;
    //reportTime
    private Date reportTime;
    private String upUrl;
    private String reportUrl;
    //sendException
    private String sendException;
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getMerctId() {
        return merctId;
    }

    public void setMerctId(Long merctId) {
        this.merctId = merctId;
    }

    public String getMerctCode() {
        return merctCode;
    }

    public void setMerctCode(String merctCode) {
        this.merctCode = merctCode;
    }

    public Long getChnId() {
        return chnId;
    }

    public void setChnId(Long chnId) {
        this.chnId = chnId;
    }

    public String getChnCode() {
        return chnCode;
    }

    public void setChnCode(String chnCode) {
        this.chnCode = chnCode;
    }

    public Long getTempId() {
        return tempId;
    }

    public void setTempId(Long tempId) {
        this.tempId = tempId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSmsMsg() {
        return smsMsg;
    }

    public void setSmsMsg(String smsMsg) {
        this.smsMsg = smsMsg;
    }

    public String getInfoCode() {
        return infoCode;
    }

    public void setInfoCode(String infoCode) {
        this.infoCode = infoCode;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getSmsId() {
        return smsId;
    }

    public void setSmsId(String smsId) {
        this.smsId = smsId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getSmsType() {
        return smsType;
    }

    public void setSmsType(String smsType) {
        this.smsType = smsType;
    }

    public String getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(String reportStatus) {
        this.reportStatus = reportStatus;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Date getFixTime() {
        return fixTime;
    }

    public void setFixTime(Date fixTime) {
        this.fixTime = fixTime;
    }

    public Date getReportTime() {
        return reportTime;
    }

    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }

    public String getSendException() {
        return sendException;
    }

    public void setSendException(String sendException) {
        this.sendException = sendException;
    }
    public String getSubmitTimeString() {
		return DateConvertUtils.format(getSubmitTime(), FORMAT_SUBMIT_TIME);
	}
	public void setSubmitTimeString(String value) {
		setSubmitTime(DateConvertUtils.parse(value, FORMAT_SUBMIT_TIME,java.util.Date.class));
	}
	public String getSendTimeString() {
		return DateConvertUtils.format(getSendTime(), FORMAT_SEND_TIME);
	}
	public void setSendTimeString(String value) {
		setSendTime(DateConvertUtils.parse(value, FORMAT_SEND_TIME,java.util.Date.class));
	}
	public String getFixTimeString() {
		return DateConvertUtils.format(getFixTime(), FORMAT_FIX_TIME);
	}
	public void setFixTimeString(String value) {
		setFixTime(DateConvertUtils.parse(value, FORMAT_FIX_TIME,java.util.Date.class));
	}
	public String getReportTimeString() {
		return DateConvertUtils.format(getReportTime(), FORMAT_REPORT_TIME);
	}
	public void setReportTimeString(String value) {
		setReportTime(DateConvertUtils.parse(value, FORMAT_REPORT_TIME,java.util.Date.class));
	}

    public String getUpUrl() {
        return upUrl;
    }

    public void setUpUrl(String upUrl) {
        this.upUrl = upUrl;
    }

    public String getReportUrl() {
        return reportUrl;
    }

    public void setReportUrl(String reportUrl) {
        this.reportUrl = reportUrl;
    }
}

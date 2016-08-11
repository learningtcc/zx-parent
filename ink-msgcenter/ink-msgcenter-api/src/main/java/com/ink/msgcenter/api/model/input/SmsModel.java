package com.ink.msgcenter.api.model.input;

import java.util.Date;

/**
 * 短信发送接口超类bean
 * Created by aiyungui on 2016/5/24.
 */
public class SmsModel extends MsgInput{

    private static final long serialVersionUID = 1952831239667263234L;
    /*模版ID*/
    private String tempId ;
    /*业务单号*/
    private String bizId;
    /*模版参数集合*/
    private String paramJson;
    /*发送时间*/
    private Date sendTime;
    /*通道代码*/
    private String chnCode;
    /*状态通知*/
    private String reportUrl;

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

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
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
}

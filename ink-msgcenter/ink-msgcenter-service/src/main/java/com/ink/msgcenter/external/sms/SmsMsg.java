package com.ink.msgcenter.external.sms;

import java.util.Date;

/**
 * 短信消息
 * Created by aiyungui on 2016/5/18.
 */
public class SmsMsg {

    /*手机号*/
    private String mobile;
    /*发送内容*/
    private String content;
    /*发送时间*/
    private Date sendTime;
    //通道参数
    private String chnParam;
    /*扩展子号*/
    private String extno;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getExtno() {
        return extno;
    }

    public void setExtno(String extno) {
        this.extno = extno;
    }

    public String getChnParam() {
        return chnParam;
    }

    public void setChnParam(String chnParam) {
        this.chnParam = chnParam;
    }
}

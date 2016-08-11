package com.ink.msgcenter.api.model.input;

/**
 * 短信发送扩展消息bean
 * Created by aiyungui on 2016/5/18.
 */
public class SmsExtInput extends SmsInput{

    private static final long serialVersionUID = 7121656310544115359L;
    /*扩展子号*/
    private String extNo ;
    /*扩展信息*/
    private String extInfo;
    /*上行通知*/
    private String upUrl;

    public String getUpUrl() {
        return upUrl;
    }

    public void setUpUrl(String upUrl) {
        this.upUrl = upUrl;
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
}

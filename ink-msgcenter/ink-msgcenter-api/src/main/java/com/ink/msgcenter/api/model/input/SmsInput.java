package com.ink.msgcenter.api.model.input;

/**
 * 短信发送消息bean
 * Created by aiyungui on 2016/5/18.
 */
public class SmsInput extends SmsModel{

    private static final long serialVersionUID = 3892988682066793869L;

    /*手机号，多个用“,”分割*/
    private String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}

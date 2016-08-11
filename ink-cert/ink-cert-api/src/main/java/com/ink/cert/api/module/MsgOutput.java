package com.ink.cert.api.module;

import java.io.Serializable;

/**
 * 结果信息返回module
 * Created by aiyungui on 2016/6/22.
 */
public class MsgOutput implements Serializable{

    private static final long serialVersionUID = 3533299771735265678L;
    //结果Code
    private String resultCode;
    //结果消息
    private String resultMsg;
    //报文消息
    private Object message;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MsgOutput{" +
                "resultCode='" + resultCode + '\'' +
                ", resultMsg='" + resultMsg + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}

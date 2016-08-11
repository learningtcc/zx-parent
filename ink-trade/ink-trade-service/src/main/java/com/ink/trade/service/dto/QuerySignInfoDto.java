package com.ink.trade.service.dto;

import java.io.Serializable;

/**
 * 查询签约信息dto Created by huohb on 2016/5/6.
 */
public class QuerySignInfoDto implements Serializable {
    private static final long serialVersionUID = -6720928202585324782L;

    private String merNo;// 商户号

    private String userId;// 用户号

    private String cardNo;// 卡号

    private String bankShort;// 银行英文缩写

    private String channelId;// 渠道号

    private String idNo;// 身份证号

    private String phoneNo;// 手机号

    private String realName;// 姓名

    private String cardType;// 卡类型

    private String idType;// 证件类型
    private String payType;// 支付类型

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getMerNo() {
        return merNo;
    }

    public void setMerNo(String merNo) {
        this.merNo = merNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getBankShort() {
        return bankShort;
    }

    public void setBankShort(String bankShort) {
        this.bankShort = bankShort;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

}

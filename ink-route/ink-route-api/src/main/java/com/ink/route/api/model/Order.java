package com.ink.route.api.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by YKDZ075 on 2017/5/21.
 */
public class Order {
    private String bankShort;// 银行Code
    private String cardId;// 卡ID
    private String mchId;// 商户ID
    private BigDecimal amount;//交易金额
    private Integer payType;//支付方式
    private Date orderTime;//订单时间
    private String bankCode;//银行简码

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getBankShort() {
        return bankShort;
    }

    public void setBankShort(String bankShort) {
        this.bankShort = bankShort;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

}
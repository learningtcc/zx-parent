package com.ink.balance.vo.input;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class TranParamVo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 2317207402395048457L;

    private int channelNo;

    private int payType;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date tradeBeginTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date trandeEndTime;

    private int dealType;

    private String platOrderId;

    private String bankFlow;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date checkBeginTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date checkEndTime;

    public TranParamVo() {
        super();
        // TODO Auto-generated constructor stub
    }

    public TranParamVo(int channelNo, int payType, Date tradeBeginTime, Date trandeEndTime, int dealType,
                       String platOrderId, String bankFlow, Date checkBeginTime, Date checkEndTime) {
        super();
        this.channelNo = channelNo;
        this.payType = payType;
        this.tradeBeginTime = tradeBeginTime;
        this.trandeEndTime = trandeEndTime;
        this.dealType = dealType;
        this.platOrderId = platOrderId;
        this.bankFlow = bankFlow;
        this.checkBeginTime = checkBeginTime;
        this.checkEndTime = checkEndTime;
    }

    public int getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(int channelNo) {
        this.channelNo = channelNo;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public Date getTradeBeginTime() {
        return tradeBeginTime;
    }

    public void setTradeBeginTime(Date tradeBeginTime) {
        this.tradeBeginTime = tradeBeginTime;
    }

    public Date getTrandeEndTime() {
        return trandeEndTime;
    }

    public void setTrandeEndTime(Date trandeEndTime) {
        this.trandeEndTime = trandeEndTime;
    }

    public int getDealType() {
        return dealType;
    }

    public void setDealType(int dealType) {
        this.dealType = dealType;
    }

    public String getPlatOrderId() {
        return platOrderId;
    }

    public void setPlatOrderId(String platOrderId) {
        this.platOrderId = platOrderId;
    }

    public String getBankFlow() {
        return bankFlow;
    }

    public void setBankFlow(String bankFlow) {
        this.bankFlow = bankFlow;
    }


    public Date getCheckBeginTime() {
        return checkBeginTime;
    }

    public void setCheckBeginTime(Date checkBeginTime) {
        this.checkBeginTime = checkBeginTime;
    }

    public Date getCheckEndTime() {
        return checkEndTime;
    }

    public void setCheckEndTime(Date checkEndTime) {
        this.checkEndTime = checkEndTime;
    }

    @Override
    public String toString() {
        return "TranParamVo [channelNo=" + channelNo + ", payType=" + payType + ", tradeBeginTime=" + tradeBeginTime
                + ", trandeEndTime=" + trandeEndTime + ", dealType=" + dealType + ", platOrderId=" + platOrderId
                + ", bankFlow=" + bankFlow + ", checkBeginTime=" + checkBeginTime + ", checkEndTime=" + checkEndTime
                + "]";
    }


}

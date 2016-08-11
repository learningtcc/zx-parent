package com.ink.balance.api.model.in;

import java.io.Serializable;
import java.util.Date;

/**
 * @author bo.chen
 * @Description 渠道数据查询条件参数
 * @date 2016年4月15日 上午10:31:11
 */
public class ChannelDataQueryParamInput implements Serializable {

    private static final long serialVersionUID = 7475220831224521325L;

    private String channelNo;

    private String merchantNo;

    private int checkStatus;

    private int adjustStatus;

    private Date tradeBeginTime;

    private Date tradeEndTime;

    private Date checkBeginTime;

    private Date checkEndTime;

    private int transStatus;

    private int resideFlag;

    /**
     * 平台订单号
     */
    private String platformOrderNo;

    /**
     * 交易流水号
     */
    private String transNo;


    public String getPlatformOrderNo() {
        return platformOrderNo;
    }

    public void setPlatformOrderNo(String platformOrderNo) {
        this.platformOrderNo = platformOrderNo;
    }

    public String getTransNo() {
        return transNo;
    }

    public void setTransNo(String transNo) {
        this.transNo = transNo;
    }

    public int getResideFlag() {
        return resideFlag;
    }

    public void setResideFlag(int resideFlag) {
        this.resideFlag = resideFlag;
    }

    public int getTransStatus() {
        return transStatus;
    }

    public void setTransStatus(int transStatus) {
        this.transStatus = transStatus;
    }

    public String getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo;
    }

    public int getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(int checkStatus) {
        this.checkStatus = checkStatus;
    }

    public int getAdjustStatus() {
        return adjustStatus;
    }

    public void setAdjustStatus(int adjustStatus) {
        this.adjustStatus = adjustStatus;
    }

    public Date getTradeBeginTime() {
        return tradeBeginTime;
    }

    public void setTradeBeginTime(Date tradeBeginTime) {
        this.tradeBeginTime = tradeBeginTime;
    }

    public Date getTradeEndTime() {
        return tradeEndTime;
    }

    public void setTradeEndTime(Date tradeEndTime) {
        this.tradeEndTime = tradeEndTime;
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

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    @Override
    public String toString() {
        return "ChannelDataQueryParamInput{" +
                "channelNo='" + channelNo + '\'' +
                ", merchantNo='" + merchantNo + '\'' +
                ", checkStatus=" + checkStatus +
                ", adjustStatus=" + adjustStatus +
                ", tradeBeginTime=" + tradeBeginTime +
                ", tradeEndTime=" + tradeEndTime +
                ", checkBeginTime=" + checkBeginTime +
                ", checkEndTime=" + checkEndTime +
                ", transStatus=" + transStatus +
                ", resideFlag=" + resideFlag +
                ", platformOrderNo='" + platformOrderNo + '\'' +
                ", transNo='" + transNo + '\'' +
                '}';
    }
}

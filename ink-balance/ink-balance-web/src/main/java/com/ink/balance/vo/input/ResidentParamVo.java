package com.ink.balance.vo.input;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ResidentParamVo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 2047986181047087125L;

    private String channelNo;

    private String merchantNo;

    private String  channelMerchantNo;

    private int payType;

    private int residentType;

    private int redidentTime;

    private int checkStatus = -1;

    private int resideFlag = -1;

    private int payStatus;

    private int transStatus;

    /**
     * 平台订单号
     */
    private String platformOrderNo;

    /**
     * 交易流水号
     */
    private String transNo;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date tradeBeginTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date tradeEndTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date checkBeginTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date checkEndTime;

    public String getChannelMerchantNo() {
        return channelMerchantNo;
    }

    public void setChannelMerchantNo(String channelMerchantNo) {
        this.channelMerchantNo = channelMerchantNo;
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

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
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

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public int getResidentType() {
        return residentType;
    }

    public void setResidentType(int residentType) {
        this.residentType = residentType;
    }

    public int getRedidentTime() {
        return redidentTime;
    }

    public void setRedidentTime(int redidentTime) {
        this.redidentTime = redidentTime;
    }


    public int getCheckStatus() {
        return checkStatus;
    }


    public void setCheckStatus(int checkStatus) {
        this.checkStatus = checkStatus;
    }


    public int getPayStatus() {
        return payStatus;
    }


    public void setPayStatus(int payStatus) {
        this.payStatus = payStatus;
    }

    @Override
    public String toString() {
        return "ResidentParamVo{" +
                "channelNo='" + channelNo + '\'' +
                ", merchantNo='" + merchantNo + '\'' +
                ", channelMerchantNo='" + channelMerchantNo + '\'' +
                ", payType=" + payType +
                ", residentType=" + residentType +
                ", redidentTime=" + redidentTime +
                ", checkStatus=" + checkStatus +
                ", resideFlag=" + resideFlag +
                ", payStatus=" + payStatus +
                ", transStatus=" + transStatus +
                ", platformOrderNo='" + platformOrderNo + '\'' +
                ", transNo='" + transNo + '\'' +
                ", tradeBeginTime=" + tradeBeginTime +
                ", tradeEndTime=" + tradeEndTime +
                ", checkBeginTime=" + checkBeginTime +
                ", checkEndTime=" + checkEndTime +
                '}';
    }
}

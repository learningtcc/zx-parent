package com.ink.balance.api.model.in;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author bo.chen
 * @Description 平台数据查询条件参数
 * @date 2016年4月18日 上午10:31:11
 */
public class PlatformDataMqParamInput implements Serializable {

    private static final long serialVersionUID = 7475220831224521325L;

    /**
     * 支付渠道（1：CMBC）
     */
    private String channelNo;

    /**
     * 支付金额
     */
    private String amt;
    /**
     * 到账金额
     */
    private String arrivedAmt;


    /**
     * 支付生成时间
     */
    private String payTime;

    /**
     * 到账时间
     */
    private String arrivedTime;

    /**
     * 支付渠道商户号
     */
    private String channelMerchantNo;

    /**
     * 平台订单号
     */
    private String platformOrderNo;

    /**
     * 交易流水号
     */
    private String transNo;

    private Date tradeBeginTime;

    private Date tradeEndTime;

    private Date checkBeginTime;

    private Date checkEndTime;

    /**
     * 交易状态 1、待支付 2、支付成功 3、支付失败
     */
    private String payStatus;

    /**
     * 支付方式（1：代收、2：代付、3：网银、4：快捷）
     */
    private String payMethod;

    /**
     * 交易状态 0：初始 1：未匹配 2：已匹配 3、差错
     */
    private Integer checkStatus;

    /**
     * 驻留标识(0:非驻留，1：驻留)
     */
    private Integer resideFlag;

    public String getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo;
    }

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getChannelMerchantNo() {
        return channelMerchantNo;
    }

    public void setChannelMerchantNo(String channelMerchantNo) {
        this.channelMerchantNo = channelMerchantNo;
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

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public Integer getResideFlag() {
        return resideFlag;
    }

    public void setResideFlag(Integer resideFlag) {
        this.resideFlag = resideFlag;
    }

    public String getArrivedAmt() {
        return arrivedAmt;
    }

    public void setArrivedAmt(String arrivedAmt) {
        this.arrivedAmt = arrivedAmt;
    }

    public String getArrivedTime() {
        return arrivedTime;
    }

    public void setArrivedTime(String arrivedTime) {
        this.arrivedTime = arrivedTime;
    }

    @Override
    public String toString() {
        return "PlatformDataMqParamInput{" +
                "channelNo='" + channelNo + '\'' +
                ", amt='" + amt + '\'' +
                ", arrivedAmt='" + arrivedAmt + '\'' +
                ", payTime='" + payTime + '\'' +
                ", arrivedTime='" + arrivedTime + '\'' +
                ", channelMerchantNo='" + channelMerchantNo + '\'' +
                ", platformOrderNo='" + platformOrderNo + '\'' +
                ", transNo='" + transNo + '\'' +
                ", tradeBeginTime=" + tradeBeginTime +
                ", tradeEndTime=" + tradeEndTime +
                ", checkBeginTime=" + checkBeginTime +
                ", checkEndTime=" + checkEndTime +
                ", payStatus='" + payStatus + '\'' +
                ", payMethod='" + payMethod + '\'' +
                ", checkStatus=" + checkStatus +
                ", resideFlag=" + resideFlag +
                '}';
    }
}
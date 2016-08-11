package com.ink.balance.vo.output;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TranMainVo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 657253062029728353L;

    private Long id;

    private String channelName;

    private String payType;

    private String platOrderId;

    private String bankFlow;

    private Date trandeTime;

    private Date checkTime;

    private String trandeStatus;

    private BigDecimal trandeMoney;

    private String exceptionType;

    private String dealStatus;

    private Date dealTime;

    private String optName;

    private String remark;


    public TranMainVo() {
        super();
        // TODO Auto-generated constructor stub
    }

    public TranMainVo(Long id, String channelName, String payType, String platOrderId, String bankFlow,
                      Date trandeTime, Date checkTime, String trandeStatus, BigDecimal trandeMoney, String exceptionType,
                      String dealStatus, Date dealTime, String optName, String remark) {
        super();
        this.id = id;
        this.channelName = channelName;
        this.payType = payType;
        this.platOrderId = platOrderId;
        this.bankFlow = bankFlow;
        this.trandeTime = trandeTime;
        this.checkTime = checkTime;
        this.trandeStatus = trandeStatus;
        this.trandeMoney = trandeMoney;
        this.exceptionType = exceptionType;
        this.dealStatus = dealStatus;
        this.dealTime = dealTime;
        this.optName = optName;
        this.remark = remark;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }


    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
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

    public Date getTrandeTime() {
        return trandeTime;
    }

    public void setTrandeTime(Date trandeTime) {
        this.trandeTime = trandeTime;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public String getTrandeStatus() {
        return trandeStatus;
    }

    public void setTrandeStatus(String trandeStatus) {
        this.trandeStatus = trandeStatus;
    }

    public BigDecimal getTrandeMoney() {
        return trandeMoney;
    }

    public void setTrandeMoney(BigDecimal trandeMoney) {
        this.trandeMoney = trandeMoney;
    }

    public String getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(String exceptionType) {
        this.exceptionType = exceptionType;
    }

    public String getDealStatus() {
        return dealStatus;
    }

    public void setDealStatus(String dealStatus) {
        this.dealStatus = dealStatus;
    }

    public Date getDealTime() {
        return dealTime;
    }

    public void setDealTime(Date dealTime) {
        this.dealTime = dealTime;
    }

    public String getOptName() {
        return optName;
    }

    public void setOptName(String optName) {
        this.optName = optName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "TranMainVo [id=" + id + ", channelName=" + channelName + ", payType=" + payType + ", platOrderId="
                + platOrderId + ", bankFlow=" + bankFlow + ", trandeTime=" + trandeTime + ", checkTime=" + checkTime
                + ", trandeStatus=" + trandeStatus + ", trandeMoney=" + trandeMoney + ", exceptionType=" + exceptionType
                + ", dealStatus=" + dealStatus + ", dealTime=" + dealTime + ", optName=" + optName + ", remark="
                + remark + "]";
    }


}

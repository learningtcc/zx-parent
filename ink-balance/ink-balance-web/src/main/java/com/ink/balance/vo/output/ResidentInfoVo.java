package com.ink.balance.vo.output;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ResidentInfoVo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1941942890549693385L;

    private Long id;

    private String channelName;

    private String payType;

    private String platOrderId;

    private String bankFlow;

    private String residentType;

    private Date trandeTime;

    private Date residentTime;

    private String trandeStatus;

    private BigDecimal trandeMoney;

    public ResidentInfoVo() {
        super();
        // TODO Auto-generated constructor stub
    }

    public ResidentInfoVo(Long id, String channelName, String payType, String platOrderId, String bankFlow,
                          String residentType, Date trandeTime, Date residentTime, String trandeStatus, BigDecimal trandeMoney) {
        super();
        this.id = id;
        this.channelName = channelName;
        this.payType = payType;
        this.platOrderId = platOrderId;
        this.bankFlow = bankFlow;
        this.residentType = residentType;
        this.trandeTime = trandeTime;
        this.residentTime = residentTime;
        this.trandeStatus = trandeStatus;
        this.trandeMoney = trandeMoney;
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

    public String getResidentType() {
        return residentType;
    }

    public void setResidentType(String residentType) {
        this.residentType = residentType;
    }

    public Date getTrandeTime() {
        return trandeTime;
    }

    public void setTrandeTime(Date trandeTime) {
        this.trandeTime = trandeTime;
    }

    public Date getResidentTime() {
        return residentTime;
    }

    public void setResidentTime(Date residentTime) {
        this.residentTime = residentTime;
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

    @Override
    public String toString() {
        return "ResidentInfoVo [id=" + id + ", channelName=" + channelName + ", payType=" + payType + ", platOrderId="
                + platOrderId + ", bankFlow=" + bankFlow + ", residentType=" + residentType + ", trandeTime="
                + trandeTime + ", residentTime=" + residentTime + ", trandeStatus=" + trandeStatus + ", trandeMoney="
                + trandeMoney + "]";
    }


}

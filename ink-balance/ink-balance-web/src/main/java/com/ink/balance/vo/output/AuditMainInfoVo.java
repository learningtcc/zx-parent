package com.ink.balance.vo.output;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AuditMainInfoVo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 7179009563522437456L;

    private String channelName;

    private String payType;

    private String platOrderId;

    private String bankFlow;

    private Date trandeTime;

    private Date checkTime;

    private String trandeStatus;

    private BigDecimal trandeMoney;

    private String exceptionType;

    private Date applyTime;

    private String optName;

    private String remark;

    private String auditStatus;

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

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
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

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public AuditMainInfoVo() {
        super();
        // TODO Auto-generated constructor stub
    }

    public AuditMainInfoVo(String channelName, String payType, String platOrderId, String bankFlow, Date trandeTime,
                           Date checkTime, String trandeStatus, BigDecimal trandeMoney, String exceptionType, Date applyTime,
                           String optName, String remark, String auditStatus) {
        super();
        this.channelName = channelName;
        this.payType = payType;
        this.platOrderId = platOrderId;
        this.bankFlow = bankFlow;
        this.trandeTime = trandeTime;
        this.checkTime = checkTime;
        this.trandeStatus = trandeStatus;
        this.trandeMoney = trandeMoney;
        this.exceptionType = exceptionType;
        this.applyTime = applyTime;
        this.optName = optName;
        this.remark = remark;
        this.auditStatus = auditStatus;
    }

    @Override
    public String toString() {
        return "AuditMainInfoVo [channelName=" + channelName + ", payType=" + payType + ", platOrderId=" + platOrderId
                + ", bankFlow=" + bankFlow + ", trandeTime=" + trandeTime + ", checkTime=" + checkTime
                + ", trandeStatus=" + trandeStatus + ", trandeMoney=" + trandeMoney + ", exceptionType=" + exceptionType
                + ", applyTime=" + applyTime + ", optName=" + optName + ", remark=" + remark + ", auditStatus="
                + auditStatus + "]";
    }


}

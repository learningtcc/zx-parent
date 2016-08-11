package com.ink.balance.vo.input;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class LogParamVo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 8742585209389912341L;

    private String platOrderId;

    private String bankFlow;

    private int optType;

    private int optId;

    private int auditId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date applyBeginTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date applyEndTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date auditBeginTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date auditEndTime;

    private int auditStatus;

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

    public int getOptType() {
        return optType;
    }

    public void setOptType(int optType) {
        this.optType = optType;
    }

    public int getOptId() {
        return optId;
    }

    public void setOptId(int optId) {
        this.optId = optId;
    }

    public int getAuditId() {
        return auditId;
    }

    public void setAuditId(int auditId) {
        this.auditId = auditId;
    }

    public Date getApplyBeginTime() {
        return applyBeginTime;
    }

    public void setApplyBeginTime(Date applyBeginTime) {
        this.applyBeginTime = applyBeginTime;
    }

    public Date getApplyEndTime() {
        return applyEndTime;
    }

    public void setApplyEndTime(Date applyEndTime) {
        this.applyEndTime = applyEndTime;
    }

    public Date getAuditBeginTime() {
        return auditBeginTime;
    }

    public void setAuditBeginTime(Date auditBeginTime) {
        this.auditBeginTime = auditBeginTime;
    }

    public Date getAuditEndTime() {
        return auditEndTime;
    }

    public void setAuditEndTime(Date auditEndTime) {
        this.auditEndTime = auditEndTime;
    }

    public int getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(int auditStatus) {
        this.auditStatus = auditStatus;
    }

    public LogParamVo() {
        super();
        // TODO Auto-generated constructor stub
    }

    public LogParamVo(String platOrderId, String bankFlow, int optType, int optId, int auditId, Date applyBeginTime,
                      Date applyEndTime, Date auditBeginTime, Date auditEndTime, int auditStatus) {
        super();
        this.platOrderId = platOrderId;
        this.bankFlow = bankFlow;
        this.optType = optType;
        this.optId = optId;
        this.auditId = auditId;
        this.applyBeginTime = applyBeginTime;
        this.applyEndTime = applyEndTime;
        this.auditBeginTime = auditBeginTime;
        this.auditEndTime = auditEndTime;
        this.auditStatus = auditStatus;
    }

    @Override
    public String toString() {
        return "LogParamVo [platOrderId=" + platOrderId + ", bankFlow=" + bankFlow + ", optType=" + optType + ", optId="
                + optId + ", auditId=" + auditId + ", applyBeginTime=" + applyBeginTime + ", applyEndTime="
                + applyEndTime + ", auditBeginTime=" + auditBeginTime + ", auditEndTime=" + auditEndTime
                + ", auditStatus=" + auditStatus + "]";
    }


}

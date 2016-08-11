package com.ink.balance.vo.input;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class AuditParamVo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -2870841684790706503L;

    private String platOrderId;//

    private String bankFlow;//

    private int changeType;//

    private int optId;//

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date applyBeginTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date applyEndTime;

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

    public int getChangeType() {
        return changeType;
    }

    public void setChangeType(int changeType) {
        this.changeType = changeType;
    }

    public int getOptId() {
        return optId;
    }

    public void setOptId(int optId) {
        this.optId = optId;
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

    public int getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(int auditStatus) {
        this.auditStatus = auditStatus;
    }

    public AuditParamVo() {
        super();
        // TODO Auto-generated constructor stub
    }

    public AuditParamVo(String platOrderId, String bankFlow, int changeType, int optId, Date applyBeginTime,
                        Date applyEndTime, int auditStatus) {
        super();
        this.platOrderId = platOrderId;
        this.bankFlow = bankFlow;
        this.changeType = changeType;
        this.optId = optId;
        this.applyBeginTime = applyBeginTime;
        this.applyEndTime = applyEndTime;
        this.auditStatus = auditStatus;
    }

    @Override
    public String toString() {
        return "AuditParamVo [platOrderId=" + platOrderId + ", bankFlow=" + bankFlow + ", changeType=" + changeType
                + ", optId=" + optId + ", applyBeginTime=" + applyBeginTime + ", applyEndTime=" + applyEndTime
                + ", auditStatus=" + auditStatus + "]";
    }


}

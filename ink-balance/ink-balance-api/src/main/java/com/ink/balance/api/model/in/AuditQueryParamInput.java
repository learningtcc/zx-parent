package com.ink.balance.api.model.in;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xuguoqi
 * @Description 审核分页查询条件参数
 * @date 2016年4月7日 上午10:44:40
 */
public class AuditQueryParamInput implements Serializable {

    private static final long serialVersionUID = -5907729037538810590L;

    private String platOrderId;

    private String bankFlow;

    private int changeType;

    private int optId;

    private Date applyBeginTime;

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

    public AuditQueryParamInput() {
        super();
        // TODO Auto-generated constructor stub
    }

    public AuditQueryParamInput(String platOrderId, String bankFlow, int changeType, int optId, Date applyBeginTime,
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
        return "AuditQueryParamInput [platOrderId=" + platOrderId + ", bankFlow=" + bankFlow + ", changeType="
                + changeType + ", optId=" + optId + ", applyBeginTime=" + applyBeginTime + ", applyEndTime="
                + applyEndTime + ", auditStatus=" + auditStatus + "]";
    }


}

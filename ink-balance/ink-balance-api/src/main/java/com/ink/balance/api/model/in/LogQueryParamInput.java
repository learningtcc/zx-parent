package com.ink.balance.api.model.in;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description 日志分页查询参数条件
 * @author xuguoqi
 * @date 2016年4月7日 上午10:47:21
 */
public class LogQueryParamInput implements Serializable {

    private static final long serialVersionUID = 6083544539044473963L;

    private String platOrderId;

    private String bankFlow;

    private int optType;

    private int optId;

    private int auditId;

    private Date applyBeginTime;

    private Date applyEndTime;

    private Date auditBeginTime;

    private Date auditEndTime;

    private int auditStatus;

    public LogQueryParamInput() {
        super();
    }

    public LogQueryParamInput(String platOrderId, String bankFlow, int optType, int optId, int auditId,
                              Date applyBeginTime, Date applyEndTime, Date auditBeginTime, Date auditEndTime, int auditStatus) {
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
        return "LogQueryParamInput [platOrderId=" + platOrderId + ", bankFlow=" + bankFlow + ", optType=" + optType
                + ", optId=" + optId + ", auditId=" + auditId + ", applyBeginTime=" + applyBeginTime + ", applyEndTime="
                + applyEndTime + ", auditBeginTime=" + auditBeginTime + ", auditEndTime=" + auditEndTime
                + ", auditStatus=" + auditStatus + "]";
    }


}

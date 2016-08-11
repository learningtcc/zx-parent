package com.ink.channel.core.minsheng.request;

public class CmbcAccountVerifyReqBean extends CmbcBodyBean{

    /**
     * 
     */
    private static final long serialVersionUID = -1241979751831074766L;
    private String tranCode;
    private String tranState;
    private String startDate;
    private String endDate;
    public String getTranCode() {
        return tranCode;
    }
    public void setTranCode(String tranCode) {
        this.tranCode = tranCode;
    }
    public String getTranState() {
        return tranState;
    }
    public void setTranState(String tranState) {
        this.tranState = tranState;
    }
    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    
}

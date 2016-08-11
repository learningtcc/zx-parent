package com.ink.balance.api.model.in;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xuguoqi
 * @Description 主对账查询条件参数
 * @date 2016年4月7日 上午10:31:11
 */
public class CheckQueryParamInput implements Serializable {

    private static final long serialVersionUID = 7475220831224521325L;

    private Long id;

    private String channelNo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private int checkStatus;

    private int adjustStatus;

    private Date tradeBeginTime;

    private Date tradeEndTime;

    private Date checkBeginTime;

    private Date checkEndTime;

    private String ChannelMerchantNo;

    public String getChannelMerchantNo() {
        return ChannelMerchantNo;
    }

    public void setChannelMerchantNo(String channelMerchantNo) {
        ChannelMerchantNo = channelMerchantNo;
    }

    public String getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo;
    }

    public int getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(int checkStatus) {
        this.checkStatus = checkStatus;
    }

    public int getAdjustStatus() {
        return adjustStatus;
    }

    public void setAdjustStatus(int adjustStatus) {
        this.adjustStatus = adjustStatus;
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

    @Override
    public String toString() {
        return "CheckQueryParamInput{" +
                "id=" + id +
                ", channelNo='" + channelNo + '\'' +
                ", checkStatus=" + checkStatus +
                ", adjustStatus=" + adjustStatus +
                ", tradeBeginTime=" + tradeBeginTime +
                ", tradeEndTime=" + tradeEndTime +
                ", checkBeginTime=" + checkBeginTime +
                ", checkEndTime=" + checkEndTime +
                ", ChannelMerchantNo='" + ChannelMerchantNo + '\'' +
                '}';
    }
}

/**
 * yinker.com Copyright (c) 2016-2025 All Rights Reserved.
 *
 * @Description TODO
 * @author xuguoqi
 * @date 2016年4月7日 下午3:54:46
 */
package com.ink.balance.core.query;

import java.io.Serializable;
import java.util.Date;

import com.ink.base.BaseQuery;

/**
 * @author xuguoqi
 * @Description 总对账数据查询参数
 * @date 2016年4月7日 下午3:54:46
 */
public class CheckChannelQuery extends BaseQuery implements Serializable {

    private static final long serialVersionUID = -4223042774608130459L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 渠道编号
     */
    private String channelNo;

    /**
     * 对账结果状态
     */
    private int checkStatus;

    /**
     * 处理状态
     */
    private int adjustStatus;

    /**
     * 交易开始时间
     */
    private Date tradeBeginTime;

    /**
     * 支付渠道商户号
     */
    private String channelMerchantNo;

    public String getChannelMerchantNo() {
        return channelMerchantNo;
    }

    public void setChannelMerchantNo(String channelMerchantNo) {
        this.channelMerchantNo = channelMerchantNo;
    }

    /**
     * 交易结束时间
     */
    private Date tradeEndTime;

    /**
     * 对账开始时间
     */
    private Date checkBeginTime;

    /**
     * 对账结束时间
     */
    private Date checkEndTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return "CheckChannelQuery{" +
                "id=" + id +
                ", channelNo='" + channelNo + '\'' +
                ", checkStatus=" + checkStatus +
                ", adjustStatus=" + adjustStatus +
                ", tradeBeginTime=" + tradeBeginTime +
                ", channelMerchantNo='" + channelMerchantNo + '\'' +
                ", tradeEndTime=" + tradeEndTime +
                ", checkBeginTime=" + checkBeginTime +
                ", checkEndTime=" + checkEndTime +
                '}';
    }
}

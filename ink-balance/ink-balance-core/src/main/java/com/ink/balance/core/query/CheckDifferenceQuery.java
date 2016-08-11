/**
 * yinker.com Copyright (c) 2016-2025 All Rights Reserved.
 *
 * @Description TODO
 * @author xuguoqi
 * @date 2016年4月11日 上午9:59:25
 */
package com.ink.balance.core.query;

import java.io.Serializable;
import java.util.Date;

import com.ink.base.BaseQuery;

/**
 * @author xuguoqi
 * @Description 差异数据查询参数
 * @date 2016年4月11日 上午9:59:25
 */
public class CheckDifferenceQuery extends BaseQuery implements Serializable {

    private static final long serialVersionUID = -7373497025358777111L;

    /**
     * 渠道编号
     */
    private String channelNo;

    /**
     * 支付渠道商户号
     */
    private String channelMerchantNo;

    /**
     * 差错类型(1:渠道单边，2平台单边 3、金额差错 4、状态差错)
     */
    private Integer differenceType;

    /**
     * 差错来源（1：渠道  2：平台）
     */
    private Integer differenceSource;

    /**
     * 平台订单号
     */
    private String platformOrderNo;

    /**
     * 处理状态,0待处理1处理完成 2、挂起
     */
    private Integer handleStatus;

    /**
     * 对账汇总批次号
     */
    private Long refMainrecordId;

    /**
     * 开始时间
     */
    private Date createBeginDate;

    /**
     * 结束时间
     */
    private Date createEndDate;

    public String getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo;
    }

    public String getChannelMerchantNo() {
        return channelMerchantNo;
    }

    public void setChannelMerchantNo(String channelMerchantNo) {
        this.channelMerchantNo = channelMerchantNo;
    }

    public Integer getDifferenceType() {
        return differenceType;
    }

    public void setDifferenceType(Integer differenceType) {
        this.differenceType = differenceType;
    }

    public Integer getDifferenceSource() {
        return differenceSource;
    }

    public void setDifferenceSource(Integer differenceSource) {
        this.differenceSource = differenceSource;
    }

    public String getPlatformOrderNo() {
        return platformOrderNo;
    }

    public void setPlatformOrderNo(String platformOrderNo) {
        this.platformOrderNo = platformOrderNo;
    }

    public Integer getHandleStatus() {
        return handleStatus;
    }

    public void setHandleStatus(Integer handleStatus) {
        this.handleStatus = handleStatus;
    }

    public Date getCreateBeginDate() {
        return createBeginDate;
    }

    public void setCreateBeginDate(Date createBeginDate) {
        this.createBeginDate = createBeginDate;
    }

    public Date getCreateEndDate() {
        return createEndDate;
    }

    public void setCreateEndDate(Date createEndDate) {
        this.createEndDate = createEndDate;
    }


    public Long getRefMainrecordId() {
        return refMainrecordId;
    }

    public void setRefMainrecordId(Long refMainrecordId) {
        this.refMainrecordId = refMainrecordId;
    }

    @Override
    public String toString() {
        return "CheckDifferenceQuery{" +
                "channelNo='" + channelNo + '\'' +
                ", channelMerchantNo='" + channelMerchantNo + '\'' +
                ", differenceType=" + differenceType +
                ", differenceSource=" + differenceSource +
                ", platformOrderNo='" + platformOrderNo + '\'' +
                ", handleStatus=" + handleStatus +
                ", refMainrecordId=" + refMainrecordId +
                ", createBeginDate=" + createBeginDate +
                ", createEndDate=" + createEndDate +
                '}';
    }
}

/**
 * yinker.com Copyright (c) 2016-2025 All Rights Reserved.
 *
 * @Description TODO
 * @author xuguoqi
 * @date 2016年4月14日 下午7:02:26
 */
package com.ink.balance.vo.input;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description 差异分页查询
 * @author xuguoqi
 * @date 2016年4月14日 下午7:02:26
 */
public class DifferenceParamVo implements Serializable {

    private static final long serialVersionUID = 5880257516381917852L;


    /** 渠道编号 */
    private String channelNo;

    /** 支付渠道商户号 */
    private String channelMerchantNo;

    /** 差错类型(1:渠道单边，2平台单边 3、金额差错 4、状态差错) */
    private Integer differenceType = -1;

    /** 差错来源（1：渠道  2：平台） */
    private Integer differenceSource = -1;
    /**  对账汇总批次号*/
    private Long refMainrecordId;

    /** 平台订单号 */
    private String platformOrderNo;

    /** 处理状态,0待处理1处理完成 2、挂起 */
    private Integer handleStatus = -1;


    /** 开始时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createBeginDate;

    /** 结束时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
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

    public DifferenceParamVo() {
        super();
    }

    public Long getRefMainrecordId() {
        return refMainrecordId;
    }

    public void setRefMainrecordId(Long refMainrecordId) {
        this.refMainrecordId = refMainrecordId;
    }


    @Override
    public String toString() {
        return "DifferenceParamVo{" +
                "channelNo='" + channelNo + '\'' +
                ", channelMerchantNo='" + channelMerchantNo + '\'' +
                ", differenceType=" + differenceType +
                ", differenceSource=" + differenceSource +
                ", refMainrecordId=" + refMainrecordId +
                ", platformOrderNo='" + platformOrderNo + '\'' +
                ", handleStatus=" + handleStatus +
                ", createBeginDate=" + createBeginDate +
                ", createEndDate=" + createEndDate +
                '}';
    }
}

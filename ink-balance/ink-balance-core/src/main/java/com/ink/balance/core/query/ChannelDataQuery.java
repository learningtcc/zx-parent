package com.ink.balance.core.query;

import java.io.Serializable;
import java.util.Date;

import com.ink.base.BaseQuery;

/**
 * @author 作者 :bo.chen
 * @version 创建时间：2016年3月29日 下午5:42:52
 * @description 描述：渠道数据查询参数
 */
public class ChannelDataQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3133971548293862089L;

    private Long id;

    /**
     * 商户编号
     */
    private String merchantNo;

    /**
     * 支付渠道（1：）
     */
    private String channelNo;

    /**
     * 平台订单号
     */
    private String platformOrderNo;

    /**
     * 交易流水号
     */
    private String transNo;

    /**
     * 交易状态 1、待支付 2、支付成功 3、支付失败
     */
    private Integer transStatus;

    /**
     * 支付方式（1：代收、2：代付、3：网银、4：快捷）
     */
    private String payMethod;

    /**
     * 交易时间
     */
    private Date transTime;

    /**
     * 交易状态 0：初始 1：未匹配 2：已匹配 3、差错
     */
    private Integer checkStatus;

    /**
     * 驻留标识(0:非驻留，1：驻留)
     */
    private Integer resideFlag;

    /**
     * 驻留截止日
     */
    private Date resideToDate;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 删除标志
     */
    private Integer delFlag;

    private Date tradeBeginTime;

    private Date tradeEndTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public String getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo;
    }

    public String getPlatformOrderNo() {
        return platformOrderNo;
    }

    public void setPlatformOrderNo(String platformOrderNo) {
        this.platformOrderNo = platformOrderNo;
    }

    public String getTransNo() {
        return transNo;
    }

    public void setTransNo(String transNo) {
        this.transNo = transNo;
    }

    public Integer getTransStatus() {
        return transStatus;
    }

    public void setTransStatus(Integer transStatus) {
        this.transStatus = transStatus;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public Integer getResideFlag() {
        return resideFlag;
    }

    public void setResideFlag(Integer resideFlag) {
        this.resideFlag = resideFlag;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Date getResideToDate() {
        return resideToDate;
    }

    public void setResideToDate(Date resideToDate) {
        this.resideToDate = resideToDate;
    }

    public Date getTransTime() {
        return transTime;
    }

    public void setTransTime(Date transTime) {
        this.transTime = transTime;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "ChannelDataQuery{" +
                "id=" + id +
                ", merchantNo='" + merchantNo + '\'' +
                ", channelNo='" + channelNo + '\'' +
                ", platformOrderNo='" + platformOrderNo + '\'' +
                ", transNo='" + transNo + '\'' +
                ", transStatus=" + transStatus +
                ", payMethod='" + payMethod + '\'' +
                ", transTime=" + transTime +
                ", checkStatus=" + checkStatus +
                ", resideFlag=" + resideFlag +
                ", resideToDate=" + resideToDate +
                ", updateTime=" + updateTime +
                ", delFlag=" + delFlag +
                ", tradeBeginTime=" + tradeBeginTime +
                ", tradeEndTime=" + tradeEndTime +
                '}';
    }
}

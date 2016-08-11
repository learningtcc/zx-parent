package com.ink.balance.core.query;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ink.base.BaseQuery;

/**
 * @author 作者 :bo.chen
 * @version 创建时间：2016年3月29日 下午5:42:52
 * @description 描述：平台数据查询参数
 */
public class PlatformDataQuery extends BaseQuery implements Serializable {

    private static final long serialVersionUID = -8644040192969941348L;

    private Long id;

    /**
     * 支付渠道（1：CMBC）
     */
    private String channelNo;

    /**
     * 支付渠道商户号
     */
    private String channelMerchantNo;

    /**
     * 平台订单号
     */
    private String platformOrderNo;

    /**
     * 支付金额
     */
    private BigDecimal amt;

    /**
     * 到账金额
     */
    private BigDecimal arrivedAmt;

    /**
     * 交易流水号
     */
    private String transNo;

    /**
     * 交易状态 1、待支付 2、支付成功 3、支付失败
     */
    private Integer payStatus;

    /**
     * 支付方式（1：代收、2：代付、3：网银、4：快捷）
     */
    private String payMethod;

    /**
     * 交易状态 0：初始 1：未匹配 2：已匹配 3、差错
     */
    private Integer checkStatus;

    /**
     * 到账时间
     */
    private Date arrivedTime;

    /**
     * 支付生成时间
     */
    private Date payTime;

    /**
     * 驻留标识(0:非驻留，1：驻留)
     */
    private Integer resideFlag;

    /**
     * 驻留截止日
     */
    private Date resideToDate;

    /**
     * 删除标志
     */
    private Integer delFlag;

    /**
     * 交易开始时间
     */
    private Date tradeBeginTime;

    /**
     * 交易结束时间
     */
    private Date tradeEndTime;

    /**
     * 更新时间
     */
    private Date updateDate;


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

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
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

    public Date getArrivedTime() {
        return arrivedTime;
    }

    public void setArrivedTime(Date arrivedTime) {
        this.arrivedTime = arrivedTime;
    }

    public BigDecimal getAmt() {
        return amt;
    }

    public void setAmt(BigDecimal amt) {
        this.amt = amt;
    }

    public BigDecimal getArrivedAmt() {
        return arrivedAmt;
    }

    public void setArrivedAmt(BigDecimal arrivedAmt) {
        this.arrivedAmt = arrivedAmt;
    }

    public String getChannelMerchantNo() {
        return channelMerchantNo;
    }

    public void setChannelMerchantNo(String channelMerchantNo) {
        this.channelMerchantNo = channelMerchantNo;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "PlatformDataQuery{" +
                "id=" + id +
                ", channelNo='" + channelNo + '\'' +
                ", channelMerchantNo='" + channelMerchantNo + '\'' +
                ", platformOrderNo='" + platformOrderNo + '\'' +
                ", amt=" + amt +
                ", arrivedAmt=" + arrivedAmt +
                ", transNo='" + transNo + '\'' +
                ", payStatus=" + payStatus +
                ", payMethod='" + payMethod + '\'' +
                ", checkStatus=" + checkStatus +
                ", arrivedTime=" + arrivedTime +
                ", payTime=" + payTime +
                ", resideFlag=" + resideFlag +
                ", resideToDate=" + resideToDate +
                ", delFlag=" + delFlag +
                ", tradeBeginTime=" + tradeBeginTime +
                ", tradeEndTime=" + tradeEndTime +
                ", updateDate=" + updateDate +
                '}';
    }
}

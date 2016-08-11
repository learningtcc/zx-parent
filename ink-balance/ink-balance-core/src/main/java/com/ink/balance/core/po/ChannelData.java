package com.ink.balance.core.po;

import java.math.BigDecimal;
import java.util.Date;

import com.ink.base.BaseEntity;

/**
 * @author bo.chen
 * @Description 渠道数据
 * @date 2016年4月11日 上午11:03:07
 */
public class ChannelData extends BaseEntity implements java.io.Serializable {
    private static final long serialVersionUID = -6731585846188518734L;

    /**
     * 主键
     */
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
     * 订单金额
     */
    private BigDecimal amt;

    /**
     * 实收金额
     */
    private BigDecimal receivedAmt;

    /**
     * 交易时间
     */
    private Date transTime;

    /**
     * 交易状态 1、待支付 2、支付成功 3、支付失败
     */
    private Integer transStatus;

    /**
     * 支付方式（1：代收、2：代付、3：网银、4：快捷）
     */
    private String payMethod;

    /**
     * 交易状态 0：初始 1：未匹配 2：已匹配  3、差错
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
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 删除标志
     */
    private Integer delFlag;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 备注
     */
    private String remark;

    /**
     * 批次号
     */
    private String batchId;

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
        this.merchantNo = merchantNo == null ? null : merchantNo.trim();
    }

    public String getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo == null ? null : channelNo.trim();
    }

    public String getPlatformOrderNo() {
        return platformOrderNo;
    }

    public void setPlatformOrderNo(String platformOrderNo) {
        this.platformOrderNo = platformOrderNo == null ? null : platformOrderNo.trim();
    }

    public String getTransNo() {
        return transNo;
    }

    public void setTransNo(String transNo) {
        this.transNo = transNo == null ? null : transNo.trim();
    }

    public BigDecimal getAmt() {
        return amt;
    }

    public void setAmt(BigDecimal amt) {
        this.amt = amt;
    }

    public BigDecimal getReceivedAmt() {
        return receivedAmt;
    }

    public void setReceivedAmt(BigDecimal receivedAmt) {
        this.receivedAmt = receivedAmt;
    }

    public Date getTransTime() {
        return transTime;
    }

    public void setTransTime(Date transTime) {
        this.transTime = transTime;
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
        this.payMethod = payMethod == null ? null : payMethod.trim();
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

    public Date getResideToDate() {
        return resideToDate;
    }

    public void setResideToDate(Date resideToDate) {
        this.resideToDate = resideToDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    @Override
    public String toString() {
        return "ChannelData{" +
                "id=" + id +
                ", merchantNo='" + merchantNo + '\'' +
                ", channelNo='" + channelNo + '\'' +
                ", platformOrderNo='" + platformOrderNo + '\'' +
                ", transNo='" + transNo + '\'' +
                ", amt=" + amt +
                ", receivedAmt=" + receivedAmt +
                ", transTime=" + transTime +
                ", transStatus=" + transStatus +
                ", payMethod='" + payMethod + '\'' +
                ", checkStatus=" + checkStatus +
                ", resideFlag=" + resideFlag +
                ", resideToDate=" + resideToDate +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", delFlag=" + delFlag +
                ", version=" + version +
                ", remark='" + remark + '\'' +
                ", batchId='" + batchId + '\'' +
                '}';
    }
}
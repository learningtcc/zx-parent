package com.ink.balance.core.po;

import java.math.BigDecimal;
import java.util.Date;

import com.ink.base.BaseEntity;

/**
 * @author bo.chen
 * @Description 平台数据
 * @date 2016年4月11日 上午11:03:07
 */
public class PlatformData extends BaseEntity implements java.io.Serializable {
    private static final long serialVersionUID = -2675567336946281676L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 支付金额
     */
    private BigDecimal amt;

    /**
     * 到账金额
     */
    private BigDecimal arrivedAmt;

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
     * 交易流水号
     */
    private String transNo;

    /**
     * 支付生成时间
     */
    private Date payTime;

    /**
     * 到账时间
     */
    private Date arrivedTime;

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
    private Date createDate;

    /**
     * 更新时间
     */
    private Date updateDate;

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
        this.platformOrderNo = platformOrderNo == null ? null : platformOrderNo
                .trim();
    }

    public String getTransNo() {
        return transNo;
    }

    public void setTransNo(String transNo) {
        this.transNo = transNo == null ? null : transNo.trim();
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getArrivedTime() {
        return arrivedTime;
    }

    public void setArrivedTime(Date arrivedTime) {
        this.arrivedTime = arrivedTime;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
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

    public String getChannelMerchantNo() {
        return channelMerchantNo;
    }

    public void setChannelMerchantNo(String channelMerchantNo) {
        this.channelMerchantNo = channelMerchantNo;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    @Override
    public String toString() {
        return "PlatformData{" +
                "id=" + id +
                ", amt=" + amt +
                ", arrivedAmt=" + arrivedAmt +
                ", channelNo='" + channelNo + '\'' +
                ", channelMerchantNo='" + channelMerchantNo + '\'' +
                ", platformOrderNo='" + platformOrderNo + '\'' +
                ", transNo='" + transNo + '\'' +
                ", payTime=" + payTime +
                ", arrivedTime=" + arrivedTime +
                ", payStatus=" + payStatus +
                ", payMethod='" + payMethod + '\'' +
                ", checkStatus=" + checkStatus +
                ", resideFlag=" + resideFlag +
                ", resideToDate=" + resideToDate +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", delFlag=" + delFlag +
                ", version=" + version +
                ", remark='" + remark + '\'' +
                ", batchId='" + batchId + '\'' +
                '}';
    }
}
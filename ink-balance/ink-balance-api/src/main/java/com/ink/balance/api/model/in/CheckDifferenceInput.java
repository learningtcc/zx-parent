package com.ink.balance.api.model.in;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author bo.chen
 * @Description 差异数据,插入更新参数
 * @date 2016年4月11日 上午11:03:07
 */
public class CheckDifferenceInput implements java.io.Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1968587005554150431L;

    /**  */
    private Long id;

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

    /**  */
    private Long refMainrecordId;

    /**  */
    private String seqNo;

    /**
     * 平台订单号
     */
    private String platformOrderNo;

    /**  */
    private BigDecimal amount;

    /**  */
    private Integer status;

    /**  */
    private Date date;

    /**
     * 处理状态,0待处理1处理完成 2、挂起
     */
    private Integer handleStatus;

    /**
     * 处理说明
     */
    private String handleNotes;

    /**  */
    private Date createDate;

    /**  */
    private Date updateDate;

    /**  */
    private Long operatorId;

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

    public Long getRefMainrecordId() {
        return refMainrecordId;
    }

    public void setRefMainrecordId(Long refMainrecordId) {
        this.refMainrecordId = refMainrecordId;
    }

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo == null ? null : seqNo.trim();
    }

    public String getPlatformOrderNo() {
        return platformOrderNo;
    }

    public void setPlatformOrderNo(String platformOrderNo) {
        this.platformOrderNo = platformOrderNo == null ? null : platformOrderNo.trim();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getHandleStatus() {
        return handleStatus;
    }

    public void setHandleStatus(Integer handleStatus) {
        this.handleStatus = handleStatus;
    }

    public String getHandleNotes() {
        return handleNotes;
    }

    public void setHandleNotes(String handleNotes) {
        this.handleNotes = handleNotes == null ? null : handleNotes.trim();
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

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
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

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    @Override
    public String toString() {
        return "CheckDifference{" +
                "id=" + id +
                ", channelNo='" + channelNo + '\'' +
                ", channelMerchantNo='" + channelMerchantNo + '\'' +
                ", differenceType=" + differenceType +
                ", differenceSource=" + differenceSource +
                ", refMainrecordId=" + refMainrecordId +
                ", seqNo='" + seqNo + '\'' +
                ", platformOrderNo='" + platformOrderNo + '\'' +
                ", amount=" + amount +
                ", status=" + status +
                ", date=" + date +
                ", handleStatus=" + handleStatus +
                ", handleNotes='" + handleNotes + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", operatorId=" + operatorId +
                ", delFlag=" + delFlag +
                ", version=" + version +
                ", remark='" + remark + '\'' +
                ", batchId='" + batchId + '\'' +
                '}';
    }
}
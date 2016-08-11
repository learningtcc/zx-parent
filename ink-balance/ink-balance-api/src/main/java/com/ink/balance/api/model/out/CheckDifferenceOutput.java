package com.ink.balance.api.model.out;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author xuguoqi
 * @Description TODO
 * @date 2016年4月11日 上午10:11:32
 */
public class CheckDifferenceOutput implements Serializable {

    private static final long serialVersionUID = -2346629200568052397L;

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

    /**
     * 总对账表id
     */
    private Long refMainrecordId;

    /**
     * 流水号
     */
    private String seqNo;

    /**
     * 平台订单号
     */
    private String platformOrderNo;

    /**
     * 金额
     */
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
        this.seqNo = seqNo;
    }

    public String getPlatformOrderNo() {
        return platformOrderNo;
    }

    public void setPlatformOrderNo(String platformOrderNo) {
        this.platformOrderNo = platformOrderNo;
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
        this.handleNotes = handleNotes;
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
        this.remark = remark;
    }

    public CheckDifferenceOutput() {
        super();
    }

    public CheckDifferenceOutput(Long id, String channelNo, String channelMerchantNo, Integer differenceType,
                                 Integer differenceSource, Long refMainrecordId, String seqNo, String platformOrderNo, BigDecimal amount,
                                 Integer status, Date date, Integer handleStatus, String handleNotes, Date createDate, Date updateDate,
                                 Long operatorId, Integer delFlag, Integer version, String remark) {
        super();
        this.id = id;
        this.channelNo = channelNo;
        this.channelMerchantNo = channelMerchantNo;
        this.differenceType = differenceType;
        this.differenceSource = differenceSource;
        this.refMainrecordId = refMainrecordId;
        this.seqNo = seqNo;
        this.platformOrderNo = platformOrderNo;
        this.amount = amount;
        this.status = status;
        this.date = date;
        this.handleStatus = handleStatus;
        this.handleNotes = handleNotes;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.operatorId = operatorId;
        this.delFlag = delFlag;
        this.version = version;
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "CheckDifferenceOutput [id=" + id + ", channelNo=" + channelNo + ", channelMerchantNo="
                + channelMerchantNo + ", differenceType=" + differenceType + ", differenceSource=" + differenceSource
                + ", refMainrecordId=" + refMainrecordId + ", seqNo=" + seqNo + ", platformOrderNo=" + platformOrderNo
                + ", amount=" + amount + ", status=" + status + ", date=" + date + ", handleStatus=" + handleStatus
                + ", handleNotes=" + handleNotes + ", createDate=" + createDate + ", updateDate=" + updateDate
                + ", operatorId=" + operatorId + ", delFlag=" + delFlag + ", version=" + version + ", remark=" + remark
                + "]";
    }


}

package com.ink.balance.api.model.in;

import com.ink.base.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author bo.chen
 * @Description 总对账数据入参
 * @date 2016年6月17日 上午11:03:07
 */
public class CheckChannelParamInput extends BaseEntity implements java.io.Serializable {
    private static final long serialVersionUID = -3948987922865008959L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 对账日期
     */
    private Date checkDate;

    /**
     * 交易日期
     */
    private Date tradeDate;

    /**
     * 渠道编号
     */
    private String channelNo;

    /**
     * 支付渠道商户号
     */
    private String channelMerchantNo;

    /**
     * 渠道总金额
     */
    private BigDecimal channelAmount;

    /**
     * 渠道总笔数
     */
    private Integer channelCount;

    /**
     * 平台总金额
     */
    private BigDecimal platformAmount;

    /**
     * 平台总笔数
     */
    private Integer platformCount;

    /**
     * 平台单边笔数
     */
    private Integer platformSideCount;

    /**
     * 渠道单边笔数
     */
    private Integer channelSideCount;
    /**
     * 匹配总金额
     */
    private BigDecimal matchAmount;

    /**
     * 匹配总笔数
     */
    private Integer matchCount;

    /**
     * 对账结果状态（1、匹配 2、未匹配）此值不会改变，处理状态可变
     */
    private Integer checkStatus;

    /**
     * 差异笔数（生成后不会变，未处理差异笔数会改变）
     */
    private Integer differenceCount;

    /**
     * 渠道对账文件名称
     */
    private String channelCheckFile;

    /**
     * 文件来源(1:跑批、2：上传)
     */
    private String fileSource;

    /**
     * 处理状态,0待处理1处理完成
     */
    private Integer adjustStatus;

    /**
     * 未处理差异笔数
     */
    private Integer unhandleCount;

    /**
     * 处理说明（变更处理状态时写）
     */
    private String handleNotes;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 更新时间
     */
    private Date updateDate;

    /**
     * 操作员
     */
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
     * 批次号
     */
    private String batchId;

    public BigDecimal getMatchAmount() {
        return matchAmount;
    }

    public void setMatchAmount(BigDecimal matchAmount) {
        this.matchAmount = matchAmount;
    }

    public Integer getMatchCount() {
        return matchCount;
    }

    public void setMatchCount(Integer matchCount) {
        this.matchCount = matchCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public Date getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    public String getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo == null ? null : channelNo.trim();
    }

    public String getChannelMerchantNo() {
        return channelMerchantNo;
    }

    public void setChannelMerchantNo(String channelMerchantNo) {
        this.channelMerchantNo = channelMerchantNo == null ? null
                : channelMerchantNo.trim();
    }

    public BigDecimal getChannelAmount() {
        return channelAmount;
    }

    public void setChannelAmount(BigDecimal channelAmount) {
        this.channelAmount = channelAmount;
    }

    public Integer getChannelCount() {
        return channelCount;
    }

    public void setChannelCount(Integer channelCount) {
        this.channelCount = channelCount;
    }

    public BigDecimal getPlatformAmount() {
        return platformAmount;
    }

    public void setPlatformAmount(BigDecimal platformAmount) {
        this.platformAmount = platformAmount;
    }

    public Integer getPlatformCount() {
        return platformCount;
    }

    public void setPlatformCount(Integer platformCount) {
        this.platformCount = platformCount;
    }

    public Integer getPlatformSideCount() {
        return platformSideCount;
    }

    public void setPlatformSideCount(Integer platformSideCount) {
        this.platformSideCount = platformSideCount;
    }

    public Integer getChannelSideCount() {
        return channelSideCount;
    }

    public void setChannelSideCount(Integer channelSideCount) {
        this.channelSideCount = channelSideCount;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public Integer getDifferenceCount() {
        return differenceCount;
    }

    public void setDifferenceCount(Integer differenceCount) {
        this.differenceCount = differenceCount;
    }

    public String getChannelCheckFile() {
        return channelCheckFile;
    }

    public void setChannelCheckFile(String channelCheckFile) {
        this.channelCheckFile = channelCheckFile == null ? null
                : channelCheckFile.trim();
    }

    public String getFileSource() {
        return fileSource;
    }

    public void setFileSource(String fileSource) {
        this.fileSource = fileSource == null ? null : fileSource.trim();
    }

    public Integer getAdjustStatus() {
        return adjustStatus;
    }

    public void setAdjustStatus(Integer adjustStatus) {
        this.adjustStatus = adjustStatus;
    }

    public Integer getUnhandleCount() {
        return unhandleCount;
    }

    public void setUnhandleCount(Integer unhandleCount) {
        this.unhandleCount = unhandleCount;
    }

    public String getHandleNotes() {
        return handleNotes;
    }

    public void setHandleNotes(String handleNotes) {
        this.handleNotes = handleNotes == null ? null : handleNotes.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    @Override
    public String toString() {
        return "CheckChannel{" +
                "id=" + id +
                ", checkDate=" + checkDate +
                ", tradeDate=" + tradeDate +
                ", channelNo='" + channelNo + '\'' +
                ", channelMerchantNo='" + channelMerchantNo + '\'' +
                ", channelAmount=" + channelAmount +
                ", channelCount=" + channelCount +
                ", platformAmount=" + platformAmount +
                ", platformCount=" + platformCount +
                ", platformSideCount=" + platformSideCount +
                ", channelSideCount=" + channelSideCount +
                ", matchAmount=" + matchAmount +
                ", matchCount=" + matchCount +
                ", checkStatus=" + checkStatus +
                ", differenceCount=" + differenceCount +
                ", channelCheckFile='" + channelCheckFile + '\'' +
                ", fileSource='" + fileSource + '\'' +
                ", adjustStatus=" + adjustStatus +
                ", unhandleCount=" + unhandleCount +
                ", handleNotes='" + handleNotes + '\'' +
                ", remark='" + remark + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", operatorId=" + operatorId +
                ", delFlag=" + delFlag +
                ", version=" + version +
                ", batchId='" + batchId + '\'' +
                '}';
    }
}
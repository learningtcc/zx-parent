package com.ink.balance.api.model.out;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author bo.chen
 * @Description TODO
 * @date 2016年4月28日 上午11:05:29
 */
public class CheckBalanceOutput implements Serializable {

    private static final long serialVersionUID = 2879756877624854858L;

    /**
     * 主键
     */
    private Long id;
    /**
     * 关联主键（平台或渠道数据表主键）
     */
    private Long refId;
    /**
     * 调账对象（1：渠道、2：平台）
     */
    private int balanceSource;
    /**
     * 调账方向（1：正、2：负）
     */
    private int balanceDirection;
    /**
     * 调账金额
     */
    private BigDecimal balanceAmt;

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
     * 调账状态
     */
    private int balanceStatus;

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
     * 调账说明
     */
    private String balanceNote;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRefId() {
        return refId;
    }

    public void setRefId(Long refId) {
        this.refId = refId;
    }

    public int getBalanceSource() {
        return balanceSource;
    }

    public void setBalanceSource(int balanceSource) {
        this.balanceSource = balanceSource;
    }

    public int getBalanceDirection() {
        return balanceDirection;
    }

    public void setBalanceDirection(int balanceDirection) {
        this.balanceDirection = balanceDirection;
    }

    public BigDecimal getBalanceAmt() {
        return balanceAmt;
    }

    public void setBalanceAmt(BigDecimal balanceAmt) {
        this.balanceAmt = balanceAmt;
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

    public int getBalanceStatus() {
        return balanceStatus;
    }

    public void setBalanceStatus(int balanceStatus) {
        this.balanceStatus = balanceStatus;
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

    public String getBalanceNote() {
        return balanceNote;
    }

    public void setBalanceNote(String balanceNote) {
        this.balanceNote = balanceNote;
    }

    @Override
    public String toString() {
        return "CheckBalance{" +
                "id=" + id +
                ", refId=" + refId +
                ", balanceSource=" + balanceSource +
                ", balance_direction=" + balanceDirection +
                ", balanceAmt=" + balanceAmt +
                ", channelNo='" + channelNo + '\'' +
                ", platformOrderNo='" + platformOrderNo + '\'' +
                ", transNo='" + transNo + '\'' +
                ", balanceStatus=" + balanceStatus +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", delFlag=" + delFlag +
                ", version=" + version +
                ", balanceNote='" + balanceNote + '\'' +
                '}';
    }

}

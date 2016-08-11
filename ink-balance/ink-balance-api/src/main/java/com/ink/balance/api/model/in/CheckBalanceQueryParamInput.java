package com.ink.balance.api.model.in;

import java.io.Serializable;
import java.util.Date;

/**
 * @author bo.chen
 * @Description 调账数据查询条件参数
 * @date 2016年4月28日 上午10:31:11
 */
public class CheckBalanceQueryParamInput implements Serializable {

    private static final long serialVersionUID = 7475220831224521325L;

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
     * 调账状态
     */
    private int balanceStatus;

    private String channelNo;

    private Date createBeginDate;

    private Date createEndDate;

    /**
     * 平台订单号
     */
    private String platformOrderNo;

    /**
     * 交易流水号
     */
    private String transNo;

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

    public int getBalanceStatus() {
        return balanceStatus;
    }

    public void setBalanceStatus(int balanceStatus) {
        this.balanceStatus = balanceStatus;
    }

    public String getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo;
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

    @Override
    public String toString() {
        return "CheckBalanceQueryParamInput{" +
                "refId=" + refId +
                ", balanceSource=" + balanceSource +
                ", balanceDirection=" + balanceDirection +
                ", balanceStatus=" + balanceStatus +
                ", channelNo='" + channelNo + '\'' +
                ", tradeBeginTime=" + createBeginDate +
                ", tradeEndTime=" + createEndDate +
                ", platformOrderNo='" + platformOrderNo + '\'' +
                ", transNo='" + transNo + '\'' +
                '}';
    }
}

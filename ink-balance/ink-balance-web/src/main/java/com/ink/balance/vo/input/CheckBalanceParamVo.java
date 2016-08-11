package com.ink.balance.vo.input;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author bo.chen
 * @Description 调账分页查询
 * @date 2016年4月14日 下午7:02:26
 */
public class CheckBalanceParamVo implements Serializable {

    private static final long serialVersionUID = 5880257516381917852L;

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
     * 创建开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createBeginDate;

    /**
     * 创建结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createEndDate;

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

    @Override
    public String toString() {
        return "CheckBalanceParamVo{" +
                "refId=" + refId +
                ", balanceSource=" + balanceSource +
                ", balanceDirection=" + balanceDirection +
                ", channelNo='" + channelNo + '\'' +
                ", platformOrderNo='" + platformOrderNo + '\'' +
                ", transNo='" + transNo + '\'' +
                ", balanceStatus=" + balanceStatus +
                ", createBeginDate=" + createBeginDate +
                ", createEndDate=" + createEndDate +
                '}';
    }
}

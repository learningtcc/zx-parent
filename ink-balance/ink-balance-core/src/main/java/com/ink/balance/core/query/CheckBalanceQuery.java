package com.ink.balance.core.query;

import com.ink.base.BaseQuery;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 作者 :bo.chen
 * @version 创建时间：2016年4月28日 下午5:42:52
 * @description 描述：调账数据查询参数
 */
public class CheckBalanceQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3133971548293862089L;

    private Long id;
    /**
     * 关联主键（平台或渠道数据表主键）
     */
    private Long refId;

    /**
     * 调账对象（1：渠道、2：平台）
     */
    private Integer balanceSource;

    /**
     * 调账方向（1：正、2：负）
     */
    private Integer balanceDirection;

    /**
     * 支付渠道（1：）
     */
    private String channelNo;

    /**
     * 平台订单号
     */
    private String platformOrderNo;

    /**
     * 调账状态
     */
    private Integer balanceStatus;

    /**
     * 创建开始时间
     */
    private Date createBeginDate;

    /**
     * 创建结束时间
     */
    private Date createEndDate;

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

    public Integer getBalanceSource() {
        return balanceSource;
    }

    public void setBalanceSource(Integer balanceSource) {
        this.balanceSource = balanceSource;
    }

    public Integer getBalanceDirection() {
        return balanceDirection;
    }

    public void setBalanceDirection(Integer balanceDirection) {
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

    public Integer getBalanceStatus() {
        return balanceStatus;
    }

    public void setBalanceStatus(Integer balanceStatus) {
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
        return "CheckBalanceQuery{" +
                "id=" + id +
                ", refId=" + refId +
                ", balanceSource=" + balanceSource +
                ", balanceDirection=" + balanceDirection +
                ", channelNo='" + channelNo + '\'' +
                ", platformOrderNo='" + platformOrderNo + '\'' +
                ", balanceStatus=" + balanceStatus +
                ", createBeginDate=" + createBeginDate +
                ", createEndDate=" + createEndDate +
                '}';
    }
}

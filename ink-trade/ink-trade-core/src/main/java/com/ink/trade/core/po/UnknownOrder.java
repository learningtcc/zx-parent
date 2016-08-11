/**
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.trade.core.po;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.ink.base.BaseEntity;
import com.ink.base.util.DateConvertUtils;

/**
 * @author  zongtt
 * @version 1.0
 * @since 1.0
 */

public class UnknownOrder extends BaseEntity implements java.io.Serializable {
    private static final long serialVersionUID = 5454155825314635342L;

    //alias
    public static final String TABLE_ALIAS = "UnknownOrder";
//
//	public static final String ALIAS_ID = "主键";
//
//	public static final String ALIAS_PAY_ID = "支付订单表主键";
//
//	public static final String ALIAS_PAY_ORDER_NO = "支付订单号";
//
//	public static final String ALIAS_ORDER_STATUS = "订单状态";
//
//	public static final String ALIAS_FINAL_STATUS = "是否终态";
//
//	public static final String ALIAS_CHANNEL_NO = "落地渠道号";
//
//	public static final String ALIAS_EXECUTE_REMARK = "任务标记位（唯一标识某台机器）";
//
//	public static final String ALIAS_EXECUTE_COUNT = "任务执行次数";
//
//	public static final String ALIAS_ORDER_TIME = "订单时间";
//
//	public static final String ALIAS_LAST_UPDATE_TIME = "最后更新时间";
//
//	public static final String ALIAS_TRANS_TYPE = "交易类型";
//
//	public static final String ALIAS_TRADE_ORDER_ID = "交易订单主键";
//
//	public static final String ALIAS_MCH_ID = "商户号";
//

    //date formats
    public static final String FORMAT_ORDER_TIME = DATE_FORMAT;
    public static final String FORMAT_LAST_UPDATE_TIME = DATE_FORMAT;

    //columns START
    //主键
    private Long id;
    //支付订单表主键
    private Long payId;
    //支付订单号
    private String payOrderNo;
    //订单状态
    private Integer orderStatus;
    //是否终态
    private String finalStatus;
    //落地渠道号
    private String channelNo;
    //任务标记位（唯一标识某台机器）
    private String executeRemark;
    //任务执行次数
    private Integer executeCount;
    //订单时间
    private java.util.Date orderTime;
    //最后更新时间
    private java.util.Date lastUpdateTime;
    //交易类型
    private String transType;
    //交易订单主键
    private Long tradeOrderId;
    //商户号
    private String mchId;
    //columns END

    public UnknownOrder(){
    }

    public UnknownOrder(
            java.lang.Long id
    ){
        this.id = id;
    }

    public void setId(java.lang.Long value) {
        this.id = value;
    }

    public java.lang.Long getId() {
        return this.id;
    }
    public void setPayId(java.lang.Long value) {
        this.payId = value;
    }

    public java.lang.Long getPayId() {
        return this.payId;
    }
    public void setPayOrderNo(java.lang.String value) {
        this.payOrderNo = value;
    }

    public java.lang.String getPayOrderNo() {
        return this.payOrderNo;
    }
    public void setOrderStatus(java.lang.Integer value) {
        this.orderStatus = value;
    }

    public java.lang.Integer getOrderStatus() {
        return this.orderStatus;
    }
    public void setFinalStatus(java.lang.String value) {
        this.finalStatus = value;
    }

    public java.lang.String getFinalStatus() {
        return this.finalStatus;
    }
    public void setChannelNo(java.lang.String value) {
        this.channelNo = value;
    }

    public java.lang.String getChannelNo() {
        return this.channelNo;
    }
    public void setExecuteRemark(java.lang.String value) {
        this.executeRemark = value;
    }

    public java.lang.String getExecuteRemark() {
        return this.executeRemark;
    }
    public void setExecuteCount(java.lang.Integer value) {
        this.executeCount = value;
    }

    public java.lang.Integer getExecuteCount() {
        return this.executeCount;
    }
    public String getOrderTimeString() {
        return DateConvertUtils.format(getOrderTime(), FORMAT_ORDER_TIME);
    }
    public void setOrderTimeString(String value) {
        setOrderTime(DateConvertUtils.parse(value, FORMAT_ORDER_TIME,java.util.Date.class));
    }

    public void setOrderTime(java.util.Date value) {
        this.orderTime = value;
    }

    public java.util.Date getOrderTime() {
        return this.orderTime;
    }
    public String getLastUpdateTimeString() {
        return DateConvertUtils.format(getLastUpdateTime(), FORMAT_LAST_UPDATE_TIME);
    }
    public void setLastUpdateTimeString(String value) {
        setLastUpdateTime(DateConvertUtils.parse(value, FORMAT_LAST_UPDATE_TIME,java.util.Date.class));
    }

    public void setLastUpdateTime(java.util.Date value) {
        this.lastUpdateTime = value;
    }

    public java.util.Date getLastUpdateTime() {
        return this.lastUpdateTime;
    }
    public void setTransType(java.lang.String value) {
        this.transType = value;
    }

    public java.lang.String getTransType() {
        return this.transType;
    }
    public void setTradeOrderId(java.lang.Long value) {
        this.tradeOrderId = value;
    }

    public java.lang.Long getTradeOrderId() {
        return this.tradeOrderId;
    }
    public void setMchId(java.lang.String value) {
        this.mchId = value;
    }

    public java.lang.String getMchId() {
        return this.mchId;
    }
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("Id",getId())
                .append("PayId",getPayId())
                .append("PayOrderNo",getPayOrderNo())
                .append("OrderStatus",getOrderStatus())
                .append("FinalStatus",getFinalStatus())
                .append("ChannelNo",getChannelNo())
                .append("ExecuteRemark",getExecuteRemark())
                .append("ExecuteCount",getExecuteCount())
                .append("OrderTime",getOrderTime())
                .append("LastUpdateTime",getLastUpdateTime())
                .append("TransType",getTransType())
                .append("TradeOrderId",getTradeOrderId())
                .append("MchId",getMchId())
                .toString();
    }

    public String toString(String separator) {
        StringBuffer sb = new StringBuffer();

        sb.append("主键:").append(id).append(separator);
        sb.append("支付订单表主键:").append(payId).append(separator);
        sb.append("支付订单号:").append(payOrderNo).append(separator);
        sb.append("订单状态:").append(orderStatus).append(separator);
        sb.append("是否终态:").append(finalStatus).append(separator);
        sb.append("落地渠道号:").append(channelNo).append(separator);
        sb.append("任务标记位（唯一标识某台机器）:").append(executeRemark).append(separator);
        sb.append("任务执行次数:").append(executeCount).append(separator);
        sb.append("订单时间:").append(getOrderTimeString()).append(separator);
        sb.append("最后更新时间:").append(getLastUpdateTimeString()).append(separator);
        sb.append("交易类型:").append(transType).append(separator);
        sb.append("交易订单主键:").append(tradeOrderId).append(separator);
        sb.append("商户号:").append(mchId).append(separator);

        return sb.toString();
    }

    public int hashCode() {
        return new HashCodeBuilder()
                .append(getId())
                .toHashCode();
    }

    public boolean equals(Object obj) {
        if(obj instanceof UnknownOrder == false) return false;
        if(this == obj) return true;
        UnknownOrder other = (UnknownOrder)obj;
        return new EqualsBuilder()
                .append(getId(),other.getId())
                .isEquals();
    }
}


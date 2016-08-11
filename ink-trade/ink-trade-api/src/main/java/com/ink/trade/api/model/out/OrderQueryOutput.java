package com.ink.trade.api.model.out;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <pre>
 * <b>类描述:</b>(订单查询输出)
 * <b>作者:</b>zx
 * <b>创建日期:</b>2016年5月5日 下午3:19:42
 * </pre>
 */
public class OrderQueryOutput implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -6986029570621994819L;

    private String version;// 版本号

    private String merchantId;// 商户号

    private String userId;// 用户号

    private Date tradeDate;// 交易日期

    private BigDecimal amt;// 交易金额

    private String orderId;// 订单号

    private String requestId;// 请求流水号

    private int orderStatus;// 订单状态

    private String tradeCode;// 交易码

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    public BigDecimal getAmt() {
        return amt;
    }

    public void setAmt(BigDecimal amt) {
        this.amt = amt;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getTradeCode() {
        return tradeCode;
    }

    public void setTradeCode(String tradeCode) {
        this.tradeCode = tradeCode;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("OrderQueryOutput [version=");
        builder.append(version);
        builder.append(", merchantId=");
        builder.append(merchantId);
        builder.append(", userId=");
        builder.append(userId);
        builder.append(", tradeDate=");
        builder.append(tradeDate);
        builder.append(", amt=");
        builder.append(amt);
        builder.append(", orderId=");
        builder.append(orderId);
        builder.append(", requestId=");
        builder.append(requestId);
        builder.append(", orderStatus=");
        builder.append(orderStatus);
        builder.append(", tradeCode=");
        builder.append(tradeCode);
        builder.append("]");
        return builder.toString();
    }
    
}

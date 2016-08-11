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

import java.math.BigDecimal;

/**
 * @author zongtt
 * @version 1.0
 * @since 1.0
 */

public class TradeOrder extends BaseEntity implements java.io.Serializable {
    private static final long serialVersionUID = 5454155825314635342L;

    //alias
    public static final String TABLE_ALIAS = "TradeOrder";
//
//	public static final String ALIAS_ID = "主键id";
//
//	public static final String ALIAS_REQ_ID = "请求流水号";
//
//	public static final String ALIAS_MCH_ID = "商户号";
//
//	public static final String ALIAS_TXN_NAME = "交易名称";
//
//	public static final String ALIAS_TXN_CODE = "交易码";
//
//	public static final String ALIAS_ORDER_TIME = "订单日期";
//
//	public static final String ALIAS_PHONE_NO = "手机号";
//
//	public static final String ALIAS_USER_NAME = "姓名";
//
//	public static final String ALIAS_AMT = "订单金额";
//
//	public static final String ALIAS_STATUS = "订单状态";
//
//	public static final String ALIAS_VERSION = "版本号";
//
//	public static final String ALIAS_CREATE_TIME = "创建时间";
//
//	public static final String ALIAS_OPERATOR = "操作人";
//
//	public static final String ALIAS_REMARK = "备注";
//
//	public static final String ALIAS_CID = "绑卡信息表主键";
//
//	public static final String ALIAS_LASTUPDATE_TIME = "最后修改时间";
//
//	public static final String ALIAS_CHANNEL_NO = "渠道号，支付成功后更新订单状态时，插入渠道号信息";
//
//	public static final String ALIAS_CARD_NO = "银行卡号";
//
//	public static final String ALIAS_USER_ID = "用户id";
//
//	public static final String ALIAS_ORDER_ID = "商户订单号";
//
//	public static final String ALIAS_TRADE_DATE = "交易时间-记录商户传过来的交易时间";
//
//	public static final String ALIAS_ACCOUNT_TYPE = "账户类型";
//
//	public static final String ALIAS_BANK_SHORT = "银行简码";
//
//	public static final String ALIAS_TOKEN = "渠道token";
//
//	public static final String ALIAS_PAY_TYPE = "支付方式";
//

    //date formats
    public static final String FORMAT_ORDER_TIME = DATE_FORMAT;
    public static final String FORMAT_CREATE_TIME = DATE_FORMAT;
    public static final String FORMAT_LASTUPDATE_TIME = DATE_FORMAT;
    public static final String FORMAT_TRADE_DATE = DATE_FORMAT;

    //columns START
    //主键id
    private Long id;
    //请求流水号
    private String reqId;
    //商户号
    private String mchId;
    //交易名称
    private String txnName;
    //交易码
    private String txnCode;
    //订单日期
    private java.util.Date orderTime;
    //手机号
    private String phoneNo;
    //姓名
    private String userName;
    //订单金额
    private BigDecimal amt;
    //订单状态
    private Integer status;
    //版本号
    private Integer version;
    //创建时间
    private java.util.Date createTime;
    //操作人
    private String operator;
    //备注
    private String remark;
    //绑卡信息表主键
    private Long cid;
    //最后修改时间
    private java.util.Date lastupdateTime;
    //渠道号，支付成功后更新订单状态时，插入渠道号信息
    private String channelNo;
    //银行卡号
    private String cardNo;
    //用户id
    private String userId;
    //商户订单号
    private String orderId;
    //交易时间-记录商户传过来的交易时间
    private java.util.Date tradeDate;
    //账户类型
    private String accountType;
    //银行简码
    private String bankShort;
    //渠道token
    private String token;
    //支付方式
    private String payType;
    //回调地址
    private String noticeUrl;
    private String routeBusinessType;
    
    //columns END

    public TradeOrder() {
    }

    public String getRouteBusinessType() {
		return routeBusinessType;
	}

	public void setRouteBusinessType(String routeBusinessType) {
		this.routeBusinessType = routeBusinessType;
	}

	public String getNoticeUrl() {
		return noticeUrl;
	}

	public void setNoticeUrl(String noticeUrl) {
		this.noticeUrl = noticeUrl;
	}

	public TradeOrder(
            java.lang.Long id
    ) {
        this.id = id;
    }

    public void setId(java.lang.Long value) {
        this.id = value;
    }

    public java.lang.Long getId() {
        return this.id;
    }

    public void setReqId(java.lang.String value) {
        this.reqId = value;
    }

    public java.lang.String getReqId() {
        return this.reqId;
    }

    public void setMchId(java.lang.String value) {
        this.mchId = value;
    }

    public java.lang.String getMchId() {
        return this.mchId;
    }

    public void setTxnName(java.lang.String value) {
        this.txnName = value;
    }

    public java.lang.String getTxnName() {
        return this.txnName;
    }

    public void setTxnCode(java.lang.String value) {
        this.txnCode = value;
    }

    public java.lang.String getTxnCode() {
        return this.txnCode;
    }

    public String getOrderTimeString() {
        return DateConvertUtils.format(getOrderTime(), FORMAT_ORDER_TIME);
    }

    public void setOrderTimeString(String value) {
        setOrderTime(DateConvertUtils.parse(value, FORMAT_ORDER_TIME, java.util.Date.class));
    }

    public void setOrderTime(java.util.Date value) {
        this.orderTime = value;
    }

    public java.util.Date getOrderTime() {
        return this.orderTime;
    }

    public void setPhoneNo(java.lang.String value) {
        this.phoneNo = value;
    }

    public java.lang.String getPhoneNo() {
        return this.phoneNo;
    }

    public void setUserName(java.lang.String value) {
        this.userName = value;
    }

    public java.lang.String getUserName() {
        return this.userName;
    }

    public void setAmt(BigDecimal value) {
        this.amt = value;
    }

    public BigDecimal getAmt() {
        return this.amt;
    }

    public void setStatus(java.lang.Integer value) {
        this.status = value;
    }

    public java.lang.Integer getStatus() {
        return this.status;
    }

    public void setVersion(java.lang.Integer value) {
        this.version = value;
    }

    public java.lang.Integer getVersion() {
        return this.version;
    }

    public String getCreateTimeString() {
        return DateConvertUtils.format(getCreateTime(), FORMAT_CREATE_TIME);
    }

    public void setCreateTimeString(String value) {
        setCreateTime(DateConvertUtils.parse(value, FORMAT_CREATE_TIME, java.util.Date.class));
    }

    public void setCreateTime(java.util.Date value) {
        this.createTime = value;
    }

    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    public void setOperator(java.lang.String value) {
        this.operator = value;
    }

    public java.lang.String getOperator() {
        return this.operator;
    }

    public void setRemark(java.lang.String value) {
        this.remark = value;
    }

    public java.lang.String getRemark() {
        return this.remark;
    }

    public void setCid(java.lang.Long value) {
        this.cid = value;
    }

    public java.lang.Long getCid() {
        return this.cid;
    }

    public String getLastupdateTimeString() {
        return DateConvertUtils.format(getLastupdateTime(), FORMAT_LASTUPDATE_TIME);
    }

    public void setLastupdateTimeString(String value) {
        setLastupdateTime(DateConvertUtils.parse(value, FORMAT_LASTUPDATE_TIME, java.util.Date.class));
    }

    public void setLastupdateTime(java.util.Date value) {
        this.lastupdateTime = value;
    }

    public java.util.Date getLastupdateTime() {
        return this.lastupdateTime;
    }

    public void setChannelNo(java.lang.String value) {
        this.channelNo = value;
    }

    public java.lang.String getChannelNo() {
        return this.channelNo;
    }

    public void setCardNo(java.lang.String value) {
        this.cardNo = value;
    }

    public java.lang.String getCardNo() {
        return this.cardNo;
    }

    public void setUserId(java.lang.String value) {
        this.userId = value;
    }

    public java.lang.String getUserId() {
        return this.userId;
    }

    public void setOrderId(java.lang.String value) {
        this.orderId = value;
    }

    public java.lang.String getOrderId() {
        return this.orderId;
    }

    public String getTradeDateString() {
        return DateConvertUtils.format(getTradeDate(), FORMAT_TRADE_DATE);
    }

    public void setTradeDateString(String value) {
        setTradeDate(DateConvertUtils.parse(value, FORMAT_TRADE_DATE, java.util.Date.class));
    }

    public void setTradeDate(java.util.Date value) {
        this.tradeDate = value;
    }

    public java.util.Date getTradeDate() {
        return this.tradeDate;
    }

    public void setAccountType(java.lang.String value) {
        this.accountType = value;
    }

    public java.lang.String getAccountType() {
        return this.accountType;
    }

    public void setBankShort(java.lang.String value) {
        this.bankShort = value;
    }

    public java.lang.String getBankShort() {
        return this.bankShort;
    }

    public void setToken(java.lang.String value) {
        this.token = value;
    }

    public java.lang.String getToken() {
        return this.token;
    }

    public void setPayType(java.lang.String value) {
        this.payType = value;
    }

    public java.lang.String getPayType() {
        return this.payType;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("Id", getId())
                .append("ReqId", getReqId())
                .append("MchId", getMchId())
                .append("TxnName", getTxnName())
                .append("TxnCode", getTxnCode())
                .append("OrderTime", getOrderTime())
                .append("PhoneNo", getPhoneNo())
                .append("UserName", getUserName())
                .append("Amt", getAmt())
                .append("Status", getStatus())
                .append("Version", getVersion())
                .append("CreateTime", getCreateTime())
                .append("Operator", getOperator())
                .append("Remark", getRemark())
                .append("Cid", getCid())
                .append("LastupdateTime", getLastupdateTime())
                .append("ChannelNo", getChannelNo())
                .append("CardNo", getCardNo())
                .append("UserId", getUserId())
                .append("OrderId", getOrderId())
                .append("TradeDate", getTradeDate())
                .append("AccountType", getAccountType())
                .append("BankShort", getBankShort())
                .append("Token", getToken())
                .append("PayType", getPayType())
                .toString();
    }

    public String toString(String separator) {
        StringBuffer sb = new StringBuffer();

        sb.append("主键id:").append(id).append(separator);
        sb.append("请求流水号:").append(reqId).append(separator);
        sb.append("商户号:").append(mchId).append(separator);
        sb.append("交易名称:").append(txnName).append(separator);
        sb.append("交易码:").append(txnCode).append(separator);
        sb.append("订单日期:").append(getOrderTimeString()).append(separator);
        sb.append("手机号:").append(phoneNo).append(separator);
        sb.append("姓名:").append(userName).append(separator);
        sb.append("订单金额:").append(amt).append(separator);
        sb.append("订单状态:").append(status).append(separator);
        sb.append("版本号:").append(version).append(separator);
        sb.append("创建时间:").append(getCreateTimeString()).append(separator);
        sb.append("操作人:").append(operator).append(separator);
        sb.append("备注:").append(remark).append(separator);
        sb.append("绑卡信息表主键:").append(cid).append(separator);
        sb.append("最后修改时间:").append(getLastupdateTimeString()).append(separator);
        sb.append("渠道号，支付成功后更新订单状态时，插入渠道号信息:").append(channelNo).append(separator);
        sb.append("银行卡号:").append(cardNo).append(separator);
        sb.append("用户id:").append(userId).append(separator);
        sb.append("商户订单号:").append(orderId).append(separator);
        sb.append("交易时间-记录商户传过来的交易时间:").append(getTradeDateString()).append(separator);
        sb.append("账户类型:").append(accountType).append(separator);
        sb.append("银行简码:").append(bankShort).append(separator);
        sb.append("渠道token:").append(token).append(separator);
        sb.append("支付方式:").append(payType).append(separator);

        return sb.toString();
    }

    public int hashCode() {
        return new HashCodeBuilder()
                .append(getId())
                .toHashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof TradeOrder == false) return false;
        if (this == obj) return true;
        TradeOrder other = (TradeOrder) obj;
        return new EqualsBuilder()
                .append(getId(), other.getId())
                .isEquals();
    }
}


package com.ink.trade.core.po;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.ink.base.BaseEntity;
import com.ink.base.util.DateConvertUtils;

public class TradeLog extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "TradeLog";
	//date formats
	public static final String FORMAT_ORDER_DATE = DATE_FORMAT;
	public static final String FORMAT_CREATE_TIME = DATE_FORMAT;
	//columns START
	//主键
	private Long id;
	//请求流水号
	private String reqId;
	//商户id
	private String mchId;
	//订单id
	private String ordId;
	//交易码
	private String txnCode;
	//订单日期
	private java.util.Date orderDate;
	//订单金额
	private java.math.BigDecimal amt;
	//交易状态
	private Integer status;
	//操作员
	private String operator;
	//channelNo
	private String channelNo;
	//userId
	private String userId;
	//createTime
	private java.util.Date createTime;
	//cardNo
	private String cardNo;
	//cid
	private Long cid;
	//phone
	private String phone;
	private String routeBusinessType;
	//columns END

	public TradeLog(){
	}

	public TradeLog(
		java.lang.Long id
	){
		this.id = id;
	}

	public String getRouteBusinessType() {
		return routeBusinessType;
	}

	public void setRouteBusinessType(String routeBusinessType) {
		this.routeBusinessType = routeBusinessType;
	}

	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.Long getId() {
		return this.id;
	}
	public void setReqId(String value) {
		this.reqId = value;
	}
	
	public String getReqId() {
		return this.reqId;
	}

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public void setOrdId(String value) {
		this.ordId = value;
	}
	
	public String getOrdId() {
		return this.ordId;
	}
	public void setTxnCode(java.lang.String value) {
		this.txnCode = value;
	}
	
	public java.lang.String getTxnCode() {
		return this.txnCode;
	}
	public String getOrderDateString() {
		return DateConvertUtils.format(getOrderDate(), FORMAT_ORDER_DATE);
	}
	public void setOrderDateString(String value) {
		setOrderDate(DateConvertUtils.parse(value, FORMAT_ORDER_DATE,java.util.Date.class));
	}
	
	public void setOrderDate(java.util.Date value) {
		this.orderDate = value;
	}
	
	public java.util.Date getOrderDate() {
		return this.orderDate;
	}
	public void setAmt(java.math.BigDecimal value) {
		this.amt = value;
	}
	
	public java.math.BigDecimal getAmt() {
		return this.amt;
	}

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setOperator(java.lang.String value) {
		this.operator = value;
	}
	
	public java.lang.String getOperator() {
		return this.operator;
	}
	public void setChannelNo(java.lang.String value) {
		this.channelNo = value;
	}
	
	public java.lang.String getChannelNo() {
		return this.channelNo;
	}
	public void setUserId(java.lang.String value) {
		this.userId = value;
	}
	
	public java.lang.String getUserId() {
		return this.userId;
	}
	public String getCreateTimeString() {
		return DateConvertUtils.format(getCreateTime(), FORMAT_CREATE_TIME);
	}
	public void setCreateTimeString(String value) {
		setCreateTime(DateConvertUtils.parse(value, FORMAT_CREATE_TIME,java.util.Date.class));
	}
	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	public void setCardNo(java.lang.String value) {
		this.cardNo = value;
	}
	
	public java.lang.String getCardNo() {
		return this.cardNo;
	}
	public void setCid(java.lang.Long value) {
		this.cid = value;
	}
	
	public java.lang.Long getCid() {
		return this.cid;
	}
	public void setPhone(java.lang.String value) {
		this.phone = value;
	}
	
	public java.lang.String getPhone() {
		return this.phone;
	}
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("ReqId",getReqId())
			.append("MchId",getMchId())
			.append("OrdId",getOrdId())
			.append("TxnCode",getTxnCode())
			.append("OrderDate",getOrderDate())
			.append("Amt",getAmt())
			.append("Status",getStatus())
			.append("Operator",getOperator())
			.append("ChannelNo",getChannelNo())
			.append("UserId",getUserId())
			.append("CreateTime",getCreateTime())
			.append("CardNo",getCardNo())
			.append("Cid",getCid())
			.append("Phone",getPhone())
			.toString();
	}
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TradeLog == false) return false;
		if(this == obj) return true;
		TradeLog other = (TradeLog)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

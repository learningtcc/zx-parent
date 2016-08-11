package com.ink.trade.core.po;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.ink.base.BaseEntity;
import com.ink.base.util.DateConvertUtils;

public class PayLog extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "PayLog";
	//date formats
	public static final String FORMAT_CREATE_TIME = DATE_FORMAT;
	public static final String FORMAT_ORDER_DATE = DATE_FORMAT;
	public static final String FORMAT_LASTUPDATE_TIME = DATE_FORMAT;
	public static final String FORMAT_REQ_TIME = DATE_FORMAT;
	public static final String FORMAT_REP_TIME = DATE_FORMAT;
	
	//columns START
	//主键
	private Long id;
	//支付信息主键
	private Long payId;
	//交易请求流水
	private String reqId;
	//交易订单号
	private String ordId;
	//渠道名称
	private String chanelName;
	//渠道编号
	private String chanelNo;
	//状态
	private Integer status;
	//创建时间
	private java.util.Date createTime;
	//备注
	private String remark;
	//支付金额
	private java.math.BigDecimal amt;
	//商户号
	private String mchId;
	//订单日期
	private java.util.Date orderDate;
	//响应码
	private String repCode;
	//响应信息
	private String repMsg;
	//通道响应码
	private String asileRepCode;
	//通道响应信息
	private String asileRepMsg;
	//版本号
	private Integer version;
	//最后更新时间
	private java.util.Date lastupdateTime;
	//请求时间
	private java.util.Date reqTime;
	//响应时间
	private java.util.Date repTime;
	//支付订单号
	private String payOrderId;
	//支付请求流水号
	private String payReqId;
	//渠道交易订单号
	private String asileTransId;
	//columns END

	public PayLog(){
	}

	public PayLog(
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
	public void setReqId(String value) {
		this.reqId = value;
	}
	
	public String getReqId() {
		return this.reqId;
	}
	public void setOrdId(String value) {
		this.ordId = value;
	}
	
	public String getOrdId() {
		return this.ordId;
	}
	public void setChanelName(java.lang.String value) {
		this.chanelName = value;
	}
	
	public java.lang.String getChanelName() {
		return this.chanelName;
	}
	public void setChanelNo(java.lang.String value) {
		this.chanelNo = value;
	}
	
	public java.lang.String getChanelNo() {
		return this.chanelNo;
	}
	public void setStatus(java.lang.Integer value) {
		this.status = value;
	}
	
	public java.lang.Integer getStatus() {
		return this.status;
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
	public void setRemark(java.lang.String value) {
		this.remark = value;
	}
	
	public java.lang.String getRemark() {
		return this.remark;
	}
	public void setAmt(java.math.BigDecimal value) {
		this.amt = value;
	}
	
	public java.math.BigDecimal getAmt() {
		return this.amt;
	}

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
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
	public void setRepCode(java.lang.String value) {
		this.repCode = value;
	}
	
	public java.lang.String getRepCode() {
		return this.repCode;
	}
	public void setRepMsg(java.lang.String value) {
		this.repMsg = value;
	}
	
	public java.lang.String getRepMsg() {
		return this.repMsg;
	}
	public void setAsileRepCode(java.lang.String value) {
		this.asileRepCode = value;
	}
	
	public java.lang.String getAsileRepCode() {
		return this.asileRepCode;
	}
	public void setAsileRepMsg(java.lang.String value) {
		this.asileRepMsg = value;
	}
	
	public java.lang.String getAsileRepMsg() {
		return this.asileRepMsg;
	}
	public void setVersion(java.lang.Integer value) {
		this.version = value;
	}
	
	public java.lang.Integer getVersion() {
		return this.version;
	}
	public String getLastupdateTimeString() {
		return DateConvertUtils.format(getLastupdateTime(), FORMAT_LASTUPDATE_TIME);
	}
	public void setLastupdateTimeString(String value) {
		setLastupdateTime(DateConvertUtils.parse(value, FORMAT_LASTUPDATE_TIME,java.util.Date.class));
	}
	
	public void setLastupdateTime(java.util.Date value) {
		this.lastupdateTime = value;
	}
	
	public java.util.Date getLastupdateTime() {
		return this.lastupdateTime;
	}
	public String getReqTimeString() {
		return DateConvertUtils.format(getReqTime(), FORMAT_REQ_TIME);
	}
	public void setReqTimeString(String value) {
		setReqTime(DateConvertUtils.parse(value, FORMAT_REQ_TIME,java.util.Date.class));
	}
	
	public void setReqTime(java.util.Date value) {
		this.reqTime = value;
	}
	
	public java.util.Date getReqTime() {
		return this.reqTime;
	}
	public String getRepTimeString() {
		return DateConvertUtils.format(getRepTime(), FORMAT_REP_TIME);
	}
	public void setRepTimeString(String value) {
		setRepTime(DateConvertUtils.parse(value, FORMAT_REP_TIME,java.util.Date.class));
	}
	
	public void setRepTime(java.util.Date value) {
		this.repTime = value;
	}
	
	public java.util.Date getRepTime() {
		return this.repTime;
	}
	public void setPayOrderId(java.lang.String value) {
		this.payOrderId = value;
	}
	
	public java.lang.String getPayOrderId() {
		return this.payOrderId;
	}
	public void setPayReqId(java.lang.String value) {
		this.payReqId = value;
	}
	
	public java.lang.String getPayReqId() {
		return this.payReqId;
	}
	public void setAsileTransId(java.lang.String value) {
		this.asileTransId = value;
	}
	
	public java.lang.String getAsileTransId() {
		return this.asileTransId;
	}
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("PayId",getPayId())
			.append("ReqId",getReqId())
			.append("OrdId",getOrdId())
			.append("ChanelName",getChanelName())
			.append("ChanelNo",getChanelNo())
			.append("Status",getStatus())
			.append("CreateTime",getCreateTime())
			.append("Remark",getRemark())
			.append("Amt",getAmt())
			.append("MchId",getMchId())
			.append("OrderDate",getOrderDate())
			.append("RepCode",getRepCode())
			.append("RepMsg",getRepMsg())
			.append("AsileRepCode",getAsileRepCode())
			.append("AsileRepMsg",getAsileRepMsg())
			.append("Version",getVersion())
			.append("LastupdateTime",getLastupdateTime())
			.append("ReqTime",getReqTime())
			.append("RepTime",getRepTime())
			.append("PayOrderId",getPayOrderId())
			.append("PayReqId",getPayReqId())
			.append("AsileTransId",getAsileTransId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof PayLog == false) return false;
		if(this == obj) return true;
		PayLog other = (PayLog)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}


package com.ink.trade.api.platform.trade.model.base;

import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.ink.base.BaseEntity;
import com.ink.base.util.DateConvertUtils;

public class PayOrderEntity extends BaseEntity implements java.io.Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 326734455250566980L;
	//alias
    public static final String TABLE_ALIAS = "Pay";

	public static final String ALIAS_ID = "主键";

	public static final String ALIAS_REQ_ID = "交易请求流水号";

	public static final String ALIAS_ORD_ID = "交易订单号";

	public static final String ALIAS_CHANEL_NAME = "渠道名称";

	public static final String ALIAS_CHANEL_NO = "渠道编号";

	public static final String ALIAS_AMT = "订单金额";

	public static final String ALIAS_STATUS = "支付状态";

	public static final String ALIAS_CREATE_TIME = "创建时间";

	public static final String ALIAS_VERSION = "版本号";

	public static final String ALIAS_REMARK = "备注";

	public static final String ALIAS_REQ_TIME = "请求时间";

	public static final String ALIAS_REP_TIME = "响应时间";

	public static final String ALIAS_LASTUPDATE_TIME = "最后修改时间";

	public static final String ALIAS_MCH_ID = "商户号";

	public static final String ALIAS_ORDER_DATE = "订单日期";

	public static final String ALIAS_ASILE_REP_CODE = "通道响应码";

	public static final String ALIAS_ASILE_REP_MSG = "通道响应信息";

	public static final String ALIAS_REP_CODE = "响应码";

	public static final String ALIAS_REP_MSG = "响应信息";

	public static final String ALIAS_PAY_ORDER_ID = "支付订单号";

	public static final String ALIAS_PAY_REQ_ID = "支付流水号";

	public static final String ALIAS_PAY_TYPE = "支付方式";


    //date formats
    public static final String FORMAT_CREATE_TIME = DATE_FORMAT;
    public static final String FORMAT_REQ_TIME = DATE_FORMAT;
    public static final String FORMAT_REP_TIME = DATE_FORMAT;
    public static final String FORMAT_LASTUPDATE_TIME = DATE_FORMAT;
    public static final String FORMAT_ORDER_DATE = DATE_FORMAT;

    //columns START
    //主键
    private Long id;
    //交易请求流水号
    private String reqId;
    //交易订单号
    private String ordId;
    //渠道名称
    private String chanelName;
    //渠道编号
    private String chanelNo;
    //订单金额
    private BigDecimal amt;
    //支付状态
    private Integer status;
    //创建时间
    private java.util.Date createTime;
    //版本号
    private Integer version;
    //备注
    private String remark;
    //请求时间
    private java.util.Date reqTime;
    //响应时间
    private java.util.Date repTime;
    //最后修改时间
    private java.util.Date lastupdateTime;
    //商户号
    private String mchId;
    //订单日期
    private java.util.Date orderDate;
    //通道响应码
    private String asileRepCode;
    //通道响应信息
    private String asileRepMsg;
    //响应码
    private String repCode;
    //响应信息
    private String repMsg;
    //支付订单号
    private String payOrderId;
    //支付流水号
    private String payReqId;
    //支付方式
    private String payType;
    //columns END

    public PayOrderEntity() {
    }

    public PayOrderEntity(
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

    public void setOrdId(java.lang.String value) {
        this.ordId = value;
    }

    public java.lang.String getOrdId() {
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

    public void setVersion(java.lang.Integer value) {
        this.version = value;
    }

    public java.lang.Integer getVersion() {
        return this.version;
    }

    public void setRemark(java.lang.String value) {
        this.remark = value;
    }

    public java.lang.String getRemark() {
        return this.remark;
    }

    public String getReqTimeString() {
        return DateConvertUtils.format(getReqTime(), FORMAT_REQ_TIME);
    }

    public void setReqTimeString(String value) {
        setReqTime(DateConvertUtils.parse(value, FORMAT_REQ_TIME, java.util.Date.class));
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
        setRepTime(DateConvertUtils.parse(value, FORMAT_REP_TIME, java.util.Date.class));
    }

    public void setRepTime(java.util.Date value) {
        this.repTime = value;
    }

    public java.util.Date getRepTime() {
        return this.repTime;
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

    public void setMchId(java.lang.String value) {
        this.mchId = value;
    }

    public java.lang.String getMchId() {
        return this.mchId;
    }

    public String getOrderDateString() {
        return DateConvertUtils.format(getOrderDate(), FORMAT_ORDER_DATE);
    }

    public void setOrderDateString(String value) {
        setOrderDate(DateConvertUtils.parse(value, FORMAT_ORDER_DATE, java.util.Date.class));
    }

    public void setOrderDate(java.util.Date value) {
        this.orderDate = value;
    }

    public java.util.Date getOrderDate() {
        return this.orderDate;
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
                .append("OrdId", getOrdId())
                .append("ChanelName", getChanelName())
                .append("ChanelNo", getChanelNo())
                .append("Amt", getAmt())
                .append("Status", getStatus())
                .append("CreateTime", getCreateTime())
                .append("Version", getVersion())
                .append("Remark", getRemark())
                .append("ReqTime", getReqTime())
                .append("RepTime", getRepTime())
                .append("LastupdateTime", getLastupdateTime())
                .append("MchId", getMchId())
                .append("OrderDate", getOrderDate())
                .append("AsileRepCode", getAsileRepCode())
                .append("AsileRepMsg", getAsileRepMsg())
                .append("RepCode", getRepCode())
                .append("RepMsg", getRepMsg())
                .append("PayOrderId", getPayOrderId())
                .append("PayReqId", getPayReqId())
                .append("PayType", getPayType())
                .toString();
    }

    public String toString(String separator) {
        StringBuffer sb = new StringBuffer();

        sb.append("主键:").append(id).append(separator);
        sb.append("交易请求流水号:").append(reqId).append(separator);
        sb.append("交易订单号:").append(ordId).append(separator);
        sb.append("渠道名称:").append(chanelName).append(separator);
        sb.append("渠道编号:").append(chanelNo).append(separator);
        sb.append("订单金额:").append(amt).append(separator);
        sb.append("支付状态:").append(status).append(separator);
        sb.append("创建时间:").append(getCreateTimeString()).append(separator);
        sb.append("版本号:").append(version).append(separator);
        sb.append("备注:").append(remark).append(separator);
        sb.append("请求时间:").append(getReqTimeString()).append(separator);
        sb.append("响应时间:").append(getRepTimeString()).append(separator);
        sb.append("最后修改时间:").append(getLastupdateTimeString()).append(separator);
        sb.append("商户号:").append(mchId).append(separator);
        sb.append("订单日期:").append(getOrderDateString()).append(separator);
        sb.append("通道响应码:").append(asileRepCode).append(separator);
        sb.append("通道响应信息:").append(asileRepMsg).append(separator);
        sb.append("响应码:").append(repCode).append(separator);
        sb.append("响应信息:").append(repMsg).append(separator);
        sb.append("支付订单号:").append(payOrderId).append(separator);
        sb.append("支付流水号:").append(payReqId).append(separator);
        sb.append("支付方式:").append(payType).append(separator);

        return sb.toString();
    }

    public int hashCode() {
        return new HashCodeBuilder()
                .append(getId())
                .toHashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof PayOrderEntity == false) return false;
        if (this == obj) return true;
        PayOrderEntity other = (PayOrderEntity) obj;
        return new EqualsBuilder()
                .append(getId(), other.getId())
                .isEquals();
    }

}

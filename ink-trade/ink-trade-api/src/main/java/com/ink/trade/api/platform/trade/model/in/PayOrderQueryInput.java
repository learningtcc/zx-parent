package com.ink.trade.api.platform.trade.model.in;

import java.io.Serializable;
import java.math.BigDecimal;

import com.ink.base.BaseQuery;

public class PayOrderQueryInput extends BaseQuery implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6402983943091847662L;
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getReqId() {
		return reqId;
	}
	public void setReqId(String reqId) {
		this.reqId = reqId;
	}
	public String getOrdId() {
		return ordId;
	}
	public void setOrdId(String ordId) {
		this.ordId = ordId;
	}
	public String getChanelName() {
		return chanelName;
	}
	public void setChanelName(String chanelName) {
		this.chanelName = chanelName;
	}
	public String getChanelNo() {
		return chanelNo;
	}
	public void setChanelNo(String chanelNo) {
		this.chanelNo = chanelNo;
	}
	public BigDecimal getAmt() {
		return amt;
	}
	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public java.util.Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public java.util.Date getReqTime() {
		return reqTime;
	}
	public void setReqTime(java.util.Date reqTime) {
		this.reqTime = reqTime;
	}
	public java.util.Date getRepTime() {
		return repTime;
	}
	public void setRepTime(java.util.Date repTime) {
		this.repTime = repTime;
	}
	public java.util.Date getLastupdateTime() {
		return lastupdateTime;
	}
	public void setLastupdateTime(java.util.Date lastupdateTime) {
		this.lastupdateTime = lastupdateTime;
	}
	public String getMchId() {
		return mchId;
	}
	public void setMchId(String mchId) {
		this.mchId = mchId;
	}
	public java.util.Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(java.util.Date orderDate) {
		this.orderDate = orderDate;
	}
	public String getAsileRepCode() {
		return asileRepCode;
	}
	public void setAsileRepCode(String asileRepCode) {
		this.asileRepCode = asileRepCode;
	}
	public String getAsileRepMsg() {
		return asileRepMsg;
	}
	public void setAsileRepMsg(String asileRepMsg) {
		this.asileRepMsg = asileRepMsg;
	}
	public String getRepCode() {
		return repCode;
	}
	public void setRepCode(String repCode) {
		this.repCode = repCode;
	}
	public String getRepMsg() {
		return repMsg;
	}
	public void setRepMsg(String repMsg) {
		this.repMsg = repMsg;
	}
	public String getPayOrderId() {
		return payOrderId;
	}
	public void setPayOrderId(String payOrderId) {
		this.payOrderId = payOrderId;
	}
	public String getPayReqId() {
		return payReqId;
	}
	public void setPayReqId(String payReqId) {
		this.payReqId = payReqId;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
    
    

}

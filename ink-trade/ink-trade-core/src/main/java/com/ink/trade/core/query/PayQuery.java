/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.trade.core.query;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import com.ink.base.BaseQuery;

/**
 * @author  zongtt
 * @version 1.0
 * @since 1.0
 */

public class PayQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    
	/** 主键 */
	private java.lang.Long id;
	/** 交易请求流水号 */
	private java.lang.String reqId;
	/** 交易订单号 */
	private java.lang.String ordId;
	/** 渠道名称 */
	private java.lang.String chanelName;
	/** 渠道编号 */
	private java.lang.String chanelNo;
	/** 订单金额 */
	private BigDecimal amt;
	/** 支付状态  0 创建订单1待支付2处理中3成功4失败5待退款6退款中7退款成功8退款失败 */
	private java.lang.Integer status;
	/** 创建时间 */
	private java.util.Date createTimeBegin;
	private java.util.Date createTimeEnd;
	/** 版本号 */
	private java.lang.Integer version;
	/** 备注 */
	private java.lang.String remark;
	/** 请求时间 */
	private java.util.Date reqTimeBegin;
	private java.util.Date reqTimeEnd;
	/** 响应时间 */
	private java.util.Date repTimeBegin;
	private java.util.Date repTimeEnd;
	/** 最后修改时间 */
	private java.util.Date lastupdateTimeBegin;
	private java.util.Date lastupdateTimeEnd;
	/** 商户号 */
	private java.lang.String mchId;
	/** 订单日期 */
	private java.util.Date orderDateBegin;
	private java.util.Date orderDateEnd;
	/** 通道响应码 */
	private java.lang.String asileRepCode;
	/** 通道响应信息 */
	private java.lang.String asileRepMsg;
	/** 响应码 */
	private java.lang.String repCode;
	/** 响应信息 */
	private java.lang.String repMsg;
	/** 支付订单号 */
	private java.lang.String payOrderId;
	/** 支付流水号 */
	private java.lang.String payReqId;
	/** 支付方式 */
	private java.lang.String payType;
	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.String getReqId() {
		return this.reqId;
	}
	
	public void setReqId(java.lang.String value) {
		this.reqId = value;
	}
	
	public java.lang.String getOrdId() {
		return this.ordId;
	}
	
	public void setOrdId(java.lang.String value) {
		this.ordId = value;
	}
	
	public java.lang.String getChanelName() {
		return this.chanelName;
	}
	
	public void setChanelName(java.lang.String value) {
		this.chanelName = value;
	}
	
	public java.lang.String getChanelNo() {
		return this.chanelNo;
	}
	
	public void setChanelNo(java.lang.String value) {
		this.chanelNo = value;
	}
	
	public BigDecimal getAmt() {
		return this.amt;
	}
	
	public void setAmt(BigDecimal value) {
		this.amt = value;
	}
	
	public java.lang.Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(java.lang.Integer value) {
		this.status = value;
	}
	
	public java.util.Date getCreateTimeBegin() {
		return this.createTimeBegin;
	}
	
	public void setCreateTimeBegin(java.util.Date value) {
		this.createTimeBegin = value;
	}	
	
	public java.util.Date getCreateTimeEnd() {
		return this.createTimeEnd;
	}
	
	public void setCreateTimeEnd(java.util.Date value) {
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.createTimeEnd = calendar.getTime();
		}else {
			this.createTimeEnd = value;
		}
	}
	
	public java.lang.Integer getVersion() {
		return this.version;
	}
	
	public void setVersion(java.lang.Integer value) {
		this.version = value;
	}
	
	public java.lang.String getRemark() {
		return this.remark;
	}
	
	public void setRemark(java.lang.String value) {
		this.remark = value;
	}
	
	public java.util.Date getReqTimeBegin() {
		return this.reqTimeBegin;
	}
	
	public void setReqTimeBegin(java.util.Date value) {
		this.reqTimeBegin = value;
	}	
	
	public java.util.Date getReqTimeEnd() {
		return this.reqTimeEnd;
	}
	
	public void setReqTimeEnd(java.util.Date value) {
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.reqTimeEnd = calendar.getTime();
		}else {
			this.reqTimeEnd = value;
		}
	}
	
	public java.util.Date getRepTimeBegin() {
		return this.repTimeBegin;
	}
	
	public void setRepTimeBegin(java.util.Date value) {
		this.repTimeBegin = value;
	}	
	
	public java.util.Date getRepTimeEnd() {
		return this.repTimeEnd;
	}
	
	public void setRepTimeEnd(java.util.Date value) {
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.repTimeEnd = calendar.getTime();
		}else {
			this.repTimeEnd = value;
		}
	}
	
	public java.util.Date getLastupdateTimeBegin() {
		return this.lastupdateTimeBegin;
	}
	
	public void setLastupdateTimeBegin(java.util.Date value) {
		this.lastupdateTimeBegin = value;
	}	
	
	public java.util.Date getLastupdateTimeEnd() {
		return this.lastupdateTimeEnd;
	}
	
	public void setLastupdateTimeEnd(java.util.Date value) {
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.lastupdateTimeEnd = calendar.getTime();
		}else {
			this.lastupdateTimeEnd = value;
		}
	}
	
	public java.lang.String getMchId() {
		return this.mchId;
	}
	
	public void setMchId(java.lang.String value) {
		this.mchId = value;
	}
	
	public java.util.Date getOrderDateBegin() {
		return this.orderDateBegin;
	}
	
	public void setOrderDateBegin(java.util.Date value) {
		this.orderDateBegin = value;
	}	
	
	public java.util.Date getOrderDateEnd() {
		return this.orderDateEnd;
	}
	
	public void setOrderDateEnd(java.util.Date value) {
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.orderDateEnd = calendar.getTime();
		}else {
			this.orderDateEnd = value;
		}
	}
	
	public java.lang.String getAsileRepCode() {
		return this.asileRepCode;
	}
	
	public void setAsileRepCode(java.lang.String value) {
		this.asileRepCode = value;
	}
	
	public java.lang.String getAsileRepMsg() {
		return this.asileRepMsg;
	}
	
	public void setAsileRepMsg(java.lang.String value) {
		this.asileRepMsg = value;
	}
	
	public java.lang.String getRepCode() {
		return this.repCode;
	}
	
	public void setRepCode(java.lang.String value) {
		this.repCode = value;
	}
	
	public java.lang.String getRepMsg() {
		return this.repMsg;
	}
	
	public void setRepMsg(java.lang.String value) {
		this.repMsg = value;
	}
	
	public java.lang.String getPayOrderId() {
		return this.payOrderId;
	}
	
	public void setPayOrderId(java.lang.String value) {
		this.payOrderId = value;
	}
	
	public java.lang.String getPayReqId() {
		return this.payReqId;
	}
	
	public void setPayReqId(java.lang.String value) {
		this.payReqId = value;
	}
	
	public java.lang.String getPayType() {
		return this.payType;
	}
	
	public void setPayType(java.lang.String value) {
		this.payType = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}


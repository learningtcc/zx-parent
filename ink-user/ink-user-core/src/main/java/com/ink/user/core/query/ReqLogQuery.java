/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.user.core.query;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Calendar;

import com.ink.base.BaseQuery;

/**
 * @author  zongtt
 * @version 1.0
 * @since 1.0
 */

public class ReqLogQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    
	/** 数据库表主键 */
	private java.lang.Long id;
	/** 创建时间 */
	private java.util.Date createTimeBegin;
	private java.util.Date createTimeEnd;
	/** 最后修改时间 */
	private java.util.Date lastUpdateTimeBegin;
	private java.util.Date lastUpdateTimeEnd;
	/** 商户编号 */
	private java.lang.Long mchId;
	/** 订单编号 */
	private java.lang.String ordId;
	/** 订单日期 */
	private java.util.Date ordDateBegin;
	private java.util.Date ordDateEnd;
	/** 订单时间 */
	private java.util.Date ordTimeBegin;
	private java.util.Date ordTimeEnd;
	/** 交易代码 */
	private java.lang.String txnCode;
	/** 交易名称 */
	private java.lang.String txnName;
	/** 客户号, 填写手机号 */
	private java.lang.String custId;
	/** 原始订单编号 */
	private java.lang.String oriOrdId;
	/** 原始订单日期 */
	private java.util.Date oriOrdDateBegin;
	private java.util.Date oriOrdDateEnd;
	/** 原始交易代码 */
	private java.lang.String oriTxnCode;
	/** 返回码 */
	private java.lang.String retCode;
	/** 返回描述 */
	private java.lang.String retMsg;
	/** 备注 */
	private java.lang.String memo;
	/** 删除标识 0-正常 1-删除 */
	private java.lang.Boolean delFlag;
	/** version */
	private java.lang.Integer version;
	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
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
	
	public java.util.Date getLastUpdateTimeBegin() {
		return this.lastUpdateTimeBegin;
	}
	
	public void setLastUpdateTimeBegin(java.util.Date value) {
		this.lastUpdateTimeBegin = value;
	}	
	
	public java.util.Date getLastUpdateTimeEnd() {
		return this.lastUpdateTimeEnd;
	}
	
	public void setLastUpdateTimeEnd(java.util.Date value) {
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.lastUpdateTimeEnd = calendar.getTime();
		}else {
			this.lastUpdateTimeEnd = value;
		}
	}
	
	public java.lang.Long getMchId() {
		return this.mchId;
	}
	
	public void setMchId(java.lang.Long value) {
		this.mchId = value;
	}
	
	public java.lang.String getOrdId() {
		return this.ordId;
	}
	
	public void setOrdId(java.lang.String value) {
		this.ordId = value;
	}
	
	public java.util.Date getOrdDateBegin() {
		return this.ordDateBegin;
	}
	
	public void setOrdDateBegin(java.util.Date value) {
		this.ordDateBegin = value;
	}	
	
	public java.util.Date getOrdDateEnd() {
		return this.ordDateEnd;
	}
	
	public void setOrdDateEnd(java.util.Date value) {
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.ordDateEnd = calendar.getTime();
		}else {
			this.ordDateEnd = value;
		}
	}
	
	public java.util.Date getOrdTimeBegin() {
		return this.ordTimeBegin;
	}
	
	public void setOrdTimeBegin(java.util.Date value) {
		this.ordTimeBegin = value;
	}	
	
	public java.util.Date getOrdTimeEnd() {
		return this.ordTimeEnd;
	}
	
	public void setOrdTimeEnd(java.util.Date value) {
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.ordTimeEnd = calendar.getTime();
		}else {
			this.ordTimeEnd = value;
		}
	}
	
	public java.lang.String getTxnCode() {
		return this.txnCode;
	}
	
	public void setTxnCode(java.lang.String value) {
		this.txnCode = value;
	}
	
	public java.lang.String getTxnName() {
		return this.txnName;
	}
	
	public void setTxnName(java.lang.String value) {
		this.txnName = value;
	}
	
	public java.lang.String getCustId() {
		return this.custId;
	}
	
	public void setCustId(java.lang.String value) {
		this.custId = value;
	}
	
	public java.lang.String getOriOrdId() {
		return this.oriOrdId;
	}
	
	public void setOriOrdId(java.lang.String value) {
		this.oriOrdId = value;
	}
	
	public java.util.Date getOriOrdDateBegin() {
		return this.oriOrdDateBegin;
	}
	
	public void setOriOrdDateBegin(java.util.Date value) {
		this.oriOrdDateBegin = value;
	}	
	
	public java.util.Date getOriOrdDateEnd() {
		return this.oriOrdDateEnd;
	}
	
	public void setOriOrdDateEnd(java.util.Date value) {
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.oriOrdDateEnd = calendar.getTime();
		}else {
			this.oriOrdDateEnd = value;
		}
	}
	
	public java.lang.String getOriTxnCode() {
		return this.oriTxnCode;
	}
	
	public void setOriTxnCode(java.lang.String value) {
		this.oriTxnCode = value;
	}
	
	public java.lang.String getRetCode() {
		return this.retCode;
	}
	
	public void setRetCode(java.lang.String value) {
		this.retCode = value;
	}
	
	public java.lang.String getRetMsg() {
		return this.retMsg;
	}
	
	public void setRetMsg(java.lang.String value) {
		this.retMsg = value;
	}
	
	public java.lang.String getMemo() {
		return this.memo;
	}
	
	public void setMemo(java.lang.String value) {
		this.memo = value;
	}
	
	public java.lang.Boolean getDelFlag() {
		return this.delFlag;
	}
	
	public void setDelFlag(java.lang.Boolean value) {
		this.delFlag = value;
	}
	
	public java.lang.Integer getVersion() {
		return this.version;
	}
	
	public void setVersion(java.lang.Integer value) {
		this.version = value;
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}


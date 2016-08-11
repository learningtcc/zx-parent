/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.trade.api.platform.trade.model.in;

import java.io.Serializable;
import java.util.Calendar;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.ink.base.BaseQuery;

/**
 * @author  zongtt
 * @version 1.0
 * @since 1.0
 */

public class AuthQueryInput extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    
	/** 主键 */
	private java.lang.Long id;
	/** 银行卡号 */
	private java.lang.String cardNo;
	/** 银行卡类型 */
	private java.lang.String cardType;
	/** 手机号 */
	private java.lang.String phoneNo;
	/** 证件类型 */
	private java.lang.String idType;
	/** 身份证号 */
	private java.lang.String idNo;
	/** 姓名 */
	private java.lang.String userName;
	/** 所属银行简码 */
	private java.lang.String bankShort;
	/** 所属银行 */
	private java.lang.String bankName;
	/** 状态 */
	private java.lang.Integer status;
	/** 版本号 */
	private java.lang.Integer version;
	/** 删除标识 */
	private java.lang.Integer isDelete;
	/** 备注 */
	private java.lang.String remark;
	/** 商户号 */
	private java.lang.String mchId;
	/** 最后修改时间 */
	private java.util.Date lastupdateTimeBegin;
	private java.util.Date lastupdateTimeEnd;
	/** 请求流水号 */
	private java.lang.String reqId;
	/** 客户号 */
	private java.lang.String userId;
	/** createTime */
	private java.util.Date createTimeBegin;
	private java.util.Date createTimeEnd;
	/** 支付类型 */
	private java.lang.String payType;
	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.String getCardNo() {
		return this.cardNo;
	}
	
	public void setCardNo(java.lang.String value) {
		this.cardNo = value;
	}
	
	public java.lang.String getCardType() {
		return this.cardType;
	}
	
	public void setCardType(java.lang.String value) {
		this.cardType = value;
	}
	
	public java.lang.String getPhoneNo() {
		return this.phoneNo;
	}
	
	public void setPhoneNo(java.lang.String value) {
		this.phoneNo = value;
	}
	
	public java.lang.String getIdType() {
		return this.idType;
	}
	
	public void setIdType(java.lang.String value) {
		this.idType = value;
	}
	
	public java.lang.String getIdNo() {
		return this.idNo;
	}
	
	public void setIdNo(java.lang.String value) {
		this.idNo = value;
	}
	
	public java.lang.String getUserName() {
		return this.userName;
	}
	
	public void setUserName(java.lang.String value) {
		this.userName = value;
	}
	
	public java.lang.String getBankShort() {
		return this.bankShort;
	}
	
	public void setBankShort(java.lang.String value) {
		this.bankShort = value;
	}
	
	public java.lang.String getBankName() {
		return this.bankName;
	}
	
	public void setBankName(java.lang.String value) {
		this.bankName = value;
	}
	
	public java.lang.Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(java.lang.Integer value) {
		this.status = value;
	}
	
	public java.lang.Integer getVersion() {
		return this.version;
	}
	
	public void setVersion(java.lang.Integer value) {
		this.version = value;
	}
	
	public java.lang.Integer getIsDelete() {
		return this.isDelete;
	}
	
	public void setIsDelete(java.lang.Integer value) {
		this.isDelete = value;
	}
	
	public java.lang.String getRemark() {
		return this.remark;
	}
	
	public void setRemark(java.lang.String value) {
		this.remark = value;
	}
	
	public java.lang.String getMchId() {
		return this.mchId;
	}
	
	public void setMchId(java.lang.String value) {
		this.mchId = value;
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
	
	public java.lang.String getReqId() {
		return this.reqId;
	}
	
	public void setReqId(java.lang.String value) {
		this.reqId = value;
	}
	
	public java.lang.String getUserId() {
		return this.userId;
	}
	
	public void setUserId(java.lang.String value) {
		this.userId = value;
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


/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.user.core.query;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.ink.base.BaseQuery;

/**
 * @author  zongtt
 * @version 1.0
 * @since 1.0
 */

public class TnsAuthQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    
	/** 数据库表主键 */
	private java.lang.Long id;
	/** 创建时间 */
	private java.util.Date createTimeBegin;
	private java.util.Date createTimeEnd;
	/** 最后修改时间 */
	private java.util.Date lastUpdateTimeBegin;
	private java.util.Date lastUpdateTimeEnd;
	/** 交易流水表主键 */
	private java.lang.Long tnsLogId;
	/** 交易代码 */
	private java.lang.String txnCode;
	/** 交易名称 */
	private java.lang.String txnName;
	/** 记帐日期 */
	private java.util.Date accDateBegin;
	private java.util.Date accDateEnd;
	/** 客户号, 填写手机号 */
	private java.lang.String custId;
	/** 姓名 */
	private java.lang.String custName;
	/** 客户类型 0-个人 1-商户 */
	private java.lang.Boolean custType;
	/** 子帐户号 */
	private java.lang.String sacId;
	/** 交易币种 */
	private java.lang.String cur;
	/** 授权金额 */
	private BigDecimal authAmt;
	/** 可授权余额 */
	private BigDecimal authBal;
	/** 授权状态 0-授权中 1-授权完成 */
	private java.lang.Boolean authStatus;
	/** 备注 */
	private java.lang.String memo;
	/** 删除标识 0-正常 1-删除 */
	private java.lang.Boolean delFlag;
	/** 预留字段1 */
	private java.lang.String filler1;
	/** 预留字段2 */
	private java.lang.String filler2;
	/** 预留字段3 */
	private java.lang.String filler3;
	/** version */
	private java.lang.Integer version;
	/** revFlg */
	private java.lang.String revFlg;
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
	
	public java.lang.Long getTnsLogId() {
		return this.tnsLogId;
	}
	
	public void setTnsLogId(java.lang.Long value) {
		this.tnsLogId = value;
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
	
	public java.util.Date getAccDateBegin() {
		return this.accDateBegin;
	}
	
	public void setAccDateBegin(java.util.Date value) {
		this.accDateBegin = value;
	}	
	
	public java.util.Date getAccDateEnd() {
		return this.accDateEnd;
	}
	
	public void setAccDateEnd(java.util.Date value) {
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.accDateEnd = calendar.getTime();
		}else {
			this.accDateEnd = value;
		}
	}
	
	public java.lang.String getCustId() {
		return this.custId;
	}
	
	public void setCustId(java.lang.String value) {
		this.custId = value;
	}
	
	public java.lang.String getCustName() {
		return this.custName;
	}
	
	public void setCustName(java.lang.String value) {
		this.custName = value;
	}
	
	public java.lang.Boolean getCustType() {
		return this.custType;
	}
	
	public void setCustType(java.lang.Boolean value) {
		this.custType = value;
	}
	
	public java.lang.String getSacId() {
		return this.sacId;
	}
	
	public void setSacId(java.lang.String value) {
		this.sacId = value;
	}
	
	public java.lang.String getCur() {
		return this.cur;
	}
	
	public void setCur(java.lang.String value) {
		this.cur = value;
	}
	
	public BigDecimal getAuthAmt() {
		return this.authAmt;
	}
	
	public void setAuthAmt(BigDecimal value) {
		this.authAmt = value;
	}
	
	public BigDecimal getAuthBal() {
		return this.authBal;
	}
	
	public void setAuthBal(BigDecimal value) {
		this.authBal = value;
	}
	
	public java.lang.Boolean getAuthStatus() {
		return this.authStatus;
	}
	
	public void setAuthStatus(java.lang.Boolean value) {
		this.authStatus = value;
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
	
	public java.lang.String getFiller1() {
		return this.filler1;
	}
	
	public void setFiller1(java.lang.String value) {
		this.filler1 = value;
	}
	
	public java.lang.String getFiller2() {
		return this.filler2;
	}
	
	public void setFiller2(java.lang.String value) {
		this.filler2 = value;
	}
	
	public java.lang.String getFiller3() {
		return this.filler3;
	}
	
	public void setFiller3(java.lang.String value) {
		this.filler3 = value;
	}
	
	public java.lang.Integer getVersion() {
		return this.version;
	}
	
	public void setVersion(java.lang.Integer value) {
		this.version = value;
	}
	
	public java.lang.String getRevFlg() {
		return this.revFlg;
	}
	
	public void setRevFlg(java.lang.String value) {
		this.revFlg = value;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}


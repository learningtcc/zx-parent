/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.user.core.query;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import com.ink.base.BaseQuery;

/**
 * @author  zongtt
 * @version 1.0
 * @since 1.0
 */

public class TnsTxnQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    
	/** 数据库表主键 */
	private java.lang.Long id;
	/** 创建时间 */
	private java.util.Date createTimeBegin;
	private java.util.Date createTimeEnd;
	/** 最后修改时间 */
	private java.util.Date lastUpdateTimeBegin;
	private java.util.Date lastUpdateTimeEnd;
	/** 交易代码 */
	private java.lang.String txnCode;
	/** 交易名称 */
	private java.lang.String txnName;
	/** 交易性质 1-充值 2-消费 3-资金转入 4-资金转出 5-汇入 6-汇出 7-手续费 8-取现 9-其他 */
	private Integer txnNature;
	/** 运行级别 1-联机 2-批处理 3-联机/批处理 */
	private java.lang.Integer runLv;
	/** 交易金额方向 D-借 C-贷 O-其他 */
	private java.lang.String dir;
	/** 冲正标识 0-不允许 1-允许 */
	private java.lang.Integer revAllowFlg;
	/** 手续费收取标识 0-不允许 1-允许 */
	private java.lang.Integer feeFlg;
	/** 最小交易金额 不含手续费 */
	private BigDecimal minAmt;
	/** 最大交易金额 不含手续费 */
	private BigDecimal maxAmt;
	/** 允许的交易渠道 每4位表示一种交易渠道，如果是全部，填"0000" */
	private java.lang.String channel;
	/** 默认子帐户顺序 每4位表示一种子帐户类型，表示交易默认借贷子帐户顺序 */
	private java.lang.String accOrder;
	/** 允许的账户状态, 从左到右依次填入, 0-全部 1-启用 2-停用 3-待审核 4-审核拒绝 9-注销 */
	private java.lang.String accStatus;
	/** 状态 1-启用 2-停用 9-注销 */
	private java.lang.Integer status;
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
	
	public Integer getTxnNature() {
		return this.txnNature;
	}
	
	public void setTxnNature(Integer value) {
		this.txnNature = value;
	}
	
	public java.lang.Integer getRunLv() {
		return this.runLv;
	}
	
	public void setRunLv(java.lang.Integer value) {
		this.runLv = value;
	}
	
	public java.lang.String getDir() {
		return this.dir;
	}
	
	public void setDir(java.lang.String value) {
		this.dir = value;
	}
	
	public java.lang.Integer getRevAllowFlg() {
		return this.revAllowFlg;
	}
	
	public void setRevAllowFlg(java.lang.Integer value) {
		this.revAllowFlg = value;
	}
	
	public java.lang.Integer getFeeFlg() {
		return this.feeFlg;
	}
	
	public void setFeeFlg(java.lang.Integer value) {
		this.feeFlg = value;
	}
	
	public BigDecimal getMinAmt() {
		return this.minAmt;
	}
	
	public void setMinAmt(BigDecimal value) {
		this.minAmt = value;
	}
	
	public BigDecimal getMaxAmt() {
		return this.maxAmt;
	}
	
	public void setMaxAmt(BigDecimal value) {
		this.maxAmt = value;
	}
	
	public java.lang.String getChannel() {
		return this.channel;
	}
	
	public void setChannel(java.lang.String value) {
		this.channel = value;
	}
	
	public java.lang.String getAccOrder() {
		return this.accOrder;
	}
	
	public void setAccOrder(java.lang.String value) {
		this.accOrder = value;
	}
	
	public java.lang.String getAccStatus() {
		return this.accStatus;
	}
	
	public void setAccStatus(java.lang.String value) {
		this.accStatus = value;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TnsTxnQuery [id=");
		builder.append(id);
		builder.append(", createTimeBegin=");
		builder.append(createTimeBegin);
		builder.append(", createTimeEnd=");
		builder.append(createTimeEnd);
		builder.append(", lastUpdateTimeBegin=");
		builder.append(lastUpdateTimeBegin);
		builder.append(", lastUpdateTimeEnd=");
		builder.append(lastUpdateTimeEnd);
		builder.append(", txnCode=");
		builder.append(txnCode);
		builder.append(", txnName=");
		builder.append(txnName);
		builder.append(", txnNature=");
		builder.append(txnNature);
		builder.append(", runLv=");
		builder.append(runLv);
		builder.append(", dir=");
		builder.append(dir);
		builder.append(", revAllowFlg=");
		builder.append(revAllowFlg);
		builder.append(", feeFlg=");
		builder.append(feeFlg);
		builder.append(", minAmt=");
		builder.append(minAmt);
		builder.append(", maxAmt=");
		builder.append(maxAmt);
		builder.append(", channel=");
		builder.append(channel);
		builder.append(", accOrder=");
		builder.append(accOrder);
		builder.append(", accStatus=");
		builder.append(accStatus);
		builder.append(", status=");
		builder.append(status);
		builder.append(", version=");
		builder.append(version);
		builder.append("]");
		return builder.toString();
	}
	

}


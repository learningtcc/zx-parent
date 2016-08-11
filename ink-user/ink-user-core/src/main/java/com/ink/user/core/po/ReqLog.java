/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.user.core.po;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.ink.base.BaseEntity;
import com.ink.base.util.DateConvertUtils;

/**
 * @author  zongtt
 * @version 1.0
 * @since 1.0
 */

public class ReqLog extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "ReqLog";
//	
//	public static final String ALIAS_ID = "数据库表主键";
//	
//	public static final String ALIAS_CREATE_TIME = "创建时间";
//	
//	public static final String ALIAS_LAST_UPDATE_TIME = "最后修改时间";
//	
//	public static final String ALIAS_MCH_ID = "商户编号";
//	
//	public static final String ALIAS_ORD_ID = "订单编号";
//	
//	public static final String ALIAS_ORD_DATE = "订单日期";
//	
//	public static final String ALIAS_ORD_TIME = "订单时间";
//	
//	public static final String ALIAS_TXN_CODE = "交易代码";
//	
//	public static final String ALIAS_TXN_NAME = "交易名称";
//	
//	public static final String ALIAS_CUST_ID = "客户号, 填写手机号";
//	
//	public static final String ALIAS_ORI_ORD_ID = "原始订单编号";
//	
//	public static final String ALIAS_ORI_ORD_DATE = "原始订单日期";
//	
//	public static final String ALIAS_ORI_TXN_CODE = "原始交易代码";
//	
//	public static final String ALIAS_RET_CODE = "返回码";
//	
//	public static final String ALIAS_RET_MSG = "返回描述";
//	
//	public static final String ALIAS_MEMO = "备注";
//	
//	public static final String ALIAS_DEL_FLAG = "删除标识 0-正常 1-删除";
//	
//	public static final String ALIAS_VERSION = "version";
//	
//	public static final String ALIAS_ACC_DEPOSIT_TYPE = "托管类型（我们：APT0000001，民生：CMBC000001）";
//	
	
	//date formats
	public static final String FORMAT_CREATE_TIME = DATE_TIME_FORMAT;
	public static final String FORMAT_LAST_UPDATE_TIME = DATE_TIME_FORMAT;
	public static final String FORMAT_ORD_DATE = DATE_TIME_FORMAT;
	public static final String FORMAT_ORD_TIME = DATE_TIME_FORMAT;
	public static final String FORMAT_ORI_ORD_DATE = DATE_TIME_FORMAT;
	
	//columns START
	//数据库表主键
	private Long id;
	//创建时间
	private java.util.Date createTime;
	//最后修改时间
	private java.util.Date lastUpdateTime;
	//商户编号
	private Long mchId;
	//订单编号
	private String ordId;
	//订单日期
	private java.util.Date ordDate;
	//订单时间
	private java.util.Date ordTime;
	//交易代码
	private String txnCode;
	//交易名称
	private String txnName;
	//客户号, 填写手机号
	private String custId;
	//原始订单编号
	private String oriOrdId;
	//原始订单日期
	private java.util.Date oriOrdDate;
	//原始交易代码
	private String oriTxnCode;
	//返回码
	private String retCode;
	//返回描述
	private String retMsg;
	//备注
	private String memo;
	//删除标识 0-正常 1-删除
	private Integer delFlag;
	//version
	private Integer version;
	//columns END

	public ReqLog(){
	}

	public ReqLog(
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
	public String getLastUpdateTimeString() {
		return DateConvertUtils.format(getLastUpdateTime(), FORMAT_LAST_UPDATE_TIME);
	}
	public void setLastUpdateTimeString(String value) {
		setLastUpdateTime(DateConvertUtils.parse(value, FORMAT_LAST_UPDATE_TIME,java.util.Date.class));
	}
	
	public void setLastUpdateTime(java.util.Date value) {
		this.lastUpdateTime = value;
	}
	
	public java.util.Date getLastUpdateTime() {
		return this.lastUpdateTime;
	}
	public void setMchId(java.lang.Long value) {
		this.mchId = value;
	}
	
	public java.lang.Long getMchId() {
		return this.mchId;
	}
	public void setOrdId(java.lang.String value) {
		this.ordId = value;
	}
	
	public java.lang.String getOrdId() {
		return this.ordId;
	}
	public String getOrdDateString() {
		return DateConvertUtils.format(getOrdDate(), FORMAT_ORD_DATE);
	}
	public void setOrdDateString(String value) {
		setOrdDate(DateConvertUtils.parse(value, FORMAT_ORD_DATE,java.util.Date.class));
	}
	
	public void setOrdDate(java.util.Date value) {
		this.ordDate = value;
	}
	
	public java.util.Date getOrdDate() {
		return this.ordDate;
	}
	public String getOrdTimeString() {
		return DateConvertUtils.format(getOrdTime(), FORMAT_ORD_TIME);
	}
	public void setOrdTimeString(String value) {
		setOrdTime(DateConvertUtils.parse(value, FORMAT_ORD_TIME,java.util.Date.class));
	}
	
	public void setOrdTime(java.util.Date value) {
		this.ordTime = value;
	}
	
	public java.util.Date getOrdTime() {
		return this.ordTime;
	}
	public void setTxnCode(java.lang.String value) {
		this.txnCode = value;
	}
	
	public java.lang.String getTxnCode() {
		return this.txnCode;
	}
	public void setTxnName(java.lang.String value) {
		this.txnName = value;
	}
	
	public java.lang.String getTxnName() {
		return this.txnName;
	}
	public void setCustId(java.lang.String value) {
		this.custId = value;
	}
	
	public java.lang.String getCustId() {
		return this.custId;
	}
	public void setOriOrdId(java.lang.String value) {
		this.oriOrdId = value;
	}
	
	public java.lang.String getOriOrdId() {
		return this.oriOrdId;
	}
	public String getOriOrdDateString() {
		return DateConvertUtils.format(getOriOrdDate(), FORMAT_ORI_ORD_DATE);
	}
	public void setOriOrdDateString(String value) {
		setOriOrdDate(DateConvertUtils.parse(value, FORMAT_ORI_ORD_DATE,java.util.Date.class));
	}
	
	public void setOriOrdDate(java.util.Date value) {
		this.oriOrdDate = value;
	}
	
	public java.util.Date getOriOrdDate() {
		return this.oriOrdDate;
	}
	public void setOriTxnCode(java.lang.String value) {
		this.oriTxnCode = value;
	}
	
	public java.lang.String getOriTxnCode() {
		return this.oriTxnCode;
	}
	public void setRetCode(java.lang.String value) {
		this.retCode = value;
	}
	
	public java.lang.String getRetCode() {
		return this.retCode;
	}
	public void setRetMsg(java.lang.String value) {
		this.retMsg = value;
	}
	
	public java.lang.String getRetMsg() {
		return this.retMsg;
	}
	public void setMemo(java.lang.String value) {
		this.memo = value;
	}
	
	public java.lang.String getMemo() {
		return this.memo;
	}
	
	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	public void setVersion(java.lang.Integer value) {
		this.version = value;
	}
	
	public java.lang.Integer getVersion() {
		return this.version;
	}
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("CreateTime",getCreateTime())
			.append("LastUpdateTime",getLastUpdateTime())
			.append("MchId",getMchId())
			.append("OrdId",getOrdId())
			.append("OrdDate",getOrdDate())
			.append("OrdTime",getOrdTime())
			.append("TxnCode",getTxnCode())
			.append("TxnName",getTxnName())
			.append("CustId",getCustId())
			.append("OriOrdId",getOriOrdId())
			.append("OriOrdDate",getOriOrdDate())
			.append("OriTxnCode",getOriTxnCode())
			.append("RetCode",getRetCode())
			.append("RetMsg",getRetMsg())
			.append("Memo",getMemo())
			.append("DelFlag",getDelFlag())
			.append("Version",getVersion())
			.toString();
	}
	
	public String toString(String separator) {
		StringBuffer sb = new StringBuffer();

			sb.append("数据库表主键:").append(id).append(separator);
			sb.append("创建时间:").append(getCreateTimeString()).append(separator);
			sb.append("最后修改时间:").append(getLastUpdateTimeString()).append(separator);
			sb.append("商户编号:").append(mchId).append(separator);
			sb.append("订单编号:").append(ordId).append(separator);
			sb.append("订单日期:").append(getOrdDateString()).append(separator);
			sb.append("订单时间:").append(getOrdTimeString()).append(separator);
			sb.append("交易代码:").append(txnCode).append(separator);
			sb.append("交易名称:").append(txnName).append(separator);
			sb.append("客户号, 填写手机号:").append(custId).append(separator);
			sb.append("原始订单编号:").append(oriOrdId).append(separator);
			sb.append("原始订单日期:").append(getOriOrdDateString()).append(separator);
			sb.append("原始交易代码:").append(oriTxnCode).append(separator);
			sb.append("返回码:").append(retCode).append(separator);
			sb.append("返回描述:").append(retMsg).append(separator);
			sb.append("备注:").append(memo).append(separator);
			sb.append("删除标识 0-正常 1-删除:").append(delFlag).append(separator);
			sb.append("version:").append(version).append(separator);
		
		return sb.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ReqLog == false) return false;
		if(this == obj) return true;
		ReqLog other = (ReqLog)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}


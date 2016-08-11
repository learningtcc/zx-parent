package com.ink.trade.core.po;

import java.util.Date;

public class FailerLog {
	//ID
	private Long id;
	//渠道名称
	private String asileName;
	//渠道编码
	private String asileCode;
	//创建时间
	private Date createTime;
	//请求码
	private String reqId;
	//交易码
	private String txnCode;
	//备注
	private String remark;
	
	private int isDelete;
	//银行编码
	private String bankCode;
	//银行名称
	private String bankName;
	private String routeBusinessType;
	
	public String getRouteBusinessType() {
		return routeBusinessType;
	}
	public void setRouteBusinessType(String routeBusinessType) {
		this.routeBusinessType = routeBusinessType;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAsileName() {
		return asileName;
	}
	public void setAsileName(String asileName) {
		this.asileName = asileName;
	}
	public String getAsileCode() {
		return asileCode;
	}
	public void setAsileCode(String asileCode) {
		this.asileCode = asileCode;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getReqId() {
		return reqId;
	}
	public void setReqId(String reqId) {
		this.reqId = reqId;
	}
	public String getTxnCode() {
		return txnCode;
	}
	public void setTxnCode(String txnCode) {
		this.txnCode = txnCode;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	@Override
	public String toString() {
		return "FailerLog [id=" + id + ", asileName=" + asileName
				+ ", asileCode=" + asileCode + ", createTime=" + createTime
				+ ", reqId=" + reqId + ", txnCode=" + txnCode + ", remark="
				+ remark + ", isDelete=" + isDelete + ", bankCode=" + bankCode
				+ ", bankName=" + bankName + "]";
	}
	
	
}

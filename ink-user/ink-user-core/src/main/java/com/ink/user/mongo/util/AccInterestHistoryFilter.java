package com.ink.user.mongo.util;

import org.mongodb.morphia.annotations.Property;

public class AccInterestHistoryFilter extends MongoPageFilter{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3294900217521232173L;
	/** 创建时间 */
	@MongoQueryKey(value = "create_time", acction = "greaterThanOrEq")
	private String beginCreateTime;
	
	@MongoQueryKey(value = "create_time", acction = "lessThanOrEq")
	private String endCreateTime;
	/**交易代码*/
	@MongoQueryKey(value = "txnCode")
	private String txnCode;
	@MongoQueryKey(value = "mchId")
	private String mchId;
	@MongoQueryKey(value = "custId")
	private String custId;
	@MongoQueryKey(value = "pacId")
	private String pacId;
	@MongoQueryKey(value = "sacType")
	private String sacType;
	//当前余额
	@MongoQueryKey(value = "curAmt")
	private String curAmt;
	//利息金额
	@MongoQueryKey(value = "interestAmt")
	private String interestAmt;
	//昨日余额
	@MongoQueryKey(value = "lasAmt")
	private String lasAmt;
	//0-成功，1-失败
	@MongoQueryKey(value = "state")
	private String state;
	@MongoQueryKey(value = "retCode")
	private String retCode;
	@MongoQueryKey(value = "retMsg")
	private String retMsg;
	public String getBeginCreateTime() {
		return beginCreateTime;
	}
	public void setBeginCreateTime(String beginCreateTime) {
		this.beginCreateTime = beginCreateTime;
	}
	public String getEndCreateTime() {
		return endCreateTime;
	}
	public void setEndCreateTime(String endCreateTime) {
		this.endCreateTime = endCreateTime;
	}
	public String getTxnCode() {
		return txnCode;
	}
	public void setTxnCode(String txnCode) {
		this.txnCode = txnCode;
	}
	public String getMchId() {
		return mchId;
	}
	public void setMchId(String mchId) {
		this.mchId = mchId;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getPacId() {
		return pacId;
	}
	public void setPacId(String pacId) {
		this.pacId = pacId;
	}
	public String getSacType() {
		return sacType;
	}
	public void setSacType(String sacType) {
		this.sacType = sacType;
	}
	public String getCurAmt() {
		return curAmt;
	}
	public void setCurAmt(String curAmt) {
		this.curAmt = curAmt;
	}
	public String getInterestAmt() {
		return interestAmt;
	}
	public void setInterestAmt(String interestAmt) {
		this.interestAmt = interestAmt;
	}
	public String getLasAmt() {
		return lasAmt;
	}
	public void setLasAmt(String lasAmt) {
		this.lasAmt = lasAmt;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getRetCode() {
		return retCode;
	}
	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}
	public String getRetMsg() {
		return retMsg;
	}
	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AccInterestHistoryFilter [beginCreateTime=");
		builder.append(beginCreateTime);
		builder.append(", endCreateTime=");
		builder.append(endCreateTime);
		builder.append(", txnCode=");
		builder.append(txnCode);
		builder.append(", mchId=");
		builder.append(mchId);
		builder.append(", custId=");
		builder.append(custId);
		builder.append(", pacId=");
		builder.append(pacId);
		builder.append(", sacType=");
		builder.append(sacType);
		builder.append(", curAmt=");
		builder.append(curAmt);
		builder.append(", interestAmt=");
		builder.append(interestAmt);
		builder.append(", lasAmt=");
		builder.append(lasAmt);
		builder.append(", state=");
		builder.append(state);
		builder.append(", retCode=");
		builder.append(retCode);
		builder.append(", retMsg=");
		builder.append(retMsg);
		builder.append("]");
		return builder.toString();
	}
	
	
}

package com.ink.user.core.po.mongo;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

@Entity(value = "acc_batch_log", noClassnameStored = true)
public class AccBatchLog implements Serializable{

	/** 
	 * @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = -8813333010520661459L;

	@Id
	private ObjectId _id;
	
	/** 创建时间 */
	@Property("create_time")
	private String createTime;
	
	/** 利息金额 */
	@Property("interest_amt")
	private String interestAmt;
	
	/**交易代码*/
	@Property("txn_code")
	private String txnCode;
	
	@Property("cust_id")
	private String custId;
	@Property("org_id")
	private String orgId;
	//报文信息，json字符串
	@Property("data")
	private String data;
	
	
	public AccBatchLog() {
		this.createTime = "";
		this.interestAmt = "";
		this.txnCode = "";
		this.custId = "";
		this.orgId = "";
		this.data = "";
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getInterestAmt() {
		return interestAmt;
	}

	public void setInterestAmt(String interestAmt) {
		this.interestAmt = interestAmt;
	}

	public String getTxnCode() {
		return txnCode;
	}

	public void setTxnCode(String txnCode) {
		this.txnCode = txnCode;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
}

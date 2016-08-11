package com.ink.user.core.po.mongo;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

/**
 * @Description: 体验金发放历史记录mongo
 * @author wanghao
 * @date 2016年5月5日 下午2:30:36
 */
@Entity(value="gold_grant_history",noClassnameStored=true)
public class GoldGrantHistory implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2357440525144720450L;

	@Id
	private ObjectId _id;
	/** 创建时间 */
	@Property("create_time")
	private String createTime;
	/**交易代码*/
	@Property("txn_code")
	private String txnCode;
	@Property("mch_id")
	private String mchId;
	@Property("cust_id")
	private String custId;
	@Property("pac_id")
	private String pacId;
	@Property("aac_type")
	private String sacType;
	//当前余额
	@Property("cur_amt")
	private String curAmt;
	//利息金额
	@Property("recovery_amt")
	private String recoveryAmt;
	//昨日余额
	@Property("las_amt")
	private String lasAmt;
	//0-成功，1-失败
	@Property("state")
	private String state;
	@Property("ret_code")
	private String retCode;
	@Property("ret_msg")
	private String retMsg;
	
	
	public GoldGrantHistory() {
		this.createTime = "";
		this.txnCode = "";
		this.mchId = "";
		this.custId = "";
		this.pacId = "";
		this.sacType = "";
		this.curAmt = "";
		this.recoveryAmt = "";
		this.lasAmt = "";
		this.state = "";
		this.retCode="";
		this.retMsg="";
	}
	
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
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
	public String getRecoveryAmt() {
		return recoveryAmt;
	}

	public void setRecoveryAmt(String recoveryAmt) {
		this.recoveryAmt = recoveryAmt;
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
}

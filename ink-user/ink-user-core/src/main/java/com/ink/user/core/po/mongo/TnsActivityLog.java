package com.ink.user.core.po.mongo;


import java.io.Serializable;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.utils.IndexDirection;

/**
 * User Experience fellowships granted logs
 * 
 * <b>
 * in  a POJO, java primitive types and the basic types default is not required to complete the reading and writing notes (unless annotatedTransient).
 *   mongoDB only supports four data types: Integer, Long, Double, String
 * morphia automatically mapped java primitive data types and String, an array of these types, as well as List, Set, Map to mongoDB in
 * </b>
 * 
 * @author jerry
 * 
 * @version 0.1.1
 *
 * time 2015/11/19
 *
 */
@Entity(value="tns_activity_log",noClassnameStored=true)
public class TnsActivityLog implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id private ObjectId _id;
	

	@Property("id")
	@Indexed(value=IndexDirection.ASC,unique=true)
	private Long id;
	
	@Property("amt")
	private String  amt; //java bigdecimal to  String which is mongodb morphia  support 
	@Property("mch_id")
    private String mchId;
	@Property("cust_id")
	private String custId;
	@Property("txn_code")
	private String txnCode;
	@Property("trade_date")
	private String tradeDate;
	//1-体验金发放;2-体验金回收
	@Property("status")
	private Integer status;
	@Property("ret_code")
	private String retCode;
	@Property("ret_msg")
	private String retMsg;
	
	
	
	public TnsActivityLog() {
		super();
		this.id =0l;
		this.amt = "";
		this.mchId = "";
		this.txnCode = "";
		this.status = 0;
		this.tradeDate="";
		this.retCode="";
		this.retMsg="";
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}

	public String getTxnCode() {
		return txnCode;
	}



	public void setTxnCode(String txnCode) {
		this.txnCode = txnCode;
	}



	public String getTradeDate() {
		return tradeDate;
	}



	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}

	public String getAmt() {
		return amt;
	}



	public void setAmt(String amt) {
		this.amt = amt;
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


	public Integer getStatus() {
		return status;
	}



	public void setStatus(Integer status) {
		this.status = status;
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

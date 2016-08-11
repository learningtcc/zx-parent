package com.ink.user.core.po.mongo;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.utils.IndexDirection;

@Entity(value = "tns_msg_log", noClassnameStored = true)
public class TnsMsgLog implements Serializable {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 3514739477689258640L;

	@Id
	private ObjectId _id;

	@Property("msg_id")
	@Indexed(value = IndexDirection.ASC, unique = true)
	private Long msgId;
	// 交易代码
	@Property("txn_code")
	private String txnCode;
	@Property("mch_id")
	private String mchId;
	@Property("cust_id")
	private String custId;
	@Property("ord_id")
	private String ordId;
	@Property("version")
	private String version;
	@Property("msg_type")
	private String msgType;
	@Property("sender")
	private String sender ;
	@Property("receiver")
	private String receiver;
	@Property("facility")
	private String facility;
	@Property("deposit_type")
	private String depositType;
	@Property("msg_req_date")
	private String msgReqDate;
	// json字符串
	@Property("data")
	private String data;
	// 返回码
	@Property("ret_code")
	private String retCode;
	// 返回描述
	@Property("ret_msg")
	private String retMsg;

	public TnsMsgLog() {
		this.msgId=001l;
		this.txnCode = "";
		this.mchId = "";
		this.custId="";
		this.ordId="";
		this.version = "";
		this.msgType = "";
		this.sender = "";
		this.receiver = "";
		this.facility="";
		this.depositType="";
		this.msgReqDate="";
		this.retCode = "";
		this.retMsg = "";
		this.data = "";
		this.retCode="";
		this.retMsg="";
	}
	

	public Long getMsgId() {
		return msgId;
	}

	public void setMsgId(Long msgId) {
		this.msgId = msgId;
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

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getFacility() {
		return facility;
	}

	public void setFacility(String facility) {
		this.facility = facility;
	}

	public String getDepositType() {
		return depositType;
	}

	public void setDepositType(String depositType) {
		this.depositType = depositType;
	}

	public String getMsgReqDate() {
		return msgReqDate;
	}

	public void setMsgReqDate(String msgReqDate) {
		this.msgReqDate = msgReqDate;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
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


	public String getCustId() {
		return custId;
	}


	public void setCustId(String custId) {
		this.custId = custId;
	}


	public String getOrdId() {
		return ordId;
	}


	public void setOrdId(String ordId) {
		this.ordId = ordId;
	}
}

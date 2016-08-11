package com.ink.user.api.model.out;

import java.io.Serializable;

/**
 * @Description: 查询商户信息
 * @author wanghao^_^
 * @date 2016年6月13日 下午3:31:16
 * @version V1.0
 */
public class QueryMchInfoOutput extends RetOutput implements Serializable {

	/**
	* @Fields serialVersionUID : TODO
	*/
	private static final long serialVersionUID = -3904989580481577843L;
	private String txnCode;
	private String mchId;
	private String custId;
	private String mchName;
	private String mcc;
	private String mchNature;
	private String orgCode;
	private String regNo;
	private String regAmt;
	private String regAdress;
	private String busAdress;
	private String contact;
	private String mblNo;
	private String telNo;
	private String email;
	private String status;


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

	public String getMchName() {
		return mchName;
	}

	public void setMchName(String mchName) {
		this.mchName = mchName;
	}

	public String getMcc() {
		return mcc;
	}

	public void setMcc(String mcc) {
		this.mcc = mcc;
	}

	public String getMchNature() {
		return mchNature;
	}

	public void setMchNature(String mchNature) {
		this.mchNature = mchNature;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getRegNo() {
		return regNo;
	}

	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}

	public String getRegAmt() {
		return regAmt;
	}

	public void setRegAmt(String regAmt) {
		this.regAmt = regAmt;
	}

	public String getRegAdress() {
		return regAdress;
	}

	public void setRegAdress(String regAdress) {
		this.regAdress = regAdress;
	}

	public String getBusAdress() {
		return busAdress;
	}

	public void setBusAdress(String busAdress) {
		this.busAdress = busAdress;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getMblNo() {
		return mblNo;
	}

	public void setMblNo(String mblNo) {
		this.mblNo = mblNo;
	}

	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("QueryMchInfoOutput [txnCode=");
		builder.append(txnCode);
		builder.append(", mchId=");
		builder.append(mchId);
		builder.append(", custId=");
		builder.append(custId);
		builder.append(", mchName=");
		builder.append(mchName);
		builder.append(", mcc=");
		builder.append(mcc);
		builder.append(", mchNature=");
		builder.append(mchNature);
		builder.append(", orgCode=");
		builder.append(orgCode);
		builder.append(", regNo=");
		builder.append(regNo);
		builder.append(", regAmt=");
		builder.append(regAmt);
		builder.append(", regAdress=");
		builder.append(regAdress);
		builder.append(", busAdress=");
		builder.append(busAdress);
		builder.append(", contact=");
		builder.append(contact);
		builder.append(", mblNo=");
		builder.append(mblNo);
		builder.append(", telNo=");
		builder.append(telNo);
		builder.append(", email=");
		builder.append(email);
		builder.append(", status=");
		builder.append(status);
		builder.append(", getRetCode()=");
		builder.append(getRetCode());
		builder.append(", getRetMsg()=");
		builder.append(getRetMsg());
		builder.append(", getOrdId()=");
		builder.append(getOrdId());
		builder.append(", getAccTnsId()=");
		builder.append(getAccTnsId());
		builder.append(", getTradeDate()=");
		builder.append(getTradeDate());
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append(", getClass()=");
		builder.append(getClass());
		builder.append(", hashCode()=");
		builder.append(hashCode());
		builder.append("]");
		return builder.toString();
	}

}

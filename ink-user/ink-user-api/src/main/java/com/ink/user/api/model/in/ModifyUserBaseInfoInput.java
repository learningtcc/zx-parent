package com.ink.user.api.model.in;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class ModifyUserBaseInfoInput implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5902528045021963929L;

	// 交易代码
	@NotNull
	@Length(max = 8)
	private String txnCode;
	// 商户编号（托管商户编号）
	@NotNull
	@Length(max = 24)
	private String mchId;
	// 客户编号
	@NotNull
	@Length(max = 40)
	private String custId;
	// 订单日期(YYYYMMDD)
	@NotNull
	@Length(max = 14)
	private String tradeDate;
	// 请求流水号
	@NotNull
	@Length(max = 64)
	private String ordId;
	// 手机号，验证条件，不能通过本接口修改
	@NotNull
	@Length(max = 11)
	private String mblNo;
	// 证件类型，验证条件，不能通过本接口修改
	@NotNull
	@Length(max = 2)
	private String idType;
	// 证件号，验证条件，不能通过本接口修改
	@NotNull
	@Length(max = 18)
	private String idNo;
	// 姓名
	@Length(max = 30)
	private String custName;
	// 性别
	@Length(max = 1)
	private String sex;
	// 生日
	@Length(max = 10)
	private String birthday;
	// 固定电话
	@Length(max = 20)
	private String telNo;
	// 邮箱
	@Length(max = 15)
	private String email;
	// 邮编
	@Length(max = 6)
	private String zipcode;
	// 地址
	private String address;

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

	public String getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}

	public String getOrdId() {
		return ordId;
	}

	public void setOrdId(String ordId) {
		this.ordId = ordId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
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

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMblNo() {
		return mblNo;
	}

	public void setMblNo(String mblNo) {
		this.mblNo = mblNo;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ModifyUserBaseInfoInput [txnCode=");
		builder.append(txnCode);
		builder.append(", mchId=");
		builder.append(mchId);
		builder.append(", custId=");
		builder.append(custId);
		builder.append(", tradeDate=");
		builder.append(tradeDate);
		builder.append(", ordId=");
		builder.append(ordId);
		builder.append(", mblNo=");
		builder.append(mblNo);
		builder.append(", idType=");
		builder.append(idType);
		builder.append(", idNo=");
		builder.append(idNo);
		builder.append(", custName=");
		builder.append(custName);
		builder.append(", sex=");
		builder.append(sex);
		builder.append(", birthday=");
		builder.append(birthday);
		builder.append(", telNo=");
		builder.append(telNo);
		builder.append(", email=");
		builder.append(email);
		builder.append(", zipcode=");
		builder.append(zipcode);
		builder.append(", address=");
		builder.append(address);
		builder.append("]");
		return builder.toString();
	}

}

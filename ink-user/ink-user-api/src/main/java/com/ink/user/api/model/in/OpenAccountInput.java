package com.ink.user.api.model.in;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/**
 * @Description: 开户
 * @author wanghao^_^
 * @date 2016年6月13日 下午4:15:42
 * @version V1.0
 */
public class OpenAccountInput implements Serializable {

	/**
	* @Fields serialVersionUID : TODO
	*/
	private static final long serialVersionUID = 8521622258021498578L;
	// 交易代码
	@NotNull
	@Length(max=8)
	private String txnCode;
	// 商户编号（托管商户编号）
	@NotNull
	@Length(max=24)
	private String mchId;
	// 客户编号
	@NotNull
	@Length(max=40)
	private String custId;
	// 请求流水号
	@NotNull
	@Length(max=64)
	private String ordId;
	// 客户类型
	@NotNull
	@Length(max=2)
	private String custType;
	// 交易时间
	@NotNull
	@Length(max=14)
	private String tradeDate;
	// 姓名
	@Length(max=30)
	private String custName;
	// 证件类型
	@Length(max=2)
	private String idType;
	// 证件号码
	@Length(max=18)
	private String idNo;
	// 联系手机号
	@NotNull
	@Length(max=11)
	private String mblNo;
	//性别
	@Length(max=1)
	private String sex;
	//生日
	@Length(max=10)
	private String birthday;
	//固定电话
	@Length(max=20)
	private String telNo;
	//邮箱
	@Length(max=15)
	private String email;
	//邮编
	@Length(max=6)
	private String zipcode;
	//地址
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
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
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
	public String getMblNo() {
		return mblNo;
	}
	public void setMblNo(String mblNo) {
		this.mblNo = mblNo;
	}
	public String getCustType() {
		return custType;
	}
	public void setCustType(String custType) {
		this.custType = custType;
	}
	public String getOrdId() {
		return ordId;
	}
	public void setOrdId(String ordId) {
		this.ordId = ordId;
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
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OpenAccountInput [txnCode=");
		builder.append(txnCode);
		builder.append(", mchId=");
		builder.append(mchId);
		builder.append(", custId=");
		builder.append(custId);
		builder.append(", ordId=");
		builder.append(ordId);
		builder.append(", custType=");
		builder.append(custType);
		builder.append(", tradeDate=");
		builder.append(tradeDate);
		builder.append(", custName=");
		builder.append(custName);
		builder.append(", idType=");
		builder.append(idType);
		builder.append(", idNo=");
		builder.append(idNo);
		builder.append(", mblNo=");
		builder.append(mblNo);
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

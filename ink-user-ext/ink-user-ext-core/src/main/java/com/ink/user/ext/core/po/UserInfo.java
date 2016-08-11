/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.user.ext.core.po;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.ink.base.BaseEntity;
import com.ink.base.util.DateConvertUtils;

/**
 * @author  zongtt
 * @version 1.0
 * @since 1.0
 */

public class UserInfo extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "用户信息";
//	
//	public static final String ALIAS_ID = "id";
//	
//	public static final String ALIAS_CUST_ID = "客户号";
//	
//	public static final String ALIAS_MCH_ID = "商户号";
//	
//	public static final String ALIAS_NAME = "名称";
//	
//	public static final String ALIAS_NICK_NAME = "昵称";
//	
//	public static final String ALIAS_PHONE = "手机号";
//	
//	public static final String ALIAS_ID_TYPE = "证件类型";
//	
//	public static final String ALIAS_ID_NO = "证件号";
//	
//	public static final String ALIAS_ADDRESS = "地址";
//	
//	public static final String ALIAS_SEX = "性别";
//	
//	public static final String ALIAS_EMAIL = "邮箱";
//	
//	public static final String ALIAS_ZIPCODE = "邮编";
//	
//	public static final String ALIAS_STATUS = "状态";
//	
//	public static final String ALIAS_CREATE_TIME = "创建时间";
//	
//	public static final String ALIAS_UPDATE_TIME = "更新时间";
//	
//	public static final String ALIAS_DEL_FLAG = "删除标识";
//	
	
	//date formats
	public static final String FORMAT_CREATE_TIME = DATE_TIME_FORMAT;
	public static final String FORMAT_UPDATE_TIME = DATE_TIME_FORMAT;
	
	//columns START
	//id
	private Long id;
	//客户号
	private Long custId;
	//商户号
	private Long mchId;
	//名称
	private String name;
	//昵称
	private String nickName;
	//手机号
	private String phone;
	//证件类型
	private String idType;
	//证件号
	private String idNo;
	//地址
	private String address;
	//性别
	private String sex;
	//邮箱
	private String email;
	//邮编
	private String zipcode;
	//状态
	private String status;
	//创建时间
	private Date createTime;
	//更新时间
	private Date updateTime;
	//删除标识
	private Boolean delFlag;
	//columns END

	public UserInfo(){
	}

	public UserInfo(
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
	public void setCustId(java.lang.Long value) {
		this.custId = value;
	}
	
	public java.lang.Long getCustId() {
		return this.custId;
	}
	public void setMchId(java.lang.Long value) {
		this.mchId = value;
	}
	
	public java.lang.Long getMchId() {
		return this.mchId;
	}
	public void setName(java.lang.String value) {
		this.name = value;
	}
	
	public java.lang.String getName() {
		return this.name;
	}
	public void setNickName(java.lang.String value) {
		this.nickName = value;
	}
	
	public java.lang.String getNickName() {
		return this.nickName;
	}
	public void setPhone(java.lang.String value) {
		this.phone = value;
	}
	
	public java.lang.String getPhone() {
		return this.phone;
	}
	public void setIdType(java.lang.String value) {
		this.idType = value;
	}
	
	public java.lang.String getIdType() {
		return this.idType;
	}
	public void setIdNo(java.lang.String value) {
		this.idNo = value;
	}
	
	public java.lang.String getIdNo() {
		return this.idNo;
	}
	public void setAddress(java.lang.String value) {
		this.address = value;
	}
	
	public java.lang.String getAddress() {
		return this.address;
	}
	public void setSex(java.lang.String value) {
		this.sex = value;
	}
	
	public java.lang.String getSex() {
		return this.sex;
	}
	public void setEmail(java.lang.String value) {
		this.email = value;
	}
	
	public java.lang.String getEmail() {
		return this.email;
	}
	public void setZipcode(java.lang.String value) {
		this.zipcode = value;
	}
	
	public java.lang.String getZipcode() {
		return this.zipcode;
	}
	
	public String getCreateTimeString() {
		return DateConvertUtils.format(getCreateTime(), FORMAT_CREATE_TIME);
	}
	public void setCreateTimeString(String value) {
		setCreateTime(DateConvertUtils.parse(value, FORMAT_CREATE_TIME,Date.class));
	}
	
	public void setCreateTime(Date value) {
		this.createTime = value;
	}
	
	public Date getCreateTime() {
		return this.createTime;
	}
	public String getUpdateTimeString() {
		return DateConvertUtils.format(getUpdateTime(), FORMAT_UPDATE_TIME);
	}
	public void setUpdateTimeString(String value) {
		setUpdateTime(DateConvertUtils.parse(value, FORMAT_UPDATE_TIME,Date.class));
	}
	
	public void setUpdateTime(Date value) {
		this.updateTime = value;
	}
	
	public Date getUpdateTime() {
		return this.updateTime;
	}
	public void setDelFlag(java.lang.Boolean value) {
		this.delFlag = value;
	}
	
	public java.lang.Boolean getDelFlag() {
		return this.delFlag;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserInfo [id=");
		builder.append(id);
		builder.append(", custId=");
		builder.append(custId);
		builder.append(", mchId=");
		builder.append(mchId);
		builder.append(", name=");
		builder.append(name);
		builder.append(", nickName=");
		builder.append(nickName);
		builder.append(", phone=");
		builder.append(phone);
		builder.append(", idType=");
		builder.append(idType);
		builder.append(", idNo=");
		builder.append(idNo);
		builder.append(", address=");
		builder.append(address);
		builder.append(", sex=");
		builder.append(sex);
		builder.append(", email=");
		builder.append(email);
		builder.append(", zipcode=");
		builder.append(zipcode);
		builder.append(", status=");
		builder.append(status);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append(", updateTime=");
		builder.append(updateTime);
		builder.append(", delFlag=");
		builder.append(delFlag);
		builder.append("]");
		return builder.toString();
	}

	public boolean equals(Object obj) {
		if(obj instanceof UserInfo == false) return false;
		if(this == obj) return true;
		UserInfo other = (UserInfo)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
	
	public String getPkValue() {
		if(getId() != null){
			return String.valueOf(getId());
		}
		
		return "";
	}
}


/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.user.ext.core.query;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Calendar;

import com.ink.base.BaseQuery;

/**
 * @author  zongtt
 * @version 1.0
 * @since 1.0
 */

public class UserInfoQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    
	/** id */
	private java.lang.Long id;
	/** 客户号 */
	private java.lang.Long custId;
	/** 商户号 */
	private java.lang.Long mchId;
	/** 名称 */
	private java.lang.String name;
	/** 昵称 */
	private java.lang.String nickName;
	/** 手机号 */
	private java.lang.String phone;
	/** 证件类型 */
	private java.lang.String idType;
	/** 证件号 */
	private java.lang.String idNo;
	/** 地址 */
	private java.lang.String address;
	/** 性别 */
	private java.lang.String sex;
	/** 邮箱 */
	private java.lang.String email;
	/** 邮编 */
	private java.lang.String zipcode;
	/** 状态 */
	private java.lang.Integer status;
	/** 创建时间 */
	private java.util.Date createTimeBegin;
	private java.util.Date createTimeEnd;
	/** 更新时间 */
	private java.util.Date updateTimeBegin;
	private java.util.Date updateTimeEnd;
	/** 删除标识 */
	private java.lang.Integer delFlag;
	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.Long getCustId() {
		return this.custId;
	}
	
	public void setCustId(java.lang.Long value) {
		this.custId = value;
	}
	
	public java.lang.Long getMchId() {
		return this.mchId;
	}
	
	public void setMchId(java.lang.Long value) {
		this.mchId = value;
	}
	
	public java.lang.String getName() {
		return this.name;
	}
	
	public void setName(java.lang.String value) {
		this.name = value;
	}
	
	public java.lang.String getNickName() {
		return this.nickName;
	}
	
	public void setNickName(java.lang.String value) {
		this.nickName = value;
	}
	
	public java.lang.String getPhone() {
		return this.phone;
	}
	
	public void setPhone(java.lang.String value) {
		this.phone = value;
	}
	
	public java.lang.String getIdType() {
		return this.idType;
	}
	
	public void setIdType(java.lang.String value) {
		this.idType = value;
	}
	
	public java.lang.String getIdNo() {
		return this.idNo;
	}
	
	public void setIdNo(java.lang.String value) {
		this.idNo = value;
	}
	
	public java.lang.String getAddress() {
		return this.address;
	}
	
	public void setAddress(java.lang.String value) {
		this.address = value;
	}
	
	public java.lang.String getSex() {
		return this.sex;
	}
	
	public void setSex(java.lang.String value) {
		this.sex = value;
	}
	
	public java.lang.String getEmail() {
		return this.email;
	}
	
	public void setEmail(java.lang.String value) {
		this.email = value;
	}
	
	public java.lang.String getZipcode() {
		return this.zipcode;
	}
	
	public void setZipcode(java.lang.String value) {
		this.zipcode = value;
	}
	
	public java.lang.Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(java.lang.Integer value) {
		this.status = value;
	}
	
	public java.util.Date getCreateTimeBegin() {
		return this.createTimeBegin;
	}
	
	public void setCreateTimeBegin(java.util.Date value) {
		this.createTimeBegin = value;
	}	
	
	public java.util.Date getCreateTimeEnd() {
		return this.createTimeEnd;
	}
	
	public void setCreateTimeEnd(java.util.Date value) {
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.createTimeEnd = calendar.getTime();
		}else {
			this.createTimeEnd = value;
		}
	}
	
	public java.util.Date getUpdateTimeBegin() {
		return this.updateTimeBegin;
	}
	
	public void setUpdateTimeBegin(java.util.Date value) {
		this.updateTimeBegin = value;
	}	
	
	public java.util.Date getUpdateTimeEnd() {
		return this.updateTimeEnd;
	}
	
	public void setUpdateTimeEnd(java.util.Date value) {
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.updateTimeEnd = calendar.getTime();
		}else {
			this.updateTimeEnd = value;
		}
	}
	
	public java.lang.Integer getDelFlag() {
		return this.delFlag;
	}
	
	public void setDelFlag(java.lang.Integer value) {
		this.delFlag = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}


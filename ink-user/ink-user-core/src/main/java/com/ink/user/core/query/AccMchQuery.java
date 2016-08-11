/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.user.core.query;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import com.ink.base.BaseQuery;

/**
 * @author  zongtt
 * @version 1.0
 * @since 1.0
 */

public class AccMchQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    
	/** 数据库表主键 */
	private Long id;
	/** 创建时间 */
	private Date createTimeBegin;
	private Date createTimeEnd;
	/** 最后修改时间 */
	private Date lastUpdateTimeBegin;
	private Date lastUpdateTimeEnd;
	/** 商户编号 */
	private Long mchId;
	/** 客户号, 填写手机号 作为商户的法人资金账户 */
	private String custId;
	/** 商户名称 */
	private String mchName;
	/** 商户类别表主键 */
	private Long accMccId;
	/** 商户类别 */
	private String mcc;
	/** 商户性质 1-国营 2-股份制 3-集体 4-中外合资、合作 5-外商独资 6-私营合伙 7-私营独资 8-个体 9-其他 */
	private Integer mchNature;
	/** 组织机构代码 */
	private String orgCode;
	/** 注册登记号 */
	private String regNo;
	/** 注册资本 */
	private BigDecimal regAmt;
	/** 注册地址 */
	private String regAddress;
	/** 营业地址 */
	private String busAddress;
	/** 业务联系人 */
	private String contact;
	/** 联系移动电话 */
	private String mblNo;
	/** 联系固定电话 */
	private String telNo;
	/** 联系邮箱 */
	private String email;
	/** 状态 1-启用 2-停用 3-待审核 4-审核拒绝 9-注销 */
	private Integer status;
	/** 删除标识 0-正常 1-删除 */
	private Integer delFlag;
	/** 预留字段1 */
	private String filler1;
	/** 预留字段2 */
	private String filler2;
	/** 预留字段3 */
	private String filler3;
	/** version */
	private Integer version;
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long value) {
		this.id = value;
	}
	
	public Date getCreateTimeBegin() {
		return this.createTimeBegin;
	}
	
	public void setCreateTimeBegin(Date value) {
		this.createTimeBegin = value;
	}	
	
	public Date getCreateTimeEnd() {
		return this.createTimeEnd;
	}
	
	public void setCreateTimeEnd(Date value) {
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.createTimeEnd = calendar.getTime();
		}else {
			this.createTimeEnd = value;
		}
	}
	
	public Date getLastUpdateTimeBegin() {
		return this.lastUpdateTimeBegin;
	}
	
	public void setLastUpdateTimeBegin(Date value) {
		this.lastUpdateTimeBegin = value;
	}	
	
	public Date getLastUpdateTimeEnd() {
		return this.lastUpdateTimeEnd;
	}
	
	public void setLastUpdateTimeEnd(Date value) {
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.lastUpdateTimeEnd = calendar.getTime();
		}else {
			this.lastUpdateTimeEnd = value;
		}
	}
	
	public Long getMchId() {
		return this.mchId;
	}
	
	public void setMchId(Long value) {
		this.mchId = value;
	}
	
	public String getCustId() {
		return this.custId;
	}
	
	public void setCustId(String value) {
		this.custId = value;
	}
	
	public String getMchName() {
		return this.mchName;
	}
	
	public void setMchName(String value) {
		this.mchName = value;
	}
	
	public Long getAccMccId() {
		return this.accMccId;
	}
	
	public void setAccMccId(Long value) {
		this.accMccId = value;
	}
	
	public String getMcc() {
		return this.mcc;
	}
	
	public void setMcc(String value) {
		this.mcc = value;
	}
	
	public String getOrgCode() {
		return this.orgCode;
	}
	
	public void setOrgCode(String value) {
		this.orgCode = value;
	}
	
	public String getRegNo() {
		return this.regNo;
	}
	
	public void setRegNo(String value) {
		this.regNo = value;
	}
	
	public BigDecimal getRegAmt() {
		return this.regAmt;
	}
	
	public void setRegAmt(BigDecimal value) {
		this.regAmt = value;
	}
	
	public String getRegAddress() {
		return this.regAddress;
	}
	
	public void setRegAddress(String value) {
		this.regAddress = value;
	}
	
	public String getBusAddress() {
		return this.busAddress;
	}
	
	public void setBusAddress(String value) {
		this.busAddress = value;
	}
	
	public String getContact() {
		return this.contact;
	}
	
	public void setContact(String value) {
		this.contact = value;
	}
	
	public String getMblNo() {
		return this.mblNo;
	}
	
	public void setMblNo(String value) {
		this.mblNo = value;
	}
	
	public String getTelNo() {
		return this.telNo;
	}
	
	public void setTelNo(String value) {
		this.telNo = value;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String value) {
		this.email = value;
	}
	
	public String getFiller1() {
		return this.filler1;
	}
	
	public void setFiller1(String value) {
		this.filler1 = value;
	}
	
	public String getFiller2() {
		return this.filler2;
	}
	
	public void setFiller2(String value) {
		this.filler2 = value;
	}
	
	public String getFiller3() {
		return this.filler3;
	}
	
	public void setFiller3(String value) {
		this.filler3 = value;
	}
	
	public Integer getVersion() {
		return this.version;
	}
	
	public void setVersion(Integer value) {
		this.version = value;
	}

	public Integer getMchNature() {
		return mchNature;
	}

	public void setMchNature(Integer mchNature) {
		this.mchNature = mchNature;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AccMchQuery [id=");
		builder.append(id);
		builder.append(", createTimeBegin=");
		builder.append(createTimeBegin);
		builder.append(", createTimeEnd=");
		builder.append(createTimeEnd);
		builder.append(", lastUpdateTimeBegin=");
		builder.append(lastUpdateTimeBegin);
		builder.append(", lastUpdateTimeEnd=");
		builder.append(lastUpdateTimeEnd);
		builder.append(", mchId=");
		builder.append(mchId);
		builder.append(", custId=");
		builder.append(custId);
		builder.append(", mchName=");
		builder.append(mchName);
		builder.append(", accMccId=");
		builder.append(accMccId);
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
		builder.append(", regAddress=");
		builder.append(regAddress);
		builder.append(", busAddress=");
		builder.append(busAddress);
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
		builder.append(", delFlag=");
		builder.append(delFlag);
		builder.append(", filler1=");
		builder.append(filler1);
		builder.append(", filler2=");
		builder.append(filler2);
		builder.append(", filler3=");
		builder.append(filler3);
		builder.append(", version=");
		builder.append(version);
		builder.append("]");
		return builder.toString();
	}

}


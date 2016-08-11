///**    
// * <p> Copyright (c) 2015-2025 银客网.</p>
// * <p>All Rights Reserved. 保留所有权利. </p>
// */
//package com.ink.user.core.query;
//
//import org.apache.commons.lang.builder.ToStringBuilder;
//import org.apache.commons.lang.builder.ToStringStyle;
//
//import java.io.Serializable;
//import java.util.Calendar;
//
//import com.ink.base.BaseQuery;
//
///**
// * @author  zongtt
// * @version 1.0
// * @since 1.0
// */
//
//public class AccOrgQuery extends BaseQuery implements Serializable {
//    private static final long serialVersionUID = 3148176768559230877L;
//    
//	/** 数据库表主键 */
//	private java.lang.Long id;
//	/** 创建人 */
//	private java.lang.String owner;
//	/** 创建人所属机构 */
//	private java.lang.String ownerGroup;
//	/** 创建时间 */
//	private java.util.Date createTimeBegin;
//	private java.util.Date createTimeEnd;
//	/** 最后修改时间 */
//	private java.util.Date lastUpdateTimeBegin;
//	private java.util.Date lastUpdateTimeEnd;
//	/** 机构编号 */
//	private java.lang.Long orgId;
//	/** 机构名称 */
//	private java.lang.String orgName;
//	/** 机构简称 */
//	private java.lang.String orgSname;
//	/** 机构类型 1-银行 2-支付机构 */
//	private java.lang.Boolean orgType;
//	/** 业务联系人 */
//	private java.lang.String contact;
//	/** 联系移动电话 */
//	private java.lang.String mblNo;
//	/** 联系固定电话 */
//	private java.lang.String telNo;
//	/** 联系邮箱 */
//	private java.lang.String email;
//	/** 状态 1-启用 2-停用 3-待审核 4-审核拒绝 9-注销 */
//	private java.lang.Boolean status;
//	/** 删除标识 0-正常 1-删除 */
//	private java.lang.Boolean delFlag;
//	/** 预留字段1 */
//	private java.lang.String filler1;
//	/** 预留字段2 */
//	private java.lang.String filler2;
//	/** 预留字段3 */
//	private java.lang.String filler3;
//	/** version */
//	private java.lang.Integer version;
//	public java.lang.Long getId() {
//		return this.id;
//	}
//	
//	public void setId(java.lang.Long value) {
//		this.id = value;
//	}
//	
//	public java.lang.String getOwner() {
//		return this.owner;
//	}
//	
//	public void setOwner(java.lang.String value) {
//		this.owner = value;
//	}
//	
//	public java.lang.String getOwnerGroup() {
//		return this.ownerGroup;
//	}
//	
//	public void setOwnerGroup(java.lang.String value) {
//		this.ownerGroup = value;
//	}
//	
//	public java.util.Date getCreateTimeBegin() {
//		return this.createTimeBegin;
//	}
//	
//	public void setCreateTimeBegin(java.util.Date value) {
//		this.createTimeBegin = value;
//	}	
//	
//	public java.util.Date getCreateTimeEnd() {
//		return this.createTimeEnd;
//	}
//	
//	public void setCreateTimeEnd(java.util.Date value) {
//		if(value != null){
//			Calendar calendar = Calendar.getInstance();
//			calendar.setTime(value);
//			calendar.add(Calendar.DAY_OF_MONTH, 1);
//			this.createTimeEnd = calendar.getTime();
//		}else {
//			this.createTimeEnd = value;
//		}
//	}
//	
//	public java.util.Date getLastUpdateTimeBegin() {
//		return this.lastUpdateTimeBegin;
//	}
//	
//	public void setLastUpdateTimeBegin(java.util.Date value) {
//		this.lastUpdateTimeBegin = value;
//	}	
//	
//	public java.util.Date getLastUpdateTimeEnd() {
//		return this.lastUpdateTimeEnd;
//	}
//	
//	public void setLastUpdateTimeEnd(java.util.Date value) {
//		if(value != null){
//			Calendar calendar = Calendar.getInstance();
//			calendar.setTime(value);
//			calendar.add(Calendar.DAY_OF_MONTH, 1);
//			this.lastUpdateTimeEnd = calendar.getTime();
//		}else {
//			this.lastUpdateTimeEnd = value;
//		}
//	}
//	
//	public java.lang.Long getOrgId() {
//		return this.orgId;
//	}
//	
//	public void setOrgId(java.lang.Long value) {
//		this.orgId = value;
//	}
//	
//	public java.lang.String getOrgName() {
//		return this.orgName;
//	}
//	
//	public void setOrgName(java.lang.String value) {
//		this.orgName = value;
//	}
//	
//	public java.lang.String getOrgSname() {
//		return this.orgSname;
//	}
//	
//	public void setOrgSname(java.lang.String value) {
//		this.orgSname = value;
//	}
//	
//	public java.lang.Boolean getOrgType() {
//		return this.orgType;
//	}
//	
//	public void setOrgType(java.lang.Boolean value) {
//		this.orgType = value;
//	}
//	
//	public java.lang.String getContact() {
//		return this.contact;
//	}
//	
//	public void setContact(java.lang.String value) {
//		this.contact = value;
//	}
//	
//	public java.lang.String getMblNo() {
//		return this.mblNo;
//	}
//	
//	public void setMblNo(java.lang.String value) {
//		this.mblNo = value;
//	}
//	
//	public java.lang.String getTelNo() {
//		return this.telNo;
//	}
//	
//	public void setTelNo(java.lang.String value) {
//		this.telNo = value;
//	}
//	
//	public java.lang.String getEmail() {
//		return this.email;
//	}
//	
//	public void setEmail(java.lang.String value) {
//		this.email = value;
//	}
//	
//	public java.lang.Boolean getStatus() {
//		return this.status;
//	}
//	
//	public void setStatus(java.lang.Boolean value) {
//		this.status = value;
//	}
//	
//	public java.lang.Boolean getDelFlag() {
//		return this.delFlag;
//	}
//	
//	public void setDelFlag(java.lang.Boolean value) {
//		this.delFlag = value;
//	}
//	
//	public java.lang.String getFiller1() {
//		return this.filler1;
//	}
//	
//	public void setFiller1(java.lang.String value) {
//		this.filler1 = value;
//	}
//	
//	public java.lang.String getFiller2() {
//		return this.filler2;
//	}
//	
//	public void setFiller2(java.lang.String value) {
//		this.filler2 = value;
//	}
//	
//	public java.lang.String getFiller3() {
//		return this.filler3;
//	}
//	
//	public void setFiller3(java.lang.String value) {
//		this.filler3 = value;
//	}
//	
//	public java.lang.Integer getVersion() {
//		return this.version;
//	}
//	
//	public void setVersion(java.lang.Integer value) {
//		this.version = value;
//	}
//	
//
//	public String toString() {
//		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
//	}
//	
//}
//

package com.ink.user.core.po;

import java.io.Serializable;
import java.util.Date;

public class AccCustProof implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -993048867045658502L;

	/** 数据库表主键 */
    private Long id;
    
    /** 创建时间 */
    private Date createTime;

    /** 最后修改时间 */
    private Date lastUpdateTime;
    
    /** 客户ID */
    private String custId;
    
    /** 平台客户号 */
    private Long uid;
    
    /** 修改前的证件类型 */
    private String beforeIdType;
    
    /** 修改前的证件号 */
    private String beforeIdNo;
    
    /** 修改后的证件类型 */
    private String idType;
    
    /** 修改后的证件号 */
    private String idNo;
    
    /** 修改前的银行预留手机号 */
    private String beforeMblNo;
    
    /** 修改后的银行预留手机号 */
    private String mblNo;
    
    /** 预留字段1 */
    private String filler1;
    
    /** 预留字段2 */
    private String filler2;
    
    /** 预留字段3 */
    private String filler3;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getBeforeIdType() {
		return beforeIdType;
	}

	public void setBeforeIdType(String beforeIdType) {
		this.beforeIdType = beforeIdType;
	}

	public String getBeforeIdNo() {
		return beforeIdNo;
	}

	public void setBeforeIdNo(String beforeIdNo) {
		this.beforeIdNo = beforeIdNo;
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


	public String getFiller1() {
		return filler1;
	}

	public void setFiller1(String filler1) {
		this.filler1 = filler1;
	}

	public String getFiller2() {
		return filler2;
	}

	public void setFiller2(String filler2) {
		this.filler2 = filler2;
	}

	public String getFiller3() {
		return filler3;
	}

	public void setFiller3(String filler3) {
		this.filler3 = filler3;
	}

	public String getBeforeMblNo() {
		return beforeMblNo;
	}

	public void setBeforeMblNo(String beforeMblNo) {
		this.beforeMblNo = beforeMblNo;
	}

	public String getMblNo() {
		return mblNo;
	}

	public void setMblNo(String mblNo) {
		this.mblNo = mblNo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AccCustProof [id=");
		builder.append(id);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append(", lastUpdateTime=");
		builder.append(lastUpdateTime);
		builder.append(", custId=");
		builder.append(custId);
		builder.append(", uid=");
		builder.append(uid);
		builder.append(", beforeIdType=");
		builder.append(beforeIdType);
		builder.append(", beforeIdNo=");
		builder.append(beforeIdNo);
		builder.append(", idType=");
		builder.append(idType);
		builder.append(", idNo=");
		builder.append(idNo);
		builder.append(", beforeMblNo=");
		builder.append(beforeMblNo);
		builder.append(", mblNo=");
		builder.append(mblNo);
		builder.append(", filler1=");
		builder.append(filler1);
		builder.append(", filler2=");
		builder.append(filler2);
		builder.append(", filler3=");
		builder.append(filler3);
		builder.append("]");
		return builder.toString();
	}

    
}

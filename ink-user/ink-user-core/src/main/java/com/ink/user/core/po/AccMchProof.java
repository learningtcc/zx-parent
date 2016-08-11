package com.ink.user.core.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AccMchProof implements Serializable{
	
	/**
	* @Fields serialVersionUID : TODO
	*/
	private static final long serialVersionUID = -1473854424146525929L;

	private Long id;

	private Long mchId;

	private String sacType;

	private BigDecimal amt;

	private Date createTime;

	private Date lastUpdateTime;

	private String txnCode;

	private Long sacId;

	private Long pacId;
	
	private String dir;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMchId() {
		return mchId;
	}

	public void setMchId(Long mchId) {
		this.mchId = mchId;
	}

	public String getSacType() {
		return sacType;
	}

	public void setSacType(String sacType) {
		this.sacType = sacType;
	}

	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
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

	public String getTxnCode() {
		return txnCode;
	}

	public void setTxnCode(String txnCode) {
		this.txnCode = txnCode;
	}

	public Long getSacId() {
		return sacId;
	}

	public void setSacId(Long sacId) {
		this.sacId = sacId;
	}

	public Long getPacId() {
		return pacId;
	}

	public void setPacId(Long pacId) {
		this.pacId = pacId;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}
}
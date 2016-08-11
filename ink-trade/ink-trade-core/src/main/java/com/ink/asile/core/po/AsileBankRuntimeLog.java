package com.ink.asile.core.po;

import java.util.Date;

public class AsileBankRuntimeLog {
	/**  */
	private Long id;

	/**  */
	private String asileName;

	/**  */
	private String asileCode;

	/**  */
	private String bankName;

	/**  */
	private String bankCode;

	/**  */
	private String asileProductCode;

	/**  */
	private String asileProductName;

	/**  */
	private String remark;

	/**  */
	private Date createTime;

	/**  */
	private Long createrId;

	/**  */
	private String createrName;

	/**  */
	private Date updateTime;

	/**  */
	private Long updaterId;

	/**  */
	private String updaterName;

	/**  */
	private String isDelete;

	/**  */
	private Integer version;

	/**  */
	private Integer offOnLine;

	/**  */
	private String changeWhy;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAsileName() {
		return asileName;
	}

	public void setAsileName(String asileName) {
		this.asileName = asileName == null ? null : asileName.trim();
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName == null ? null : bankName.trim();
	}

	public String getAsileCode() {
		return asileCode;
	}

	public void setAsileCode(String asileCode) {
		this.asileCode = asileCode;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getAsileProductCode() {
		return asileProductCode;
	}

	public void setAsileProductCode(String asileProductCode) {
		this.asileProductCode = asileProductCode == null ? null
				: asileProductCode.trim();
	}

	public String getAsileProductName() {
		return asileProductName;
	}

	public void setAsileProductName(String asileProductName) {
		this.asileProductName = asileProductName == null ? null
				: asileProductName.trim();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getCreaterId() {
		return createrId;
	}

	public void setCreaterId(Long createrId) {
		this.createrId = createrId;
	}

	public String getCreaterName() {
		return createrName;
	}

	public void setCreaterName(String createrName) {
		this.createrName = createrName == null ? null : createrName.trim();
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getUpdaterId() {
		return updaterId;
	}

	public void setUpdaterId(Long updaterId) {
		this.updaterId = updaterId;
	}

	public String getUpdaterName() {
		return updaterName;
	}

	public void setUpdaterName(String updaterName) {
		this.updaterName = updaterName == null ? null : updaterName.trim();
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete == null ? null : isDelete.trim();
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getOffOnLine() {
		return offOnLine;
	}

	public void setOffOnLine(Integer offOnLine) {
		this.offOnLine = offOnLine;
	}

	public String getChangeWhy() {
		return changeWhy;
	}

	public void setChangeWhy(String changeWhy) {
		this.changeWhy = changeWhy == null ? null : changeWhy.trim();
	}
}
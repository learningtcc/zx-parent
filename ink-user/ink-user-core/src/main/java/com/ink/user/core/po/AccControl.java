//package com.ink.user.core.po;
//
//import java.io.Serializable;
//import java.util.Date;
//
//import org.springframework.format.annotation.DateTimeFormat;
//
//public class AccControl implements Serializable {
//	/**
//	 * @Fields serialVersionUID : TODO
//	 */
//	private static final long serialVersionUID = -205817647146155962L;
//
//	/** 数据库表主键 */
//	private String id;
//
//	/** 版本号 */
//	private Integer version;
//
//	/** 开户日期 */
//	@DateTimeFormat(pattern="yyyy-MM-dd")
//	private Date openDate;
//
//	/** 销户日期 */
//	@DateTimeFormat(pattern="yyyy-MM-dd")
//	private Date closeDate;
//
//	/** 状态 1-启用 2-停用 9-注销 */
//	private Integer status;
//
//	/** 删除标识 0-正常 1-删除 */
//	private String delFlag;
//
//	/** 创建人 */
//	private String owner;
//
//	/** 创建人所属机构 */
//	private String ownerGroup;
//
//	/** 创建时间 */
//	private Date createTime;
//
//	/** 最后修改时间 */
//	private Date lastUpdateTime;
//
//	/**  */
//	private String custId;
//
//	/**  */
//	private Long mchId;
//
//	/**  */
//	private String txnCode;
//
//	@Override
//	public String toString() {
//		return "AccControl [id=" + id + ", version=" + version + ", openDate="
//				+ openDate + ", closeDate=" + closeDate + ", status=" + status
//				+ ", delFlag=" + delFlag + ", owner=" + owner + ", ownerGroup="
//				+ ownerGroup + ", createTime=" + createTime
//				+ ", lastUpdateTime=" + lastUpdateTime + ", custId=" + custId
//				+ ", mchId=" + mchId + ", txnCode=" + txnCode + "]";
//	}
//
//	public String getId() {
//		return id;
//	}
//
//	public void setId(String id) {
//		this.id = id;
//	}
//
//	public Integer getVersion() {
//		return version;
//	}
//
//	public void setVersion(Integer version) {
//		this.version = version;
//	}
//
//	public Date getOpenDate() {
//		return openDate;
//	}
//
//	public void setOpenDate(Date openDate) {
//		this.openDate = openDate;
//	}
//
//	public Date getCloseDate() {
//		return closeDate;
//	}
//
//	public void setCloseDate(Date closeDate) {
//		this.closeDate = closeDate;
//	}
//
//	public Integer getStatus() {
//		return status;
//	}
//
//	public void setStatus(Integer status) {
//		this.status = status;
//	}
//
//	public String getDelFlag() {
//		return delFlag;
//	}
//
//	public void setDelFlag(String delFlag) {
//		this.delFlag = delFlag;
//	}
//
//	public String getOwner() {
//		return owner;
//	}
//
//	public void setOwner(String owner) {
//		this.owner = owner == null ? null : owner.trim();
//	}
//
//	public String getOwnerGroup() {
//		return ownerGroup;
//	}
//
//	public void setOwnerGroup(String ownerGroup) {
//		this.ownerGroup = ownerGroup == null ? null : ownerGroup.trim();
//	}
//
//	public Date getCreateTime() {
//		return createTime;
//	}
//
//	public void setCreateTime(Date createTime) {
//		this.createTime = createTime;
//	}
//
//	public Date getLastUpdateTime() {
//		return lastUpdateTime;
//	}
//
//	public void setLastUpdateTime(Date lastUpdateTime) {
//		this.lastUpdateTime = lastUpdateTime;
//	}
//
//	public String getCustId() {
//		return custId;
//	}
//
//	public void setCustId(String custId) {
//		this.custId = custId == null ? null : custId.trim();
//	}
//
//	public Long getMchId() {
//		return mchId;
//	}
//
//	public void setMchId(Long mchId) {
//		this.mchId = mchId;
//	}
//
//	public String getTxnCode() {
//		return txnCode;
//	}
//
//	public void setTxnCode(String txnCode) {
//		this.txnCode = txnCode;
//	}
//}
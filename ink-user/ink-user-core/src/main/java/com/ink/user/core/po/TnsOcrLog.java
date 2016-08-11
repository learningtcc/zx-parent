package com.ink.user.core.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TnsOcrLog implements Serializable {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 8255904164890261082L;

	/** 数据库表主键 */
	private Long id;

	/** 创建人 */
	private String owner;

	/** 创建人所属机构 */
	private String ownerGroup;

	/** 创建时间 */
	private Date createTime;

	/** 最后修改时间 */
	private Date lastUpdateTime;

	/** 交易流水表主键 */
	private Long tnsLogId;

	/** 资金账户帐号 客户账户填写子帐户号，商户账户、机构账户和科目账户填写内部账户号 */
	private String accId;

	/** 账户类型 0-客户子帐户号 1-商户账户 2-机构账户 3-科目账户 */
	private Integer accType;

	/** 登记类型 O-开户 C-销户 */
	private String ocrType;

	/** 开户账期 */
	private Date openAccDate;

	/** 销户账期 */
	private Date closeAccDate;

	/** 币种 */
	private String cur;

	/** 上级科目号 */
	private String upItemId;

	/** 开户后余额 */
	private BigDecimal openBal;

	/** 销户后余额 */
	private BigDecimal closeBal;

	/** 备注 */
	private String memo;

	/** 删除标识 0-正常 1-删除 */
	private Boolean delFlag;

	/** 预留字段1 */
	private String filler1;

	/** 预留字段2 */
	private String filler2;

	/** 预留字段3 */
	private String filler3;

	/**  */
	private Integer version;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner == null ? null : owner.trim();
	}

	public String getOwnerGroup() {
		return ownerGroup;
	}

	public void setOwnerGroup(String ownerGroup) {
		this.ownerGroup = ownerGroup == null ? null : ownerGroup.trim();
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

	public Long getTnsLogId() {
		return tnsLogId;
	}

	public void setTnsLogId(Long tnsLogId) {
		this.tnsLogId = tnsLogId;
	}

	public String getAccId() {
		return accId;
	}

	public void setAccId(String accId) {
		this.accId = accId == null ? null : accId.trim();
	}

	public Integer getAccType() {
		return accType;
	}

	public void setAccType(Integer accType) {
		this.accType = accType;
	}

	public String getOcrType() {
		return ocrType;
	}

	public void setOcrType(String ocrType) {
		this.ocrType = ocrType == null ? null : ocrType.trim();
	}

	public Date getOpenAccDate() {
		return openAccDate;
	}

	public void setOpenAccDate(Date openAccDate) {
		this.openAccDate = openAccDate;
	}

	public Date getCloseAccDate() {
		return closeAccDate;
	}

	public void setCloseAccDate(Date closeAccDate) {
		this.closeAccDate = closeAccDate;
	}

	public String getCur() {
		return cur;
	}

	public void setCur(String cur) {
		this.cur = cur == null ? null : cur.trim();
	}

	public String getUpItemId() {
		return upItemId;
	}

	public void setUpItemId(String upItemId) {
		this.upItemId = upItemId == null ? null : upItemId.trim();
	}

	public BigDecimal getOpenBal() {
		return openBal;
	}

	public void setOpenBal(BigDecimal openBal) {
		this.openBal = openBal;
	}

	public BigDecimal getCloseBal() {
		return closeBal;
	}

	public void setCloseBal(BigDecimal closeBal) {
		this.closeBal = closeBal;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo == null ? null : memo.trim();
	}

	public Boolean getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Boolean delFlag) {
		this.delFlag = delFlag;
	}

	public String getFiller1() {
		return filler1;
	}

	public void setFiller1(String filler1) {
		this.filler1 = filler1 == null ? null : filler1.trim();
	}

	public String getFiller2() {
		return filler2;
	}

	public void setFiller2(String filler2) {
		this.filler2 = filler2 == null ? null : filler2.trim();
	}

	public String getFiller3() {
		return filler3;
	}

	public void setFiller3(String filler3) {
		this.filler3 = filler3 == null ? null : filler3.trim();
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "TnsOcrLog [id=" + id + ", owner=" + owner + ", ownerGroup="
				+ ownerGroup + ", createTime=" + createTime
				+ ", lastUpdateTime=" + lastUpdateTime + ", tnsLogId="
				+ tnsLogId + ", accId=" + accId + ", accType=" + accType
				+ ", ocrType=" + ocrType + ", openAccDate=" + openAccDate
				+ ", closeAccDate=" + closeAccDate + ", cur=" + cur
				+ ", upItemId=" + upItemId + ", openBal=" + openBal
				+ ", closeBal=" + closeBal + "]";
	}
}
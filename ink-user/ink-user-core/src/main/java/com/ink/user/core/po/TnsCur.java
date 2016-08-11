package com.ink.user.core.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TnsCur implements Serializable {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -4732786395845079619L;

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

	/** 货币码 */
	private String cur;

	/** 符号 */
	private String sym;

	/** 辅币单位 */
	private BigDecimal secUnit;

	/** 买入单位 */
	private BigDecimal purUnit;

	/** 可否兑换 Y－可兑换 N－不可以 */
	private Integer exgFlg;

	/** 最小整钞单位 */
	private BigDecimal minBillUnit;

	/** 最小兑换单位 */
	private BigDecimal minExgUnit;

	/** 最小零钞单位 */
	private BigDecimal minChgUnit;

	/** 进位单位 */
	private BigDecimal run;

	/** 中文描述 */
	private String chDesc;

	/** 英文描述 */
	private String enDesc;

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

	public String getCur() {
		return cur;
	}

	public void setCur(String cur) {
		this.cur = cur == null ? null : cur.trim();
	}

	public String getSym() {
		return sym;
	}

	public void setSym(String sym) {
		this.sym = sym == null ? null : sym.trim();
	}

	public BigDecimal getSecUnit() {
		return secUnit;
	}

	public void setSecUnit(BigDecimal secUnit) {
		this.secUnit = secUnit;
	}

	public BigDecimal getPurUnit() {
		return purUnit;
	}

	public void setPurUnit(BigDecimal purUnit) {
		this.purUnit = purUnit;
	}

	public Integer getExgFlg() {
		return exgFlg;
	}

	public void setExgFlg(Integer exgFlg) {
		this.exgFlg = exgFlg;
	}

	public BigDecimal getMinBillUnit() {
		return minBillUnit;
	}

	public void setMinBillUnit(BigDecimal minBillUnit) {
		this.minBillUnit = minBillUnit;
	}

	public BigDecimal getMinExgUnit() {
		return minExgUnit;
	}

	public void setMinExgUnit(BigDecimal minExgUnit) {
		this.minExgUnit = minExgUnit;
	}

	public BigDecimal getMinChgUnit() {
		return minChgUnit;
	}

	public void setMinChgUnit(BigDecimal minChgUnit) {
		this.minChgUnit = minChgUnit;
	}

	public BigDecimal getRun() {
		return run;
	}

	public void setRun(BigDecimal run) {
		this.run = run;
	}

	public String getChDesc() {
		return chDesc;
	}

	public void setChDesc(String chDesc) {
		this.chDesc = chDesc == null ? null : chDesc.trim();
	}

	public String getEnDesc() {
		return enDesc;
	}

	public void setEnDesc(String enDesc) {
		this.enDesc = enDesc == null ? null : enDesc.trim();
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "TnsCur [id=" + id + ", owner=" + owner + ", ownerGroup="
				+ ownerGroup + ", createTime=" + createTime
				+ ", lastUpdateTime=" + lastUpdateTime + ", cur=" + cur
				+ ", sym=" + sym + ", secUnit=" + secUnit + ", purUnit="
				+ purUnit + ", exgFlg=" + exgFlg + ", minBillUnit="
				+ minBillUnit + ", minExgUnit=" + minExgUnit + ", minChgUnit="
				+ minChgUnit + ", run=" + run + ", chDesc=" + chDesc
				+ ", enDesc=" + enDesc + "]";
	}

}
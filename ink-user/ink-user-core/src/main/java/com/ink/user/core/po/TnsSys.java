package com.ink.user.core.po;

import java.io.Serializable;
import java.util.Date;

public class TnsSys implements Serializable {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 6972124144283192703L;

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

	/** 系统编号 */
	private String sysId;

	/** 系统名称 */
	private String sysName;

	/** 当前工作日期 */
	private Date accDate;

	/** 上一工作日期 */
	private Date lstAccDate;

	/** 下一工作日期 */
	private Date nxtAccDate;

	/** 系统工作状态 1-联机 2-批处理 4-恢复 */
	private Integer sysStatus;

	/** 系统跟踪级别 应用系统中大于或等于此级别的信息记录在log中最大值为9，即只有最重要的信息才记录在log中 */
	private Integer traceLv;

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

	public String getSysId() {
		return sysId;
	}

	public void setSysId(String sysId) {
		this.sysId = sysId == null ? null : sysId.trim();
	}

	public String getSysName() {
		return sysName;
	}

	public void setSysName(String sysName) {
		this.sysName = sysName == null ? null : sysName.trim();
	}

	public Date getAccDate() {
		return accDate;
	}

	public void setAccDate(Date accDate) {
		this.accDate = accDate;
	}

	public Date getLstAccDate() {
		return lstAccDate;
	}

	public void setLstAccDate(Date lstAccDate) {
		this.lstAccDate = lstAccDate;
	}

	public Date getNxtAccDate() {
		return nxtAccDate;
	}

	public void setNxtAccDate(Date nxtAccDate) {
		this.nxtAccDate = nxtAccDate;
	}

	public Integer getSysStatus() {
		return sysStatus;
	}

	public void setSysStatus(Integer sysStatus) {
		this.sysStatus = sysStatus;
	}

	public Integer getTraceLv() {
		return traceLv;
	}

	public void setTraceLv(Integer traceLv) {
		this.traceLv = traceLv;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "TnsSys [id=" + id + ", owner=" + owner + ", ownerGroup="
				+ ownerGroup + ", createTime=" + createTime
				+ ", lastUpdateTime=" + lastUpdateTime + ", sysId=" + sysId
				+ ", sysName=" + sysName + ", accDate=" + accDate
				+ ", lstAccDate=" + lstAccDate + ", nxtAccDate=" + nxtAccDate
				+ ", sysStatus=" + sysStatus + ", traceLv=" + traceLv + "]";
	}
}
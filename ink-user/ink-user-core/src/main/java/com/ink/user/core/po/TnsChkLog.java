package com.ink.user.core.po;

import java.io.Serializable;
import java.util.Date;

public class TnsChkLog implements Serializable {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -2606914213372544725L;

	/** 数据库表主键 */
	private Long id;

	/** 流程运行实例的id */
	private String flowId;

	/** 流程节点工作项id，每次接受任务时流程都会产生一个此流程下的唯一id */
	private String flowTaskId;

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

	/** 交易代码 */
	private String txnCode;

	/** 记帐日期 */
	private Date accDate;

	/** 交易渠道 */
	private String channel;

	/** 复核状态 0-申请 1-拒绝 2-成功 3-失败 */
	private Integer chkStatus;

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

	/** 交易报文体 */
	private String txnBuf;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFlowId() {
		return flowId;
	}

	public void setFlowId(String flowId) {
		this.flowId = flowId == null ? null : flowId.trim();
	}

	public String getFlowTaskId() {
		return flowTaskId;
	}

	public void setFlowTaskId(String flowTaskId) {
		this.flowTaskId = flowTaskId == null ? null : flowTaskId.trim();
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

	public String getTxnCode() {
		return txnCode;
	}

	public void setTxnCode(String txnCode) {
		this.txnCode = txnCode == null ? null : txnCode.trim();
	}

	public Date getAccDate() {
		return accDate;
	}

	public void setAccDate(Date accDate) {
		this.accDate = accDate;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel == null ? null : channel.trim();
	}

	public Integer getChkStatus() {
		return chkStatus;
	}

	public void setChkStatus(Integer chkStatus) {
		this.chkStatus = chkStatus;
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

	public String getTxnBuf() {
		return txnBuf;
	}

	public void setTxnBuf(String txnBuf) {
		this.txnBuf = txnBuf == null ? null : txnBuf.trim();
	}

	@Override
	public String toString() {
		return "TnsChkLog [id=" + id + ", flowId=" + flowId + ", flowTaskId="
				+ flowTaskId + ", owner=" + owner + ", ownerGroup="
				+ ownerGroup + ", createTime=" + createTime
				+ ", lastUpdateTime=" + lastUpdateTime + ", tnsLogId="
				+ tnsLogId + ", txnCode=" + txnCode + ", accDate=" + accDate
				+ ", channel=" + channel + ", chkStatus=" + chkStatus + "]";
	}
}
package com.ink.user.core.po;

import java.io.Serializable;
import java.util.Date;

public class TnsAceGen implements Serializable {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 4624623243579320902L;

	/** 数据库表主键 */
	private String id;

	/** 创建人 */
	private String owner;

	/** 创建人所属机构 */
	private String ownerGroup;

	/** 创建时间 */
	private Date createTime;

	/** 最后修改时间 */
	private Date lastUpdateTime;

	/** 联机交易代码表主键 */
	private Long tnsTxnId;

	/** 交易代码 */
	private String txnCode;

	/** 分录组号 */
	private String aceGroup;

	/** 交易金额方向 D-借 C-贷 */
	private String dir;

    /** 账户方向 P-账户方 A-对转方 */
    private String accDir;

	/** 账户类型 0-客户账户 1-商户账户 2-机构账户 3-科目账户 */
	private Integer accType;

	/** 账户科目号, 类型为科目账户时必填 */
	private String accItemId;

	/** 对方账户类型 0-客户账户 1-商户账户 2-机构账户 3-科目账户 */
	private Integer agaType;

	/** 对方账户科目号, 类型为科目账户时必填 */
	private String agaItemId;

	/** 实时标志 0-实时入账 1-异步入账 */
	private Integer runtimeFlg;

	/** 冲正标志 N-正常 Y-红字 B-蓝字 */
	private String revFlg;

	/**  */
	private Integer version;
	
	private String chargeType;
	//上级科目号
	private String upItemId;
	//科目号
	private String itemId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public Long getTnsTxnId() {
		return tnsTxnId;
	}

	public void setTnsTxnId(Long tnsTxnId) {
		this.tnsTxnId = tnsTxnId;
	}

	public String getTxnCode() {
		return txnCode;
	}

	public void setTxnCode(String txnCode) {
		this.txnCode = txnCode == null ? null : txnCode.trim();
	}

	public String getAceGroup() {
		return aceGroup;
	}

	public void setAceGroup(String aceGroup) {
		this.aceGroup = aceGroup == null ? null : aceGroup.trim();
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir == null ? null : dir.trim();
	}

    public String getAccDir() {
        return accDir;
    }

    public void setAccDir(String accDir) {
        this.accDir = accDir == null ? null : accDir.trim();
    }

	public Integer getAccType() {
		return accType;
	}

	public void setAccType(Integer accType) {
		this.accType = accType;
	}

	public String getAccItemId() {
		return accItemId;
	}

	public void setAccItemId(String accItemId) {
		this.accItemId = accItemId == null ? null : accItemId.trim();
	}

	public Integer getAgaType() {
		return agaType;
	}

	public void setAgaType(Integer agaType) {
		this.agaType = agaType;
	}

	public String getAgaItemId() {
		return agaItemId;
	}

	public void setAgaItemId(String agaItemId) {
		this.agaItemId = agaItemId == null ? null : agaItemId.trim();
	}

	public Integer getRuntimeFlg() {
		return runtimeFlg;
	}

	public void setRuntimeFlg(Integer runtimeFlg) {
		this.runtimeFlg = runtimeFlg;
	}

	public String getRevFlg() {
		return revFlg;
	}

	public void setRevFlg(String revFlg) {
		this.revFlg = revFlg == null ? null : revFlg.trim();
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getChargeType() {
		return chargeType;
	}

	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}
	

	public String getUpItemId() {
		return upItemId;
	}

	public void setUpItemId(String upItemId) {
		this.upItemId = upItemId;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	@Override
	public String toString() {
		return "TnsAceGen [id=" + id + ", owner=" + owner + ", ownerGroup="
				+ ownerGroup + ", createTime=" + createTime
				+ ", lastUpdateTime=" + lastUpdateTime + ", tnsTxnId="
				+ tnsTxnId + ", txnCode=" + txnCode + ", aceGroup=" + aceGroup
				+ ", dir=" + dir + ", accType=" + accType + ", accItemId="
				+ accItemId + ", agaType=" + agaType + ", agaItemId="
				+ agaItemId + "]";
	}
}